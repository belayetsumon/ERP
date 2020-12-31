/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.inventory.controller.transactions;

import com.itgarden.ERP.module.inventory.model.transactions.InventoryLocationTransfers;
import com.itgarden.ERP.module.inventory.repository.transactions.InventoryLocationTransfersRepository;
import com.itgarden.ERP.module.inventory.settings.services.InventoryLocationsService;
import com.itgarden.ERP.module.inventory.settings.services.ItemService;
import com.itgarden.ERP.module.sales.cart.model.SalesCartItem;
import com.itgarden.ERP.module.settings.model.company_setup.TransactionsType;
import com.itgarden.ERP.module.settings.repository.company_setup.TransactionsTypeRepository;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/inventorylocationtransfers")
public class InventoryLocationTransfersController {

    @Autowired
    ItemService itemService;

    @Autowired
    TransactionsTypeRepository transactionsTypeRepository;

    @Autowired
    InventoryLocationsService inventoryLocationsService;
    
    @Autowired
    InventoryLocationTransfersRepository inventoryLocationTransfersRepository;
    
    

    @RequestMapping(value = {"", "/", "/index"})
    public String index(Model model, InventoryLocationTransfers inventoryLocationTransfers) {
        ///   set  transactions Type
        TransactionsType transactionsType = new TransactionsType();

        transactionsType = transactionsTypeRepository.findBySlug("location-transfer");

        inventoryLocationTransfers.setTransType(transactionsType);

        SalesCartItem salesCartItem = new SalesCartItem();

        model.addAttribute("inventoryLocationlist", inventoryLocationsService.activelocationlist());

        model.addAttribute("itemList", itemService.allActiveAndSalableItemList(false, false));
        return "module/inventory/transactions/inventorylocationtransfers";
    }
    
    
    
    @RequestMapping("/save")
    public String save(Model model, @Valid InventoryLocationTransfers inventoryLocationTransfers, BindingResult bindingResult, RedirectAttributes redirectAttrs) {

        if (bindingResult.hasErrors()) {
            ///   set  transactions Type
            TransactionsType transactionsType = new TransactionsType();

            transactionsType = transactionsTypeRepository.findBySlug("inventory -adjustment");

            inventoryLocationTransfers.setTransType(transactionsType);

           

            model.addAttribute("inventoryLocationlist", inventoryLocationsService.activelocationlist());

            model.addAttribute("itemList", itemService.allActiveAndSalableItemList(false, false));
            return "module/inventory/transactions/inventoryadjustments";
        }

        inventoryLocationTransfersRepository.save(inventoryLocationTransfers);

        InventoryLocationTransfers lastid = inventoryLocationTransfersRepository.findTopByOrderByIdDesc();

        redirectAttrs.addAttribute("id", lastid.getId()).addFlashAttribute("success_messages", "Successfully Save.");

        return "redirect:/inventorylocationtransfers/view/{id}";
    }
    

    @GetMapping(value = "/view/{id}")
    public String view(Model model, @PathVariable Long id, InventoryLocationTransfers inventoryLocationTransfers) {
        model.addAttribute("ltId", id);

        return "module/inventory/transactions/inventorylocationtransfers_view";
    }
    
    

    @RequestMapping("/details/{id}")
    public String salesorderdetails(Model model, @PathVariable Long id, InventoryLocationTransfers inventoryLocationTransfers) {

        model.addAttribute("inventoryAdjustment", inventoryLocationTransfersRepository.getOne(id));

        return "module/inventory/transactions/inventorylocationtransfers_details";
    }
    
    

//    @RequestMapping("/edit/{id}")
//
//    public String edit(Model model, @PathVariable Long id, SalesOrder salesOrderEntry) {
//
//        model.addAttribute("salesOrderEntry", salesOrderRepository.getOne(id));
//
//        model.addAttribute("customerlist", customersRepository.findAll());
//
//        model.addAttribute("pamenttramlist", paymentTermsRepository.findAll());
//
//        model.addAttribute("pricetypelist", SalesTypeName.values());
//
//        ///   set  transactions Type
//        TransactionsType transactionsType = new TransactionsType();
//        transactionsType.setId(165L);
//        salesOrderEntry.setTransactionsType(transactionsType);
//
//        //  customer refarance 
//        CustomerBranches customerBranches = new CustomerBranches();
//        customerBranches.setId(162L);
//        salesOrderEntry.setBranchCode(customerBranches);
//
//        //  Shiping Company
//        ShippingCompany shippingCompany = new ShippingCompany();
//        shippingCompany.setId(163L);
//        salesOrderEntry.setShipVia(shippingCompany);
//        return "module/sales/transactions/create_order";
//
//    }
//
//    @GetMapping(value = "/delete/{id}")
//    public String delete(Model model, @PathVariable Long id, SalesOrder salesOrder, RedirectAttributes redirectAttrs) {
//        redirectAttrs.addFlashAttribute("success_messages", " Successfully Delete.");
//        salesOrderRepository.deleteById(id);
//        return "redirect:/salesorder/salesorderlist";
//
//    }
//
//
    @GetMapping(value = "/close")
    public String close(Model model, HttpSession salesItemSession) {

//        if (salesItemSession.getAttribute("salesItemCartSession") != null) {
//
//            salesItemSession.removeAttribute("salesItemCartSession");
//
//        }
//
//        if (salesItemSession.getAttribute("salesItemCartSessionTotalDiscount") != null) {
//
//            salesItemSession.removeAttribute("salesItemCartSessionTotalDiscount");
//
//        }
//
//        if (salesItemSession.getAttribute("salesItemCartSessionTotalVat") != null) {
//
//            salesItemSession.removeAttribute("salesItemCartSessionTotalVat");
//
//        }
//
//        if (salesItemSession.getAttribute("salesItemCartSessionItemTotal") != null) {
//
//            salesItemSession.removeAttribute("salesItemCartSessionItemTotal");
//
//        }

        return "redirect:/inventory/index";
    } 
    
    
    

}
