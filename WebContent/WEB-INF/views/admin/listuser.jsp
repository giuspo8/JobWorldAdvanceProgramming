<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<div class="body">
	<div class="container_slide">
		<div class="slide"></div>
	</div>
	<div class="offer">
		<div style="text-align: center; margin: auto;">
		<c:if test="${not empty con}">
						<div
							style="color: red; font-weight: bold; margin: 30px 0px; text-align: center">Uno
							o più dati obbligatori non sono stati inseriti</div>
					</c:if>
			<table class="tabelle_lista">
				<tr>
					<th>E-mail</th>
					<th>Nome</th>
					<th>Cognome</th>
					<th>EtÃ </th>
					<th>Curriculum</th>
					<th></th>
					<th></th>
				</tr>
				<c:forEach items="${people }" var="person">
					<tr>
						<td>${person.getUser().getEmail()}</td>
						<td>${person.getFirstName()}</td>
						<td>${person.getSecondName()}</td>
						<td>${now_year - person.getBirthDate().getYear()}</td>
						<td><a href="<c:url value="/admin/curriculum/${person.getId()}"/>"> Visualizza curriculum</a></td>
						<td><a href="<c:url value="/admin/listuser/${person.getId()}/delete"/>">Elimina</a></td>
						<td><a href="<c:url value="/admin/listuser/${person.getId()}/edit" />">Modifica</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>