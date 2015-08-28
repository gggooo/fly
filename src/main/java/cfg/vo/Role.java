package cfg.vo;
import common.vo.BaseTO;
public class Role extends BaseTO{
	public int ord;
	public int getOrd(){return this.ord;}
	public void setOrd(int one){this.ord=one;}
	public String id="";
	public String getId(){return this.id;}
	public void setId(String one){this.id=one;}
	public String role="";
	public String getRole(){return this.role;}
	public void setRole(String one){this.role=one;}
	public String descr="";
	public String getDescr(){return this.descr;}
	public void setDescr(String one){this.descr=one;}
	public String usrct="";
	public String getUsrct(){return this.usrct;}
	public void setUsrct(String one){this.usrct=one;}
}