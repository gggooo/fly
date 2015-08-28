package cfg.vo;
import common.vo.BaseTO;
public class Domain extends BaseTO{
	public String id="";
	public String getId(){return this.id;}
	public void setId(String one){this.id=one;}
	public String domain="";
	public String getDomain(){return this.domain;}
	public void setDomain(String one){this.domain=one;}
	public String descr="";
	public String getDescr(){return this.descr;}
	public void setDescr(String one){this.descr=one;}
	public String enable="";
	public String getEnable(){return this.enable;}
	public void setEnable(String one){this.enable=one;}
	public String pkg="";
	public String getPkg(){return this.pkg;}
	public void setPkg(String one){this.pkg=one;}
	public String usrct="";
	public String getUsrct(){return this.usrct;}
	public void setUsrct(String one){this.usrct=one;}
}