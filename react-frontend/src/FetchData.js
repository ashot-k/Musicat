import { useEffect, useState } from "react";

const useFetchData = (url) => {
    const [data, setData] = useState(null);
    const [isPending, setPending] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        getData(url)
    }, [url]);

    function getData(url) {
        fetch(url)
            .then(res => {
                return res.json();
            })
            .then(data => {
                setData(data);
                setPending(false);
            }).catch(err => {
                setError(err);
                if (data == null)
                    setTimeout(() => {
                        getData(url);
                    }, 2500);
            })
    }

    return { data, isPending, error }
}
export default useFetchData



