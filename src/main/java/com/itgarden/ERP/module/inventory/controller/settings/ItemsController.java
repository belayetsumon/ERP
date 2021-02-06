/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.inventory.controller.settings;

import com.itextpdf.barcodes.Barcode128;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.property.TextAlignment;
import com.itgarden.ERP.module.finance_banking.repository.settings.GlAccountsRepository;
import com.itgarden.ERP.module.inventory.DTO.ItemDTO;
import com.itgarden.ERP.module.inventory.model.enumvalue.ItemType;
import com.itgarden.ERP.module.inventory.model.enumvalue.Itemstatus;
import com.itgarden.ERP.module.inventory.model.enumvalue.SalesType;
import com.itgarden.ERP.module.inventory.model.settings.Items;
import com.itgarden.ERP.module.inventory.model.settings.PurchasingPricing;
import com.itgarden.ERP.module.inventory.model.settings.SalesPricing;
import com.itgarden.ERP.module.inventory.model.settings.StandardCosts;
import com.itgarden.ERP.module.inventory.repository.settings.ItemCategoriesRepository;
import com.itgarden.ERP.module.inventory.repository.settings.ItemsRepository;
import com.itgarden.ERP.module.inventory.repository.settings.PurchasingPricingRepository;
import com.itgarden.ERP.module.inventory.repository.settings.SalesPricingRepository;
import com.itgarden.ERP.module.inventory.repository.settings.StandardCostsRepository;
import com.itgarden.ERP.module.inventory.repository.settings.UnitsRepository;
import com.itgarden.ERP.module.inventory.settings.services.ItemService;
import com.itgarden.ERP.module.inventory.settings.services.SalesPricingService;
import com.itgarden.ERP.module.settings.model.enumvalue.YesNo;
import com.itgarden.ERP.module.settings.repository.company_setup.ItemTaxTypesRepository;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping({"/items"})
public class ItemsController {

    @Autowired
    ItemsRepository itemsRepository;
    @Autowired
    ItemCategoriesRepository itemCategoriesRepository;
    @Autowired
    ItemTaxTypesRepository itemTaxTypesRepository;
    @Autowired
    UnitsRepository unitsRepository;
    @Autowired
    GlAccountsRepository glAccountsRepository;
    @Autowired
    ItemService itemService;
    @Autowired
    SalesPricingService salesPricingService;

    @Autowired
    SalesPricingRepository salesPricingRepository;

    @Autowired
    PurchasingPricingRepository purchasingPricingRepository;

    @Autowired
    StandardCostsRepository standardCostsRepository;

    @RequestMapping({"/index"})
    public String index(final Model model, final Items items) {
        model.addAttribute("itemlist", itemService.allItems());
        model.addAttribute("itemCategorieslist", itemCategoriesRepository.findAll());
        model.addAttribute("itemtaxtypes", itemTaxTypesRepository.findAll());
        model.addAttribute("itemtypes", ItemType.values());
        model.addAttribute("units", unitsRepository.findAll());
        model.addAttribute("glaccounts", glAccountsRepository.findAll());
        model.addAttribute("ststus", Itemstatus.values());
        model.addAttribute("yesno", YesNo.values());

        return "module/inventory/settings/itemsIndex";
    }

    @RequestMapping({"/add"})
    public String add(final Model model, final Items items) {
        model.addAttribute("itemCategorieslist", itemCategoriesRepository.findAll());
        model.addAttribute("itemtaxtypes", itemTaxTypesRepository.findAll());
        model.addAttribute("itemtypes", ItemType.values());
        model.addAttribute("units", unitsRepository.findAll());
        model.addAttribute("glaccounts", glAccountsRepository.findAll());
        model.addAttribute("ststus", Itemstatus.values());
        model.addAttribute("yesno", YesNo.values());
        // items.setItemCode(itemService.itemCode());
        return "module/inventory/settings/items";
    }

