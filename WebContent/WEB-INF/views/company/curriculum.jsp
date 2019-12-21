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

	<div class="body">
		<div class="container_slide">
			<div class="slide">
			<form:form id="curriculum_form" method="POST" action="${action_url}">
					<h2>Ecco il curriculum di: ${person.getFirstName()} ${person.getSecondName()} </h2>
					<div id="regform_div">             		
					<label class='searchsub_lbl'>Esperienze lavorative</label><br>
					<textarea form='curriculum_form' name='workExperience' disabled="disabled">${curriculum.workExperience }</textarea><br>
					<label class='searchsub_lbl'>Istruzione e Formazione</label><br>
					<textarea form='curriculum_form' name='education' disabled="disabled">${curriculum.education }</textarea><br>
					<label class='searchsub_lbl'>Competenze</label><br>
					<textarea form='curriculum_form' name='personalSkills' disabled="disabled">${curriculum.personalSkills }</textarea><br>
					<label class='searchsub_lbl'>Altre informazioni</label><br>
					<textarea form='curriculum_form' name='additionalInfo' disabled="disabled">${curriculum.additionalInfo }</textarea>
					<input type='hidden' name='email' value='${email }'>
					</div>
				</form:form>
		</div>

	</div>
	</div>
	<footer>