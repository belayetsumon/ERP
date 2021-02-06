/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.inventory.controller.transactions;

import com.itgarden.ERP.module.inventory.cart.model.InventoryCartItem;
import com.itgarden.ERP.module.inventory.model.settings.Items;
import com.itgarden.ERP.module.inventory.model.transactions.InventoryLocationTransfers;
import com.itgarden.ERP.module.inventory.model.transactions.InventoryLocationTransfersItem;
import com.itgarden.ERP.module.inventory.model.transactions.StockMoves;
import com.itgarden.ERP.module.inventory.repository.settings.ItemsRepository;
import com.itgarden.ERP.module.inventory.repository.transactions.InventoryLocationTransfersItemRepository;
import com.itgarden.ERP.module.inventory.repository.transactions.InventoryLocationTransfersRepository;
import com.itgarden.ERP.module.inventory.repository.transactions.StockMovesRepository;
import com.itgarden.ERP.module.inventory.settings.services.InventoryLocationsService;
import com.itgarden.ERP.module.inventory.settings.services.ItemService;
import com.itgarden.ERP.module.inventory.transactions.service.InventoryLocationTransfersService;
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
@RequestMapping("/inventorylocationtransfers")
public class InventoryLocationTransfersController {
    
    
     @Autowired
    LoggedUserService loggedUserService;

    @Autowired
    ItemService itemService;

    @Autowired
    TransactionsTypeRepository transactionsTypeRepository;

    @Autowired
    InventoryLocationsService inventoryLocationsService;

    @Autowired
    InventoryLocationTransfersRepository inventoryLocationTransfersRepository;

    @Autowired
    InventoryLocationTransfersItemRepository inventoryLocationTransfersItemRepository;

    @Autowired
    ItemsRepository itemsRepository;

    @Autowired
    StockMovesRepository stockMovesRepository;
    
    @Autowired
    InventoryLocationTransfersService inventoryLocationTransfersService;
    

