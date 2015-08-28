package cfg.vo;
import common.vo.BaseTO;
public class RoleFilter extends BaseTO{
	public String id="";
	public String getId(){return this.id;}
	public void setId(String one){this.id=one;}
	public cfg.vo.Role role;
	public cfg.vo.Role getRole(){return this.role;}
	public void setRole(cfg.vo.Role one){this.role=one;}
	public cfg.vo.Filter filter;
	public cfg.vo.Filter getFilter(){return this.filter;}
	public void setFilter(cfg.vo.Filter one){this.filter=one;}
	public String usrct="";
	public String getUsrct(){return this.usrct;}
	public void setUsrct(String one){this.usrct=one;}
}