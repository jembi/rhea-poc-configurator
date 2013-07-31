/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.rheapocconfigurator.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.rheapocconfigurator.AuthenticationTestInput;
import org.openmrs.module.rheapocconfigurator.ValidateFormsResult;
import org.openmrs.module.rheapocconfigurator.api.RHEAPoCConfiguratorService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * The main controller.
 */
@Controller
@SessionAttributes("tests")
public class  RHEAPoCConfiguratorTestsController {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	@RequestMapping(value = "/module/rheapocconfigurator/testSystem", method = RequestMethod.GET)
	public void manage(ModelMap model) {
		if (model.get("tests")==null)
			model.put("tests", new Tests());
	}
	
	@RequestMapping(value = "/module/rheapocconfigurator/testSystem", method = RequestMethod.POST)
	public ModelAndView runConfigurator(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("tests") Tests tests, BindingResult errors) {
		RHEAPoCConfiguratorService cs = Context.getService(RHEAPoCConfiguratorService.class);
		
		try {
			tests.statusAuthTest = cs.performAuthenticationTest(tests.getAuthTestInput());
		} catch (UnexpectedRollbackException ex) {
			tests.statusAuthTest = false;
		}
		try {
			tests.formsResult = cs.validateFormConcepts();
		} catch (UnexpectedRollbackException ex) {
			if (tests.formsResult==null)
				tests.formsResult = new ValidateFormsResult();
			tests.formsResult.setStatus(false);
		}
		
		return new ModelAndView("redirect:testSystem.form");
	}
	
	public static class Tests {
		private Boolean statusAuthTest;
		
		private AuthenticationTestInput authTestInput = new AuthenticationTestInput();
		private ValidateFormsResult formsResult = null;
		
		
		public Boolean getOverallStatus() {
			if (statusAuthTest==null || formsResult==null)
				return null;
			return (statusAuthTest && formsResult.getStatus());
		}

		public Boolean getStatusAuthTest() {
			return statusAuthTest;
		}

		public void setStatusAuthTest(Boolean statusAuthTest) {
			this.statusAuthTest = statusAuthTest;
		}

		public Boolean getStatusFormsValidation() {
			return formsResult!=null && formsResult.getStatus();
		}

		public AuthenticationTestInput getAuthTestInput() {
			return authTestInput;
		}

		public void setAuthTestInput(AuthenticationTestInput authTestInput) {
			this.authTestInput = authTestInput;
		}

		public ValidateFormsResult getFormsResult() {
			return formsResult;
		}

		public void setFormsResult(ValidateFormsResult formsResult) {
			this.formsResult = formsResult;
		}
	}
}
