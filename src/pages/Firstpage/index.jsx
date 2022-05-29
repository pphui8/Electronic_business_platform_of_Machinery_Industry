import React, { Fragment } from 'react'
import Header from "../../components/Header";
import Footer from "../../components/Footer";
import './index.css'

export default function Firstpage() {
  return (
    <Fragment>
      <div className="hero_area">
        {/* <!-- header section strats --> */}
        <Header />
        {/* <!-- end header section --> */}
        {/* <!-- slider section --> */}
        <section className="slider_section ">
          <div className="slider_bg_box">
            <img
              src="https://tva2.sinaimg.cn/large/006z6YU4ly1h2p2y6sqiaj34n433k1l1.jpg"
              alt="#"
            ></img>
          </div>
          <div
            id="customCarousel1"
            className="carousel slide"
            data-ride="carousel"
          >
            <div className="carousel-inner">
              <h1>斯塔克工业</h1>
              <h2>您的工业级购物专家</h2>
            </div>
          </div>
        </section>
        {/* <!-- end slider section --> */}
      </div>
      {/* <!-- why section --> */}
      <section className="why_section layout_padding">
        <div className="container">
          <div className="heading_container heading_center">
            <h2>为什么选择我们</h2>
          </div>
          <div className="row">
            <div className="col-md-4">
              <div className="box ">
                <div className="detail-box">
                  <h5>超快送货</h5>
                  <p>
                    斯塔克工业在在全国拥有超过404个临时仓库，并与京东、顺丰等知名快递业合作，保证物流速度
                  </p>
                </div>
              </div>
            </div>
            <div className="col-md-4">
              <div className="box ">
                <div className="detail-box">
                  <h5>支持定制</h5>
                  <p>
                    您可以在斯塔克工业定制工业物件，并与设计师进行一对一沟通，下单后我们将立即开始制作，满足您的需求
                  </p>
                </div>
              </div>
            </div>
            <div className="col-md-4">
              <div className="box ">
                <div className="detail-box">
                  <h5>最好的质量</h5>
                  <p>
                    通过与厂家的直接合作，一对一式的订单与厂商信息流通，层层物流信息监控，保证质量上乘的物件按时送达
                  </p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
      {/* <!-- end why section --> */}
      {/* <!-- arrival section --> */}
      <section className="arrival_section">
        <div className="container">
          <div className="box">
            <div className="arrival_bg_box">
              <img
                src="https://tva3.sinaimg.cn/large/006z6YU4ly1h2p3za8k21j30d608r0t3.jpg"
                alt=""
              ></img>
            </div>
            <div className="row">
              <div className="col-md-6 ml-auto">
                <div className="heading_container remove_line_bt">
                  <h2>质量看得见</h2>
                </div>
                <p>
                  斯塔克工业为您提供全方位的质量保障，您可以通过斯塔克工业的定制服务，让您的工业物件达到更高的质量标准
                </p>
              </div>
            </div>
          </div>
        </div>
      </section>
      {/* <!-- end arrival section --> */}
      {/* <!-- product section --> */}
      <section className="product_section layout_padding">
        <div className="container">
          <div className="heading_container heading_center">
            <h2>
              <span>产品详情</span>
            </h2>
          </div>
          <div className="row">
            <div className="col-sm-6 col-md-4 col-lg-4">
              <div className="box">
                <div className="option_container">
                  <div className="options">
                    <a href="" className="option1">
                      Men's Shirt
                    </a>
                    <a href="" className="option2">
                      Buy Now
                    </a>
                  </div>
                </div>
                <div className="img-box">
                  <img src="images/p1.png" alt=""></img>
                </div>
                <div className="detail-box">
                  <h5>Men's Shirt</h5>
                  <h6>$75</h6>
                </div>
              </div>
            </div>
            <div className="col-sm-6 col-md-4 col-lg-4">
              <div className="box">
                <div className="option_container">
                  <div className="options">
                    <a href="" className="option1">
                      Add To Cart
                    </a>
                    <a href="" className="option2">
                      Buy Now
                    </a>
                  </div>
                </div>
                <div className="img-box">
                  <img src="images/p2.png" alt=""></img>
                </div>
                <div className="detail-box">
                  <h5>Men's Shirt</h5>
                  <h6>$80</h6>
                </div>
              </div>
            </div>
            <div className="col-sm-6 col-md-4 col-lg-4">
              <div className="box">
                <div className="option_container">
                  <div className="options">
                    <a href="" className="option1">
                      Add To Cart
                    </a>
                    <a href="" className="option2">
                      Buy Now
                    </a>
                  </div>
                </div>
                <div className="img-box">
                  <img src="images/p3.png" alt=""></img>
                </div>
                <div className="detail-box">
                  <h5>Women's Dress</h5>
                  <h6>$68</h6>
                </div>
              </div>
            </div>
            <div className="col-sm-6 col-md-4 col-lg-4">
              <div className="box">
                <div className="option_container">
                  <div className="options">
                    <a href="" className="option1">
                      Add To Cart
                    </a>
                    <a href="" className="option2">
                      Buy Now
                    </a>
                  </div>
                </div>
                <div className="img-box">
                  <img src="images/p4.png" alt=""></img>
                </div>
                <div className="detail-box">
                  <h5>Women's Dress</h5>
                  <h6>$70</h6>
                </div>
              </div>
            </div>
            <div className="col-sm-6 col-md-4 col-lg-4">
              <div className="box">
                <div className="option_container">
                  <div className="options">
                    <a href="" className="option1">
                      Add To Cart
                    </a>
                    <a href="" className="option2">
                      Buy Now
                    </a>
                  </div>
                </div>
                <div className="img-box">
                  <img src="images/p5.png" alt=""></img>
                </div>
                <div className="detail-box">
                  <h5>Women's Dress</h5>
                  <h6>$75</h6>
                </div>
              </div>
            </div>
            <div className="col-sm-6 col-md-4 col-lg-4">
              <div className="box">
                <div className="option_container">
                  <div className="options">
                    <a href="" className="option1">
                      Add To Cart
                    </a>
                    <a href="" className="option2">
                      Buy Now
                    </a>
                  </div>
                </div>
                <div className="img-box">
                  <img src="images/p6.png" alt=""></img>
                </div>
                <div className="detail-box">
                  <h5>Women's Dress</h5>
                  <h6>$58</h6>
                </div>
              </div>
            </div>
            <div className="col-sm-6 col-md-4 col-lg-4">
              <div className="box">
                <div className="option_container">
                  <div className="options">
                    <a href="" className="option1">
                      Add To Cart
                    </a>
                    <a href="" className="option2">
                      Buy Now
                    </a>
                  </div>
                </div>
                <div className="img-box">
                  <img src="images/p7.png" alt=""></img>
                </div>
                <div className="detail-box">
                  <h5>Women's Dress</h5>
                  <h6>$80</h6>
                </div>
              </div>
            </div>
            <div className="col-sm-6 col-md-4 col-lg-4">
              <div className="box">
                <div className="option_container">
                  <div className="options">
                    <a href="" className="option1">
                      Add To Cart
                    </a>
                    <a href="" className="option2">
                      Buy Now
                    </a>
                  </div>
                </div>
                <div className="img-box">
                  <img src="images/p8.png" alt=""></img>
                </div>
                <div className="detail-box">
                  <h5>Men's Shirt</h5>
                  <h6>$65</h6>
                </div>
              </div>
            </div>
            <div className="col-sm-6 col-md-4 col-lg-4">
              <div className="box">
                <div className="option_container">
                  <div className="options">
                    <a href="" className="option1">
                      Add To Cart
                    </a>
                    <a href="" className="option2">
                      Buy Now
                    </a>
                  </div>
                </div>
                <div className="img-box">
                  <img src="images/p9.png" alt=""></img>
                </div>
                <div className="detail-box">
                  <h5>Men's Shirt</h5>
                  <h6>$65</h6>
                </div>
              </div>
            </div>
            <div className="col-sm-6 col-md-4 col-lg-4">
              <div className="box">
                <div className="option_container">
                  <div className="options">
                    <a href="" className="option1">
                      Add To Cart
                    </a>
                    <a href="" className="option2">
                      Buy Now
                    </a>
                  </div>
                </div>
                <div className="img-box">
                  <img src="images/p10.png" alt=""></img>
                </div>
                <div className="detail-box">
                  <h5>Men's Shirt</h5>
                  <h6>$65</h6>
                </div>
              </div>
            </div>
            <div className="col-sm-6 col-md-4 col-lg-4">
              <div className="box">
                <div className="option_container">
                  <div className="options">
                    <a href="" className="option1">
                      Add To Cart
                    </a>
                    <a href="" className="option2">
                      Buy Now
                    </a>
                  </div>
                </div>
                <div className="img-box">
                  <img src="images/p11.png" alt=""></img>
                </div>
                <div className="detail-box">
                  <h5>Men's Shirt</h5>
                  <h6>$65</h6>
                </div>
              </div>
            </div>
            <div className="col-sm-6 col-md-4 col-lg-4">
              <div className="box">
                <div className="option_container">
                  <div className="options">
                    <a href="" className="option1">
                      Add To Cart
                    </a>
                    <a href="" className="option2">
                      Buy Now
                    </a>
                  </div>
                </div>
                <div className="img-box">
                  <img src="images/p12.png" alt=""></img>
                </div>
                <div className="detail-box">
                  <h5>Women's Dress</h5>
                  <h6>$65</h6>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
      {/* <!-- end product section --> */}
      {/* <!-- subscribe section --> */}
      <section className="subscribe_section">
        <div className="container-fuild">
          <div className="box">
            <div className="row">
              <div className="col-md-6 offset-md-3">
                <div className="subscribe_form ">
                  <div className="heading_container heading_center">
                    <h3>登录获取账号支持</h3>
                  </div>
                  <p>
                    您的支持是我们最大的动力，我们将竭诚为您提供最优质的服务。
                  </p>
                  <form action="">
                    <input type="email" placeholder="请输入邮箱"></input>
                    <button>登录</button>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
      {/* <!-- end subscribe section --> */}
      {/* <!-- footer start --> */}
      <Footer></Footer>
      {/* <!-- footer end --> */}
    </Fragment>
  );
}
