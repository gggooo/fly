package common.vo;

public class PropertyGridBean {
	String name;
	String value;
	String group;
	
	
	public PropertyGridBean() {
		super();
	}
	
	public PropertyGridBean(String name, String value, String group) {
		super();
		this.name = name;
		this.value = value;
		this.group = group;
	}
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
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	
}
