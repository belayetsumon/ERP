/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.branch.controller.transactions;


import com.itgarden.ERP.globalservices.DateTimeService;
import com.itgarden.ERP.module.finance_banking.model.enumvalue.AccountType;
import com.itgarden.ERP.module.finance_banking.repository.settings.BankAccountsRepository;
import com.itgarden.ERP.module.finance_banking.settings.services.BankAccountsService;
import com.itgarden.ERP.module.inventory.model.settings.InventoryLocations;
import com.itgarden.ERP.module.inventory.model.settings.Items;
import com.itgarden.ERP.module.inventory.model.transactions.StockMoves;
import com.itgarden.ERP.module.inventory.repository.settings.InventoryLocationsRepository;
import com.itgarden.ERP.module.inventory.repository.settings.ItemsRepository;
import com.itgarden.ERP.module.inventory.repository.transactions.StockMovesRepository;
import com.itgarden.ERP.module.inventory.settings.services.InventoryLocationsService;
import com.itgarden.ERP.module.inventory.settings.services.ItemService;
import com.itgarden.ERP.module.sales.cart.model.SalesCartItem;
import com.itgarden.ERP.module.sales.model.enumvalue.SalesTypeName;
import com.itgarden.ERP.module.sales.model.transactions.Pos;
import com.itgarden.ERP.module.sales.model.transactions.PosItem;
import com.itgarden.ERP.module.sales.repository.settings.CustomersRepository;
import com.itgarden.ERP.module.sales.repository.settings.SalesPersonsRepository;
import com.itgarden.ERP.module.sales.repository.transactions.PosItemRepository;
import com.itgarden.ERP.module.sales.repository.transactions.PosRepository;
import com.itgarden.ERP.module.sales.repository.transactions.SalesQuotationEntryRepository;
import com.itgarden.ERP.module.sales.settings.services.CustomerService;
import com.itgarden.ERP.module.sales.transactions.services.PosService;
import com.itgarden.ERP.module.settings.model.company_setup.TransactionsType;
import com.itgarden.ERP.module.settings.repository.company_setup.TransactionsTypeRepository;
import com.itgarden.ERP.module.settings.repository.miscellaneous.PaymentTermsRepository;
import com.itgarden.ERP.module.settings.repository.miscellaneous.ShippingCompanyRepository;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/branchpos")
public class BranchPosController {

    @Autowired
    DateTimeService globalService;

    // Repository 
    @Autowired
    InventoryLocationsService inventoryLocationsService;

    @Autowired
    CustomersRepository customersRepository;

    @Autowired
    PaymentTermsRepository paymentTermsRepository;

    @Autowired
    SalesQuotationEntryRepository salesQuotationEntryRepository;

    @Autowired
    TransactionsTypeRepository transactionsTypeRepository;

    @Autowired
    ShippingCompanyRepository shippingCompanyRepository;

    @Autowired
    ItemsRepository itemsRepository;

    // service
    @Autowired
    CustomerService customerService;

    @Autowired
    ItemService itemService;

    @Autowired
    PosRepository posRepository;

    @Autowired
    PosService posService;

    @Autowired
    PosItemRepository posItemRepository;

    @Autowired
    InventoryLocationsRepository inventoryLocationsRepository;

    @Autowired
    BankAccountsRepository bankAccountsRepository;

    @Autowired
    SalesPersonsRepository salesPersonsRepository;

    @Autowired
    BankAccountsService bankAccountsService;

    @Autowired
    StockMovesRepository stockMovesRepository;

    @RequestMapping("/index")
    public String index(Model model, Pos pos) throws ParseException {

        model.addAttribute("customerlist", customersRepository.findAll());

        model.addAttribute("banklist", bankAccountsRepository.findAll());

        model.addAttribute("salesmanlist", salesPersonsRepository.findAll());

        model.addAttribute("pricetypelist", SalesTypeName.values());

        model.addAttribute("shippingCompanylist", shippingCompanyRepository.findAll());

        model.addAttribute("itemList", itemService.allActiveAndSalableItemList(false, false));

        // set location
        InventoryLocations inventoryLocations = inventoryLocationsRepository.findByLocationCode("jamuna-2");

        pos.setFromStkLoc(inventoryLocations);

        model.addAttribute("locationName", inventoryLocations);

        ///   set  transactions Type
        TransactionsType transactionsType = new TransactionsType();
        transactionsType = transactionsTypeRepository.findBySlug("pos");
        pos.setTransactionsType(transactionsType);

        pos.setPosReference(posService.refarance());

        model.addAttribute("ref", posService.refarance());

        pos.setInvoiceDate(globalService.currentDate());

        model.addAttribute("ndate", globalService.currentDate());

//        SalesCartItem salesCartItem = new SalesCartItem();
//        model.addAttribute("salesCartItem", salesCartItem);
        return "module/branch/transactions/pos";
    }

