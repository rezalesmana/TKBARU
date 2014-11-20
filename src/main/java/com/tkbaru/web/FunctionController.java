package com.tkbaru.web;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tkbaru.common.Constants;
import com.tkbaru.model.User;
import com.tkbaru.service.FunctionService;

@Controller
public class FunctionController {
	private static final Logger logger = LoggerFactory.getLogger(FunctionController.class);
	
	@Autowired
	FunctionService functionManager;
	
	@RequestMapping(value = "/admin/function.html", method = RequestMethod.GET)
	public String functionPageLoad(Locale locale, Model model) {		

		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_PAGELOAD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_FUNCTION;
	}

	@RequestMapping(value = "/admin/function/add.html", method = RequestMethod.GET)
	public String functionAdd(Locale locale, Model model) {
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_FUNCTION;
	}

	@RequestMapping(value = "/admin/function/edit/{selectedId}.html", method = RequestMethod.GET)
	public String userEdit(Locale locale, Model model, @PathVariable Integer selectedId) {
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		logger.info(String.valueOf(selectedId));
		return Constants.JSPPAGE_FUNCTION;
	}

	@RequestMapping(value = "/admin/function/delete/{selectedId}.html", method = RequestMethod.GET)
	public String functionDelete(Locale locale, Model model, @PathVariable Integer selectedId) {
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_DELETE);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_FUNCTION;
	}

}