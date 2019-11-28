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
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Parisienne&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Hepta+Slab&display=swap"
	rel="stylesheet">
<title>Home</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/functions.js"/>"></script>
</head>

<body>

	<header>
		<div class="navigation_bar">

			<ul>
				<li id="logo"><span class="ham_menu_div"><input
						type="checkbox" id="menyAvPaa"> <label id="burger"
						for="menyAvPaa">
							<div></div>
							<div></div>
							<div></div>
					</label>
						<nav id="meny">
							<div>
								<c:url value="/filter" var="action_url" />
								        
								<form:form method="POST" action="${action_url}">
								<h2>Cerca lavoro:</h2>
             						<table>
										<tr><td>
											<label class="searchsub_lbl">Posizione ricercata</label>
										</td></tr>
										<tr><td>
											<input placeholder="Posizione ricercata" name="position" class="searchsub_input">
										</td></tr>
										<tr><td>
											<label class="searchsub_lbl">Tipologia di contratto</label>
										</td></tr>
										<tr><td>
											<input placeholder="Tipologia di contratto" name="contractType" class="searchsub_input">
										</td></tr>
										<tr><td>
											<label class="searchsub_lbl">Livello di esperienza richiesto</label>
										</td></tr>
										<tr><td>
											<input placeholder="Esperienza" name="minExperience" class="searchsub_input">
										</td></tr>
										<tr><td>
											<label class="searchsub_lbl">Livello di istruzione richiesto</label>
										</td></tr>
										<tr><td>
											<input placeholder="Istruzione" name="minEducationLevel" class="searchsub_input">
										</td></tr>
										<tr><td>
											<label class="searchsub_lbl">Regione</label>
										</td></tr>
										<tr><td>
											<input placeholder="Regione" name="region" class="searchsub_input">
										</td></tr>
										<tr><td>
											<label class="searchsub_lbl">Provincia</label>
										</td></tr>
										<tr><td>
											<input placeholder="Provincia" name="province" class="searchsub_input">
										</td></tr>
										<tr><td>
											<label class="searchsub_lbl">Città</label>
										</td></tr>
										<tr><td>
											<input placeholder="Città" name="town" class="searchsub_input">
										</td></tr>
									</table>
									<div id="searchsub"><input type="submit" value="Invio">  </div>
								</form:form>
							</div>
						</nav>
				</span> <span><img src="<c:url value="resources/img/logo.png"/>"></span></li>
				<li><a href="">Registrati</a></li>
				<li>-</li>
				<li><a href="" class="login_btn">Accedi</a></li>
			</ul>
		</div>
	</header>
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
					<h3>${j.position}</h3>
					<hr>
					<div class="general_info"></div>
					<hr>
					<div class="general_info_body">
						<img class="pic" src="<c:url value="" />">
						<div class="description">
							<span class="more">${j.description}</span>
						</div>
					</div>
				</div>
			</c:forEach>
		</c:if>

	</div>
	<footer>
		<div class="container">
			<h2>Guarda</h2>
			<h3>la nostra galleria</h3>
			<div>
				<img src="<c:url value="/resources/img/galleria1.jpg"/>"> <img
					src="<c:url value="/resources/img/galleria2.jpg"/>"> <img
					src="<c:url value="/resources/img/galleria3.jpg"/>">
			</div>
		</div>
	</footer>


</body>

</html>