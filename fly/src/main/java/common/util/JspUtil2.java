package common.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cfg.vo.Bean;
import cfg.vo.Cube;
import cfg.vo.CubeTd;
import cfg.vo.Filter;
import cfg.vo.FilterProperty;
import cfg.vo.Member;
import cfg.vo.Myinput;
import cfg.vo.Org;
import cfg.vo.Perf;
import cfg.vo.PerfProperty;
import cfg.vo.Permission;
import cfg.vo.Property;
import cfg.vo.Quote;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import common.dao.BaseDao;
import common.vo.AsyncTree;
import common.vo.BaseTO;
import common.vo.FootPerf;
import common.vo.NameValuePair;

public class JspUtil2 {

	public static final String ROWNOTSELECTED_TRIP = "请在表格中选中需要操作的一行！";
	public static final String PERFS_NAME = "ps";
	public static final String LIST_TABLE_NAME = "df_tableName";
	private static final String LIST_JS_NAME = "df_sear()";
	public static final String LIST_TOOLBAR_NAME = "df_tb";
	private static final String DEL_JS_NAME = "df_del()";
	private static final String ADD_JS_NAME = "df_add()";
	private static final String COPY_JS_NAME = "df_copy()";
	public static final String ADD_EDIT_TABLE = "<table class='horTable' border='1px;'>";
	private static final String EXPORT_XLS_JS_NAME = "df_export_xls()";
	public static final String EXPORT_SINGLE_JS_NAME = "df_export_single()";
	private static final String DF_DESCR = "DF_DESCR";// edit表中的id对应描述。add表中的pop页面返回值

	public static final String DOWNLOAD_FILE = "downloadTemp";

	/**
	 * List数据的表格
	 * 
	 * @param name
	 * @return
	 */
	public static StringBuilder findListTable(String... name) {
		StringBuilder res = new StringBuilder();
		res.append("<table id='")
				.append(name.length == 0 ? LIST_TABLE_NAME : name[0])
				.append("'></table><input type=\"hidden\" id=\"ids\">");
		// res.append("<input type='hidden' name='ids' id='ids'/>");
		return res;
	}

	/**
	 * List页面中的工具栏
	 * 
	 * @param className
	 * @param from
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */

	public static StringBuilder findToolBar(StringBuilder plainToolbar,
			StringBuilder custFilter,  String psid, BaseTO bean)
			throws ClassNotFoundException, IllegalArgumentException,
			IllegalAccessException, SecurityException, NoSuchFieldException {
		return findToolBar(plainToolbar, custFilter, psid, bean, "");
	}

	public static StringBuilder findToolBar(StringBuilder plainToolbar,
			StringBuilder custFilter, String psid, BaseTO bean,
			String quotePropertyId) throws ClassNotFoundException,
			IllegalArgumentException, IllegalAccessException,
			SecurityException, NoSuchFieldException {
		StringBuilder orgfilter = new StringBuilder();
		List<PerfProperty> pps = Util.findPpsBySearch(psid);
		for (PerfProperty pp : pps) {
			final Property property = pp.getProperty();
			String[] beanValue = filedVal(bean, pp);
			if (property.getId().equals(quotePropertyId)) {
				// 如果这个属性就是引用页面的关联属性，则在搜索栏加入其hiddenValue
				pp.setEditshowhide("0");
				// 找到关联属性的值
			}
			orgfilter.append(findInput(pp, beanValue,"toolbar",false)).append("&nbsp;");
		}
		StringBuilder res = new StringBuilder("<div id='").append(
				LIST_TOOLBAR_NAME).append("'>");
		StringBuilder filter = new StringBuilder(
				"<div style='margin-bottom:5px'>");

		
		filter.append(custFilter).append(orgfilter);
		
		filter.append("</div>");
		// StringBuilder plain = plainToolbarHtml();
		res.append(plainToolbar).append(filter).append("</div>");
		return res;
	}

	private static String propertyDftvalue(PerfProperty pp,
			final Property property) {
		String dftvalue = property.getDftvalue();
		if (Util.notEmptyString(pp.getDftvalue())) {
			dftvalue = pp.getDftvalue();
		}
		return dftvalue;
	}

	/**
	 * List页面中的工具栏
	 * 
	 * @param className
	 * @param from
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	public static StringBuilder findToolBar(StringBuilder plainToolbar,
			StringBuilder custFilter, String psid)
			throws ClassNotFoundException, IllegalArgumentException,
			IllegalAccessException, SecurityException, NoSuchFieldException {
		return findToolBar(plainToolbar, new StringBuilder(), psid, null);
	}

	/**
	 * 常规按钮
	 * @param qb 
	 * @param from 
	 * @param bean 
	 * @param pid 
	 * 
	 * @return
	 * @throws IllegalAccessException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 */
	public static StringBuilder plainToolbarHtml(String from, Quote qb, BaseTO bean, String pid) throws SecurityException,
			IllegalArgumentException, NoSuchFieldException,
			IllegalAccessException {
		StringBuilder plain = toolBarHtml(from,qb,bean,pid);
		plain.append("</div>");
		return plain;
	}

	private static StringBuilder toolBarHtml(String from, Quote qb, BaseTO bean, String pid)
			throws SecurityException, IllegalArgumentException,
			NoSuchFieldException, IllegalAccessException {
		StringBuilder plain = new StringBuilder(
				"<div style='margin-bottom:5px'>");
		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ctx
				.get(StrutsStatics.HTTP_REQUEST);
		@SuppressWarnings("unchecked")
		List<Permission> buttonPermission = (List<Permission>) request
				.getAttribute("buttonPermission");
		@SuppressWarnings("unchecked")
		List<Permission> specialPermission = (List<Permission>) request
				.getAttribute("specialPermission");
		for (Permission p : buttonPermission) {
			String actionName = p.getActionName();
			String jsName = "jsm_" + actionName + "()";
			if (actionName.startsWith("detail")) {
				plain.append(
						"<a href='javascript:void(0)' class='easyui-linkbutton' data-options='plain:true' onclick='")
						.append(jsName).append("'>详细</a>");
				// plain.append("<a href='javascript:void(0)' class='easyui-linkbutton' data-options='iconCls:\"icon-search\",plain:true' onclick='")
				// .append(jsName).append("'>详细</a>");
				plain.append("<script>"
						+ openUrlByOneRowJs(jsName, permissionUrl(p))
						+ "</script>");
			} else if (actionName.startsWith("del")) {
				plain.append(
						"<a href='javascript:void(0)' class='easyui-linkbutton' data-options='plain:true' onclick='")
						.append(DEL_JS_NAME).append("'>删除</a>");
				plain.append("<script>" + findDelJs(permissionUrl(p))
						+ "</script>");
			} else if (actionName.startsWith("add")) {
				plain.append(
						"<a href='javascript:void(0)' class='easyui-linkbutton' data-options='plain:true' onclick='")
						.append(ADD_JS_NAME).append("'>增加</a>");
				plain.append("<script>" + findAddJs(permissionUrl(p),qb, bean)
						+ "</script>");
				plain.append(
						"<a href='javascript:void(0)' class='easyui-linkbutton' data-options='plain:true' onclick='")
						.append(jsName).append("'>复制</a>");
				plain.append("<script>" + openUrlByOneRowJs(jsName,permissionUrl(p))
						+ "</script>");
			} else if (actionName.startsWith("edit")) {
				plain.append(
						"<a href='javascript:void(0)' class='easyui-linkbutton' data-options='plain:true' onclick='")
						.append(jsName).append("'>修改</a>");
				plain.append("<script>"
						+ openUrlByOneRowJs(jsName, permissionUrl(p))
						+ "</script>");
			} else if (actionName.startsWith("c_")) {
				plain.append(
						"<a href='javascript:void(0)' class='easyui-linkbutton' data-options='plain:true' onclick='")
						.append(jsName).append("'>").append(p.getDescr())
						.append("</a>");
				plain.append("<script>"
						+ openUrlByOneRowJs(jsName, permissionUrl(p))
						+ "</script>");
			}
		}
		for (Permission p : specialPermission) {
			String actionName = p.getActionName();
			String jsName = "jsm_" + actionName + "()";
			if (actionName.equals("c_Perf_editUnder")) {
				plain.append(
						"<a href='javascript:void(0)' class='easyui-linkbutton' data-options='plain:true' onclick='")
						.append(jsName).append("'>"+p.getDescr()+"</a>");
				plain.append("<script>"
						+ c_editUnderPerf_js(jsName, permissionUrl(p),pid)
						+ "</script>");
			}
		}
		plain.append("<a href='javascript:void(0)' class='easyui-linkbutton' data-options='plain:true' onclick='window.print();'>打印</a>");
		plain.append(
				"<a href='javascript:void(0)' class='easyui-linkbutton' data-options='plain:true' onclick='")
				.append(EXPORT_XLS_JS_NAME).append("'>导出</a>");
		plain.append("<a href='javascript:void(0)' onclick='javascript:")
			.append(LIST_JS_NAME)
			.append("' class='easyui-linkbutton'  data-options='plain:true'><i class=\"icon-search\"></i>搜索</a>");

		plain.append("<a class='easyui-linkbutton'  data-options='plain:true' onclick='javascript:window.document.location.reload()'><i class=\"icon-repeat\"></i>刷新</a>");
		if (Util.notEmptyString(from)) {
			StringBuilder fs = new StringBuilder();
			fs.append("<a class='easyui-linkbutton' href='javascript:void(0)'  onclick='df_cls()'>带值返回</a>");
			plain.append(fs);
		}
		return plain;
	}

