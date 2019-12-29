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
					<th>Posizione</th>
					<th>Tipo di contratto</th>
					<th>Data di scadenza</th>
					<th>Regione</th>
					<th>Provincia</th>
					<th>Città</th>
					<th></th>
					<th></th>
					<th></th>
				</tr>
				<c:forEach items="${jobs }" var="job">
					<tr>
						<td>${job.position}</td>
						<td>${job.contractType}</td>
						<td>${job.expiringDate }</td>
						<td>${job.region }</td>
						<td>${job.province }</td>
						<td>${job.town }</td>
						<td>
						<c:choose>
						<c:when test="${isAdmin}">
							<a href="<c:url value="/admin/interested/${job.id}"/>">Visualizza
									candidati</a>
						</c:when>
						<c:otherwise>
							<a href="<c:url value="/company/interested/${job.id}"/>">Visualizza
									candidati</a>
						</c:otherwise>
						</c:choose>
								</td>
						<td>
						<c:choose>
						<c:when test="${isAdmin}">
							<a href="<c:url value="/admin/joboffer/${job.id}/delete"/>">Elimina</a>
						</c:when>
						<c:otherwise>
							<a href="<c:url value="/company/joboffer/${job.id}/delete"/>">Elimina</a>
						</c:otherwise>
						</c:choose>
							</td>
						<td>
						<c:choose>
						<c:when test="${isAdmin}">
							<a href="<c:url value="/admin/joboffer/${job.id}"/>">Modifica</a>
						</c:when>
						<c:otherwise>
							<a href="<c:url value="/company/joboffer/${job.id}"/>">Modifica</a>
						</c:otherwise>
						</c:choose>
						</td>
					</tr>
				</c:forEach>
			</table>
			<c:choose>
						<c:when test="${isCompany}">
							<a href="<c:url value="/company/joboffer/"/>">Crea offerta di
				lavoro</a>
						</c:when>
						</c:choose>
		</div>
	</div>
</div>