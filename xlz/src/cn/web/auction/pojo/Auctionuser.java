package cn.web.auction.pojo;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

//pojo里面的属性名和jsp里面的控件的“name”一一相同对应（register.jsp和index.jsp）
//jsp文件要以在这个为准
public class Auctionuser {
	
    private Integer userid;
    
  //用注解对username进行验证，验证错误就返回message的信息，信息的内容在对应的properties文件里面
  	@Size(min=3,max=6,message= "{register.username.length.error}")
    private String username;//用户名长度为3-6
  	
  	@Size(min=4,message= "{register.userpassword.length.error}")
    private String userpassword;//密码长度至少为4
  	
  	//记事本为“\d{18}”，这里多的一个'\'是转义字符
  	//此处为正则表达式，与上面不同
  	@Pattern(regexp="\\d{18}",message="{register.usercardno.format.error}")
    private String usercardno;//身份证号长度为18

    private String usertel;

    private String useraddress;

    private String userpostnumber;

    private Integer userisadmin;

    private String userquestion;

    private String useranswer;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword == null ? null : userpassword.trim();
    }

    public String getUsercardno() {
        return usercardno;
    }

    public void setUsercardno(String usercardno) {
        this.usercardno = usercardno == null ? null : usercardno.trim();
    }

    public String getUsertel() {
        return usertel;
    }

    public void setUsertel(String usertel) {
        this.usertel = usertel == null ? null : usertel.trim();
    }

    public String getUseraddress() {
        return useraddress;
    }

    public void setUseraddress(String useraddress) {
        this.useraddress = useraddress == null ? null : useraddress.trim();
    }

    public String getUserpostnumber() {
        return userpostnumber;
    }

    public void setUserpostnumber(String userpostnumber) {
        this.userpostnumber = userpostnumber == null ? null : userpostnumber.trim();
    }

    public Integer getUserisadmin() {
        return userisadmin;
    }

    public void setUserisadmin(Integer userisadmin) {
        this.userisadmin = userisadmin;
    }

    public String getUserquestion() {
        return userquestion;
    }

    public void setUserquestion(String userquestion) {
        this.userquestion = userquestion == null ? null : userquestion.trim();
    }

    public String getUseranswer() {
        return useranswer;
    }

    public void setUseranswer(String useranswer) {
        this.useranswer = useranswer == null ? null : useranswer.trim();
    }
}