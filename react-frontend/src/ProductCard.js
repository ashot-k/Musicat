import { Link } from 'react-router-dom';
import ProductImage from './ProductImage';
const ProductCard = ({ product, imageURL }) => {
    return (
        <div className="card-container" key={product.id}>
            <div className="card border-top-0">
                <Link to={'/items/' + product.id}>
                    <ProductImage productImage={product.imageURL} />
                </Link>
                <div className="card-body product-info d-flex flex-column ">
                    <h6 className="card-title album-name">
                        <a className="album-name" href="#">{product.name}</a>
                    </h6>
                    <a className="card-subtitle hov text-decoration-none artist-name" href="#">{product.artist}</a>
                    <p className="product-price" >{product.price + '€'}</p>
                    <div style={{ width: '100%' }}>
                        <button className="add-to-cart-btn btn btn-outline-success fw-bold" type="button">Add to Cart</button>
                    </div>
                </div>
            </div>
        </div>
    )
}
export default ProductCard;