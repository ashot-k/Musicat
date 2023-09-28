
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
    str = str.replace("mini-");
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

            addToMiniCart();
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
            console.log("called onanimend");
            container.removeChild(added_container);
        }, false);
    }, false);

    const img = document.createElement("img");
    img.src = document.getElementById("product" + productId).src
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



let cart_number = document.getElementsByClassName("cart-items-number")[0];

function updateCartItemCounter() {
    cartItems = $("#mini-cart-content  .mini-cart-item");
    let counter = 0;
    if (cartItems.length < 1) {
        cart_number.innerHTML = "";
        return counter;
    }
    for (let i = 0; i < cartItems.length; i++) {
        const quantityField = cartItems[i].querySelector(".mini-cart-item-quantity").innerHTML;
        counter += parseInt(quantityField);
    }
    cart_number.innerHTML = parseInt(counter);
}
function increaseCartItemCounter() {
    if (cart_number.innerHTML.length < 1) {
        cart_number.innerHTML = 1;
        return;
    }
    cart_number.innerHTML = parseInt(cart_number.innerHTML) + 1;
}


