<spring:htmlEscape defaultHtmlEscape="true" />
<ul id="menu">
	<li class="first"><a
		href="${pageContext.request.contextPath}/admin"><spring:message
				code="admin.title.short" /></a></li>

	<li
		<c:if test='<%= request.getRequestURI().contains("/configureSystem") %>'>class="active"</c:if>>
		<a
		href="${pageContext.request.contextPath}/module/rheapocconfigurator/configureSystem.form"><spring:message
				code="rheapocconfigurator.configureSystem" /></a>
	</li>
	
	<!-- Add further links here -->
	<li
		<c:if test='<%= request.getRequestURI().contains("/testSystem") %>'>class="active"</c:if>>
		<a
		href="${pageContext.request.contextPath}/module/rheapocconfigurator/testSystem.form"><spring:message
				code="rheapocconfigurator.testSystem" /></a>
	</li>
</ul>
<h2>
	<spring:message code="rheapocconfigurator.title" />
</h2>
