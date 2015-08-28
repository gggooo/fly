package cfg.vo;
import common.vo.BaseTO;
public class Quote extends BaseTO{
	public String id="";
	public String getId(){return this.id;}
	public void setId(String one){this.id=one;}
	public cfg.vo.Perf oranPerf;
	public cfg.vo.Perf getOranPerf(){return this.oranPerf;}
	public void setOranPerf(cfg.vo.Perf one){this.oranPerf=one;}
	public cfg.vo.Property oranProperty;
	public cfg.vo.Property getOranProperty(){return this.oranProperty;}
	public void setOranProperty(cfg.vo.Property one){this.oranProperty=one;}
	public cfg.vo.Perf quotePerf;
	public cfg.vo.Perf getQuotePerf(){return this.quotePerf;}
	public void setQuotePerf(cfg.vo.Perf one){this.quotePerf=one;}
	public cfg.vo.Property quoteProperty;
	public cfg.vo.Property getQuoteProperty(){return this.quoteProperty;}
	public void setQuoteProperty(cfg.vo.Property one){this.quoteProperty=one;}
	public String divTitle="";
	public String getDivTitle(){return this.divTitle;}
	public void setDivTitle(String one){this.divTitle=one;}
	public String usrct="";
	public String getUsrct(){return this.usrct;}
	public void setUsrct(String one){this.usrct=one;}
}