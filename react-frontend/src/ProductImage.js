const ProductImage = ({ productImage }) => {
    const domain = "http://localhost:8080";

    return (
        <img className="border border-secondary border-1 rounded product-image" src={domain + productImage} alt="Product Image" />
    );
}

export default ProductImage;