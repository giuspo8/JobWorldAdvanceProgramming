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
<body>

	<header>
		<div class="navigation_bar">

			<ul>
				<li id="logo"><span><img
						src="<c:url value="resources/img/logo.png"/>"></span></li>
			</ul>
		</div>
	</header>
	<div class="body">
		<div class="container_slide">
			<div class="slide"></div>
		</div>
		<div class="offer" style="width: 50%;">
			<div style="text-align:center; width:80%; margin:auto;">
				<c:url value="/autentication" var="action_url" />
				       
				<form:form method="POST" action="${action_url}">
					<h2>LOGIN</h2>
             						<table style="text-align:center; margin:auto;">
						<tr>
							<td><label class="searchsub_lbl" style="text-align:center;">Email</label></td>
						</tr>
						<tr>
							<td style="text-align:center;"><input placeholder="Inserisci qui la tua e-mail"
								name="email" class="searchsub_input" ></td>
						</tr>
						<tr>
							<td><label class="searchsub_lbl" style="text-align:center;">Password</label></td>
						</tr>
						<tr>
							<td style="text-align:center;"><input placeholder="Inserisci qui la tua password"
								name="password" class="searchsub_input"></td>
						</tr>
					</table>
					<div id="searchsub" style="text-align:center;">
						<input type="submit" value="Invio">  
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<footer>
		<div>
			<div class="faq">
				<a href="<c:url value=""/>">Tutto su JobWorld</a> - <a href="">FAQ
					- Aiuto</a>
			</div>
		</div>
		<p>JobWorld.it ottempera alla normativa vigente sulla Privacy.
			JobWorld S.r.l. a socio unico, società soggetta a direzione e
			coordinamento di Coordination S.r.l. - Sede legale Via Monte D'Ago
			19, 20159 Ancona. Codice Fiscale, Partita I.V.A., Registro Imprese
			04603040967; Capitale Sociale 98.100 i. v. JobWorld S.r.l. è iscritta
			all'Albo Informatico - Sez. IV - Aut. Min. Prov. Prot. n. 0000138</p>
	</footer>
</body>
</html>