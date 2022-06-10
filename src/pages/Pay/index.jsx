import React, { Fragment } from 'react'
import Header from "../../components/Header";
import Footer from "../../components/Footer";
import { AlipayCircleOutlined } from '@ant-design/icons';
import { Button } from "antd";
import { useNavigate, useParams } from "react-router-dom";
import "./index.css";

export default function Pay() {
  const { sumPrice } = useParams();
  const navgate = useNavigate();
  
  const toSuccess = () => {
    const orders = JSON.parse(localStorage.getItem(`odered`));
    console.log(orders);
    orders.map((value) => {
      value.status = `已支付`;
    })
    navgate("/success");
  }

  return (
    <Fragment>
      <Header />
      <div className="pay">
        <div className="container">
          <span style={{ fontSize: "40px" }}>支付: </span>
          <AlipayCircleOutlined style={{ fontSize: "50px", color: "#08c" }} />
          <br />
          <span style={{ fontSize: "40px" }}>合计:{sumPrice}</span>
          <br />
          <img
            src="https://tvax3.sinaimg.cn/large/006z6YU4ly1h2x73xxjwqj30dw0dwt9j.jpg"
            alt="#"
            width={300}
            height={300}
            className="erweima"
          />
          <br />
          <Button
            type="primary"
            size="large"
            style={{ marginLeft: "40px" }}
            onClick={toSuccess}
          >
            完成支付
          </Button>
        </div>
      </div>

      <Footer />
    </Fragment>
  );
}
