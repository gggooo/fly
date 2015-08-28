package cfg.vo;
import common.vo.BaseTO;
public class Cons extends BaseTO{
	public String id="";
	public String getId(){return this.id;}
	public void setId(String one){this.id=one;}
	public String name="";
	public String getName(){return this.name;}
	public void setName(String one){this.name=one;}
	public String value="";
	public String getValue(){return this.value;}
	public void setValue(String one){this.value=one;}
	public String descr="";
	public String getDescr(){return this.descr;}
	public void setDescr(String one){this.descr=one;}
	public String enable="";
	public String getEnable(){return this.enable;}
	public void setEnable(String one){this.enable=one;}
	public String ord="";
	public String getOrd(){return this.ord;}
	public void setOrd(String one){this.ord=one;}
	public cfg.vo.Domain domain;
	public cfg.vo.Domain getDomain(){return this.domain;}
	public void setDomain(cfg.vo.Domain one){this.domain=one;}
	public String usrct="";
	public String getUsrct(){return this.usrct;}
	public void setUsrct(String one){this.usrct=one;}
}