package cn.web.auction.pojo;

public class AuctionCustom extends Auction{
	//创建一个类，用来存放AuctionCustomMapper.xml某个方法（获取竞拍结束记录）返回的结果
	//该类相比于Auction.java，多了auctionprice和username（前端要用到）。
	 private Double auctionprice;
	 private String username;
	 
	public Double getAuctionprice() {
		return auctionprice;
	}

	public void setAuctionprice(Double auctionprice) {
		this.auctionprice = auctionprice;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
