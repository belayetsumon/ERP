/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



$(document).ready(function () {

    $(function () {
        $("#adDate").datepicker({

            dateFormat: 'dd/mm/yy'

        });
    });

});




// Quteation item search By Name 

$(document).ready(function () {

    $('.inventory_adjust_item_select').on('change', function () {
        var itemId = $(this).val();
        // alert("Item-Id");
        $.ajax({
            type: 'GET',
            url: "/items/itembyitemid/" + itemId,
            dataType: 'json',
            success: function (data) {
                $("#iadj_id").val(data.id);

                $("#iadj_quantity").val(1);


                $("#iadj_price").val(data.purchasePrice);


                $("#iadj_unit").val(data.unitsofMeasureName);

                $("#iadju_itemTotal").val(data.purchasePrice);


                //alert(data);
            },
            error: function (request, status, error) {
                alert(request.responseText);
            }
        });

    });

});



//Price Calculation

$(document).ready(function () {

    $('#iadj_price,#iadj_quantity').on('change', function () {

        var quantity = $("#iadj_quantity").val();
        var price = $("#iadj_price").val();

//        var dis = (qtidis / 100);

        var itemTotal = quantity * price;



        $("#iadju_itemTotal").val(itemTotal);

    });

});





// adjustment item Submit



$(document).ready(function () {

    $("#inventory_adj_ItemSaveButton").on("click", quotationItemSave);

    function quotationItemSave() {
        //  var url = $("#qTsalesCartItem").attr("action");

        var formData = {
            'id': $('#iadj_id').val(),
            'itemCode': $('.inventory_adjust_item_selectt').val(),
            'itemDescription': $('#didescription').val(),
            'quantity': $('#iadj_quantity').val(),
            'unit': $('#iadj_unit').val(),
            'price': $('#iadj_price').val(),
           
            'itemTotal': $('#iadju_itemTotal').val(),
        };

        // alert(JSON.stringify(formData));
        $.ajax({
            type: 'POST', // define the type of HTTP verb we want to use (POST for our form)
            url: "/inventorycartitem/save",
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





//Item sales price Update

$(document).ready(function () {

    $('.salesPriceListTable').on('click', '#salesPriceEdit', function (event) {
        event.preventDefault();
        var tr = $(this).closest("tr");
        var salesPriceId = $(tr).find("#salesPriceId").val();
        var salePriceType = $(tr).find("#salespriceType").html();
        var salePrice = $(tr).find("#salesPrice").html();

        $("#id").val(salesPriceId);
        $("#price").val(salePrice);
        $("#salesType").append(new Option(salePriceType, salePriceType, true, true));

        // alert("id" + salesPriceId + "itemId" + salesPriceProductId + "prictype" + salePriceType + salePrice);


    });

});



//Item sales price delete

$(document).ready(function () {

    $('.adj_itemTable').on('click', '#adj_itemDelete', function (event) {
        event.preventDefault();
        var tr = $(this).closest("tr");
        var itemId = $(tr).find("#itemId").val();
        //   alert(salesPriceId);
        $.ajax({
            type: 'GET',
            url: "/inventorycartitem/delete/" + salesPriceId,
            success: function () {
                alert("Successfully deleted");
                $(tr).remove();
            },
            error: function (request, status, error) {
                alert('Delete error !!');
            }
        });
    });
});