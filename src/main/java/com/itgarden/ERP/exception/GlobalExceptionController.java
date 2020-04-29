/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.exception;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author User
 */
@ControllerAdvice

public class GlobalExceptionController {

    private static final long serialVersionUID = 1L;

    @ExceptionHandler(Exception.class)
    @RequestMapping("/url")
    public String page(Model model,HttpServletRequest request, Exception ex) {
      model.addAttribute("error","An error occurred, please try again later");
       model.addAttribute("cause", ex.getCause());
       model.addAttribute("message", ex.getMessage());
       model.addAttribute("stacktrace", ex.getStackTrace());
        return "exception/error";
    }

}
