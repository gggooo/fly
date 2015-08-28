package cfg.vo;
import common.vo.BaseTO;
public class Member extends BaseTO{
	public String id="";
	public String getId(){return this.id;}
	public void setId(String one){this.id=one;}
	public cfg.vo.Org org;
	public cfg.vo.Org getOrg(){return this.org;}
	public void setOrg(cfg.vo.Org one){this.org=one;}
	public String loginId="";
	public String getLoginId(){return this.loginId;}
	public void setLoginId(String one){this.loginId=one;}
	public String descr="";
	public String getDescr(){return this.descr;}
	public void setDescr(String one){this.descr=one;}
	public String psd="";
	public String getPsd(){return this.psd;}
	public void setPsd(String one){this.psd=one;}
	public String usrct="";
	public String getUsrct(){return this.usrct;}
	public void setUsrct(String one){this.usrct=one;}
}