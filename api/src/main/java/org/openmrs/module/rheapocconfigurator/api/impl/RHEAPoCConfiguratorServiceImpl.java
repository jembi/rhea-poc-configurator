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
import org.openmrs.api.APIException;
import org.openmrs.api.EncounterService;
import org.openmrs.api.PatientService;
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
		String[] types = new String[]{
			"NID",
			"Primary Care ID Type",
			"Mutuelle",
			"RAMA"
		};
		PatientService ps = Context.getPatientService();
		
		try {
			for (String type : types) {
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
		String[] types = new String[]{
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
		
		EncounterService es = Context.getEncounterService();
		try {
			for (String type : types) {
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
		// TODO Auto-generated method stub
		return false;
	}
}