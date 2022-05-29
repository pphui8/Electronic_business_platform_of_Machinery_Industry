import React from 'react'

export default function Footer() {
  return (
    <footer>
      <div className="container">
        <div className="row">
          <div className="col-md-4">
            <div className="full">
              <div className="logo_footer">
                <a href="#">
                  <img
                    width="210"
                    src="https://tvax1.sinaimg.cn/large/006z6YU4ly1h2pcnyo5ibj30zk0810tl.jpg"
                    alt="#"
                  />
                </a>
              </div>
              <div className="information_f">
                <p>
                  <strong>地址:</strong> 中关村-北京-中国
                </p>
                <p>
                  <strong>TELEPHONE:</strong> +86 123 456 789
                </p>
                <p>
                  <strong>EMAIL:</strong> pphui8@gmail.com
                </p>
              </div>
            </div>
          </div>
          <div className="col-md-8">
            <div className="row">
              <div className="col-md-7">
                <div className="row">
                  <div className="col-md-6">
                    <div className="widget_menu">
                      <h3>菜单</h3>
                      <ul>
                        <li>
                          <a href="#">主页</a>
                        </li>
                        <li>
                          <a href="#">关于</a>
                        </li>
                        <li>
                          <a href="#">服务</a>
                        </li>
                        <li>
                          <a href="#">测试</a>
                        </li>
                        <li>
                          <a href="#">博客</a>
                        </li>
                        <li>
                          <a href="#">联系我们</a>
                        </li>
                      </ul>
                    </div>
                  </div>
                  <div className="col-md-6">
                    <div className="widget_menu">
                      <h3>账号</h3>
                      <ul>
                        <li>
                          <a href="#">账号</a>
                        </li>
                        <li>
                          <a href="#">登出</a>
                        </li>
                        <li>
                          <a href="#">登入</a>
                        </li>
                        <li>
                          <a href="#">购买</a>
                        </li>
                        <li>
                          <a href="#">获取支持</a>
                        </li>
                        <li>
                          <a href="#">客服</a>
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
              </div>
              <div className="col-md-5">
                <div className="widget_menu">
                  <h3>新特性</h3>
                  <div className="information_f">
                    <p>关注我们的官方微博或微信公众号获取更多资讯！</p>
                  </div>
                  <div className="form_sub">
                    <form>
                      <fieldset>
                        <div className="field">
                          <input
                            type="email"
                            placeholder="Enter Your Mail"
                            name="email"
                          />
                          <input type="submit" value="Subscribe" />
                        </div>
                      </fieldset>
                    </form>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </footer>
  );
}
