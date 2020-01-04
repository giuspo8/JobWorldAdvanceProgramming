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
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>

<sec:authorize access="hasRole('USER')" var="isUser" />
<sec:authorize access="hasRole('ADMIN')" var="isAdmin" />

<div class="body">
	<div class="container_slide">
		<hr>
		<div class="offer">
			<div style="text-align: center; margin: auto;">
				<c:choose>
					<c:when test="${isUser}">
						<c:url value="/user/update" var="action_url" />
					</c:when>
					<c:when test="${isAdmin}">
						<c:url value="/admin/listuser/${person.getId()}/update"
							var="action_url" />
					</c:when>
				</c:choose>
				       
				<form:form method="POST" action="${action_url}"
					enctype="multipart/form-data">
					<c:choose>
						<c:when test="${isUser}">
							<h3>Stai visualizzando le tue informazioni generali</h3>
						</c:when>
						<c:when test="${isAdmin}">
							<h3>Stai visualizzando le informazioni generali di:</h3>
							<h3>${person.getFirstName()} ${person.getSecondName()}</h3>
						</c:when>
					</c:choose>
					<c:if test="${not empty date_error }">
						<div style="color: red; font-weight: bold; margin: 30px 0px;">La
							data inserita non è formattata come giorno/mese/anno in numero</div>
					</c:if>
					<c:if test="${not empty con}">
						<div
							style="color: red; font-weight: bold; margin: 30px 0px; text-align: center">Uno
							o più dati obbligatori non sono stati inseriti</div>
					</c:if>
             						<table
						style="text-align: center; width: 500px; margin: auto;">
						<tr>
							<td><label class="searchsub_lbl" for="fileupload"
								style="text-align: center;"> Questa è la tua attuale
									foto se vuoi modificarla selezionane una con sfoglia:</label></td>
						</tr>
						<tr>

							<td><img src="<c:url value="${user.getImage()}"/>"></td>
						</tr>
						<tr>
							<td style="text-align: center;"><input type="file"
								value="${user.getImage()}" name="image"></td>
						</tr>
					</table>
					<table style="text-align: center; width: 500px; margin: auto;">
						<tr>
							<td><label class="searchsub_lbl" style="text-align: center;">Nome</label></td>
							<td><label class="searchsub_lbl" style="text-align: center;">Cognome</label></td>
						</tr>
						<tr>
							<td style="text-align: center;"><input
								value="${person.getFirstName()}" name="firstName"
								class="searchsub_input"></td>
							<td style="text-align: center;"><input
								value="${person.getSecondName()}" name="secondName"
								class="searchsub_input"></td>
						</tr>
					</table>
					<table style="text-align: center; width: 500px; margin: auto;">
						<tr>
							<td><label class="searchsub_lbl" style="text-align: center;">Modifica
									la tua descrizione: </label></td>
						</tr>
						<tr>
							<td style="text-align: center;"><textarea
									placeholder="Inserisci qui una descrizione della tua azienda"
									name="description" class="searchsub_input"
									style="height: 500px;">${user.getDescription()}</textarea></td>
						</tr>
					</table>
					<table style="text-align: center; width: 500px; margin: auto;">
						<tr>
							<td><label class="searchsub_lbl" style="text-align: center;">Data
									di nascita</label></td>
							<td><label class="searchsub_lbl" style="text-align: center;">Numero
									di telefono</label></td>
						</tr>
						<tr>
							<td style="text-align: center;"><input value="${date}"
								placeholder="Giorno/Mese/Anno in numeri" name="birthDate"
								class="searchsub_input"></td>
							<td style="text-align: center;"><input
								value="${person.getNumber()}" name="number"
								class="searchsub_input"></td>
						</tr>
					</table>
					<!-- 
						<tr>
							<td style="text-align: center;">
							<select name="minEducationLevel" class="searchsub_input searchsub_select">
							<c:forEach items="${person.getInterests()}" var="i">
							</c:forEach>
							</select>
							</td>
						</tr> -->
					<div id="searchsub" style="text-align: center; margin-top: 20px;">
						<input type="submit" value="Invio">  
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>
