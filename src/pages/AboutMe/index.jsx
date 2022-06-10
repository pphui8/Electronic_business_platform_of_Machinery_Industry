import React, { useEffect, useState } from "react";
import MyHeader from "../../components/Header";
import MyFooter from "../../components/Footer";
import Login from "./Login";
import UserData from "./UserData";

import {
  DesktopOutlined,
  FileOutlined,
  PieChartOutlined,
  TeamOutlined,
  UserOutlined,
  CopyOutlined
} from "@ant-design/icons";
import { Breadcrumb, Layout, Menu } from "antd";
import { Outlet, useNavigate } from "react-router-dom";
const { Header, Content, Footer, Sider } = Layout;

function getItem(label, key, icon, children) {
  return {
    key,
    icon,
    children,
    label,
  };
}

const items = [
  getItem("用户信息", "userdata", <PieChartOutlined />),
  getItem("我的订单", "orders", <DesktopOutlined />),
  getItem("用户设置", "setuserdata", <CopyOutlined />),
];

export default function AboutMe() {
  const [collapsed, setCollapsed] = useState(false);
  const userData = localStorage.getItem("user");
  const navigate = useNavigate();
  const url = window.location.href;
  
  useEffect(() => {
    if(url.endsWith("/aboutme")) {
      navigate("/aboutme/userdata");
    }
  }, [url]);
  
  // const userData = null;
  if (userData) {
    return (
      <div>
        <MyHeader />
        <div className="container">
          <Layout
            style={{
              minHeight: "100vh",
            }}
          >
            <Sider
              collapsible
              collapsed={collapsed}
              onCollapse={(value) => setCollapsed(value)}
              trigger={null}
            >
              <div className="logo" />
              <Menu
                theme="dark"
                defaultSelectedKeys={["1"]}
                mode="inline"
                items={items}
                onClick={(e) => {
                  switch (e.key) {
                    case "userdata": navigate(`/aboutme/userData`); break;
                    case "orders": navigate(`/aboutme/orders`); break;
                    case "setuserdata": navigate(`/aboutme/setUserData`); break;
                }}}
              />
            </Sider>
            <Layout className="site-layout">
              <Header
                className="site-layout-background"
                style={{
                  padding: 0,
                }}
              />
              <Content
                style={{
                  margin: "0 16px",
                }}
              >
                <div
                  className="site-layout-background"
                  style={{
                    padding: 24,
                    minHeight: 360,
                  }}
                >
                  <Outlet />
                </div>
              </Content>
              <Footer
                style={{
                  textAlign: "center",
                }}
              >
              </Footer>
            </Layout>
          </Layout>
        </div>
        <MyFooter />
      </div>
    );
  } else {
    return <Login />;
  }
}
