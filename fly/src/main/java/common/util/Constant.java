package common.util;

import java.util.ArrayList;
import java.util.List;

import cfg.vo.Bean;
import cfg.vo.PerfProperty;

public class Constant {

	public static final String SESSION_MEMBER = "SESSION_MEMBER";
	public static String SESSION_MARK = "m";

	public static final String SPLIT_MARK = ","; // 统一分隔标记

	public static final String ENABLE_DEL = "-2";
	public static final String ENABLE_NOR = "1";
	
	public static final String USRCT = "";//new Project's flag, <= 4 char
	
	/**
	 * 
	 */
	public static List<PerfProperty> PPS =  new ArrayList<PerfProperty>();
	public static List<Bean> BEANS =  new ArrayList<Bean>();
	
	public static final long DAY_SPAN = 24 * 60 * 60 * 1000;
	
	public static final String FOOTER_HJ = "合计:";
	public static final String URL_SUFFIX = ".sg";
	public static final  String PAGE_PARAM = "?isPage=1";
	public static final  String PAGE_PARAM2 = "?isPage=2";
	public static final String EDIT = "edit";
	public static final String ADD = "add";
	public static final String DETAIL = "detail";
	public static final String EXPORT = "export";
	public static final String FIND = "find";
	public static final String PERF = "perf";
	public static final String ID = "id";
	public static final String UPDATE_SUCCESS =  "成功更新！";
	public static final String ADD_SUCCESS =  "成功新增！";
	public static final String DEL_SUCCESS =  "成功删除！";
	public static final String DEL_ERR =  "删除失败！";
}
