<!--

 * Home page del Portale JobWorld
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
		<div class="slide">
			<img src="<c:url value="resources/img/jobworldhome.jpeg"/>">
		</div>
		<hr>
	</div>
	<c:if test="${fn:length(jobOffers) == 0}">Non ci sono offerte da mostrare.</c:if>
	<c:if test="${fn:length(jobOffers) gt 0}">
		<c:forEach items="${jobOffers}" var="j">
			<div class="offer">
				<h3>
					<a class="title_a"
						href="<c:url value="/moreinfo/${j.getCompany().getId()}/${j.id}"/>">${j.position}</a>
				</h3>
				<hr>
				<div class="general_info">
					<b>Candidature valide fino al</b> ${j.expiringDate }.<br> <b>Azienda:
					</b>${j.getCompany().getName() }<br>
					<b>Località: </b>
					<c:if test="${not empty j.region}">
					${j.region }, 
					</c:if>
					<c:if test="${empty j.region }">
					N.D., 
					</c:if>
					<c:if test="${not empty j.province}">
					${j.province }, 
					</c:if>
					<c:if test="${empty j.province }">
					N.D., 
					</c:if>
					<c:if test="${not empty j.town}">
					${j.town}.
					</c:if>
					<c:if test="${empty j.town}">
					N.D.
					</c:if>
				</div>
				<hr>
				<div class="general_info_body">
					<img class="pic"
						src="<c:url value="${j.getCompany().getUser().getImage()}" />">
					<div class="description">
						<span class="more">${j.description}</span>
						<c:if test="${isUser}">
							<c:if test="${person.isInterested(j)}">
								<a class="app_btn" href="<c:url value="/user/unapply/${j.id}"/>">Togli
									la candidatura</a>
							</c:if>
							<c:if test="${! person.isInterested(j)}">
								<a class="app_btn" href="<c:url value="/user/apply/${j.id}"/>">Candidati</a>
							</c:if>
						</c:if>
					</div>
				</div>
			</div>
		</c:forEach>
	</c:if>

</div>