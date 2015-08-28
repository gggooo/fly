package cfg.act;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cfg.vo.Filter;
import cfg.vo.Member;
import cfg.vo.Member_ForRole;
import cfg.vo.Org;
import cfg.vo.OrgMember;
import cfg.vo.Perf;
import cfg.vo.Permission;
import cfg.vo.Quote;
import cfg.vo.Role;
import cfg.vo.RoleAtt;
import cfg.vo.RoleFilter;
import cfg.vo.RoleMember;
import cfg.vo.RoleOrg;
import cfg.vo.RolePerf;
import cfg.vo.RolePermission;

import common.act.BaseAction;
import common.dao.BaseDao;
import common.util.Constant;
import common.util.FileUtil;
import common.util.JspUtil2;
import common.util.Util;
import common.vo.AsyncTree;
import common.vo.BaseTO;
import common.vo.EasyOut;
import common.vo.NameValuePair;

public class RoleAction extends CfgAction {

	private Logger log = Logger.getLogger(RoleAction.class);
	// private Role bean = new Role();
	private String check;
	private OrgMember mbean = new OrgMember();
	private String inRoleInput;
	private String inOro;
	private String orgHtml;
	private String attHtml;
	
	public String c_roleToMemberOrgAtt() throws NoSuchFieldException,
			IllegalAccessException, ClassNotFoundException {
		String url="cfg_c_Role_toMemberOrgAtt.sg";
		if ("1".equals(isPage)) {
			// 返回定制页
			bean=baseDao.get(Role.class, String.valueOf(idField.get(bean)));
			StringBuilder orgTree = new StringBuilder();
			Role role = (Role)bean;
			orgTree
					.append("<div  style='width:30%;'>"
							+ "组织："
							+ role.getDescr()
							+ "<a class='easyui-linkbutton' onclick='getChecked()'>保存</a> <a class='easyui-linkbutton' onclick='collapse()'>收起</a> "
							+ "<ul id='roletree' class=\"easyui-tree\" data-options=\"url:'"+url+"?isPage=orgTree&bean.id="
							+ role.getId()
							+ "',checkbox:true,cascadeCheck:false"
							+ " \"></ul>"
							+ "</div>");
			// permissionTree.append("<script>$(function(){$('#roletree').tree({onLoadSuccess:function(node, data){ $('#roletree').tree({onCheck:function(node, checked){alert(node.text+checked);}});}});});function tr(){}</script>");
			// permissionTree.append("<script>$(function(){setTimeout('tr()',5000);});function tr(){$('.tree-checkbox').click(function(){alert(1)});}</script>");
			orgTree
					.append("<script>function collapse(){$('#roletree').tree('collapseAll');} function getChecked(){var nodes = $('#roletree').tree('getChecked');var s = '';	"
							+ "for(var i=0; i<nodes.length; i++){if (s != '') s += ',';"
							+ "s += nodes[i].id;}"
							+ "$.post('"+url+"?isPage=orgTreeSub',{'id':s,'bean.id':"
							+ role.getId()
							+ "},function(data){alert(data);});}</script>");

			setOrgHtml(orgTree.toString());
			
			StringBuilder attSb = new StringBuilder();
			attSb.append("组织属性：<form action='"+url+"?isPage=attSub' method='POST'><input type='submit' value='提交'><p>");
			DetachedCriteria dc= DetachedCriteria.forClass(RoleAtt.class);
			dc.add(Restrictions.eq("role", bean)).add(Restrictions.eq("enable", Constant.ENABLE_NOR));
			List<RoleAtt> ras = baseDao.find(dc);
			
			List<NameValuePair> nvlist = Util.nvlist(baseDao, "cfg_org_att");
			for(NameValuePair nv:nvlist){
				String value = nv.getValue();
				if(Util.notEmptyString(value)){
					String chk="";
					for(RoleAtt ra:ras){
						String att = ra.getAtt();
						if(att.equals(value)){
							chk="checked";
						}
					}
					attSb.append("<input type='checkbox' name='atts' value='"+value+"' "+chk+">")
						.append(nv.getName());
				}
			}
			attSb.append("</form>");
			setAttHtml(attSb.toString());
			return "page";
		} else if ("orgTreeSub".equals(isPage)) {
			bean = baseDao.get(Role.class,String.valueOf(idField.get(bean)));
			id = "," + id + ",";
			DetachedCriteria dc = DetachedCriteria
					.forClass(RoleOrg.class);
			dc.add(Restrictions.eq("role", bean)).add(Restrictions.eq("enable", Constant.ENABLE_NOR));
			List<RoleOrg> rpl = baseDao.find(dc);
			for (RoleOrg ro : rpl) {
				String pid = ro.getOrg().getId();
				if (!id.contains("," + pid + ",")) {
					ro.setEnable(Constant.ENABLE_DEL);
					ro.setUpdateDate(now);
					ro.setUpdateMember(sessionMember);
					baseDao.update(ro);
				} else {
					id = id.replace("," + pid + ",", ",");
				}
			}
			if (id.length() > 1) {
				String[] split = id.split(",");
				for (String s : split) {
					if (Util.notEmptyString(s)) {
						RoleOrg rp = new RoleOrg();
						Org one = new Org();
						one.setId(s);
						rp.setOrg(one);
						rp.setRole((Role) bean);
						rp.setEnable(Constant.ENABLE_NOR);
						rp.setCreateDate(now);
						rp.setCreateMember(sessionMember);
						baseDao.save(rp);
					}
				}
			}

			setBodyHtml(Constant.UPDATE_SUCCESS);
			return "orgTreeSub";
		} else if ("orgTree".equals(isPage)) {
				 
			// 返回角色对应的组织树
			DetachedCriteria dc = DetachedCriteria.forClass(RoleOrg.class);
			dc.add(Restrictions.eq("role", bean)).add(
					Restrictions.eq("enable", "1"));
			List<RoleOrg> rpl = baseDao.find(dc);
			List<String> mpl = new ArrayList<String>();
			for (RoleOrg rp : rpl) {
				mpl.add(rp.getOrg().getId());
			}
			treeValue = new ArrayList<AsyncTree>();
			List<AsyncTree> findTreeValue = Util.findCheckedTreeValue(mpl,
					new Org(), null, baseDao);
			treeValue.addAll(findTreeValue);
			return "orgTree";
		} else if ("member".equals(isPage)) {
			setCoeo(new EasyOut<BaseTO>());
			DetachedCriteria dc = DetachedCriteria.forClass(OrgMember.class);
			Member member_sear = getMbean().getMember();
			if(member_sear!=null&&Util.notEmptyString(member_sear.getDescr())){
				dc.createAlias("member", "ms");
				dc.add(Restrictions.ilike("ms.descr", member_sear.getDescr(),MatchMode.ANYWHERE));
			}
			if(Util.notEmptyString(inRoleInput)){
				
			}
			Org org = getMbean().getOrg();
			if (org != null && Util.notEmptyString(org.getId()))
				dc.add(Restrictions.eq("org", org));
			dc.add(Restrictions.eq("enable", "1"));

			getCoeo().setTotal(baseDao.findCount(dc));
			if (Util.emptyString(getSort())) {
				setSort("id");
				setOrder("desc");
			}
			dc = DetachedCriteria.forClass(OrgMember.class);
			if (Util.notEmptyString(getSort()) && !getSort().contains(".")) {// !sortx.contains(".")
				// ：在包含点号时，无法用其进行排序
				if ("desc".equals(getOrder())) {
					dc.addOrder(Order.desc(getSort()));
				} else {
					dc.addOrder(Order.asc(getSort()));
				}
				dc.addOrder(Order.asc("id"));
			}
			if(member_sear!=null&&Util.notEmptyString(member_sear.getDescr())){
				dc.createAlias("member", "ms");
				dc.add(Restrictions.ilike("ms.descr", member_sear.getDescr(),MatchMode.ANYWHERE));
			}
			if (org != null && Util.notEmptyString(org.getId()))
				dc.add(Restrictions.eq("org", org));
			dc.add(Restrictions.eq("enable", "1"));
			List<BaseTO> cl = baseDao.findWithPage2(dc, (getPage() - 1)
					* getRows(), getRows());
			List<BaseTO> ml = new ArrayList<BaseTO>();
			for (BaseTO c : cl) {
				OrgMember x = (OrgMember) c;
				Member_ForRole mf = new Member_ForRole();
				Member member = x.getMember();
				mf.setDescr(member.getDescr());
				mf.setId(member.getId());
				mf.setInRole(inRole(bean, member));
				ml.add(mf);
			}
			getCoeo().setRows(ml);
			return "member";
		} else if ("memberSub".equals(isPage)) {
			if(bean!=null&&getMbean()!=null&&getMbean().getMember()!=null&&Util.notEmptyString(getMbean().getMember().getId())){
				Member sm=getMbean().getMember()
						;
				DetachedCriteria dc = DetachedCriteria.forClass(RoleMember.class);
				dc.add(Restrictions.eq("role", bean)).add(Restrictions.eq("member", sm)).add(Restrictions.eq("enable", "1"));
				RoleMember rm = baseDao.findBean(dc);
				if("1".equals(inOro)){
					if(rm==null){
						rm=new RoleMember();
						rm.setMember(sm);
						rm.setRole((Role)bean);
						rm.setEnable("1");
						rm.setCreateDate(now);
						rm.setCreateMember(sessionMember);
						baseDao.save(rm);
					}
				}else{
					if(rm!=null){
						rm.setEnable(Constant.ENABLE_DEL);
						rm.setUpdateDate(now);
						rm.setUpdateMember(sessionMember);
						baseDao.update(rm);
					}
				}
				setBodyHtml("ok");
			}
			return "memberSub";
		}
		return SUCCESS;
	}

