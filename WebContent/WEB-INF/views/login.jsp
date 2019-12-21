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
			<div class="slide"></div>
		</div>
		<div class="offer" style="width: 50%;">
			<div style="text-align:center; width:80%; margin:auto;">
				       
				<form name='login' action="<c:url value="/login" />" method='POST'>
					<h2>LOGIN</h2>
					<c:if test="${not empty errorMessage}"><div style="color:red; font-weight: bold; margin: 30px 0px;">${errorMessage}</div></c:if>
             						<table style="text-align:center; margin:auto;">
						<tr>
							<td><label class="searchsub_lbl" style="text-align:center;">Email</label></td>
						</tr>
						<tr>
							<td style="text-align:center;"><input type='text' placeholder="Inserisci qui la tua e-mail"
								name="username" class="searchsub_input" ></td>
						</tr>
						<tr>
							<td><label class="searchsub_lbl" style="text-align:center;">Password</label></td>
						</tr>
						<tr>
							<td style="text-align:center;"><input type='password' placeholder="Inserisci qui la tua password"
								name="password" class="searchsub_input"></td>
						</tr>
					</table>
					<div id="searchsub" style="text-align:center;">
						<input type="submit" value="Invio">  
					</div>
				</form>
			</div>
		</div>
	</div>
	<footer>
		