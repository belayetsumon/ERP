/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.inventory.cart.controller;

import com.itgarden.ERP.module.inventory.cart.model.InventoryCartItem;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import javax.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author User
 */
@RestController
@RequestMapping("/inventorycartitem")
@SessionAttributes("inventoryCartItemSession")
public class InventoryCartItemController {

    @PostMapping(value = "/save")
    public ResponseEntity<InventoryCartAjaxResponse> hello(@RequestBody(required = false) InventoryCartItem inventoryCartItem, HttpSession inventoryItemSession) {

        if (inventoryItemSession.getAttribute("inventoryItemCartSession") == null) {

            List<InventoryCartItem> cartItemList = new ArrayList<InventoryCartItem>();

            cartItemList.add(inventoryCartItem);

            inventoryItemSession.setAttribute("inventoryItemCartSession", cartItemList);

        } else {

            List<InventoryCartItem> cartItemList = (List<InventoryCartItem>) inventoryItemSession.getAttribute("inventoryItemCartSession");

            Predicate<InventoryCartItem> condition = ici -> ici.getId() == inventoryCartItem.getId();

            cartItemList.removeIf(condition);

            cartItemList.add(inventoryCartItem);

            inventoryItemSession.setAttribute("inventoryItemCartSession", cartItemList);

        }

        InventoryCartAjaxResponse inventoryCartAjaxResponse = new InventoryCartAjaxResponse();

        inventoryCartAjaxResponse.setMsg("Saved successfully");

        return new ResponseEntity<>(inventoryCartAjaxResponse, HttpStatus.OK);

    }

    /// Cart Item Delete
    @RequestMapping("/itemdelete/{id}")
    ResponseEntity<String> itemDelete(@PathVariable Long id, InventoryCartItem inventoryCartItem, HttpSession inventoryItemSession
    ) {

        List<InventoryCartItem> salesCartItem = (List<InventoryCartItem>) inventoryItemSession.getAttribute("inventoryItemCartSession");

        Predicate<InventoryCartItem> condition = ici -> ici.getId() == id;

        salesCartItem.removeIf(condition);

        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }

}
