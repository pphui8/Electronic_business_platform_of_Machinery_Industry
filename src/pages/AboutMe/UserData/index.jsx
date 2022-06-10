import React, { Fragment, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { Space, Typography, Avatar, Button } from "antd";
const { Title, Link } = Typography;

export default function UserData() {
  const [userData, setUserData] = useState({});
  const navgate = useNavigate();
  const getData = () => {
    const user = localStorage.getItem("user");
    if (user) {
      // console.log(JSON.parse(user));
      setUserData(JSON.parse(user));
    }
  };

  useEffect(() => {
    getData();
  }, []);

  const logOut = () => {
    localStorage.removeItem("user");
    navgate(`/`);
  };
  
  return (
    <Fragment>
      <Space direction="vertical">
        <Avatar size={64} icon="user" />
        <br />
        <Title level={3}>用户名: {userData.account}</Title>
        <Title level={3}>邮箱: {userData.email}</Title>
        <Title level={3}>手机号: {userData.phone}</Title>
        <Title level={3}>年龄: {userData.age}</Title>
        <Button type="primary" onClick={logOut}>
          登出
        </Button>
      </Space>
    </Fragment>
  );
}
