import React from 'react'
import { Link } from 'react-router-dom';
import "./index.css";

export default function index(props) {
  const product_name = props.product_name;
  const photo_url = props.photo_url;
  const price = props.price;
  const product_id = props.product_id;
  return (
    <div className="col-sm-6 col-md-4 col-lg-3">
      <div className="box">
        <div className="option_container">
          <div className="options">
            <Link to={`/productAbout/${product_id}`} className="link">
              现在购买
            </Link>
          </div>
        </div>
        <div className="img-box">
          <img src={photo_url} alt="#" />
        </div>
        <div className="detail-box">
          <h5>{product_name}</h5>
          <h6>￥{price}</h6>
        </div>
      </div>
    </div>
  );
}