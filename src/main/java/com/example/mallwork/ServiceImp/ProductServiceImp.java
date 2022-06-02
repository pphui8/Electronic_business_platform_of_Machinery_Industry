package com.example.mallwork.ServiceImp;

import com.example.mallwork.Common.SverResponse;
import com.example.mallwork.Entity.Product;
import com.example.mallwork.Tools.PageBean;
import com.example.mallwork.Vo.ProductFloorVo;
import com.example.mallwork.Vo.ProductListVo;

import java.util.List;

public interface ProductServiceImp {
	/**
	 * 所有商品展示
	 * @param productId
	 * @param partId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public SverResponse<PageBean<Product>> findProduct(Integer productId, Integer partId, Integer pageNum, Integer pageSize);
	/**
	 * 门户查找热门商品
	 * @param num
	 * @return
	 */
	public SverResponse<List<Product>> findHotProducts(Integer num);
	/**
	 * 门户获得首页所有楼层资料
	 * @return
	 */
	public SverResponse<ProductFloorVo> findFloorProducts();
	/**
	 * 门户，根据商品编号获得商品信息
	 * @param productId
	 * @return
	 */
	public SverResponse<Product> findProductDetailForPortal(Integer productId);
	/**
	 * 搜索商品
	 * @param productTypeId
	 * @param partsId
	 * @param name
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public SverResponse<PageBean<ProductListVo>> findProductsForProtal(Integer productTypeId, Integer partsId,
																	   String name, int pageNum, int pageSize);
}
