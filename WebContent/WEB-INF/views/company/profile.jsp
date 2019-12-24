<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>

<div class="body">
	<div class="container_slide"></div>
	<div class="offer">
		<div style="text-align: center; margin: auto;">
			<c:url value="/company/update" var="action_url" />
			       
			<form:form method="POST" action="${action_url}"
				enctype="multipart/form-data">
				<h3>Cambia le informazioni della tua azienda:</h3>
             						<table
					style="text-align: center; width: 500px; margin: auto;">
					<tr>

						<td><label class="searchsub_lbl" for="fileupload"
							style="text-align: center;"> Questa è la tua attuale foto
								se vuoi modificarla selezionane una con sfoglia:</label></td>
					</tr>
					<tr>
						<td><img src="<c:url value="${user.getImage()}"/>"></td>
					</tr>
					<tr>
						<td style="text-align: center;"><input type="file"
							value="${user.getImage()}" name="image"></td>
					</tr>
					<tr>
						<td><label class="searchsub_lbl" style="text-align: center;">Nome
								azienda:</label></td>
					</tr>
					<tr>
						<td style="text-align: center;"><input
							value="${company.getName()}" name="nome_azienda"
							class="searchsub_input"></td>
					</tr>
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
				<div id="searchsub" style="text-align: center;">
					<input type="submit" value="Invio">  
				</div>
			</form:form>
		</div>
	</div>
</div>