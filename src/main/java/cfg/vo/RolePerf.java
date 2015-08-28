package cfg.vo;
import common.vo.BaseTO;
public class RolePerf extends BaseTO{
	public String id="";
	public String getId(){return this.id;}
	public void setId(String one){this.id=one;}
	public cfg.vo.Role role;
	public cfg.vo.Role getRole(){return this.role;}
	public void setRole(cfg.vo.Role one){this.role=one;}
	public cfg.vo.Perf perf;
	public cfg.vo.Perf getPerf(){return this.perf;}
	public void setPerf(cfg.vo.Perf one){this.perf=one;}
	public String usrct="";
	public String getUsrct(){return this.usrct;}
	public void setUsrct(String one){this.usrct=one;}
}