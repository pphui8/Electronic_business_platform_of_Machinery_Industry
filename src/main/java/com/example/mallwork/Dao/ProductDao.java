package com.example.mallwork.Dao;

import com.example.mallwork.Entity.Product;

import java.util.List;

public interface ProductDao {
	/**
	 * 根据条件查询商品的数量
	 * @param productId
	 * @param partsId
	 * @return
	 */
	public Integer getTotalCount(Integer productId, Integer partsId);

	/**
	 * 根据条件插入商品信息
	 * @param productId
	 * @param partsId
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<Product> findProductsByInfo(Integer productId, Integer partsId, Integer startIndex, Integer pageSize);

	/**
	 * 根据商品Id查询商品信息
	 * @param id
	 * @return
	 */
	public Product findProductById(Integer id);
	/**
	 * 更新商品信息
	 * @param product
	 */
	public int updateProduct(Product product);
	/**
	 * 删除某个用户购物车所有商品
	 * @param userid
	 */
	public int deleteCartProduct(Integer userid);
	/**
	 * 查找热门商品
	 * @param num
	 * @return
	 */
	public List<Product> findHotProducts(Integer num);
	/**
	 * 根据商品类型查询商品信息
	 * @param  categoryId
	 * @return
	 */
	public List<Product> findProductsByProductCategory(int  categoryId);
	/**
	 * 根据条件获得总记录数
	 * @param product
	 * @return
	 */
	public Integer getTotalCount(Product product);
	/**
	 * 根据条件分页查询
	 * @param product
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<Product> findProducts(Product product, int startIndex, int pageSize);
}
