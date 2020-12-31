/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//Date Start

//Quotation Item Search
$(document).ready(function () {
    $('#ddCustomers').select2({
        placeholder: 'Select a month'
    });
});

$(document).ready(function () {

    $(function () {
        $("#ddDate").datepicker({

            dateFormat: 'dd/mm/yy'

        });
    });

    $(function () {
        $("#dddeliveryDate").datepicker(
                {
                    dateFormat: 'dd/mm/yy'
                });
    });

});


//Date End


//Customer Search

$(document).ready(function () {

    $('#ddCustomers').on('change', function () {

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
    $('.dditemselect').select2({
        placeholder: 'Select a month'
    });
});



// Quteation item search By Name 

$(document).ready(function () {

    $('.dditemselect').on('change', function () {
        var itemId = $(this).val();
        // alert("Item-Id");
        $.ajax({
            type: 'GET',
            url: "/items/itembyitemid/" + itemId,
            dataType: 'json',
            success: function (data) {
                $("#ddid").val(data.id);

                $("#ddquantity").val(1);

                if ($("#priceType").val() === "Wholesale") {
                    $("#ddprice").val(data.wholesalePrice);
                } else {

                    $("#ddprice").val(data.retailPrice);
                }
                $("#ddunit").val(data.unitsofMeasureName);
                $("#dddiscount").val(data.discount);

                //alert(data);
            },
            error: function (request, status, error) {
                alert(request.responseText);
            }
        });

    });


//Price Calculation

    $('#ddprice,#ddquantity,#dddiscount,#ddVat').on('change', function () {

        var qtquantity = $("#ddquantity").val();
        var qtiprice = $("#ddprice").val();
        var qtidis = $("#dddiscount").val();
        var qtVat = $("#ddVat").val();

//        var dis = (qtidis / 100);

        var unitTotal = qtquantity * qtiprice;

        var totaldis = (unitTotal * qtidis) / 100;

        var afterDiscount = unitTotal - totaldis;


        var totalTax = (afterDiscount * qtVat) / 100;

        var afterTaxTotal = afterDiscount + totalTax;



        $("#ddtotalDiscount").val(totaldis.toFixed(2));

        $("#ddtotalVat").val(totalTax.toFixed(2));

        $("#dditemTotal").val(afterTaxTotal.toFixed(2));

    });
});

// Quteation item Submit



$(document).ready(function () {

    $("#ddItemSaveButton").on("click", quotationItemSave);

    function quotationItemSave() {
        //  var url = $("#qTsalesCartItem").attr("action");

        var formData = {
            'id': $('#ddid').val(),
            'itemCode': $('.dditemselect').val(),
            'itemDescription': $('#dddescription').val(),
            'quantity': $('#ddquantity').val(),
            'unit': $('#ddunit').val(),
            'price': $('#ddprice').val(),
            'discountPercent': $('#dddiscount').val(),
            'discountTotal': $('#ddtotalDiscount').val(),
            'taxPercent': $('#ddVat').val(),
            'taxTotal': $('#ddtotalVat').val(),
            'itemTotal': $('#dditemTotal').val(),
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

    $("#dditemTable").on("click", '#dditemDelete', function () {
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

    $("#dditemTable").on("click", '#dditemEdit', function () {

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
        $('#ddid').val(itemId);
        $('.dditemselect').val(itemCode);

        $(".dditemselect").append(new Option(itemCode, itemId, true, true));

        $('#dddescription').val();
        $('#ddquantity').val(itemQuantity);
        $('#ddunit').val(itemUnit);
        $('#ddprice').val(itemPrice);
        $('#dddiscount').val(itemDiscount);
        $('#ddVat').val(itemTax);
        $('#dditemTotal').val(itemItemTotal);

    });

});