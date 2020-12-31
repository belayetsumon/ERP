/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.itgarden.ERP.module.sales.cart.controller;

/**
 *
 * @author User
 */
public class SalesCartAjaxResponse {
public String msg;

    public SalesCartAjaxResponse(String msg) {
        this.msg = msg;
    }

    public SalesCartAjaxResponse() {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
