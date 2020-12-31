/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.sales.controller.transactions;

import com.itgarden.ERP.module.inventory.model.settings.Items;
import com.itgarden.ERP.module.inventory.repository.settings.ItemsRepository;
import com.itgarden.ERP.module.inventory.settings.services.InventoryLocationsService;
import com.itgarden.ERP.module.inventory.settings.services.ItemService;
import com.itgarden.ERP.module.sales.cart.model.SalesCartItem;
import com.itgarden.ERP.module.sales.model.enumvalue.SalesTypeName;
import com.itgarden.ERP.module.sales.model.settings.CustomerBranches;
import com.itgarden.ERP.module.sales.model.transactions.SalesOrder;
import com.itgarden.ERP.module.sales.model.transactions.SalesOrderItem;
import com.itgarden.ERP.module.sales.repository.settings.CustomersRepository;
import com.itgarden.ERP.module.sales.repository.transactions.SalesInvoiceItemRepository;
import com.itgarden.ERP.module.sales.repository.transactions.SalesInvoiceRepository;
import com.itgarden.ERP.module.sales.repository.transactions.SalesOrderItemRepository;
import com.itgarden.ERP.module.settings.model.company_setup.TransactionsType;
import com.itgarden.ERP.module.settings.model.miscellaneous.ShippingCompany;
import com.itgarden.ERP.module.settings.repository.miscellaneous.PaymentTermsRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.itgarden.ERP.module.sales.repository.transactions.SalesOrderRepository;
import com.itgarden.ERP.module.sales.repository.transactions.SalesQuotationEntryRepository;
import com.itgarden.ERP.module.sales.settings.services.CustomerService;
import com.itgarden.ERP.module.settings.repository.company_setup.TransactionsTypeRepository;
import com.itgarden.ERP.module.settings.repository.miscellaneous.ShippingCompanyRepository;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.validation.ObjectError;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/salesorder")
public class SalesOrderController {

    // Repository 
    @Autowired
    InventoryLocationsService inventoryLocationsService;

    @Autowired
    CustomersRepository customersRepository;

    @Autowired
    PaymentTermsRepository paymentTermsRepository;

    @Autowired
    SalesQuotationEntryRepository salesQuotationEntryRepository;

    @Autowired
    TransactionsTypeRepository transactionsTypeRepository;

    @Autowired
    ShippingCompanyRepository shippingCompanyRepository;

    @Autowired
    ItemsRepository itemsRepository;

    // service
    @Autowired
    CustomerService customerService;

    @Autowired
    ItemService itemService;

    @Autowired
    SalesInvoiceRepository salesInvoiceRepository;

    @Autowired
    SalesInvoiceItemRepository salesInvoiceItemRepository;

    @Autowired
    SalesOrderRepository salesOrderRepository;

    @Autowired
    SalesOrderItemRepository salesOrderItemRepository;

    @RequestMapping("/salesorderlist")
    public String index(Model model) {

        model.addAttribute("salesorerlist", salesOrderRepository.findAll());

        return "module/sales/transactions/sales_order_list";
    }

    @RequestMapping("/createorder")
    public String createorder(Model model, SalesOrder salesOrder) {

        model.addAttribute("customerlist", customersRepository.findAll());

        model.addAttribute("paymenttramlist", paymentTermsRepository.findAll());

        model.addAttribute("pricetypelist", SalesTypeName.values());

        model.addAttribute("inventoryLocationlist", inventoryLocationsService.activelocationlist());

        model.addAttribute("shippingCompanylist", shippingCompanyRepository.findAll());

        model.addAttribute("itemList", itemService.allActiveAndSalableItemList(false, false));

        ///   set  transactions Type
        TransactionsType transactionsType = new TransactionsType();

        transactionsType = transactionsTypeRepository.findBySlug("sales-invoice");
        salesOrder.setTransactionsType(transactionsType);

        SalesCartItem salesCartItem = new SalesCartItem();

        model.addAttribute("salesCartItem", salesCartItem);

        return "module/sales/transactions/create_order";
    }

