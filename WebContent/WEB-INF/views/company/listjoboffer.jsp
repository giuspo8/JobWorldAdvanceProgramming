<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>

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
						<td><a href="<c:url value="/company/interested/${job.id}"/>">Visualizza
								candidati</a></td>
						<td><a
							href="<c:url value="/company/joboffer/${job.id}/delete"/>">Elimina</a></td>
						<td><a href="<c:url value="/company/joboffer/${job.id}"/>">Modifica</a></td>
					</tr>
				</c:forEach>
			</table>
			<a href="<c:url value="/company/joboffer/"/>">Crea offerta di
				lavoro</a>
		</div>
	</div>
</div>