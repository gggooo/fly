package cfg.vo;
import common.vo.BaseTO;
public class FilterProperty extends BaseTO{
	public String id="";
	public String getId(){return this.id;}
	public void setId(String one){this.id=one;}
	public cfg.vo.Filter filter;
	public cfg.vo.Filter getFilter(){return this.filter;}
	public void setFilter(cfg.vo.Filter one){this.filter=one;}
	public cfg.vo.Property property;
	public cfg.vo.Property getProperty(){return this.property;}
	public void setProperty(cfg.vo.Property one){this.property=one;}
	public String op="";
	public String getOp(){return this.op;}
	public void setOp(String one){this.op=one;}
	public String val="";
	public String getVal(){return this.val;}
	public void setVal(String one){this.val=one;}
	public String clazz="";
	public String getClazz(){return this.clazz;}
	public void setClazz(String one){this.clazz=one;}
	public String usrct="";
	public String getUsrct(){return this.usrct;}
	public void setUsrct(String one){this.usrct=one;}
}