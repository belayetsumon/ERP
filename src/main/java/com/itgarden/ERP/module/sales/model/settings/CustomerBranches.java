/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.sales.model.settings;

import com.itgarden.ERP.module.finance_banking.model.settings.GlAccounts;
import com.itgarden.ERP.module.inventory.model.settings.InventoryLocations;
import com.itgarden.ERP.module.settings.model.company_setup.TaxGroups;
import com.itgarden.ERP.module.sales.model.enumvalue.SalesGroups;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 *
 * @author User
 */
@Entity
public class CustomerBranches {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// Name and Contact
	private String branchName;

	private String branchShortName;

	@ManyToOne
	private SalesPersons salesPerson;

	@ManyToOne
	private SalesAreas salesArea;

	@NotNull(message = "This field cannot be blank.")
	
	@Enumerated(EnumType.STRING)
	private SalesGroups salesGroup;

	@ManyToOne
	private InventoryLocations inventoryLocations;

	@ManyToOne
	private TaxGroups taxGroup;

	// GL Accounts
	@NotNull(message = "Sales Account field cannot be blank.")
	@ManyToOne
	private GlAccounts salesAccount;

	@NotNull(message = "Sales Discount Account field cannot be blank.")
	@ManyToOne
	private GlAccounts salesDiscountAccount;

	@NotNull(message = "Accounts Receivable Account field cannot be blank.")
	@ManyToOne
	private GlAccounts accountsReceivableAccount;

	@NotNull(message = "Prompt Payment Discount Account field cannot be blank.")
	@ManyToOne
	private GlAccounts promptPaymentDiscountAccount;

	private String bankAccountNumber;

	// General contact data
	// Contact Data //
	private String contactPerson;
	private String phoneNumber;
	private String mobileNumber;
	private String faxNumber;
	@Email
	private String email;

	// Addresses
	@Lob
	private String mailingAddress;
	@Lob
	private String physicalAddress;
	@Lob
	private String generalNotes;

	public CustomerBranches() {
	}

	public CustomerBranches(Long id, String branchName, String branchShortName, SalesPersons salesPerson,
			SalesAreas salesArea, @NotNull(message = "This field cannot be blank.") SalesGroups salesGroup,
			InventoryLocations inventoryLocations, TaxGroups taxGroup,
			@NotNull(message = "Sales Account field cannot be blank.") GlAccounts salesAccount,
			@NotNull(message = "Sales Discount Account field cannot be blank.") GlAccounts salesDiscountAccount,
			@NotNull(message = "Accounts Receivable Account field cannot be blank.") GlAccounts accountsReceivableAccount,
			@NotNull(message = "Prompt Payment Discount Account field cannot be blank.") GlAccounts promptPaymentDiscountAccount,
			String bankAccountNumber, String contactPerson, String phoneNumber, String mobileNumber, String faxNumber,
			@Email String email, String mailingAddress, String physicalAddress, String generalNotes) {
		super();
		this.id = id;
		this.branchName = branchName;
		this.branchShortName = branchShortName;
		this.salesPerson = salesPerson;
		this.salesArea = salesArea;
		this.salesGroup = salesGroup;
		this.inventoryLocations = inventoryLocations;
		this.taxGroup = taxGroup;
		this.salesAccount = salesAccount;
		this.salesDiscountAccount = salesDiscountAccount;
		this.accountsReceivableAccount = accountsReceivableAccount;
		this.promptPaymentDiscountAccount = promptPaymentDiscountAccount;
		this.bankAccountNumber = bankAccountNumber;
		this.contactPerson = contactPerson;
		this.phoneNumber = phoneNumber;
		this.mobileNumber = mobileNumber;
		this.faxNumber = faxNumber;
		this.email = email;
		this.mailingAddress = mailingAddress;
		this.physicalAddress = physicalAddress;
		this.generalNotes = generalNotes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchShortName() {
		return branchShortName;
	}

	public void setBranchShortName(String branchShortName) {
		this.branchShortName = branchShortName;
	}

	public SalesPersons getSalesPerson() {
		return salesPerson;
	}

	public void setSalesPerson(SalesPersons salesPerson) {
		this.salesPerson = salesPerson;
	}

	public SalesAreas getSalesArea() {
		return salesArea;
	}

	public void setSalesArea(SalesAreas salesArea) {
		this.salesArea = salesArea;
	}

	public SalesGroups getSalesGroup() {
		return salesGroup;
	}

	public void setSalesGroup(SalesGroups salesGroup) {
		this.salesGroup = salesGroup;
	}

	public InventoryLocations getInventoryLocations() {
		return inventoryLocations;
	}

	public void setInventoryLocations(InventoryLocations inventoryLocations) {
		this.inventoryLocations = inventoryLocations;
	}

	public TaxGroups getTaxGroup() {
		return taxGroup;
	}

	public void setTaxGroup(TaxGroups taxGroup) {
		this.taxGroup = taxGroup;
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

	public GlAccounts getAccountsReceivableAccount() {
		return accountsReceivableAccount;
	}

	public void setAccountsReceivableAccount(GlAccounts accountsReceivableAccount) {
		this.accountsReceivableAccount = accountsReceivableAccount;
	}

	public GlAccounts getPromptPaymentDiscountAccount() {
		return promptPaymentDiscountAccount;
	}

	public void setPromptPaymentDiscountAccount(GlAccounts promptPaymentDiscountAccount) {
		this.promptPaymentDiscountAccount = promptPaymentDiscountAccount;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMailingAddress() {
		return mailingAddress;
	}

	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}

	public String getPhysicalAddress() {
		return physicalAddress;
	}

	public void setPhysicalAddress(String physicalAddress) {
		this.physicalAddress = physicalAddress;
	}

	public String getGeneralNotes() {
		return generalNotes;
	}

	public void setGeneralNotes(String generalNotes) {
		this.generalNotes = generalNotes;
	}

	
}
