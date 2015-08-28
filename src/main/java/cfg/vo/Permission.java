package cfg.vo;
import common.vo.BaseTO;
public class Permission extends BaseTO{
	public String id="";
	public String getId(){return this.id;}
	public void setId(String one){this.id=one;}
	public cfg.vo.Permission parent;
	public cfg.vo.Permission getParent(){return this.parent;}
	public void setParent(cfg.vo.Permission one){this.parent=one;}
	public String namespace="";
	public String getNamespace(){return this.namespace;}
	public void setNamespace(String one){this.namespace=one;}
	public String actionName="";
	public String getActionName(){return this.actionName;}
	public void setActionName(String one){this.actionName=one;}
	public String descr="";
	public String getDescr(){return this.descr;}
	public void setDescr(String one){this.descr=one;}
	public String type="";
	public String getType(){return this.type;}
	public void setType(String one){this.type=one;}
	public String enable="";
	public String getEnable(){return this.enable;}
	public void setEnable(String one){this.enable=one;}
	public String usrct="";
	public String getUsrct(){return this.usrct;}
	public void setUsrct(String one){this.usrct=one;}
}