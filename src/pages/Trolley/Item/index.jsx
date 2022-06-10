import React, { Fragment } from 'react'
import { Card, InputNumber, Form, Input, Button } from "antd";
import { Link } from "react-router-dom";
import "antd/dist/antd.css";
import "./index.css";

export default function Item(props) {
  const [count, setCount] = React.useState(0);
  const productValue = props.productValue;
  const delOrder = props.delOrder;
  const changePrice = props.changePrice;

  return (
    <div className="site-card-border-less-wrapper">
      <Card
        title={productValue.productName}
        className="item"
        extra={
          <Link to={`/productAbout/${productValue.productId}`}>
            查看商品详情
          </Link>
        }
      >
        <Form.Item>
          <span>个数：</span>
          <span>{productValue.productNum}</span>
        </Form.Item>
        <Form.Item>
          <span>单价：</span>
          <span>{productValue.productPrice}</span>
        </Form.Item>
        <Form.Item>
          <span>配送地区：{productValue.address}</span>
        </Form.Item>
        <Form.Item>
          <span>收货地址：{productValue.userAddress}</span>
        </Form.Item>
        <span>总价：</span>
        <span id="mySummerPrice">
          {productValue.productPrice * productValue.productNum}
        </span>
        <Form.Item>
          <span>创建时间：{productValue.time}</span>
        </Form.Item>
        <Button type="primary" onClick={() => delOrder(productValue.orderId)}>
          删除商品
        </Button>
      </Card>
    </div>
  );
}
