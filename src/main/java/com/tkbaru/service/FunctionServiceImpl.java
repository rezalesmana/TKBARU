package com.tkbaru.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tkbaru.dao.FunctionDAO;
import com.tkbaru.model.Function;

public class FunctionServiceImpl implements FunctionService {

	@Autowired
	FunctionDAO functionDAO;
	
	@Override
	public List<Function> getAllFunctions() {
		
		return functionDAO.getAllFunctions();
	}

	@Override
	public Function getFunctionById(int selectedId) {
		
		return functionDAO.getFunctionById(selectedId);
	}

	@Override
	public void addFunction(Function func) {

		functionDAO.addFunction(func);
	}

	@Override
	public void editFunction(Function func) {
		
		functionDAO.editFunction(func);
	}

	@Override
	public void deleteFunction(int selectedId) {
		
		functionDAO.deleteFunction(selectedId);
	}

}
