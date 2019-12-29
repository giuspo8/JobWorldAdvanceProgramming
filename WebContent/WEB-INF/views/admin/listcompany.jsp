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
			<table class="tabelle_lista">
				<tr>
					<th>Nome</th>
					<th>E-mail</th>
					<th></th>
					<th></th>
					<th></th>
				</tr>
				<c:forEach items="${companys }" var="company">
					<tr>
						<td>${company.name}</td>
						<td>${company.getUser().getEmail()}</td>
						<td><a href="<c:url value="/admin/listjoboffer/${company.id}"/>"> Visualizza offerte di lavoro</a></td>
						<td><a href="<c:url value="/admin/deletecompany/${company.id}"/>">Elimina</a></td>
						<td><a href="<c:url value="/admin/editcompany/${company.id}"/>">Modifica</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>