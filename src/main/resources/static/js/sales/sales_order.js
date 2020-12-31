
//Quotation Item Search
$(document).ready(function () {
    $('#soCustomers').select2({
        placeholder: 'Select a month'
    });
});

$(document).ready(function () {

    $(function () {
        $("#orderDate").datepicker({

            dateFormat: 'dd/mm/yy'

        });
    });

    $(function () {
        $("#sodeliveryDate").datepicker(
                {
                    dateFormat: 'dd/mm/yy'
                });
    });

});


//Date End


//Customer Search

$(document).ready(function () {

    $('#soCustomers').on('change', function () {

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
    $('.soitemselect').select2({
        placeholder: 'Select a month'
    });
});



// Quteation item search By Name 

$(document).ready(function () {

    $('.soitemselect').on('change', function () {
        var itemId = $(this).val();
        // alert("Item-Id");
        $.ajax({
            type: 'GET',
            url: "/items/itembyitemid/" + itemId,
            dataType: 'json',
            success: function (data) {
                $("#soid").val(data.id);

                $("#soquantity").val(1);

                if ($("#priceType").val() === "Wholesale") {
                    $("#soprice").val(data.wholesalePrice);
                } else {

                    $("#soprice").val(data.retailPrice);
                }
                $("#sounit").val(data.unitsofMeasureName);
                $("#sodiscount").val(data.discount);

                //alert(data);
            },
            error: function (request, status, error) {
                alert(request.responseText);
            }
        });

    });


//Price Calculation

    $('#soprice,#soquantity,#sodiscount,#soVat').on('change', function () {

        var qtquantity = $("#soquantity").val();
        var qtiprice = $("#soprice").val();
        var qtidis = $("#sodiscount").val();
        var qtVat = $("#soVat").val();

//        var dis = (qtidis / 100);

        var unitTotal = qtquantity * qtiprice;

        var totaldis = (unitTotal * qtidis) / 100;

        var afterDiscount = unitTotal - totaldis;


        var totalTax = (afterDiscount * qtVat) / 100;

        var afterTaxTotal = afterDiscount + totalTax;



        $("#sototalDiscount").val(totaldis.toFixed(2));

        $("#sototalVat").val(totalTax.toFixed(2));

        $("#soitemTotal").val(afterTaxTotal.toFixed(2));

    });
});

// Quteation item Submit



$(document).ready(function () {

    $("#soItemSaveButton").on("click", quotationItemSave);

    function quotationItemSave() {
        //  var url = $("#qTsalesCartItem").attr("action");

        var formData = {
            'id': $('#soid').val(),
            'itemCode': $('.soitemselect').val(),
            'itemDescription': $('#sodescription').val(),
            'quantity': $('#soquantity').val(),
            'unit': $('#sounit').val(),
            'price': $('#soprice').val(),
            'discountPercent': $('#sodiscount').val(),
            'discountTotal': $('#sototalDiscount').val(),
            'taxPercent': $('#soVat').val(),
            'taxTotal': $('#sototalVat').val(),
            'itemTotal': $('#soitemTotal').val(),
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

    $("#soitemTable").on("click", '#soitemDelete', function () {
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

    $("#soitemTable").on("click", '#soitemEdit', function () {

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
        $('#soid').val(itemId);
        $('.soitemselect').val(itemCode);

        $(".soitemselect").append(new Option(itemCode, itemId, true, true));

        $('#sodescription').val();
        $('#soquantity').val(itemQuantity);
        $('#sounit').val(itemUnit);
        $('#soprice').val(itemPrice);
        $('#sodiscount').val(itemDiscount);
        $('#soVat').val(itemTax);
        $('#soitemTotal').val(itemItemTotal);

    });

});