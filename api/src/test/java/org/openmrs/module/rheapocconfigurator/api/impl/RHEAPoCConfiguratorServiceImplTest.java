package org.openmrs.module.rheapocconfigurator.api.impl;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.List;

import org.junit.Test;
import org.openmrs.module.rheapocconfigurator.ValidateFormsResult;

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

	@Test
	public void testGetConceptIDs() {
		ValidateFormsResult formsResult = new ValidateFormsResult();
		List<Integer> result = RHEAPoCConfiguratorServiceImpl.getConceptIds(
			"<htmlform> \n" +
			"<translations defaultLocale=\"en\">\n" +
			"\n" +
			"	<code name=\"hd\">\n" +
			"		<variant locale=\"en\" value=\"Heart disease\"/>\n" +
			"		<variant locale=\"fr\" value=\"Maladie du cœur\"/>\n" +
			"	</code>\n" +
			"	<code name=\"diabetes\">\n" +
			"		<variant locale=\"en\" value=\"Diabetes\"/>\n" +
			"		<variant locale=\"fr\" value=\"Diabéte\"/>\n" +
			"	</code>\n" +
			"	<code name=\"asthma\">\n" +
			"		<variant locale=\"en\" value=\"Asthma\"/>\n" +
			"		<variant locale=\"fr\" value=\"Asthme\"/>\n" +
			"	</code>\n" +
			"	<code name=\"tb\">\n" +
			"		<variant locale=\"en\" value=\"Tuberculosis\"/>\n" +
			"		<variant locale=\"fr\" value=\"Tuberculose\"/>\n" +
			"	</code>\n" +
			"	<code name=\"hbp\">\n" +
			"		<variant locale=\"en\" value=\"Hypertension\"/>\n" +
			"		<variant locale=\"fr\" value=\"Hypertension artérielle\"/>\n" +
			"	</code>\n" +
			"	<code name=\"Small Pelvis\">\n" +
			"		<variant locale=\"en\" value=\"Small Pelvis\"/>\n" +
			"		<variant locale=\"fr\" value=\"Petit bassin\"/>\n" +
			"	</code>\n" +
			"	<code name=\"numberOfBirths\">\n" +
			"		<variant locale=\"en\" value=\"Number of Births\"/>\n" +
			"		<variant locale=\"fr\" value=\"Nombre des naissances\"/>\n" +
			"	</code>\n" +
			"	<code name=\"Loss of Conciousness While Pregnant or When Giving Birth\">\n" +
			"		<variant locale=\"en\" value=\"Loss of Conciousness While Pregnant or When Giving Birth\"/>\n" +
			"		<variant locale=\"fr\" value=\"Perte de conscience pendant la grossesse ou lors de l'accouchement\"/>\n" +
			"	</code>\n" +
			"	<code name=\"Postconvulsive Loss of Consciousness\">\n" +
			"		<variant locale=\"en\" value=\"Postconvulsive Loss of Consciousness\"/>\n" +
			"		<variant locale=\"fr\" value=\"perte de conscience post-convulsive\"/>\n" +
			"	</code>\n" +
			"	<code name=\"Uterine Rupture\">\n" +
			"		<variant locale=\"en\" value=\"Uterine Rupture\"/>\n" +
			"		<variant locale=\"fr\" value=\"Rupture utérine\"/>\n" +
			"	</code>\n" +
			"	<code name=\"Excessive Bleeding\">\n" +
			"		<variant locale=\"en\" value=\"Excessive Bleeding (≥ 500ml) After Giving Birth\"/>\n" +
			"		<variant locale=\"fr\" value=\"Saigne beaucoup (≥ 500 ml) peu de temps après l'accouchement\"/>\n" +
			"	</code>\n" +
			"	<code name=\"Gave Birth to a Child Who Died the Same Day\">\n" +
			"		<variant locale=\"en\" value=\"Gave Birth to a Child Who Died the Same Day\"/>\n" +
			"		<variant locale=\"fr\" value=\"A accouché d'un enfant décédé le même jour\"/>\n" +
			"	</code>\n" +
			"	<code name=\"Successive Abortions\">\n" +
			"		<variant locale=\"en\" value=\"Successive Abortions\"/>\n" +
			"		<variant locale=\"fr\" value=\"Avortements successifs\"/>\n" +
			"	</code>\n" +
			"	<code name=\"risk\">\n" +
			"		<variant locale=\"en\" value=\"Height is less than 150 cm\"/>\n" +
			"		<variant locale=\"fr\" value=\"hauteur inférieure à 150 cm\"/>\n" +
			"	</code>\n" +
			"	<code name=\"First Pregnancy and is Above 30\">\n" +
			"		<variant locale=\"en\" value=\"First Pregnancy and is Above 30\"/>\n" +
			"		<variant locale=\"fr\" value=\"Première grossesse et la femme a plus de 30 ans\"/>\n" +
			"	</code>\n" +
			"	<code name=\"Is Under 18 or Above 35\">\n" +
			"		<variant locale=\"en\" value=\"Is Under 18 or Above 35\"/>\n" +
			"		<variant locale=\"fr\" value=\"Femme de moins de 18 ans ou de plus de 35 ans\"/>\n" +
			"	</code>\n" +
			"	<code name=\"Yes\">\n" +
			"		<variant locale=\"en\" value=\"Yes\"/>\n" +
			"		<variant locale=\"fr\" value=\"Oui\"/>\n" +
			"	</code>\n" +
			"	<code name=\"No\">\n" +
			"		<variant locale=\"en\" value=\"No\"/>\n" +
			"		<variant locale=\"fr\" value=\"Non\"/>\n" +
			"	</code>\n" +
			"	<code name=\"height\">\n" +
			"		<variant locale=\"en\" value=\"Height\"/>\n" +
			"		<variant locale=\"fr\" value=\"Taille\"/>\n" +
			"	</code>\n" +
			"	<code name=\"pregnancies\">\n" +
			"		<variant locale=\"en\" value=\"Number Of Pregnancies\"/>\n" +
			"		<variant locale=\"fr\" value=\"Nombre de grossesses\"/>\n" +
			"	</code>\n" +
			"	<code name=\"births\">\n" +
			"		<variant locale=\"en\" value=\"Number Of Term Births:\"/>\n" +
			"		<variant locale=\"fr\" value=\"Nombre de naissances à terme\"/>\n" +
			"	</code>\n" +
			"	<code name=\"sbirths\">\n" +
			"		<variant locale=\"en\" value=\"Number Of Still Births\"/>\n" +
			"		<variant locale=\"fr\" value=\"Nombre d'enfants mort-nés\"/>\n" +
			"	</code>\n" +
			"	<code name=\"children\">\n" +
			"		<variant locale=\"en\" value=\"Number Of Children Alive\"/>\n" +
			"		<variant locale=\"fr\" value=\"Nombre d'enfants\"/>\n" +
			"	</code>\n" +
			"	<code name=\"csection\">\n" +
			"		<variant locale=\"en\" value=\"Number Of C-Sections\"/>\n" +
			"		<variant locale=\"fr\" value=\"Nombre de césariennes\"/>\n" +
			"	</code>\n" +
			"	<code name=\"abortion\">\n" +
			"		<variant locale=\"en\" value=\"Number Of Abortions/Miscarriages\"/>\n" +
			"		<variant locale=\"fr\" value=\"Nombre d'avortements / fausses couches\"/>\n" +
			"	</code>\n" +
			"	<code name=\"dead_children\">\n" +
			"		<variant locale=\"en\" value=\"Number Of Children Who died After Birth:\"/>\n" +
			"		<variant locale=\"fr\" value=\"Nombre d'enfants décédés après naissance\"/>\n" +
			"	</code>\n" +
			"	<code name=\"lbdob\">\n" +
			"		<variant locale=\"en\" value=\"Date of Birth for last born\"/>\n" +
			"		<variant locale=\"fr\" value=\"Date de naissance du dernier né\"/>\n" +
			"	</code>\n" +
			"	<code name=\"lbstatus\">\n" +
			"		<variant locale=\"en\" value=\"Last born status\"/>\n" +
			"		<variant locale=\"fr\" value=\"Status du dernier né\"/>\n" +
			"	</code>\n" +
			"	<code name=\"Alive\">\n" +
			"		<variant locale=\"en\" value=\"Alive\"/>\n" +
			"		<variant locale=\"fr\" value=\"Né vivant\"/>\n" +
			"	</code>\n" +
			"	<code name=\"Dead\">\n" +
			"		<variant locale=\"en\" value=\"Dead\"/>\n" +
			"		<variant locale=\"fr\" value=\"Mort-né\"/>\n" +
			"	</code>\n" +
			"	<code name=\"Unknown\">\n" +
			"		<variant locale=\"en\" value=\"Unknown\"/>\n" +
			"		<variant locale=\"fr\" value=\"Inconnu\"/>\n" +
			"	</code>\n" +
			"	<code name=\"lmp\">\n" +
			"		<variant locale=\"en\" value=\"First day of last menstral period \"/>\n" +
			"		<variant locale=\"fr\" value=\"Date des dernières règles \"/>\n" +
			"	</code>\n" +
			"	<code name=\"tested\">\n" +
			"		<variant locale=\"en\" value=\"Has she been tested for HIV/AIDS?\"/>\n" +
			"		<variant locale=\"fr\" value=\"Est-ce que le patient a subi un test de dépistage du VIH?\"/>\n" +
			"	</code>\n" +
			"	<code name=\"due_date\">\n" +
			"		<variant locale=\"en\" value=\"Approximate date of delivery\"/>\n" +
			"		<variant locale=\"fr\" value=\"Date probable d'accouchement\"/>\n" +
			"	</code>\n" +
			"	<code name=\"partner\">\n" +
			"		<variant locale=\"en\" value=\"Did she arrive to the clinic with her partner?\"/>\n" +
			"		<variant locale=\"fr\" value=\"Est-ce que le patient est venu avec son partenaire?\"/>\n" +
			"	</code>\n" +
			"	<code name=\"placenta\">\n" +
			"		<variant locale=\"en\" value=\"Incomplete Placenta\"/>\n" +
			"		<variant locale=\"fr\" value=\"Placenta incomplet\"/>\n" +
			"	</code>\n" +
			"	<code name=\"hypertension\">\n" +
			"		<variant locale=\"en\" value=\"Hypertension\"/>\n" +
			"		<variant locale=\"fr\" value=\"Hypertension\"/>\n" +
			"	</code>\n" +
			"	<code name=\"5preg\">\n" +
			"		<variant locale=\"en\" value=\"More Than 5 pregnancies\"/>\n" +
			"		<variant locale=\"fr\" value=\"Plus de 5 grossesses\"/>\n" +
			"	</code>\n" +
			"	<code name=\"fibroids\">\n" +
			"		<variant locale=\"en\" value=\"Uterine Fibroids\"/>\n" +
			"		<variant locale=\"fr\" value=\"Les fibromes utérins\"/>\n" +
			"	</code>\n" +
			"	<code name=\"encounterDetails\">\n" +
			"		<variant locale=\"en\" value=\"1. Encounter Details\"/>\n" +
			"		<variant locale=\"fr\" value=\"1. Rencontrer les détails\"/>\n" +
			"	</code>\n" +
			"	<code name=\"obInformation\">\n" +
			"		<variant locale=\"en\" value=\"2. OB Information\"/>\n" +
			"		<variant locale=\"fr\" value=\"2. Information obstétrique\"/>\n" +
			"	</code>\n" +
			"	<code name=\"obstetricalRiskFactors\">\n" +
			"		<variant locale=\"en\" value=\"Obstetrical risk factors\"/>\n" +
			"		<variant locale=\"fr\" value=\"Facteurs de risque obstétricaux\"/>\n" +
			"	</code>\n" +
			"	<code name=\"medicalConditions\">\n" +
			"		<variant locale=\"en\" value=\"Medical Conditions\"/>\n" +
			"		<variant locale=\"fr\" value=\"Conditions médicales\"/>\n" +
			"	</code>\n" +
			"	<code name=\"medicalDates\">\n" +
			"		<variant locale=\"en\" value=\"Medical Dates\"/>\n" +
			"		<variant locale=\"fr\" value=\"Dates médicaux\"/>\n" +
			"	</code>\n" +
			"</translations>\n" +
			"<script type=\"text/javascript\">\n" +
			"\n" +
			"var $ = jQuery.noConflict();\n" +
			"$(document).ready(function() {\n" +
			"	var group1 = $j(\"#ageOne\");\n" +
			"	var group2 = $j(\"#ageTwo\");\n" +
			"	var group3 = $j(\"#parity\")\n" +
			"\n" +
			"\n" +
			"	var patAge = $(\"#age\").text();\n" +
			"	var testAge = 17;\n" +
			"	var testAge1 = 38;\n" +
			"\n" +
			"	if(patAge &lt; 18 ||patAge &gt; 35)\n" +
			"	{\n" +
			"		group1.find(\"input[type$='checkbox']\").attr(\"checked\",true);\n" +
			"	}\n" +
			"	$(\"#height\").change(function() {\n" +
			"		var txt = $(\"#height input\").val();\n" +
			"		var group = $j(\"#heightOK\");\n" +
			"		//alert(txt);\n" +
			"\n" +
			"		if(txt == null || txt == ''){\n" +
			"			group.find(\"input[type$='checkbox']\").attr(\"checked\",false);	\n" +
			"		}\n" +
			"		else if(txt &gt; 149) {                      \n" +
			"			group.find(\"input[type$='checkbox']\").attr(\"checked\",false);		\n" +
			"		} \n" +
			"\n" +
			"		else {\n" +
			"\n" +
			"			group.find(\"input[type$='checkbox']\").attr(\"checked\",true);	\n" +
			"		}\n" +
			"\n" +
			"	});\n" +
			"\n" +
			"	$(\"#nop\").change(function() {\n" +
			"		var txt = $(\"#nop input\").val();\n" +
			"		if(txt == 1 &amp;&amp; patAge &gt; 30 ){\n" +
			"\n" +
			"			group2.find(\"input[type$='checkbox']\").attr(\"checked\",true);\n" +
			"		}\n" +
			"		else{\n" +
			"			group2.find(\"input[type$='checkbox']\").attr(\"checked\",false);\n" +
			"		}\n" +
			"\n" +
			"		if(txt == null || txt == ''){\n" +
			"			group2.find(\"input[type$='checkbox']\").attr(\"checked\",false);\n" +
			"			group3.find(\"input[type$='checkbox']\").attr(\"checked\",false);\n" +
			"		}\n" +
			"		else if(txt &gt; 5){\n" +
			"			group3.find(\"input[type$='checkbox']\").attr(\"checked\",true);\n" +
			"		}\n" +
			"		else{\n" +
			"			group3.find(\"input[type$='checkbox']\").attr(\"checked\",false);\n" +
			"		}\n" +
			"	});\n" +
			"});\n" +
			"\n" +
			"</script> <script type=\"text/javascript\">\n" +
			"$j(function() {\n" +
			"	getField('lmp.value').change(function() {\n" +
			"		var mp = getValue('lmp.value');\n" +
			"        //alert(mp);\n" +
			"		var d = new Date(mp);\n" +
			"\n" +
			"		d.setFullYear (d.getFullYear() + 1);\n" +
			"		d.setMonth(d.getMonth() - 3);\n" +
			"\n" +
			"		d.setDate(d.getDate() + 7);\n" +
			"\n" +
			"		var curr_date = d.getDate();\n" +
			"\n" +
			"		var curr_month = d.getMonth();\n" +
			"		curr_month++;\n" +
			"\n" +
			"		var curr_year = d.getFullYear();\n" +
			"\n" +
			"		var duedate = curr_year + \"-\" + curr_month + \"-\" + curr_date;\n" +
			"\n" +
			"\n" +
			"		setValue('dd.value',duedate);\n" +
			"\n" +
			"\n" +
			"	});\n" +
			"\n" +
			"});\n" +
			"</script> \n" +
			"<script type=\"text/javascript\">       \n" +
			"var $ = jQuery.noConflict();\n" +
			"$j(document).ready(function(){\n" +
			"   // $(\"#location select\").attr(\"disabled\",true); \n" +
			"\n" +
			"\n" +
			"});\n" +
			"</script>\n" +
			"<macros>\n" +
			"	paperFormId = (Fill this in) \n" +
			"	headerColor =#009d8e\n" +
			"	fontOnHeaderColor = white \n" +
			"</macros>\n" +
			"\n" +
			"<style>\n" +
			"table.risks td {\n" +
			"	font-size: 0.9em;\n" +
			"}\n" +
			"\n" +
			".section {\n" +
			"	border: 1px solid$headerColor;\n" +
			"	padding: 2px;\n" +
			"	text-align: left;\n" +
			"	margin-bottom: 1em;\n" +
			"}\n" +
			"\n" +
			".sectionHeader {\n" +
			"	background-color: $headerColor;\n" +
			"	color: $fontOnHeaderColor;\n" +
			"	display: block;\n" +
			"	padding: 2px;\n" +
			"	font-weight: bold;\n" +
			"}\n" +
			"\n" +
			"table.baseline-aligned td {\n" +
			"	vertical-align: center;\n" +
			"}\n" +
			"</style>\n" +
			"\n" +
			"<span style=\"float: right\">Paper Form ID: $paperFormId</span>\n" +
			"<h2>ANC Past Medical History (v1.0)</h2>\n" +
			"<includeIf velocityTest=\"$patient.gender == 'F' \">\n" +
			"	<section headerCode=\"encounterDetails\">\n" +
			"		<table class=\"baseline-aligned\">\n" +
			"			<tr>\n" +
			"				<td>Date:</td>\n" +
			"				<td><encounterDate default=\"today\" /></td>\n" +
			"			</tr>\n" +
			"			<tr>\n" +
			"				<td>Location:</td>\n" +
			"				<td><encounterLocation id=\"location\" default=\"GlobalProperty:registration.defaultLocationCode\"/></td>\n" +
			"			</tr>\n" +
			"			<tr>\n" +
			"				<td>Provider:</td>\n" +
			"				<td><encounterProvider id=\"provider\" /></td>\n" +
			"			</tr>\n" +
			"		</table>\n" +
			"	</section>\n" +
			"\n" +
			"	\n" +
			"	<section >\n" +
			"		<section headerCode=\"obInformation\">\n" +
			"			<table class=\"baseline-aligned\">\n" +
			"				<!--<tr>\n" +
			"\n" +
			"					<td><obs conceptId=\"6547\" answerConceptIds=\"1065,1066\"\n" +
			"						answerCodes=\"Yes,No\" style=\"radio\" labelCode=\"partner\"/></td>\n" +
			"					</tr>-->\n" +
			"					<tr>\n" +
			"\n" +
			"						<td><obs conceptId=\"5090\" id=\"height\" labelCode=\"height\"/>(cm)</td>\n" +
			"					</tr>\n" +
			"\n" +
			"					<tr>\n" +
			"\n" +
			"						<td><obs conceptId=\"5624\" id=\"nop\" labelCode=\"pregnancies\"/></td>\n" +
			"					</tr>\n" +
			"					<tr style=\"display: none;\">\n" +
			"						<td><span id=\"age\"><lookup expression=\"patient.age\" /></span></td>\n" +
			"					</tr>\n" +
			"					<tr>\n" +
			"\n" +
			"						<td><obs conceptId=\"1053\" labelCode=\"numberOfBirths\"/></td>\n" +
			"					</tr>\n" +
			"\n" +
			"					<tr>\n" +
			"\n" +
			"						<td><obs conceptId=\"9623\" labelCode=\"births\"/></td>\n" +
			"					</tr>\n" +
			"					<tr>\n" +
			"\n" +
			"						<td><obs conceptId=\"8157\" labelCode=\"abortion\"/></td>\n" +
			"					</tr>\n" +
			"\n" +
			"\n" +
			"\n" +
			"\n" +
			"					<tr>\n" +
			"\n" +
			"						<td><obs conceptId=\"8158\" labelCode=\"dead_children\"/></td>\n" +
			"					</tr>\n" +
			"					<tr>\n" +
			"\n" +
			"						<td><obs conceptId=\"2649\" labelCode=\"children\"/></td>\n" +
			"					</tr>\n" +
			"\n" +
			"\n" +
			"\n" +
			"					<tr>\n" +
			"\n" +
			"						<td><obs conceptId=\"8404\" labelCode=\"lbdob\"/></td>\n" +
			"					</tr>\n" +
			"					<tr>\n" +
			"\n" +
			"						<td><obs conceptId=\"8159\" answerConceptIds=\"6543,6544,1067\"\n" +
			"							answerCodes=\"Alive,Dead,Unknown\" style=\"radio\" labelCode=\"lbstatus\"/></td>\n" +
			"						</tr>\n" +
			"						<tr>\n" +
			"\n" +
			"							<td><obs conceptId=\"8143\" labelCode=\"csection\"/></td>\n" +
			"						</tr>\n" +
			"						<tr>\n" +
			"\n" +
			"							<td><obs conceptId=\"8142\" labelCode=\"sbirths\"/></td>\n" +
			"						</tr>\n" +
			"\n" +
			"\n" +
			"\n" +
			"					</table>\n" +
			"				</section>\n" +
			"				<section headerCode=\"obstetricalRiskFactors\">\n" +
			"					<table class=\"baseline-aligned risks\">\n" +
			"						<tr>\n" +
			"							<td><obs conceptId=\"8534\" answerConceptId=\"8527\"\n" +
			"								answerCodes=\"Small Pelvis\" style=\"checkbox\" /></td>\n" +
			"								<td><obs conceptId=\"8534\" answerConceptId=\"8528\"\n" +
			"									answerCodes=\"Loss of Conciousness While Pregnant or When Giving Birth\"\n" +
			"									style=\"checkbox\" /></td>\n" +
			"									<td><obs conceptId=\"8534\" answerConceptId=\"8529\"\n" +
			"										answerCodes=\"Postconvulsive Loss of Consciousness\"\n" +
			"										style=\"checkbox\" /></td>\n" +
			"									</tr>\n" +
			"\n" +
			"									<tr>\n" +
			"										<td><obs conceptId=\"8534\" answerConceptId=\"8530\"\n" +
			"											answerCodes=\"Uterine Rupture\" style=\"checkbox\" /></td>\n" +
			"											<td><obs conceptId=\"8534\" answerConceptId=\"8531\"\n" +
			"												answerCodes=\"Excessive Bleeding\"\n" +
			"												style=\"checkbox\" /></td>\n" +
			"												<td><obs conceptId=\"8534\" answerConceptId=\"8532\"\n" +
			"													answerCodes=\"Gave Birth to a Child Who Died the Same Day\"\n" +
			"													style=\"checkbox\" /></td>\n" +
			"												</tr>\n" +
			"												<tr>\n" +
			"													<td><obs conceptId=\"8534\" answerConceptId=\"8533\"\n" +
			"														answerCodes=\"Successive Abortions\" style=\"checkbox\" /></td>\n" +
			"<!--<td><obs conceptId=\"8534\" id=\"heightOK\" answerConceptId=\"8518\" style=\"checkbox\" answerCodes=\"hrisk\"/>\n" +
			"</td>-->\n" +
			"<td><obs conceptId=\"8534\" answerConceptId=\"8518\" id=\"heightOK\" answerCodes=\"risk\"/></td>\n" +
			"<td><obs id=\"ageTwo\" conceptId=\"8534\"\n" +
			"	answerConceptId=\"8536\"\n" +
			"	answerCodes=\"First Pregnancy and is Above 30\" style=\"checkbox\" /></td>\n" +
			"</tr>\n" +
			"<tr>\n" +
			"	<td><obs id=\"ageOne\" conceptId=\"8534\"\n" +
			"		answerConceptId=\"8535\" answerCodes=\"Is Under 18 or Above 35\"\n" +
			"		style=\"checkbox\" /></td>\n" +
			"        <td><obs conceptId=\"8534\" answerConceptId=\"9618\" id=\"parity\" answerCode=\"5preg\"/></td>\n" +
			"		<!--<td><obs conceptId=\"8534\" answerConceptId=\"9618\" answerCodes=\"risk\" id=\"parity\" style=\"checkbox\" /></td>-->\n" +
			"		<td><obs conceptId=\"8534\"\n" +
			"			answerConceptId=\"9617\" answerCode=\"placenta\"\n" +
			"			style=\"checkbox\" /></td>\n" +
			"		</tr>\n" +
			"\n" +
			"	</table>\n" +
			"</section>\n" +
			"<section headerCode=\"medicalConditions\">\n" +
			"	<table class=\"baseline-aligned\">\n" +
			"		<tr>\n" +
			"			<td><obs conceptId=\"8505\" answerConceptId=\"3305\"\n" +
			"				style=\"checkbox\" answerCode=\"hd\"/></td>\n" +
			"				<td><obs conceptId=\"8505\" answerConceptId=\"6806\"\n" +
			"					style=\"checkbox\" /></td>\n" +
			"					<td><obs conceptId=\"8505\" answerConceptId=\"8504\"\n" +
			"						style=\"checkbox\" answerCode=\"fibroids\"/></td>\n" +
			"					</tr>\n" +
			"\n" +
			"					<tr>\n" +
			"						<td><obs conceptId=\"8505\" answerConceptId=\"3720\"\n" +
			"							style=\"checkbox\" answerCode=\"diabetes\"/></td>\n" +
			"							<td><obs conceptId=\"8505\" answerConceptId=\"5\"\n" +
			"								style=\"checkbox\" answerCode=\"asthma\"/></td>\n" +
			"								<td><obs conceptId=\"8505\" answerConceptId=\"58\"\n" +
			"									style=\"checkbox\" answerCode=\"tb\"/></td>\n" +
			"								</tr>\n" +
			"								<tr><td><obs conceptId=\"8534\"\n" +
			"									answerConceptId=\"903\" answerCode=\"hbp\"\n" +
			"									style=\"checkbox\"/></td>\n" +
			"									<td><obs conceptId=\"1621\" style=\"checkbox\" labelCode=\"tested\"/></td>\n" +
			"								</tr>\n" +
			"\n" +
			"								<tr>\n" +
			"\n" +
			"\n" +
			"								</tr>\n" +
			"\n" +
			"							</table>\n" +
			"						</section>\n" +
			"						<section headerCode=\"medicalDates\">\n" +
			"							<table class=\"baseline-aligned\">\n" +
			"								<tr>\n" +
			"\n" +
			"									<td><obs conceptId=\"968\" id=\"lmp\" labelCode=\"lmp\"/></td>\n" +
			"								</tr>\n" +
			"								<tr>\n" +
			"\n" +
			"									<td><obs conceptId=\"6188\" id=\"dd\" allowFutureDates=\"true\" labelCode=\"due_date\"/></td>\n" +
			"								</tr>\n" +
			"\n" +
			"\n" +
			"\n" +
			"							</table>\n" +
			"						</section>\n" +
			"					</section>\n" +
			"					<submit />\n" +
			"				</includeIf>\n" +
			"				<includeIf velocityTest=\"$patient.gender == 'M' \">\n" +
			"					<section headerLabel=\"Invalid Patient Message\">\n" +
			"\n" +
			"						<table border=\"0\" width=\"100%\">\n" +
			"\n" +
			"							<tr><td>This Form Is Only Filled For Female Patients:</td>\n" +
			"\n" +
			"							</tr>\n" +
			"\n" +
			"						</table>\n" +
			"\n" +
			"					</section>\n" +
			"				</includeIf> \n" +
			"\n" +
					"				</htmlform>\n",
			formsResult
		);
			
		Integer[] expected = new Integer[]{
			5090,
			5624,
			1053,
			9623,
			8157,
			8158,
			2649,
			8404,
			8159,
			8143,
			8142,
			8534,
			8534,
			8534,
			8534,
			8534,
			8534,
			8534,
			8534,
			8534,
			8534,
			8534,
			8534,
			8505,
			8505,
			8505,
			8505,
			8505,
			8505,
			8534,
			1621,
			968,
			6188
		};
		int i = 0;
		for (Integer j : result) {
			assertEquals(expected[i].intValue(), j.intValue());
			i++;
		}
	}
}
