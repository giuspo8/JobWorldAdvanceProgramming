<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>

<div class="body">
	<div class="container_slide"></div><hr>
	<div class="offer">
		<div style="text-align: center; margin: auto;">
			<c:url value="/admin/update" var="action_url" />
			       
			<form:form method="POST" action="${action_url}"
				enctype="multipart/form-data">
				<h3>Cambia le informazioni della tua azienda:</h3>
				
				<c:if test="${not empty errorMassage}">
					<div style="color: red; font-weight: bold; margin: 30px 0px;">${errorMassage}</div>
				</c:if>
             						<table
					style="text-align: center; width: 500px; margin: auto;">
					<tr>
						<td><label class="searchsub_lbl" style="text-align: center;">La tua mail:</label></td>
					</tr>
					<tr>
						<td style="text-align: center;"><input
							value="${user.getEmail()}" disabled="disabled" name="username"
							class="searchsub_input"></td>
					</tr>
					<tr>
						<td><label class="searchsub_lbl" style="text-align: center;">Cambia la tua password:</label></td>
					</tr>
					<tr>
						<td style="text-align: center;"><input
							type='password'
							 name="password"
							class="searchsub_input" placeholder="Inserisci qui la tua vecchia password"></td>
					</tr>
					<tr>
						<td style="text-align: center;"><input
							 name="new_password"
							class="searchsub_input" placeholder="Inserisci qui la tua nuova password"></td>
					</tr>
				</table>
				<div id="searchsub" style="text-align: center;">
					<input type="submit" value="Invio">  
				</div>
			</form:form>
		</div>
	</div>
</div>