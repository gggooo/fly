package cfg.vo;
import common.vo.BaseTO;
public class RolePermission extends BaseTO{
	public cfg.vo.Permission permission;
	public cfg.vo.Permission getPermission(){return this.permission;}
	public void setPermission(cfg.vo.Permission one){this.permission=one;}
	public String usrct="";
	public String getUsrct(){return this.usrct;}
	public void setUsrct(String one){this.usrct=one;}
	public String id="";
	public String getId(){return this.id;}
	public void setId(String one){this.id=one;}
	public cfg.vo.Role role;
	public cfg.vo.Role getRole(){return this.role;}
	public void setRole(cfg.vo.Role one){this.role=one;}
}