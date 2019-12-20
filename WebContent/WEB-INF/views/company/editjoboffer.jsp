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
						src="../../<c:url value="resources/img/logo.png"/>"></span></li>
			</ul>
		</div>
	</header>
	<div class="body">
		<div class="container_slide">
			<div class="slide"></div>
		</div>
		<div class="offer">
			<div style="text-align:center; margin:auto;">
			<c:if test="${empty job }">Curriculum non individuato, inizia inserendone uno nuovo:
			<c:url value="/company/joboffer/create" var="action_url" />
			</c:if>
			<c:if test="${not empty job }">
			<c:url value="/company/joboffer/${job.getId()}/update" var="action_url" />
			</c:if>
				       
				<form:form method="POST" action="${action_url}" enctype="multipart/form-data">
					<h3>Informazoni dell'offerta di lavoro:</h3>
             						<table style="text-align:center; width:500px; margin:auto;">
						<tr>
							
							<td colspan="3"><label class="searchsub_lbl" style="text-align:center;"> Posizione:</label></td>
						</tr>
						<tr>
							<td colspan="3" style="text-align:center;"><input value="${job.position}"
								name="position" class="searchsub_input"></td>
						</tr>
						<tr>
							<td colspan="3"><label class="searchsub_lbl" style="text-align:center;">Tipo di contratto:</label></td>
						</tr>
						<tr>
							<td colspan="3" style="text-align:center;"><input value="${job.contractType}"
								name="contractType" class="searchsub_input" ></td>
						</tr>
						<tr>
							<td><label class="searchsub_lbl" style="text-align:center;">Minimo di educazione richiesta: </label></td>
							<td><label class="searchsub_lbl" style="text-align:center;">Minimo di esperienza richiesta: </label></td>
							<td><label class="searchsub_lbl" style="text-align:center;">Data di scadenza offerta:</label></td>
						</tr>
						<tr>
							<td style="text-align:center;"><select value="${job.minEducationLevel}"
								name="minEducationLevel" class="searchsub_input" >
									<c:choose>
	    							<c:when test="${job.minEducationLevel == 'SENZA_STUDI'}">
	    								<option value="SENZA_STUDI" selected>Nessun livello di istruzione</option>
	   								</c:when>    
	    							<c:otherwise>
	    								<option value="SENZA_STUDI" >Nessun livello di istruzione</option>
	    							</c:otherwise>
									</c:choose>
									<c:choose>
	    							<c:when test="${job.minEducationLevel == 'LICENZA_MEDIA'}">
	    								<option value="LICENZA_MEDIA" selected>Licenza media</option>
	   								</c:when>    
	    							<c:otherwise>
	    								<option value="LICENZA_MEDIA" >Licenza media</option>
	    							</c:otherwise>
									</c:choose>
									<c:choose>
									<c:when test="${job.minEducationLevel == 'DIPLOMA_DI_MATURITA'}">
	    								<option value="DIPLOMA_DI_MATURITA" selected>Diploma di maturità</option>
	   								</c:when>    
	    							<c:otherwise>
	    								<option value="DIPLOMA_DI_MATURITA">Diploma di maturità</option>
	    							</c:otherwise>
									</c:choose>
									<c:choose>
									<c:when test="${job.minEducationLevel == 'LAUREA_TRIENNALE'}">
	    								<option value="LAUREA_TRIENNALE" selected>Laurea di primo livello</option>
	   								</c:when>    
	    							<c:otherwise>
	    								<option value="LAUREA_TRIENNALE">Laurea di primo livello</option>
	    							</c:otherwise>
									</c:choose>
									<c:choose>
									<c:when test="${job.minEducationLevel == 'LAUREA_SPECIALISTICA'}">
	    								<option value="LAUREA_SPECIALISTICA" selected>Laurea di secondo livello</option>
	   								</c:when>    
	    							<c:otherwise>
	    								<option value="LAUREA_SPECIALISTICA">Laurea di secondo livello</option>
	    							</c:otherwise>
									</c:choose>
								</select></td>
							<td  style="text-align:center;"><input value="${job.minExperience}"
								name="minExperience" class="searchsub_input" ></td>
							<td  style="text-align:center;"><input value="${job.expiringDate}"
								name="expiringDate" class="searchsub_input" ></td>
						</tr>
						<tr>
							<td colspan="3"><label class="searchsub_lbl" style="text-align:center;">Descrizione lavoro:</label></td>
						</tr>
						<tr>
							<td colspan="3" style="text-align:center;"><textarea placeholder="Inserisci qui una descrizione della tua azienda"
								name="description" class="searchsub_input" style="height:500px;;">${job.description}</textarea></td>
						</tr>
						<tr>
							<td><label class="searchsub_lbl" style="text-align:center;">Regione:</label></td>
							<td><label class="searchsub_lbl" style="text-align:center;">Provincia:</label></td>
							<td><label class="searchsub_lbl" style="text-align:center;">Città:</label></td>
						</tr>
						<tr>
							<td style="text-align:center;"><input value="${job.region}"
								name="region" class="searchsub_input" ></td>
							<td style="text-align:center;"><input value="${job.province}"
								name="province_" class="searchsub_input" ></td>
							<td style="text-align:center;"><input value="${job.town}"
								name="town" class="searchsub_input" ></td>
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