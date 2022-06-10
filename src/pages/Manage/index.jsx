import { Button, Drawer, Radio, Space, Input, Layout, Menu  } from "antd";
import React, { Fragment, useEffect, useState } from "react";
import {
  MenuFoldOutlined,
  MenuUnfoldOutlined,
  UploadOutlined,
  UserOutlined,
  VideoCameraOutlined,
} from "@ant-design/icons";
import "antd/dist/antd.css";
import { Outlet, useNavigate } from "react-router-dom";
import Order from "./Order";
import Products from "./Products";
import User from "./User";

const { Header, Sider, Content } = Layout;

export default function Manage() {
   const [visible, setVisible] = useState(false);
   const [collapsed, setCollapsed] = useState(false);
   const navigate = useNavigate();

   const onClose = () => {
     setVisible(false);
   };

   useEffect(() => {
    setVisible(true);
   }, []);

   return (
     <Fragment>
       <Drawer
         title="请输入管理员密码"
         placement="top"
         closable={false}
         onClose={onClose}
         visible={visible}
         key="top"
         maskClosable={false}
       >
         <Input></Input>
         <Button type="primary" style={{ marginTop: "30px" }} onClick={onClose}>
           确定
         </Button>
       </Drawer>
       <Layout style={{ height: "100vh" }}>
         <Sider trigger={null} collapsible collapsed={collapsed}>
           <div className="logo" />
           <Menu
             theme="dark"
             mode="inline"
             defaultSelectedKeys={["1"]}
             items={[
               {
                 key: "user",
                 icon: <UserOutlined />,
                 label: "用户管理",
               },
               {
                 key: "products",
                 icon: <VideoCameraOutlined />,
                 label: "商品管理",
               },
               {
                 key: "orders",
                 icon: <UploadOutlined />,
                 label: "订单管理",
               },
             ]}
             onClick={(e) => {
               switch (e.key) {
                 case "user":
                   navigate(`/manage/user`);
                   break;
                 case "products":
                   navigate(`/manage/products`);
                   break;
                 case "orders":
                   navigate(`/manage/order`);
                   break;
               }
             }}
           />
         </Sider>
         <Layout className="site-layout">
           <Header className="site-layout-background" style={{ padding: 0 }}>
             {React.createElement(
               collapsed ? MenuUnfoldOutlined : MenuFoldOutlined,
               {
                 className: "trigger",
                 onClick: () => setCollapsed(!collapsed),
               }
             )}
           </Header>
           <Content
             className="site-layout-background"
             style={{
               margin: "24px 16px",
               padding: 24,
               minHeight: 280,
             }}
           >
             <Outlet />
           </Content>
         </Layout>
       </Layout>
     </Fragment>
   );
}
