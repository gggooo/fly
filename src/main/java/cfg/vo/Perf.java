package cfg.vo;
import common.vo.BaseTO;
public class Perf extends BaseTO{
	public String id="";
	public String getId(){return this.id;}
	public void setId(String one){this.id=one;}
	public cfg.vo.Bean bean;
	public cfg.vo.Bean getBean(){return this.bean;}
	public void setBean(cfg.vo.Bean one){this.bean=one;}
	public cfg.vo.Permission permission;
	public cfg.vo.Permission getPermission(){return this.permission;}
	public void setPermission(cfg.vo.Permission one){this.permission=one;}
	public String descr="";
	public String getDescr(){return this.descr;}
	public void setDescr(String one){this.descr=one;}
	public String ord="";
	public String getOrd(){return this.ord;}
	public void setOrd(String one){this.ord=one;}
	public String layout="";
	public String getLayout(){return this.layout;}
	public void setLayout(String one){this.layout=one;}
	public String usrct="";
	public String getUsrct(){return this.usrct;}
	public void setUsrct(String one){this.usrct=one;}
}