    @RequestMapping("/save")
    public String save(Model model, @Valid SalesOrder salesOrder,
            BindingResult bindingResult,
            RedirectAttributes redirectAttrs, HttpSession salesItemSession) {

        List<SalesCartItem> cartItemList = (List<SalesCartItem>) salesItemSession.getAttribute("salesItemCartSession");

        if (cartItemList == null) {

            ObjectError cartItemListError;

            cartItemListError = new ObjectError("salesInvoiceItem", "Sales quotation items are empty");

            bindingResult.addError(cartItemListError);
        }

        if (bindingResult.hasErrors()) {

            model.addAttribute("customerlist", customersRepository.findAll());

            model.addAttribute("paymenttramlist", paymentTermsRepository.findAll());

            model.addAttribute("pricetypelist", SalesTypeName.values());

            model.addAttribute("inventoryLocationlist", inventoryLocationsService.activelocationlist());

            model.addAttribute("shippingCompanylist", shippingCompanyRepository.findAll());

            model.addAttribute("itemList", itemService.allActiveAndSalableItemList(false, false));

            ///   set  transactions Type
            TransactionsType transactionsType = new TransactionsType();

            transactionsType = transactionsTypeRepository.findBySlug("sales-invoice");
            salesOrder.setTransactionsType(transactionsType);

            return "module/sales/transactions/create_order";
        }

        salesOrderRepository.save(salesOrder);

        SalesOrder lastinsertid = salesOrderRepository.findTopByOrderByOrdernoDesc();
        Long orderid = lastinsertid.getOrderno();

        List<SalesOrderItem> salesOrderItemList = new ArrayList<SalesOrderItem>();

        for (SalesCartItem cartItemLists : cartItemList) {

            SalesOrderItem salesOrderItem = new SalesOrderItem();
            // 1
            salesOrderItem.setSalesOrder(lastinsertid);

            TransactionsType transactionsTypeid = new TransactionsType();

            transactionsTypeid.setId(254L);

            salesOrderItem.setTransactionsType(transactionsTypeid);

            Items items = new Items();

            items.setId(cartItemLists.getId());

            //2 
            salesOrderItem.setItemId(items);

            //3
            salesOrderItem.setDescription("");

            //4 
            salesOrderItem.setUnitPrice(cartItemLists.getPrice());
            // 5 
            salesOrderItem.setUnitTax(cartItemLists.getTaxPercent());
            //6 

            salesOrderItem.setQuantity(cartItemLists.getQuantity());
            //7

            salesOrderItem.setDiscount_percent(cartItemLists.getDiscountPercent());

            salesOrderItemList.add(salesOrderItem);

            //salesQuotationItemRepository.saveAll(salesQuotationItemList);
        }

        salesOrderItemRepository.saveAll(salesOrderItemList);

        salesItemSession.removeAttribute("salesItemCartSession");
        salesItemSession.removeAttribute("salesItemCartSessionTotalDiscount");
        salesItemSession.removeAttribute("salesItemCartSessionTotalVat");
        salesItemSession.removeAttribute("salesItemCartSessionItemTotal");

        redirectAttrs.addAttribute("id", orderid).addFlashAttribute("success_messages", "Successfully Save.");

        return "redirect:/salesorder/view/{id}";
    }

    @RequestMapping("/salesorderdetails/{id}")
    public String salesorderdetails(Model model, @PathVariable Long id, SalesOrder salesOrder) {

        model.addAttribute("salesOrder", salesOrderRepository.getOne(id));

        return "module/sales/transactions/sales_order_details";
    }

    @RequestMapping("/edit/{id}")

    public String edit(Model model, @PathVariable Long id, SalesOrder salesOrderEntry) {

        model.addAttribute("salesOrderEntry", salesOrderRepository.getOne(id));

        model.addAttribute("customerlist", customersRepository.findAll());

        model.addAttribute("pamenttramlist", paymentTermsRepository.findAll());

        model.addAttribute("pricetypelist", SalesTypeName.values());

        ///   set  transactions Type
        TransactionsType transactionsType = new TransactionsType();
        transactionsType.setId(165L);
        salesOrderEntry.setTransactionsType(transactionsType);

        //  customer refarance 
        CustomerBranches customerBranches = new CustomerBranches();
        customerBranches.setId(162L);
        salesOrderEntry.setBranchCode(customerBranches);

        //  Shiping Company
        ShippingCompany shippingCompany = new ShippingCompany();
        shippingCompany.setId(163L);
        salesOrderEntry.setShipVia(shippingCompany);
        return "module/sales/transactions/create_order";

    }

    @GetMapping(value = "/delete/{id}")
    public String delete(Model model, @PathVariable Long id, SalesOrder salesOrder, RedirectAttributes redirectAttrs) {
        redirectAttrs.addFlashAttribute("success_messages", " Successfully Delete.");
        salesOrderRepository.deleteById(id);
        return "redirect:/salesorder/salesorderlist";

    }

    @GetMapping(value = "/view/{id}")
    public String view(Model model, @PathVariable Long id, SalesOrder salesOrder) {

        return "module/sales/transactions/salesorderview";
    }

    @GetMapping(value = "/close")
    public String close(Model model, HttpSession salesItemSession) {

        if (salesItemSession.getAttribute("salesItemCartSession") != null) {

            salesItemSession.removeAttribute("salesItemCartSession");

        }

        if (salesItemSession.getAttribute("salesItemCartSessionTotalDiscount") != null) {

            salesItemSession.removeAttribute("salesItemCartSessionTotalDiscount");

        }

        if (salesItemSession.getAttribute("salesItemCartSessionTotalVat") != null) {

            salesItemSession.removeAttribute("salesItemCartSessionTotalVat");

        }

        if (salesItemSession.getAttribute("salesItemCartSessionItemTotal") != null) {

            salesItemSession.removeAttribute("salesItemCartSessionItemTotal");

        }

        return "redirect:/sales/index";
    }

}
