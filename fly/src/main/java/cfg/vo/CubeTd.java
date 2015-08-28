package cfg.vo;

public class CubeTd {
	int colspan;
	int rowspan;
	String descr;
	String style;
	private boolean clearDescr;
	int ppid;
	private String pn;
	private String pnDescr;
	public int getColspan() {
		return colspan;
	}
	public void setColspan(int colspan) {
		this.colspan = colspan;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public int getPpid() {
		return ppid;
	}
	public void setPpid(int ppid) {
		this.ppid = ppid;
	}
	public int getRowspan() {
		return rowspan;
	}
	public void setRowspan(int rowspan) {
		this.rowspan = rowspan;
	}
	public String getPn() {
		return pn;
	}
	public void setPn(String pn) {
		this.pn = pn;
	}
	public boolean isClearDescr() {
		return clearDescr;
	}
	public void setClearDescr(boolean clearDescr) {
		this.clearDescr = clearDescr;
	}
	public String getPnDescr() {
		return pnDescr;
	}
	public void setPnDescr(String pnDescr) {
		this.pnDescr = pnDescr;
	}
	
}