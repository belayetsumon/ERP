/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {

    $(function () {
        $("#iltDate").datepicker({

            dateFormat: 'dd/mm/yy'

        });
    });

});


$(document).ready(function () {
    $('.inventory_location_transfer_item_select').select2({
        placeholder: 'Select a item'
    });
});




// Quteation item search By Name 

$(document).ready(function () {

    $('.inventory_location_transfer_item_select').on('change', function () {
        var itemId = $(this).val();
        // alert("Item-Id");
        $.ajax({
            type: 'GET',
            url: "/items/itembyitemid/" + itemId,
            dataType: 'json',
            success: function (data) {
                $("#iadj_id").val(data.id);

                $("#iadj_quantity").val(1);


                $("#iadj_price").val(data.retailPrice);


                $("#iadj_unit").val(data.unitsofMeasureName);

                $("#iadju_itemTotal").val(data.retailPrice);

                //alert(data);
            },
            error: function (request, status, error) {
                alert(request.responseText);
            }
        });

    });

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

    $('.salesPriceListTable').on('click', '#salesPriceDelete', function (event) {
        event.preventDefault();
        var tr = $(this).closest("tr");
        var salesPriceId = $(tr).find("#salesPriceId").val();
        //   alert(salesPriceId);
        $.ajax({
            type: 'GET',
            url: "/salespricing/delete/" + salesPriceId,
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