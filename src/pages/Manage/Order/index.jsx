import React, { Fragment, useEffect } from "react";
import toast from "react-hot-toast";
import { Button, Card } from "antd";
import { nanoid } from "nanoid";

export default function Order() {
  const [orders, setOrders] = React.useState([]);

  const getOrders = () => {
    const odered = localStorage.getItem("odered");
    if (odered) {
      setOrders(JSON.parse(odered));
    } else {
      toast.error("您还没有订单");
    }
  };

  useEffect(() => {
    getOrders();
  }, []);

  const delThisOrder = (orderId) => {
    const odered = JSON.parse(localStorage.getItem("odered"));
    const newOrders = odered.filter((value) => value.orderId !== orderId);
    setOrders(newOrders);
    localStorage.setItem(`odered`, newOrders);
  };

  return (
    <Fragment>
      {orders.map((item, index) => {
        return (
          <Card
            title={item.productName}
            extra={
              <a href="#" onClick={() => delThisOrder(item.orderId)}>
                取消订单
              </a>
            }
            style={{
              width: 400,
              float: "left",
              margin: "10px",
            }}
            key={nanoid()}
          >
            <p>下单id：{item.account}</p>
            <p>商品数量：{item.productNum}</p>
            <p>收货地址：{item.userAddress}</p>
            <p>状态：{item.status}</p>
            {/* <Button type="primary" onClick={}>查看详细信息</Button> */}
          </Card>
        );
      })}
    </Fragment>
  );
}
