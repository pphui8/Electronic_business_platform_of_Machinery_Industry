import React, { Fragment } from "react";
import { Toaster } from "react-hot-toast";
import { Routes, Route, Navigate } from "react-router-dom";

import Firstpage from "./pages/Firstpage";
import Products from "./pages/Products";
import Register from "./pages/Register";
import Trolley from "./pages/Trolley";
import ProductAbout from "./pages/ProductAbout";
import Pay from "./pages/Pay";
import FindAccount from "./pages/FindAccount";
import AboutMe from "./pages/AboutMe";
import UserData from "./pages/AboutMe/UserData";
import Orders from "./pages/AboutMe/Orders";
import SetUserData from "./pages/AboutMe/SetUserData";
import Manage from "./pages/Manage";
import ManageOrder from './pages/Manage/Order'
import ManageProducts from "./pages/Manage/Products";
import ManageUser from "./pages/Manage/User";
import Success from "./pages/Success";
 
export default function App() {
  return (
    <Fragment>
      <Toaster />
      <Routes>
        <Route path="/" element={<Firstpage />}></Route>
        <Route path="/products" element={<Products />}></Route>
        <Route path="/aboutme" element={<AboutMe />}>
          <Route path="/aboutme/userdata" element={<UserData />}></Route>
          <Route path="/aboutme/orders" element={<Orders />}></Route>
          <Route path="/aboutme/setuserdata" element={<SetUserData />}></Route>
          <Route
            path="/aboutme/*"
            element={<Navigate to="/aboutme/userdata" replace />}
          ></Route>
        </Route>
        <Route path="/register" element={<Register />}></Route>
        <Route path="/trolley" element={<Trolley />}></Route>
        <Route
          path="/productAbout/:product_id"
          element={<ProductAbout />}
        ></Route>
        <Route path="/pay/:sumPrice" element={<Pay />}></Route>
        <Route path="/findAccount" element={<FindAccount />}></Route>
        <Route path="/manage" element={<Manage />}>
          <Route path="/manage/order" element={<ManageOrder />}></Route>
          <Route path="/manage/products" element={<ManageProducts />}></Route>
          <Route path="/manage/user" element={<ManageUser />}></Route>
        </Route>
        <Route path="/success" element={<Success />}></Route>
        <Route path="*" element={<Navigate to="/" />}></Route>
      </Routes>
    </Fragment>
  );
}