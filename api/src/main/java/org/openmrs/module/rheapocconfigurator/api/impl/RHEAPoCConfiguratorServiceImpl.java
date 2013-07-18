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
package org.openmrs.module.rheapocconfigurator.api.impl;

import org.openmrs.EncounterType;
import org.openmrs.PatientIdentifierType;
import org.openmrs.Privilege;
import org.openmrs.Role;
import org.openmrs.api.APIException;
import org.openmrs.api.EncounterService;
import org.openmrs.api.PatientService;
import org.openmrs.api.UserService;
import org.openmrs.api.context.Context;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.rheapocconfigurator.api.RHEAPoCConfiguratorService;
import org.openmrs.module.rheapocconfigurator.api.db.RHEAPoCConfiguratorDAO;

/**
 * It is a default implementation of {@link RHEAPoCConfiguratorService}.
 */
public class RHEAPoCConfiguratorServiceImpl extends BaseOpenmrsService implements RHEAPoCConfiguratorService {
	
	private static final String[] IDENTIFIER_TYPES = new String[]{
		"NID",
		"Primary Care ID Type",
		"Mutuelle",
		"RAMA"
	};
	private static final String[] ENCOUNTER_TYPES = new String[]{
		"ANC OB and Past Medical History",
		"ANC Physical",
		"ANC Testing",
		"ANC Maternal Treatments and Interventions",
		"ANC Referral",
		"ANC Referral Confirmation",
		"ANC Delivery Report",
		"RapidSMS Notification BIRTH",
		"RapidSMS Notification Maternal Death",
		"RapidSMS Notification RISK",
	};
	private static final String[] PROVIDER_PRIVILEGES = new String[]{
		"Add Appointments",
		"Add Encounters",
		"Add Observations",
		"Add People",
		"Can view a result of patient lab test",
		"Delete Appointments",
		"Edit Appointments",
		"Edit FormEntry Archive",
		"Edit FormEntry Queue",
		"Edit People",
		"Exit a patient from care",
		"Form Entry",
		"Manage Implementation Id",
		"Patient Dashboard - View Demographics Section",
		"Patient Dashboard - View Forms Section",
		"Patient Dashboard - View Overview Section",
		"Patient Dashboard - View PMTCT",
		"View FormEntry Error",
		"View Forms",
		"View Locations",
		"View Navigation Menu",
		"View Patient Programs",
		"View Patients",
		"View People",
		"View PMTCT patients in ANC",
		"View PMTCT pediatric tests",
		"View Regimens",
		"View Users",
		"Add ANC and follow-up information",
		"Edit Encounters",
		"Edit Observations",
		"Manage Services and Providers",
		"Manage Tokens",
		"Patient Dashboard - View Appointments Section",
		"Patient Dashboard - View Encounters Section",
		"Patient Dashboard - View Graphs Section",
		"Patient Dashboard - View Patient Summary",
		"Patient Dashboard - View Regimen Section",
		"Search Appointments",
		"View Appointments",
		"View Concepts",
		"View Encounters",
		"View FormEntry Archive",
		"View FormEntry Queue",
		"View lab results",
		"View Observations",
		"View Orders",
		"View PMTCT",
		"View Programs",
		"View Unpublished Forms",
		"View provider appointments"
	};
	
	protected final Log log = LogFactory.getLog(this.getClass());
	
	private RHEAPoCConfiguratorDAO dao;
	
	/**
     * @param dao the dao to set
     */
    public void setDao(RHEAPoCConfiguratorDAO dao) {
	    this.dao = dao;
    }
    
    /**
     * @return the dao
     */
    public RHEAPoCConfiguratorDAO getDao() {
	    return dao;
    }

	@Override
	public boolean setupGlobalProperties() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setupIdentifierTypes() {
		PatientService ps = Context.getPatientService();
		
		try {
			for (String type : IDENTIFIER_TYPES) {
				if (ps.getPatientIdentifierTypeByName(type) == null) {
					PatientIdentifierType pit = new PatientIdentifierType();
					pit.setName(type);
					pit.setDescription(type);
					ps.savePatientIdentifierType(pit);
				}
			}
		} catch (APIException ex) {
			log.error("Failed to setup identifier types", ex);
			return false;
		}
		
		return true;
	}

	@Override
	public boolean setupConfigForPrimaryCareModule() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setupConfigForAppointmentModule() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setupEncounterTypes() {
		EncounterService es = Context.getEncounterService();
		try {
			for (String type : ENCOUNTER_TYPES) {
				if (es.getEncounterType(type) == null) {
					EncounterType et = new EncounterType();
					et.setName(type);
					et.setDescription(type);
					es.saveEncounterType(et);
				}
			}
		} catch (APIException ex) {
			log.error("Failed to setup encounter types", ex);
			return false;
		}
		
		return true;
	}

	@Override
	public boolean setupForms() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setupProviderPrivileges() {
		log.info("------setupProviderPrivileges");
		UserService us = Context.getUserService();
		try {
			Role provider = us.getRole("Provider");
			for (String privilegeName : PROVIDER_PRIVILEGES) {
				log.info(privilegeName);
				Privilege privilege = us.getPrivilege(privilegeName);
				if (privilege==null) {
					privilege = new Privilege();
					privilege.setPrivilege(privilegeName);
					privilege.setName(privilegeName);
					privilege.setDescription(privilegeName);
					us.savePrivilege(privilege);
				}
				provider.addPrivilege(privilege);
			}
			us.saveRole(provider);
		} catch (APIException ex) {
			log.error("Failed to setup provider privileges", ex);
			return false;
		}
		
		return true;
	}
}