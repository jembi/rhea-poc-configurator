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
package org.openmrs.module.rheapocconfigurator.api;

import org.openmrs.annotation.Authorized;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.rheapocconfigurator.GlobalPropertiesInput;
import org.openmrs.util.PrivilegeConstants;
import org.springframework.transaction.annotation.Transactional;

/**
 * This service exposes module's core functionality. It is a Spring managed bean which is configured in moduleApplicationContext.xml.
 * <p>
 * It can be accessed only via Context:<br>
 * <code>
 * Context.getService(RHEAPoCConfiguratorService.class).someMethod();
 * </code>
 * 
 * @see org.openmrs.api.context.Context
 */
@Transactional
public interface RHEAPoCConfiguratorService extends OpenmrsService {
     
	@Authorized({PrivilegeConstants.MANAGE_IDENTIFIER_TYPES})
	boolean setupIdentifierTypes();
	@Authorized({PrivilegeConstants.MANAGE_GLOBAL_PROPERTIES})
	boolean setupGlobalProperties(GlobalPropertiesInput input);
	@Authorized({PrivilegeConstants.MANAGE_ENCOUNTER_TYPES})
	boolean setupEncounterTypes();
	@Authorized({PrivilegeConstants.MANAGE_FORMS})
	boolean setupForms();
	@Authorized({PrivilegeConstants.MANAGE_PRIVILEGES})
	boolean setupProviderPrivileges();
	@Authorized({PrivilegeConstants.MANAGE_PERSON_ATTRIBUTE_TYPES})
	boolean setupProviderAttributes();
}