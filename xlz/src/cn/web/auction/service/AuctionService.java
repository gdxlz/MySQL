package cn.web.auction.service;

import java.util.List;

import cn.web.auction.pojo.AuctionCustom;
import cn.web.auction.pojo.Auction;
import cn.web.auction.pojo.Auctionrecord;

public interface AuctionService {
//创建拍卖物接口，定义功能
	public List<Auction> findAuctions(Auction auction);

	public void addAuction(Auction auction);

	public void removeAuction(Integer auctionid);

	public Auction findAuctionsById(Integer auctionid);

	public void updateAuctionById(Auction auction);

	public Auction findAuctionAndRecordList(int auctionid);

	public void addAuctionRecord(Auctionrecord record)throws Exception;
	
	public List<AuctionCustom> findAuctionEndtimeList();

	public List<Auction> findAuctionNoEndtimeList();

}
