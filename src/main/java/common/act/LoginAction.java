package common.act;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import cfg.vo.Member;
import cfg.vo.OrgRole;
import cfg.vo.OrgRoleMember;
import cfg.vo.Permission;
import cfg.vo.Role;
import cfg.vo.RolePermission;

import common.dao.BaseDao;
import common.util.Constant;
import common.util.MD5;
import common.util.Util;

public class LoginAction extends BaseAction{
	BaseDao baseDao;
	Member user;
	String psd;
	String psd2;
	String msg;
	private String url;//登录时,浏览器的url地址
	private Map<String,String> show = new HashMap<String, String>();
	private String bodyHtml;
	private String navHtml;

	public String personSet(){
		request.getSession().getAttribute("SESSION_MEMBER");
		return SUCCESS;
	}
	public String psd(){
		if(Util.notEmptyString(psd)&&psd.equals(psd2)){
			Member sm = (Member)request.getSession().getAttribute("SESSION_MEMBER");
			if(sm!=null&&user!=null&&sm.getPsd().equals(user.getPsd())){
				sm.setPsd(psd);
				baseDao.update(sm);
				msg="修改成功!";
			}else{
				msg="未登陆或者原密码不正确!";
			}
		}else {
			msg="密码不一致!";
		}
		return SUCCESS;
	}
	
