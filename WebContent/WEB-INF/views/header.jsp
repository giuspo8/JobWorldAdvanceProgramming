<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
						</nav> </span> <span><a href="<c:url value="/"/>"><img src="<c:url value="/resources/img/logo.png"/>"></a></span></li>
				<sec:authorize access="hasRole('COMPANY')" var="isCompany" />
				<sec:authorize access="hasRole('USER')" var="isUser" />
				<sec:authorize access="isAuthenticated()" var="isAuth" />

				<c:choose>
					<c:when test="${isCompany}">
					<li><a href="<c:url value="/logout" />">Logout</a></li>
					<li>-</li>
					<li><a href="<c:url value="/company/listjoboffer"/>">Offerte di lavoro</a></li>
					<li>-</li>
					<li><a href="<c:url value="/company/profile"/>" class="login_btn"><sec:authentication property="principal.username" /></a></li>
					</c:when>
					<c:when test="${isUser}">
					<li><a href="<c:url value="/logout" />">Logout</a></li>
					<li>-</li>
					<li><a href="<c:url value="/user/curriculum"/>">Curriculum</a></li>
					<li>-</li>
					<li><a href="<c:url value="/user/profile"/>" class="login_btn"><sec:authentication property="principal.username" /></a></li>
					</c:when>
					<c:otherwise>
						<li><a href="<c:url value="/register"/>">Registrati</a></li>
						<li>-</li>
						<li><a href="<c:url value="/login"/>" class="login_btn">Accedi</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</header>