/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//Date Start

//Quotation Item Search
$(document).ready(function () {
    $('#diCustomers').select2({
        placeholder: 'Select a month'
    });
});

$(document).ready(function () {

    $(function () {
        $("#invoiceDate").datepicker({

            dateFormat: 'dd/mm/yy'

        });
    });

    $(function () {
        $("#diDueDate").datepicker(
                {
                    dateFormat: 'dd/mm/yy'
                });
    });

});


//Date End


//Customer Search

$(document).ready(function () {

    $('#diCustomers').on('change', function () {

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
    $('.diitemselect').select2({
        placeholder: 'Select a item'
    });
});



// Quteation item search By Name 

$(document).ready(function () {

    $('.diitemselect').on('change', function () {
        var itemId = $(this).val();
        // alert("Item-Id");
        $.ajax({
            type: 'GET',
            url: "/items/itembyitemid/" + itemId,
            dataType: 'json',
            success: function (data) {
                $("#diid").val(data.id);

                $("#diquantity").val(1);

                if ($("#priceType").val() === "Wholesale") {
                    $("#diprice").val(data.wholesalePrice);
                } else {

                    $("#diprice").val(data.retailPrice);
                }
                $("#diunit").val(data.unitsofMeasureName);
                $("#didiscount").val(data.discount);

                //alert(data);
            },
            error: function (request, status, error) {
                alert(request.responseText);
            }
        });

    });


//Price Calculation

    $('#diprice,#diquantity,#didiscount,#diVat').on('change', function () {

        var qtquantity = $("#diquantity").val();
        var qtiprice = $("#diprice").val();
        var qtidis = $("#didiscount").val();
        var qtVat = $("#diVat").val();

//        var dis = (qtidis / 100);

        var unitTotal = qtquantity * qtiprice;

        var totaldis = (unitTotal * qtidis) / 100;

        var afterDiscount = unitTotal - totaldis;


        var totalTax = (afterDiscount * qtVat) / 100;

        var afterTaxTotal = afterDiscount + totalTax;



        $("#ditotalDiscount").val(totaldis.toFixed(2));

        $("#ditotalVat").val(totalTax.toFixed(2));

        $("#diitemTotal").val(afterTaxTotal.toFixed(2));

    });
});

// Quteation item Submit



$(document).ready(function () {

    $("#invoiceItemSaveButton").on("click", quotationItemSave);

    function quotationItemSave() {
        //  var url = $("#qTsalesCartItem").attr("action");

        var formData = {
            'id': $('#diid').val(),
            'itemCode': $('.diitemselect').val(),
            'itemDescription': $('#didescription').val(),
            'quantity': $('#diquantity').val(),
            'unit': $('#diunit').val(),
            'price': $('#diprice').val(),
            'discountPercent': $('#didiscount').val(),
            'discountTotal': $('#ditotalDiscount').val(),
            'taxPercent': $('#diVat').val(),
            'taxTotal': $('#ditotalVat').val(),
            'itemTotal': $('#diitemTotal').val(),
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

    $("#diitemTable").on("click", '#diitemDelete', function () {
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

    $("#diitemTable").on("click", '#diitemEdit', function () {

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
        $('#diid').val(itemId);
        $('.diitemselect').val(itemCode);

        $(".diitemselect").append(new Option(itemCode, itemId, true, true));

        $('#didescription').val();
        $('#diquantity').val(itemQuantity);
        $('#diunit').val(itemUnit);
        $('#diprice').val(itemPrice);
        $('#didiscount').val(itemDiscount);
        $('#diVat').val(itemTax);
        $('#diitemTotal').val(itemItemTotal);

    });

});