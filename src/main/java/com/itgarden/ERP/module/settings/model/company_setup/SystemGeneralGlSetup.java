/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.settings.model.company_setup;

import com.itgarden.ERP.module.finance_banking.model.settings.GlAccounts;
import com.itgarden.ERP.module.settings.model.enumvalue.AccountsType;
import com.itgarden.ERP.module.settings.model.enumvalue.DepreciationPeriod;
import com.itgarden.ERP.module.settings.model.enumvalue.InvoiceIdentification;
import com.itgarden.ERP.module.settings.model.enumvalue.TaxAlgorithm;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author User
 */
@Entity
public class SystemGeneralGlSetup {

    @Id
     private Long id;

    ////  General GL /////
    private int pastDueDaysInterval;

    @NotNull(message = "Accounts Type cannot be blank.")
    @Enumerated(EnumType.STRING)
    private AccountsType accountsType;

    @NotNull(message = "Retained Earnings cannot be blank.")
     @ManyToOne(optional = false)
    private GlAccounts retainedEarnings;

    @NotNull(message = " Profit Loss Year cannot be blank.")
     @ManyToOne(optional = false)
    private GlAccounts profitLossYear;

    @NotNull(message = "Exchange Variances Account cannot be blank.")
     @ManyToOne(optional = false)
    private GlAccounts exchangeVariancesAccount;

    @NotNull(message = " Bank Charges Account cannot be blank.")
     @ManyToOne(optional = false)
    private GlAccounts bankChargesAccount;

    @NotNull(message = " Tax Algorithm cannot be blank.")
    @Enumerated(EnumType.STRING)
    private TaxAlgorithm taxAlgorithm;

    //// Customers and Sales ///
    @NotNull(message = "Default Credit Limit cannot be blank.")
    private double defaultCreditLimit;

    @NotNull(message = "Invoice Identification cannot be blank.")
    @Enumerated(EnumType.STRING)
    private InvoiceIdentification invoiceIdentification;

    private byte accumulateBatchShipping;

    private byte printItemImageonQuote;

    private String legalTextonInvoice;

    @NotNull(message = "Shipping Charged Account cannot be blank.")
    @ManyToOne(optional = false)
    private GlAccounts shippingChargedAccount;

    @NotNull(message = "Deferred Income Account cannot be blank.")
    @ManyToOne(optional = false)
    private GlAccounts deferredIncomeAccount;

    //  Customers and Sales Defaults //
    @NotNull(message = "Receivable Account cannot be blank.")
    @ManyToOne(optional = false)
    private GlAccounts receivableAccount;

    @NotNull(message = "Sales Account cannot be blank.")
    @ManyToOne(optional = false)
    private GlAccounts salesAccount;

    @NotNull(message = "Sales Discount Account cannot be blank.")
    @ManyToOne(optional = false)
    private GlAccounts salesDiscountAccount;

    @NotNull(message = "Prompt Payment DiscountAccount cannot be blank.")
    @ManyToOne(optional = false)
    private GlAccounts promptPaymentDiscountAccount;

    private int quoteValidDays;
    private int deliveryRequiredBy;

    ///// Suppliers and Purchasing ////
    private double deliveryOverReceiveAllowance;
    private double invoiceOverChargeAllowance;

    /// Suppliers and Purchasing Defaults ///
    @NotNull(message = "Payable Account cannot be blank.")
    @ManyToOne(optional = false)
    private GlAccounts payableAccount;

    @NotNull(message = "Purchase Discount Accountcannot be blank.")
    @ManyToOne(optional = false)
    private GlAccounts purchaseDiscountAccount;

    @NotNull(message = "GRN Clearing Account cannot be blank.")
    @ManyToOne(optional = false)
    private GlAccounts grnClearingAccount;

    private int receivalRequiredBy;
    private byte showPoitemcodes;

    // Inventory///
    private byte allowNegativeInventory;

    private byte nozeroAmountsService;

    private byte locationNotifications;

    private byte allowNegativePrices;

    /////Items Defaults/////
    @NotNull(message = "Items Defaults Sales Account cannot be blank.")
    @ManyToOne(optional = false)
    private GlAccounts itemsDefaultsSalesAccount;
    
    @NotNull(message = "Inventory Account cannot be blank.")
    @ManyToOne(optional = false)
    private GlAccounts inventoryAccount;
    
    @NotNull(message = "COGS Account cannot be blank.")
    @ManyToOne(optional = false)
    private GlAccounts cogsAccount;
    
    @NotNull(message = "Inventory Adjustments Account cannot be blank.")
    @ManyToOne(optional = false)
    private GlAccounts inventoryAdjustmentsAccount;
    
