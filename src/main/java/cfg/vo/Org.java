package cfg.vo;
import common.vo.BaseTO;
public class Org extends BaseTO{
	public String id="";
	public String getId(){return this.id;}
	public void setId(String one){this.id=one;}
	public String orgCode="";
	public String getOrgCode(){return this.orgCode;}
	public void setOrgCode(String one){this.orgCode=one;}
	public String descr="";
	public String getDescr(){return this.descr;}
	public void setDescr(String one){this.descr=one;}
	public cfg.vo.Org parent;
	public cfg.vo.Org getParent(){return this.parent;}
	public void setParent(cfg.vo.Org one){this.parent=one;}
	public String att1="";
	public String getAtt1(){return this.att1;}
	public void setAtt1(String one){this.att1=one;}
	public String att2="";
	public String getAtt2(){return this.att2;}
	public void setAtt2(String one){this.att2=one;}
	public String att3="";
	public String getAtt3(){return this.att3;}
	public void setAtt3(String one){this.att3=one;}
	public String offi="";
	public String getOffi(){return this.offi;}
	public void setOffi(String one){this.offi=one;}
	public String usrct="";
	public String getUsrct(){return this.usrct;}
	public void setUsrct(String one){this.usrct=one;}
}