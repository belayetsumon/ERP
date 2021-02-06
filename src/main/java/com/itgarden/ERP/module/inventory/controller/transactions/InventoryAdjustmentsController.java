/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.inventory.controller.transactions;

import com.itgarden.ERP.module.inventory.cart.model.InventoryCartItem;
import com.itgarden.ERP.module.inventory.model.settings.Items;
import com.itgarden.ERP.module.inventory.model.transactions.InventoryAdjustmentItem;
import com.itgarden.ERP.module.inventory.model.transactions.InventoryAdjustments;
import com.itgarden.ERP.module.inventory.model.transactions.StockMoves;
import com.itgarden.ERP.module.inventory.repository.settings.ItemsRepository;
import com.itgarden.ERP.module.inventory.repository.transactions.InventoryAdjustmentItemRepository;
import com.itgarden.ERP.module.inventory.repository.transactions.InventoryAdjustmentsRepository;
import com.itgarden.ERP.module.inventory.repository.transactions.StockMovesRepository;
import com.itgarden.ERP.module.inventory.settings.services.InventoryLocationsService;
import com.itgarden.ERP.module.inventory.settings.services.ItemService;
import com.itgarden.ERP.module.inventory.transactions.service.InventoryAdjustmentsService;
import com.itgarden.ERP.module.sales.cart.model.SalesCartItem;
import com.itgarden.ERP.module.settings.model.company_setup.TransactionsType;
import com.itgarden.ERP.module.settings.repository.company_setup.TransactionsTypeRepository;
import com.itgarden.ERP.module.user.services.LoggedUserService;
import java.math.BigDecimal;
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
@RequestMapping("/inventoryadjustments")
public class InventoryAdjustmentsController {
    
    @Autowired
    LoggedUserService loggedUserService;

    @Autowired
    ItemService itemService;

    @Autowired
    TransactionsTypeRepository transactionsTypeRepository;

    @Autowired
    InventoryLocationsService inventoryLocationsService;

    @Autowired
    InventoryAdjustmentsRepository inventoryAdjustmentsRepository;

    @Autowired
    InventoryAdjustmentItemRepository inventoryAdjustmentItemRepository;

    @Autowired
    InventoryAdjustmentsService inventoryAdjustmentsService;

    @Autowired
    ItemsRepository itemsRepository;

    @Autowired
    StockMovesRepository stockMovesRepository;
    
    
    

    @RequestMapping(value = {"", "/", "/index"})
    public String index(Model model, InventoryAdjustments inventoryAdjustments) {

        inventoryAdjustments.setInAdReference(inventoryAdjustmentsService.refarance());

        ///   set  transactions Type
        TransactionsType transactionsType = new TransactionsType();

        transactionsType = transactionsTypeRepository.findBySlug("inventory-adjustment");

        inventoryAdjustments.setTransType(transactionsType);

        SalesCartItem salesCartItem = new SalesCartItem();

        model.addAttribute("inventoryLocationlist", inventoryLocationsService.activelocationlist());

        model.addAttribute("itemList", itemService.allActiveAndSalableItemList(false, false));

        return "module/inventory/transactions/inventoryadjustments";
    }

