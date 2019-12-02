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
			<div class="slide">
				<c:url value="/add" var="action_url" />
				        
				<form:form id="regform" method="POST" action="${action_url}">
					<h2>Registrati</h2>
					<h4>Che tipo di utente sei?</h4>
					<input type="radio" name="type" checked value="person">Persona<br>
					<input type="radio" name="type" value="company">Azienda<br>
					<div id="regform_div">
             		<table>
						<tr>
							<td><label class='searchsub_lbl'>E-mail</label></td>
						</tr>
						<tr>
							<td><input placeholder='E-mail' name='email'
								class='searchsub_input'></td>
						</tr>
						</table><table>
						<tr>
							<td><label class='searchsub_lbl'>Password</label></td>
							<td><label class='searchsub_lbl'>Ripeti Password</label></td>
						</tr>
						<tr>
							<td><input type='password' id='p' placeholder='Password' name='password'
								class='searchsub_input'></td>
							<td><input type='password' id='rp' oninput='passvalid()' placeholder='Ripeti Password'
								class='searchsub_input'></td>
						</tr>
						<tr>
							<td><label class='searchsub_lbl'>Nome</label></td>
							<td><label class='searchsub_lbl'>Cognome</label></td>
						</tr>
						<tr>
							<td><input placeholder='Nome' name='firstName'
								class='searchsub_input'></td>
							<td><input placeholder='Cognome' name='secondName'
								class='searchsub_input'></td>
						</tr>
						<tr>
							<td><label class='searchsub_lbl'>Data di nascita</label></td>
							<td><label class='searchsub_lbl'>Numero di telefono</label></td>
						<tr>
							<td><input placeholder='Data di nascita' name='birthDate'
								class='searchsub_input'></td>
							<td><input placeholder='Numero di telefono' name='number'
								class='searchsub_input'></td>
						</tr>						
					</table>
					<table><tr>
							<td><label class='searchsub_lbl'>Interessi</label><select
								multiple name='interests'
								class='searchsub_input'>
									<option value='' selected>Interessi</option>
									<option value='INFORMATICA'>Informatica</option>
									<option value='ECONOMIA'>Economia</option>
							</select></td>
						</tr></table>
						<label class='searchsub_lbl'>Descrizione</label><br>
					<textarea form='regform' name='description'></textarea>
					<div id='searchsub'>
						<input disabled id='submit' type='submit' value='Invio'>  
					</div>
					</div>
				</form:form>
			</div>
		</div>
		<footer> </footer>
</body>
</html>