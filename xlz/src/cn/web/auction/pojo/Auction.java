package cn.web.auction.pojo;

import java.util.List;//一般表示链表数据结构的时候用java.util包中的List
import java.math.BigDecimal;
import java.util.Date;

//com.sun.xml这个是有关xml的类库，这个包不知道哪里来的，作用是什么
//import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class Auction {
    @Override
	public String toString() {
		return "Auction [auctionid=" + auctionid + ", auctionname=" + auctionname + ", auctionstartprice="
				+ auctionstartprice + ", auctionupset=" + auctionupset + ", auctionstarttime=" + auctionstarttime
				+ ", auctionendtime=" + auctionendtime + ", auctionpic=" + auctionpic + ", auctionpictype="
				+ auctionpictype + ", auctiondesc=" + auctiondesc + "]";
	}

	private Integer auctionid;

    private String auctionname;

    private Double auctionstartprice;

    private Double auctionupset;

    private Date auctionstarttime;

    private Date auctionendtime;

    private String auctionpic;

    private String auctionpictype;

    private String auctiondesc;
    
    //与Auctionrecord对应的一对多关系
    private List<Auctionrecord> auctionrecordList;

    public List<Auctionrecord> getAuctionrecordList() {
		return auctionrecordList;
	}

	public void setAuctionrecordList(List<Auctionrecord> auctionrecordList) {
		this.auctionrecordList = auctionrecordList;
	}

	public Integer getAuctionid() {
        return auctionid;
    }

    public void setAuctionid(Integer auctionid) {
        this.auctionid = auctionid;
    }

    public String getAuctionname() {
        return auctionname;
    }

    public void setAuctionname(String auctionname) {
        this.auctionname = auctionname == null ? null : auctionname.trim();
    }

    public Double getAuctionstartprice() {
        return auctionstartprice;
    }

    public void setAuctionstartprice(Double auctionstartprice) {
        this.auctionstartprice = auctionstartprice;
    }

    public Double getAuctionupset() {
        return auctionupset;
    }

    public void setAuctionupset(Double auctionupset) {
        this.auctionupset = auctionupset;
    }

    public Date getAuctionstarttime() {
        return auctionstarttime;
    }

    public void setAuctionstarttime(Date auctionstarttime) {
        this.auctionstarttime = auctionstarttime;
    }

    public Date getAuctionendtime() {
        return auctionendtime;
    }

    public void setAuctionendtime(Date auctionendtime) {
        this.auctionendtime = auctionendtime;
    }

    public String getAuctionpic() {
        return auctionpic;
    }

    public void setAuctionpic(String auctionpic) {
        this.auctionpic = auctionpic == null ? null : auctionpic.trim();
    }

    public String getAuctionpictype() {
        return auctionpictype;
    }

    public void setAuctionpictype(String auctionpictype) {
        this.auctionpictype = auctionpictype == null ? null : auctionpictype.trim();
    }

    public String getAuctiondesc() {
        return auctiondesc;
    }

    public void setAuctiondesc(String auctiondesc) {
        this.auctiondesc = auctiondesc == null ? null : auctiondesc.trim();
    }
}