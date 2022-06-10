import React, { Fragment, useEffect } from "react";
import Header from "../../../components/Header";
import Footer from "../../../components/Footer";
import { Button } from "antd";
import toast from "react-hot-toast";
import "./index.css";
import { Link, useNavigate } from "react-router-dom";

export default function Login() {
  const navgate = useNavigate();
  const myLogin = () => {
    let account = document.getElementById("account").value;
    let password = document.getElementById("password").value;
    if(account === "" || password === ""){
      toast.error("账号或密码不能为空");
    }
    fetch(
      "http://192.168.211.156:8080/mall/user/do_login.do?account=" +
        account.trim() +
        "&password=" +
        password.trim()
    )
      .then((res) => res.json())
      .then((data) => {
        console.log(data);
        if (data.status === 0) {
          toast.success("登录成功");
          let tmp = data.data;
          tmp.password = password;
          localStorage.setItem("user", JSON.stringify(tmp));
          navgate(`/aboutme/userdata`);
        } else {
          toast.error(data.msg);
        }
      })
      .catch((err) => toast.error(`请求失败`));
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
                <h3>登录</h3>
              </div>
            </div>
          </div>
        </div>
      </section>
      {/* <!-- end inner page section --> */}
      {/* <!-- why section --> */}
      <section className="why_section layout_padding">
        <div className="container">
          <div className="row">
            <div className="col-lg-8 offset-lg-2">
              <div className="full">
                <form action="index.html">
                  <fieldset>
                    <input
                      type="text"
                      id="account"
                      placeholder="请输入账号"
                      name="account"
                      required
                      style={{ "text-transform": "lowercase" }}
                    />
                    <input
                      type="password"
                      id="password"
                      placeholder="请输入密码"
                      name="password"
                      required
                      style={{ "text-transform": "lowercase" }}
                    />
                    <a onClick={myLogin} className="submit">
                      提交
                    </a>
                    <Link to="/register" className="register_btn">
                      没有账号？注册一个
                    </Link>
                    <Link
                      to="/findAccount"
                      className="register_btn"
                      style={{ marginLeft: "40px" }}
                    >
                      忘记密码
                    </Link>
                  </fieldset>
                </form>
              </div>
            </div>
          </div>
        </div>
      </section>
      {/* <!-- end why section --> */}
      <Footer />
    </Fragment>
  );
}
