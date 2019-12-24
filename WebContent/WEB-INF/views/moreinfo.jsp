<!--

 * Informazioni aggiuntive di una offerta di lavoro
 * 
 * @author Giuseppe Costantini
 * @author Simone di Saverio
 * @author Lorenzo Giuliani
 * @author Savio Feng
 * @version 1.0
 
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<sec:authorize access="hasRole('USER')" var="isUser" />
<div class="body">
	<div class="container_slide">
		<div>
			<hr>
			<h3>Informazioni dell'azienda promotrice.</h3>
			<img
				src="<c:url value="${joboffer.getCompany().getUser().getImage()}" />"><br>
			<b>Nome: </b>${joboffer.getCompany().getName()}<br> <b>Descrizione:
			</b>${joboffer.getCompany().getUser().getDescription()}<br>
			<hr>
			<h3>Informazioni dell'offerta di lavoro.</h3>
			<b>Posizione ricercata: </b>${joboffer.position }<br> <b>Regione:
			</b>${joboffer.region }<br> <b>Provincia: </b>${joboffer.province }<br>
			<b>Città: </b>${joboffer.town }<br> <b>Descrizione: </b>${joboffer.description }<br>
			<b>Tipo di contratto: </b>${joboffer.contractType }<br> <b>Livello
				di istruzione minimo richiesto: </b>${joboffer.minEducationLevel }<br>
			<b>Livello di esperienza minimo richiesto: </b>${joboffer.minExperience }<br>
			<hr>
			<b>Valido fino al </b>${joboffer.expiringDate }
			<hr>
			<c:if test="${isUser}">
				<c:if test="${person.isInterested(j)}">
					<a class="app_btn" href="<c:url value="/user/unapply/${joboffer.id}"/>">Togli
						la candidatura</a>
				</c:if>
				<c:if test="${! person.isInterested(j)}">
					<a class="app_btn" href="<c:url value="/user/apply/${joboffer.id}"/>">Candidati</a>
				</c:if>
			</c:if>
		</div>
	</div>
</div>