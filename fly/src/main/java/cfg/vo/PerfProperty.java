package cfg.vo;
import common.vo.BaseTO;
public class PerfProperty extends BaseTO{
	public String id="";
	public String getId(){return this.id;}
	public void setId(String one){this.id=one;}
	public cfg.vo.Perf perf;
	public cfg.vo.Perf getPerf(){return this.perf;}
	public void setPerf(cfg.vo.Perf one){this.perf=one;}
	public cfg.vo.Property property;
	public cfg.vo.Property getProperty(){return this.property;}
	public void setProperty(cfg.vo.Property one){this.property=one;}
	public String editshowhide="";
	public String getEditshowhide(){return this.editshowhide;}
	public void setEditshowhide(String one){this.editshowhide=one;}
	public String kind="";
	public String getKind(){return this.kind;}
	public void setKind(String one){this.kind=one;}
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
	public String usrct="";
	public String getUsrct(){return this.usrct;}
	public void setUsrct(String one){this.usrct=one;}
}