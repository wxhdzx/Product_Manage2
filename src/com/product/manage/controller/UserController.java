package com.product.manage.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.product.manage.pojo.User;
import com.product.manage.service.UserService;

@Controller
/*
 * �û�ʵ�ֵ�¼ע�Ṧ��
  */
public class UserController {
	@Autowired
	private UserService userService;
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String toLogin(String uName,String uPassword,HttpServletRequest request) {
		User record=new User();
		record.setuName(uName);
		record.setuPassword(uPassword);;
		User user = userService.queryByRecord(record);
		if(user!=null) {
			//��user���浽session��
			request.getSession().setAttribute("user",user);
			//��ת��������
			return "index";
		}
		request.setAttribute("message","�û��������������");
		return "login";
	}
	/*
	 * ע�Ṧ��
	 * */
	@RequestMapping(value="register")
	public String register(User user,HttpServletRequest request) {
		Integer i = userService.save(user);
		if(i>0) {
			//�����¼����
			request.setAttribute("message","ע��ɹ�,���¼");
			return "login";
		}
		return "register";
	}
	//��¼����
	@RequestMapping(value="login",method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	//�˳���¼
	@RequestMapping("logout")
	public String logou(HttpServletRequest request) {
		request.getSession().invalidate();
		return "login";
	}
}