	private static StringBuilder c_editUnderPerf_js(String jsName, String url, String pid) {
		StringBuilder res = new StringBuilder();
		res.append("function ").append(jsName).append("{window.open('")
		.append(url).append("&bean.id="+pid+"','_blank');}");
		return res;
	}

	public static String permissionUrl(Permission p) {
		return p.getNamespace() + "_" + p.getActionName() + Constant.URL_SUFFIX
				+ Constant.PAGE_PARAM;
	}

	/**
	 * 查找主BeanList的Js方法
	 * 
	 * @param name
	 *            赋值表格名称
	 * @param url
	 *            beanList的数据链接
	 * @param className
	 *            bean的clazz
	 * @param custParams
	 * @param from
	 * @return
	 */
	public static StringBuilder findSearJs(String url, String psid)
			throws SecurityException, ClassNotFoundException,
			IllegalArgumentException, IllegalAccessException,
			InstantiationException, NoSuchFieldException {
		return findSearJs("", url, "", psid);
	}
	public static StringBuilder findSearJs(String url, String custParams,String psid)
			throws SecurityException, ClassNotFoundException,
			IllegalArgumentException, IllegalAccessException,
			InstantiationException, NoSuchFieldException {
		return findSearJs("", url, custParams, psid);
	}

	public static StringBuilder findSearJs(String name, String url,
			String custParams, String psid) throws SecurityException,
			ClassNotFoundException, IllegalArgumentException,
			IllegalAccessException, InstantiationException,
			NoSuchFieldException {
		if (!Util.notEmptyString(name)) {
			name = LIST_TABLE_NAME;
		}
		StringBuilder res = new StringBuilder();
		StringBuilder cols = new StringBuilder();
		StringBuilder params = new StringBuilder();
		List<PerfProperty> pps = Util.findPpsByList(psid);
//		cols.append("{field:'ck',checkbox:true},");
		for (PerfProperty pp : pps) {
			Property property = pp.getProperty();
			String field = property.getProperty();
			String esh = pp.getEditshowhide();
			String fom = "";
			String hid = "";
			int indexOf = field.indexOf(".");
			if (indexOf >= 0) {
				String child = field.substring(indexOf + 1, field.length());
				String f1 = field.substring(0, indexOf);
				fom = ",formatter: function(value,rec){if(rec." + f1
						+ "==null){return '';}return rec." + f1 + "['" + child
						+ "']}";
			}
			if ("0".equals(esh)) {
				hid = ",hidden:true";
			}
			String width = propertyWidth(pp, property);
			StringBuilder wsb = new StringBuilder();
			if (Util.notEmptyString(width)) {
				wsb.append(",width:").append(width);
			}
			cols.append("{field:'").append(field).append("'").append(wsb)
					.append(",sortable:true,title:'")
					.append(property.getDescr()).append("'").append(fom)
					.append(hid).append("},");
		}
		List<PerfProperty> spps = Util.findPpsBySearch(psid);
		for (PerfProperty pp : spps) {
			// 参数
//			String chk = "";
//			Myinput myinput = pp.getMyinput();
//			if (myinput != null && "radio".equals(myinput.getType())) {
//				chk = ":checked";
//			}
			// 不管searchPerf中的Property是哪一种editshowhide，都应该被视为搜索条件的一个
			Property property = pp.getProperty();
			String field = property.getProperty();
			params.append("'bean.").append(field).append("':")
					.append("$('input[name=\"bean.").append(field)
					.append("\"]').val(),");
		}
		// params.append("'isPage':'1',");
		if (Util.notEmptyString(cols.toString())) {
			cols.deleteCharAt(cols.length() - 1);
		}
		params.append(custParams);
		if (Util.notEmptyString(params.toString())) {
			params.deleteCharAt(params.length() - 1);
		}
		res.append("function ")
				.append(LIST_JS_NAME)
				.append("{$('#")
				.append(name)
				.append("').datagrid({toolbar:'#")
				.append(LIST_TOOLBAR_NAME)
				.append("',pageList:[100,50,20,10],onLoadSuccess:function(data){var rows=data['rows'];var len = rows.length;var ind = 0;var ids='';while(ind<len){var r=rows[ind];ids+=r['id']+',';ind++;}$('#ids').val(ids);}," +
						"showFooter: true,nowrap: false,pageSize:'10',singleSelect:true,fit:true,pagination:true,rownumbers:true,checkOnSelect:false,selectOnCheck:false,url:'")
				.append(url).append("',queryParams:{").append(params)
				.append("},columns:[[").append(cols).append("]]});}");
		return res;
	}

	private static String propertyWidth(PerfProperty pp, Property property) {
		String width = property.getWidth();
		if (Util.notEmptyString(pp.getWidth())) {
			width = pp.getWidth();
		}
		return width;
	}

