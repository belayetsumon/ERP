/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.branch.controller.transactions;

import com.itgarden.ERP.module.inventory.model.settings.InventoryLocations;
import com.itgarden.ERP.module.inventory.model.settings.Items;
import com.itgarden.ERP.module.inventory.repository.settings.InventoryLocationsRepository;
import com.itgarden.ERP.module.inventory.repository.settings.ItemsRepository;
import com.itgarden.ERP.module.inventory.settings.services.InventoryLocationsService;
import com.itgarden.ERP.module.inventory.settings.services.ItemService;
import com.itgarden.ERP.module.sales.cart.model.SalesCartItem;
import com.itgarden.ERP.module.sales.model.enumvalue.SalesTypeName;
import com.itgarden.ERP.module.sales.model.transactions.Pos;
import com.itgarden.ERP.module.sales.model.transactions.PosItem;
import com.itgarden.ERP.module.sales.repository.settings.CustomersRepository;
import com.itgarden.ERP.module.sales.repository.transactions.PosItemRepository;
import com.itgarden.ERP.module.sales.repository.transactions.PosRepository;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/branchpos")
public class BranchPosController {

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
    PosRepository posRepository;

    @Autowired
    PosItemRepository posItemRepository;

    @Autowired
    InventoryLocationsRepository inventoryLocationsRepository;
    

    

    @RequestMapping("/index")
    public String index(Model model, Pos pos) {
        model.addAttribute("customerlist", customersRepository.findAll());

        model.addAttribute("pricetypelist", SalesTypeName.values());

        model.addAttribute("inventoryLocationlist", inventoryLocationsService.activelocationlist());

        model.addAttribute("shippingCompanylist", shippingCompanyRepository.findAll());

        model.addAttribute("itemList", itemService.allActiveAndSalableItemList(false, false));

        // set location
        InventoryLocations inventoryLocations = inventoryLocationsRepository.findByLocationCode("jamuna-2");

        pos.setFromStkLoc(inventoryLocations);
        
        pos.setPaymentTerms(paymentTermsRepository.findBySlug("cash"));

        ///   set  transactions Type
        TransactionsType transactionsType = new TransactionsType();
        transactionsType = transactionsTypeRepository.findBySlug("pos");
        pos.setTransactionsType(transactionsType);
        SalesCartItem salesCartItem = new SalesCartItem();
        model.addAttribute("salesCartItem", salesCartItem);
        return "module/branch/transactions/pos";
    }

//    @RequestMapping(value = {"", "/", "/index"})
//    public String index(Model model, SalesInvoice salesInvoice) {
//        model.addAttribute("customerlist", customersRepository.findAll());
//
//        model.addAttribute("paymenttramlist", paymentTermsRepository.findAll());
//
//        model.addAttribute("pricetypelist", SalesTypeName.values());
//
//        model.addAttribute("inventoryLocationlist", inventoryLocationsService.activelocationlist());
//
//        model.addAttribute("shippingCompanylist", shippingCompanyRepository.findAll());
//
//        model.addAttribute("itemList", itemService.allActiveAndSalableItemList(false, false));
//
//        ///   set  transactions Type
//        TransactionsType transactionsType = new TransactionsType();
//
//        transactionsType = transactionsTypeRepository.findBySlug("sales-invoice");
//        salesInvoice.setTransactionsType(transactionsType);
//
//        SalesCartItem salesCartItem = new SalesCartItem();
//
//        model.addAttribute("salesCartItem", salesCartItem);
//        
//        
//        return "module/sales/transactions/salesinvoice";
//    }
    
    
    
    @RequestMapping("/save")
    public String save(Model model, @Valid Pos pos, BindingResult bindingResult,
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

            transactionsType = transactionsTypeRepository.findBySlug("pos");
            pos.setTransactionsType(transactionsType);

            return "module/branch/transactions/pos";
        }
        
        

        posRepository.save(pos);

        Pos lastinsertid = posRepository.findTopByOrderByInvoicenoDesc();
        Long qtid = lastinsertid.getInvoiceno();

        List<PosItem> posItemList = new ArrayList<PosItem>();

        for (SalesCartItem cartItemLists : cartItemList) {

            PosItem posItem = new PosItem();
            // 1
            posItem.setPos(lastinsertid);

//            TransactionsType transactionsTypeid = new TransactionsType();
//
//            transactionsTypeid.setId(254L);
            posItem.setTransactionsType(pos.getTransactionsType());

            Items items = new Items();

            items.setId(cartItemLists.getId());

            //2 
            posItem.setItemId(items);

            //3
            posItem.setDescription("");

            //4 
            posItem.setUnitPrice(cartItemLists.getPrice());
            // 5 
            posItem.setUnitTax(cartItemLists.getTaxPercent());
            //6 

            posItem.setQuantity(cartItemLists.getQuantity());
            //7

            posItem.setDiscount_percent(cartItemLists.getDiscountPercent());

            posItemList.add(posItem);

            //salesQuotationItemRepository.saveAll(salesQuotationItemList);
        }

        posItemRepository.saveAll(posItemList);

        salesItemSession.removeAttribute("salesItemCartSession");
        salesItemSession.removeAttribute("salesItemCartSessionTotalDiscount");
        salesItemSession.removeAttribute("salesItemCartSessionTotalVat");
        salesItemSession.removeAttribute("salesItemCartSessionItemTotal");
        redirectAttrs.addAttribute("id", qtid).addFlashAttribute("success_messages", "Successfully Save.");
        // return "redirect:/salesinvoice/view/{id}";
        return "redirect:/branch/index/{id}";
    }
    
    

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("attribute", "value");
        return "module/branch/transactions/pos_list";
    }
    
    

}
