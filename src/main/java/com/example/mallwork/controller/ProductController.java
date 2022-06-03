package com.example.mallwork.controller;

import com.example.mallwork.Common.SverResponse;
import com.example.mallwork.Entity.Product;
import com.example.mallwork.Service.ProductService;
import com.example.mallwork.Tools.PageBean;
import com.example.mallwork.Vo.ProductFloorVo;
import com.example.mallwork.Vo.ProductListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
//	����service����
	@Autowired
	private ProductService ProductService;
	@RequestMapping("/find_product.do")
	@ResponseBody
	//��ѯ��Ʒ��Ϣ������Ʒ���ͺ�������Ͳ�ѯ�Լ���ҳ����ÿҳ����
	public SverResponse<PageBean<Product>> findProducts(Integer productId, Integer partId
			, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		//����Service�㷽�����з�ҳ��ѯ
		return ProductService.findProduct(productId, partId, pageNum, pageSize);
	}
	/**
	 * ������Ʒչʾ
	 * @param num
	 * @return
	 */
	@RequestMapping("/findhotproducts.do")
	@ResponseBody
	public SverResponse<List<Product>> findHotProducts(Integer num){
		return ProductService.findHotProducts(num);
	}
	/**
	 * ����¥����Ʒ
	 * @return
	 */
	@RequestMapping("/findfloors.do")
	@ResponseBody
	public SverResponse<ProductFloorVo> findFloorProduct(){
		return ProductService.findFloorProducts();
		
	}
	/**
	 * �Ż���������Ʒ��Ż����Ʒ��Ϣ
	 * @return
	 */
	@RequestMapping("/getdetail.do")
	@ResponseBody
	public SverResponse<Product> getProductDetail(Integer productId){
		return ProductService.findProductDetailForPortal(productId);
	}
	/**
	 * ��ѯ��Ʒ��Ϣ
	 * @param productTypeId
	 * @param partsId
	 * @param name
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/searchproducts.do")
	@ResponseBody
	public SverResponse<PageBean<ProductListVo>> searchProducts(Integer productTypeId,
																Integer partsId, String name,
																@RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
																@RequestParam(value ="pageSize",defaultValue="10") int pageSize){
		return ProductService.findProductsForProtal(productTypeId,partsId,name,pageNum,pageSize);
	}
}