    @RequestMapping({"/save"})
    public String save(final Model model, @Valid final Items items, final BindingResult bindingResult, final RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("itemlist", itemsRepository.findAll());
            model.addAttribute("itemCategorieslist", itemCategoriesRepository.findAll());
            model.addAttribute("itemtaxtypes", itemTaxTypesRepository.findAll());
            model.addAttribute("itemtypes", ItemType.values());
            model.addAttribute("units", unitsRepository.findAll());
            model.addAttribute("glaccounts", glAccountsRepository.findAll());
            model.addAttribute("ststus", Itemstatus.values());
            model.addAttribute("yesno", YesNo.values());
            // items.setItemCode(itemService.itemCode());
            return "module/inventory/settings/items";
        }
        this.itemsRepository.save(items);
        redirectAttrs.addFlashAttribute("success_messages", " Successfully Save.");
        return "redirect:/items/index";
    }

    @RequestMapping({"/edit/{id}"})
    public String edit(final Model model, @PathVariable final Long id, final Items items) {
        model.addAttribute("items", this.itemsRepository.getOne(id));
        model.addAttribute("itemlist", itemsRepository.findAll());
        model.addAttribute("itemCategorieslist", itemCategoriesRepository.findAll());
        model.addAttribute("itemtaxtypes", itemTaxTypesRepository.findAll());
        model.addAttribute("itemtypes", ItemType.values());
        model.addAttribute("units", unitsRepository.findAll());
        model.addAttribute("glaccounts", glAccountsRepository.findAll());
        model.addAttribute("ststus", Itemstatus.values());
        model.addAttribute("yesno", YesNo.values());
        return "module/inventory/settings/items";
    }

    @GetMapping({"/delete/{id}"})
    public String delete(final Model model, @PathVariable final Long id, final Items items, final RedirectAttributes redirectAttrs) {
        redirectAttrs.addFlashAttribute("success_messages", " Successfully Delete.");
        this.itemsRepository.deleteById(id);
        return "redirect:/items/index";
    }

    @GetMapping({"/details/{id}"})
    public String details(final Model model, @PathVariable final Long id, Items items, final RedirectAttributes redirectAttrs) {
        items = (Items) this.itemsRepository.getOne(id);
        model.addAttribute("items", items);
        final SalesPricing salesPricing = new SalesPricing();
        salesPricing.setItem(items);
        model.addAttribute("salesPricing", salesPricing);
        model.addAttribute("salesPricingList", salesPricingService.salesTypeFindByItemId(items));
        model.addAttribute("salestype", SalesType.values());
        model.addAttribute("purchasingPricing", new PurchasingPricing());
        model.addAttribute("standardCosts", new StandardCosts());
        return "module/inventory/settings/itemsDetails";
    }

    @GetMapping(value = {"/itembyitemcode/{itemCode}"}, produces = {"application/json"})
    @ResponseBody
    public ItemDTO itemByItemCode(final Model model, @PathVariable final int itemCode) {
        final ItemDTO iteminfo = this.itemService.itemByItemCodeAndactiveSales(itemCode, false, true);
        return iteminfo;
    }

    @GetMapping(value = {"/itembyitemid/{id}"}, produces = {"application/json"})
    @ResponseBody
    public ItemDTO itemById(final Model model, @PathVariable final Long id) {
        final ItemDTO iteminfo = itemService.itemById(id);
        return iteminfo;
    }

    @RequestMapping({"/short_entry"})
    public String itemShortEntry(final Model model, final Items items) {
        model.addAttribute("itemCategorieslist", itemCategoriesRepository.findAll());
        model.addAttribute("itemtaxtypes", itemTaxTypesRepository.findAll());
        model.addAttribute("itemtypes", ItemType.values());
        model.addAttribute("units", unitsRepository.findAll());
        model.addAttribute("glaccounts", glAccountsRepository.findAll());
        model.addAttribute("ststus", Itemstatus.values());
        model.addAttribute("yesno", YesNo.values());
        model.addAttribute("itemCode", itemService.itemCode());

        return "module/inventory/settings/items_short_entry";
    }

    @RequestMapping({"/short_entry_save"})
    public String shortEntrySave(final Model model,
            @RequestParam(required = true)
            @NotNull double retailPrice,
            @RequestParam(required = true)
            @NotNull double wholesalePrice,
            @RequestParam(required = true)
            @NotNull BigDecimal purchasePrice,
            @RequestParam(required = true)
            @NotNull BigDecimal standardCosting,
            @Valid Items items, final BindingResult bindingResult, final RedirectAttributes redirectAttrs) {
        
        boolean itemcheck = itemService.itemCodeCheck(items.getItemCode());
        
          if (itemcheck ==true) {

            ObjectError itemCodeError;

            itemCodeError = new ObjectError("itemCode", "This code "+items.getItemCode()+"exists. Please  Refresh your browser and try again.");

            bindingResult.addError(itemCodeError);
        }
        
        
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("itemlist", itemsRepository.findAll());
            model.addAttribute("itemCategorieslist", itemCategoriesRepository.findAll());
            model.addAttribute("itemtaxtypes", itemTaxTypesRepository.findAll());
            model.addAttribute("itemtypes", ItemType.values());
            model.addAttribute("units", unitsRepository.findAll());
            model.addAttribute("glaccounts", glAccountsRepository.findAll());
            model.addAttribute("ststus", Itemstatus.values());
            model.addAttribute("yesno", YesNo.values());
            // items.setItemCode(itemService.itemCode());
            model.addAttribute("itemCode", itemService.itemCode());
            return "module/inventory/settings/items_short_entry";
        }
        this.itemsRepository.save(items);

        items = itemService.itemLastInsertedId();

        //salse pricing save
        SalesPricing retailPriceing = new SalesPricing();

        retailPriceing.setItem(items);
        retailPriceing.setSalesType(SalesType.Retail);
        retailPriceing.setPrice(retailPrice);

        salesPricingRepository.save(retailPriceing);

        // whole sale price save
        SalesPricing wholesalePriceing = new SalesPricing();

        wholesalePriceing.setItem(items);
        wholesalePriceing.setSalesType(SalesType.Wholesale);
        wholesalePriceing.setPrice(wholesalePrice);

        salesPricingRepository.save(wholesalePriceing);

        // purchase price save  
        PurchasingPricing purchasingPricing = new PurchasingPricing();

        purchasingPricing.setItemId(items);
        purchasingPricing.setPrice(purchasePrice);

        purchasingPricingRepository.save(purchasingPricing);

        //  standard coast save
        StandardCosts standardCosts = new StandardCosts();

        standardCosts.setItemId(items);

        standardCosts.setPrice(purchasePrice);

        standardCostsRepository.save(standardCosts);

        //redirectAttrs.addFlashAttribute("success_messages", " Successfully Save.");
        redirectAttrs.addAttribute("id", items.getId()).addFlashAttribute("success_messages", "Successfully Save.");

        return "redirect:/items/productlabel/{id}";
    }

    @RequestMapping(value = {"/productlabel-2/{id}"}, method = {RequestMethod.GET}, produces = {"application/pdf"})
    @ResponseBody
    public void createProductLabel(Model model, @PathVariable Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {

        Items item = itemsRepository.getOne(id);

        double retailSalesPricing = salesPricingService.pricebyItemAndType(item, SalesType.Retail);

        String pdfFileName = "item_code" + item.getItemCode() + ".pdf";

        // try{
        String text = request.getParameter("text");
        if (text == null || text.trim().length() == 0) {
            text = "You didn't enter any text.";
        }

        //  Rectangle rect = new Rectangle(219, 144);
        // Rectangle rect = new Rectangle(219, 144);
        Rectangle rect = new Rectangle(140, 120);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        ///  PdfWriter pdfWriter = PdfWriter.getInstance(document, baos);
        // PdfWriter writer = new PdfWriter(pdfFileName);
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(baos));
        pdfDoc.addNewPage();
        Document document = new Document(pdfDoc, new PageSize(rect));

        document.setMargins(5, 5, 0, 0);

//// Price
        Style price = new Style();
        PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_BOLD);
        price.setFont(font);
        Paragraph pra = new Paragraph();

        pra.add("Price:" + (int) Math.round(retailSalesPricing) + "+VAT" + "\n").addStyle(price).setMargins(0, 0, 0, 0).setFontSize(16).setTextAlignment(TextAlignment.CENTER);
        /// barcode
        Style barcode = new Style();
        Barcode128 code128 = new Barcode128(pdfDoc);
        // code128.setGenerateChecksum(true);
        code128.setCode(item.getItemCode());
        code128.setX(1f);
        code128.setFont(null);
        code128.setCodeType(Barcode128.CODE128);
        Image code128Image = new Image(code128.createFormXObject(pdfDoc));
        pra.add(code128Image).addStyle(barcode).setMargins(0, 0, 0, 0);
        //// code
        Style code = new Style();
        PdfFont fontcode = PdfFontFactory.createFont(FontConstants.COURIER_BOLD);
        price.setFont(fontcode);
        pra.add("\n" + item.getItemCode() + "\n").addStyle(code).setMargins(0, 0, 0, 0).setFontSize(10);

