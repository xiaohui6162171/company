package me.gaigeshen.wecha.tpl.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 预算计算
 */
public class BudgetPriceUtils {
	//根据预算的区间获得服务费，budget预算，sortName设计分类
	public static Float getServiceFee(double budget, String sortName) {
		DecimalFormat df = new DecimalFormat ("#.##");
		// 定义服务费
		String servicefee = null;
		List<String> sorts = new ArrayList<String>();
		sorts.add("建筑");
		sorts.add("室内");
		sorts.add("空间");
		sorts.add("展会");
		sorts.add("规划设计");
		sorts.add("建筑设计");
		sorts.add("钢结构设计");
		sorts.add("园林景观设计");
		sorts.add("室内设计");
		sorts.add("照明设计");
		sorts.add("视觉设计");
		sorts.add("市政设计");
		// 判断分类是否在集合内
		if (sorts.contains(sortName)) {
			if (budget >= 0 && budget <= 10000) {
				servicefee = df.format(budget * 0.06);
			} else if (budget > 10000 && budget <= 100000) {
				servicefee = df.format(budget * 0.05);
			} else if (budget > 100000 && budget <= 1000000) {
				servicefee = df.format(budget * 0.04);
			} else if (budget > 1000000 && budget <= 10000000) {
				servicefee = df.format(budget * 0.03);
			} else if (budget > 10000000) {
				servicefee = df.format(budget * 0.02);
			}
		} else {
			if (budget > 0 && budget <= 1000) {
				servicefee = df.format(budget * 0.06);
			} else if (budget > 1000 && budget <= 10000) {
				servicefee = df.format(budget * 0.05);
			} else if (budget > 10000 && budget <= 100000) {
				servicefee = df.format(budget * 0.04);
			} else if (budget > 100000 && budget <= 1000000) {
				servicefee = df.format(budget * 0.03);
			} else if (budget > 1000000) {
				servicefee = df.format(budget * 0.02);
			}
		}
		if (StringUtils.isEmpty(servicefee)) {
			return Float.valueOf(0);
		} else {
			return Float.valueOf(servicefee);
		}
	}
}
