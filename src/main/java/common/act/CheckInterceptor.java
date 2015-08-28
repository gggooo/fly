package common.act;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cfg.vo.Bean;
import cfg.vo.Filter;
import cfg.vo.Member;
import cfg.vo.Org;
import cfg.vo.OrgRole;
import cfg.vo.OrgRoleMember;
import cfg.vo.Perf;
import cfg.vo.PerfProperty;
import cfg.vo.Permission;
import cfg.vo.Role;
import cfg.vo.RoleAtt;
import cfg.vo.RoleFilter;
import cfg.vo.RoleMember;
import cfg.vo.RolePerf;
import cfg.vo.RolePermission;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import common.dao.BaseDao;
import common.util.Constant;
import common.util.JspUtil2;
import common.util.Util;

public final class CheckInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;
	
	BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public String intercept(ActionInvocation ai) throws Exception {

		// 确认Session中是否存在MEMBER
		ActionContext ctx = ActionContext.getContext();
		Map session = ctx.getSession();
		Member member = (Member) session.get(Constant.SESSION_MEMBER);
		//TEST TODO
//		member = new Member();
//		member.setId(1);
//
		HttpServletRequest request = (HttpServletRequest)ctx.get(StrutsStatics.HTTP_REQUEST);  

		DetachedCriteria dc1 = DetachedCriteria.forClass(PerfProperty.class);
		dc1.createAlias("property", "p");
		dc1.addOrder(Order.asc("p.ord"));
		dc1.addOrder(Order.asc("p.id"));
		Constant.PPS=baseDao.find(dc1);//TODO 此次可修改用于增加效率
		dc1 = DetachedCriteria.forClass(Bean.class);
		Constant.BEANS=baseDao.find(dc1);//TODO 此次可修改用于增加效率
		
		if (member!=null) {
//		// 存在性判断
			String log;
			//以下四个属性标志哥已什么角色、组织身份（非编制组织）、表现方式、数据过滤方式进入系统
//			Role role =null;
//			Org roleorg = null;
			Perf perf =null;//当前的表现Perf
			Filter filter =null;//当前的数据过滤
			boolean ispermit = false;//是否成功通过该过滤器的标志
			Bean oran =null;//当前处理类的Bean对象
			System.out.println(request.getRequestURL());
			
			//get permissionid from cfg_permission where actionName&namespace=?
			//get permission from role_permission where permission ={permissionid} and role in {MyRoles}
			//if permission is null -> input
			//else
			//get PerfList from perf where permission=?     and bean=? and kind=List  ??
			// if get PerfList is null: -> input(not set perf)
			// else get perfs from role_pref where perf in {PerfList} and role in {MyRoles} order by ord
			// if perfs is null -> input()
			// else get the max ord perf group by perfkind and set in request("permission"+pid,perfs)
//		if (member != null) {
			ActionProxy proxy = ai.getProxy();
			String actionName = proxy.getActionName();
			String namespace = proxy.getNamespace();//.replace("/", "");
			
			
			String start = startWith(actionName);

			//约定：所有的既定标准zsgcPermission的URL用  pkg_zsgc类名   ；所有的非标准Permission的URL用 pkg_c_类名_x 其他使用pkg_cc_x的方式，该方式下，没有对应的oran以及相关默认数据
			//按照以上约定，约束Permission中的namespace与actionname的输入，约束在struts中的配置
			//并体现在下面的找oran袁石类的定义中，该原始类用于接收
			if(Util.notEmptyString(start)){
				//处理标准的增删改查
				int first = actionName.indexOf("_");//目前所有的访问方式由namespace_action的方式，所以在ActionProxy中取到的是一个合集
				namespace="/"+actionName.substring(0, first);
				actionName=actionName.substring(first+1);
				String beanName = actionName.replace(start, "");
				DetachedCriteria dc=DetachedCriteria.forClass(Bean.class);
				dc.add(Restrictions.eq("pkg", namespace.replace("/", ""))).add(Restrictions.eq("bean", beanName));
				oran = baseDao.findBean(dc);
			}else{
				int first = actionName.indexOf("_c_");//目前所有的访问方式由namespace_action的方式，所以在ActionProxy中取到的是一个合集
				if(first>0){
					namespace="/"+actionName.substring(0, first);
					actionName=actionName.substring(first+3);
					int second = actionName.indexOf("_");
					if(second>0){
						String beanName = actionName.substring(0,second);
						DetachedCriteria dc=DetachedCriteria.forClass(Bean.class);
						dc.add(Restrictions.eq("pkg", namespace.replace("/", ""))).add(Restrictions.eq("bean", beanName));
						oran = baseDao.findBean(dc);
					}
				}
			}
			request.setAttribute("oran", oran);
			
//			System.out.println(actionName+namespace);
			DetachedCriteria dc = DetachedCriteria
					.forClass(Permission.class);
			dc.add(Restrictions.eq("namespace", namespace.replaceFirst("/", ""))).add(Restrictions.eq("actionName", actionName));
			Permission permission = baseDao.findBean(dc);
			if(permission!=null){
				//我的角色组中的角色是否有覆盖到该功能Permission
				
				//查找我所属的各种角色
				List<Role> myRoles=new ArrayList<Role>();
				
				//通过人员对应角色
				dc = DetachedCriteria.forClass(RoleMember.class);
				dc.add(Restrictions.eq("member", member));
				List<RoleMember> roleMembers = baseDao.find(dc);
				for(RoleMember rm:roleMembers){
					myRoles.add(rm.getRole());
				}
				//通过我的组织的属性来对应角色
				List<String> atts = Util.findMyOrgsAtts(baseDao,member);
				if(Util.notEmptyList(atts)){
					dc = DetachedCriteria.forClass(RoleAtt.class);
					dc.add(Restrictions.in("att", atts));
					List<Role> roleAtts = baseDao.find(dc);
					myRoles.addAll(roleAtts);
				}
				//TODO 通过我的组织来找对应的角色  ROLEORG 
				
				
				if(Util.notEmptyList(myRoles)){
					
					dc = DetachedCriteria.forClass(RolePermission.class);
//					dc.add(Restrictions.in("role", myrl)).add(Restrictions.eq("permission", permission));
//					RolePermission rp = baseDao.findBean(dc);
					//TODO 在此处加入ROle的优先级控制，优先级高的优先以该Role进入Permission
//					dc.createAlias("role", "r");
					dc.add(Restrictions.eq("permission", permission)).add(Restrictions.in("role", myRoles));
//					dc.addOrder(Order.asc("r.ord"));
					RolePermission rp = baseDao.findBean(dc);//这个角色、功能关系是可以进入该功能的
					
					if(rp!=null){
//						role=rp.getRole();
						dc = DetachedCriteria.forClass(Perf.class);
						dc.add(Restrictions.eq("permission", permission)).addOrder(Order.asc("ord"));
						List<Perf> pl = baseDao.find(dc);
						//Perf分几种情况：①不需要、未设置Perf的 ②唯一Perf ③多个Perf
						//对于1：直接跳转到action 3：找到我的角色组中是否有对于这些Perf的权限，若有，则找到ORD最大的Perf 2：和3一样，找到最后的Perf后，放在request中传到Action中。
						if(Util.notEmptyList(pl)){
							if (pl.size()==1) {
								perf=pl.get(0);
							}else {
								dc = DetachedCriteria.forClass(RolePerf.class);
								dc.createAlias("perf", "p");
								//对应多个perf则找ord最小的一个，权限最大
								dc.add(Restrictions.in("role", myRoles)).add(Restrictions.in("perf", pl)).addOrder(Order.asc("p.ord"));
								RolePerf rperfl = baseDao.findBean(dc);
								if(rperfl!=null){
									perf = rperfl.getPerf();
								}else{
									perf=pl.get(0);//没有设置role与Perf的关系，就在多个Perf中找第一个,ord最大的一个，权限最小
								}
							}
							//request set perf
							log="获取到perf，正确跳转！";
							System.out.println(log);
						}else{
							//直接跳转到action
							log="对应权限没设置perf，正确跳转！";
							System.out.println(log);
						}
						dc = DetachedCriteria.forClass(Filter.class);
						dc.add(Restrictions.eq("permission", permission)).addOrder(Order.desc("ord"));
						List<Filter> fl = baseDao.find(dc);
						if(Util.notEmptyList(fl)){
							if (fl.size()>0) {
								dc = DetachedCriteria.forClass(RoleFilter.class);
								dc.createAlias("filter", "p");
								//对应多个filter则找ord最小的一个，权限最大
								dc.add(Restrictions.in("role", myRoles)).add(Restrictions.in("filter", fl)).addOrder(Order.asc("p.ord"));
								RoleFilter rperfl = baseDao.findBean(dc);
								if(rperfl!=null){
									filter = rperfl.getFilter();
								}
							}
							//request set perf
							log="获取到filter，正确跳转！";
							System.out.println(log);
						}else{
							//直接跳转到action
							log="对应权限没设置filter，正确跳转！";
							System.out.println(log);
						}
						ispermit=true;
					}else{
						log="在我的所处的角色对应的权限里面 ，没有"+permission.getDescr()+"Permission";
						//在我的权限里面 ，没有该Permission
					}
				}else {
					log="我没有权限组 ，但访问了"+permission.getDescr()+"Permission";
					//没有角色组的我，访问了一个带Permission的action 
				}
				
//				request.setAttribute("role", role);
//				request.setAttribute("roleorg", roleorg);
				request.setAttribute("permission", permission);
				request.setAttribute("perf", perf);
				request.setAttribute("filter", filter);
				if("1".equals(permission.getType())){//在LIST页面中加入兄弟Permission
					Permission pp = permission.getParent();
					dc = DetachedCriteria.forClass(RolePermission.class);
					dc.createAlias("permission", "p");
					dc.add(Restrictions.in("role", myRoles)).add(Restrictions.eq("p.parent", pp)).add(Restrictions.eq("p.type", "0"));
					List<RolePermission> rpl = baseDao.find(dc);
					List<Permission> buttonPermission=new ArrayList<Permission>();
					for(RolePermission rp:rpl){
						buttonPermission.add(rp.getPermission());
					}
					request.setAttribute("buttonPermission", buttonPermission);
				}
				//在页面中加入特定权限的button
				//在每个页面中加入“cfg_c_editUnderPerf”权限的链接
				dc = DetachedCriteria.forClass(RolePermission.class);
				dc.createAlias("permission", "p");
				dc.add(Restrictions.in("role", myRoles));
				dc.add(Restrictions.eq("p.namespace", "cfg")).add(Restrictions.eq("p.actionName", "c_Perf_editUnder"));
				RolePermission rp= baseDao.findBean(dc);
				List<Permission> specialPermission=new ArrayList<Permission>();
				if(rp!=null){
					specialPermission.add(rp.getPermission());
				}
				request.setAttribute("specialPermission", specialPermission);
			}else{
				//没有设置该功能Permission,直接跳转
				log="没有设置该功能Permission,直接跳转";
				System.out.println(log);
				ispermit=true;
			}
			if (ispermit) {
//				request.setAttribute("log", log);
				return ai.invoke();
			}else{
				return "NOQX";
				
			}
		}else{
			return "input";
		}
	}
	/**
	 * 判断是否是标准的zsgc模式
	 * 若是则返回是哪种模式。
	 * @param actionName
	 * @return
	 */
	private String startWith(String actionName) {
		String[] ds={"find","add","edit","del","detail","tree","select"};
		int first = actionName.indexOf("_");//目前所有的访问方式由namespace_action的方式，所以在ActionProxy中取到的是一个合集
		String outpkg=actionName.substring(first);
		for(String s:ds){
			if(outpkg.startsWith("_"+s)){
				return s;
			}
//			if(actionName.startsWith(s)){
//				return s;
//			}
		}
		return "";
	}
}
