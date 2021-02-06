/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.sales.cart.controller;

import com.itgarden.ERP.module.inventory.repository.settings.ItemsRepository;
import com.itgarden.ERP.module.sales.cart.model.SalesCartItem;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/possalescart")
@SessionAttributes("salesCartItemSession")
public class PosSalesCartController {

    @Autowired
    ItemsRepository itemsRepository;

//    @RequestMapping(value = "/index")
//    public String index(HttpSession salesItemSession) {
//        SalesCartItem salesCartItem = new SalesCartItem();
//
//        salesCartItem.setId(2);
//        salesCartItem.setItemCode(22);
//        salesCartItem.setItemDescription("dfsdf");
//        salesCartItem.setQuantity(5);
//        salesCartItem.setPrice(50);
//        salesCartItem.setDiscount(4);
//        salesCartItem.setTax(5);
//        salesCartItem.setItemTotal(500);
//
//        salesItemSession.setAttribute("salesItemCartSession", salesCartItem);
//
//        return "redirect:/salesquotationentry/index";
//    }
    @PostMapping(value = "/hello")
    public ResponseEntity<SalesCartAjaxResponse> hello(@RequestBody(required = false) SalesCartItem salesCartItem, HttpSession salesItemSession) {

        if (salesItemSession.getAttribute("salesItemCartSession") == null) {

            List<SalesCartItem> cartItemList = new ArrayList<SalesCartItem>();
            cartItemList.add(salesCartItem);

            salesItemSession.setAttribute("salesItemCartSession", cartItemList);

            salesItemSession.setAttribute("salesItemCartSessionTotalDiscount", totaldiscount(cartItemList));

            salesItemSession.setAttribute("salesItemCartSessionTotalVat", totalVat(cartItemList));

            salesItemSession.setAttribute("salesItemCartSessionItemTotal", itemTotal(cartItemList));

        } else {

            List<SalesCartItem> cartItemList = (List<SalesCartItem>) salesItemSession.getAttribute("salesItemCartSession");

            Predicate<SalesCartItem> condition = sci -> sci.getId() == salesCartItem.getId();

//            cartItemList.removeIf(condition);

            cartItemList.add(salesCartItem);

            salesItemSession.setAttribute("salesItemCartSession", cartItemList);

            salesItemSession.setAttribute("salesItemCartSessionTotalDiscount", totaldiscount(cartItemList));

            salesItemSession.setAttribute("salesItemCartSessionTotalVat", totalVat(cartItemList));

            salesItemSession.setAttribute("salesItemCartSessionItemTotal", itemTotal(cartItemList));

        }

        SalesCartAjaxResponse salesCartAjaxResponse = new SalesCartAjaxResponse();

        salesCartAjaxResponse.setMsg("Saved successfully");

        return new ResponseEntity<>(salesCartAjaxResponse, HttpStatus.OK);
    }

    /// Cart Item Delete
    @RequestMapping("/itemdelete/{id}")

    ResponseEntity<String> itemDelete(@PathVariable Long id, HttpSession salesItemSession
    ) {

        List<SalesCartItem> salesCartItem = (List<SalesCartItem>) salesItemSession.getAttribute("salesItemCartSession");

        Predicate<SalesCartItem> condition = sci -> sci.getId() == id;

        salesCartItem.removeIf(condition);

        salesItemSession.setAttribute("salesItemCartSessionTotalDiscount", totaldiscount(salesCartItem));

        salesItemSession.setAttribute("salesItemCartSessionTotalVat", totalVat(salesCartItem));

        salesItemSession.setAttribute("salesItemCartSessionItemTotal", itemTotal(salesCartItem));

        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }

    //////////////////  start total discount;
    public BigDecimal totaldiscount(List<SalesCartItem> cartItemList) {
        
         BigDecimal totaldiscount = BigDecimal.ZERO;

        if (cartItemList != null) {

            Function<SalesCartItem, BigDecimal> totalMapper = salesCartItem -> salesCartItem.getDiscountTotal();

            return totaldiscount = cartItemList.stream().map(totalMapper).reduce(BigDecimal.ZERO, BigDecimal::add);

        } else {

            totaldiscount = BigDecimal.ZERO;

            return totaldiscount;
        }

    }

    //////////////////  end total discount;
    //////////////////  start total Vat;
    public BigDecimal totalVat(List<SalesCartItem> cartItemList) {

        BigDecimal totalVat = BigDecimal.ZERO;

        if (cartItemList != null) {

            Function<SalesCartItem, BigDecimal> totalMapper = salesCartItem -> salesCartItem.getTaxTotal();

            return totalVat = cartItemList.stream().map(totalMapper).reduce(BigDecimal.ZERO, BigDecimal::add);

        } else {

            totalVat = BigDecimal.ZERO;

            return totalVat;
        }

    }
    //////////////////  end total vat;

    //////////////////  start itemTotal;
    public BigDecimal itemTotal(List<SalesCartItem> cartItemList) {
        
        
         BigDecimal itemTotal = BigDecimal.ZERO;

        if (cartItemList != null) {

            Function<SalesCartItem, BigDecimal> totalMapper = salesCartItem -> salesCartItem.getItemTotal();

            return itemTotal = cartItemList.stream().map(totalMapper).reduce(BigDecimal.ZERO, BigDecimal::add);

        } else {

            itemTotal = BigDecimal.ZERO;

            return itemTotal;
        }
    }
    //////////////////  end itemTotal;

}
