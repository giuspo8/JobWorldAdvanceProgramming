<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>

<sec:authorize access="hasRole('COMPANY')" var="isCompany" />
<sec:authorize access="hasRole('ADMIN')" var="isAdmin" />

<div class="body">
	<div class="container_slide">
		<div class="slide"></div>
	</div>
	<div class="offer">
		<div style="text-align: center; margin: auto;">
			<table class="tabelle_lista">
				<tr>
					<th>Nome</th>
					<th>Cognome</th>
					<th>Età</th>
					<th>Numero di telefono</th>
					<th>Email</th>
					<th>Curriculum</th>
				</tr>
				<c:forEach items="${candidencies }" var="candidate">
					<tr>
						<td>${candidate.getFirstName()}</td>
						<td>${candidate.getSecondName()}</td>
						<td>${now_year - candidate.getBirthDate().getYear()}</td>
						<td>${candidate.getNumber()}</td>
						<td>${candidate.getUser().getEmail()}</td>
						<td>
						<c:choose>
						<c:when test="${isAdmin}">
							<a href="<c:url value="/admin/curriculum/${candidate.getId()}"/>">Visualizza Curriculum</a>
						</c:when>
						<c:otherwise>
							<a href="<c:url value="/company/curriculum"/>">Visualizza Curriculum</a>
						</c:otherwise>
						</c:choose>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>
