/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//Date Start

//Quotation Item Search
$(document).ready(function () {
    $('#qtCustomers').select2({
        placeholder: 'Select a month'
    });
});

$(document).ready(function () {

    $(function () {
        $("#qtDate").datepicker({

            dateFormat: 'dd/mm/yy'

        });
    });

    $(function () {
        $("#qtdeliveryDate").datepicker(
                {
                    dateFormat: 'dd/mm/yy'
                });
    });

});


//Date End


//Customer Search

$(document).ready(function () {

    $('#qtCustomers').on('change', function () {

        var customerId = $(this).val();
//alert(customerId);
        $.ajax({
            type: 'GET',
            url: "/customers/customerbyid/" + customerId,
            dataType: 'json',
            success: function (data) {

                // $("#contactPersonsName").val("");
                $("#discount").val("");

                $("#contactPersonsName").val(data.contactPersonsName);

                $("#discount").val(data.discountPercent);

                $("#priceType").append(new Option(data.salesTypeOrPriceList, data.salesTypeOrPriceList, true, true));

                //alert(data);
            },
            error: function (request, status, error) {
                alert(request.responseText);
            }
        });

    });



});


//End Customer And order Information

//Start Item Information
//
//Quotation Item Search
$(document).ready(function () {
    $('.qtitemselect').select2({
        placeholder: 'Select a month'
    });
});



// Quteation item search By Name 

$(document).ready(function () {

    $('.qtitemselect').on('change', function () {
        var itemId = $(this).val();
        // alert("Item-Id");
        $.ajax({
            type: 'GET',
            url: "/items/itembyitemid/" + itemId,
            dataType: 'json',
            success: function (data) {
                $("#qtid").val(data.id);

                $("#qtquantity").val(1);

                if ($("#priceType").val() === "Wholesale") {
                    $("#qtprice").val(data.wholesalePrice);
                } else {

                    $("#qtprice").val(data.retailPrice);
                }
                $("#qtunit").val(data.unitsofMeasureName);
                $("#qtdiscount").val(data.discount);

                //alert(data);
            },
            error: function (request, status, error) {
                alert(request.responseText);
            }
        });

    });


//Price Calculation

    $('#qtprice,#qtquantity,#qtdiscount,#qtVat').on('change', function () {

        var qtquantity = $("#qtquantity").val();
        var qtiprice = $("#qtprice").val();
        var qtidis = $("#qtdiscount").val();
        var qtVat = $("#qtVat").val();

//        var dis = (qtidis / 100);

        var unitTotal = qtquantity * qtiprice;

        var totaldis = (unitTotal * qtidis) / 100;

        var afterDiscount = unitTotal - totaldis;


        var totalTax = (afterDiscount * qtVat) / 100;

        var afterTaxTotal = afterDiscount + totalTax;



        $("#qttotalDiscount").val(totaldis.toFixed(2));

        $("#qttotalVat").val(totalTax.toFixed(2));

        $("#qtitemTotal").val(afterTaxTotal.toFixed(2));

    });
});

// Quteation item Submit



$(document).ready(function () {

    $("#quotationItemSaveButton").on("click", quotationItemSave);

    function quotationItemSave() {
        //  var url = $("#qTsalesCartItem").attr("action");

        var formData = {
            'id': $('#qtid').val(),
            'itemCode': $('.qtitemselect').val(),
            'itemDescription': $('#qtdescription').val(),
            'quantity': $('#qtquantity').val(),
            'unit': $('#qtunit').val(),
            'price': $('#qtprice').val(),
            'discountPercent': $('#qtdiscount').val(),
            'discountTotal': $('#qttotalDiscount').val(),
            'taxPercent': $('#qtVat').val(),
            'taxTotal': $('#qttotalVat').val(),
            'itemTotal': $('#qtitemTotal').val(),
        };

        // alert(JSON.stringify(formData));
        $.ajax({
            type: 'POST', // define the type of HTTP verb we want to use (POST for our form)
            url: "/salescart/hello",
            data: JSON.stringify(formData), // our data object
            dataType: 'json',
            contentType: 'application/json',

            success: function (data) {
                window.location.reload();

                //  alert(JSON.stringify(data.msg));
                // $('#qTitemTable').html(data).delay(1000);
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

    $("#qTitemTable").on("click", '#qTitemDelete', function () {
//        alert("hello");

        var tr = $(this).closest("tr");
        var itemId = $(tr).find("#itemId").html();
//        alert(itemId);

        $.ajax({
            type: 'GET',
            url: "/salescart/itemdelete/" + itemId,
            success: function (data) {
                alert(data);
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

    $("#qTitemTable").on("click", '#qTitemEdit', function () {

        alert("hello");

        var tr = $(this).closest("tr");

        var itemId = $(tr).find("#itemId").html();
        var itemCode = $(tr).find("#itemCode").html();
        var itemQuantity = $(tr).find("#itemQuantity").html();
        var itemUnit = $(tr).find("#itemUnit").html();
        var itemPrice = $(tr).find("#itemPrice").html();
        var itemDiscount = $(tr).find("#itemDiscount").html();
        var itemTax = $(tr).find("#itemTax").html();
        var itemItemTotal = $(tr).find("#itemItemTotal").html();
        $('#qtid').val(itemId);
        $('.qtitemselect').val(itemCode);

        $(".qtitemselect").append(new Option(itemCode, itemId, true, true));

        $('#qtdescription').val();
        $('#qtquantity').val(itemQuantity);
        $('#qtunit').val(itemUnit);
        $('#qtprice').val(itemPrice);
        $('#qtdiscount').val(itemDiscount);
        $('#qtVat').val(itemTax);
        $('#qtitemTotal').val(itemItemTotal);

    });

});