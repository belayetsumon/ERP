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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    ////  General GL /////
    int pastDueDaysInterval;
    @NotNull(message = "This field cannot be blank.")
    @Enumerated(EnumType.STRING)
    AccountsType accountsType;
    @NotNull(message = "This field cannot be blank.")
    @ManyToOne
    GlAccounts retainedEarnings;
    @NotNull(message = "This field cannot be blank.")
    @ManyToOne
    GlAccounts profitLossYear;
    @NotNull(message = "This field cannot be blank.")
    @ManyToOne
    GlAccounts exchangeVariancesAccount;
    @NotNull(message = "This field cannot be blank.")
    @ManyToOne
    GlAccounts bankChargesAccount;
   @NotNull(message = "This field cannot be blank.")
    @Enumerated(EnumType.STRING)
    TaxAlgorithm taxAlgorithm;

    //// Customers and Sales ///
    String defaultCreditLimit;
    @NotNull(message = "This field cannot be blank.")
    @Enumerated(EnumType.STRING)
    InvoiceIdentification nvoiceIdentification;
    byte accumulateBatchShipping;
    byte PrintItemImageonQuote;
    String legalTextonInvoice;
    @NotNull(message = "This field cannot be blank.")
    @ManyToOne
    GlAccounts shippingChargedAccount;
    @NotNull(message = "This field cannot be blank.")
    @ManyToOne
    GlAccounts deferredIncomeAccount;

    //  Customers and Sales Defaults //
    @NotNull(message = "This field cannot be blank.")
    @ManyToOne
    GlAccounts receivableAccount;
    @NotNull(message = "This field cannot be blank.")
    @ManyToOne
    GlAccounts salesAccount;
    @NotNull(message = "This field cannot be blank.")
    @ManyToOne
    GlAccounts salesDiscountAccount;
    @NotNull(message = "This field cannot be blank.")
    @ManyToOne
    GlAccounts promptPaymentDiscountAccount;
    int quoteValidDays;
    int deliveryRequiredBy;

    ///// Suppliers and Purchasing ////
    int deliveryOverReceiveAllowance;
    int invoiceOverChargeAllowance;

    /// Suppliers and Purchasing Defaults ///
    @NotNull(message = "This field cannot be blank.")
    @ManyToOne
    GlAccounts payableAccount;
    @NotNull(message = "This field cannot be blank.")
    @ManyToOne
    GlAccounts purchaseDiscountAccount;
    @NotNull(message = "This field cannot be blank.")
    @ManyToOne
    GlAccounts grnClearingAccount;
    int receivalRequiredBy;
    byte showPoitemcodes;

    // Inventory///
    byte allowNegativeInventory;
    byte nozeroAmountsService;
    byte locationNotifications;
    byte allowNegativePrices;

    /////Items Defaults/////
    @NotNull(message = "This field cannot be blank.")
    @ManyToOne
    GlAccounts ItemsDefaultsSalesAccount;
    @NotNull(message = "This field cannot be blank.")
    @ManyToOne
    GlAccounts inventoryAccount;
    @NotNull(message = "This field cannot be blank.")
    @ManyToOne
    GlAccounts cogsAccount;
    @NotNull(message = "This field cannot be blank.")
    @ManyToOne
    GlAccounts inventoryAdjustmentsAccount;
    @NotNull(message = "This field cannot be blank.")
    @ManyToOne
    GlAccounts wipAccount;

    /// Fixed Assets Defaults////
    @NotNull(message = "This field cannot be blank.")
    @ManyToOne
    GlAccounts lossOnAssetDisposalAccount;
    @NotNull(message = "This field cannot be blank.")
    @Enumerated(EnumType.STRING)
    DepreciationPeriod depreciationPeriod;

    //// Manufacturing Defaults///////
    int manufacturingDefaults;

    public SystemGeneralGlSetup() {
    }

    public SystemGeneralGlSetup(Long id, int pastDueDaysInterval, AccountsType accountsType, GlAccounts retainedEarnings, GlAccounts profitLossYear, GlAccounts exchangeVariancesAccount, GlAccounts bankChargesAccount, TaxAlgorithm taxAlgorithm, String defaultCreditLimit, InvoiceIdentification nvoiceIdentification, byte accumulateBatchShipping, byte PrintItemImageonQuote, String legalTextonInvoice, GlAccounts shippingChargedAccount, GlAccounts deferredIncomeAccount, GlAccounts receivableAccount, GlAccounts salesAccount, GlAccounts salesDiscountAccount, GlAccounts promptPaymentDiscountAccount, int quoteValidDays, int deliveryRequiredBy, int deliveryOverReceiveAllowance, int invoiceOverChargeAllowance, GlAccounts payableAccount, GlAccounts purchaseDiscountAccount, GlAccounts grnClearingAccount, int receivalRequiredBy, byte showPoitemcodes, byte allowNegativeInventory, byte nozeroAmountsService, byte locationNotifications, byte allowNegativePrices, GlAccounts ItemsDefaultsSalesAccount, GlAccounts inventoryAccount, GlAccounts cogsAccount, GlAccounts inventoryAdjustmentsAccount, GlAccounts wipAccount, GlAccounts lossOnAssetDisposalAccount, DepreciationPeriod depreciationPeriod, int manufacturingDefaults) {
        this.id = id;
        this.pastDueDaysInterval = pastDueDaysInterval;
        this.accountsType = accountsType;
        this.retainedEarnings = retainedEarnings;
        this.profitLossYear = profitLossYear;
        this.exchangeVariancesAccount = exchangeVariancesAccount;
        this.bankChargesAccount = bankChargesAccount;
        this.taxAlgorithm = taxAlgorithm;
        this.defaultCreditLimit = defaultCreditLimit;
        this.nvoiceIdentification = nvoiceIdentification;
        this.accumulateBatchShipping = accumulateBatchShipping;
        this.PrintItemImageonQuote = PrintItemImageonQuote;
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
        this.ItemsDefaultsSalesAccount = ItemsDefaultsSalesAccount;
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

    public String getDefaultCreditLimit() {
        return defaultCreditLimit;
    }

    public void setDefaultCreditLimit(String defaultCreditLimit) {
        this.defaultCreditLimit = defaultCreditLimit;
    }

    public InvoiceIdentification getNvoiceIdentification() {
        return nvoiceIdentification;
    }

    public void setNvoiceIdentification(InvoiceIdentification nvoiceIdentification) {
        this.nvoiceIdentification = nvoiceIdentification;
    }

    public byte getAccumulateBatchShipping() {
        return accumulateBatchShipping;
    }

    public void setAccumulateBatchShipping(byte accumulateBatchShipping) {
        this.accumulateBatchShipping = accumulateBatchShipping;
    }

    public byte getPrintItemImageonQuote() {
        return PrintItemImageonQuote;
    }

    public void setPrintItemImageonQuote(byte PrintItemImageonQuote) {
        this.PrintItemImageonQuote = PrintItemImageonQuote;
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

    public int getDeliveryOverReceiveAllowance() {
        return deliveryOverReceiveAllowance;
    }

    public void setDeliveryOverReceiveAllowance(int deliveryOverReceiveAllowance) {
        this.deliveryOverReceiveAllowance = deliveryOverReceiveAllowance;
    }

    public int getInvoiceOverChargeAllowance() {
        return invoiceOverChargeAllowance;
    }

    public void setInvoiceOverChargeAllowance(int invoiceOverChargeAllowance) {
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
        return ItemsDefaultsSalesAccount;
    }

    public void setItemsDefaultsSalesAccount(GlAccounts ItemsDefaultsSalesAccount) {
        this.ItemsDefaultsSalesAccount = ItemsDefaultsSalesAccount;
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
