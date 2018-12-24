package com.zhongdun.ga.fri.module.util;

import android.graphics.Point;

public class Config {

	// 是否调试
	public static final boolean DEBUG = true;
	// 默认在线或离线
	public static boolean online = true;
	// 默认地图中心点
	public static Point centerPoint = new Point(11639754, 3990758);
	// 逆地理坐标
	public static Point inverSePoint = centerPoint;

	public static String mToken = "ca78282dfff148e3be5390ce027068f5";
	public static String mId = "3456";
	public static String mBaseUrl = "http://minedata.cn/service/solu/style/id/3456";


	public static final String BASE_URL ="http://web.ppmap.gab.ydxxw";
	public static final String ACCESS_TOKEN = "75d65a4902004299bfea31557f03fad3";
	public static final String SOLUTION = "3596";
	public static final String STYLE_URL = Config.BASE_URL+"/service/solu/style/id/3596";



	/*public static String ACCESS_TOKEN = "ca78282dfff148e3be5390ce027068f5";
	public static String SOLUTION = "3456";
	public static String STYLE_URL = "http://minedata.cn/service/solu/style/id/3456";*/

	// Search
	public static String SEARCH_DEFAULT_CITY = "北京市";
	public static String SEARCH_DEFAULT_PROVINCE = "北京市";
	public static String SEARCH_BY_KEY_TEXT = "酒店";
	public static String SEARCH_BY_TYPE_TEXT = "停车场";
	public static String SEARCH_BY_NEAR_TEXT = "酒店";

	public static String getOnlineText(boolean isonline) {
		if (isonline) {
			return "在线";
		} else {
			return "离线";
		}

	}
	
	public static class SearchMode {
		public static final int search = 0;
		public static final int suggest = 1;
	}
}
