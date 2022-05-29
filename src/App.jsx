import React, { Fragment } from "react";
import { Toaster } from "react-hot-toast";
import { Routes, Route, Navigate } from "react-router-dom";

import Firstpage from "./pages/Firstpage";
import Products from "./pages/Products";
import Login from "./pages/Login";

export default function App() {
  return (
    <Fragment>
      <Toaster />
      <Routes>
        <Route path="/" element={<Firstpage />}></Route>
        <Route path="/products" element={<Products />}></Route>
        <Route path="/login" element={<Login />}></Route>
        <Route path="*" element={<Navigate to="/" />}></Route>
      </Routes>
    </Fragment>
  );
}