    @RequestMapping("/save")
    public String save(Model model, @Valid Pos pos, BindingResult bindingResult,
            RedirectAttributes redirectAttrs, HttpSession salesItemSession) throws ParseException {

        boolean referCheck = posService.referCodeCheck(pos.getPosReference());

        if (referCheck == true) {

            ObjectError referCodeError;

            referCodeError = new ObjectError("posReference", "This code " + pos.getPosReference() + " is exists. Please  refresh your browser and try again.");

            bindingResult.addError(referCodeError);
        }

        List<SalesCartItem> cartItemList = (List<SalesCartItem>) salesItemSession.getAttribute("salesItemCartSession");

        if (cartItemList == null) {

            ObjectError cartItemListError;

            cartItemListError = new ObjectError("salesInvoiceItem", "You must enter at least one non empty item line.");

            bindingResult.addError(cartItemListError);
        }

        if (bindingResult.hasErrors()) {

            model.addAttribute("customerlist", customersRepository.findAll());

            model.addAttribute("banklist", bankAccountsRepository.findAll());

            model.addAttribute("salesmanlist", salesPersonsRepository.findAll());

            model.addAttribute("pricetypelist", SalesTypeName.values());

            model.addAttribute("shippingCompanylist", shippingCompanyRepository.findAll());

            model.addAttribute("itemList", itemService.allActiveAndSalableItemList(false, false));

            // set location
            InventoryLocations inventoryLocations = inventoryLocationsRepository.findByLocationCode("jamuna-2");

            pos.setFromStkLoc(inventoryLocations);

            model.addAttribute("locationName", inventoryLocations);

            ///   set  transactions Type
            TransactionsType transactionsType = new TransactionsType();
            transactionsType = transactionsTypeRepository.findBySlug("pos");
            pos.setTransactionsType(transactionsType);

            pos.setPosReference(posService.refarance());

            model.addAttribute("ref", posService.refarance());

            // date set
            pos.setInvoiceDate(globalService.currentDate());

            model.addAttribute("ndate", globalService.currentDate());

            SalesCartItem salesCartItem = new SalesCartItem();
            model.addAttribute("salesCartItem", salesCartItem);
            return "module/branch/transactions/pos";
        }

        if (pos.getBankAccounts().getAccountType() == AccountType.cash) {
            pos.setAlloc(pos.getGrandTotal());
        }

        posRepository.save(pos);

        Pos lastinsertid = posRepository.findTopByOrderByInvoicenoDesc();
        Long inid = lastinsertid.getInvoiceno();

        List<PosItem> posItemList = new ArrayList<PosItem>();

        List<StockMoves> stockMoveslist = new ArrayList<StockMoves>();

        for (SalesCartItem cartItemLists : cartItemList) {

            PosItem posItem = new PosItem();
            // 1
            posItem.setPos(lastinsertid);

//            TransactionsType transactionsTypeid = new TransactionsType();
//
//            transactionsTypeid.setId(254L);
            posItem.setTransactionsType(pos.getTransactionsType());

            Items items = new Items();

            items.setId(cartItemLists.getItemId());

            //2 
            posItem.setItemId(items);

            //3
            posItem.setDescription("");

            //4 
            posItem.setUnitPrice(cartItemLists.getPrice());
            // 5 
            posItem.setUnitTax(cartItemLists.getTaxPercent());
            //6 

            posItem.setQuantity(cartItemLists.getQuantity());
            //7

            posItem.setDiscount_percent(cartItemLists.getDiscountPercent());

            posItem.setItemTotal(cartItemLists.getItemTotal());

            posItemList.add(posItem);

            //salesQuotationItemRepository.saveAll(salesQuotationItemList);
            //  stock moves From
            StockMoves stockMovesFrom = new StockMoves();

            stockMovesFrom.setTranDate(pos.getInvoiceDate());

            stockMovesFrom.setItems(items);

            TransactionsType transactionsType = new TransactionsType();

            transactionsType = transactionsTypeRepository.findBySlug("pos");

            stockMovesFrom.setTransType(transactionsType);

            stockMovesFrom.setTransNo(inid);

            stockMovesFrom.setReference(pos.getPosReference());

            stockMovesFrom.setInventoryLocations(pos.getFromStkLoc());

            stockMovesFrom.setQty(cartItemLists.getQuantity().negate());

            stockMovesFrom.setPrice(cartItemLists.getPrice().negate());

            if (items.getStandardCosts() == null) {

                stockMovesFrom.setStandardCost(BigDecimal.valueOf(0));

            } else {

                stockMovesFrom.setStandardCost(items.getStandardCosts().getPrice().negate());
            }

            stockMovesFrom.setTotalPrice(cartItemLists.getItemTotal().negate());

            stockMovesFrom.setMemo(pos.getComments());

            stockMoveslist.add(stockMovesFrom);
        }

        posItemRepository.saveAll(posItemList);

        stockMovesRepository.saveAll(stockMoveslist);

        salesItemSession.removeAttribute("salesItemCartSession");
        salesItemSession.removeAttribute("salesItemCartSessionTotalDiscount");
        salesItemSession.removeAttribute("salesItemCartSessionTotalVat");
        salesItemSession.removeAttribute("salesItemCartSessionItemTotal");
        redirectAttrs.addAttribute("id", inid).addFlashAttribute("success_messages", "Successfully Save.");
        // return "redirect:/salesinvoice/view/{id}";
        return "redirect:/branchpos/print/{id}";
    }

