package cn.web.auction.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
//import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import cn.web.auction.pojo.Auction;
import cn.web.auction.pojo.AuctionCustom;
import cn.web.auction.pojo.Auctionrecord;
import cn.web.auction.pojo.Auctionuser;
import cn.web.auction.service.AuctionService;
import cn.web.auction.serviceImpl.AuctionServiceImpl;

@Controller
@RequestMapping("/auction")
public class AuctionController {
	//用接口创建一个实现了接口的类？用于调用接口实现的方法？
	//接口类型的引用指向了一个实现给接口的对象，这是java中的一种多态现象
	@Autowired
	private AuctionService auctionservice;
	
	//定义一个常量,表示一页记录的条数
	public static final int PAGE_SIZE = 5;
	
	@RequestMapping("/queryAuctions")
	public ModelAndView	queryAuctions(
			//让pageNum放在url上，通过URL传给当前界面/auction/queryAuctions
			//value是当前的页数，required是指越界后是否显示空（一般为false）(原意是：该参数是否是必要的)，
			//defaultValue是一开始默认显示的页数的序号
			@RequestParam(value="pageNo",required=false,defaultValue="1")int pageNo,
			//把查询条件作为model给到前端使用,可以回显；另外做到按下一页也要递交表单
			@ModelAttribute("condition") Auction auction) {
		
		//ModelAndView:	可以指定作用域的数据和视图，用来传数据给前端jsp界面
		ModelAndView mv =new ModelAndView();
		
		//起始一页为PAGE_SIZE行,其中pageNo是页面显示的页码数？
		PageHelper.startPage(pageNo,PAGE_SIZE);
		
		//通过service对象返回对象列表list，auction是存储条件的对象，用户没有在前端设条件时，auction为null，会查询所有商品
		List<Auction> list = auctionservice.findAuctions(auction);
		
		System.out.println("###################AuctionController测试#########################");
		//先打印list，如果list为空，打印的值为null
		//打印出來记录的条数为PAGE_SIZE，是因为PageHelper？
		System.out.println(list);
		Auction auction1 = null;
		//循环打印list内部对象的属性
        for (int i = 0; i < list.size(); i++) {
            auction1 = list.get(i);
            System.out.println(auction1.getAuctionname() + auction1.getAuctiondesc());
        }
        System.out.println("###################AuctionController测试#########################");
        
        //PageInfo 用于控制上一页，下一页，最后一页等信息。
		PageInfo pageInfo = new PageInfo<>(list);
		
		//把数值，对象等等通过ModelAndView对象mv返回给前端jsp
		mv.addObject("auctionList",list);
		mv.addObject("pageInfo",pageInfo);
		
		//设置要返回的界面
		mv.setViewName("index");
		return mv;
	}
	
