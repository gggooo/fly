package common.util;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

import com.opensymphony.xwork2.ActionContext;

import cfg.vo.Bean;
import cfg.vo.Filter;
import cfg.vo.FilterProperty;
import cfg.vo.Member;
import cfg.vo.Myinput;
import cfg.vo.Org;
import cfg.vo.OrgMember;
import cfg.vo.Perf;
import cfg.vo.PerfProperty;
import cfg.vo.Property;

import common.dao.BaseDao;
import common.vo.AsyncTree;
import common.vo.BaseTO;
import common.vo.NameValuePair;
import common.vo.SpringProperties;

public class Util {

	public static NumberFormat n3 = NumberFormat.getInstance();
	static{
		n3.setMaximumFractionDigits(3);
		n3.setGroupingUsed(false);
	}
	public static NumberFormat n2 = NumberFormat.getInstance();
	static{
		n2.setMaximumFractionDigits(2);
		n2.setGroupingUsed(false);
	}
	public static NumberFormat getNumberFormat(int max) {
		NumberFormat res = NumberFormat.getInstance();
		res.setMaximumFractionDigits(max);
		res.setGroupingUsed(false);
		return res;
	}

	private static String projectName="fly2";        //  你项目的名称

	//获取当前项目的绝对路径
	  public static String getPorjectPath(){
	   String nowpath;             //当前tomcat的bin目录的路径 如 D:/java/software/apache-tomcat-6.0.14/bin
	   String tempdir;
	   nowpath=System.getProperty("user.dir");
	   tempdir=nowpath.replace("bin", "webapps");  //把bin 文件夹变到 webapps文件里面
	   tempdir+="/"+projectName;  //拼成D:/java/software/apache-tomcat-6.0.14/webapps/sz_pro
	   return tempdir;  
	  }

//	public static String constantProSD(BaseDao dao, String crewid) {
//		return dao.findConstant("prosd_" + crewid);
//	}

//	public static String constantSpecialty(BaseDao dao, String crewid) {
//		return dao.findConstant("specialty_" + crewid);
//	}


