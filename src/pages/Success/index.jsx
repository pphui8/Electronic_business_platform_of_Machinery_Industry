import React, { Fragment } from 'react'
import Header from "../../components/Header";
import Footer from "../../components/Footer";
import { useNavigate } from 'react-router-dom'
import { Card } from "antd";
import { CheckCircleOutlined } from "@ant-design/icons";

export default function Success() {
  const navgate = useNavigate();
  const toOrdered = () => {
      navgate(`/aboutme/orders`);
  }
  return (
    <Fragment>
      <Header />
      {/* <!-- inner page section --> */}
      <section className="inner_page_head">
        <div className="container_fuild">
          <div className="row">
            <div className="col-md-12">
              <div className="full">
                <h3>购买成功</h3>
              </div>
            </div>
          </div>
        </div>
      </section>
      {/* <!-- end inner page section --> */}
      <div className="container">
        <Card
          title="感谢您的支持"
          extra={
            <a href="#" onClick={toOrdered}>
              去我的订单
            </a>
          }
          style={{ width: "70%" }}
          bordered={false}
        >
          <CheckCircleOutlined />
          <span style={{"marginLeft": "10px"}}>请等待商家发货</span>
        </Card>
      </div>
      <Footer />
    </Fragment>
  );
}
