import React, { Fragment } from "react";
import Header from "../../components/Header";
import Footer from "../../components/Footer";

export default function Login() {
  return (
    <Fragment>
      <Header />
      {/* <!-- inner page section --> */}
      <section class="inner_page_head">
        <div class="container_fuild">
          <div class="row">
            <div class="col-md-12">
              <div class="full">
                <h3>登录</h3>
              </div>
            </div>
          </div>
        </div>
      </section>
      {/* <!-- end inner page section --> */}
      {/* <!-- why section --> */}
      <section class="why_section layout_padding">
         <div class="container">
            <div class="row">
               <div class="col-lg-8 offset-lg-2">
                  <div class="full">
                     <form action="index.html">
                        <fieldset>
                           <input type="text" placeholder="Enter your full name" name="name" required />
                           <input type="email" placeholder="Enter your email address" name="email" required />
                           <input type="text" placeholder="Enter subject" name="subject" required />
                           <textarea placeholder="Enter your message" required></textarea>
                           <input type="submit" value="Submit" />
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
