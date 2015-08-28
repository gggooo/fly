package cfg.vo;
import common.vo.BaseTO;
public class OrgRole extends BaseTO{
	public String id;
	public String getId(){return this.id;}
	public void setId(String one){this.id=one;}
	public cfg.vo.Org org;
	public cfg.vo.Org getOrg(){return this.org;}
	public void setOrg(cfg.vo.Org one){this.org=one;}
	public cfg.vo.Role role;
	public cfg.vo.Role getRole(){return this.role;}
	public void setRole(cfg.vo.Role one){this.role=one;}
}