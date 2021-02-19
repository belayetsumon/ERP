/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//Date Start
//
//$(document).ready(function () {
//
//
//
//    $(function () {
//        $("#invoiceDate").datepicker({
//            update: new Date(),
//            dateFormat: 'dd/mm/yy'
//
//        });
//    });
//});
//Date End




//Start Item Information
//
//Quotation Item Search

// Quteation item search By Name 

$(document).ready(function () {

    /// barcode

    $('.p_itemBarCode').focusout(function () {
        var itemBarCode = $(this).val().trim();


        if (itemBarCode != '') {
            $.ajax({
                type: 'GET',
                url: "/items/itembyitemcode/" + itemBarCode,
                dataType: 'json',
                success: function (data) {

                    $("#p_itemId").val(data.id);

                    $("#p_itemBarCode").val();

                    $("#p_name").val(data.name);

                    $("#p_quantity").val(1);

                    $("#p_price").val(data.retailPrice);

                    $("#p_unit").val(data.unitsofMeasureName);

                    $("#p_discount").val(data.discount);

                    $("#p_vat").val(7.5);


                    if (data.discount != null) {

                        var discount = data.discount / 100;

                    } else {

                        var discount = 0.00;
                    }
                   var tdiscount = data.retailPrice * discount;

                    var afterDiscTotal = data.retailPrice - tdiscount;

                    var vat = 7.5 /100;

                    var tVat = afterDiscTotal * vat;


                    var afterVatTotal = afterDiscTotal + (afterDiscTotal * 7.5 / 100);

                    $("#p_totalDiscount").val(tdiscount.toFixed(2));

                    $("#p_totalVat").val(tVat.toFixed(2));


                    $("#p_itemTotal").val(afterVatTotal.toFixed(2));

                    //alert(data);
                },
                error: function (request, status, error) {
                    // alert(request.responseText);

                    alert("Something wrong!");
                }
            });

            /// Ajax end
        } else {

            alert("Product/Item barcode is empty");

        }

        //null condition end


    });


    //Price Calculation

    $('.p_itemBarCode,#p_price,#p_quantity,#p_discount,#p_vat').on('change', function () {

        var qtquantity = $("#p_quantity").val();
        var qtiprice = $("#p_price").val();
        var qtidis = $("#p_discount").val();
        var qtVat = $("#p_vat").val();

//        var dis = (qtidis / 100);

        var unitTotal = qtquantity * qtiprice;

        var totaldis = (unitTotal * qtidis) / 100;

        var afterDiscount = unitTotal - totaldis;


        var totalTax = (afterDiscount * qtVat) / 100;

        var afterTaxTotal = afterDiscount + totalTax;

        $("#p_totalDiscount").val(totaldis.toFixed(2));

        $("#p_totalVat").val(totalTax.toFixed(2));

        $("#p_itemTotal").val(afterTaxTotal.toFixed(2));

    });
});

// Quteation item Submit



$(document).ready(function () {

    $("#p_itemSaveButton").on("click", posItemSave);

    function posItemSave() {
        //  var url = $("#qTsalesCartItem").attr("action");

        var formData = {
            'id': $('#p_id').val(),
            'itemId': $('#p_itemId').val(),
            'itemCode': $('.p_itemBarCode').val(),
            'name': $('#p_name').val(),
            'itemDescription': $('#p_description').val(),
            'quantity': $('#p_quantity').val(),
            'unit': $('#p_unit').val(),
            'price': $('#p_price').val(),
            'discountPercent': $('#p_discount').val(),
            'discountTotal': $('#p_totalDiscount').val(),
            'taxPercent': $('#p_vat').val(),
            'taxTotal': $('#p_totalVat').val(),
            'itemTotal': $('#p_itemTotal').val(),
        };

        // alert(JSON.stringify(formData));
        $.ajax({
            type: 'POST', // define the type of HTTP verb we want to use (POST for our form)
            url: "/salescart/save",
            data: JSON.stringify(formData), // our data object
            dataType: 'json',
            contentType: 'application/json',

            success: function (data) {
                window.location.reload();

                $("#p_id").val("");
                $("#p_itemId").val("");
                $(".p_itemBarCode").val("");
                $("#p_name").val("");
                $("#p_description").val("");
                $("#p_quantity").val("");
                $("#p_price").val("");
                $("#p_unit").val("");
                $("#p_discount").val("");
                $("#p_totalDiscount").val("");
                $("#p_vat").val("");
                $("#p_totalVat").val("");
                $("#p_itemTotal").val("");
            },
            error: function (request, status, error) {
                alert(error);
            }
        });

    }
    ;
});


//qutation item delete

$(document).ready(function () {

    $("#p_itemTable").on("click", '#p_itemDelete', function () {
//        alert("hello");

        var tr = $(this).closest("tr");
        var id = $(tr).find("#id").html();
//        alert(itemId);

        $.ajax({
            type: 'GET',
            url: "/salescart/itemdelete/" + id,
            success: function (data) {
                // alert(data);
                $(tr).remove();
                window.location.reload();
            },
            error: function (request, status, error) {
                alert('Delete error !!');
            }
        });
    });

});

//quotation item update



$(document).ready(function () {

    $("#p_itemTable").on("click", '#p_itemEdit', function () {

        //   alert("hello");

        var tr = $(this).closest("tr");
        var id = $(tr).find("#id").html();
        var itemId = $(tr).find("#itemId").val();
        var itemCode = $(tr).find("#itemCode").html();
        var itemName = $(tr).find("#itemName").html();
        var itemQuantity = $(tr).find("#itemQuantity").html();
        var itemUnit = $(tr).find("#itemUnit").html();
        var itemPrice = $(tr).find("#itemPrice").html();
        var itemDiscount = $(tr).find("#itemDiscount").html();
        var itemTax = $(tr).find("#itemTax").html();
        var itemItemTotal = $(tr).find("#itemItemTotal").html();

        $('#p_id').val(id);
        $('#p_itemId').val(itemId);
        $('.p_itemBarCode').val(itemCode);
        $('#p_name').val(itemName);
        $('#p_description').val();
        $('#p_quantity').val(itemQuantity);
        $('#p_unit').val(itemUnit);
        $('#p_price').val(itemPrice);
        $('#p_discount').val(itemDiscount);
        $('#p_vat').val(itemTax);
        $('#p_itemTotal').val(itemItemTotal);

    });

});

/// bank charge

$(document).ready(function () {

    $("#bankAccounts").on("change", function () {

        var bankId = $(this).val().trim();
//        alert(itemId);

        $.ajax({
            type: 'GET',
            url: "/branchpos/bankcharge/" + bankId,
            success: function (data) {

                $("#bankCharge").val(data);

            },
            error: function (request, status, error) {
                alert('Something wrong!');
            }
        });

    });

});