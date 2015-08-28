package common.vo;

import java.io.Serializable;

public class FootPerf implements Serializable {
	private String field;
	private int maximum=2;
	String kind;
	public FootPerf() {
		super();
	}
	public FootPerf(String property,  String kind) {
		this.setField(property);
		this.kind = kind;
	}
	public FootPerf(String property, int maximum, String kind) {
		this.setField(property);
		this.setMaximum(maximum);
		this.kind = kind;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public int getMaximum() {
		return maximum;
	}
	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}
	
}
