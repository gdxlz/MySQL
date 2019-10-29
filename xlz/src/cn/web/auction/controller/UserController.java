package cn.web.auction.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.web.auction.pojo.Auction;
import cn.web.auction.pojo.Auctionuser;//不知道老师怎么加进去的
import cn.web.auction.service.UserService;

@Controller
@RequestMapping("/user") //写义该Controller的全局访问名
public class UserController {
	
	//controller通过Userservice接口定义一个实现了接口全部方法的对象，进而调用service的逻辑功能
	@Autowired
	private UserService userService;
	
	//登录功能
	@RequestMapping("/doLogin")
	public String dologin(String username,
			String userpassword,HttpSession session,Model mv,String inputCode) {
		
		//先验证验证码，有利于提高效率
		//Number.jsp中把验证码的值存放到了session中，所以这里可以通过session获得
		//从login.jsp拿到inputCode与number.jsp中自动生成的放在session中的验证码值进行判断
		if(!inputCode.equals(session.getAttribute("numrand"))) {
			//把错误提示信息通过model传给前端
			//Model只是用来传输数据的，并不会进行业务的寻址。ModelAndView 却是可以进行业务寻址
			mv.addAttribute("errorMsg","验证码错误");
			return "login";
		}
		
		Auctionuser user=userService.login(username, userpassword);
		if (user!=null) {//登录成功
			session.setAttribute("user", user);
			//在视图解析器里面将index变为/index.jsp
			//return "index";
			//登录成功则跳转到获取拍卖品的controller，controller先获取数据，然后再跳转到index界面
			return "redirect:/auction/queryAuctions"; 
		}else {//登录失败会返回错误信息
			mv.addAttribute("errorMsg","用户名或密码错误");
			return "login";
		}
	}
	
	//注册功能
	@RequestMapping("/register")
	public String register(
			//用于存放对象user，但是jsp要使用user时要用registerUser这个名字
			//用于存放errorlist，在jsp页面会循环打印校验错误信息
			Model model,
			//用于发生注册失败时的数据回显
			@ModelAttribute("registerUser") 
			//通过注解@Validated对前端传来的Auctionuser对象的属性参数进行验证（pojo文件里面有用注解@的验证过程）
			//BindingResult用于存放校验错误信息的容器
			//这两个是连体婴，死步骤
			@Validated Auctionuser user,BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			//存放所有错误信息
			
			//方式一：将错误信息一起输出
	/*		//ObjectError是封装好的类
			List<ObjectError> errorList = bindingResult.getAllErrors();
			//控制台测试
			for(ObjectError error:errorList) {
			System.out.println(error.getDefaultMessage());
			*/	
			
			//方式二：将每个错误信息分开
			List<FieldError> erros = bindingResult.getFieldErrors();
			//将错误信息一个个对应的
			for(FieldError fieldError:erros) {
				//往model加入键值对，键为fieldError.getField()，
				//是错误的类型：userpassword，username,usercardno
				//值是fieldError.getDefaultMessage()，是校对返回的错误信息
				//model把这些键值对返回给前端jsp界面,供前端调用
				model.addAttribute(fieldError.getField(),fieldError.getDefaultMessage());
				System.out.println(fieldError.getField());
		}
			//错误信息放入model，供jsp页面使用(下面代码用于方式一)
		    //model.addAttribute("errorlist",errorList);
			return "register";
		}
		
		//表单的控件的name的值与pojo类的属性名一一对应，所以框架自动会将表单提交的属性放在一个对象中（方法里面的形参），
		//这里将表单的数值封装进入了Auctionuser user中，可以作为对象直接添加
		userService.addUser(user);
		return "login";
	}
	
	//注销功能
	@RequestMapping("/doLogout")
	//关闭登录后创建后的会话，清空相关信息
	public String doLogout(HttpSession Session) {
		Session.invalidate();
		return "login";
	}
}
