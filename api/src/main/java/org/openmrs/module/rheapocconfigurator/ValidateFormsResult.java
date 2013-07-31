package org.openmrs.module.rheapocconfigurator;

import java.util.ArrayList;
import java.util.List;

public class ValidateFormsResult {
	
	private boolean status = false;
	private List<String> errors = new ArrayList<String>();
	
	public boolean getStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public List<String> getErrors() {
		return errors;
	}
	
	public void addError(String error) {
		this.errors.add(error);
	}
}
