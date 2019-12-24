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

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

			<div class="offer">
			<form:form id="curriculum_form" method="POST" action="${action_url}" style="text-align:center; width:1000px; margin:auto;">
					<h3>Ecco il curriculum di: ${person.getFirstName()} ${person.getSecondName()} </h3> 
					<table style="text-align:center; width:700px; margin:auto;">
					<tr>
						<td><label class='searchsub_lbl'>Esperienze lavorative</label><br></td>
					</tr>
					<tr>
						<td><textarea class="searchsub_input" form='curriculum_form' name='workExperience' disabled="disabled" style=" height:400px; resize:none; margin:auto;">${curriculum.workExperience }</textarea><br></td>
					</tr>
					<tr>
						<td><label class='searchsub_lbl'>Istruzione e Formazione</label><br></td>
					</tr>
					<tr>
						<td><textarea class="searchsub_input" form='curriculum_form' name='education' disabled="disabled" style="  height:400px; resize:none; margin:auto;">${curriculum.education }</textarea><br></td>
					</tr>
					<tr>
						<td><label class='searchsub_lbl'>Competenze</label><br></td>
					</tr>
					<tr>
						<td><textarea class="searchsub_input" form='curriculum_form' name='personalSkills' disabled="disabled" style="  height:400px; resize:none; margin:auto;">${curriculum.personalSkills }</textarea><br></td>
					</tr>
					<tr>
						<td><label class='searchsub_lbl'>Altre informazioni</label><br></td>
					</tr>
					<tr>
						<td><textarea class="searchsub_input" form='curriculum_form' name='additionalInfo' disabled="disabled" style="  height:400px; resize:none; margin:auto;">${curriculum.additionalInfo }</textarea></td>
					</tr>
					</table>
					<input type='hidden' name='email' value='${email }'>
				</form:form>
				</div>
	<footer>