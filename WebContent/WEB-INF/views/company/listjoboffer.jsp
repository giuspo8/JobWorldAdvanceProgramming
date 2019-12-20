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
<title>Profilo di: ${company.getName()}</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/functions.js"/>"></script>
</head>
<body>

	<header>
		<div class="navigation_bar">

			<ul>
				<li id="logo"><span><img
						src=<c:url value="/resources/img/logo.png"/>></span></li>
			</ul>
		</div>
	</header>
	<div class="body">
		<div class="container_slide">
			<div class="slide"></div>
		</div>
		<div class="offer">
			<div style="text-align:center; margin:auto;">
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
					<td>${job.position} </td>
					<td>${job.contractType} </td>
					<td>${job.expiringDate } </td>
					<td>${job.region } </td>
					<td>${job.province } </td>
					<td>${job.town } </td>
					<td><a href="<c:url value="/company/interested/${job.id}"/>">Visualizza candidati</a></td>
					<td><a href="<c:url value="/company/joboffer/${job.id}/delete"/>">Elimina</a></td>
					<td><a href="<c:url value="/company/joboffer/${job.id}"/>">Modifica</a></td>
				</tr>
				</c:forEach>
				</table>
				<a href="<c:url value="/company/joboffer/"/>">Crea offerta di lavoro</a>
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