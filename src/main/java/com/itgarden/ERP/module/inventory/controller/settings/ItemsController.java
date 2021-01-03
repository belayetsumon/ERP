/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.ERP.module.inventory.controller.settings;

import org.springframework.web.bind.annotation.RequestMethod;
import java.io.IOException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.Barcode39;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ResponseBody;
import com.itgarden.ERP.module.inventory.DTO.ItemDTO;
import com.itgarden.ERP.module.inventory.model.settings.StandardCosts;
import com.itgarden.ERP.module.inventory.model.settings.PurchasingPricing;
import com.itgarden.ERP.module.inventory.model.enumvalue.SalesType;
import com.itgarden.ERP.module.inventory.model.settings.SalesPricing;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.validation.BindingResult;
import javax.validation.Valid;
import com.itgarden.ERP.module.settings.model.enumvalue.YesNo;
import com.itgarden.ERP.module.inventory.model.enumvalue.Itemstatus;
import com.itgarden.ERP.module.inventory.model.enumvalue.ItemType;
import com.itgarden.ERP.module.inventory.model.settings.Items;
import org.springframework.ui.Model;
import com.itgarden.ERP.module.inventory.settings.services.SalesPricingService;
import com.itgarden.ERP.module.inventory.settings.services.ItemService;
import com.itgarden.ERP.module.finance_banking.repository.settings.GlAccountsRepository;
import com.itgarden.ERP.module.inventory.repository.settings.UnitsRepository;
import com.itgarden.ERP.module.settings.repository.company_setup.ItemTaxTypesRepository;
import com.itgarden.ERP.module.inventory.repository.settings.ItemCategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.itgarden.ERP.module.inventory.repository.settings.ItemsRepository;
import com.itgarden.ERP.module.inventory.repository.settings.PurchasingPricingRepository;
import com.itgarden.ERP.module.inventory.repository.settings.SalesPricingRepository;
import com.itgarden.ERP.module.inventory.repository.settings.StandardCostsRepository;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

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

        return "redirect:/items/details/{id}";
    }

    @RequestMapping(value = {"/productlabel/{id}"}, method = {RequestMethod.GET}, produces = {"application/pdf"})
    @ResponseBody
    public void createProductLabel(Model model, @PathVariable Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {

        Items item = itemsRepository.getOne(id);

        double retailSalesPricing = salesPricingService.pricebyItemAndType(item, SalesType.Retail);

        String pdfFileName = "item_code" + item.getItemCode() + ".pdf";

        try {
            String text = request.getParameter("text");
            if (text == null || text.trim().length() == 0) {
                text = "You didn't enter any text.";
            }

            Rectangle rect = new Rectangle(150, 108);
            rect.enableBorderSide(1);
            rect.enableBorderSide(2);
            rect.enableBorderSide(4);
            rect.enableBorderSide(8);
            rect.setBorder(2);
            rect.setBorderColor(BaseColor.BLACK);

            Document document = new Document(rect, 5, 5, 5, 5);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            PdfWriter pdfWriter = PdfWriter.getInstance(document, baos);

            document.open();
            PdfContentByte cb = pdfWriter.getDirectContent();
            Font fontSize_8_bold = FontFactory.getFont("Times", 8.0f, 1, BaseColor.DARK_GRAY);

            Paragraph item_label = new Paragraph("Price:" + retailSalesPricing + "+VAT", fontSize_8_bold);

            document.add(item_label);

            //   barcode //
//            Barcode39 barcode39 = new Barcode39();
//            barcode39.setCode("123456789");
//            barcode39.setCode(item.getItemCode());
//
//            Image codeEANImage = barcode39.createImageWithBarcode(cb, null, null);
//
//            document.add(codeEANImage);

            Barcode128 code128 = new Barcode128();
            code128.setGenerateChecksum(true);
            code128.setCode(item.getItemCode());

            //Add Barcode to PDF document
            document.add(code128.createImageWithBarcode(cb, null, null));

            // end barcode
            document.close();

            response.setHeader("Expires", "0");

            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");

            response.setHeader("Pragma", "public");

            response.setContentType("application/pdf");

            response.setContentLength(baos.size());

            response.setHeader("Content-disposition", "inline; filename=" + pdfFileName);

            OutputStream os = (OutputStream) response.getOutputStream();
            baos.writeTo(os);
            os.flush();
            os.close();

        } catch (DocumentException e) {
            throw new IOException(e.getMessage());
        }
    }
}
