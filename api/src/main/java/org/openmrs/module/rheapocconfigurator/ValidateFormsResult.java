package org.openmrs.module.rheapocconfigurator;

import java.util.ArrayList;
import java.util.List;

public class ValidateFormsResult {
	
	private boolean status = false;
	private List<String> failedConcepts = new ArrayList<String>();
	
	public boolean getStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public List<String> getFailedConcepts() {
		return failedConcepts;
	}
	
	public void addFailedConcept(String conceptID) {
		this.failedConcepts.add(conceptID);
	}
}