	private String inRole(BaseTO bean, Member x) {
		String res = "";
		DetachedCriteria dc = DetachedCriteria.forClass(RoleMember.class);
		dc.add(Restrictions.eq("role", bean)).add(Restrictions.eq("member", x)).add(Restrictions.eq("enable", "1"));
		RoleMember rm = baseDao.findBean(dc);
		if (rm != null) {
			res = "√";
		}
		return res;
	}

	public String c_editUnderRole() throws NoSuchFieldException,
			IllegalAccessException {
		if ("1".equals(isPage)) {
			StringBuilder permissionTree = new StringBuilder();
			Role role = baseDao.get(Role.class, String.valueOf(idField.get(bean)));
			permissionTree
					.append("<div  style='width:30%;float:left;'>"
							+ "角色："
							+ role.getDescr()
							+ "<a class='easyui-linkbutton' onclick='getChecked()'>保存功能配置</a> <a class='easyui-linkbutton' onclick='collapse()'>收起</a> "
							+ "<ul id='roletree' class=\"easyui-tree\" data-options=\"url:'cfg_c_Role_editUnder.sg?isPage=tree&bean.id="
							+ role.getId()
							+ "',checkbox:true,cascadeCheck:false, "
							+ // cascadeCheck:false,
							"onClick: function(node){ $.post('cfg_c_Role_editUnder.sg?isPage=perf&bean.id="
							+ role.getId()
							+ "&id='+node.id,function(data){$('#perf').html(data);});}"
							+
							// ",onCheck:function(node, checked){alert(node.text+checked);}"
							// +
							" \"></ul>"
							+ "</div><div id='perf' style='width:70%;float:right;'></div>");
			// permissionTree.append("<script>$(function(){$('#roletree').tree({onLoadSuccess:function(node, data){ $('#roletree').tree({onCheck:function(node, checked){alert(node.text+checked);}});}});});function tr(){}</script>");
			// permissionTree.append("<script>$(function(){setTimeout('tr()',5000);});function tr(){$('.tree-checkbox').click(function(){alert(1)});}</script>");
			permissionTree
					.append("<script>function collapse(){$('#roletree').tree('collapseAll');} function getChecked(){var nodes = $('#roletree').tree('getChecked');var s = '';	"
							+ "for(var i=0; i<nodes.length; i++){if (s != '') s += ',';"
							+ "s += nodes[i].id;}"
							+ "$.post('cfg_c_Role_editUnder.sg?isPage=permissionSub',{'id':s,'bean.id':"
							+ role.getId()
							+ "},function(data){alert(data);});}</script>");

			setBodyHtml(permissionTree.toString());
			return "page";
		} else if ("tree".equals(isPage)) {
			DetachedCriteria dc = DetachedCriteria
					.forClass(RolePermission.class);
			dc.add(Restrictions.eq("role", bean));
			List<RolePermission> rpl = baseDao.find(dc);
			List<String> mpl = new ArrayList<String>();
			for (RolePermission rp : rpl) {
				mpl.add(rp.getPermission().getId());
			}
			treeValue = new ArrayList<AsyncTree>();
			List<AsyncTree> findTreeValue = Util.findCheckedTreeValue(mpl,
					new Permission(), null, baseDao);
			treeValue.addAll(findTreeValue);

			return "tree";
		} else if ("perf".equals(isPage)) {
			Permission p = baseDao.get(Permission.class, Integer.valueOf(id));
			DetachedCriteria dc = DetachedCriteria.forClass(Perf.class);
			dc.add(Restrictions.eq("permission", p));
			List<Perf> pl = baseDao.find(dc);
			Role role = baseDao.get(Role.class, String.valueOf(idField.get(bean)));
			dc = DetachedCriteria.forClass(RolePerf.class);
			dc.add(Restrictions.eq("role", role));
			List<RolePerf> rpl = baseDao.find(dc);
			// if pl de p in rpl ,then checked
			StringBuilder res = new StringBuilder();
			res.append("表现：(不选则以ord小的)<p>");
			for (Perf perf : pl) {
				String pod = perf.getId();
				res.append(
						"<input type='checkbox' "
								+ (contain(perf, rpl) ? "checked='checked'"
										: "")
								+ " name='' value='perf:"
								+ pod
								+ "' /><a target='_blank' href='cfg_detailPerf.sg?bean.id="
								+ pod + "'>").append(
						perf.getDescr() + "(" + perf.getOrd() + ")</a><p>");
			}
			dc = DetachedCriteria.forClass(Filter.class);
			dc.add(Restrictions.eq("permission", p));
			List<Filter> fl = baseDao.find(dc);
			dc = DetachedCriteria.forClass(RoleFilter.class);
			dc.add(Restrictions.eq("role", role));
			List<RoleFilter> rfl = baseDao.find(dc);
			// if pl de p in rpl ,then checked
			res.append("数据过滤：(不选则无过滤)<p>");
			for (Filter filter : fl) {
				String pod = filter.getId();
				res.append(
						"<input type='checkbox' "
								+ (contain(filter, rfl) ? "checked='checked'"
										: "")
								+ " name='' value='filter:"
								+ pod
								+ "' /><a target='_blank' href='cfg_detailFilter.sg?bean.id="
								+ pod + "'>").append(
						filter.getDescr() + "(" + filter.getOrd() + ")</a><p>");
			}
			res.append("<script>$(function(){if ($.browser.msie) "
					+ "{ $('input:checkbox').click(function () "
					+ "{ this.blur(); this.focus(); }); }  $('input:checkbox').change(function(){"
					+ "$.post('cfg_c_Role_editUnder.sg?isPage=perfSub',{'id':$(this).val(),'check':$(this).attr('checked'),'bean.id':"
					+ role.getId() + "}," + "function(data){alert(data)}); "
					+ "});});</script>");
			setBodyHtml(res.toString());
			return "perf";
		}
		// 提交Permission修正
		else if ("permissionSub".equals(isPage)) {
			bean = baseDao.get(Role.class,String.valueOf(idField.get(bean)));
			id = "," + id + ",";
			DetachedCriteria dc = DetachedCriteria
					.forClass(RolePermission.class);
			dc.add(Restrictions.eq("role", bean));
			List<RolePermission> rpl = baseDao.find(dc);
			for (RolePermission rp : rpl) {
				String pid = rp.getPermission().getId();
				if (!id.contains("," + pid + ",")) {
					baseDao.delete(rp);
				} else {
					id = id.replace("," + pid + ",", ",");
				}
			}
			if (id.length() > 1) {
				String[] split = id.split(",");
				for (String s : split) {
					if (Util.notEmptyString(s)) {
						RolePermission rp = new RolePermission();
						Permission one = new Permission();
						one.setId(s);
						rp.setPermission(one);
						rp.setRole((Role) bean);
						baseDao.save(rp);
					}
				}
			}

			setBodyHtml(Constant.UPDATE_SUCCESS);
			return SUCCESS;
		}
		// 提交Perf或者Filter
		else if ("perfSub".equals(isPage)) {
			bean = baseDao.get(Role.class, String.valueOf(idField.get(bean)));
			if (Util.notEmptyString(id)) {
				if (id.startsWith("perf:")) {
					String idStr = id.replace("perf:", "");
					Perf perf = baseDao.get(Perf.class, Integer.valueOf(idStr));
					if (perf != null) {
						if ("checked".equals(check)) {
							RolePerf rp = new RolePerf();
							rp.setPerf(perf);
							rp.setRole((Role) bean);
							baseDao.save(rp);
						} else {
							DetachedCriteria dc = DetachedCriteria
									.forClass(RolePerf.class);
							dc.add(Restrictions.eq("perf", perf));
							dc.add(Restrictions.eq("role", bean));
							RolePerf findBean = baseDao.findBean(dc);
							if (findBean != null) {
								baseDao.delete(findBean);
							}
						}
					}

				} else if (id.startsWith("filter:")) {
					String idStr = id.replace("filter:", "");
					Filter perf = baseDao.get(Filter.class,
							Integer.valueOf(idStr));
					if (perf != null) {
						if ("checked".equals(check)) {
							RoleFilter rp = new RoleFilter();
							rp.setFilter(perf);
							rp.setRole((Role) bean);
							baseDao.save(rp);
						} else {
							DetachedCriteria dc = DetachedCriteria
									.forClass(RoleFilter.class);
							dc.add(Restrictions.eq("filter", perf));
							dc.add(Restrictions.eq("role", bean));
							RoleFilter findBean = baseDao.findBean(dc);
							if (findBean != null) {
								baseDao.delete(findBean);
							}
						}
					}

				}
				setBodyHtml(Constant.UPDATE_SUCCESS);
				return SUCCESS;
			}
		}
		return SUCCESS;
	}