/// Product Name
        Style productnameStyle = new Style();

        //Resource resource = new ClassPathResource("ARIALUNI.TTF");
        // Resource resource = new ClassPathResource("ArialUnicodeMS.ttf");
        Resource resource = new ClassPathResource("Noto_Sans_Bengali-Regular.ttf");

        //        Resource resource = new ClassPathResource("unicode.timesu.ttf");
        // Resource resource = new ClassPathResource("NikoshBAN.ttf");
        //Resource resource = new ClassPathResource("SolaimanLipi.ttf");
        // System.out.println("ffffffffffffffffffffffffffffffffffffffffffffff"+resource.getFilename());
        System.out.println("ffffffffffffffffffffffffffffffffffffffffffffff" + resource.getURI());

        System.out.println("ffffffffffffffffffffffffffffffffffffffffffffff" + resource.getURL());

        System.out.println("ffffffffffffffffffffffffffffffffffffffffffffff" + resource.getFile());

        System.out.println("ffffffffffffffffffffffffffffffffffffffffffffff" + resource.getInputStream());

        System.out.println("ffffffffffffffffffffffffffffffffffffffffffffff" + item.getName());

        PdfFont namefont = PdfFontFactory.createFont(resource.getFile().getPath(), PdfEncodings.IDENTITY_H, true);

        //PdfFont namefont = PdfFontFactory.createFont(FontConstants.COURIER_BOLD);
        productnameStyle.setFont(namefont);

        pra.add(item.getName() + "\n").addStyle(productnameStyle).setMargins(0, 0, 0, 0).setFontSize(12);

        // discount 
        if (item.getDiscount() > 0.00) {
            pra.add("Discount:" + item.getDiscount() + "%");
        }

        document.add(pra);
//
//            // end barcode

        document.close();

        /// header settings 
        response.setHeader("Expires", "0");

        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");

        response.setHeader("Pragma", "public");

        response.setContentType("application/pdf; charset=UTF-8");

        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        response.setContentLength(baos.size());

        response.setHeader("Content-disposition", "inline; filename=" + pdfFileName);

        OutputStream os = (OutputStream) response.getOutputStream();

        baos.writeTo(os);

        os.flush();

        os.close();

    }

    @RequestMapping(value = {"/productlabel/{id}"})

    public String itemShortEntry(Model model, @PathVariable Long id) {
        Items item = itemsRepository.getOne(id);
        model.addAttribute("item", item);

        model.addAttribute("retailSalesPricing", (int) Math.round(salesPricingService.pricebyItemAndType(item, SalesType.Retail)));
        // discount 
        if (item.getDiscount() > 0.00) {
            model.addAttribute("discount", item.getDiscount());
        }
        return "module/inventory/settings/items_print";
    }
}
