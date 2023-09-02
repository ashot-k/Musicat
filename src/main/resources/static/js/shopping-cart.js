function addToCart(item, name) {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(item),
        url: '/add-to-cart',
        success: function () {
            increaseCounter();
            addToCartPopup(item, name);
        },
        error: function (xhr, status, error) {
            console.log("Error:", error);
        }
    });
}

function addToCartPopup(item, name) {
    const container = document.getElementById("added-to-cart-popup");
    const added_container = document.createElement("div");

    //clear previous
    added_container.textContent = "";
    added_container.className = "product-added";
    added_container.addEventListener("animationend", function () {
        added_container.addEventListener("animationend", function () {
            console.log("called onanimend");
            container.removeChild(added_container);
        }, false);
    }, false);

    const img = document.createElement("img");
    img.src = document.getElementById(item).src
    img.width = 40;
    img.height = 40;
    const h6 = document.createElement("h6");
    h6.textContent = name + " has been added to the cart!";
    h6.style.padding = "5px";
    h6.style.whiteSpace = "nowrap";

    added_container.appendChild(img);
    added_container.appendChild(h6);
    container.appendChild(added_container);
}

function increaseCounter() {
    var cart_number = document.getElementById("cart-number").textContent;
    console.log(cart_number);
    document.getElementById("cart-number").textContent = parseInt(cart_number) + 1;
}

function decreaseCounter() {
    var cart_number = document.getElementById("cart-number").textContent;
    console.log(cart_number);
    document.getElementById("cart-number").textContent = parseInt(cart_number) - 1;
}