	private boolean contain(Filter filter, List<RoleFilter> rpl) {
		for (RoleFilter rp : rpl) {
			Filter filter2 = rp.getFilter();
			if (filter2 != null & filter2.getId() == filter.getId()) {
				return true;
			}
		}
		return false;
	}

	private boolean contain(Perf perf, List<RolePerf> rpl) {
		for (RolePerf rp : rpl) {
			Perf perf2 = rp.getPerf();
			if (perf2 != null & perf2.getId() == perf.getId()) {
				return true;
			}
		}
		return false;
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	public OrgMember getMbean() {
		return mbean;
	}

	public void setMbean(OrgMember mbean) {
		this.mbean = mbean;
	}

	public String getInRoleInput() {
		return inRoleInput;
	}

	public void setInRoleInput(String inRoleInput) {
		this.inRoleInput = inRoleInput;
	}

	public String getInOro() {
		return inOro;
	}

	public void setInOro(String inOro) {
		this.inOro = inOro;
	}

	public String getOrgHtml() {
		return orgHtml;
	}

	public void setOrgHtml(String orgHtml) {
		this.orgHtml = orgHtml;
	}

	public String getAttHtml() {
		return attHtml;
	}

	public void setAttHtml(String attHtml) {
		this.attHtml = attHtml;
	}

}