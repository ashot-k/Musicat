
function onLoad() {
    const cartItems = $(".cart-container  .cart-item");
    console.log("cart.js onLoad()");
    for (let i = 0; i < cartItems.length; i++) {
        const quantityField = cartItems[i].querySelector(".quantity-input-field");
        const totalPrice = cartItems[i].querySelector(".total-price");
        const unitPrice = cartItems[i].querySelector(".unit-price");
        const removeBtn = cartItems[i].querySelector(".remove-item-button");
        const id = cartItems[i].id;
        const miniCartItem = document.getElementById("mini-" + id);
        const miniCartQuantity = miniCartItem.querySelector(".mini-cart-item-quantity");

        quantityField.onchange = function () {
            let quantity = quantityField.value;
            if (quantity <= 0) {
                quantityField.value = 1;
                miniCartQuantity.innerHTML = quantityField.value;
                return;
            }
            miniCartQuantity.innerHTML = quantityField.value;
            updatePrice(quantity, unitPrice, totalPrice, id);
            updateCartItemCounter();
        }
        removeBtn.onclick = function () {
            removeItem(id);
        }
    }
}

function cartItemId(str) {
    str = str.replace("mini-", '');
    return str.replace("cartItemId", '');
}

function updatePrice(quantity, unitPrice, totalPrice, id) {
    totalPrice.value = quantity * parseFloat(unitPrice.innerHTML)
    totalPrice.innerHTML = totalPrice.value;
    $.ajax({
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(quantity),
        url: '/update-quantity/' + cartItemId(id),
        asynch: false,
        success: function () {
        },
        error: function (xhr, status, error) {
            console.log("Error:", error);
        }
    });
}

function addToCart(productId, name) {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(productId),
        url: '/add-to-cart',
        success: function () {
            addToCartPopup(productId, name);
            addToMiniCart(productId);
            increaseCartItemCounter();
        },
        error: function (xhr, status, error) {
            console.log("Error:", error);
        }
    });
}

function addToCartPopup(productId, name) {
    const container = document.getElementById("added-to-cart-popup");
    const added_container = document.createElement("div");
    //clear previous
    added_container.className = "product-added";
    added_container.addEventListener("animationend", function () {
        added_container.addEventListener("animationend", function () {
            container.removeChild(added_container);
        }, false);
    }, false);

    const img = document.createElement("img");
    img.src = document.getElementById("product" + productId).src;
    const itemName = document.createElement("h6");
    itemName.textContent = name;
    const h6 = document.createElement("h6")
    h6.textContent = " added to the cart!";
    added_container.appendChild(img);
    added_container.appendChild(itemName);
    added_container.appendChild(h6);
    itemName.id = "popupItemName";

    container.appendChild(added_container);
}
function removeItem(id) {
    id = cartItemId(id);
    const path = window.location.pathname;
    const page = path.substring(path.lastIndexOf('/') + 1);

    if(page == "cart"){
        removeFromCart(id);
    }
    removeFromMiniCart(id);
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: '/remove-item/' + cartItemId(id),
        asynch: false,
        success: function () {
            updateCartItemCounter();
        },
        error: function (xhr, status, error) {
            console.log("Error:", error);
        }
    });
}

function removeFromCart(id) {
    document.getElementById("cartItemId"+id).remove();
}



let cartCounter = document.getElementsByClassName("cart-items-number")[0];

function updateCartItemCounter() {
    let cartItems = $("#mini-cart-content  .mini-cart-item");
    let counter = 0;
    if (cartItems.length < 1) {
        cartCounter.innerHTML = "";
        return counter;
    }
    for (let i = 0; i < cartItems.length; i++) {
        const quantityField = cartItems[i].querySelector(".mini-cart-item-quantity").innerHTML;
        counter += parseInt(quantityField);
    }
    cartCounter.innerHTML = parseInt(counter);
}
function increaseCartItemCounter() {
    if (cartCounter.innerHTML.length < 1) {
        cartCounter.innerHTML = 1;
        return;
    }
    cartCounter.innerHTML = parseInt(cartCounter.innerHTML) + 1;
}


