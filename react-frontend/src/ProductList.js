
import "./css/product-list.scss";
import ProductCard from './ProductCard';
const ProductList = ({ products, domain }) => {
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
                        <ProductCard product={product} imageURL={domain + product.imageURL}></ProductCard>
                    ))}
                </div>
                {/* <div className="mt-2" if="${#strings.isEmpty(noResults)} " th: insert="~{/fragments/page-nav :: pages}" /> */}
            </div>
        </div>
    )
}
export default ProductList;