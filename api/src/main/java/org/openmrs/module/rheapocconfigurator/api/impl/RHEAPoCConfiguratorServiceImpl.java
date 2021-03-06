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

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.openmrs.Concept;
import org.openmrs.ConceptMap;
import org.openmrs.EncounterType;
import org.openmrs.Form;
import org.openmrs.GlobalProperty;
import org.openmrs.PatientIdentifierType;
import org.openmrs.PersonAttributeType;
import org.openmrs.Privilege;
import org.openmrs.RelationshipType;
import org.openmrs.Role;
import org.openmrs.VisitType;
import org.openmrs.api.APIException;
import org.openmrs.api.AdministrationService;
import org.openmrs.api.ConceptService;
import org.openmrs.api.EncounterService;
import org.openmrs.api.FormService;
import org.openmrs.api.PatientService;
import org.openmrs.api.PersonService;
import org.openmrs.api.UserService;
import org.openmrs.api.VisitService;
import org.openmrs.api.context.Context;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.htmlformentry.HtmlForm;
import org.openmrs.module.htmlformentry.HtmlFormEntryService;
import org.openmrs.module.rheapocadapter.handler.ClientRegistryService;
import org.openmrs.module.rheapocconfigurator.AuthenticationTestInput;
import org.openmrs.module.rheapocconfigurator.GlobalPropertiesInput;
import org.openmrs.module.rheapocconfigurator.ValidateFormsResult;
import org.openmrs.module.rheapocconfigurator.api.RHEAPoCConfiguratorService;
import org.openmrs.module.rheapocconfigurator.api.db.RHEAPoCConfiguratorDAO;

/**
 * It is a default implementation of {@link RHEAPoCConfiguratorService}.
 */
public class RHEAPoCConfiguratorServiceImpl extends BaseOpenmrsService implements RHEAPoCConfiguratorService {
	
