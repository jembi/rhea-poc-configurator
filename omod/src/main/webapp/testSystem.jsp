<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<openmrs:require privilege="Manage Global Properties" otherwise="/login.htm" redirect="/module/rheapocconfigurator/testSystem.form" />

<form:form id="testGo" modelAttribute="tests" method="post">
<c:choose>
	<c:when test="${tests.overallStatus == null}">
		<p>Fill in the appropriate settings and then click the 'Perform Tests' button to the start the process for testing this system against the Rwanda HIE</p>
	</c:when>
	<c:otherwise>
		<c:choose>
			<c:when test="${tests.overallStatus == true}">
				<p>Testing successful. Yay!</p>
			</c:when>
			<c:otherwise>
				<p>One or more tests failed</p>
			</c:otherwise>
		</c:choose>
		<table>
			<tr>
				<td>Connection &amp; Authentication Test</td>
				<td>
					<c:choose>
						<c:when test="${tests.statusAuthTest == true}"><img src="${pageContext.request.contextPath}/images/checkmark.png"/></c:when>
						<c:otherwise><img src="${pageContext.request.contextPath}/images/error.gif"/></c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td>Form Concepts Validation</td>
				<td>
					<c:choose>
						<c:when test="${tests.statusFormsValidation == true}"><img src="${pageContext.request.contextPath}/images/checkmark.png"/></c:when>
						<c:otherwise><img src="${pageContext.request.contextPath}/images/error.gif"/></c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>
	</c:otherwise>
</c:choose>
<br/>
<p>
	<table>
		<tr><td>Test Patient NID</td><td><form:input id="testNID" path="authTestInput.testPatientNID"/></td></tr>
	</table>
	<br/>
	<input type="submit" value="Perform Tests">
</p>
</form:form>

<%@ include file="/WEB-INF/template/footer.jsp"%>