function addToCart(item, quantity) {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(item),
        url: '/add-to-cart',
        success: function () {

        },
        error: function (xhr, status, error) {
            console.log("Error:", error);
        }
    });
}