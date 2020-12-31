/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.sales.settings.services;

import com.itgarden.ERP.module.sales.dto.CustomerDTO;
import com.itgarden.ERP.module.sales.dto.CustomerSearchDto;
import com.itgarden.ERP.module.sales.model.settings.Customers;
import com.itgarden.ERP.module.sales.repository.settings.CustomersRepository;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class CustomerService {

    public CustomersRepository getCustomersRepository() {
        return customersRepository;
    }

    public void setCustomersRepository(CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }
    
    @Autowired
    CustomersRepository customersRepository;

    /// query by contact persins or customer name
    public List<CustomerSearchDto> customerSearch(String customerName, String contactpersonsname) {
        List<Customers> customerList = customersRepository.findByCustomerNameContainingIgnoreCaseOrContactPersonsNameContainingIgnoreCase(customerName, contactpersonsname);
        
        List<CustomerSearchDto> customerSearch = new ArrayList<>();
        
        for (Customers customerSearchlist : customerList) {
            
            CustomerSearchDto csDto = new CustomerSearchDto();
            
            csDto.setCustomerId(customerSearchlist.getId());
            
            csDto.setCustomerName(customerSearchlist.getCustomerName());
            
            customerSearch.add(csDto);
        }
        
        return customerSearch;
    }
    
    
    
    
    public CustomerDTO customerById(Long id) {
        
        
        Customers customerInfo = customersRepository.getOne(id);
        
        CustomerDTO customerDto = new CustomerDTO();
        
        if(customerInfo!=null){
        customerDto.setId(customerInfo.getId());
        customerDto.setCustomerName(customerInfo.getCustomerName());
        customerDto.setCustomerShortName(customerInfo.getCustomerShortName());
        customerDto.setAddress(customerInfo.getAddress());
        customerDto.setGstNo(customerInfo.getGstNo());
        customerDto.setSalesTypeOrPriceList(customerInfo.getSalesTypeOrPriceList());
        customerDto.setContactPersonsName(customerInfo.getContactPersonsName());
        customerDto.setPhone(customerInfo.getPhone());
        customerDto.setMobile(customerInfo.getMobile());
        customerDto.setEmail(customerInfo.getEmail());
        customerDto.setSalesPerson(customerInfo.getSalesPerson());
        customerDto.setBankAccountNumber(customerInfo.getBankAccountNumber());
        customerDto.setMailingAddress(customerInfo.getMailingAddress());
        customerDto.setBillingAddress(customerInfo.getBillingAddress());
        customerDto.setDeliveryAddress(customerInfo.getDeliveryAddress());
        customerDto.setDiscountPercent(customerInfo.getDiscountPercent());
        customerDto.setPromptPaymentDiscountPercent(customerInfo.getPromptPaymentDiscountPercent());
        customerDto.setCreditLimit(customerInfo.getCreditLimit());
        customerDto.setPaymentTerms(customerInfo.getPaymentTerms());
        customerDto.setGeneralNotes(customerInfo.getGeneralNotes());
        customerDto.setDefaultInventoryLocation(customerInfo.getDefaultInventoryLocation().getId());
        customerDto.setDefaultInventoryLocationName(customerInfo.getDefaultInventoryLocation().getLocationName());
        customerDto.setSalesArea(customerInfo.getSalesArea().getId());
        customerDto.setSalesAreaName(customerInfo.getSalesArea().getAreaName());
        customerDto.setTaxGroup(customerInfo.getTaxGroup().getId());
        customerDto.setTaxGroupName(customerInfo.getTaxGroup().getName());
        customerDto.setCreatedBy(customerInfo.getCreatedBy());
//            customerDto.setCreated(customerInfo.getCreated().toString());
//            customerDto.setModified(customerInfo.getModified().toString());
        customerDto.setModifiedBy(customerInfo.getModifiedBy());
         return customerDto;
        }
        customerDto.setCustomerName("empty");
         return customerDto;
       
        
        
    }
    
}