    @RequestMapping(value = {"", "/", "/index"})
    public String index(Model model, InventoryLocationTransfers inventoryLocationTransfers) {
        
        /// ser refe
        
        inventoryLocationTransfers.setIltReference(inventoryLocationTransfersService.refarance());
        
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
    public String save(Model model, @Valid InventoryLocationTransfers inventoryLocationTransfers, BindingResult bindingResult,
            RedirectAttributes redirectAttrs, HttpSession inventoryItemSession) {

        
          boolean referCheck = inventoryLocationTransfersService.referCodeCheck(inventoryLocationTransfers.getIltReference());
        
          if (referCheck ==true) {

            ObjectError referCodeError;

            referCodeError = new ObjectError("inAdReference", "This code "+inventoryLocationTransfers.getIltReference()+" is exists. Please  refresh your browser and try again.");

            bindingResult.addError(referCodeError);
        }
  
        
        List<InventoryCartItem> cartItemList = (List<InventoryCartItem>) inventoryItemSession.getAttribute("inventoryItemCartSession");

        if (cartItemList == null || cartItemList.size() == 0) {

            ObjectError cartItemListError;

            cartItemListError = new ObjectError("inventoryLocationTransfersItem", "You must enter at least one non empty item line.");

            bindingResult.addError(cartItemListError);
        }

        if (bindingResult.hasErrors()) {
            ///   set  transactions Type
            TransactionsType transactionsType = new TransactionsType();

            transactionsType = transactionsTypeRepository.findBySlug("location-transfer");

            inventoryLocationTransfers.setTransType(transactionsType);

            model.addAttribute("inventoryLocationlist", inventoryLocationsService.activelocationlist());

            model.addAttribute("itemList", itemService.allActiveAndSalableItemList(false, false));
            return "module/inventory/transactions/inventorylocationtransfers";
        }

        inventoryLocationTransfersRepository.save(inventoryLocationTransfers);

        InventoryLocationTransfers lastid = inventoryLocationTransfersRepository.findTopByOrderByIdDesc();

        List<InventoryLocationTransfersItem> inventoryLocationTransfersItemList = new ArrayList<InventoryLocationTransfersItem>();

        List<StockMoves> stockMoveslist = new ArrayList<StockMoves>();

        for (InventoryCartItem inventoryCartItemlist : cartItemList) {

            InventoryLocationTransfersItem inventoryLocationTransfersItem = new InventoryLocationTransfersItem();

            inventoryLocationTransfersItem.setInventoryLocationTransfers(lastid);

            Items itemCode = itemsRepository.getOne(inventoryCartItemlist.getId());

            inventoryLocationTransfersItem.setItemCode(itemCode);

            inventoryLocationTransfersItem.setQuantity(inventoryCartItemlist.getQuantity());

            inventoryLocationTransfersItem.setUnit(inventoryCartItemlist.getUnit());

            inventoryLocationTransfersItem.setPrice(inventoryCartItemlist.getPrice());

            inventoryLocationTransfersItem.setItemTotal(inventoryCartItemlist.getItemTotal());

            inventoryLocationTransfersItemList.add(inventoryLocationTransfersItem);
            
            
            
            //  stock moves From
            StockMoves stockMovesFrom = new StockMoves();

            stockMovesFrom.setTranDate(inventoryLocationTransfers.getIltDate());

            stockMovesFrom.setItems(itemCode);
            
             TransactionsType transactionsType = new TransactionsType();

            transactionsType = transactionsTypeRepository.findBySlug("location-transfer");

            stockMovesFrom.setTransType(transactionsType);

            stockMovesFrom.setTransNo(lastid.getId());

            stockMovesFrom.setReference(inventoryLocationTransfers.getIltReference());

            stockMovesFrom.setInventoryLocations(inventoryLocationTransfers.getFromLocation());

            stockMovesFrom.setQty(inventoryCartItemlist.getQuantity().negate());

            stockMovesFrom.setPrice(inventoryCartItemlist.getPrice().negate());

            if (itemCode.getStandardCosts() == null) {

                stockMovesFrom.setStandardCost(BigDecimal.valueOf(0));

            } else {

                stockMovesFrom.setStandardCost(itemCode.getStandardCosts().getPrice().negate());
            }

            stockMovesFrom.setTotalPrice(inventoryCartItemlist.getItemTotal().negate());

            stockMovesFrom.setMemo(inventoryLocationTransfers.getMemo());

            stockMoveslist.add(stockMovesFrom);
    
            //  stock moves to
            StockMoves stockMovesTo = new StockMoves();

            stockMovesTo.setTranDate(inventoryLocationTransfers.getIltDate());

            stockMovesTo.setItems(itemCode);

            stockMovesTo.setTransType(inventoryLocationTransfers.getTransType());

            stockMovesTo.setTransNo(lastid.getId());

            stockMovesTo.setReference(inventoryLocationTransfers.getIltReference());

            stockMovesTo.setInventoryLocations(inventoryLocationTransfers.getToLocation());

            stockMovesTo.setQty(inventoryCartItemlist.getQuantity());

            stockMovesTo.setPrice(inventoryCartItemlist.getPrice());

            if (itemCode.getStandardCosts() == null) {

                stockMovesTo.setStandardCost(BigDecimal.valueOf(0));

            } else {

                stockMovesTo.setStandardCost(itemCode.getStandardCosts().getPrice());
            }

            stockMovesTo.setTotalPrice(inventoryCartItemlist.getItemTotal());

            stockMovesTo.setMemo(inventoryLocationTransfers.getMemo());

            stockMoveslist.add(stockMovesTo);

        }

        inventoryLocationTransfersItemRepository.saveAll(inventoryLocationTransfersItemList);

        stockMovesRepository.saveAll(stockMoveslist);

        inventoryItemSession.removeAttribute("inventoryItemCartSession");

        redirectAttrs.addAttribute("id", lastid.getId()).addFlashAttribute("success_messages", "Successfully Save.");

        return "redirect:/inventorylocationtransfers/view/{id}";
    }

    @GetMapping(value = "/view/{id}")
    public String view(Model model, @PathVariable Long id, InventoryLocationTransfers inventoryLocationTransfers) {
        model.addAttribute("itId", id);

        return "module/inventory/transactions/inventorylocationtransfers_view";
    }

    @GetMapping(value = "/list")
    public String list(Model model, InventoryLocationTransfers inventoryLocationTransfers) {

        model.addAttribute("list", inventoryLocationTransfersRepository.findAllByOrderByIdDesc());

        return "module/inventory/transactions/inventorylocationtransfers_list";
    }

    @RequestMapping("/details/{id}")
    public String salesorderdetails(Model model, @PathVariable Long id, InventoryLocationTransfers inventoryLocationTransfers) {

        model.addAttribute("inventoryAdjustment", inventoryLocationTransfersRepository.getOne(id));

        return "module/inventory/transactions/inventorylocationtransfers_details";
    }

    @RequestMapping("/print/{id}")
    public String print(Model model, @PathVariable Long id, InventoryLocationTransfers inventoryLocationTransfers) {

        model.addAttribute("user_name", loggedUserService.activeUserName());
        
        model.addAttribute("inventoryAdjustment", inventoryLocationTransfersRepository.getOne(id));

        return "module/inventory/transactions/inventorylocationtransfers_print";
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
