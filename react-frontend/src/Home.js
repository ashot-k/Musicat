
import ProductList from "./ProductList";
import useFetchData from "./FetchData";


const Home = () => {
    const domain = "http://localhost:8080";
    const url = "http://localhost:8080/api/products";
    const { data, isPending, error } = useFetchData(url);
    
    return (
        <div className="home">
            {isPending && <div>Loading..... </div>}
            {data && <ProductList products={data} domain={domain} />}
        </div>
    );
}
export default Home;