	/**
	 * 对List的foot进行通用方法设定（只有一个返回foot的情况，遇到多个的情况，建议拆分成一个个的fpl再合成一个footlist）
	 * 
	 * @param cl
	 *            Listのvalue
	 * @param fpl
	 *            FootPerfのList 要显示什么样的Foot
	 * @param className
	 *            Foot属于那个class
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static List<BaseTO> findFoot(List<BaseTO> cl, List<FootPerf> fpl,
			String className) throws ClassNotFoundException, SecurityException,
			NoSuchFieldException, IllegalArgumentException,
			IllegalAccessException, InstantiationException {
		// data 准备
		List<BaseTO> res = new ArrayList<BaseTO>();
		Class<?> footClass = Class.forName(className);
		BaseTO foot = (BaseTO) footClass.newInstance();
		res.add(foot);
		// 统计数据的合集
		List<Double> dl = new ArrayList<Double>();
		for (FootPerf f : fpl) {
			dl.add(Double.valueOf(0));
		}
		// 根据Listvalue做数据统计，塞到对应的数据合集dl中
		for (BaseTO m : cl) {
			int ind = 0;
			for (FootPerf f : fpl) {
				Field field = footClass.getDeclaredField(f.getField());
				Object fieldValue = field.get(m);
				Double fieldValued = 0d;
				if(fieldValue!=null){
					String fieldStr = fieldValue.toString();
					if(Util.notEmptyString(fieldStr)){
						fieldValued = Double.valueOf(fieldStr);
					}
				}
				Double dl1 = dl.get(ind);
				dl1 += fieldValued;
				dl.set(ind++, dl1);
			}
		}

		// 有了数据的合计后，再对数据进行再一次标准化封装，塞到foot中
		int ind = 0;
		for (FootPerf f : fpl) {
			String kind = f.getKind();
			Field descrField = footClass.getDeclaredField(f.getField());
			Double dl1 = dl.get(ind++);
			if ("AVG".equals(kind)) {
				int size = cl.size();
				if (size > 0) {
					dl1 = dl1 / size;
				}
			}

			String format = Util.getNumberFormat(f.getMaximum()).format(dl1);
			descrField.set(foot, format);
		}
		return res;
	}

	/**
	 * 根据List内容 画出一个List的tableHTML
	 * 
	 * @param className
	 * @param psid
	 * @return
	 * @throws ClassNotFoundException
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 * @throws UnsupportedEncodingException
	 */
	public static InputStream drawXls(String className, String psid,
			List<BaseTO> cl) throws ClassNotFoundException,
			NoSuchFieldException, IllegalAccessException,
			UnsupportedEncodingException {
		StringBuilder tableHtmlBuilder = new StringBuilder();
		tableHtmlBuilder
				.append("<html><head><style type='text/css'>td {mso-number-format:\"\\@\";}h1 {font-family: Arial, sans-serif;font-size: 24px;margin: 20px;color: #369;padding-bottom: 4px;border-bottom: 1px solid #999;text-align:center}h3 {margin-left: 20px;color:#0099FF;}table{border-collapse: collapse;font-family: 'Lucida Sans Unicode','Lucida Grande',Sans-Serif;font-size: 12px;margin: 20px;text-align: left;width: 97%};td {padding: 8px;}</style></head><body>");
		tableHtmlBuilder.append("<table border='1' class='horTable'>");
		Class<?> clazz = Class.forName(className);
		tableHtmlBuilder.append("<tr>");
		List<PerfProperty> pps = Util.findPpsByList(psid);
		for (PerfProperty pp : pps) {
			tableHtmlBuilder.append("<td>").append(pp.getProperty().getDescr())
					.append("</td>");
		}
		tableHtmlBuilder.append("</tr>");
		for (BaseTO c : cl) {
			tableHtmlBuilder.append("<tr>");
			for (PerfProperty pp : pps) {
				String beanValue = "";
				String field = pp.getProperty().getProperty();
				int indexOf = field.indexOf(".");
				if (indexOf >= 0) {
					String child = field.substring(indexOf + 1, field.length());
					String f1 = field.substring(0, indexOf);
					Field declaredField = clazz.getDeclaredField(f1);
					Class<?> type = declaredField.getType();
					BaseTO mem = (BaseTO) declaredField.get(c);
					if (mem != null) {
						Field childField = type.getDeclaredField(child);
						beanValue = String.valueOf(childField.get(mem));
					}
				} else {
					Field declaredField = clazz.getDeclaredField(field);
					beanValue = String.valueOf(declaredField.get(c));
				}
				tableHtmlBuilder.append("<td>").append(beanValue)
						.append("</td>");
			}
			tableHtmlBuilder.append("</tr>");
		}
		tableHtmlBuilder.append("</table></body></html>");
		byte b[] = tableHtmlBuilder.toString().getBytes();
		return new ByteArrayInputStream(b);
	}

	public static StringBuilder findexportXlsJs(String url, String psid)
			throws SecurityException, NoSuchFieldException,
			ClassNotFoundException, IllegalArgumentException,
			IllegalAccessException {
		return findexportXlsJs(url, "", psid);
	}

	public static StringBuilder findexportXlsJs(String url, String custParams,
			String psid) throws SecurityException, NoSuchFieldException,
			ClassNotFoundException, IllegalArgumentException,
			IllegalAccessException {
		StringBuilder res = new StringBuilder();
		StringBuilder params = new StringBuilder();
		List<PerfProperty> spps = Util.findPpsBySearch(psid);
		for (PerfProperty pp : spps) {
			// 参数
//			String chk = "";
//			Myinput myinput = propertyMyinput(pp, pp.getProperty());
//			if (myinput != null && "radio".equals(myinput.getType())) {
//				chk = ":checked";
//			}
			// 不管searchPerf中的Property是哪一种editshowhide，都应该被视为搜索条件的一个
			Property property = pp.getProperty();
			String field = property.getProperty();
			params.append("'bean.").append(field).append("':")
					.append("$('input[name=\"bean.").append(field)
					.append("\"]').val(),");
		}

		params.append(custParams);
		if (Util.notEmptyString(params.toString())) {
			params.deleteCharAt(params.length() - 1);
		}
		res.append("function ")
				.append(EXPORT_XLS_JS_NAME)
				.append("{$.post('")
				.append(url)
				.append("',{")
				.append(params)
				.append("},function(json){window.open('download.sg?exportUrl='+json)},'JSON');}");
		return res;
	}

	/**
	 * 导出一个
	 * 
	 * @param url
	 * 
	 * @param string
	 * @return
	 */
	public static StringBuilder findExportSingleJs(String name, String url) {
		if (!Util.notEmptyString(name)) {
			name = LIST_TABLE_NAME;
		}
		StringBuilder res = new StringBuilder();
		res.append("function ")
				.append(EXPORT_SINGLE_JS_NAME)
				.append("{var row = $('#")
				.append(name)
				.append("').datagrid('getSelected');if(row){var id = row.id;$.post('")
				.append(url)
				.append("',{'bean.id':id},function(json){window.open('download.sg?exportUrl='+json)},'JSON');}else{alert('")
				.append(ROWNOTSELECTED_TRIP).append("');}}");
		return res;
	}

	/**
	 * 在页面初始化时调用LIST_JS_NAME方法
	 * 
	 * @return
	 */
	public static StringBuilder findSearInitJs() {
		StringBuilder res = new StringBuilder();
		res.append("$(function(){").append(LIST_JS_NAME).append(";});");
		return res;
	}

	/**
	 * 在页面初始化时调用LIST_JS_NAME方法
	 * 
	 * @return
	 */
	public static StringBuilder findLaterSearInitJs() {
		StringBuilder res = new StringBuilder();
		res.append("$(function(){setTimeout(\"").append(LIST_JS_NAME)
				.append(";\",500);});");
		return res;
	}

