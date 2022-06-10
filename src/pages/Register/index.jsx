import React, { Fragment } from "react";
import Header from "../../components/Header";
import Footer from "../../components/Footer";
import Productcard from "../../components/Productcard";

export default function Register() {
  return (
    <Fragment>
      <Header></Header>
      {/* <!-- inner page section --> */}
      <section className="inner_page_head">
        <div className="container_fuild">
          <div className="row">
            <div className="col-md-12">
              <div className="full">
                <h3>注册一个账号</h3>
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
                      type="account"
                      placeholder="请输入账号"
                      name="account"
                      required
                    />
                    <input
                      type="password"
                      placeholder="请输入密码"
                      name="password"
                      required
                    />
                    <input
                      type="password"
                      placeholder="请再次输入密码"
                      name="password"
                      required
                    />
                    <input type="submit" value="Submit" />
                  </fieldset>
                </form>
              </div>
            </div>
          </div>
        </div>
      </section>
      {/* <!-- end why section --> */}
      <Footer></Footer>
    </Fragment>
  );
}
