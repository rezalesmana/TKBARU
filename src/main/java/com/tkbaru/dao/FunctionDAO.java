package com.tkbaru.dao;

import java.util.List;

import com.tkbaru.model.Function;

public interface FunctionDAO {
	public List<Function> getAllFunctions();
	public Function getFunctionById(int selectedId);
	public void addFunction(Function func);
	public void editFunction(Function func);
	public void deleteFunction(int selectedId);
}
