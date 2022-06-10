import React, { Fragment, useState } from "react";
import Header from "../../components/Header";
import Footer from "../../components/Footer";
import Productcard from "../../components/Productcard";
import { Image, Card, InputNumber, Button, Select, Input } from "antd";
import toast from "react-hot-toast";
import "antd/dist/antd.css";
import { nanoid } from "nanoid";
import "./index.css"
import { useParams } from "react-router-dom";

export default function ProductAbout(props) {
  const [count, setCount] = React.useState(1);
  const { Option } = Select;
  let { product_id } = useParams();
  const [productValue, setProductValue] = React.useState({});
  const [cities, setCities] = useState(cityData[provinceData[0]]);
  const [secondCity, setSecondCity] = useState(cityData[provinceData[0]][0]);


  const handleProvinceChange = (value) => {
    setCities(cityData[value]);
    setSecondCity(cityData[value][0]);
  };

  const onSecondCityChange = (value) => {
    setSecondCity(value);
  };

  const onChange = (value) => {
    if(value < 1) {
      setCount(1);
      return;
    }
    setCount(value);
  }

  const getProducts = () => {
    if (product_id === undefined) {
      toast.error("请求失败");
      return;
    }
    
    fetch(`http://192.168.211.156:8080/mall/product/getdetail.do?productId=` + product_id)
      .then((res) => res.json())
      .then((data) => {
        setProductValue(data.data);
        if (data.status === 1) {
          toast.error(`商品信息请求失败`);
        }
      })
      .catch((err) => {
        console.log(err)
        toast.error(`请求失败`);
      });
  };

  React.useEffect(() => {
    getProducts();
  }, []);
  
  const addToTrolley = () => {
    const user = localStorage.getItem("user");
    if (user === null) {
      toast.error("请先登录");
      return;
    }
    const userInfo = JSON.parse(user);
    const account = userInfo.account;
    const password = userInfo.password;
    // 北京市朝阳区翻斗小区一栋一单元二楼
    let newDate = new Date();
    const order = {
      orderId: nanoid(),
      account: account,
      productNum: count,
      productId: product_id,
      productName: productValue.name,
      productPrice: productValue.price,
      address: `${cities}${secondCity}`,
      userAddress: document.getElementById("userAddress").value,
      time: newDate.toLocaleString(),
    };
    if (order.userAddress === "") {
      toast.error("请输入详细地址");
      return;
    }
    const orders = localStorage.getItem("orders");
    const ordersInfo = JSON.parse(orders);
    if (ordersInfo !== null) {
      for(let i = 0; i < ordersInfo.length; i++) {
        if(ordersInfo[i].productId === order.productId) {
          ordersInfo[i].productNum += order.productNum;
          localStorage.setItem("orders", JSON.stringify(ordersInfo));
          toast.success("添加成功");
          return;
        }
      }
    }
    if (orders === null) {
      localStorage.setItem("orders", JSON.stringify([order]));
    } else {
      ordersInfo.push(order);
      localStorage.setItem("orders", JSON.stringify(ordersInfo));
    }
    toast.success("添加购物车成功");
  }
  
  return (
    <Fragment>
      <Header></Header>
      {/* <!-- mid section --> */}
      <div className="mid-section">
        <div className="container">
          <Image width={300} height={300} src={productValue.iconUrl} />
          <Card
            bordered={false}
            style={{ display: "inline-block", width: 500 }}
          >
            <br />
            <br />
            <br />
            <p>商品名称: {productValue.name}</p>
            <p>价格：{productValue.price}</p>
            <p>总计：{productValue.price * count}</p>
            <b>购买数量：</b>
            <InputNumber
              defaultValue={count}
              onChange={onChange}
              className="purchaseNumber"
              min={1}
            />
            <br />
            <b>收货地址: </b>
            <Select
              defaultValue={provinceData[0]}
              style={{
                width: 120,
              }}
              onChange={handleProvinceChange}
            >
              {provinceData.map((province) => (
                <Option key={province}>{province}</Option>
              ))}
            </Select>
            <Select
              style={{
                width: 120,
                marginLeft: 10,
              }}
              value={secondCity}
              onChange={onSecondCityChange}
            >
              {cities.map((city) => (
                <Option key={city}>{city}</Option>
              ))}
            </Select>
            <br />
            <b>详细地址: </b>
            <Input style={{"width": 300, "marginTop": 10}} id="userAddress"></Input>
            <br />
            <Button
              type="primary"
              onClick={addToTrolley}
              style={{ marginTop: 10 }}
            >
              加入购物车
            </Button>
          </Card>
        </div>
      </div>
      <br />
      <br />
      <br />
      {/* <!-- mid section --> */}
      <Footer></Footer>
    </Fragment>
  );
}

const provinceData = ["Zhejiang", "Jiangsu"];
const cityData = {
  Zhejiang: ["Hangzhou", "Ningbo", "Wenzhou"],
  Jiangsu: ["Nanjing", "Suzhou", "Zhenjiang"],
};

// const provinceData = ["浙江", "江苏"];
// const cityData = {
//   Zhejiang: ["杭州", "宁波", "温州"],
//   Jiangsu: ["南京", "苏州", "镇江"],
// };

// const provinceData = [
//   "河北省",
//   "山西省",
//   "辽宁省",
//   "吉林省",
//   "黑龙江省",
//   "江苏省",
//   "浙江省",
//   "安徽省",
//   "福建省",
//   "江西省",
//   "山东省",
//   "河南省",
//   "湖北省",
//   "湖南省",
//   "广东省",
//   "海南省",
//   "四川省",
//   "贵州省",
//   "云南省",
//   "陕西省",
//   "甘肃省",
//   "青海省",
//   "台湾省",
// ];

// const citiesData = [
//   "石家庄",
//   "太原",
//   "沈阳",
//   "长春",
//   "哈尔滨",
//   "南京",
//   "杭州",
//   "合肥",
//   "福州",
//   "南昌",
//   "济南",
//   "郑州",
//   "武汉",
//   "长沙",
//   "广州",
//   "海口",
//   "成都",
//   "贵阳",
//   "昆明",
//   "西安",
//   "兰州",
//   "西宁",
//   "台北",
// ]