package com.food.core.util;

import cn.hutool.core.util.StrUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
* @ClassName: MoneyUtils
*/
public class MoneyUtils {
	
	
	
	public static final int SCALE = 8;
	public static final int YUAN_CALE=2;
	public static final String CURRENCY_FEN_REGEX = "\\-?[0-9]+";
	public static final String YUAN_ZERO="0.00";

    /**
     * 金额元转分
     * @see： 注意:该方法可处理贰仟万以内的金额,且若有小数位,则不限小数位的长度
     * @see ：注意:如果你的金额达到了贰仟万以上,则不推荐使用该方法,否则计算出来的结果会令人大吃一惊
     * @param amount  金额的元进制字符串
     * @return String 金额的分进制字符串
     */
	@Deprecated
    public static Integer moneyYuanToFen(String amount){
        if(StrUtil.isEmpty(amount)){
            return 0;
        }
        String regex = "\\d\\.\\d*|[1-9]\\d*|\\d*\\.\\d*|\\d";
        Pattern pattern = Pattern.compile(regex); //将给定的正则表达式编译到模式中。
        Matcher isNum = pattern.matcher(amount);//创建匹配给定输入与此模式的匹配器。
        if(!isNum.matches()){
            return 0;
        }
        //传入的金额字符串代表的是一个整数
        if(-1 == amount.indexOf(".")){
            return Integer.parseInt(amount) * 100 ;
        }
        //传入的金额字符串里面含小数点-->取小数点前面的字符串,并将之转换成单位为分的整数表示
        int money_fen = Integer.parseInt(amount.substring(0, amount.indexOf("."))) * 100;
        //取到小数点后面的字符串
        String pointBehind = (amount.substring(amount.indexOf(".") + 1));
        //amount=12.3
        if(pointBehind.length() == 1){
            return money_fen + Integer.parseInt(pointBehind)*10 ;
        }
        //小数点后面的第一位字符串的整数表示
        int pointString_1 = Integer.parseInt(pointBehind.substring(0, 1));
        //小数点后面的第二位字符串的整数表示
        int pointString_2 = Integer.parseInt(pointBehind.substring(1, 2));
        //amount==12.03,amount=12.00,amount=12.30
        if(pointString_1 == 0){
            return money_fen + pointString_2 ;
        }else{
            return money_fen + pointString_1*10 + pointString_2 ;
        }
    }
	@Deprecated
    public static String moneyFenToYuan(Integer money){
        if(money ==null){
            return "0.00";
        }
        String amount = String.valueOf(money);
        if(amount.indexOf(".") > -1){
            return amount;
        }
        if(amount.length() == 1){
            return "0.0" + amount;
        }else if(amount.length() == 2){
            return "0." + amount;
        }else{
            return amount.substring(0, amount.length()-2) + "." + amount.substring(amount.length()-2);
        }
    }
    
    

	/**
	 * 判断valA是否大于valB，如果valA大于valB，那么返回true，否则返回false
	 * 
	 * @param valA
	 * @param valB
	 * @return
	 */
	public static boolean compare(BigDecimal valA, BigDecimal valB) {
		return (valA.compareTo(valB) > 0);
	}

	/**
	 * 判断valA和valB的值是否相等，如果valA和valB的值是否相等，那么返回true，否则返回false
	 * 
	 * @param valA
	 * @param valB
	 * @return
	 */
	public static boolean equals(BigDecimal valA, BigDecimal valB) {
		return (valA.compareTo(valB) == 0);
	}

	/**
	 * 用于货币计算的加法，返回结果默认精确到小数点后八位，舍入模式：四舍五入
	 * 
	 * @param valA
	 * @param valB
	 * @author wangjc
	 * @date 2014-12-10
	 * @return （valA + valB）的结果
	 */
	public static BigDecimal add(BigDecimal valA, BigDecimal valB) {
		return valA.add(valB).setScale(SCALE, RoundingMode.HALF_UP);
	}

	/**
	 * 用于货币计算的加法，返回结果默认精确到小数点后八位，舍入模式：自定义
	 * 
	 * @param valA
	 * @param valB
	 * @param roundingMode
	 *            自定义的取舍模式
	 * @return （valA + valB）的结果
	 */

	public static BigDecimal add(BigDecimal valA, BigDecimal valB, RoundingMode roundingMode) {
		return valA.add(valB).setScale(SCALE, roundingMode);
	}

	/**
	 * 用于货币计算的减法，返回结果默认精确到小数点后八位 四舍五入
	 * 
	 * @param valA
	 * @param valB
	 * @return （valA - valB）的结果
	 */
	public static BigDecimal minus(BigDecimal valA, BigDecimal valB) {
		return valA.add(valB.negate()).setScale(SCALE, RoundingMode.HALF_UP);
	}

	/**
	 * 用于货币计算的减法，返回结果的舍入模式：自定义
	 * 
	 * @param valA
	 * @param valB
	 * @param roundingMode
	 *            自定义的取舍模式
	 * @return （valA - valB）的结果
	 */
	public static BigDecimal minus(BigDecimal valA, BigDecimal valB, RoundingMode roundingMode) {
		return valA.add(valB.negate()).setScale(SCALE, roundingMode);
	}

	/**
	 * 用于货币计算的乘法，返回结果默认精确到小数点后八位
	 * 
	 * @param valA
	 * @param valB
	 * @return （valA * valB）的结果
	 */
	public static BigDecimal multiply(BigDecimal valA, BigDecimal valB) {
		return valA.multiply(valB).setScale(SCALE, RoundingMode.HALF_UP);
	}


