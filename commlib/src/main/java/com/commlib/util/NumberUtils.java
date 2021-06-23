package com.commlib.util;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class NumberUtils {

	/**
	 * 格式化数值
	 * @param fd 指定保留小数位
	 * @param value 数值
	 * @return
	 */
	public static double getDouble2FractionDigits(int fd, double value){
		double finalMoney = value;
        DecimalFormat formater = new DecimalFormat();
        formater.setMaximumFractionDigits(fd);
        formater.setGroupingSize(0);
        formater.setRoundingMode(RoundingMode.FLOOR);
//        System.out.println(formater.format(finalMoney));
		finalMoney= Double.parseDouble(formater.format(finalMoney));
        return finalMoney;
	}
	public static double getDoubleStr2FractionDigits(int fd, String value){
		double finalMoney = Double.parseDouble(value);
		DecimalFormat formater = new DecimalFormat();
		formater.setMaximumFractionDigits(fd);
		formater.setGroupingSize(0);
		formater.setRoundingMode(RoundingMode.FLOOR);
//        System.out.println(formater.format(finalMoney));
		finalMoney= Double.parseDouble(formater.format(finalMoney));
		return finalMoney;
	}
}
