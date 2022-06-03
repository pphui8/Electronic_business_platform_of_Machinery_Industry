package com.example.mallwork.Service.impl;

import com.example.mallwork.Common.SverResponse;
import com.example.mallwork.Entity.Product;
import com.example.mallwork.Service.ProductService;
import com.example.mallwork.Tools.ConstUtil;
import com.example.mallwork.Tools.PageBean;
import com.example.mallwork.Vo.ProductFloorVo;
import com.example.mallwork.Vo.ProductListVo;
import com.example.mallwork.dao.ParamDao;
import com.example.mallwork.dao.ProductDao;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {
	@Autowired
	private ProductDao ProductDao;
	@Autowired
	private ParamDao actionParamDao;
	//查询方法
	@Override
	public SverResponse<PageBean<Product>> findProduct(Integer productId, Integer partsId, Integer pageNum,
													   Integer pageSize) {
		// TODO Auto-generated method stub
		//1.先要根据条件获得查询商品的总条数，调用Dao层获取数据库
		int totalCount=ProductDao.getTotalCount(productId, partsId);
		PageBean<Product> pageBean = new PageBean<>(pageNum, pageSize, totalCount);
		//2.调用Dao层获取分页查询的商品信息
		pageBean.setData(ProductDao.findProductsByInfo(productId, partsId, pageNum, pageSize));
		return SverResponse.createRespBySuccess(pageBean);
	}
	/**
	 *查找热门商品
	 */
	@Override
	public SverResponse<List<Product>> findHotProducts(Integer num) {
		//直接查询所需数据
		List<Product> products = ProductDao.findHotProducts(num);
		return SverResponse.createRespBySuccess(products);
	}
	/**
	 * 查找楼层数据
	 */
	@Override
	public SverResponse<ProductFloorVo> findFloorProducts() {
		//创建vo对象
		ProductFloorVo vo = new ProductFloorVo();
		//1楼数据
		List<Product> products1 = ProductDao.findProductsByProductCategory(ConstUtil.ProductType.TYPE_HNTJX);
		vo.setOneFloor(products1);
		//2楼数据
		List<Product> products2 = ProductDao.findProductsByProductCategory(ConstUtil.ProductType.TYPE_JZQZJJX);
		vo.setTwoFloor(products2);
		//3楼数据
		List<Product> products3 = ProductDao.findProductsByProductCategory(ConstUtil.ProductType.TYPE_GCQZJJX);
		vo.setThreeFloor(products3);
		//4楼数据
		List<Product> products4 = ProductDao.findProductsByProductCategory(ConstUtil.ProductType.TYPE_LMJX);
		vo.setFourFloor(products4);
		return SverResponse.createRespBySuccess(vo);
	}
	/**
	 * 门户，根据商品编号获得商品信息
	 */
	@Override
	public SverResponse<Product> findProductDetailForPortal(Integer productId) {
		//判断产品编号是否为空
		if(productId ==null) {
			return SverResponse.createByErrorMessage("产品编号不能为空");
		}
		//查询商品详情
		Product product = ProductDao.findProductById(productId);
		//判断产品是否下架
		if(product==null) {
			return SverResponse.createByErrorMessage("产品已经下架！");
		}
		if(product.getStatus()==ConstUtil.ProductStatus.STATUS_OFF_SALE) {
			return SverResponse.createByErrorMessage("产品已经下架！");
		}
		return SverResponse.createRespBySuccess(product);
	}
	/**
	 * 查询商品信息
	 */
	public SverResponse<PageBean<ProductListVo>> findProductsForProtal(Integer productTypeId, Integer partsId,
																	   String name, int pageNum, int pageSize) {
		//创建对象
		Product product = new Product();
		int totalRecord = 0;
		//判断name是否为空
		if(name !=null && !name.equals("")) {
			product.setName(name);
		}
		if(productTypeId!=0) {
			product.setProductId(productTypeId);
		}
		if(partsId!=0) {
			product.setPartsId(partsId);
		}
		//前端显示商品都为在售
		product.setStatus(2);
		//查找符合条件的总记录数
		totalRecord = ProductDao.getTotalCount(product);
		//创建分页对象
		PageBean<ProductListVo> pageBean = new PageBean<>(pageNum, pageSize, totalRecord);
		//读取数据 
		List<Product> products = ProductDao.findProducts(product,pageBean.getStartIndex(),pageSize);
		//封装vo
		List<ProductListVo> voList = Lists.newArrayList();
		for(Product p:products) {
			voList.add(createProductListVo(p));
		}
		pageBean.setData(voList);
		return SverResponse.createRespBySuccess(pageBean);
	}
	
	//封装vo对象
	private ProductListVo createProductListVo(Product product) {
		ProductListVo vo = new ProductListVo();
		vo.setId(product.getId());
		vo.setName(product.getName());
		vo.setPartsCategory(actionParamDao.findParamById(product.getPartsId()).getName());
		vo.setProductCategory(actionParamDao.findParamById(product.getProductId()).getName());
		vo.setPrice(product.getPrice());
		vo.setStatus(product.getStatus());
		vo.setIconUrl(product.getIconUrl());
		vo.setStatusDesc(ConstUtil.ProductStatus.getStatusDesc(product.getStatus()));
		vo.setHotStatus(ConstUtil.HotStatus.getHotDesc(product.getHot()));
		vo.setHot(product.getHot());
		return vo;
		
	}
	
}