	/**
	 * 将指定的值转换为BigDecimal对象，如果val为null或者为空，那么默认返回0
	 * 
	 * @param val
	 * @return
	 */
	public static BigDecimal toBigDecimal(String val) {
		if (val == null || "".equals(val.trim())) {
			return BigDecimal.ZERO;
		} else {
			return new BigDecimal(val);
		}
	}

	/**
	 * 分转换成元 精度 小数点后八位， 四舍五入
	 * 
	 * @param fen
	 * @return yuan的String
	 */
	
	public static String fenToyuan(Long fen){
		if(null==fen){
			fen=0L;
		}
		DecimalFormat df=new  DecimalFormat("0.00"); 
    	return df.format(BigDecimal.valueOf(fen).divide(new BigDecimal(100).setScale(SCALE, RoundingMode.HALF_UP)));
	}
	
	/**
	 * 分转换成元 精度 小数点后八位， 取舍自定义
	 * 
	 * @param fen
	 * @return yuan的String
	 */
	public static String fenToyuan(Long fen,RoundingMode  roundingMode){
		if(null==fen){
			fen=0L;
		}
		DecimalFormat df=new  DecimalFormat("0.00"); 
    	return df.format(BigDecimal.valueOf(fen).divide(new BigDecimal(100).setScale(SCALE,roundingMode)));
	}

   
	 /**   
     * 将元为单位的转换为分 替换小数点，支持以逗号区分的金额  
     *   
     * @param yuan  
     * @return  
     */    
    public static Long yuanTofen(String yuan){
    	if(null==yuan){
    		yuan=YUAN_ZERO;
    	}
        String currency =  yuan.replaceAll("\\$|\\￥|\\,", "");  //处理包含, ￥ 或者$的金额    
        int index = currency.indexOf(".");    
        int length = currency.length();    
        Long amLong = 0l;    
        if(index == -1){    
            amLong = Long.valueOf(currency+"00");    
        }else if(length - index >= 3){    
            amLong = Long.valueOf((currency.substring(0, index+3)).replace(".", ""));    
        }else if(length - index == 2){    
            amLong = Long.valueOf((currency.substring(0, index+2)).replace(".", "")+0);    
        }else{    
            amLong = Long.valueOf((currency.substring(0, index+1)).replace(".", "")+"00");    
        }    
        return amLong;    
    }
    /**
     * bigdecimal  转换成两位小数元 取舍四舍五入 精度为小数后八位
     * @param bigDecimal
     * @return
     */
    public static String bigDecimalToyuan(BigDecimal  bigDecimal){
    	bigDecimal.setScale(SCALE, RoundingMode.HALF_UP);
    	DecimalFormat df=new  DecimalFormat("0.00"); 
    	return  df.format(bigDecimal);
    }
    /**
     * bigdecimal  转换成两位小数元 取舍自定义  精度为小数后八位
     * @param bigDecimal
     * @param roundingMode
     * @return
     */
    public static String bigDecimalToyuan(BigDecimal  bigDecimal,RoundingMode roundingMode){
    	bigDecimal.setScale(SCALE, roundingMode);
    	DecimalFormat df=new  DecimalFormat("0.00"); 
    	return  df.format(bigDecimal);
    }
    /**
     * 分转化成毫 直接乘以100
     * @param fen
     * @return
     */
    public static Long  fenTohao(Long fen){
    	if(null==fen){
    		fen=0L;
    		
    	}
    	return BigDecimal.valueOf(fen).multiply(new BigDecimal(100)).longValue();
    }
    
    /**
     * 毫转化成分 除以100 精度小数后8位 ，四舍五入
     * @param hao
     * @return
     */
    public static Long haoTofen(Long hao){
    	if(null==hao){
    		hao=0L;
    	}
    	return BigDecimal.valueOf(hao).divide(new BigDecimal(100).setScale(SCALE, RoundingMode.HALF_UP)).longValue();
    }
    /**
     * 毫转化成分 除以100 精度小数点后8位， 自定义取舍
     * @param hao
     * @param roundingMode
     * @return
     */
    public static Long haoTofen(Long hao,RoundingMode roundingMode){
    	if(null==hao){
    		hao=0L;
    	}
    	return BigDecimal.valueOf(hao).divide(new BigDecimal(100).setScale(SCALE, roundingMode)).longValue();
    }
    /**
     * 元转换成毫  
     * @param yuan
     * @return
     */
    public static Long yuanTohao(String yuan){
    	if(null==yuan){
    		yuan=YUAN_ZERO;
    	}
    	return fenTohao(yuanTofen(yuan));
    	
    }
    /**
     * 毫转化成元 直接除以10000 小数点后8位  四舍五入
     * @param hao
     * @return
     */
    public static String haoToyuan(Long hao){
    	if(null==hao){
    		hao=0L;
    	}
    	DecimalFormat df=new  DecimalFormat("0.00"); 
    	return df.format(BigDecimal.valueOf(hao).divide(new BigDecimal(10000).setScale(SCALE, RoundingMode.HALF_UP)));
    }
    
    /**
     * 毫转化成元 除以1000 小数点后8位，取舍自定义
     * @param hao
     * @param roundingMode
     * @return
     */
    public static String haoToyuan(Long hao,RoundingMode roundingMode ){
    	if(null==hao){
    		hao=0L;
    	}
    	DecimalFormat df=new  DecimalFormat("0.00"); 
    	return df.format(BigDecimal.valueOf(hao).divide(new BigDecimal(10000).setScale(SCALE, roundingMode)));
    }
    
}