	/**
	 * 新增一个Bean方法
	 * 
	 * 弹出一个新页面
	 * 
	 * @param url
	 * @return
	 * @throws IllegalAccessException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 */
	public static StringBuilder findAddJs(String url) throws SecurityException,
			IllegalArgumentException, NoSuchFieldException,
			IllegalAccessException {
		return findAddJs(url, null, null);
	}

	public static StringBuilder findAddJs(String url, Quote qb, BaseTO bean)
			throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		StringBuilder res = new StringBuilder();
		if (qb != null) {
			url += "&quote=" + qb.getId();
			if (bean != null) {
				final Class<? extends BaseTO> clazz = bean.getClass();
				String beanValue = "";
				// 找到关联属性的值
				final String propertyName = qb.getQuoteProperty().getProperty();
				int indexOf = propertyName.indexOf(".");
				if (indexOf >= 0) {
					String child = propertyName.substring(indexOf + 1,
							propertyName.length());
					String f1 = propertyName.substring(0, indexOf);
					Field declaredField = clazz.getDeclaredField(f1);
					Class<?> paramClazz = declaredField.getType();
					BaseTO mem = (BaseTO) declaredField.get(bean);
					if (mem != null) {
						Field childField = paramClazz.getDeclaredField(child);
						beanValue = String.valueOf(childField.get(mem));
					}
				} else {
					Field declaredField = clazz.getDeclaredField(propertyName);
					beanValue = String.valueOf(declaredField.get(bean));
				}
				url += "&bean." + qb.getQuoteProperty().getProperty() + "="
						+ beanValue;
			}
		}
		res.append("function ").append(ADD_JS_NAME).append("{window.open('")
				.append(url).append("','_blank');}");
		return res;
	}

	/**
	 * 弹出框通用JS pop():弹出，并接受返回值，返回值本身不能含有"," pop_clear():清空值
	 * 
	 * field url 弹出框链接 返回值格式【aaa,bbb】 name为text_field赋值aaa，表现文本
	 * name为field赋值bbb，提交文本
	 * 
	 * @return
	 */
	public static StringBuilder findPopJs() {
		StringBuilder res = new StringBuilder();
		res.append("function pop(field,url){digStr='dialogHeight:600px;dialogWidth:700px;center:yes';var ReturnValue = window.showModalDialog(url,'',digStr);if(ReturnValue!=null&&ReturnValue!=''&&ReturnValue!='null'){var ind = ReturnValue.indexOf(',');var text=ReturnValue.substring(0,ind);var value=ReturnValue.substring(ind+1,ReturnValue.length);var ft =$('input[name=\"text_'+field+'\"]');var fv =$('input[name=\"'+field+'\"]');ft.val(text);fv.val(value);}}");
		res.append("function pop_clear(field){var ft =$('input[name=\"text_'+field+'\"]');var fv =$('input[name=\"'+field+'\"]');ft.val('');fv.val('');}");
		return res;
	}

	/**
	 * 添加bean页面通用表行结构 根据classname 找到表现类
	 * 
	 * @param className
	 * @param string
	 * @return
	 */
	public static StringBuilder findAddForm(String addUrl, BaseTO bean, Perf perf, String quotePropertyId)
			throws ClassNotFoundException, IllegalArgumentException,
			IllegalAccessException, SecurityException, NoSuchFieldException {
		return buildAddForm(bean, perf, false, quotePropertyId, addUrl, null);
	}

