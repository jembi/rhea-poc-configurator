<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<form:form id="configGo" modelAttribute="config" method="post">
<c:choose>
	<c:when test="${config.overallStatus == null}">
		<p>Click the submit button to the start the configuration process</p>
	</c:when>
	<c:otherwise>
		<c:choose>
			<c:when test="${config.overallStatus == true}">
				<p>Configuration successful. The RHEA PoC Configurator module can now safely be removed.</p>
			</c:when>
			<c:otherwise>
				<p>One or more configuration sub-processes failed</p>
			</c:otherwise>
		</c:choose>
		<table>
			<tr>
				<td>Global Properties</td>
				<td>
					<c:choose>
						<c:when test="${config.statusGlobalProperties == true}"><img src="${pageContext.request.contextPath}/images/checkmark.png"/></c:when>
						<c:otherwise><img src="${pageContext.request.contextPath}/images/error.gif"/></c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td>Identifier Types</td>
				<td>
					<c:choose>
						<c:when test="${config.statusIdentifierTypes == true}"><img src="${pageContext.request.contextPath}/images/checkmark.png"/></c:when>
						<c:otherwise><img src="${pageContext.request.contextPath}/images/error.gif"/></c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td>Config for Primary Care Module</td>
				<td>
					<c:choose>
						<c:when test="${config.statusConfigForPrimaryCareModule == true}"><img src="${pageContext.request.contextPath}/images/checkmark.png"/></c:when>
						<c:otherwise><img src="${pageContext.request.contextPath}/images/error.gif"/></c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td>Config for Appointment Module</td>
				<td>
					<c:choose>
						<c:when test="${config.statusConfigForAppointmentModule == true}"><img src="${pageContext.request.contextPath}/images/checkmark.png"/></c:when>
						<c:otherwise><img src="${pageContext.request.contextPath}/images/error.gif"/></c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td>Encounter Types</td>
				<td>
					<c:choose>
						<c:when test="${config.statusEncounterTypes == true}"><img src="${pageContext.request.contextPath}/images/checkmark.png"/></c:when>
						<c:otherwise><img src="${pageContext.request.contextPath}/images/error.gif"/></c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td>Forms</td>
				<td>
					<c:choose>
						<c:when test="${config.statusForms == true}"><img src="${pageContext.request.contextPath}/images/checkmark.png"/></c:when>
						<c:otherwise><img src="${pageContext.request.contextPath}/images/error.gif"/></c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td>Provider Privileges</td>
				<td>
					<c:choose>
						<c:when test="${config.statusProviderPrivileges == true}"><img src="${pageContext.request.contextPath}/images/checkmark.png"/></c:when>
						<c:otherwise><img src="${pageContext.request.contextPath}/images/error.gif"/></c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>
	</c:otherwise>
</c:choose>
<input type="submit" value="Perform Configuration" <c:if test="${config.overallStatus == true}"><c:out value="disabled='disabled'"/></c:if>>
</form:form>

<%@ include file="/WEB-INF/template/footer.jsp"%>