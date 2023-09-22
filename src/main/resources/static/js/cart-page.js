function onLoad(){
    var quantityInputs = $(".quantity-input-field");
    quantityInputs.onchange = updatePrice;
}

function updatePrice(event){
    let quantityField = event.target.parentElement;
}