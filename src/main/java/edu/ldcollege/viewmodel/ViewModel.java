package edu.ldcollege.viewmodel;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component("viewModel")
@Scope(scopeName = "prototype",proxyMode = ScopedProxyMode.NO)
public class ViewModel<T> {

	private int total;
	private List<T> rows;
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
