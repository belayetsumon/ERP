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
import com.itgarden.ERP.module.sales.model.transactions.SalesInvoice;
import com.itgarden.ERP.module.sales.model.transactions.SalesInvoiceItem;
import com.itgarden.ERP.module.sales.repository.settings.CustomersRepository;
import com.itgarden.ERP.module.sales.repository.transactions.SalesInvoiceItemRepository;
import com.itgarden.ERP.module.sales.repository.transactions.SalesInvoiceRepository;
import com.itgarden.ERP.module.sales.repository.transactions.SalesQuotationEntryRepository;
import com.itgarden.ERP.module.sales.settings.services.CustomerService;
import com.itgarden.ERP.module.settings.model.company_setup.TransactionsType;
import com.itgarden.ERP.module.settings.repository.company_setup.TransactionsTypeRepository;
import com.itgarden.ERP.module.settings.repository.miscellaneous.PaymentTermsRepository;
import com.itgarden.ERP.module.settings.repository.miscellaneous.ShippingCompanyRepository;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/salesinvoice")
public class SalesInvoiceController {

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


    @RequestMapping(value = {"", "/", "/index"})
    public String index(Model model, SalesInvoice salesInvoice) {
        model.addAttribute("customerlist", customersRepository.findAll());

        model.addAttribute("paymenttramlist", paymentTermsRepository.findAll());

        model.addAttribute("pricetypelist", SalesTypeName.values());

        model.addAttribute("inventoryLocationlist", inventoryLocationsService.activelocationlist());

        model.addAttribute("shippingCompanylist", shippingCompanyRepository.findAll());

        model.addAttribute("itemList", itemService.allActiveAndSalableItemList(false, false));

        ///   set  transactions Type
        TransactionsType transactionsType = new TransactionsType();

        transactionsType = transactionsTypeRepository.findBySlug("sales-invoice");
        salesInvoice.setTransactionsType(transactionsType);

        SalesCartItem salesCartItem = new SalesCartItem();

        model.addAttribute("salesCartItem", salesCartItem);
        
        
        return "module/sales/transactions/salesinvoice";
    }

    @RequestMapping("/save")
    public String save(Model model, @Valid SalesInvoice salesInvoice, BindingResult bindingResult,
            RedirectAttributes redirectAttrs, HttpSession salesItemSession) {

        List<SalesCartItem> cartItemList = (List<SalesCartItem>) salesItemSession.getAttribute("salesItemCartSession");

        if (cartItemList == null) {

            ObjectError cartItemListError;

            cartItemListError = new ObjectError("salesInvoiceItem", "You must enter at least one non empty item line.");

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
            salesInvoice.setTransactionsType(transactionsType);

            return "module/sales/transactions/salesinvoice";
        }

        salesInvoiceRepository.save(salesInvoice);

        SalesInvoice lastinsertid = salesInvoiceRepository.findTopByOrderByInvoicenoDesc();
        Long qtid = lastinsertid.getInvoiceno();

        List<SalesInvoiceItem> salesInvoiceItemList = new ArrayList<SalesInvoiceItem>();

        for (SalesCartItem cartItemLists : cartItemList) {

            SalesInvoiceItem salesInvoiceItem = new SalesInvoiceItem();
            // 1
            salesInvoiceItem.setSalesInvoice(lastinsertid);

            TransactionsType transactionsTypeid = new TransactionsType();

            transactionsTypeid.setId(254L);

            salesInvoiceItem.setTransactionsType(transactionsTypeid);

            Items items = new Items();

            items.setId(cartItemLists.getId());

            //2 
            salesInvoiceItem.setItemId(items);

            //3
            salesInvoiceItem.setDescription("");

            //4 
            salesInvoiceItem.setUnitPrice(cartItemLists.getPrice());
            // 5 
            salesInvoiceItem.setUnitTax(cartItemLists.getTaxPercent());
            //6 

            salesInvoiceItem.setQuantity(cartItemLists.getQuantity());
            //7

            salesInvoiceItem.setDiscount_percent(cartItemLists.getDiscountPercent());

            salesInvoiceItemList.add(salesInvoiceItem);

            //salesQuotationItemRepository.saveAll(salesQuotationItemList);
        }

        salesInvoiceItemRepository.saveAll(salesInvoiceItemList);

        salesItemSession.removeAttribute("salesItemCartSession");
        salesItemSession.removeAttribute("salesItemCartSessionTotalDiscount");
        salesItemSession.removeAttribute("salesItemCartSessionTotalVat");
        salesItemSession.removeAttribute("salesItemCartSessionItemTotal");

        redirectAttrs.addAttribute("id", qtid).addFlashAttribute("success_messages", "Successfully Save.");

        return "redirect:/salesinvoice/view/{id}";
    }
    
    
    
    
    
    @GetMapping(value = "/delete/{id}")
    public String delete(Model model, @PathVariable Long id, SalesInvoice salesInvoice, RedirectAttributes redirectAttrs) {
        redirectAttrs.addFlashAttribute("success_messages", " Successfully Delete.");

        salesInvoiceRepository.deleteById(id);

        return "redirect:/salesquotationentry/index";

    }
    
    
    
    
    @GetMapping(value = "/view/{id}")
    public String view(Model model, @PathVariable Long id, SalesInvoice salesInvoice) {

        model.addAttribute("inId", id);

        return "module/sales/transactions/invoiceview";
    }

    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable Long id, SalesInvoice salesInvoice) {

        model.addAttribute("salesInvoice", salesInvoiceRepository.getOne(id));

        return "module/sales/transactions/sales_invoice_details";
    }

    @GetMapping(value = "/pdf/{id}")
    public String createpdf(Model model, @PathVariable Long id, SalesInvoice salesInvoice) {

        model.addAttribute("inId", id);

        return "module/sales/transactions/salesqtview";
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
