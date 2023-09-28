function addToMiniCart(id) {
    let cartContent = document.getElementById("mini-cart-content");

    $.get("add-new", function (data) {
      /*  // Create a new cartItem object
        if(cartContent.querySelector("#" + data.cartItemId).length > 0){
            return;
        }
        const cartItem = {
            product: {
                id: data.product.id,
                imageURL: "/images/album-images/" + data.product.id + "/album_image.png",
                name: data.product.name
            },
            quantity: data.quantity,
            cartItemId: data.cartItemId
        };
        console.log(cartItem);
        const miniCartItem = document.createElement("body");
        miniCartItem.innerHTML = `
    <div class="d-flex align-items-center gap-1 mini-cart-item-name-container" style="width: 40%">
        <a href="/item/${cartItem.product.id}">
            <img class="cart-item-image" src="${cartItem.product.imageURL}" alt="Image not Found">
        </a>
        <a class="cart-item-property hov cart-item-name" href="/item/${cartItem.product.id}">
            ${cartItem.product.name}
        </a>
    </div>
    <div class="d-flex align-items-center gap-1 justify-content-end" style="width: 60%; padding-right: 15px">
        <div class="mini-cart-item-quantity-container d-flex flex-column">
            <label>Quantity</label>
            <h6 class="mini-cart-item-quantity" style="text-align: center">${cartItem.quantity}</h6>
        </div>
        <button class="btn btn-danger remove-item-button" type="button" onclick="removeItem('${cartItem.cartItemId}')">Remove</button>
    </div>
`;
        const container = document.getElementById("mini-cart-content");
        container.appendChild(miniCartItem);*/
    });
}

function removeFromMiniCart(id) {
    document.getElementById("mini-cartItemId" + id).remove();
}