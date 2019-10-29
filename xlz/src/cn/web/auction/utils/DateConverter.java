package cn.web.auction.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class DateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String str) {
		if(str!=null && !"".equals(str)) {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return sdf.parse(str);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	

}