    @RequestMapping("/save")
    public String save(Model model, @Valid InventoryAdjustments inventoryAdjustments, BindingResult bindingResult, RedirectAttributes redirectAttrs, HttpSession inventoryItemSession) {

      
         boolean referCheck = inventoryAdjustmentsService.referCodeCheck(inventoryAdjustments.getInAdReference());
        
          if (referCheck ==true) {

            ObjectError referCodeError;

            referCodeError = new ObjectError("inAdReference", "This code "+inventoryAdjustments.getInAdReference()+" is exists. Please  refresh your browser and try again.");

            bindingResult.addError(referCodeError);
        }
        
        
        
        
        
        
        List<InventoryCartItem> cartItemList = (List<InventoryCartItem>) inventoryItemSession.getAttribute("inventoryItemCartSession");

        if (cartItemList == null || cartItemList.size() == 0) {

            ObjectError cartItemListError;

            cartItemListError = new ObjectError("inventoryAdjustmentItem", "You must enter at least one non empty item line.");

            bindingResult.addError(cartItemListError);
        }

        if (bindingResult.hasErrors()) {

            inventoryAdjustments.setInAdReference(inventoryAdjustmentsService.refarance());
            ///   set  transactions Type
            TransactionsType transactionsType = new TransactionsType();

            transactionsType = transactionsTypeRepository.findBySlug("inventory-adjustment");

            inventoryAdjustments.setTransType(transactionsType);

            SalesCartItem salesCartItem = new SalesCartItem();

            model.addAttribute("inventoryLocationlist", inventoryLocationsService.activelocationlist());

            model.addAttribute("itemList", itemService.allActiveAndSalableItemList(false, false));
            return "module/inventory/transactions/inventoryadjustments";
        }

        inventoryAdjustmentsRepository.save(inventoryAdjustments);

        InventoryAdjustments lasid = inventoryAdjustmentsRepository.findTopByOrderByIdDesc();

        Long id = lasid.getId();

        List< InventoryAdjustmentItem> inventoryAdjustmentItemList = new ArrayList<InventoryAdjustmentItem>();
        List<StockMoves> stockMoveslist = new ArrayList<StockMoves>();

        for (InventoryCartItem inventoryCartItemlist : cartItemList) {

            InventoryAdjustmentItem inventoryAdjustmentItem = new InventoryAdjustmentItem();

            inventoryAdjustmentItem.setInventoryAdjustments(lasid);

            Items itemCode = itemsRepository.getOne(inventoryCartItemlist.getId());

            inventoryAdjustmentItem.setItemCode(itemCode);

            inventoryAdjustmentItem.setQuantity(inventoryCartItemlist.getQuantity());

            inventoryAdjustmentItem.setUnit(inventoryCartItemlist.getUnit());

            inventoryAdjustmentItem.setPrice(inventoryCartItemlist.getPrice());

            inventoryAdjustmentItem.setItemTotal(inventoryCartItemlist.getItemTotal());

            inventoryAdjustmentItemList.add(inventoryAdjustmentItem);

            //  stock moves table
            StockMoves stockMoves = new StockMoves();

            stockMoves.setTranDate(inventoryAdjustments.getAdDate());

            stockMoves.setItems(itemCode);
            
            TransactionsType transactionsType = new TransactionsType();

            transactionsType = transactionsTypeRepository.findBySlug("inventory-adjustment");

            stockMoves.setTransType(transactionsType);

            stockMoves.setTransNo(lasid.getId());

            stockMoves.setReference(inventoryAdjustments.getInAdReference());

            stockMoves.setInventoryLocations(inventoryAdjustments.getInventoryLocations());

            stockMoves.setQty(inventoryCartItemlist.getQuantity());

            stockMoves.setPrice(inventoryCartItemlist.getPrice());

            if (itemCode.getStandardCosts() == null) {

                stockMoves.setStandardCost(BigDecimal.valueOf(0));

            } else {

                stockMoves.setStandardCost(itemCode.getStandardCosts().getPrice());
            }
            stockMoves.setTotalPrice(inventoryCartItemlist.getItemTotal());
            stockMoves.setMemo(inventoryAdjustments.getMemo());
            stockMoveslist.add(stockMoves);
        }

        inventoryAdjustmentItemRepository.saveAll(inventoryAdjustmentItemList);

        stockMovesRepository.saveAll(stockMoveslist);

        inventoryItemSession.removeAttribute("inventoryItemCartSession");

        redirectAttrs.addAttribute("id", lasid.getId()).addFlashAttribute("success_messages", "Successfully Save.");

        return "redirect:/inventoryadjustments/view/{id}";
    }

    @GetMapping(value = "/view/{id}")
    public String view(Model model, @PathVariable Long id, InventoryAdjustments inventoryAdjustments) {
        model.addAttribute("adId", id);

        return "module/inventory/transactions/inventoryadjustments_view";
    }

    @GetMapping(value = "/list")
    public String list(Model model, InventoryAdjustments inventoryAdjustments) {

        model.addAttribute("list", inventoryAdjustmentsRepository.findAllByOrderByIdDesc());

        return "module/inventory/transactions/inventoryadjustments_list";
    }

    @RequestMapping("/details/{id}")
    public String salesorderdetails(Model model, @PathVariable Long id, InventoryAdjustments inventoryAdjustments) {

        model.addAttribute("inventoryAdjustment", inventoryAdjustmentsRepository.getOne(id));

        return "module/inventory/transactions/inventoryadjustments_details";
    }

    @RequestMapping("/print/{id}")
    public String print(Model model, @PathVariable Long id, InventoryAdjustments inventoryAdjustments) {

      model.addAttribute("user_name", loggedUserService.activeUserName());
        
        model.addAttribute("inventoryAdjustment", inventoryAdjustmentsRepository.getOne(id));

        return "module/inventory/transactions/inventoryadjustments_print";
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

        if (salesItemSession.getAttribute("inventoryItemCartSession") != null) {

            salesItemSession.removeAttribute("inventoryItemCartSession");

        }
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
