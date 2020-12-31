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
import com.itgarden.ERP.module.sales.model.transactions.SalesQuotationEntry;
import com.itgarden.ERP.module.sales.model.transactions.SalesQuotationItem;
import com.itgarden.ERP.module.sales.repository.settings.CustomersRepository;
import com.itgarden.ERP.module.sales.repository.transactions.SalesQuotationEntryRepository;
import com.itgarden.ERP.module.sales.repository.transactions.SalesQuotationItemRepository;
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
@RequestMapping("/salesquotationentry")
public class SalesQuotationEntryController {

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
    SalesQuotationItemRepository salesQuotationItemRepository;

    @RequestMapping(value = {"", "/", "/index"})
    public String index(Model model, SalesQuotationEntry salesQuotationEntry, SalesQuotationItem salesQuotationItem, HttpSession salesSession) {

        model.addAttribute("customerlist", customersRepository.findAll());

        model.addAttribute("paymenttramlist", paymentTermsRepository.findAll());

        model.addAttribute("pricetypelist", SalesTypeName.values());

        model.addAttribute("inventoryLocationlist", inventoryLocationsService.activelocationlist());

        model.addAttribute("shippingCompanylist", shippingCompanyRepository.findAll());

        model.addAttribute("itemList", itemService.allActiveAndSalableItemList(false, false));

        ///   set  transactions Type
        TransactionsType transactionsType = new TransactionsType();

        transactionsType = transactionsTypeRepository.findBySlug("sales-quotation");
        salesQuotationEntry.setTransactionsType(transactionsType);

        SalesCartItem salesCartItem = new SalesCartItem();

        model.addAttribute("salesCartItem", salesCartItem);

        return "module/sales/transactions/salesquotationentry";
    }

    @RequestMapping("/save")
    public String save(Model model, @Valid SalesQuotationEntry salesQuotationEntry, BindingResult bindingResult,
            RedirectAttributes redirectAttrs, HttpSession salesItemSession) {

        List<SalesCartItem> cartItemList = (List<SalesCartItem>) salesItemSession.getAttribute("salesItemCartSession");

        if (cartItemList == null) {

            ObjectError cartItemListError;

            cartItemListError = new ObjectError("salesQuotationItem", "Sales quotation items are empty");

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

            transactionsType = transactionsTypeRepository.findBySlug("sales-quotation");
            salesQuotationEntry.setTransactionsType(transactionsType);

            return "module/sales/transactions/salesquotationentry";
        }

        salesQuotationEntryRepository.save(salesQuotationEntry);

        SalesQuotationEntry lastinsertid = salesQuotationEntryRepository.findTopByOrderByQtnoDesc();
        Long qtid = lastinsertid.getQtno();

        List<SalesQuotationItem> salesQuotationItemList = new ArrayList<SalesQuotationItem>();

        for (SalesCartItem cartItemLists : cartItemList) {

            SalesQuotationItem salesQuotationItem = new SalesQuotationItem();
            // 1
            salesQuotationItem.setSalesQuotationEntry(lastinsertid);

            TransactionsType transactionsTypeid = new TransactionsType();

            transactionsTypeid.setId(254L);

            salesQuotationItem.setTransactionsType(transactionsTypeid);

            Items items = new Items();

            items.setId(cartItemLists.getId());

            //2 
            salesQuotationItem.setItemId(items);

            //3
            salesQuotationItem.setDescription("");

            //4 
            salesQuotationItem.setUnitPrice(cartItemLists.getPrice());
            // 5 
            salesQuotationItem.setUnitTax(cartItemLists.getTaxPercent());
            //6 

            salesQuotationItem.setQuantity(cartItemLists.getQuantity());
            //7

            salesQuotationItem.setDiscount_percent(cartItemLists.getDiscountPercent());

            salesQuotationItemList.add(salesQuotationItem);

            //salesQuotationItemRepository.saveAll(salesQuotationItemList);
        }

        salesQuotationItemRepository.saveAll(salesQuotationItemList);

        salesItemSession.removeAttribute("salesItemCartSession");
        salesItemSession.removeAttribute("salesItemCartSessionTotalDiscount");
        salesItemSession.removeAttribute("salesItemCartSessionTotalVat");
        salesItemSession.removeAttribute("salesItemCartSessionItemTotal");

        redirectAttrs.addAttribute("id", qtid).addFlashAttribute("success_messages", "Successfully Save.");

        return "redirect:/salesquotationentry/view/{id}";
    }

    @RequestMapping("/edit/{id}")

    public String edit(Model model, @PathVariable Long id, SalesQuotationEntry salesQuotationEntry, SalesQuotationItem salesQuotationItem) {

        model.addAttribute("salesQuotationEntry", salesQuotationEntryRepository.getOne(id));

        model.addAttribute("customerlist", customersRepository.findAll());

        model.addAttribute("paymenttramlist", paymentTermsRepository.findAll());

        model.addAttribute("pricetypelist", SalesTypeName.values());

        model.addAttribute("inventoryLocationlist", inventoryLocationsService.activelocationlist());

        model.addAttribute("shippingCompanylist", shippingCompanyRepository.findAll());

        model.addAttribute("itemList", itemService.allActiveAndSalableItemList(false, false));

        ///   set  transactions Type
        TransactionsType transactionsType = new TransactionsType();

        transactionsType = transactionsTypeRepository.findBySlug("sales-quotation");
        salesQuotationEntry.setTransactionsType(transactionsType);

        return "module/sales/transactions/salesquotationentry";

    }

    @GetMapping(value = "/delete/{id}")
    public String delete(Model model, @PathVariable Long id, SalesQuotationEntry salesQuotationEntry, RedirectAttributes redirectAttrs) {
        redirectAttrs.addFlashAttribute("success_messages", " Successfully Delete.");

        salesQuotationEntryRepository.deleteById(id);

        return "redirect:/salesquotationentry/index";

    }

    @GetMapping(value = "/view/{id}")
    public String view(Model model, @PathVariable Long id, SalesQuotationEntry salesQuotationEntry) {

        model.addAttribute("qtId", id);

        return "module/sales/transactions/salesqtview";
    }

    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable Long id, SalesQuotationEntry salesQuotationEntry) {

        model.addAttribute("salesQuotation", salesQuotationEntryRepository.getOne(id));

        return "module/sales/transactions/sales_quotation_details";
    }

    @GetMapping(value = "/pdf/{id}")
    public String createpdf(Model model, @PathVariable Long id, SalesQuotationEntry salesQuotationEntry) {

        model.addAttribute("qtId", id);

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
