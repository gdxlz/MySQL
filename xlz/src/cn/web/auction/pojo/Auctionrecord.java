package cn.web.auction.pojo;

import java.math.BigDecimal;
import java.util.Date;

import cn.web.auction.service.UserService;

public class Auctionrecord {
    private Integer id;

    private Integer userid;

    private Integer auctionid;

    private Date auctiontime;

    private Double auctionprice;

    private Auctionuser user;
    
    @Override
    public String toString() {
    	return "Auctionrecord [id=" + id + ", userid=" + userid + ", auctionid=" + auctionid + ", auctiontime="
    			+ auctiontime + ", auctionprice=" + auctionprice + ", user=" + user + "]";
    }
    public Auctionuser getUser() {
		return user;
	}


	public void setUser(Auctionuser user) {
		this.user = user;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getAuctionid() {
        return auctionid;
    }

    public void setAuctionid(Integer auctionid) {
        this.auctionid = auctionid;
    }

    public Date getAuctiontime() {
        return auctiontime;
    }

    public void setAuctiontime(Date auctiontime) {
        this.auctiontime = auctiontime;
    }

    public Double getAuctionprice() {
        return auctionprice;
    }

    public void setAuctionprice(Double auctionprice) {
        this.auctionprice = auctionprice;
    }
}