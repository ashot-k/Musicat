import { useParams } from "react-router-dom";
import useFetchData from "../../FetchData";
import ProductImage from "./ProductImage";
import TrackList from "./TrackList";

const ProductPage = () => {
  const { productId } = useParams();
  const { data: product, error, isPending } = useFetchData('http://localhost:8080/api/products/' + productId);
  ;

  return (
    <div className="container my-5">
      {isPending && <div> loading... </div>}
      {error && <div> {error}</div>}
      {product && (
        <div className="row">
          <div>
          </div>
          <div className="col-md-12 text-start mb-1">
            <h2 className="fw-bold"> {product.name} </h2>
          </div>
          <div className="d-flex flex-column align-items-center col-md-6 w-100 text-start mb-4 gap-3">
            <div className="d-flex flex-row align-items-start gap-4 w-100">
              <div>
                <ProductImage productImage={product.imageURL} />
              </div>
              <div className="col-lg-5 d-flex flex-fill flex-column justify-content-between" style={{ height: '240px', width: '500px' }}>
                <div>
                  <h4 className="fs-5 fw-bold">
                    Band:
                    <a className="hov text-decoration-none h5" style={{ textDecoration: 'none' }} href="#">{product.artist}</a>
                  </h4>
                  <h4 className="fs-5 fw-bold">Genre:{product.genre}</h4>
                  <h4 className="fs-5 fw-bold" >Year: {product.year}</h4>
                </div>
                <div>
                  <span className="fs-4 h4 fw-bold">Price:
                    <span className="fw-bold" >{product.price}€</span>
                  </span>
                  <hr className="mt-1" style={{ width: 0 }} />
                  <form action="#" method="post" style={{ width: '25%' }}>
                    <input type="hidden" name="productId" tvalue={product.id} />
                    <button type="button" className="btn btn-success fs-5 fw-bold w-100" >Add to Cart
                    </button>
                  </form>
                </div>
              </div>
            </div>
            <hr className="mt-3 mb-1 w-100" />
            <div style={{ width: '100%' }}>
              <span className="fs-5 self-align-end" style={{ display: 'block', padding: '5px 0 5px', wordWrap: 'normal' }} >
              </span>
            </div>
            <div>
              <div id="trackListing">
                <h4 style={{ textAlign: 'center' }}>Track Listing</h4>
                <hr className="mt-3 w-100" />
                <TrackList tracks={product.tracks} /> 
              </div>
            </div>
            <h4 className="my-4">Related Products</h4>
            <div className="row">
            </div>
          </div>
        </div>
      )}
    </div>
  )
}
export default ProductPage;