package cfg.vo;


import java.util.List;

public class Cube {
	String descr;
	int kind;
	int quote;
	CubeTd[][] tds;
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public int getKind() {
		return kind;
	}
	public void setKind(int kind) {
		this.kind = kind;
	}
	public CubeTd[][] getTds() {
		return tds;
	}
	public void setTds(CubeTd[][] tds) {
		this.tds = tds;
	}
	public int getQuote() {
		return quote;
	}
	public void setQuote(int quote) {
		this.quote = quote;
	}
	
}