	//发布商品
	//有两个操作要完成，1、将文件上传到服务器，2、将图片信息传入数据库
	@RequestMapping("/publishAuctions")
	public String publishAuctions(Auction auction, MultipartFile pic,HttpSession session) {
		try {
			if(pic.getSize()>0) {
//			images不是工程里面的文件夹吗？	是的，先得到文件夹的绝对路径，然后把图片传进去
			String path = session.getServletContext().getRealPath("images");
			System.out.println("打印图片存储位置   打印图片存储位置   打印图片存储位置");
			System.out.println(path);
			//E:\java\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\xlz\images
			//双击Server里面的服务器，可以选择，修改项目部署的路径：java，Tomcat，自定义路径
			System.out.println("打印图片存储位置   打印图片存储位置   打印图片存储位置");
			//path是 images 在Tomcat的绝对路径，pic.getOriginalFilename()是文件名
			File targetFile = new File(path,pic.getOriginalFilename());
//			真正的传图片到工程的images文件夹下面
			pic.transferTo(targetFile);
			
			auction.setAuctionpic(pic.getOriginalFilename());
			auction.setAuctionpictype(pic.getContentType());
			auctionservice.addAuction(auction);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/auction/queryAuctions"; 
	}
	
	
	//删除商品
	//通过超链接获得参数，对于初学者极其麻烦，网上没有搜到系统的教学，具体的还是要自己日后看视频
	//这里的/removeAuction/{auctionid}中的auction可以换成各种变量，例如：a,似乎只是用来放数据以此来找路径，与前端(auctionId)没有对应关系
	@RequestMapping(value="/removeAuction/{auctionid}",method=RequestMethod.GET)
	//这里的auctionid和上面的一样，可以是各种变量，原因不明
	public String removeAuction( @PathVariable Integer auctionid) {
		//要删除两个表的数据：表auction 的auctionId,表auctionrecord 的 autionId(外键，要先删除)
		System.out.println(auctionid);
		auctionservice.removeAuction(auctionid);
		return "redirect:/auction/queryAuctions"; 
	}
	
	//跳转到修改商品的界面
	//在跳转前，通过前端超链接传来的auctionid，查询获得相关的全部属性，将属性通过对象和mv返回到前端
	@RequestMapping(value="/toUpdate/{auctionid}",method=RequestMethod.GET)
	public ModelAndView toUpdate(@PathVariable Integer auctionid){	
		System.out.println(auctionid);
		Auction auction = auctionservice.findAuctionsById(auctionid);
		ModelAndView mv =new ModelAndView();
		mv.addObject("auction", auction);
		mv.setViewName("updateAuction");
		return mv;
	}
	
	//真正的修改商品：删除旧图片；修改对象属性
	@RequestMapping(value="/updateAuctoinSubmit")
	public String update(Auction auction,MultipartFile pic,HttpSession session) {
		System.out.println(auction);
		
		try {
			if(pic.getSize()>0) {
			String path = session.getServletContext().getRealPath("images");
			
			//获得服务器images下面的名为auction.getAuctionpic()的图片
			File oldFile = new File(path,auction.getAuctionpic());
			if (oldFile.exists()) {
				oldFile.delete();
			}
			//个人理解为在服务器images下面先用pic.getOriginalFilename()这个名称“占”一个位置，后面利用transferTo将图片放进去
			File targetFile = new File(path,pic.getOriginalFilename());
			pic.transferTo(targetFile);
			
			auction.setAuctionpic(pic.getOriginalFilename());
			auction.setAuctionpictype(pic.getContentType()); 
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("测试修改    测试修改    测试修改    测试修改    测试修改");
	    System.out.println("auction到这里是"+auction);
	    System.out.println("测试修改    测试修改    测试修改    测试修改    测试修改");
	    
		auctionservice.updateAuctionById(auction);
		return "redirect:/auction/queryAuctions"; 
	}
	
	//跳转到竞拍页面
	@RequestMapping(value="/toDetail/{auctionid}")
	public ModelAndView toDetail(@PathVariable int auctionid) {
		System.out.println("77777777777777777777777777777777777777");
		System.out.println(auctionid);
		Auction auction = auctionservice.findAuctionAndRecordList(auctionid);
		System.out.println(auction);
		ModelAndView mv =new ModelAndView();
		mv.addObject("auctionDetail", auction);
		mv.setViewName("auctionDetail");
		return mv;
	}
	
	//实现竞拍功能(插入一条竞拍记录)
	@RequestMapping("/saveAuctionRecord")
	public String saveAuctionRecord(Auctionrecord record,HttpSession session,Model model) {
		
		try {
			//Auctionrecord类中的属性名与前端某些控件的name相同，已经属性值自动添加进去对象record
			//前端中没有传来的属性：userid和auctiontime，需要自己进行获取，这两个属性在controller中进行传值
			//设置竞拍记录的用户ID
			Auctionuser user = (Auctionuser)session.getAttribute("user");//user来自UserController登录时设置的
			record.setUserid(user.getUserid());
			
			//设置竞拍时间（当前时间）
			record.setAuctiontime(new Date());
			System.out.println("controller--------------------------");
			System.out.println("record:"+record);
			System.out.println("record.getAuctionid():"+record.getAuctionid());
			System.out.println("controller--------------------------");
			auctionservice.addAuctionRecord(record);
			
			//点了竞拍后还是调回当前界面
		} catch (Exception e) {
			model.addAttribute("errorMsg",e.getMessage());
			return "error";
		}
		
		//根据凭借符+对路径进行拼接，跳到同一商品的页面
		return "redirect:/auction/toDetail/"+record.getAuctionid();
	}
	
	//显示竞拍结果：拍卖结束、正在拍卖中
	@RequestMapping("/toAuctionResult")
    public ModelAndView getAuctionResult() {
		//ModelAndView:	可以指定作用域的数据和视图，用来传数据给前端jsp界面
	    ModelAndView mv =new ModelAndView();
	    
	    //获取竞拍结束的商品
	    List<AuctionCustom> auctionCustomList=auctionservice.findAuctionEndtimeList();
		//获取正在竞拍的商品
		List<Auction> auctionList=auctionservice.findAuctionNoEndtimeList();
		
		mv.addObject("auctionCustomList",auctionCustomList);
		mv.addObject("auctionList",auctionList);
		mv.setViewName("auctionResult");
		return mv;
	}
}
