import React from 'react'
import { Link } from "react-router-dom";
import './index.css'

export default function Header() {
  return (
    <header className="header_section">
      <div className="container">
        <nav className="navbar navbar-expand-lg custom_nav-container ">
          <a className="navbar-brand" href="/">
            <img
              width="250"
              src="https://tvax1.sinaimg.cn/large/006z6YU4ly1h2pcltxdotj30zk081wfd.jpg"
              alt="#"
            />
          </a>
          <button
            className="navbar-toggler"
            type="button"
            data-toggle="collapse"
            data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span className=""> </span>
          </button>
          <div className="collapse navbar-collapse" id="navbarSupportedContent">
            <ul className="navbar-nav">
              <li className="nav-item active">
                <Link to="/" className="nav_link">
                  主页
                </Link>
              </li>
              <li className="nav-item">
                <Link to="/products" className="nav_link">
                  购买
                </Link>
              </li>
              <li className="nav-item">
                <Link to="/aboutme" className="nav_link">
                  用户
                </Link>
              </li>
              <li className="nav-item">
                <Link to="/trolley" className="nav_link">
                  购物车
                </Link>
              </li>
              {/* <li className="nav-item dropdown">
                <a
                  className="nav-link dropdown-toggle"
                  href="#"
                  data-toggle="dropdown"
                  role="button"
                  aria-haspopup="true"
                  aria-expanded="true"
                >
                  <span className="nav-label"></span>Pages{" "}
                  <span className="caret"></span>
                </a>
                <ul className="dropdown-menu">
                  <li>
                    <a href="about.html">About</a>
                  </li>
                  <li>
                    <a href="testimonial.html">Testimonial</a>
                  </li>
                </ul>
              </li> */}
            </ul>
          </div>
        </nav>
      </div>
    </header>
  );
}
