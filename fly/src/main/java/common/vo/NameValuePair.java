package common.vo;

public class NameValuePair extends BaseTO {
	String name;
	String value;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public void setValue(int value) {
		this.value = String.valueOf(value);
	}
	public NameValuePair(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}
	public NameValuePair() {
		super();
	}
}
