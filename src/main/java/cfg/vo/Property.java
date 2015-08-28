package cfg.vo;
import common.vo.BaseTO;
public class Property extends BaseTO{
	public Property() {
		super();
	}
	public Property(Bean bean, String property, String type, String descr,
			String length, int ord, String width) {
		super();
		this.bean = bean;
		this.property = property;
		this.type = type;
		this.descr = descr;
		this.length = length;
		this.ord = ord;
		this.width = width;
	}
	public String usrct="";
	public String getUsrct(){return this.usrct;}
	public void setUsrct(String one){this.usrct=one;}
	public String id;
	public String getId(){return this.id;}
	public void setId(String one){this.id=one;}
	public cfg.vo.Bean bean;
	public cfg.vo.Bean getBean(){return this.bean;}
	public void setBean(cfg.vo.Bean one){this.bean=one;}
	public String property="";
	public String getProperty(){return this.property;}
	public void setProperty(String one){this.property=one;}
	public String type="";
	public String getType(){return this.type;}
	public void setType(String one){this.type=one;}
	public String descr="";
	public String getDescr(){return this.descr;}
	public void setDescr(String one){this.descr=one;}
	public String length="";
	public String getLength(){return this.length;}
	public void setLength(String one){this.length=one;}
	public String votype="";
	public String getVotype(){return this.votype;}
	public void setVotype(String one){this.votype=one;}
	public cfg.vo.Myinput myinput;
	public cfg.vo.Myinput getMyinput(){return this.myinput;}
	public void setMyinput(cfg.vo.Myinput one){this.myinput=one;}
	public String required="";
	public String getRequired(){return this.required;}
	public void setRequired(String one){this.required=one;}
	public String valueurl="";
	public String getValueurl(){return this.valueurl;}
	public void setValueurl(String one){this.valueurl=one;}
	public String validType="";
	public String getValidType(){return this.validType;}
	public void setValidType(String one){this.validType=one;}
	public int ord;
	public int getOrd(){return this.ord;}
	public void setOrd(int one){this.ord=one;}
	public String dftvalue="";
	public String getDftvalue(){return this.dftvalue;}
	public void setDftvalue(String one){this.dftvalue=one;}
	public String width="";
	public String getWidth(){return this.width;}
	public void setWidth(String one){this.width=one;}
	public String min="";
	public String getMin(){return this.min;}
	public void setMin(String one){this.min=one;}
	public String max="";
	public String getMax(){return this.max;}
	public void setMax(String one){this.max=one;}
	public String showvalue="";
	public String getShowvalue(){return this.showvalue;}
	public void setShowvalue(String one){this.showvalue=one;}

}