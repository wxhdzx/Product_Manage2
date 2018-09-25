package com.product.manage.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.product.manage.pojo.Category;
import com.product.manage.pojo.Product;
import com.product.manage.service.CategoryService;
import com.product.manage.service.ProductService;
import com.product.manage.util.PageBean;

@Controller
@RequestMapping("product")
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	@RequestMapping(value="list")
	public String list(HttpServletRequest request,@RequestParam(defaultValue="1")Integer currentPage,@RequestParam(defaultValue="5")Integer pageSize,Integer minprice,Integer maxprice,String pName) {
		 PageBean pb = productService.queryAllPage(currentPage, pageSize,minprice,maxprice,pName);
		 request.getSession().setAttribute("pb",pb);
		 return "index";
	}
	//������ӽ���
	@RequestMapping("add")
	public String toAdd(HttpServletRequest request) {
	/*	List<String> cName = categoryService.queryName();
		request.setAttribute("cName",cName);*/
		 List<Category> list = categoryService.queryAll();
		 request.getSession().setAttribute("list",list);
		
		return "add";
	}
	//ɾ��ҳ��
	@RequestMapping("delete")
	public String delete(Integer pId) {
		productService.deleteById(pId);
		return "redirect:list.do";
	}
	//���������Ϣ
	@RequestMapping("save")
	public String save(Product product) {
		
		product.setpDate(new Date());
		productService.saveProduct(product);
		return "redirect:list.do";
	}
	//����id���Ҹ���Ʒ
	@RequestMapping("toUpdate")
	public String findById(Integer pId,HttpServletRequest request) {
		Product product = productService.selectById(pId);
		request.getSession().setAttribute("product",product);
		
		 List<Category> list = categoryService.queryAll();
		 request.getSession().setAttribute("list",list);
		return "update";
	}
	//�޸���Ʒ����
	@RequestMapping("update")
	public String update(Product product) {
		productService.updateById(product);
		return "redirect:list.do";
	}
}
