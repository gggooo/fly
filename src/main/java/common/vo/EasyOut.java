package common.vo;

import java.util.List;

public class EasyOut<T> {
	List<T> rows;
	int total;
	List<T> footer;
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public void setFooter(List<T> footer) {
		this.footer = footer;
	}
	public List<T> getFooter() {
		return footer;
	}
	
}