    @NotNull(message = "WIP Account cannot be blank.")
    @ManyToOne(optional = false)
    private GlAccounts wipAccount;

    /// Fixed Assets Defaults////
    @NotNull(message = "Loss On Asset Disposal Account cannot be blank.")
    @ManyToOne(optional = false)
    private GlAccounts lossOnAssetDisposalAccount;
    
    @NotNull(message = "Depreciation Period cannot be blank.")
    @Enumerated(EnumType.STRING)
    private DepreciationPeriod depreciationPeriod;

    //// Manufacturing Defaults///////
    private int manufacturingDefaults;

///// Audit /// 
//    @Version
//    private int version;
//
//    @CreatedBy
//    @Column(nullable = false, updatable = false)
//    private String createdBy;
//
//    @CreatedDate
//    @Column(nullable = false, updatable = false)
//    private LocalDateTime created;
//
//    @LastModifiedBy
//    @Column(insertable = false, updatable = true)
//    private String modifiedBy;
//
//    @LastModifiedDate
//    @Column(insertable = false, updatable = true)
//    private LocalDateTime modified;
//
//    /// End Audit //// 
    public SystemGeneralGlSetup() {
    }

    public SystemGeneralGlSetup(Long id, int pastDueDaysInterval, AccountsType accountsType, GlAccounts retainedEarnings, GlAccounts profitLossYear, GlAccounts exchangeVariancesAccount, GlAccounts bankChargesAccount, TaxAlgorithm taxAlgorithm, double defaultCreditLimit, InvoiceIdentification invoiceIdentification, byte accumulateBatchShipping, byte printItemImageonQuote, String legalTextonInvoice, GlAccounts shippingChargedAccount, GlAccounts deferredIncomeAccount, GlAccounts receivableAccount, GlAccounts salesAccount, GlAccounts salesDiscountAccount, GlAccounts promptPaymentDiscountAccount, int quoteValidDays, int deliveryRequiredBy, double deliveryOverReceiveAllowance, double invoiceOverChargeAllowance, GlAccounts payableAccount, GlAccounts purchaseDiscountAccount, GlAccounts grnClearingAccount, int receivalRequiredBy, byte showPoitemcodes, byte allowNegativeInventory, byte nozeroAmountsService, byte locationNotifications, byte allowNegativePrices, GlAccounts itemsDefaultsSalesAccount, GlAccounts inventoryAccount, GlAccounts cogsAccount, GlAccounts inventoryAdjustmentsAccount, GlAccounts wipAccount, GlAccounts lossOnAssetDisposalAccount, DepreciationPeriod depreciationPeriod, int manufacturingDefaults) {
        this.id = id;
        this.pastDueDaysInterval = pastDueDaysInterval;
        this.accountsType = accountsType;
        this.retainedEarnings = retainedEarnings;
        this.profitLossYear = profitLossYear;
        this.exchangeVariancesAccount = exchangeVariancesAccount;
        this.bankChargesAccount = bankChargesAccount;
        this.taxAlgorithm = taxAlgorithm;
        this.defaultCreditLimit = defaultCreditLimit;
        this.invoiceIdentification = invoiceIdentification;
        this.accumulateBatchShipping = accumulateBatchShipping;
        this.printItemImageonQuote = printItemImageonQuote;
        this.legalTextonInvoice = legalTextonInvoice;
        this.shippingChargedAccount = shippingChargedAccount;
        this.deferredIncomeAccount = deferredIncomeAccount;
        this.receivableAccount = receivableAccount;
        this.salesAccount = salesAccount;
        this.salesDiscountAccount = salesDiscountAccount;
        this.promptPaymentDiscountAccount = promptPaymentDiscountAccount;
        this.quoteValidDays = quoteValidDays;
        this.deliveryRequiredBy = deliveryRequiredBy;
        this.deliveryOverReceiveAllowance = deliveryOverReceiveAllowance;
        this.invoiceOverChargeAllowance = invoiceOverChargeAllowance;
        this.payableAccount = payableAccount;
        this.purchaseDiscountAccount = purchaseDiscountAccount;
        this.grnClearingAccount = grnClearingAccount;
        this.receivalRequiredBy = receivalRequiredBy;
        this.showPoitemcodes = showPoitemcodes;
        this.allowNegativeInventory = allowNegativeInventory;
        this.nozeroAmountsService = nozeroAmountsService;
        this.locationNotifications = locationNotifications;
        this.allowNegativePrices = allowNegativePrices;
        this.itemsDefaultsSalesAccount = itemsDefaultsSalesAccount;
        this.inventoryAccount = inventoryAccount;
        this.cogsAccount = cogsAccount;
        this.inventoryAdjustmentsAccount = inventoryAdjustmentsAccount;
        this.wipAccount = wipAccount;
        this.lossOnAssetDisposalAccount = lossOnAssetDisposalAccount;
        this.depreciationPeriod = depreciationPeriod;
        this.manufacturingDefaults = manufacturingDefaults;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPastDueDaysInterval() {
        return pastDueDaysInterval;
    }

    public void setPastDueDaysInterval(int pastDueDaysInterval) {
        this.pastDueDaysInterval = pastDueDaysInterval;
    }

    public AccountsType getAccountsType() {
        return accountsType;
    }

    public void setAccountsType(AccountsType accountsType) {
        this.accountsType = accountsType;
    }

    public GlAccounts getRetainedEarnings() {
        return retainedEarnings;
    }

    public void setRetainedEarnings(GlAccounts retainedEarnings) {
        this.retainedEarnings = retainedEarnings;
    }

    public GlAccounts getProfitLossYear() {
        return profitLossYear;
    }

    public void setProfitLossYear(GlAccounts profitLossYear) {
        this.profitLossYear = profitLossYear;
    }

    public GlAccounts getExchangeVariancesAccount() {
        return exchangeVariancesAccount;
    }

    public void setExchangeVariancesAccount(GlAccounts exchangeVariancesAccount) {
        this.exchangeVariancesAccount = exchangeVariancesAccount;
    }

    public GlAccounts getBankChargesAccount() {
        return bankChargesAccount;
    }

    public void setBankChargesAccount(GlAccounts bankChargesAccount) {
        this.bankChargesAccount = bankChargesAccount;
    }

    public TaxAlgorithm getTaxAlgorithm() {
        return taxAlgorithm;
    }

    public void setTaxAlgorithm(TaxAlgorithm taxAlgorithm) {
        this.taxAlgorithm = taxAlgorithm;
    }

    public double getDefaultCreditLimit() {
        return defaultCreditLimit;
    }

    public void setDefaultCreditLimit(double defaultCreditLimit) {
        this.defaultCreditLimit = defaultCreditLimit;
    }

    public InvoiceIdentification getInvoiceIdentification() {
        return invoiceIdentification;
    }

    public void setInvoiceIdentification(InvoiceIdentification invoiceIdentification) {
        this.invoiceIdentification = invoiceIdentification;
    }

    public byte getAccumulateBatchShipping() {
        return accumulateBatchShipping;
    }

    public void setAccumulateBatchShipping(byte accumulateBatchShipping) {
        this.accumulateBatchShipping = accumulateBatchShipping;
    }

    public byte getPrintItemImageonQuote() {
        return printItemImageonQuote;
    }

    public void setPrintItemImageonQuote(byte printItemImageonQuote) {
        this.printItemImageonQuote = printItemImageonQuote;
    }

    public String getLegalTextonInvoice() {
        return legalTextonInvoice;
    }

    public void setLegalTextonInvoice(String legalTextonInvoice) {
        this.legalTextonInvoice = legalTextonInvoice;
    }

    public GlAccounts getShippingChargedAccount() {
        return shippingChargedAccount;
    }

    public void setShippingChargedAccount(GlAccounts shippingChargedAccount) {
        this.shippingChargedAccount = shippingChargedAccount;
    }

    public GlAccounts getDeferredIncomeAccount() {
        return deferredIncomeAccount;
    }

    public void setDeferredIncomeAccount(GlAccounts deferredIncomeAccount) {
        this.deferredIncomeAccount = deferredIncomeAccount;
    }

    public GlAccounts getReceivableAccount() {
        return receivableAccount;
    }

    public void setReceivableAccount(GlAccounts receivableAccount) {
        this.receivableAccount = receivableAccount;
    }

    public GlAccounts getSalesAccount() {
        return salesAccount;
    }

    public void setSalesAccount(GlAccounts salesAccount) {
        this.salesAccount = salesAccount;
    }

    public GlAccounts getSalesDiscountAccount() {
        return salesDiscountAccount;
    }

    public void setSalesDiscountAccount(GlAccounts salesDiscountAccount) {
        this.salesDiscountAccount = salesDiscountAccount;
    }

    public GlAccounts getPromptPaymentDiscountAccount() {
        return promptPaymentDiscountAccount;
    }

    public void setPromptPaymentDiscountAccount(GlAccounts promptPaymentDiscountAccount) {
        this.promptPaymentDiscountAccount = promptPaymentDiscountAccount;
    }

    public int getQuoteValidDays() {
        return quoteValidDays;
    }

    public void setQuoteValidDays(int quoteValidDays) {
        this.quoteValidDays = quoteValidDays;
    }

    public int getDeliveryRequiredBy() {
        return deliveryRequiredBy;
    }

    public void setDeliveryRequiredBy(int deliveryRequiredBy) {
        this.deliveryRequiredBy = deliveryRequiredBy;
    }

    public double getDeliveryOverReceiveAllowance() {
        return deliveryOverReceiveAllowance;
    }

    public void setDeliveryOverReceiveAllowance(double deliveryOverReceiveAllowance) {
        this.deliveryOverReceiveAllowance = deliveryOverReceiveAllowance;
    }

    public double getInvoiceOverChargeAllowance() {
        return invoiceOverChargeAllowance;
    }

    public void setInvoiceOverChargeAllowance(double invoiceOverChargeAllowance) {
        this.invoiceOverChargeAllowance = invoiceOverChargeAllowance;
    }

    public GlAccounts getPayableAccount() {
        return payableAccount;
    }

    public void setPayableAccount(GlAccounts payableAccount) {
        this.payableAccount = payableAccount;
    }

    public GlAccounts getPurchaseDiscountAccount() {
        return purchaseDiscountAccount;
    }

    public void setPurchaseDiscountAccount(GlAccounts purchaseDiscountAccount) {
        this.purchaseDiscountAccount = purchaseDiscountAccount;
    }

    public GlAccounts getGrnClearingAccount() {
        return grnClearingAccount;
    }

    public void setGrnClearingAccount(GlAccounts grnClearingAccount) {
        this.grnClearingAccount = grnClearingAccount;
    }

    public int getReceivalRequiredBy() {
        return receivalRequiredBy;
    }

    public void setReceivalRequiredBy(int receivalRequiredBy) {
        this.receivalRequiredBy = receivalRequiredBy;
    }

    public byte getShowPoitemcodes() {
        return showPoitemcodes;
    }

    public void setShowPoitemcodes(byte showPoitemcodes) {
        this.showPoitemcodes = showPoitemcodes;
    }

    public byte getAllowNegativeInventory() {
        return allowNegativeInventory;
    }

    public void setAllowNegativeInventory(byte allowNegativeInventory) {
        this.allowNegativeInventory = allowNegativeInventory;
    }

    public byte getNozeroAmountsService() {
        return nozeroAmountsService;
    }

    public void setNozeroAmountsService(byte nozeroAmountsService) {
        this.nozeroAmountsService = nozeroAmountsService;
    }

    public byte getLocationNotifications() {
        return locationNotifications;
    }

    public void setLocationNotifications(byte locationNotifications) {
        this.locationNotifications = locationNotifications;
    }

    public byte getAllowNegativePrices() {
        return allowNegativePrices;
    }

    public void setAllowNegativePrices(byte allowNegativePrices) {
        this.allowNegativePrices = allowNegativePrices;
    }

    public GlAccounts getItemsDefaultsSalesAccount() {
        return itemsDefaultsSalesAccount;
    }

    public void setItemsDefaultsSalesAccount(GlAccounts itemsDefaultsSalesAccount) {
        this.itemsDefaultsSalesAccount = itemsDefaultsSalesAccount;
    }

    public GlAccounts getInventoryAccount() {
        return inventoryAccount;
    }

    public void setInventoryAccount(GlAccounts inventoryAccount) {
        this.inventoryAccount = inventoryAccount;
    }

    public GlAccounts getCogsAccount() {
        return cogsAccount;
    }

    public void setCogsAccount(GlAccounts cogsAccount) {
        this.cogsAccount = cogsAccount;
    }

    public GlAccounts getInventoryAdjustmentsAccount() {
        return inventoryAdjustmentsAccount;
    }

    public void setInventoryAdjustmentsAccount(GlAccounts inventoryAdjustmentsAccount) {
        this.inventoryAdjustmentsAccount = inventoryAdjustmentsAccount;
    }

    public GlAccounts getWipAccount() {
        return wipAccount;
    }

    public void setWipAccount(GlAccounts wipAccount) {
        this.wipAccount = wipAccount;
    }

    public GlAccounts getLossOnAssetDisposalAccount() {
        return lossOnAssetDisposalAccount;
    }

    public void setLossOnAssetDisposalAccount(GlAccounts lossOnAssetDisposalAccount) {
        this.lossOnAssetDisposalAccount = lossOnAssetDisposalAccount;
    }

    public DepreciationPeriod getDepreciationPeriod() {
        return depreciationPeriod;
    }

    public void setDepreciationPeriod(DepreciationPeriod depreciationPeriod) {
        this.depreciationPeriod = depreciationPeriod;
    }

    public int getManufacturingDefaults() {
        return manufacturingDefaults;
    }

    public void setManufacturingDefaults(int manufacturingDefaults) {
        this.manufacturingDefaults = manufacturingDefaults;
    }

    
    
    

}