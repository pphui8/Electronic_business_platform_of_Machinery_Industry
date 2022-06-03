package com.example.mallwork.dao;

import com.example.mallwork.Entity.Product;

import java.util.List;

public interface ProductDao {
	/**
	 * ����������ѯ��Ʒ������
	 * @param productId
	 * @param partsId
	 * @return
	 */
	public Integer getTotalCount(Integer productId, Integer partsId);

	/**
	 * ��������������Ʒ��Ϣ
	 * @param productId
	 * @param partsId
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<Product> findProductsByInfo(Integer productId, Integer partsId, Integer startIndex, Integer pageSize);

	/**
	 * ������ƷId��ѯ��Ʒ��Ϣ
	 * @param id
	 * @return
	 */
	public Product findProductById(Integer id);
	/**
	 * ������Ʒ��Ϣ
	 * @param product
	 */
	public int updateProduct(Product product);
	/**
	 * ɾ��ĳ���û����ﳵ������Ʒ
	 * @param userid
	 */
	public int deleteCartProduct(Integer userid);
	/**
	 * ����������Ʒ
	 * @param num
	 * @return
	 */
	public List<Product> findHotProducts(Integer num);
	/**
	 * ������Ʒ���Ͳ�ѯ��Ʒ��Ϣ
	 * @param  categoryId
	 * @return
	 */
	public List<Product> findProductsByProductCategory(int  categoryId);
	/**
	 * ������������ܼ�¼��
	 * @param product
	 * @return
	 */
	public Integer getTotalCount(Product product);
	/**
	 * ����������ҳ��ѯ
	 * @param product
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<Product> findProducts(Product product, int startIndex, int pageSize);
}