	/**
	 * 生成16位随机数
	 * 
	 * @return
	 */
	public static String generateWord() {
		String[] beforeShuffle = new String[] { "2", "3", "4", "5", "6", "7",
				"8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
				"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
				"W", "X", "Y", "Z" };
		List<String> list = Arrays.asList(beforeShuffle);
		Collections.shuffle(list);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
		}
		String afterShuffle = sb.toString();
		String result = afterShuffle.substring(1, 17);
		return result;
	}

	/**
	 * 生成16位随机数
	 * 
	 * @return
	 */
	public static String generateId() {
		String[] beforeShuffle = new String[] { "2", "3", "4", "5", "6", "7",
				"8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
				"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
				"W", "X", "Y", "Z" };
		List<String> list = Arrays.asList(beforeShuffle);
		Collections.shuffle(list);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
		}
		String afterShuffle = sb.toString();
		String result = afterShuffle.substring(1, 33);
		return result;
	}

	public static SpringProperties findProperty(String fileName, String bean) {
		BeanDefinitionRegistry reg = new DefaultListableBeanFactory();
		PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(
				reg);
		reader.loadBeanDefinitions(new ClassPathResource(Util
				.notEmptyString(fileName) ? fileName : "common.properties"));
		BeanFactory factory = (BeanFactory) reg;
		return (SpringProperties) factory.getBean(bean);
	}

	public static boolean emptyString(String oran) {
		return oran == null || oran.equals("");
	}

	public static boolean notEmptyString(String oran) {
		return oran != null && !oran.trim().equals("")
				&& !oran.trim().equals("null");
	}

	public static boolean notEmptyList(List list) {
		return list != null && (!list.isEmpty());
	}

	/**
	 * str1是否包含在str2中。 eg:{str1[mn_1];str2[mn_0,mn_1];return true}
	 * {str1[mn_1];str2[mn_0,mn_1_1];return false}
	 * 
	 * @param str1
	 * @param str2
	 * @return true（包含）/false（不包含）
	 */
	public static boolean contains(String str1, String str2) {
		boolean result = false;
		if (str1 != null && str2 != null) {
			String commaUserFlag = commaString(str2);
			int index = commaUserFlag.indexOf(commaString(str1));
			if (index > -1) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * eg:{str1[mn_1mn_2];str2[mn_0,mn_1];return mn_1}
	 * {str1[mn_3mn_2];str2[mn_0,mn_1];return ""}
	 * 
	 * @param str1
	 *            长字符串
	 * @param str2
	 *            字符串列表
	 * @return
	 */
	public static String containsStr(String str1, String str2) {
		String[] split = str2.split(",");
		for (String s : split) {
			if (str1.contains(s)) {
				return s;
			}
		}
		return "";
	}

	/**
	 * string两端加【，】,<br>
	 * eg:{str1[mn_1];return [,mn_1,]}
	 * 
	 * @param string
	 * @return
	 */
	private static String commaString(String string) {
		StringBuilder sb = new StringBuilder(",");
		sb.append(string);
		sb.append(",");
		return sb.toString();
	}

	/**
	 * 把一个xx,yy,zz的字符串变成list
	 * 
	 * @return
	 */
	public static List<String> strToList(String oran) {
		List<String> res = new ArrayList<String>();
		if (notEmptyString(oran)) {
			String[] split = oran.split(",");
			for (String s : split) {
				if (notEmptyString(s)) {
					res.add(s);
				}
			}
		}
		return res;
	}

	/**
	 * 返回yy在xx,yy,zz的字符串中的位置,此yy的位置为1 不存在则返回-1
	 * 
	 * @return
	 */
	public static int getIndexInStr(String s, String oran) {
		int res = -1;
		res = strToList(oran).indexOf(s);
		return res;
	}

	/**
	 * 把, ,xx, ,yy,zz, ,变成xx,yy,zz,
	 * 
	 * @param oran
	 * @return
	 */
	public static String trimStr(String oran) {
		StringBuilder res = new StringBuilder();
		if (notEmptyString(oran)) {
			String[] split = oran.split(",");
			for (String s : split) {
				if (notEmptyString(s)) {
					res.append(s.trim()).append(",");
				}
			}
		}
		return res.toString();
	}

	/**
	 * 把s加入pid中, s="5";pid="2,3,";return "2,3,5,"; s="3";pid="2,3,";return
	 * "2,3,";
	 * 
	 * @param s
	 * @param oran
	 */
	public static String addStr(String s, String oran) {
		if (oran == null) {
			oran = "";
		}
		StringBuilder res = new StringBuilder(oran);
		if (commaString(oran).indexOf(commaString(s)) == -1) {
			res.append(s).append(",");
		}
		return res.toString();
	}

	/**
	 * 在pid中减去s s="5";pid="2,3,";return "2,3,"; s="2";pid="2,3,";return "3,";
	 * 
	 * @param s
	 * @param oran
	 */
	public static String minusStr(String s, String oran) {
		if (notEmptyString(oran)) {
			return trimStr(commaString(oran).replaceAll(commaString(s), ","));
		}
		return oran;
	}

	/**
	 * 得到oran中有几个有效值 oran="1,";return 1; oran=", ,1,2,,3,,,";return 3;
	 * 
	 * @param pid
	 * @return
	 */
	public static int strNum(String oran) {
		int res = 0;
		if (notEmptyString(oran)) {
			String[] split = oran.split(",");
			for (String s : split) {
				if (notEmptyString(s)) {
					res++;
				}
			}
		}
		return 0;
	}

	/**
	 * 
	 * @param oran
	 *            1,3-5,
	 * @return 1,3,4,5
	 */
	public static List<String> spliter(String oran) {
		List<String> res = new ArrayList<String>();
		if (notEmptyString(oran)) {
			String[] split = oran.split(",");
			for (String s : split) {
				if (s.contains("-")) {
					String[] split2 = s.split("-");
					int from = Integer.valueOf(split2[0]);
					int to = Integer.valueOf(split2[1]);
					for (int i = from; i <= to; i++) {
						res.add(String.valueOf(i));
					}
				} else {
					res.add(s);
				}
			}

		}
		return res;
	}

	/**
	 * 修改页面_Computer
	 * 
	 * @param keyword
	 * @return
	 */
	public static String editTitle(String keyword) {
		return "修改页面_" + keyword;
	}

	public static String detailTitle(String keyword) {
		return "详细页面_" + keyword;
	}

	public static String addTitle(String keyword) {
		return "增加页面_" + keyword;
	}

	public static String findTitle(String keyword) {
		return "查找页面_" + keyword;
	}

	/**
	 * mt/editComputer.sg
	 * 
	 * @param pkg
	 * @param keyword
	 * @return
	 */
	public static String editUrl(String pkg, String keyword) {
		return findUrl("edit", pkg, keyword);
	}

	public static String addUrl(String pkg, String keyword) {
		return findUrl("add", pkg, keyword);
	}

	public static String findUrl(String pkg, String keyword) {
		return findUrl("find", pkg, keyword);
	}

	public static String delUrl(String pkg, String keyword) {
		return findUrl("del", pkg, keyword);
	}

	public static String findExportXlsUrl(String pkg, String keyword) {
		return findUrl("exportXls", pkg, keyword);
	}

	public static String findExportSingleUrl(String pkg, String keyword) {
		return findUrl("exportSingle", pkg, keyword);
	}

	public static String detailUrl(String pkg, String keyword) {
		return findUrl("detail", pkg, keyword);
	}
	
	private static String findUrl(String act, String pkg, String keyword) {
		return (Util.notEmptyString(pkg) ? (pkg + "_") : "") + act + keyword
				+ Constant.URL_SUFFIX;
	}

	public static Map<Integer,String> findWeekMap() {
		Map<Integer,String> res = new HashMap<Integer, String>();
		int i= 1;
		res.put(i++, "星期日");
		res.put(i++, "星期一");
		res.put(i++, "星期二");
		res.put(i++, "星期三");
		res.put(i++, "星期四");
		res.put(i++, "星期五");
		res.put(i++, "星期六");
		return res;
	}


	public static String formatToInt(double d) {
		String valueOf = String.valueOf(d);
		if (valueOf.endsWith(".0")) {
			return valueOf.replace(".0", "");
		}
		return String.valueOf(d);
	}
	
	public static List<PerfProperty> findPpsById(String perfid){
		List<PerfProperty> res = new ArrayList<PerfProperty>();
		for(PerfProperty pp : Constant.PPS){
//			System.out.println(pp.getId());
			if(pp.getPerf().getId().equals(perfid)){
				res.add(pp);
			}
		}
		return res;
	}
	
	public static List<PerfProperty> findPpsById(String perfid,String esh){
		List<PerfProperty> res = new ArrayList<PerfProperty>();
		for(PerfProperty pp : Constant.PPS){
//			System.out.println(pp.getId());
			if(pp.getPerf().getId().equals(perfid)&&esh.equals(pp.getEditshowhide())){
				res.add(pp);
			}
		}
		return res;
	}

	/**
	 * 找到对应perf下的隐藏的PP
	 * 并且不包含在ppl中
	 * @param ppl
	 * @param pid
	 * @return
	 */
	public static List<PerfProperty> findHidePp(List<PerfProperty> ppl, String pid) {
		List<PerfProperty> res = new ArrayList<PerfProperty>();
		for(PerfProperty pp : Constant.PPS){
			Perf perf = pp.getPerf();
			if(perf.getId().equals(pid)&&"0".equals(pp.getEditshowhide())&&!ppl.contains(pp)){
				res.add(pp);
			}
		}
		return res;
	}

	public static List<PerfProperty> findPpsByList(String psid) {
		List<PerfProperty> res = new ArrayList<PerfProperty>();
		for(PerfProperty pp : Constant.PPS){
			Perf perf = pp.getPerf();
			if(perf.getId().equals(psid)
					&&("1".equals(pp.getEditshowhide())||"2".equals(pp.getEditshowhide()))){
				res.add(pp);
			}
		}
		return res;
	}
	public static List<PerfProperty> findPpsBySearch(String psid) {
		List<PerfProperty> res = new ArrayList<PerfProperty>();
		for(PerfProperty pp : Constant.PPS){
			Perf perf = pp.getPerf();
			String esh = pp.getEditshowhide();
			if(perf.getId().equals(psid)
					&&("2".equals(esh)||"0".equals(esh)||"3".equals(esh))){
				if("0".equals(esh))
						pp.setEditshowhide("3");
				res.add(pp);
			}
		}
		return res;
	}


	public static String findDetailPageUrl(String pKG, String kEY_WORD) {
		// TODO Auto-generated method stub
		return null;
	}

	public static PerfProperty findPpById(String ppid) {
		for(PerfProperty pp : Constant.PPS){
			if(pp.getId().equals(ppid)){
				return pp;
			}
		}
		return null;
	}

	public static PerfProperty findPpByPn(String property,String pid) {
		for(PerfProperty pp : Constant.PPS){
			if(pp.getPerf().getId().equals(pid)&&pp.getProperty().getProperty().equals(property)){
				return pp;
			}
		}
		return null;
	}

	/**
	 * 由于本方法只用于比较，只针对in，==操作，所以原始对象、比较对象全部是String或者是StringList
	 * 用于edit（编辑页面显示、编辑数据）判断是否在编辑范围内
	 * 用于add（增加数据）是否在编辑范围内
	 * 输入：filter（List） 过滤条件
	 * 	过滤条件中含有原始对象、比较方式、比较对象
	 * 		原始对象有普通对象、二级对象（xx.xx）
	 * 		比较对象，要求是最后被比较的值，不再在该方法中做转化（是否需要取id等，在FilterValue方法判断）
	 * 		比较方式有in、=
	 * 			in：在比较对象范围内，普通对象：取得原始对象值，测试是否在比较对象范围内
	 * 			==：等于比较对象
	 * @param filter 
	 * @param bean
	 * @param baseDao
	 * @return
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 */
	public static boolean isDataIn(Filter filter, BaseTO bean, BaseDao baseDao)
			throws NoSuchFieldException, IllegalAccessException {
		boolean datain = true;
		if (filter != null) {
			DetachedCriteria dcf = DetachedCriteria
					.forClass(FilterProperty.class);
			dcf.add(Restrictions.eq("filter", filter));
			dcf.add(Restrictions.in("op", new String[]{"==","in"}));
			List<FilterProperty> fl = baseDao.find(dcf);
			if(Util.notEmptyList(fl)){
				datain = false;
				for (FilterProperty f : fl) {
					String op = f.getOp();
					String propertyName = f.getProperty().getProperty();
					Class<? extends BaseTO> clazz = bean.getClass();
					String fvalue="";
					int indexOf = propertyName.indexOf(".");
					if (indexOf >= 0) {
						String child = propertyName.substring(indexOf + 1, propertyName.length());
						String f1 = propertyName.substring(0, indexOf);
						Field declaredField = clazz.getDeclaredField(f1);
						Class<?> paramClazz = declaredField.getType();
						BaseTO mem = (BaseTO) declaredField.get(bean);
						if (mem != null) {
							Field childField = paramClazz.getDeclaredField(child);
							fvalue= String.valueOf(childField.get(mem));
						}
					} else {
						Field declaredField = clazz.getDeclaredField(propertyName);
						fvalue= String.valueOf(declaredField.get(bean));
					}
					if(Util.notEmptyString(fvalue)){
						//被比较的原始值必须非空，若空，则无比较必要
						Object filterValue = FilterValue(f.getVal(), baseDao);
						if ("in".equals(op)) {
							List<String> filterValueList = (List<String>) filterValue;
							for (String b : filterValueList) {
								if (fvalue.equals(b)) {
									datain = true;
									break;
								}
							}
						} else if ("==".equals(op)) {
//						String c="";
//						if(filterValue instanceof BaseTO){
//							Field bfield = filterValue.getClass().getDeclaredField(
//									Constant.ID);
//							int bid = bfield.getInt(filterValue);
//							c=String.valueOf(bid);
//						}else{
//							c=String.valueOf(filterValue);
//						}
							String c=(String) filterValue;
							if (fvalue.equals(c)) {
								datain = true;
							}
						}
					}
				}
			}
		}
		return datain;
	}


	public static List<AsyncTree> findTreeValue(BaseTO bean, String id,
			BaseDao baseDao, List<String> endToTopPathIds)
			throws NoSuchFieldException, IllegalAccessException {
		List<AsyncTree> treeValue = new ArrayList<AsyncTree>();
		if (Util.notEmptyString(id)) {
			bean = baseDao.get(bean.getClass(), id);
		}
		Class<? extends BaseTO> clazz = bean.getClass();
		List<BaseTO> pl = findByParent(bean, baseDao);
		for (BaseTO bo : pl) {
			AsyncTree at = new AsyncTree();
			Field declaredField = clazz.getDeclaredField("id");
			String boid = (String) declaredField.get(bo);
			Field descrField = clazz.getDeclaredField("descr");
			String bodescr = (String) descrField.get(bo);
			at.setId(boid);
			at.setText(bodescr);
			if (endToTopPathIds.contains(boid)||endToTopPathIds.contains("all")) {
				at.setChildren(findTreeValue(bean, boid, baseDao,
						endToTopPathIds));
				at.setState("open");
			} else {
				if (Util.notEmptyList(findByParent(bo, baseDao))) {
					at.setState("closed");
				} else {
					at.setState("open");
				}
			}
			treeValue.add(at);
		}
		return treeValue;
	}


	public static List<AsyncTree> findCheckedTreeValue(List<String> mpl,
			BaseTO bean, String id, BaseDao baseDao)
			throws NoSuchFieldException, IllegalAccessException {
		List<AsyncTree> treeValue = new ArrayList<AsyncTree>();
		if (Util.notEmptyString(id)) {
			bean = baseDao.get(bean.getClass(), id);
		}
		Class<? extends BaseTO> clazz = bean.getClass();
		List<BaseTO> pl = findByParent(bean, baseDao);
		for (BaseTO bo : pl) {
			AsyncTree at = new AsyncTree();
			Field declaredField = clazz.getDeclaredField("id");
			String boid = (String) declaredField.get(bo);
			Field descrField = clazz.getDeclaredField("descr");
			String bodescr = (String) descrField.get(bo);
			at.setId(boid);
			at.setText(bodescr);
			if (mpl.contains(boid)) {
				at.setChecked("true");
			}
			// if (Util.notEmptyList(findByParent(bo,baseDao))) {
			at.setState("open");
			at.setChildren(findCheckedTreeValue(mpl, bean, boid, baseDao));
			// }
			treeValue.add(at);
		}
		return treeValue;
	}

	public static List<AsyncTree> findTreeValue(BaseTO bean, String id,
			BaseDao baseDao) throws NoSuchFieldException,
			IllegalAccessException {
		List<AsyncTree> treeValue = new ArrayList<AsyncTree>();
		if (Util.notEmptyString(id)) {
			bean = baseDao.get(bean.getClass(), Integer.valueOf(id));
		}
		Class<? extends BaseTO> clazz = bean.getClass();
		List<BaseTO> pl = findByParent(bean, baseDao);
		for (BaseTO bo : pl) {
			AsyncTree at = new AsyncTree();
			Field declaredField = clazz.getDeclaredField("id");
			String boid = (String)declaredField.get(bo);
			Field descrField = clazz.getDeclaredField("descr");
			String bodescr = (String) descrField.get(bo);
			at.setId(boid);
			at.setText(bodescr);
			at.setChildren(findTreeValue(bean, boid, baseDao));
			// if (Util.notEmptyList(findByParent(bo,baseDao))) {
			// at.setState("closed");
			// } else {
			at.setState("open");
			// }
			treeValue.add(at);
		}
		return treeValue;
	}

	private static List<BaseTO> findByParent(BaseTO p, BaseDao baseDao)
			throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		Class<? extends BaseTO> clazz = p.getClass();
		DetachedCriteria dc = DetachedCriteria.forClass(clazz);
		Field declaredField = clazz.getDeclaredField("id");
		String pid = (String) declaredField.get(p);
		if (!Util.notEmptyString(pid)) {
			dc.add(Restrictions.isNull("parent"));
		} else {
			dc.add(Restrictions.eq("parent", p));
		}
		List<BaseTO> pl = baseDao.find(dc);

		return pl;
	}

	public static void setOTMNull(String PKG, String BEAN_NAME, BaseTO bean,
			BaseDao baseDao) throws NoSuchFieldException,
			IllegalAccessException, ClassNotFoundException {
		DetachedCriteria dc = DetachedCriteria.forClass(Bean.class);
		dc.add(Restrictions.eq("pkg", PKG)).add(
				Restrictions.eq("bean", BEAN_NAME));
		Bean b = baseDao.findBean(dc);
		dc = DetachedCriteria.forClass(Property.class);
		dc.add(Restrictions.eq("bean", b));
		List<Property> pl = baseDao.find(dc);
		Class<? extends BaseTO> clazz = bean.getClass();
		for (Property property : pl) {
			String votype = property.getVotype();
			if (Util.notEmptyString(votype)) {
				String propertyName = property.getProperty();
				Field propertyField = clazz.getDeclaredField(propertyName);
				Object propertyObj = propertyField.get(bean);
				if (propertyObj != null) {
					Class<?> propertyClazz = Class.forName(votype);
					Field idField = propertyClazz.getDeclaredField("id");
//					String pid = (String) idField.get(propertyObj);
					if (!Util.notEmptyString(String.valueOf(idField.get(propertyObj)))) {
						propertyField.set(bean, null);
					}
				}
			}
		}
	}

	public static List<NameValuePair> findSelectValue(BaseTO bean,
			BaseDao baseDao) throws NoSuchFieldException,
			IllegalAccessException {
		List<NameValuePair> nl = new ArrayList<NameValuePair>();
		Class<?> clazz = bean.getClass();
		String nameFiledStr = "descr";
		String idFiledStr = "id";
		DetachedCriteria dc = DetachedCriteria.forClass(clazz);
		List<BaseTO> ml = baseDao.find(dc);
		for (BaseTO m : ml) {
			Field nameField = clazz.getDeclaredField(nameFiledStr);
			Field idField = clazz.getDeclaredField(idFiledStr);
			NameValuePair nameValuePair = new NameValuePair();
			nameValuePair.setName((String) nameField.get(m));
			nameValuePair.setValue(String.valueOf(idField.get(m)));
			nl.add(nameValuePair);
		}
		return nl;
	}
	/**
	 * 
	 * @param endTreeValue
	 * @param bean
	 * @param baseDao
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 */
	public static List<String> endToTopPathIds(String endTreeValue,
			BaseTO bean, BaseDao baseDao) throws IllegalArgumentException,
			IllegalAccessException, SecurityException, NoSuchFieldException {
		List<String> res = new ArrayList<String>();
		if("all".equals(endTreeValue)){
			res.add(endTreeValue);
			return res;
		}
		if (Util.notEmptyString(endTreeValue)) {
			Class<? extends BaseTO> clazz = bean.getClass();
			Field parentField = clazz.getDeclaredField("parent");
			BaseTO baseTO = baseDao.get(clazz, Integer.valueOf(endTreeValue));
			Object parentObject = parentField.get(baseTO);

			if (parentObject != null) {

				Field declaredField = clazz.getDeclaredField("id");
				String pid = String.valueOf(declaredField.get(parentObject));
				res.add(pid);

				res.addAll(endToTopPathIds(pid, bean, baseDao));
			}
		}
		return res;
	}
	

	/**
	 * 本方法用于保存时，对于需要默认的字段进行赋值
	 * 只响应=，即赋值操作
	 * 
	 * @param filter
	 * @param baseDao
	 * @param bean
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 */
	public static  void saveFilter(Filter filter,BaseDao baseDao,BaseTO bean) throws NoSuchFieldException,
			IllegalAccessException {
		DetachedCriteria dcf = DetachedCriteria
				.forClass(FilterProperty.class);
		dcf.add(Restrictions.eq("filter", filter));
		dcf.add(Restrictions.in("op", new String[]{"="}));
		List<FilterProperty> fl = baseDao.find(dcf);
		for (FilterProperty f : fl) {
			String op = f.getOp();
			String propertyName = f.getProperty().getProperty();
			Field field = bean.getClass().getDeclaredField(propertyName);
			if ("=".equals(op)) {
				Object filterValue = FilterValue(f.getVal(),
						baseDao);
				field.set(bean, filterValue);
			}
		}
	}

	public static DetachedCriteria createRectiDc(BaseTO bean, String className,
			String psid) throws SecurityException, NoSuchFieldException,
			ClassNotFoundException, IllegalArgumentException,
			IllegalAccessException {
		return createRectiDc("", "", bean, className, psid);
	}

	/**
	 * 自带搜索条件的dc创建方法
	 * 
	 * @param sortx
	 * @param orderx
	 * @param bean
	 * @param className
	 * @return
	 */
	public static DetachedCriteria createRectiDc(String sortx, String orderx,
			BaseTO bean, String className, String psid) throws SecurityException,
			NoSuchFieldException, ClassNotFoundException,
			IllegalArgumentException, IllegalAccessException {
		Class<?> clazz = Class.forName(className);
		List<String> sl = new ArrayList<String>();// 用于检测是否会发生duplicate alias错误
		DetachedCriteria dc = DetachedCriteria.forClass(clazz);
		if (Util.notEmptyString(sortx) && !sortx.contains(".")) {// !sortx.contains(".")
																	// ：在包含点号时，无法用其进行排序
			if ("desc".equals(orderx)) {
				dc.addOrder(Order.desc(sortx));
			} else {
				dc.addOrder(Order.asc(sortx));
			}
			dc.addOrder(Order.asc("id"));
		}
		// data filter

		if (bean != null) {
			List<PerfProperty> pps = Util.findPpsBySearch(psid);
			String beanValue = "";
			for (PerfProperty pp : pps) {
				beanValue = "";
				Property property = pp.getProperty();
				String field = property.getProperty();
				int indexOf = field.indexOf(".");
				Class<?> type = null;
				String f1 = "";
				String child = "";
				if (indexOf >= 0) {
					child = field.substring(indexOf + 1, field.length());
					f1 = field.substring(0, indexOf);
					Field declaredField = clazz.getDeclaredField(f1);
					Class<?> f1type = declaredField.getType();
					BaseTO mem = (BaseTO) declaredField.get(bean);
					if (mem != null) {
						Field childField = f1type.getDeclaredField(child);
						type = childField.getType();
						beanValue = String.valueOf(childField.get(mem));
					}
				} else {
					Field declaredField = clazz.getDeclaredField(field);
					type = declaredField.getType();
					beanValue = String.valueOf(declaredField.get(bean));
				}
				if (Util.notEmptyString(beanValue)) {
					String s1 = beanValue.substring(1);
					if (beanValue.startsWith("=")) {
						dc.add(Restrictions.eq(field, s1));
					} else if (beanValue.startsWith(">")) {
						dc.add(Restrictions.gt(field, s1));
					} else if (beanValue.startsWith(">=")) {
						dc.add(Restrictions.ge(field, beanValue.substring(2)));
					} else if (beanValue.startsWith("<")) {
						dc.add(Restrictions.lt(field, s1));
					} else if (beanValue.startsWith("<=")) {
						dc.add(Restrictions.le(field, beanValue.substring(2)));
					} else if (beanValue.startsWith("%")) {
						dc.add(Restrictions.ilike(field, s1, MatchMode.END));
					} else if (beanValue.endsWith("%")) {
						dc.add(Restrictions.ilike(field,
								beanValue.substring(0, beanValue.length() - 1),
								MatchMode.START));
					} else {
						if (type != null) {
							if (type.equals(int.class)) {
								Integer valueOf = Integer.valueOf(beanValue);
								if (indexOf >= 0 && valueOf == 0) {
									// TODO 如果是donothing
								} else {
									dc.add(Restrictions.eq(field, valueOf));
								}
							} else {
								Myinput input = JspUtil2.propertyMyinput(pp, pp.getProperty());
								if (input != null
										&& input.getType().endsWith("date")) {
									// 对date进行情况判断
									if (beanValue.contains(" - ")) {
										// 时间阶段统计
										String[] ds = beanValue.split(" - ");
										dc.add(Restrictions.ge(field, ds[0]))
												.add(Restrictions.le(field,
														ds[1] + " 23:59:59"));
									} else {
										// 单天时间
										dc.add(Restrictions
												.ge(field, beanValue)).add(
												Restrictions
														.le(field, beanValue
																+ " 23:59:59"));
									}
								} else if (input != null
										&& (input.getType().equals("select")||input.getType().equals("tree"))) {
									dc.add(Restrictions.eq(field, beanValue));
									
								}else {
									if (indexOf > 0) {
										if (!sl.contains(f1)) {
											dc.createAlias(f1, f1);
											sl.add(f1);
										}

									}
									dc.add(Restrictions.ilike(field, beanValue,
											MatchMode.ANYWHERE));
								}
							}
						}
					}
				}
			}
		}
		return dc;
	}

	/**
	 * 用于查询
	 * 比较对象不用分解到其ID
	 * 
	 * @param dc
	 * @param filter
	 * @param baseDao
	 */
	public static void createFilterDc(DetachedCriteria dc, Filter filter,
			BaseDao baseDao) {
		if (filter != null) {
			DetachedCriteria dcf = DetachedCriteria
					.forClass(FilterProperty.class);
			dcf.add(Restrictions.eq("filter", filter));
			dcf.add(Restrictions.in("op", new String[]{"==","in"}));
			List<FilterProperty> fl = baseDao.find(dcf);
			// data filter
			for (FilterProperty fp : fl) {
				String op = fp.getOp();
				String clazzStr = fp.getClazz();
				// Class<?> clazzf = Class.forName(clazzStr);
				String propertyName = fp.getProperty().getProperty();
				if ("in".equals(op)) {
					dc.add(Restrictions.in(propertyName,
							(List) FilterValue(fp.getVal(), baseDao)));
				} else if ("==".equals(op)) {
					dc.add(Restrictions.eq(propertyName,
							FilterValue(fp.getVal(), baseDao)));
				}
			}
		}
	}
/**
 * 多个filter是或者关系
 * @param dc
 * @param fs
 * @param baseDao
 */
	public static void createFilterDc2(DetachedCriteria dc, List<FilterProperty> fs,
			BaseDao baseDao) {
		if (fs != null) {
			
			for (FilterProperty fp : fs) {
				String op = fp.getOp();
				String clazzStr = fp.getClazz();
				// Class<?> clazzf = Class.forName(clazzStr);
				String propertyName = fp.getProperty().getProperty();
				if ("in".equals(op)) {
					dc.add(Restrictions.in(propertyName,
							(List) FilterValue(fp.getVal(), baseDao)));
				} else if ("==".equals(op)) {
					dc.add(Restrictions.eq(propertyName,
							FilterValue(fp.getVal(), baseDao)));
				}
			}
		}
	}

	/**
	 * 针对不同的参数值，给出对应的结果集
	 * 用于filter数据过滤、增加页面中的默认数据
	 * @param val
	 * @param baseDao
	 * @return
	 */
	public static Object FilterValue(String val, BaseDao baseDao) {
		Object res =null;
		if (val.equals(":currentOrgAndUnderIds")) {
			res = currentOrgAndUnderIds(baseDao);
		} else if (val.equals(":currentOrgAndUnder")) {
			res = currentOrgAndUnder(baseDao);
		}else if (val.equals(":currentMemberId")) {
			res = findCurrentMemberId();
		}else if (val.equals(":currentMember")) {
			res = findCurrentMember();
		}else if(val.equals(":currentCompSingle")) {
			res = findCurrentCompSingle();
		}else if(val.equals(":currentCompSingleId")) {
			res = findCurrentCompSingleId();
		}
		if (val.equals(":currentDate")) {
			res = DateTimeUtils.formatDate(
					new Timestamp(System.currentTimeMillis()), "yyyy-MM-dd");
		}
		return res;
	}

	public static Object findCurrentMember() {
		ActionContext ctx = ActionContext.getContext();
		Map session = ctx.getSession();
		Member member = (Member) session.get(Constant.SESSION_MEMBER);
		return member;
	}

	private static List<Org> currentOrgAndUnder(BaseDao baseDao) {
		List<Org> ol = new ArrayList<Org>();
		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ctx
				.get(StrutsStatics.HTTP_REQUEST);
		Member mem = (Member) request.getAttribute(Constant.SESSION_MEMBER);
		Org roleorg = mem.getOrg();
		ol.addAll(orgAndUnder(baseDao, roleorg));
		return ol;
	}

	private static String findCurrentCompSingleId() {
		ActionContext ctx = ActionContext.getContext();
		Map session = ctx.getSession();
		Member member = (Member) session.get(Constant.SESSION_MEMBER);
		return String.valueOf(member.getOrg().getId());
	}

	public static List<String> currentOrgAndUnderIds(BaseDao baseDao) {
		List<String> res =new ArrayList<String>();
		List<Org> ol = currentOrgAndUnder(baseDao);
		for(Org o:ol){
			res.add(String.valueOf(o.getId()));
		}
		return res;
	}

	public static List<Org> orgAndUnder(BaseDao baseDao, Org roleorg) {
		List<Org> res = new ArrayList<Org>();
		if (roleorg != null) {
			res.add(roleorg);
			DetachedCriteria dc = DetachedCriteria.forClass(Org.class);
			dc.add(Restrictions.eq("parent", roleorg));
			List<Org> ol = baseDao.find(dc);
			for (Org o : ol) {
				res.addAll(orgAndUnder(baseDao, o));
			}
		}
		return res;
	}

	public static String findCurrentMemberId() {
		ActionContext ctx = ActionContext.getContext();
		Map session = ctx.getSession();
		Member member = (Member) session.get(Constant.SESSION_MEMBER);
		return String.valueOf(member.getId());
	}

	public static Org findCurrentCompSingle() {
		ActionContext ctx = ActionContext.getContext();
		Map session = ctx.getSession();
		Member member = (Member) session.get(Constant.SESSION_MEMBER);
		return member.getOrg();
	}

	/**
	 * 未对重复的att做判断
	 * @param baseDao
	 * @param member
	 * @return
	 */
	public static  List<String> findMyOrgsAtts(BaseDao baseDao, Member member) {
		List<Org> myOrgs = Util.findMyOrgs(baseDao, member);
		List<String> atts=new ArrayList<String>();
		for(Org o : myOrgs){
			if(Util.notEmptyString(o.getAtt1())){
				atts.add(o.getAtt1());
			}
			if(Util.notEmptyString(o.getAtt2())){
				atts.add(o.getAtt2());
			}
			if(Util.notEmptyString(o.getAtt3())){
				atts.add(o.getAtt3());
			}
		}
		return atts;
	}

	public static List<Org> findMyOrgs(BaseDao baseDao, Member member) {
		List<Org> res = new ArrayList<Org>();
		DetachedCriteria dc = DetachedCriteria.forClass(OrgMember.class);
		dc.add(Restrictions.eq("member", member)).add(Restrictions.eq("enable", "1"));
		List<OrgMember> ol = baseDao.find(dc);
		for(OrgMember om:ol){
			res.add(om.getOrg());
		}
		return res;
	}
	


	public static Field findField(Class<?> clazz, String fieldName) {
		Field[] declaredFields = clazz.getDeclaredFields();
		for (Field f : declaredFields) {
			if (f.getName().equals(fieldName))
				return f;

		}
		return null;
	}
	



	public static List<NameValuePair> nvlist(BaseDao baseDao2, String domain2) {
		List<NameValuePair> nl = new ArrayList<NameValuePair>();
		nl.add(new NameValuePair("", ""));
		if(Util.notEmptyString(domain2)){
			nl.addAll(baseDao2.findConstantSel(domain2));
		}
		return nl;
	}

	public static Bean findBeanByClass(String className) {
		for(Bean b:Constant.BEANS){
			if((b.getPkg()+".vo."+b.getBean()).equals(className)
					){
				return b;
			}
		}
		return null;
	}

//	public static String pkgAct(String pkg, String str, String beanName) {
//		return "/"+pkg+"/"+str+beanName;
//	}
}
