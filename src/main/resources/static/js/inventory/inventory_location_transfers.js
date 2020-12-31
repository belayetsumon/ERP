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