//	/**
//	 * 需要提交附件时使用
//	 * 
//	 * @param addUrl
//	 * @param bean
//	 * @param enctype
//	 * @return
//	 */
//	public static StringBuilder findAddForm(String addUrl, BaseTO bean,
//			String enctype, int psid) throws ClassNotFoundException,
//			IllegalArgumentException, IllegalAccessException,
//			SecurityException, NoSuchFieldException {
//		return buildAddForm(bean, psid, false, 0, addUrl, enctype);
//	}
//
//	public static StringBuilder findAddForm(String addUrl, BaseTO bean,
//			int psid, int quotePropertyId) throws ClassNotFoundException,
//			IllegalArgumentException, IllegalAccessException,
//			SecurityException, NoSuchFieldException {
//		return buildAddForm(bean, psid, false, quotePropertyId, addUrl, null);
//	}
//
//	/**
//	 * 需要提交附件的流程状态使用
//	 * 
//	 * @param addUrl
//	 * @param bean
//	 * @param enctype
//	 * @return
//	 */
//	public static StringBuilder findAddForm(String addUrl, BaseTO bean,
//			String enctype, int psid, Boolean ifFlag, String beanId, String ids)
//			throws ClassNotFoundException, IllegalArgumentException,
//			IllegalAccessException, SecurityException, NoSuchFieldException {
//		return buildAddForm(bean, psid, ifFlag, 0, addUrl, enctype);
//	}

	/**
	 * 创建form的头
	 * 
	 * @param addUrl
	 * @param pps
	 * @return
	 */
	private static StringBuilder formTitle(String addUrl, List<PerfProperty> pps) {
		StringBuilder res = new StringBuilder();
		res.append("<form id='df_form' ");
//		res.append("<form id='df_form' class=\"form-horizontal form-actions\"");
		boolean isEnctype = false;
		if(Util.notEmptyList(pps)){
			for(PerfProperty pp:pps
					){
				Myinput myinput = pp.getMyinput();
				if(myinput==null)
					myinput=pp.getProperty().getMyinput();
				if(myinput!=null&&"file".equals(myinput.getType())){
					isEnctype=true;
					break;
				}
			}
		}
		if (isEnctype) {
			res.append(" enctype='multipart/form-data' ");
		}
		res.append("method='POST' action='").append(addUrl).append("'>");
		return res;
	}
	

	private static StringBuilder buildAddForm(BaseTO bean, Perf perf,
			boolean isFlow, String quotePropertyId, String url, String type)
			throws ClassNotFoundException, SecurityException,
			NoSuchFieldException, IllegalArgumentException,
			IllegalAccessException {
		StringBuilder res = new StringBuilder();
		fixTop(url, isFlow, "", "", res, buttons(isFlow).toString(),perf.getId());
		
		List<PerfProperty> pps = Util.findPpsById(perf.getId());
		StringBuilder formTitle = formTitle(url, pps);
		
		String layout = perf.getLayout();
		if(Util.notEmptyString(layout)){
			List<Cube> cubes = JSON.parseArray(layout,Cube.class);
			for(Cube cube:cubes){
				int kind = cube.getKind();
				if(1==kind){
					res.append(formTitle);
					CubeTd[][] tds = cube.getTds();
					if(tds!=null)
						res.append(buildCubeProperty(bean, quotePropertyId, cube,perf.getId()));
					else
						res.append(buildNormalProperty(bean, quotePropertyId, pps));
					res.append("</form>");
				}
			}
		}else{
			res.append(formTitle);
			res.append(buildNormalProperty(bean, quotePropertyId, pps));
			res.append("</form>");
		}
		
		return res;
	}

	private static void buildQuote(BaseTO bean, StringBuilder res,
			Quote quote) throws NoSuchFieldException,
			IllegalAccessException {
		String divTitle = quote.getDivTitle();
		buildTogTitle(divTitle, res);
		final Perf quotePerf = quote.getQuotePerf();
		final Property quoteProperty = quote.getQuoteProperty();
		final Property oranProperty = quote.getOranProperty();
		final Permission permission = quotePerf.getPermission();
		final String namespace = permission.getNamespace();
		res.append("<div style='height:500px;margin-top: 20px;'>")
				.append("<iframe scrolling='auto' frameborder='0'  src='")
				.append(namespace + "_" + permission.getActionName()
						+ Constant.URL_SUFFIX + "?isPage=1&quote="
						+ quote.getId());

		if (quoteProperty != null) {
			final String propertyName = quoteProperty.getProperty();
			final String oranPropertyName = oranProperty.getProperty();
			Class<?> clazz = bean.getClass();
			Field declaredField = clazz .getDeclaredField(oranPropertyName);
			String oranPropertyValue = String.valueOf(declaredField
					.get(bean));
			res.append("&bean." + propertyName + "=" + oranPropertyValue);

		}
		res.append("' style=\"width:100%;height:100%;\"></iframe>").append(
				"</div>");
	}

	private static StringBuilder buildNormalProperty(BaseTO bean, String quotePropertyId,
			 List<PerfProperty> pps)
			throws NoSuchFieldException, IllegalAccessException {
		StringBuilder res = new StringBuilder();
		res.append(ADD_EDIT_TABLE);
		int ind=0;
		for (PerfProperty pp : pps) {
			Property property = pp.getProperty();
			if (quotePropertyId.equals(property.getId())) {
				pp.setEditshowhide("0");
			}
			String[] beanValue = filedVal(bean, pp);
			StringBuilder value = findInput(pp, beanValue);
			if(!"0".equals(pp.getEditshowhide())){
				if(ind==0){
					res.append("<tr>");
				}else if(ind%3==0){
					res.append("</tr><tr>");
				}
				ind++;
				res.append("<td>").append(value).append("</td>");
			}else{
				res.append(value);
			}
		}
		return res.append("</tr></table>");
	}

	private static StringBuilder buildCubeProperty(BaseTO bean, String quotePropertyId,Cube cube, String pid)
			throws NoSuchFieldException, IllegalAccessException {
		StringBuilder res = new StringBuilder();
		buildTogTitle(cube.getDescr(), res);
		res.append(ADD_EDIT_TABLE);
		CubeTd[][] tds = cube.getTds();
		List<PerfProperty> ppl=new ArrayList<PerfProperty>();
		for(CubeTd[] ts:tds){
			res.append("<tr>");
			for(CubeTd t:ts){
				res.append("<td");
				
				if(t.getColspan()>1){res.append(" colspan="+t.getColspan());}
				if(t.getRowspan()>1){res.append(" rowspan="+t.getRowspan());}
				if(Util.notEmptyString(t.getStyle())){res.append(" style=\""+t.getStyle()+"\"");}
				res.append(">");
//				int ppid = t.getPpid();
				String pn = t.getPn();
				if(Util.notEmptyString(pn)){
					boolean clearDescr = t.isClearDescr();
					PerfProperty pp = Util.findPpByPn(pn,pid);
					ppl.add(pp);
					if(pp!=null){
						Property property = pp.getProperty();
						if (quotePropertyId.equals(property.getId())) {
							pp.setEditshowhide("0");
						}
						String[] beanValue = filedVal(bean, pp);
						StringBuilder value = findInput(pp, beanValue,clearDescr);
						res.append(value);
					}else{
						res.append(pn+" not found");
					}
					
				} else {
					String pnDescr = t.getPnDescr();
					if(Util.notEmptyString(pnDescr)){
						PerfProperty pp = Util.findPpByPn(pnDescr,pid);
						res.append(pp.getProperty().getDescr());
					}else{
						res.append(t.getDescr());
					}
				}
				res.append("</td>");
			}
			res.append("</tr>");
		}
		List<PerfProperty> hppl=Util.findHidePp(ppl,pid);
		for(PerfProperty hpp:hppl){
			String[] beanValue = filedVal(bean, hpp);
			StringBuilder value = findInput(hpp, beanValue);
			res.append(value);
		}
		return res.append("</table>");
	}

	private static StringBuilder buildTogTitle(String str, StringBuilder res) {
		return res.append("<p></p><a href='javascript:void(0)' onclick='$(this).next().toggle();'>").append(str).append("</a>");
	}

	private static String[] filedVal(BaseTO bean,
			PerfProperty pp) throws NoSuchFieldException,
			IllegalAccessException {
		String[] beanValue = { "", "" };//描述+ID  或者  描述+描述
		Property property=pp.getProperty();//org.id
		String field = property.getProperty();//org.id
		int indexOf = field.indexOf(".");//3
		Class<? extends BaseTO> clazz=bean.getClass();//OrgMember.class
		if (indexOf >= 0) {
			String child = field.substring(indexOf + 1, field.length());//id
			String f1 = field.substring(0, indexOf);//org
			Field declaredField = clazz.getDeclaredField(f1);//org(Field) in OrgMember.class
			Class<?> paramClazz = declaredField.getType();//Org.class
			BaseTO mem = (BaseTO) declaredField.get(bean);//Org.实例
			if (mem != null) {
				Field childField = paramClazz.getDeclaredField(child);//id(Field) in Org.class
//				final Bean perfBean = property.getBean();
//				String df_descrFieldVal = perfBean.getDescrProperty();
				Bean perfBean = Util.findBeanByClass(paramClazz.getName());//Org.Bean
				String descrProperty = perfBean.getDescrProperty();
				descrProperty = Util.notEmptyString(descrProperty)?descrProperty:"id";
				Field descrField = paramClazz
						.getDeclaredField(descrProperty);//descr(Field) in Org.class
				beanValue[0] = String.valueOf(descrField.get(mem));//descr.内容 in Org.实例
				beanValue[1] = String.valueOf(childField.get(mem));//id.内容 in Org.实例
			}
		} else {
			Field declaredField = clazz.getDeclaredField(field);
			beanValue[0] = String.valueOf(declaredField.get(bean));
			beanValue[1] = String.valueOf(declaredField.get(bean));
		}
		String dftvalue = propertyDftvalue(pp, property);
		if (Util.notEmptyString(dftvalue)
				& !Util.notEmptyString(beanValue[0])
				& !Util.notEmptyString(beanValue[1])) {
			if (dftvalue.startsWith(":")) {
				beanValue[0] = (String) Util.FilterValue(dftvalue, null);//TODO:当指代信息需要以ID+描述的形式出现时，FilterValue需返回ID+描述两个值
				beanValue[1] = beanValue[0];
			} else {
				beanValue[0] = dftvalue;
				beanValue[1] = dftvalue;
			}
		}
		return beanValue;
	}

	public static String dftValue(String dftvalue) {
		String res = null;
		String replace = dftvalue.replace(":", "");
		if ("currentDate".equals(replace)) {
			res = DateTimeUtils.formatDate(
					new Timestamp(System.currentTimeMillis()), "yyyy-MM-dd");
		}
		return res;
	}

	/**
	 * 我来组成《创建页面》的提交、刷新、发送按钮。
	 */
	private static StringBuilder buttons(boolean isFlow) {
		StringBuilder res = new StringBuilder();
		res.append("<script type='text/javascript'>document.onkeydown=function(event){var e = event || window.event || arguments.callee.caller.arguments[0];if(e && e.keyCode==13&& e.altKey){$(\"#df_form\").submit();}};</script>" +
				"<ul class='nav'><li><a id='df_sub' title='快捷键：alt+enter' href='javascript:void(0)'  onclick='javascript:$(\"#df_form\").submit();'><i class=\"icon-ok\"></i>提交</a></li>");
		if (isFlow) {
			res.append("<li><a id='df_sub_f' href='javascript:void(0)'  onclick='javascript:flow_page();'><i class=\"icon-envelope\"></i>发送</a>");
		}
		if (isFlow) {
			res.append("发送流程前，请先提交数据。");
		}
		res.append("<li><a id='df_action_msg'></a></li></ul>");
		return res;
	}

	/**
	 * 新增页面中，发送按钮的js
	 * 
	 * @return
	 */
	public static StringBuilder pageFlowByNoJs(String url) {
		StringBuilder res = new StringBuilder();
		res.append(
				"function flow_page(){var no = $('input[name=\"bean.no\"]').val();window.open('")
				.append(url)
				.append("?bean.no='+no,'_blank','scrollbars=yes,toolbar=yes,status=yes');}");
		return res;
	}

	public static StringBuilder findInput(PerfProperty pp, String[] beanValue) throws SecurityException, IllegalArgumentException, NoSuchFieldException, IllegalAccessException {
		return findInput(pp, beanValue,"",false);
	}
	public static StringBuilder findInput(PerfProperty pp, String[] beanValue,boolean clearDescr) throws SecurityException, IllegalArgumentException, NoSuchFieldException, IllegalAccessException {
		return findInput(pp, beanValue,"",clearDescr);
	}
	/**
	 * 根据表现类中的Input绘制文本框
	 * 
	 * @param input
	 * @param field
	 * @param beanValue
	 *            文本框的初始值
	 * @param from 
	 * @return
	 * @throws IllegalAccessException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalArgumentException 
	 * @throws SecurityException 
	 */
	public static StringBuilder findInput(PerfProperty pp, String[] beanValue, String from,boolean clearDescr) throws SecurityException, IllegalArgumentException, NoSuchFieldException, IllegalAccessException {
		StringBuilder res = new StringBuilder();
		String val1 = "";
		String val2 = "";
		if (beanValue != null) {
			val1 = beanValue[0];
			val2 = beanValue[1];
		}
		Property property = pp.getProperty();

		Myinput myinput = propertyMyinput(pp, property);
		String esh = pp.getEditshowhide();
		String field = "bean." + property.getProperty();
		if ("0".equals(esh)) {
			res.append("<input type='hidden' name='").append(field)
					.append("' value='").append(val2).append("'>");
		} else if ("1".equals(esh)) {
			if(!clearDescr)
				res.append(property.getDescr()).append(":");
			res.append(val2).append("<input type='hidden' name='")
					.append(field).append("' value='").append(val2)
					.append("'>");
		} else if ("2".equals(esh)||("3".equals(esh))) {
			if (myinput != null) {
				if(!clearDescr)
					res.append(property.getDescr()).append(":");
				String type = myinput.getType();

				String url = propertyValueUrl(pp, property);
				String dateOptionStr="";
				String validateParams = "";//用在只需要required的select、tree、pop
				if(!Util.notEmptyString(from)){
					String max = propertyValue(pp,"max");
					String min = propertyValue(pp,"min");
					if(!Util.notEmptyString(max)&&Util.notEmptyString(property.getLength())){
						max=property.getLength();
					}
					if(!Util.notEmptyString(min)){
						min="0";
					}
					String required = "";
					String requiredPv = propertyValue(pp, "required");
					if ("1".equals(requiredPv)) {
						required = "required:true,";
						res.append("<font color=red>*</font>");
					}
					dateOptionStr = " data-options=\""+required+"validType:[";
					String validType = propertyValue(pp, "validType");
					if (Util.notEmptyString(validType)) {
						dateOptionStr += " '" + validType+"',";
						
					}
					if(Util.notEmptyString(max)){
						dateOptionStr += "'length["+min+","+max+"]']\" ";
					}
					dateOptionStr += "]\" ";
					
					if ("1".equals(requiredPv)) {
						validateParams = " required='true' ";
					}
				}
				if ("text".equals(type)) {
					res.append("<input class='easyui-validatebox'")
							.append(dateOptionStr)
							.append(" type='text' name='").append(field)
							.append("' value='").append(val1).append("'>");
				} else if ("textarea".equals(type)) {
					res.append("<textarea class='easyui-validatebox'")
							.append(dateOptionStr)
							.append(" name='").append(field)
							.append("'>").append(val1).append("</textarea>");
				} else if ("select".equals(type)) {
					res.append("<input class='easyui-combobox'")
							.append(validateParams)
							.append(" id='")
							.append(field.replace(".", ""))
							.append("' name='")
							.append(field)
							.append("' data-options='url: \"")
							.append(url)
							.append("\",valueField: \"value\",textField: \"name\",panelWidth: 350,editable: true");
					res.append("'>");
					setValueToInput(res, val2, field);
//					String js = propertyJs(pp, property);
//					if (Util.notEmptyString(js)) {
//						String com = "$('input[name=\"" + field + "\"]')";
//						res.append("<script language='JavaScript'>")
//								.append(com).append(".combobox({").append(js)
//								.append("});</script>");
//					}
					String com = "$('input[name=\"" + field + "\"]')";
					res.append("<script language='JavaScript'>")
							.append(com).append(".combobox({").append("filter: function(q, row){var opts = $(this).combobox('options');	return row[opts.textField].indexOf(q)>-1;	}")
							.append("});</script>");
				} else if ("sdate".equals(type)) {
					// 来自工具栏的搜索框，对日期搜索进行范围搜索
					res.append("<input type='text' id='").append(field).append("' ").append(dateOptionStr)
							.append("' name='").append(field)
							.append("' size='25'>");
					res.append(
							"<script type='text/javascript' charset='utf-8'>var ")
							.append(field.replace(".", ""))
							.append(" = new Kalendae.Input('")
							.append(field)
							.append("', {months:2,mode:'range',format:'YYYY-MM-DD'});</script>");
					setValueToInput(res, val1, field);
				} else if ("date".equals(type)) {
					res.append("<input class='easyui-datebox'")
							.append(dateOptionStr).append(" name='")
							.append(field).append("'>");
					setValueToInput(res, val1, field);
				} else if ("pop".equals(type)) {
					res.append("<input class='easyui-validatebox'")
							.append(validateParams).append(" readonly='readonly' type='text' name='text_")
							.append(field).append("' value='").append(val1)
							.append("'><input type='hidden' name='")
							.append(field).append("' value='").append(val2)
							.append("'>");
					// EDIT
					String pop_params = "'" + field + "','" + url + "'";
					String pop_clear_params = "'" + field + "'";
					res.append(
							"<a href='javascript:void(0)' onclick=\"javascript:pop(")
							.append(pop_params)
							.append(")\" >选择&nbsp;</a><a href='javascript:void(0)' onclick=\"javascript:pop_clear(")
							.append(pop_clear_params).append(")\" >清空</a>");
				} 
//				else if ("radio".equals(type)) {
//					res.append("<input type='radio' value='1' name='")
//							.append(field).append("'>是")
//							.append("<input type='radio' value='0' name='")
//							.append(field).append("'>否")
//							.append("<input type='radio' value='' name='")
//							.append(field).append("'>空");
//					String com = "$('input[name=\"" + field + "\"][value=\""
//							+ val1 + "\"]')";
//					res.append("<script language='JavaScript'>").append(com)
//							.append(".attr('checked','checked');</script>");
//				} 
				else if ("radio".equals(type)) {
					res.append("<input class='easyui-combobox'")
					.append(validateParams).append(" id='")
					.append(field.replace(".", ""))
					.append("' name='")
					.append(field)
					.append("' data-options='url: \"cfg_selectCons.sg?domain=yorn\",valueField: \"value\",textField: \"name\",panelWidth: 350,editable: true");
					res.append("'>");
					setValueToInput(res, val2, field);
//					String js = propertyJs(pp, property);
//					if (Util.notEmptyString(js)) {
//						String com = "$('input[name=\"" + field + "\"]')";
//						res.append("<script language='JavaScript'>")
//								.append(com).append(".combobox({").append(js)
//								.append("});</script>");
//					}
				} 
				else if ("radioEnable".equals(type)) {
					res.append("<input class='easyui-combobox'")
					.append(validateParams).append(" id='")
					.append(field.replace(".", ""))
					.append("' name='")
					.append(field)
					.append("' data-options='url: \"cfg_selectCons.sg?domain=enable\",valueField: \"value\",textField: \"name\",panelWidth: 350,editable: true");
					res.append("'>");
					setValueToInput(res, val2, field);
				} 
				else if ("tree".equals(type)) {
					res.append("<input class=' easyui-combotree'")
							.append(validateParams).append(" name='")
							.append(field).append("' data-options='url: \"")
							.append(url).append("?endTreeValue=").append(val2)
							.append("\",editable:true,panelWidth: 350").append("'>");
					setValueToInput(res, val2, field);
				} else if ("numberbox".equals(type)) {
					res.append("<input class='easyui-numberbox' ")
							.append(dateOptionStr)
							.append(" name='").append(field)
							.append("' value='").append(val1).append("'>");
				} else if ("double2".equals(type)) {
					res.append("<input class='easyui-numberbox' precision='2' ")
							.append(dateOptionStr)
							.append(" name='").append(field)
							.append("' value='").append(val1).append("'>");
				} else if ("file".equals(type)) {
					res.append("<input class='easyui-validatebox' type='file' ")
							.append(dateOptionStr)
							.append(" name='upload' >");
					res.append("<input type='hidden' name='").append(field)
							.append("' value='").append(val1).append("'>");
				} else if ("editor".equals(type)) {
					res.append("<script type='text/javascript' charset='utf-8' src='ueditor/ueditor.config.js'></script>" +
							"<script type='text/javascript' charset='utf-8' src='ueditor/ueditor.all.min.js'> </script>" +
							"<script type='text/javascript' charset='utf-8' src='ueditor/lang/zh-cn/zh-cn.js'></script>");
					res.append("<script id='editor' type='text/plain' style='width:1024px;height:500px;'></script>")
							;
					String com = "$('input[name=\"" + field + "\"]')";
					res.append("<script type='text/javascript'>var ue = UE.getEditor('editor');ue.ready(function() {ue.setContent('"+val1+"','');});ue.addListener('contentChange',function(){"+com+".val(ue.getContent())});</script>");
					res.append("<input type='hidden' name='").append(field)
							.append("'  value='").append(val1).append("'>");
				}
			}
		}
		return res;
	}

	private static String propertyValue(PerfProperty pp,String propertyName) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		Property property = pp.getProperty();
		Field pfield = property .getClass().getDeclaredField(propertyName);
		String pv=(String) pfield.get(property);
		Field ppfield = pp.getClass().getDeclaredField(propertyName);
		String ppv=(String) ppfield.get(pp);
		String dataOptions = pv;
		if (Util.notEmptyString(ppv)) {
			dataOptions = ppv;
		}
		return dataOptions;
	}

	private static String propertyValueUrl(PerfProperty pp, Property property) {
		String url = property.getValueurl();
		if (Util.notEmptyString(pp.getValueurl())) {
			url = pp.getValueurl();
		}
		return url;
	}

	static Myinput propertyMyinput(PerfProperty pp, Property property) {
		Myinput myinput = property.getMyinput();
		if (pp.getMyinput() != null) {
			myinput = pp.getMyinput();
		}
		return myinput;
	}

	public static String propertyShowValue(PerfProperty pp, Property property) {
		String showvalue = property.getShowvalue();
		if (Util.notEmptyString(pp.getShowvalue())) {
			showvalue = pp.getShowvalue();
		}
		return showvalue;
	}

	private static void setValueToInput(StringBuilder res, String val2,
			String field) {
		String com = "$('input[name=\"" + field + "\"]')";
		res.append("<script language='JavaScript'>").append(com)
				.append(".val('").append(val2).append("');</script>");
	}

	/**
	 * 保存时提交的js 提交时信息验证 用于显示返回信息
	 * 
	 * @param url
	 * @return
	 */
	public static StringBuilder findSaveSubmitJs() {
		StringBuilder res = new StringBuilder();
		res.append("$(function(){$('#df_form').form({onSubmit:function(){if($(this).form('validate')){$('#df_sub').attr('disabled','disabled');}return $(this).form('validate');},success:function(data){$('#df_action_msg').html('<font color=green>'+data+'</font>');$('#df_sub').removeAttr('disabled');}});});");
		return res;
	}


	public static StringBuilder openUrlByOneRowJs(String jsName, String url) {
		StringBuilder res = new StringBuilder();
		String grid = "$('#" + LIST_TABLE_NAME + "')";
		res.append("function ")
				.append(jsName)
				.append("{var row = ")
				.append(grid)
				.append(".datagrid('getSelected');if(row){var ids=$('#ids').val();var id = row.id;window.open('")
				.append(url)
				.append("&bean.id='+id+'&ids='+ids,'_blank');}else{alert('")
				.append(ROWNOTSELECTED_TRIP).append("');}}");
		return res;
	}


	/**
	 * 删除一个Bean方法
	 * 
	 * @param url
	 * 
	 * @param string
	 * @return
	 */
	public static StringBuilder findDelJs(String url) {
		return findDelJs("", url);
	}

	public static StringBuilder findDelJs(String name, String url) {
		if (!Util.notEmptyString(name)) {
			name = LIST_TABLE_NAME;
		}
		StringBuilder res = new StringBuilder();
		res.append("function ")
				.append(DEL_JS_NAME)
				.append("{var row = $('#")
				.append(name)
				.append("').datagrid('getSelected');if(row){$.messager.confirm('确认框', '确认删除选中记录?', function(r){if (r){var id = row.id;$.post('")
				.append(url)
				.append("&bean.id='+id,function(data){alert(data);using(['dialog','messager'], function(){$.messager.show({title:'提示',msg:data});var index = $('#")
				.append(name)
				.append("').datagrid('getRowIndex', row);$('#")
				.append(name)
				.append("').datagrid('deleteRow', index);});});}});}else{alert('")
				.append(ROWNOTSELECTED_TRIP).append("');}}");
		return res;
	}

	public static StringBuilder findDetailTable(BaseTO bean,  Perf perf,List<Quote> ql,
			String url, String beanId, String ids)
			throws ClassNotFoundException, SecurityException,
			NoSuchFieldException, IllegalArgumentException,
			IllegalAccessException {
		StringBuilder res = new StringBuilder();
		fixTop(url, false, beanId, ids, res, "",perf.getId());
		
		List<PerfProperty> pps = Util.findPpsById(perf.getId());
		String layout = perf.getLayout();
		if(Util.notEmptyString(layout)){
			List<Cube> cubes = JSON.parseArray(layout,Cube.class);
			for(Cube cube:cubes){
				int kind = cube.getKind();
				if(1==kind){
					CubeTd[][] tds = cube.getTds();
					if(tds!=null)
						res.append(buildCubeProperty(bean, "", cube,perf.getId()));
					else
						res.append(buildNormalProperty(bean, "", pps));
				}else if(2==kind){
					for(Quote quote:ql){
						buildQuote(bean, res, quote);
					}
				}
			}
		}else{
			res.append(buildNormalProperty(bean, "", pps));
		}
		return res;
	}


	/**
	 * 普通编辑类表格
	 * 
	 * @param ids
	 * @param id
	 */
