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
		<div class="offer">
			<c:url value="/add" var="action_url" />
			        
			<form:form id="regform" method="POST" action="${action_url}">
				<h3 style="text-align: center">Registrati</h3>
				<c:if test="${not empty con}">
					<div style="color: red; font-weight: bold; margin: 30px 0px; text-align:center">Uno o più dati obbligatori non sono stati inseriti</div>
				</c:if>
				<c:if test="${not empty existing}">
					<div style="color: red; font-weight: bold; margin: 30px 0px; text-align:center">L'E-Mail inserita
					è già stata utilizzata</div>
				</c:if>
				<c:if test="${not empty error}">
					<div style="color: red; font-weight: bold; margin: 30px 0px; text-align:center">L'E-Mail inserita
					non è una E-Mail ben formata</div>
				</c:if>
				<c:if test="${not empty date_error}">
					<div style="color: red; font-weight: bold; margin: 30px 0px; text-align:center">La
						data inserita non è formattata come giorno/mese/anno in numero</div>
				</c:if>
				<h4 style="text-align: center">Che tipo di utente sei?</h4>
				<div style="text-align: center">
					<input type="radio" name="type" checked value="person">Persona<br>
					<input type="radio" name="type" value="company">Azienda<br>
				</div>
				<h4 style="text-align: center">Inserisci le tue informazioni:</h4>
				<div id="regform_div">
					             
					<table style="text-align: center; width: 500px; margin: auto;">
						<tr>
							<td><label class='searchsub_lbl'>E-mail</label></td>
						</tr>
						<tr>
							<td><input placeholder='E-mail' name='email'
								class='searchsub_input'></td>
						</tr>
					</table>
					<table style="text-align: center; width: 500px; margin: auto;">
						<tr>
							<td><label class='searchsub_lbl'>Password</label></td>
							<td><label class='searchsub_lbl'>Ripeti Password</label></td>
						</tr>
						<tr>
							<td><input type='password' id='p' placeholder='Password'
								name='password' class='searchsub_input'></td>
							<td><input type='password' id='rp' oninput='passvalid()'
								placeholder='Ripeti Password' class='searchsub_input'></td>
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
					<table style="text-align: center; width: 500px; margin: auto;">
						<tr>
							<td><label class='searchsub_lbl'>Interessi</label><select
								multiple name='interests' class='searchsub_input'>
									<option value='' selected>Interessi</option>
									<option value='INFORMATICA'>Informatica</option>
									<option value='ECONOMIA'>Economia</option>
							</select></td>
						</tr>
					</table>
					<div style="text-align: center" id='searchsub'>
						<input disabled id='submit' type='submit' value='Invio'>  
					</div>
				</div>
			</form:form>
		</div>
	</div>
</div>