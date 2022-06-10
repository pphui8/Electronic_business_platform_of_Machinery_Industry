import React, { Fragment } from 'react'
import Header from "../../components/Header";
import Footer from "../../components/Footer";
import toast from "react-hot-toast";
import { nanoid } from "nanoid";
import './index.css'
import Productcard from "../../components/Productcard";

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
    <Fragment>
      <Header></Header>
      {/* <!-- inner page section --> */}
      <section className="inner_page_head">
        <div className="container_fuild">
          <div className="row">
            <div className="col-md-12">
              <div className="full">
                <h3>购买产品</h3>
              </div>
            </div>
          </div>
        </div>
      </section>
      {/* <!-- end inner page section --> */}
      {/* <!-- product section --> */}
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
      {/* <!-- end product section --> */}
      <Footer></Footer>
    </Fragment>
  );
}