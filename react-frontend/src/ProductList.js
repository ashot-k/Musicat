
import "./product-list.scss";
const ProductList = ({ products, url }) => {
    return (
        <div className="Product-List">
            <div className="d-flex flex-column list-container">
                <div className="product-list rounded d-flex flex-wrap p-5 gap-4">
                    <div className="result-info">
                        {/* <h2 className="p-2 result-title" th:if="${!#strings.isEmpty(keyword)} and ${#strings.isEmpty(noResults)}" th:text="${keyword} " />
      <h5 className="p-2" th:if="${!#strings.isEmpty(keyword)} and ${#strings.isEmpty(noResults)}" th:text="${products.getNumberOfElements()} + ' products' " />
      <h1 className="display-6 p-3 error404 rounded" th:if="${!#strings.isEmpty(noResults)}" th:utext="'No results for: <b>' + ${keyword} + '</b>'" />*/}
                    </div>

                    {products.map((product) => (
                        <div className="card-container" >
                            <div className="card border-top-0">
                                <a href="#">
                                    <img className="product-image border border-dark border-1 rounded" id={'product' + product.id} src={url + product.imageURL} alt="image not found" />
                                </a>
                                <div className="card-body product-info d-flex flex-column ">
                                    <h6 className="card-title album-name">
                                        <a className="album-name"  href="#" >{product.name}</a>
                                    </h6>
                                    <a className="card-subtitle hov text-decoration-none artist-name" href="#">{product.artist}</a> 
                                    <p className="product-price" >{product.price + '€'}</p>
                                    <div style={{ width: '100%' }}>
                                        <button className="add-to-cart-btn btn btn-outline-success fw-bold" type="button" >Add to Cart</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    ))}

                </div>
                {/* <div className="mt-2" if="${#strings.isEmpty(noResults)} " th: insert="~{/fragments/page-nav :: pages}" /> */}
            </div>







        </div>
    )
}
export default ProductList;