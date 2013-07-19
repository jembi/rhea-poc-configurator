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
import org.openmrs.module.rheapocconfigurator.GlobalPropertiesInput;
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
@SessionAttributes("config")
public class  RHEAPoCConfiguratorManageController {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	@RequestMapping(value = "/module/rheapocconfigurator/manage", method = RequestMethod.GET)
	public void manage(ModelMap model) {
		model.addAttribute("user", Context.getAuthenticatedUser());
		if (model.get("config")==null)
			model.put("config", new Config());
	}
	
	@RequestMapping(value = "/module/rheapocconfigurator/manage", method = RequestMethod.POST)
	public ModelAndView runConfigurator(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("config") Config config, BindingResult errors) {
		RHEAPoCConfiguratorService cs = Context.getService(RHEAPoCConfiguratorService.class);
		
		if (config.statusIdentifierTypes == null || config.statusIdentifierTypes == false) {
			try {
				config.statusIdentifierTypes = cs.setupIdentifierTypes();
			} catch (UnexpectedRollbackException ex) {
				config.statusIdentifierTypes = false;
			}
		}
		if (config.statusGlobalProperties == null || config.statusGlobalProperties == false) {
			try {
				config.statusGlobalProperties = cs.setupGlobalProperties(config.getGlobalPropsInput());
			} catch (UnexpectedRollbackException ex) {
				config.statusGlobalProperties = false;
			}
		}
		if (config.statusEncounterTypes == null || config.statusEncounterTypes == false) {
			try {
				config.statusEncounterTypes = cs.setupEncounterTypes();
			} catch (UnexpectedRollbackException ex) {
				config.statusEncounterTypes = false;
			}
		}
		if (config.statusForms == null || config.statusForms == false) {
			try {
				config.statusForms = cs.setupForms();
			} catch (UnexpectedRollbackException ex) {
				config.statusForms = false;
			}
		}
		if (config.statusProviderPrivileges == null || config.statusProviderPrivileges == false) {
			try {
				config.statusProviderPrivileges = cs.setupProviderPrivileges();
			} catch (UnexpectedRollbackException ex) {
				config.statusProviderPrivileges = false;
			}
		}
		
		return new ModelAndView("redirect:manage.form");
	}
	
	public static class Config {
		private Boolean statusGlobalProperties;
		private Boolean statusIdentifierTypes;
		private Boolean statusEncounterTypes;
		private Boolean statusForms;
		private Boolean statusProviderPrivileges;
		
		private GlobalPropertiesInput globalPropsInput = new GlobalPropertiesInput();
		
		
		public Boolean getOverallStatus() {
			if (statusGlobalProperties==null || statusIdentifierTypes==null ||
				statusEncounterTypes==null || statusForms==null || statusProviderPrivileges==null)
				return null;
			return (statusGlobalProperties && statusIdentifierTypes && statusEncounterTypes && statusForms &&
				statusProviderPrivileges);
		}
		

		public Boolean getStatusGlobalProperties() {
			return statusGlobalProperties;
		}

		public void setStatusGlobalProperties(Boolean statusGlobalProperties) {
			this.statusGlobalProperties = statusGlobalProperties;
		}

		public Boolean getStatusIdentifierTypes() {
			return statusIdentifierTypes;
		}

		public void setStatusIdentifierTypes(Boolean statusIdentifierTypes) {
			this.statusIdentifierTypes = statusIdentifierTypes;
		}

		public Boolean getStatusEncounterTypes() {
			return statusEncounterTypes;
		}

		public void setStatusEncounterTypes(Boolean statusEncounterTypes) {
			this.statusEncounterTypes = statusEncounterTypes;
		}

		public Boolean getStatusForms() {
			return statusForms;
		}

		public void setStatusForms(Boolean statusForms) {
			this.statusForms = statusForms;
		}

		public Boolean getStatusProviderPrivileges() {
			return statusProviderPrivileges;
		}

		public void setStatusProviderPrivileges(Boolean statusProviderPrivileges) {
			this.statusProviderPrivileges = statusProviderPrivileges;
		}

		public GlobalPropertiesInput getGlobalPropsInput() {
			return globalPropsInput;
		}

		public void setGlobalPropsInput(GlobalPropertiesInput globalPropsInput) {
			this.globalPropsInput = globalPropsInput;
		}
	}
}
