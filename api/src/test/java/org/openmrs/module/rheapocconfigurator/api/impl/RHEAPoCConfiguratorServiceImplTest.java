package org.openmrs.module.rheapocconfigurator.api.impl;

import static org.junit.Assert.*;

import org.junit.Test;

public class RHEAPoCConfiguratorServiceImplTest {

	@Test
	public void testContainsPIDWithNID() {
		assertTrue(RHEAPoCConfiguratorServiceImpl.containsPIDWithNID(
			"<ADT_A05>\n" +
			"   <MSH>\n" +
			"      <MSH.1>|</MSH.1>\n" +
			"      <MSH.2>^~\\&amp;</MSH.2>\n" +
			"      <MSH.7>\n" +
			"         <TS.1/>\n" +
			"      </MSH.7>\n" +
			"      <MSH.9>\n" +
			"         <MSG.1>ADT</MSG.1>\n" +
			"         <MSG.2>A28</MSG.2>\n" +
			"         <MSG.3>ADT_A05</MSG.3>\n" +
			"      </MSH.9>\n" +
			"      <MSH.12>\n" +
			"         <VID.1>2.5</VID.1>\n" +
			"      </MSH.12>\n" +
			"   </MSH>\n" +
			"   <PID>\n" +
			"      <PID.3>\n" +
			"         <CX.1>9234567890987654</CX.1>\n" +
			"         <CX.5>NID</CX.5>\n" +
			"      </PID.3>\n" +
			"      <PID.3>\n" +
			"         <CX.1>3630NY41-7</CX.1>\n" +
			"         <CX.5>OMRS363</CX.5>\n" +
			"      </PID.3>\n" +
			"      <PID.3>\n" +
			"         <CX.1>10e11030-f5e4-11e2-a569-12313d2f25d3</CX.1>\n" +
			"         <CX.5>ECID</CX.5>\n" +
			"      </PID.3>\n" +
			"      <PID.5>\n" +
			"         <XPN.1>\n" +
			"            <FN.1>Jane</FN.1>\n" +
			"         </XPN.1>\n" +
			"         <XPN.2>Tester</XPN.2>\n" +
			"      </PID.5>\n" +
			"      <PID.7>\n" +
			"         <TS.1>19920121</TS.1>\n" +
			"      </PID.7>\n" +
			"      <PID.8>F</PID.8>\n" +
			"      <PID.11>\n" +
			"         <XAD.3>Gatsibo</XAD.3>\n" +
			"         <XAD.4>Eastern Province/Uburasirazuba</XAD.4>\n" +
			"         <XAD.6>Rwanda</XAD.6>\n" +
			"         <XAD.8>Kigabiro</XAD.8>\n" +
			"         <XAD.9>Gasange</XAD.9>\n" +
			"         <XAD.10>Kigabiro</XAD.10>\n" +
			"      </PID.11>\n" +
			"      <PID.13>\n" +
			"         <XTN.1/>\n" +
			"      </PID.13>\n" +
			"   </PID>\n" +
			"   <NK1>\n" +
			"      <NK1.1>1</NK1.1>\n" +
			"      <NK1.2>\n" +
			"         <XPN.1>\n" +
			"            <FN.1>Janesmom</FN.1>\n" +
			"         </XPN.1>\n" +
			"      </NK1.2>\n" +
			"      <NK1.3>\n" +
			"         <CE.1>MTH</CE.1>\n" +
			"         <CE.2>mother</CE.2>\n" +
			"         <CE.3>REL_RTS</CE.3>\n" +
			"      </NK1.3>\n" +
			"   </NK1>\n" +
			"   <NK1>\n" +
			"      <NK1.1>2</NK1.1>\n" +
			"      <NK1.2>\n" +
			"         <XPN.1>\n" +
			"            <FN.1>Janesotherdad</FN.1>\n" +
			"         </XPN.1>\n" +
			"      </NK1.2>\n" +
			"      <NK1.3>\n" +
			"         <CE.1>FTH</CE.1>\n" +
			"         <CE.2>father</CE.2>\n" +
			"         <CE.3>REL_RTS</CE.3>\n" +
			"      </NK1.3>\n" +
			"   </NK1>\n" +
			"</ADT_A05>\n",
			
			"9234567890987654"
		));
	}

}
