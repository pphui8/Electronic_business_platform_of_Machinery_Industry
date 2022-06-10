import React from 'react'
import Productcard from "../../../components/Productcard";
import toast from "react-hot-toast";
import { nanoid } from "nanoid";

let index = [];
export default function Products() {
    const [products, setProducts] = React.useState([]);

    const getProducts = () => {
      fetch("http://192.168.211.156:8080/mall/product/findfloors.do")
        .then((res) => res.json())
        .then((data) => {
          data.data.oneFloor.forEach((element) => {
            index.push(element);
          });
          data.data.twoFloor.forEach((element) => {
            index.push(element);
          });
          data.data.threeFloor.forEach((element) => {
            index.push(element);
          });
          data.data.fourFloor.forEach((element) => {
            index.push(element);
          });
          setProducts(index);
        })
        .catch((err) => toast.error(`请求失败`));
    };

    React.useEffect(() => {
      getProducts();
    }, []);
  return (
    <div>
      <section className="product_section layout_padding">
        <div className="container">
          <div className="row">
            {index.map((value, index) => {
              return (
                <Productcard
                  key={nanoid()}
                  product_name={value.name}
                  price={value.price}
                  product_id={value.id}
                  photo_url={value.iconUrl}
                ></Productcard>
              );
            })}
          </div>
        </div>
      </section>
    </div>
  );
}