	public static final String[] IDENTIFIER_TYPES = new String[]{
		"NID",
		"Primary Care ID Type",
		"Mutuelle",
		"RAMA"
	};
	public static final String[] ENCOUNTER_TYPES = new String[]{
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
	public static final String[] PROVIDER_PRIVILEGES = new String[]{
		"Add Appointments",
		"Add Encounters",
		"Add Observations",
		"Add People",
		"Add Patients",
		"Add Users",
		"Can view a result of patient lab test",
		"Delete Appointments",
		"Edit Appointments",
		"Edit FormEntry Archive",
		"Edit FormEntry Queue",
		"Edit People",
		"Edit Patients",
		"Edit Users",
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
		"View provider appointments",
		"Generate Batch of Identifiers",
		"Manage Identifier Sources",
		"View Patient Identifiers",
		"View Providers",
		"Manage Encounter Roles",
		"View Visits",
		"View Encounter Types",
	};
	public static final FormMetadata[] FORMS = new FormMetadata[]{
		new FormMetadata("RHEA ANC 1: Past Medical History", "1.0", ENCOUNTER_TYPES[0], "ANC Past Medical History.html"),
		new FormMetadata("RHEA ANC 2: Physical Examination", "1.0", ENCOUNTER_TYPES[1], "ANC Physical Examination.html"),
		new FormMetadata("RHEA ANC 3: Investigation", "1.0", ENCOUNTER_TYPES[2], "ANC Investigation.html"),
		new FormMetadata("RHEA ANC 4: Referral Form", "1.0", ENCOUNTER_TYPES[4], "ANC Referral Form.html"),
		new FormMetadata("RHEA ANC 5: Referral Confirmation Form", "1.0", ENCOUNTER_TYPES[5], "ANC Referral Confirmation Form.html"),
		new FormMetadata("RHEA ANC 6: Delivery Report", "1.0", ENCOUNTER_TYPES[6], "ANC Delivery Report.html")
	};
	public static final GlobalProperty[] GLOBAL_PROPERTIES = new GlobalProperty[]{
		new GlobalProperty("registration.barCodeCount", "4"),
		new GlobalProperty("registration.healthCenterPersonAttribute", "health center"),
		new GlobalProperty("registration.insuranceNumberConcept", "6741"),
		new GlobalProperty("registration.insuranceTypeConcept", "6740"),
		new GlobalProperty("registration.insuranceTypeConceptAnswers", "6738, 6739, 6955, 6956, 6957, 1107"),
		new GlobalProperty("registration.nationalIdType", "NID"),
		new GlobalProperty("registration.restrictSearchByHealthCenter", "false"),
		new GlobalProperty("registration.serviceRequestedConcept", "6702"),
		
		new GlobalProperty("rheapocadapter.encounterType", "ANTENATAL CLINIC, ANC, ANC Referral, ANC Referral Confirmation, ANC OB and Past Medical History, ANC Physical, ANC Testing, ANC Maternal Treatments and Interventions, ANC Delivery Report"),

		new GlobalProperty("htmlformentry.dateFormat", "MM/dd/yyyy"),
	};
	public static final String VISIT_TYPE = "Primary Care Outpatient";
	public static final String VISIT_DESCRIPTION = "Represents a single day primary care visit to a health center";
	
	public static final String[] RHIE_MAPPINGS = new String[]{"ICD-10", "LOINC", "RWCS"};
	
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
	public boolean setupIdentifierTypes() {
		log.info("Setting up identifier types");
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
	

	/***
	 * setupIdentifierTypes needs to run first!
	 */
	@Override
	public boolean setupGlobalProperties(GlobalPropertiesInput input) {
		log.info("Setting up global properties");
		AdministrationService as = Context.getAdministrationService();
		try {
			for (GlobalProperty gp : GLOBAL_PROPERTIES) {
				as.saveGlobalProperty(gp);
			}
			
			as.saveGlobalProperty(new GlobalProperty("rheapocadapter.hostname", input.getHimHost()));
			as.saveGlobalProperty(new GlobalProperty("rheapocadapter.username", input.getHimUsername()));
			as.saveGlobalProperty(new GlobalProperty("rheapocadapter.password", input.getHimPassword()));
			as.saveGlobalProperty(new GlobalProperty("scheduler.username", input.getSchedulerUsername()));
			as.saveGlobalProperty(new GlobalProperty("scheduler.password", input.getSchedulerPassword()));
			as.saveGlobalProperty(new GlobalProperty("registration.defaultLocationCode", input.getLocationID()));
			as.saveGlobalProperty(new GlobalProperty("registration.rwandaLocationCodes", input.getLocationName() + ":" + input.getLocationFOSAID()));
			as.saveGlobalProperty(new GlobalProperty("rheapocadapter.sendingFacility", input.getLocationFOSAID()));
			
			RelationshipType rs = Context.getPersonService().getRelationshipTypeByName("Parent/Child");
			if (rs==null) {
				log.warn("Parent/Child relationship type not found");
				return false;
			} else {
				as.saveGlobalProperty(new GlobalProperty("registration.parentChildRelationshipTypeId", rs.getId().toString()));
			}
			
			PatientIdentifierType pit = Context.getPatientService().getPatientIdentifierTypeByName("Primary Care ID Type");
			if (pit==null) {
				log.warn("Primary Care ID Type not found");
				return false;
			} else {
				as.saveGlobalProperty(new GlobalProperty("registration.primaryIdentifierType", pit.getId().toString()));
			}
			
			as.saveGlobalProperty(new GlobalProperty("registration.otherIdentifierTypes", getAllIdentifierTypeIDsAsString()));
		} catch (APIException ex) {
			log.error("Failed to setup global properties", ex);
			return false;
		}
		
		return true;
	}
	
	private String getAllIdentifierTypeIDsAsString() {
		StringBuilder res = new StringBuilder();
		boolean first = true;
		
		for (PatientIdentifierType pid : Context.getPatientService().getAllPatientIdentifierTypes()) {
			if (!first) {
				res.append(",");
			} else {
				first = false;
			}
			res.append(pid.getId());
		}
		
		return res.toString();
	}

	@Override
	public boolean setupEncounterTypes() {
		log.info("Setting up encounter types");
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

	/***
	 * setupEncounterTypes needs to run first!
	 */
	@Override
	public boolean setupForms() {
		log.info("Setting up forms");
		FormService fs = Context.getFormService();
		EncounterService es = Context.getEncounterService();
		HtmlFormEntryService hfes = Context.getService(HtmlFormEntryService.class);
		
		try {
			for (FormMetadata fm : FORMS) {
				Form form = fs.getForm(fm.name);
				HtmlForm htmlForm = new HtmlForm();
				if (form==null) {
					form = new Form();
				} else {
					htmlForm = hfes.getHtmlFormByForm(form);
				}
				
				InputStream file = getClass().getClassLoader().getResourceAsStream(fm.file);
				StringWriter sw = new StringWriter();
				IOUtils.copy(file, sw);
				form.setName(fm.name);
				form.setVersion(fm.version);
				form.setPublished(true);
				form.setEncounterType(es.getEncounterType(fm.encounterType));
				fs.saveForm(form);
				
				htmlForm.setForm(form);
				htmlForm.setDescription(fm.name);
				htmlForm.setName(fm.name);
				htmlForm.setXmlData(sw.toString());
				hfes.saveHtmlForm(htmlForm);
				
				try {
					file.close();
				} catch (Exception ex) {
					//quiet!
				}
			}
		} catch (APIException ex) {
			log.error("Failed to setup forms", ex);
			return false;
		} catch (IOException ex) {
			log.error("Failed to setup forms", ex);
			return false;
		}
		
		return true;
	}


	@Override
	public boolean setupProviderPrivileges() {
		log.info("Setting up provider privileges");
		UserService us = Context.getUserService();
		try {
			Role provider = us.getRole("Provider");
			for (String privilegeName : PROVIDER_PRIVILEGES) {
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
	
	@Override
	public boolean setupProviderAttributes() {
		log.info("Setting up provider attributes");
		PersonService ps = Context.getPersonService();
		
		try {
			if (ps.getPersonAttributeTypeByName("NID")!=null)
				return true;
			
			PersonAttributeType pat = new PersonAttributeType();
			pat.setName("NID");
			pat.setDescription("Rwandan national identifier");
			pat.setFormat("java.lang.String");
			ps.savePersonAttributeType(pat);
		} catch (APIException ex) {
			log.error("Failed to setup provider NID attribute", ex);
			return false;
		}
		return true;
	}
	
	@Override
	public boolean setupVisitTypes() {
		log.info("Setting up visit types");
		
		VisitService vs = Context.getVisitService();
		try {
			for (VisitType vt : vs.getAllVisitTypes()) {
				if (VISIT_TYPE.equalsIgnoreCase(vt.getName()))
					return true;
			}
			
			vs.saveVisitType(new VisitType(VISIT_TYPE, VISIT_DESCRIPTION));
		} catch (APIException ex) {
			log.error("Failed to setup visit types", ex);
			return false;
		}
		
		return true;
	}


	@Override
	public boolean performAuthenticationTest(AuthenticationTestInput input) {
		log.info("Performing OpenHIM connection and authentication test");
		
		try {
			ClientRegistryService crs = new ClientRegistryService();
			String result = crs.getClient("NID-" + input.getTestPatientNID());
			log.info("Response: " + result);
			
			if (result==null || result.isEmpty()) {
				log.info("Authentication test failed");
				return false;
			}
			
			if (!containsPIDWithNID(result, input.getTestPatientNID())) {
				log.info("Authentication test failed");
				return false;
			}
			
			log.info("Authentication test successful");
			return true;
		} catch (Exception ex) {
			log.error("Authentication test failed");
			log.error("", ex);
			return false;
		}
	}
	
	protected static boolean containsPIDWithNID(String adt_a05, String expectedNID) {
		return adt_a05.replaceAll("\r", "").replaceAll("\n", "").matches(
			".*<PID\\.3>(\\s*)<CX\\.1>" + expectedNID + "</CX\\.1>(\\s*)" +
			"<CX\\.5>NID</CX\\.5>(\\s*)</PID\\.3>.*"
		);
	}

	@Override
	public ValidateFormsResult validateFormConcepts() {
		log.info("Performing form concept validations");
		ValidateFormsResult result = new ValidateFormsResult();
		FormService fs = Context.getFormService();
		HtmlFormEntryService hfes = Context.getService(HtmlFormEntryService.class);
		ConceptService cs = Context.getConceptService();
		
		result.setStatus(true);
		
		try {
			for (FormMetadata fm : FORMS) {
				Form form = fs.getForm(fm.name);
				if (form==null) continue;
				
				HtmlForm htmlForm = hfes.getHtmlFormByForm(form);
				if (htmlForm==null) continue;
				
				for (Integer conceptId : getConceptIds(htmlForm.getXmlData(), result)) {
					validateConcept(conceptId, cs.getConcept(conceptId), result);
				}
			}
		} catch (APIException ex) {
			log.error("Failed to perform validation tests", ex);
			result.setStatus(false);
			return result;
		}
        
		if (result.getStatus()) {
			log.info("Form concept validation successful");
		} else {
			log.info("Form concept validation failed");
		}
		
		for (String error : result.getErrors()) {
			log.error(error);
		}
		
		return result;
	}
	
	protected static List<Integer> getConceptIds(String formBody, ValidateFormsResult formsResult) {
		List<Integer> result = new LinkedList<Integer>();
		int i = 0;
		
		//remove all html comments
		formBody = formBody.replaceAll("(?s)<!--.*?-->", "");
		
		while (i>=0 && i<formBody.length()) {
			i = formBody.indexOf("<obs", i);
			if (i>=0 && i<formBody.length()) {
				int j = formBody.indexOf("conceptId", i);
				if (j>=0 && j<formBody.length()) {
					int start = formBody.indexOf("\"", j);
					int end = formBody.indexOf("\"", start+1);
					String conceptId = formBody.substring(start+1, end);
					try {
						result.add(Integer.parseInt(conceptId));
					} catch (NumberFormatException ex) {
						//We won't set the result status to false. We'll rather treat this error as a warning.
						formsResult.addError("(Concept ID: " + conceptId + ") Warning: Failed to parse");
					}
				}
				i = j;
			}
		}
		
		return result;
	}
	
	protected void validateConcept(Integer conceptId, Concept c, ValidateFormsResult result) {
		if (c == null) {
			result.setStatus(false);
			result.addError("(Concept ID: " + conceptId + ") Error: Unknown concept id");
		} else {
			for (ConceptMap cm : c.getConceptMappings()) {
				for (String mapping : RHIE_MAPPINGS) {
					if (mapping.equals(cm.getConceptReferenceTerm().getConceptSource().getName()))
						return;
				}
			}
			result.setStatus(false);
			result.addError("(Concept ID: " + conceptId + ") Error: No concept mappings found for any of " + Arrays.toString(RHIE_MAPPINGS));
		}
	}
	
	
	public static class FormMetadata {
		String name;
		String version;
		String encounterType;
		String file;
		
		FormMetadata(String name, String version, String encounterType, String file) {
			this.name = name;
			this.version = version;
			this.encounterType = encounterType;
			this.file = file;
		}
	}
}
