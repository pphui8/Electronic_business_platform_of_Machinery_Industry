import React, { Fragment, useEffect } from "react";
import Header from "../../components/Header";
import Footer from "../../components/Footer";
import Productcard from "../../components/Productcard";
import Item from "./Item";
import { Button } from "antd";
import "antd/dist/antd.css";
import "./index.css";
import { nanoid } from "nanoid";
import { useNavigate } from "react-router-dom";
import toast from "react-hot-toast";

export default function Trolley() {
  const [sumPrice, setSumPrice] = React.useState(0);
  const [orders, setOrders] = React.useState([]);
  const navigate = useNavigate();

  const getOrders = () => {
    const orders = localStorage.getItem("orders");
    if (orders) {
      // console.log(JSON.parse(orders));
      setOrders(JSON.parse(orders));
      let ord = JSON.parse(orders);
      let summer = 0;
      ord.map((value) => {
        summer += value.productNum * value.productPrice;
      })
      setSumPrice(summer);
    } else {
      const ordersContainer = document.getElementById("ordersContainer");
      ordersContainer.appendChild(
        document.createElement("div")
      ).innerHTML = `<div class="tips">您还没有订单</div>`;
    }
  }

  const delOrder = (id) => {
    const orders = localStorage.getItem("orders");
    // console.log(JSON.parse(orders));
    console.log(id);
    if (orders) {
      const newOrders = JSON.parse(orders).filter(
        (item) => item.orderId !== id
      );
      // console.log(newOrders);
      localStorage.setItem("orders", JSON.stringify(newOrders));
      setOrders(newOrders);
    }
  }

  useEffect(() => {
    getOrders();
  }, []);

  const toPay = () => {
    // 传总价过去
    const orders = localStorage.getItem("orders");
    if (!orders) {
      toast.error("您还没有订单");
      return;
    }
    let odered = JSON.parse(orders);
    odered.map((item) => {
      item.status = "已支付";
    });
    localStorage.setItem("odered", JSON.stringify(odered));
    localStorage.removeItem("orders");
    navigate(`/pay/${sumPrice}`);
  }
  
  return (
    <Fragment>
      <Header></Header>
      {/* <!-- inner page section --> */}
      <section className="inner_page_head">
        <div className="container_fuild">
          <div className="row">
            <div className="col-md-12">
              <div className="full">
                <h3>购物车</h3>
              </div>
            </div>
          </div>
        </div>
      </section>
      {/* <!-- end inner page section --> */}
      {/* <!-- Item section --> */}
      <div className="container" id="ordersContainer">
        {orders.map((value, index) => {
          return (
            <Item
              key={nanoid()}
              productValue={value}
              delOrder={delOrder}
            ></Item>
          );
        })}
      </div>
      {/* <!-- end Item section --> */}
      {/* <!-- sum section --> */}
      <div className="sum">
        <b className="sumText">总计 ￥{sumPrice}</b>
        <Button type="primary" onClick={toPay}>
          购卖
        </Button>
      </div>
      {/* <!-- end sum section --> */}
      <Footer></Footer>
    </Fragment>
  );
}