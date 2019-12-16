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

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
										<tr>
											<td><label class="searchsub_lbl">Posizione
													ricercata</label></td>
										</tr>
										<tr>
											<td><input placeholder="Posizione ricercata"
												name="position" class="searchsub_input"></td>
										</tr>
										<tr>
											<td><label class="searchsub_lbl">Tipologia di
													contratto</label></td>
										</tr>
										<tr>
											<td><input placeholder="Tipologia di contratto"
												name="contractType" class="searchsub_input"></td>
										</tr>
										<tr>
											<td><label class="searchsub_lbl">Livello di
													esperienza richiesto</label></td>
										</tr>
										<tr>
											<td><input placeholder="Esperienza" name="minExperience"
												class="searchsub_input"></td>
										</tr>
										<tr>
											<td><label class="searchsub_lbl">Livello di
													istruzione richiesto</label></td>
										</tr>
										<tr>
											<td><select name="minEducationLevel" class="searchsub_input searchsub_select">
													<option value="" selected>Livello di istruzione</option>
													<option value="SENZA_STUDI">Nessun livello di istruzione</option>
													<option value="LICENZA_MEDIA">Licenza media</option>
													<option value="DIPLOMA_DI_MATURITA">Diploma di maturità</option>
													<option value="LAUREA_TRIENNALE">Laurea di primo livello</option>
													<option value="LAUREA_SPECIALISTICA">Laurea di secondo livello</option>
											</select></td>
										</tr>
										<tr>
											<td><label class="searchsub_lbl">Regione</label></td>
										</tr>
										<tr>
											<td><input placeholder="Regione" name="region"
												class="searchsub_input" value="${region}"></td>
										</tr>
										<tr>
											<td><label class="searchsub_lbl">Provincia</label></td>
										</tr>
										<tr>
											<td><input placeholder="Provincia" name="province"
												class="searchsub_input"></td>
										</tr>
										<tr>
											<td><label class="searchsub_lbl">Città</label></td>
										</tr>
										<tr>
											<td><input placeholder="Città" name="town"
												class="searchsub_input" value="${city}"></td>
										</tr>
									</table>
									<div id="searchsub">
										<input type="submit" value="Invio">  
									</div>
								</form:form>
							</div>
						</nav> </span> <span><img src="<c:url value="../resources/img/logo.png"/>"></span></li>
				<li><a href="">Logout</a></li>
				<li>-</li>
				<li><a href="" class="login_btn">${firstName} ${secondName }</a></li>
			</ul>
		</div>
	</header>
	<div class="body">
		<div class="container_slide">
			<div class="slide">
				<img src="<c:url value="../resources/img/jobworldhome.jpeg"/>">
			<c:if test="${empty curriculum }">Curriculum non individuato, inizia inserendone uno nuovo:
			<c:url value="/user/createCurriculum" var="action_url" />
			</c:if>
			<c:if test="${not empty curriculum }">
			<c:url value="/user/updateCurriculum" var="action_url" />
			</c:if>
			<form:form id="curriculum_form" method="POST" action="${action_url}">
					<h2>Inserisci curriculum</h2>
					<div id="regform_div">             		
					<label class='searchsub_lbl'>Esperienze lavorative</label><br>
					<textarea form='curriculum_form' name='workExperience'>${curriculum.workExperience }</textarea><br>
					<label class='searchsub_lbl'>Istruzione e Formazione</label><br>
					<textarea form='curriculum_form' name='education'>${curriculum.education }</textarea><br>
					<label class='searchsub_lbl'>Competenze</label><br>
					<textarea form='curriculum_form' name='personalSkills'>${curriculum.personalSkills }</textarea><br>
					<label class='searchsub_lbl'>Altre informazioni</label><br>
					<textarea form='curriculum_form' name='additionalInfo'>${curriculum.additionalInfo }</textarea>
					<input type='hidden' name='email' value='${email }'>
					<div id='searchsub'>
					
						<input id='curriculum_submit' type='submit' value='Invio'>  
					</div>
				</form:form>
				<c:if test="${not empty curriculum}">
				<a href="<c:url value="/user/deleteCurriculum?email="/><sec:authentication property="principal.username" />">Elimina Curriculum</a>
				</c:if>
				${email }
		</div>

	</div>
	<footer>
		<div class="container">
			<h2>Le principali aziende</h2>
			<h3>che collaborano con noi</h3>
			<div>
				<c:forEach items="${best_three}" var="j">
				<img src="<c:url value="${j.getCompany().getUser().getImage()}"/>">
				</c:forEach>
			</div>
		</div>

		<div>
			<div class="faq">
				<a href="<c:url value="/chisiamo"/>">Tutto su JobWorld</a> - <a
					href="">FAQ - Aiuto</a>
			</div>
		</div>
		<p>JobWorld.it ottempera alla normativa vigente sulla Privacy.
			JobWorld S.r.l. a socio unico, società soggetta a direzione e
			coordinamento di Coordination S.r.l. - Sede legale Via Monte D'Ago
			19, 20159 Ancona. Codice Fiscale, Partita I.V.A., Registro Imprese
			0519849; Capitale Sociale 66.150 i. v. JobWorld S.r.l. è iscritta
			all'Albo Informatico - Sez. IV - Aut. Min. Prov. Prot. n. 5849456</p>
	</footer>


</body>

</html>