	public String login(){
		if(user!=null){
			String postPsd = user.getPsd();
			String loginId = user.getLoginId();
			user = baseDao.findBean("from Member where loginId='"+loginId+"'");
			//正确
			if(user!=null&&(MD5.encode(MD5.encode(user.getPsd())).equals(postPsd))){
				request.getSession().setAttribute("SESSION_MEMBER", user);
				if(Util.notEmptyString(url)){
					//判断是否要跳转到该url
					if(url.contains("home.sg")||url.contains("login.sg")||url.contains("logout.sg")||url.contains("loginPage.sg")){
						url = "";
					}
				}
				createLeftPage();
				return SUCCESS;
			}else{
				user = new Member();
				user.setId("");
				return "input";	
			}
		}else{
			return "input";	
		}
		
	}
	private void createLeftPage() {
		StringBuilder nav = new StringBuilder();
		List<Permission> rpl = myPermissions();
		DetachedCriteria dc=DetachedCriteria.forClass(Permission.class);
		dc.add(Restrictions.eq("type", "3")).add(Restrictions.eq("enable", "1"));
		List<Permission> p3 = baseDao.find(dc);
		nav.append("<li><a id='zy' href='javascript:void(0)' onclick='javascript:addTab(\"\",\"主页\",\"\");'>主页</a></li>");
		for(Permission p:p3){
			nav.append("<li class='dropdown'><a href='javascript:void(0)' data-toggle=\"dropdown\" class=\"dropdown-toggle\">")
			.append(p.getDescr()).append("<b class=\"caret\"></b></a>");
			List<Permission> p2=new ArrayList<Permission>
			();
			for(Permission rp:rpl){
				if(rp.getNamespace().equals(p.getNamespace())){
					p2.add(rp);
				}
			}
			if(Util.notEmptyList(p2)){
				nav.append("<ul class='dropdown-menu' id='menu1'>");
				for(Permission p1:p2){
					String urlstr = p1.getNamespace()+"_"+p1.getActionName()+Constant.URL_SUFFIX+"?isPage=1";
					nav.append("<li><a href='javascript:void(0)' onclick='javascript:addTab(\"")
					.append(p.getDescr()).append("\",\"")
					.append(p1.getDescr()).append("\",\"")
					.append(urlstr).append("\")'><i class=\"icon-chevron-right\"></i>").append(p1.getDescr()).append("</a> </li>");
				}
				nav.append("</ul>");
			}
			nav.append("</li>");
		}
		setNavHtml(nav.toString());
	}
	private Permission findP3(Permission permission) {
		if("3".equals(permission.type)){
			return permission;
		}else{
			Permission parent = permission.getParent();
			if(parent!=null){
				Permission pp = baseDao.get(Permission.class, parent.getId());
				return findP3(pp);
			}
		}
		return null;
	}
	private List<Permission> myPermissions() {
		List<Role> myrl = new ArrayList<Role>();
		DetachedCriteria dc = DetachedCriteria.forClass(OrgRoleMember.class);
		dc.add(Restrictions.or(Restrictions.eq("member", user),Restrictions.eq("org", user.getOrg())));//或者我的部门在org下面
		List<OrgRoleMember> orpl = baseDao.find(dc);
		List<Permission> rpl=new ArrayList<Permission>();
		if(Util.notEmptyList(orpl)){
			for(OrgRoleMember orp : orpl){
				OrgRole orgRole = orp.getOrgRole();
				myrl.add(orgRole.getRole());
			}
			
			dc = DetachedCriteria.forClass(RolePermission.class);
			dc.createAlias("permission", "p");
			dc.add(Restrictions.in("role", myrl)).add(Restrictions.eq("p.enable", "1"))
			.add(Restrictions.eq("p.type", "1"));
			ProjectionList projectionList = Projections.projectionList();  
	        projectionList.add(Projections.property("permission"));  
			dc.setProjection(Projections.distinct(projectionList));//防止多个角色下的功能重复。
			rpl = baseDao.find(dc);
		}
		return rpl;
	}
	private void createLeftPagebak() {
		StringBuilder body = new StringBuilder();
		body.append("<div  region='west' split='true' title='导航栏' style='width:280px;padding1:1px;overflow:hidden;'>")
		.append("<div class='easyui-accordion' fit='true' border='false'>");
		List<Role> myrl = new ArrayList<Role>();
		DetachedCriteria dc = DetachedCriteria.forClass(OrgRoleMember.class);
		dc.add(Restrictions.eq("member", user));
		List<OrgRoleMember> orpl = baseDao.find(dc);
		
		if(Util.notEmptyList(orpl)){
			for(OrgRoleMember orp : orpl){
				OrgRole orgRole = orp.getOrgRole();
				myrl.add(orgRole.getRole());
			}
			
			dc = DetachedCriteria.forClass(RolePermission.class);
			dc.createAlias("permission", "p");
			dc.add(Restrictions.in("role", myrl)).add(Restrictions.eq("p.enable", "1"))
			.add(Restrictions.eq("p.type", "1"));
			List<RolePermission> rpl = baseDao.find(dc);
			body.append("<div title='").append("链接").append("' style='padding:10px;'>");
			for(RolePermission rp : rpl){
				Permission permission = rp.getPermission();
				String urlstr = permission.getNamespace()+"/"+permission.getActionName()+Constant.URL_SUFFIX+"?isPage=1";
				body.append("<a class='easyui-linkbutton' iconCls='icon-daily' plain='true' href='javascript:void(0)' onclick='javascript:window.parent.addTab(\"")
				.append(permission.getDescr()).append("\",\"")
				.append(urlstr).append("\")'><span>").append(permission.getDescr()).append("</span></a><p/>");
			}
			body.append("</div>");
			
		}
		body.append("</div></div>");
		setBodyHtml(body.toString());
	}
	public String logout(){
		//set 部门信息
//		List<Member> ul = baseDao.find ("from Member where orgId is not null");
//		for(int i= 0;i<ul.size();i++){
//			Member member = ul.get(i);
//			Org org = baseDao.findBean("from Org where status='enabled' and id='"+member.getOrgId()+"'");
//			while(Util.notEmptyString(org.getLevel())
//					&&Integer.valueOf(org.getLevel())>4){
//				org = baseDao.findBean("from Org where status='enabled' and id='"+org.getParent()+"'");
//			}
//			if("4".equals(org.getLevel())){
//				member.setDeptId(org.getId());
//				member.setDept(org.getFullName());
//				baseDao.update(member);
//			}
//		}
		
		request.getSession().invalidate();
		return SUCCESS;
	}
	public String loginPage(){
		
		return SUCCESS;
	}
	public String psdPage(){
		
		return SUCCESS;
	}
	public String personSetPage(){
		
		return SUCCESS;
	}
	public Member getUser() {
		return user;
	}
	public void setUser(Member user) {
		this.user = user;
	}
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	public String getPsd() {
		return psd;
	}
	public void setPsd(String psd) {
		this.psd = psd;
	}
	public String getPsd2() {
		return psd2;
	}
	public void setPsd2(String psd2) {
		this.psd2 = psd2;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public void setShow(Map<String,String> show) {
		this.show = show;
	}
	public Map<String,String> getShow() {
		return show;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUrl() {
		return url;
	}
	public String getBodyHtml() {
		return bodyHtml;
	}
	public void setBodyHtml(String bodyHtml) {
		this.bodyHtml = bodyHtml;
	}
	public String getNavHtml() {
		return navHtml;
	}
	public void setNavHtml(String navHtml) {
		this.navHtml = navHtml;
	}
	
	
}
