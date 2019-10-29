package cn.web.auction.serviceImpl;

import java.util.Date;
import java.util.List;

import javax.management.loading.PrivateClassLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.web.auction.controller.AuctionController;
import cn.web.auction.mapper.AuctionCustomMapper;
import cn.web.auction.mapper.AuctionMapper;
import cn.web.auction.mapper.AuctionrecordMapper;
import cn.web.auction.pojo.Auction;
import cn.web.auction.pojo.AuctionCustom;
import cn.web.auction.pojo.AuctionExample;
import cn.web.auction.pojo.Auctionrecord;
import cn.web.auction.pojo.AuctionrecordExample;
import cn.web.auction.pojo.AuctionuserExample;
import cn.web.auction.service.AuctionService;

@Service
public class AuctionServiceImpl implements AuctionService {
	//注解@Autowired用于bean注入时使用
	@Autowired 
	private AuctionMapper auctionMapper;
	
	@Autowired 
	private AuctionrecordMapper AuctionrecordMapper;
	
	@Autowired
	private AuctionCustomMapper auctionCustomMapper;
	@Override
	//创建一个查找所有拍卖品的方法（service层的方法是给controller层调用的）
	//返回一个对象列表list
	public List<Auction> findAuctions(Auction condition) {
		AuctionExample example = new AuctionExample();
		//example的内部类：criteria
		AuctionExample.Criteria criteria = example.createCriteria();
		//拼接条件开始
		//1、判断condition是否为空,用于判断是查询全部竞拍品还是按某些条件查询
		if (condition != null) {
			
			System.out.println("++++++++++++条件查询测试++++++++++++++++++++++++");
			System.out.println(condition);
			//循环打印condition内部对象的属性
	            System.out.println(condition.getAuctionname() + condition.getAuctiondesc());
	        System.out.println("++++++++++++条件查询测试++++++++++++++++++++++++");
	        
			//判断竞拍名是否为空（string是否为空：本身是否为空，是否是空字符串）
			if (condition.getAuctionname()!= null && !"".equals(condition.getAuctionname())) {
				criteria.andAuctionnameLike("%"+condition.getAuctionname()+"%");
			}
			
			//查询竞拍品的描述
			if (condition.getAuctiondesc()!= null && !"".equals(condition.getAuctiondesc())) {
				criteria.andAuctiondescLike("%"+condition.getAuctiondesc()+"%");
			}
			
			//拼接日期的限制条件（需要用到日期类型的自定义的日期类型转换器：自己要在util包创建java类，并在springmvc中进行配置）
			//起始日期
			if (condition.getAuctionstarttime()!=null) {
				criteria.andAuctionstarttimeGreaterThanOrEqualTo(condition.getAuctionstarttime());
			}
			//结束日期
			if (condition.getAuctionendtime()!=null) {
				criteria.andAuctionendtimeLessThanOrEqualTo(condition.getAuctionendtime());
			}
			
			//起拍价
			if (condition.getAuctionstartprice()!= null ) {
				criteria.andAuctionstartpriceGreaterThanOrEqualTo(condition.getAuctionstartprice());
			}
		}
		//按照商品的起拍时间进行降序排列
		example.setOrderByClause("auctionstarttime desc");
		//如果condition为空，那么example就不会被拼接进其他条件
		List<Auction> list = auctionMapper.selectByExample(example) ;
		
		System.out.println("***********************AuctionServiceImpl测试***************************");
		System.out.println(list);
		Auction auction1 = null;
		//循环打印list内部对象的属性
        for (int i = 0; i < list.size(); i++) {
            auction1 = list.get(i);
            System.out.println(auction1.getAuctionname() + auction1.getAuctiondesc());
        }
        System.out.println("***********************AuctionServiceImpl测试***************************");
        
		return list;
	}
	
	//插入竞拍商品
	@Override
	public void addAuction(Auction auction) {
		auctionMapper.insert(auction);		
	}

    //删除竞拍商品
	@Override
	public void removeAuction(Integer auctionId) {
		
		//删除记录表的记录
	    //查询条件封装类 
		AuctionrecordExample example =new AuctionrecordExample();
		//调用封装类的内部类
		AuctionrecordExample.Criteria criteria = example.createCriteria();
		//拼接实际需要的条件（精确查询）
		//andAuctionidEqualTo()之类的方法是用反向工程创建的查询方法
		criteria.andAuctionidEqualTo(auctionId);
		//删除竞拍记录表的数据
		AuctionrecordMapper.deleteByExample(example);
		//删除商品表的记录
		auctionMapper.deleteByPrimaryKey(auctionId);
	}

	//通过ID查找auction对象
	@Override
	public Auction findAuctionsById(Integer auctionid) {
		Auction auction =auctionMapper.selectByPrimaryKey(auctionid);
		return auction;
	}

	//修改商品信息，根据传进来的对象的ID，将数据库里面的属性改成像当前对象一样
	@Override
	public void updateAuctionById(Auction auction) {
		System.out.println("测试impl修改    测试impl修改    测试impl修改    测试impl修改    测试impl修改");
	    System.out.println(auction);
	    System.out.println("测试impl修改    测试impl修改    测试impl修改    测试impl修改    测试impl修改");
		auctionMapper.updateByPrimaryKey(auction);
	}
	
	//查找一个商品的全部与竞拍有关的详细信息
	//根据ID查找连接后的三个表的全部信息
	@Override
	public Auction findAuctionAndRecordList(int auctionid) {
		return auctionCustomMapper.findAuctionAndRecordListById(auctionid);
		
	}
	//插入竞拍记录
	//异常要一层层抛出，知道controller层进行处理
	@Override
	public void addAuctionRecord(Auctionrecord record) throws Exception
	{
		//通过记录中商品的ID查找三个表中的所有信息，用于对竞拍是否成功进行判断
		Auction auction = auctionCustomMapper.findAuctionAndRecordListById(record.getAuctionid());
		if (auction.getAuctionendtime().after(new Date())==false) {	//过期
			System.out.println("record.getAuctionid()"+record.getAuctionid());
			System.out.println("auction"+auction);
			System.out.println("auction.getAuctionendtime()"+auction.getAuctionendtime());
			System.out.println("new Date()"+new Date());
			throw new Exception("拍卖时间已过期了");
		}else {
			//之前已经有人竞价
			if (auction.getAuctionrecordList()!=null && auction.getAuctionrecordList().size()>0) {//有记录数
				//查找到所有信息后有对所有记录进行按照竞拍出价进行降序排序，第一个的出价是最高的
				Auctionrecord maxRecord = auction.getAuctionrecordList().get(0);
				//要把BigDecimal改成Double（数字型包装类）才可以比较
				if (record.getAuctionprice()<maxRecord.getAuctionprice()) {
					throw new Exception("价格必须高于竞拍价");
				}
			}else {//之前无人竞价
				if (record.getAuctionprice()<auction.getAuctionstartprice()) {
					throw new Exception("价格必须高于起拍价");
				}
			}
		}
		AuctionrecordMapper.insert(record);	
	}
	
	//获取结束竞拍的记录
	@Override
	public List<AuctionCustom> findAuctionEndtimeList() {
		return auctionCustomMapper.findAuctionEndtimeList();
	}
	
	//获取正在竞拍的记录
	@Override
	public List<Auction> findAuctionNoEndtimeList() {
		return auctionCustomMapper.findAuctionNoEndtimeList();
	}

}
