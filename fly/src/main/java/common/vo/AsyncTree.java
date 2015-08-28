package common.vo;

import java.util.List;

public class AsyncTree {
	String id;
	String text;
	String state;
	String checked;
	List<AsyncTree> children;
	
	public AsyncTree() {
		super();
	}
	public AsyncTree(String id, String text, String state) {
		this.id = id;
		this.text = text;
		this.state = state;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	public List<AsyncTree> getChildren() {
		return children;
	}
	public void setChildren(List<AsyncTree> children) {
		this.children = children;
	}
}
