package cfg.vo;
import common.vo.BaseTO;
public class OrgRoleMember extends BaseTO{
	public String id;
	public String getId(){return this.id;}
	public void setId(String one){this.id=one;}
	public cfg.vo.OrgRole orgRole;
	public cfg.vo.OrgRole getOrgRole(){return this.orgRole;}
	public void setOrgRole(cfg.vo.OrgRole one){this.orgRole=one;}
	public cfg.vo.Member member;
	public cfg.vo.Member getMember(){return this.member;}
	public void setMember(cfg.vo.Member one){this.member=one;}
	public cfg.vo.Org org;
	public cfg.vo.Org getOrg(){return this.org;}
	public void setOrg(cfg.vo.Org one){this.org=one;}
}