    @RequestMapping("/list")
    public String list(Model model) {

        Pageable pageable = new PageRequest(0, 100000, Sort.Direction.DESC, "invoiceno");

        model.addAttribute("list", posRepository.findAll(pageable));

        return "module/branch/report/branch_pos_sales_list";
    }

    @RequestMapping("/unallocatedlist")
    public String unallocatedlist(Model model) {

        model.addAttribute("list", posRepository.findByAllocIsNullOrderByInvoicenoDesc());

        return "module/branch/report/unallocated_sales_list";
    }

    @RequestMapping("/allocate/{id}")
    public String allocate(Model model, @PathVariable Long id, RedirectAttributes redirectAttrs) {

        Pos pos = posRepository.getOne(id);

        pos.setAlloc(pos.getGrandTotal());

        posRepository.save(pos);

        redirectAttrs.addFlashAttribute("success_messages ", "Sucessfully save.");

        return "redirect:/branchpos/unallocatedlist";
    }

    @GetMapping(value = "/view/{id}")
    public String view(Model model, @PathVariable Long id, Pos pos) {

        model.addAttribute("inId", id);

        return "module/branch/transactions/pos_view";
    }

    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable Long id, Pos pos) {

        model.addAttribute("pos", posRepository.getOne(id));

        return "module/branch/transactions/pos_details";
    }

    @GetMapping(value = "/print/{id}")
    public String print(Model model, @PathVariable Long id, Pos pos) {

        model.addAttribute("pos", posRepository.getOne(id));

        return "module/branch/transactions/pos_print";
    }

    @GetMapping(value = "/close")
    public String close(Model model, HttpSession salesItemSession) {

        if (salesItemSession.getAttribute("salesItemCartSession") != null) {

            salesItemSession.removeAttribute("salesItemCartSession");

        }

        if (salesItemSession.getAttribute("salesItemCartSessionTotalDiscount") != null) {

            salesItemSession.removeAttribute("salesItemCartSessionTotalDiscount");

        }

        if (salesItemSession.getAttribute("salesItemCartSessionTotalVat") != null) {

            salesItemSession.removeAttribute("salesItemCartSessionTotalVat");

        }

        if (salesItemSession.getAttribute("salesItemCartSessionItemTotal") != null) {

            salesItemSession.removeAttribute("salesItemCartSessionItemTotal");

        }

        return "redirect:/branch/index";
    }

    @GetMapping(value = "/clean")
    public String clean(Model model, HttpSession salesItemSession) {

        if (salesItemSession.getAttribute("salesItemCartSession") != null) {

            salesItemSession.removeAttribute("salesItemCartSession");

        }

        if (salesItemSession.getAttribute("salesItemCartSessionTotalDiscount") != null) {

            salesItemSession.removeAttribute("salesItemCartSessionTotalDiscount");

        }

        if (salesItemSession.getAttribute("salesItemCartSessionTotalVat") != null) {

            salesItemSession.removeAttribute("salesItemCartSessionTotalVat");

        }

        if (salesItemSession.getAttribute("salesItemCartSessionItemTotal") != null) {

            salesItemSession.removeAttribute("salesItemCartSessionItemTotal");

        }

        return "redirect:/branchpos/index";
    }

    @GetMapping(value = "/bankcharge/{id}", produces = {"application/json"})
    @ResponseBody
    public BigDecimal bankCharge(Model model, @PathVariable Long id) {

        bankAccountsService.bankcharge(id);

        return bankAccountsService.bankcharge(id);

    }

}
