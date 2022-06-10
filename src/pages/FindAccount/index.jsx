import React, { Fragment, useEffect } from "react";
import Header from "../../components/Header";
import Footer from "../../components/Footer";
import { Button } from "antd";
import toast from "react-hot-toast";
import { Link } from "react-router-dom";
import './index.css';

export default function FindAccount() {
  const configCodes = {
    1: "https://tvax4.sinaimg.cn/large/006z6YU4ly1h2zv33ma26j302v00z3yh.jpg",
    2: "https://tvax1.sinaimg.cn/large/006z6YU4ly1h2zutm25tqj30bl05vdfq.jpg",
  };
  const [curConfigCode, setCurConfigCode] = React.useState(1);

  const myConfirm = () => {
    const account = document.getElementById("account").value;
    const email = document.getElementById("email").value;
    const data = JSON.parse(localStorage.getItem("user"));
    if (account !== data.account) {
      toast.error("未找到该用户");
      return;
    }
    if (email !== data.email) {
      toast.error("邮箱错误！！");
      return;
    }
    if (account === data.account && email === data.email) {
      toast.success("找回成功，您的密码是：" + data.password);
    }
  };
  
  return (
    <Fragment>
      <Header />
      {/* <!-- inner page section --> */}
      <section className="inner_page_head">
        <div className="container_fuild">
          <div className="row">
            <div className="col-md-12">
              <div className="full">
                <h3>找回密码</h3>
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
                    />
                    <input
                      type="password"
                      id="email"
                      placeholder="请输入邮箱"
                      name="password"
                      required
                    />
                    <div>
                      <input
                        type="text"
                        id="configCode"
                        placeholder="请输入验证码"
                        name="password"
                        required
                        style={{ display: "inline-block", width: "70%" }}
                      />
                      <img
                        src={configCodes[curConfigCode]}
                        alt="#"
                        width={100}
                        height={40}
                        className="captcha"
                        onClick={() => {
                          if (curConfigCode === 1) {
                            setCurConfigCode(2);
                          } else {
                            setCurConfigCode(1);
                          }
                        }}
                      />
                    </div>
                    <div>
                      <input
                        type="password"
                        id="password"
                        placeholder="请输入邮箱收到的验证码"
                        name="password"
                        required
                        style={{ display: "inline-block", width: "70%" }}
                      />
                      <div className="mysubmit">获取验证码</div>
                    </div>
                    <a className="submit" onClick={myConfirm}>提交</a>
                    <a href="#" className="register_btn">
                      没有收到邮件？
                    </a>
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
