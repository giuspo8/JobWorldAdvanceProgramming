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


<div class="body">
	<div class="container_slide">
		<hr>
		<h3>Informazioni generali</h3>
		<div class="offer">
			<div style="text-align: center; margin: auto;">
				<c:url value="/user/update" var="action_url" />
				       
				<form:form method="POST" action="${action_url}"
					enctype="multipart/form-data">
					<h3>Visualizza le tue informazioni</h3>
             						<table
						style="text-align: center; width: 500px; margin: auto;">
						<tr>
							<td><label class="searchsub_lbl" for="fileupload"
								style="text-align: center;"> Questa è la tua attuale
									foto se vuoi modificarla selezionane una con sfoglia:</label></td>
						</tr>
						<tr>

							<td><img src="../../<c:url value="${user.getImage()}"/>"></td>
						</tr>
						<tr>
							<td style="text-align: center;"><input type="file"
								value="${user.getImage()}" name="image"></td>
						</tr>
						</table>
						<table
						style="text-align: center; width: 500px; margin: auto;">
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
						<table
						style="text-align: center; width: 500px; margin: auto;">
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
						<table
						style="text-align: center; width: 500px; margin: auto;">
						<tr>
							<td><label class="searchsub_lbl" style="text-align: center;">Data
									di nascita</label></td>
							<td><label class="searchsub_lbl" style="text-align: center;">Numero
									di telefono</label></td>
						</tr>
						<tr>
							<td style="text-align: center;"><input value="${date}"
								name="birthDate" class="searchsub_input"></td>
							<td style="text-align: center;"><input
								value="${person.getNumber()}" name="number"
								class="searchsub_input"></td>
						</tr>
						</table><!-- 
						<tr>
							<td style="text-align: center;">
							<select name="minEducationLevel" class="searchsub_input searchsub_select">
							<c:forEach items="${person.getInterests()}" var="i">
							</c:forEach>
							</select>
							</td>
						</tr> -->
					<div id="searchsub" style="text-align: center;margin-top:20px;">
						<input type="submit" value="Invio">  
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>
