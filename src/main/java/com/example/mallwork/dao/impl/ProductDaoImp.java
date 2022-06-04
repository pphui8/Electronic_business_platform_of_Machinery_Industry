package com.example.mallwork.dao.impl;

import com.example.mallwork.dao.ProductDao;
import com.example.mallwork.Entity.Product;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository//���ݷ��ʲ�
public class ProductDaoImp implements ProductDao {
	/**
	 * ��ѯ������
	 */
	@Autowired //�Զ�����
	private QueryRunner queryRunner;
	private String alias = "id,name,product_id as productId,"
			+ "parts_id as partsId,icon_url as iconUrl,sub_images as"
			+ " subImages,detail,spec_param as specParam,price,stock"
			+ ",status,is_hot as hot,created,updated";
	@Override
	public Integer getTotalCount(Integer productId, Integer partsId) {
		// TODO Auto-generated method stub
		
		String sql = "select count(*) as num from commodity where 1=1";
		//��������
		List<Object> params=new ArrayList<>();
		
		if (productId!=null) {
			sql+=" and product_id = ?";
			params.add(productId);
		}
		if (partsId!=null) {
			sql+=" and parts_id = ?";
			params.add(partsId);
			}
		try {
			
			return queryRunner.query(sql, new ColumnListHandler<Long>("num"),params.toArray()).get(0).intValue();//��ȡ����list�ĵ�0��Ԫ�ز�ת��Ϊint��
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * ��ѯ������Ϣ
	 */
	@Override
	public List<Product> findProductsByInfo(Integer productId, Integer partsId, Integer startIndex,
											Integer pageSzie) {
		// TODO Auto-generated method stub
		String sql="select id,name,product_id as productId,parts_id as partsId,"
				+"icon_url as iconUrl,sub_images as subImages,detail,spec_param as specParam,"
				+"price,stock,status,is_hot as hot,"
				+"created,updated from commodity where 1=1";
		//��������
		List<Object> params=new ArrayList<>();
		
		if (productId!=null) {
			sql+=" and product_id = ?";
			params.add(productId);
		}
		if (partsId!=null) {
			sql+=" and parts_id = ?";
			params.add(partsId);
		}
		sql+=" limit ?,?";
		params.add(startIndex);
		params.add(pageSzie);
		try {
			return queryRunner.query(sql,new BeanListHandler<Product>(Product.class)
					,params.toArray());
			
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * ����id��ѯ��Ʒ��Ϣ
	 */
	@Override
	public Product findProductById(Integer id) {
		String sql="select "+ this.alias+" from commodity where id=?";
		try {
			return queryRunner.query(sql, new BeanHandler<Product>(Product.class),id);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * ������Ʒ��Ϣ
	 */
	@Override
	public int updateProduct(Product product) {
		String sql="update commodity set updated = ?";
		List<Object> params = new ArrayList<>();
		params.add(product.getupdated());
		if(!StringUtils.isEmpty(product.getName())) {
			sql +=",name = ?";
			params.add(product.getName());
		}
		if(product.getProductId()!=null) {
			sql +=",product_id=?";
			params.add(product.getProductId());
		}
		if(product.getPartsId()!=null) {
			sql +=",parts_id=?";
			params.add(product.getPartsId());
		}
		if(product.getPrice()!=null) {
			sql +=",price = ?";
			params.add(product.getPrice());
		}
		if(product.getStock()!=null) {
			sql +=",stock = ?";
			params.add(product.getStock());
		}
		if(!StringUtils.isEmpty(product.getIconUrl())) {
			sql +=",icon_url = ?";
			params.add(product.getIconUrl());
		}
		if(!StringUtils.isEmpty(product.getSubImages())) {
			sql +=",sub_images = ?";
			params.add(product.getSubImages());
		}
		if(product.getStatus()!=null) {
			sql +=",status = ?";
			params.add(product.getStatus());
		}
		if(!StringUtils.isEmpty(product.getDetail())) {
			sql +=",detail = ?";
			params.add(product.getDetail());
		}
		if(!StringUtils.isEmpty(product.getSpecParam())) {
			sql +=",spec_param = ?";
			params.add(product.getSpecParam());
		}
		if(product.getHot()!=null) {
			sql +=",is_hot = ?";
			params.add(product.getHot());
		}
		sql+= " where id = ?";
		params.add(product.getId());
		
		try {
			return queryRunner.update(sql,params.toArray());
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	/**
	 * ������ﳵ��¼
	 */
	@Override
	public int deleteCartProduct(Integer userid) {
		String sql="delete from action_carts where user_id=?";
		try {
			return queryRunner.update(sql,userid);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	/**
	 * ����������Ʒ
	 */
	@Override
	public List<Product> findHotProducts(Integer num) {
		String sql="select "+this.alias+" from commodity where is_hot=1 and status=2 "
				+ "order by updated,id desc";
		if(num !=null) {
			sql+=" limit 0,?";
		}
		try {
			if(num!=null) {
				return queryRunner.query(sql, new BeanListHandler<Product>(Product.class), num);
			}else {
				return queryRunner.query(sql, new BeanListHandler<Product>(Product.class));
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * ������Ʒ���Ͳ�ѯ��Ʒ��Ϣ
	 */
	@Override
	public List<Product> findProductsByProductCategory(int categoryId) {
		String sql = "select " +this.alias+ " from commodity where product_id = ? and status=2 order by updated desc";
		try {
			return queryRunner.query(sql, new BeanListHandler<Product>(Product.class), categoryId);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * ��ѯ�ܼ�¼��
	 */
	@Override
	public Integer getTotalCount(Product product) {
		String sql ="select count(id) as num from commodity where 1=1 ";
		List<Object> params = new ArrayList<>();
		if(product.getId()!=null) {
			sql+=" and id = ?";
			params.add(product.getId());
		}
		if(product.getName()!=null) {
			sql+=" and name like ?";
			params.add("%"+product.getName()+"%");
		}
		if(product.getStatus()!=null) {
			sql+=" and status = ?";
			params.add(product.getStatus());
		}
		if(product.getProductId()!=null) {
			sql+=" and product_id = ?";
			params.add(product.getProductId());
		}
		if(product.getPartsId()!=null) {
			sql+=" and parts_id = ?";
			params.add(product.getPartsId());
		}
		try {
			return queryRunner.query(sql, new ColumnListHandler<Long>("num"), params.toArray()).get(0).intValue();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * ����������ҳ��ѯ
	 */
	@Override
	public List<Product> findProducts(Product product, int startIndex, int pageSize) {
		String sql = "select " +this.alias+ " from commodity where 1=1";
		List<Object> params = new ArrayList<>();
		if(product.getId()!=null) {
			sql+=" and id = ?";
			params.add(product.getId());
		}
		if(product.getName()!=null) {
			sql+=" and name like ?";
			params.add("%"+product.getName()+"%");
		}
		if(product.getStatus()!=null) {
			sql+=" and status = ?";
			params.add(product.getStatus());
		}
		if(product.getProductId()!=null) {
			sql+=" and product_id = ?";
			params.add(product.getProductId());
		}
		if(product.getPartsId()!=null) {
			sql+=" and parts_id = ?";
			params.add(product.getPartsId());
		}
		sql+=" limit ?,?";
		params.add(startIndex);
		params.add(pageSize);
		try {
			return queryRunner.query(sql, new BeanListHandler<Product>(Product.class), params.toArray());
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}


}
