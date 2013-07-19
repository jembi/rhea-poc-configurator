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
package org.openmrs.module.rheapocconfigurator;

/***
 * 
 * Global properties that need input values (e.g. from a user) before they can be setup.
 *
 */
public class GlobalPropertiesInput {

		private String himHost;
		private String himUsername;
		private String himPassword;
		private String schedulerUsername;
		private String schedulerPassword;
		private String locationID;
		private String locationName;
		private String locationFOSAID;
		

		public String getHimHost() {
			return himHost;
		}


		public void setHimHost(String himHost) {
			this.himHost = himHost;
		}


		public String getHimUsername() {
			return himUsername;
		}


		public void setHimUsername(String himUsername) {
			this.himUsername = himUsername;
		}


		public String getHimPassword() {
			return himPassword;
		}


		public void setHimPassword(String himPassword) {
			this.himPassword = himPassword;
		}


		public String getSchedulerUsername() {
			return schedulerUsername;
		}


		public void setSchedulerUsername(String schedulerUsername) {
			this.schedulerUsername = schedulerUsername;
		}


		public String getSchedulerPassword() {
			return schedulerPassword;
		}


		public void setSchedulerPassword(String schedulerPassword) {
			this.schedulerPassword = schedulerPassword;
		}


		public String getLocationID() {
			return locationID;
		}


		public void setLocationID(String locationID) {
			this.locationID = locationID;
		}


		public String getLocationName() {
			return locationName;
		}


		public void setLocationName(String locationName) {
			this.locationName = locationName;
		}


		public String getLocationFOSAID() {
			return locationFOSAID;
		}


		public void setLocationFOSAID(String locationFOSAID) {
			this.locationFOSAID = locationFOSAID;
		}
}