//	public static StringBuilder findEditForm(String url, BaseTO bean, Perf perf,List<Quote> ql)
//			throws ClassNotFoundException, SecurityException,
//			NoSuchFieldException, IllegalArgumentException,
//			IllegalAccessException {
//		return buildEditForm(url, bean, null, perf,ql, false, "", "");
//	}

	public static StringBuilder findEditForm(String url, BaseTO bean, Perf perf,List<Quote> ql,
			String id, String ids) throws ClassNotFoundException,
			SecurityException, NoSuchFieldException, IllegalArgumentException,
			IllegalAccessException {
		return buildEditForm(url, bean, null, perf,ql, false, id, ids);
	}
//
//	/**
//	 * 文本上传类编辑类表格
//	 */
//	public static StringBuilder findEditForm(String url, BaseTO bean,
//			String entype, int psid) throws ClassNotFoundException,
//			SecurityException, NoSuchFieldException, IllegalArgumentException,
//			IllegalAccessException {
//		return buildEditForm(url, bean, entype, psid, false, "", "");
//	}

	private static StringBuilder buildEditForm(String url, BaseTO bean,
			String enctype, Perf perf,List<Quote> ql, boolean isFlow, String beanId, String ids)
			throws ClassNotFoundException, SecurityException,
			NoSuchFieldException, IllegalArgumentException,
			IllegalAccessException {
		StringBuilder res = new StringBuilder();
		fixTop(url, isFlow, beanId, ids, res, buttons(isFlow).toString(),perf.getId());
		List<PerfProperty> pps = Util.findPpsById(perf.getId());
		StringBuilder formTitle = formTitle(url, pps);
		
		String layout = perf.getLayout();
		if(Util.notEmptyString(layout)){
			List<Cube> cubes = JSON.parseArray(layout,Cube.class);
			for(Cube cube:cubes){
				int kind = cube.getKind();
				if(1==kind){
					res.append(formTitle);
					CubeTd[][] tds = cube.getTds();
					if(tds!=null)
						res.append(buildCubeProperty(bean, "", cube,perf.getId()));
					else
						res.append(buildNormalProperty(bean, "", pps));
					res.append("</form>");
				}else if(2==kind){
					for(Quote quote:ql){
						buildQuote(bean, res, quote);
					}
				}
			}
		}else{
			res.append(formTitle);
			res.append(buildNormalProperty(bean, "", pps));
			res.append("</form>");
		}
		
		return res;
	}


	private static void labelValue(StringBuilder res, String value, String descr, String spanLen) {
		res.append(
				"<div class=\"control-group "+spanLen+"\"><label class=\"control-label\">")
				.append(descr).append("：</label><div class=\"controls\">")
				.append(value).append("</div></div>");
	}

	private static void fixTop(String url, boolean isFlow, String beanId,
			String ids, StringBuilder res, String but, String pid) {
		res.append("<div class=\"navbar navbar-fixed-top noprint\"> <div class=\"navbar-inner\"><div class=\"container-fluid\"><div class=\"nav-collapse collapse\">");
		List<String> bl=new ArrayList<String>();
		if (Util.notEmptyString(ids)) {
			if (ids.endsWith(",")) {
				ids = ids.substring(0, ids.length() - 1);
			}
			String[] split = ids.split(",");
			int ind = 0;
			int i = 0;
			for (String s : split) {
				if (beanId.equals(s)) {
					ind = i;
				}
				i++;
			}
			int p = ind - 1;
			int n = ind + 1;
			if (p > -1) {
				String r=url+ "?isPage=1&bean.id=" + split[p] + "&ids=" + ids;
				bl.add("if(e && event.ctrlKey && e.keyCode==37){window.location.href='"+r+"'}");
				res.append("<ul class='nav'><li><a id='lastLink' title='快捷键：ctrl+向左' href='" + r
						+ "'><i class=\"icon-backward\"></i>上一个</a></li></ul>");
			}
			if (n < i) {
				String r=url+ "?isPage=1&bean.id=" + split[n] + "&ids=" + ids;
				bl.add("if(e && event.ctrlKey  && e.keyCode==39){window.location.href='"+r+"'}");
				res.append("<ul class=\"nav pull-right\"><li><a id='nextLink' title='快捷键：ctrl+向右' href='"+r+"'>下一个<i class=\"icon-forward\"></i></a></li></ul>");
			}
		}

		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ctx
				.get(StrutsStatics.HTTP_REQUEST);
		@SuppressWarnings("unchecked")
		List<Permission> specialPermission = (List<Permission>) request
				.getAttribute("specialPermission");

		for (Permission permission : specialPermission) {
			String actionName = permission.getActionName();
			if (actionName.equals("c_Perf_editUnder")) {//TODO 将所有的特殊Permission提取，放在jsputil之外
				res.append("<ul class='nav'><li><a target='_blank' href='cfg_c_Perf_editUnder.sg?isPage=1&bean.id=" + pid
						+ "'>"+permission.getDescr()+"</a></li></ul>");
			}
		}
		res.append("<ul class='nav'><li><a href='javascript:void(0)' onclick='window.print();'><i class=\"icon-print\"></i>打印</a></li></ul>");
		res.append("<script type='text/javascript'>document.onkeyup=function(event){var e = event || window.event || arguments.callee.caller.arguments[0];" );
		for(String s:bl){
			res.append(s);
		}
		res.append("};</script>");
		res.append(but);
		res.append("</div></div></div></div>");
	}

	/**
	 * 父页面关闭时调用的js方法 返回父页面选中的bean的id和DF_DESCR
	 * 
	 * @param from
	 * @param clazz2 
	 * @param className
	 * @return
	 * @throws ClassNotFoundException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public static StringBuilder findClsJs(String from, Class clazz, String descr_name)
			throws ClassNotFoundException, SecurityException,
			NoSuchFieldException, IllegalArgumentException,
			IllegalAccessException {
		StringBuilder res = new StringBuilder();
		if (Util.notEmptyString(from)) {
			res.append("function df_cls() {var ids='';var row = $('#")
					.append(LIST_TABLE_NAME)
					.append("').datagrid('getSelected');if(row){ids = row.")
					.append(descr_name)
					.append("+','+row.id;window.returnValue = ids;window.close();}else{alert('")
					.append(ROWNOTSELECTED_TRIP).append("');}}");
		}
		return res;
	}

}
