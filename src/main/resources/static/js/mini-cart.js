function addToMiniCart(data) {
        let cartContent = document.getElementById("mini-cart-content");
        let existingItem = cartContent.querySelector("#mini-cartItemId" + data.cartItemId);
        if(existingItem != null){
            cartContent.removeChild(existingItem);
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
        const miniCartItem = ` 
        <div  class="cart-item mini-cart-item"  id="mini-cartItemId${cartItem.cartItemId}">
    <div class="d-flex align-items-center gap-1 mini-cart-item-name-container">
        <a href="/item/${cartItem.product.id}">
            <img class="cart-item-image" src="${cartItem.product.imageURL}" alt="Image not Found">
        </a>
        <a class="cart-item-property cart-item-name hov" href="/item/${cartItem.product.id}">
            ${cartItem.product.name}
        </a>
    </div>
    <div class="mini-cart-item-info-container d-flex align-items-center justify-content-evenly">
        <div class="mini-cart-item-quantity-container d-flex flex-column">
            <label>Quantity</label>
            <h6 class="mini-cart-item-quantity" style="text-align: center">${cartItem.quantity}</h6>
        </div>
        <button class="btn btn-danger remove-item-button" type="button" 
        onclick="removeItem('${cartItem.cartItemId}')">Remove</button>
    </div>
 </div>`;
        cartContent.innerHTML += miniCartItem;
}

function removeFromMiniCart(id) {
    document.getElementById("mini-cartItemId" + id).remove();
}