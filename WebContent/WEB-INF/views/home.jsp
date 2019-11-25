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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript" src="<c:url value="/resources/js/functions.js"/>"></script>
</head>

<body>
	<header>
		<div class="navigation_bar">
			<ul>
				<li id="logo"><img src="<c:url value="resources/img/logo.png"/>"></li>
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
		<c:if test="${fn:length(jobOffers) == 0}">Non ci sono offerte da mostrare</c:if>
		<c:if test="${fn:length(jobOffers) gt 0}">
		<c:forEach items="${jobOffers}" var="j">		
			<div class="chi_siamo">
			<h2>${j.contractType}</h2>
				<div class="testo_foto_chi_siamo">
					<img id="foto_chi_siamo"
						src="<c:url value="/resources/img/about-us.png" />">
					<div class="testo_chi_siamo"><span class="more">${j.description}</span></div>
				</div>
			</div>
		</c:forEach>
		</c:if>
		<div class="chi_siamo" id="chi">
			<h2>Scopri</h2>
			<h3>chi siamo:</h3>
			<div class="testo_foto_chi_siamo">
				<img style="vertical-align:middle" id="foto_chi_siamo"
					src="<c:url value="/resources/img/about-us.png" />">
				<div class="testo_chi_siamo">
					Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ornare
					scelerisque diam eu aliquet. Sed blandit nibh libero, et lacinia
					odio pharetra in. Curabitur efficitur sed nisi in malesuada.
					Vivamus ultricies turpis sed accumsan sagittis. Cras gravida ligula
					ligula, non ullamcorper dui efficitur eu. Nullam vel volutpat
					tortor, at mollis tortor. Nulla sit amet eros egestas, blandit elit
					ut, luctus metus. Aenean et mauris eros.
					<ul>
						<li>Quisque massa ex, dictum congue mi non, cursus eleifend
							felis.</li>
						<li>Proin tempor molestie molestie. Vestibulum fringilla
							lorem ac diam consequat ornare. Nam mollis malesuada ipsum.
							Mauris eget purus vestibulum, placerat quam quis, volutpat quam.
							Praesent vel pellentesque mi.</li>
						<li>Cras non magna vulputate ante mattis vulputate.</li>
						<li>Praesent tincidunt dignissim justo, ac gravida nisi
							ultricies vel.</li>
						<li>Sed sagittis nunc ac gravida dignissim. Vestibulum non
							ipsum gravida, mattis purus vitae, eleifend nulla. Praesent sed
							vulputate mi. Etiam vel risus nec odio vulputate semper a eget
							ex. Suspendisse eget massa aliquet purus tincidunt auctor.</li>
					</ul>
				</div>
			</div>

		</div>
		<div class="conteiner_galleria" id="galleria">
			<h2>Guarda</h2>
			<h3>la nostra galleria</h3>
			<div>
				<img src="<c:url value="/resources/img/galleria1.jpg"/>"> <img
					src="<c:url value="/resources/img/galleria2.jpg"/>"> <img
					src="<c:url value="/resources/img/galleria3.jpg"/>">
			</div>
		</div>
	</div>
</body>

</html>