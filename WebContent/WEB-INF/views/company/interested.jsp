<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>

<div class="body">
	<div class="container_slide">
		<div class="slide"></div>
	</div>
	<div class="offer">
		<div style="text-align: center; margin: auto;">
			<table class="tabelle_lista">
				<tr>
					<th>Nome</th>
					<th>Cognome</th>
					<th>Età</th>
					<th>Numero di telefono</th>
					<th>Email</th>
					<th>Curriculum</th>
				</tr>
				<c:forEach items="${candidencies }" var="candidate">
					<tr>
						<td>${candidate.getFirstName()}</td>
						<td>${candidate.getSecondName()}</td>
						<td>${now_year - candidate.getBirthDate().getYear()}</td>
						<td>${candidate.getNumber()}</td>
						<td>${candidate.getUser().getEmail()}</td>
						<td><a
							href="<c:url value="/company/curriculum"/>?email=${candidate.getUser().getEmail()}">Visualizza
								Curriculum</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>
