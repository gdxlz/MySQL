package cn.web.auction.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import cn.web.auction.mapper.AuctionuserMapper;
import cn.web.auction.pojo.Auctionuser;
import cn.web.auction.pojo.AuctionuserExample;
import cn.web.auction.service.UserService;

@Service  //业务类和mapper之间的关系？ 调用关系 
public class UserServiceImpl implements UserService {
	
	@Autowired //自动在ioc容器中获取AuctionuserMapper类别的代理接口实例，AuctionuserMapper有着最底层的SQL语句用于调用
	private AuctionuserMapper userMapper;
	
	//登录判断用户名密码是否正确
	@Override
	public Auctionuser login(String username, String password) {
		//查询条件封装类 
		AuctionuserExample example = new AuctionuserExample();
		//根据用户名和密码查询
		//调用封装类的内部类
		AuctionuserExample.Criteria criteria = example.createCriteria();
		//拼接实际需要的条件（精确查询）
		//andUsernameEqualTo()之类的方法是用反向工程创建的查询方法
		criteria.andUsernameEqualTo(username);
		criteria.andUserpasswordEqualTo(password);
		
		List<Auctionuser> list =userMapper.selectByExample(example);
		
		//逻辑代码编写，登录验证
		//list.size()>0是怕可能会出现多个返回值对
		if(list!=null&&list.size()>0) {
			//返回的是一个user，最后将其放到session中
			return list.get(0);
		}
		return null;
	}

	//增加用户
	@Override
	public void addUser(Auctionuser user) {
		user.setUserisadmin(0);
		userMapper.insert(user);
	}

}
