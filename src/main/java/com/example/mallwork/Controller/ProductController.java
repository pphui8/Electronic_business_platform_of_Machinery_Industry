package com.example.mallwork.Controller;

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
//	创建service对象
	@Autowired
	private ProductService ProductService;
	@RequestMapping("/find_product.do")
	@ResponseBody
	//查询商品信息根据商品类型和配件类型查询以及总页数与每页行数
	public SverResponse<PageBean<Product>> findProducts(Integer productId, Integer partId
			, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		//调用Service层方法进行分页查询
		return ProductService.findProduct(productId, partId, pageNum, pageSize);
	}
	/**
	 * 热销商品展示
	 * @param num
	 * @return
	 */
	@RequestMapping("/findhotproducts.do")
	@ResponseBody
	public SverResponse<List<Product>> findHotProducts(Integer num){
		return ProductService.findHotProducts(num);
	}
	/**
	 * 查找楼层商品
	 * @return
	 */
	@RequestMapping("/findfloors.do")
	@ResponseBody
	public SverResponse<ProductFloorVo> findFloorProduct(){
		return ProductService.findFloorProducts();
		
	}
	/**
	 * 门户，根据商品编号获得商品信息
	 * @return
	 */
	@RequestMapping("/getdetail.do")
	@ResponseBody
	public SverResponse<Product> getProductDetail(Integer productId){
		return ProductService.findProductDetailForPortal(productId);
	}
	/**
	 * 查询商品信息
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
