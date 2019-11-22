<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Home Page</title>
</head>
<body>
<!-- questo non e` un commento di vista: ${mySecret} -->
<%--questo e` un commento di vista (JSTL) ${mySecret} --%>
<%-- NB questo codice non funziona:

Numero di cantanti: ${singers.size()}. <c:if test="${singers.size() ge 0}">Sono pochi!</c:if>

Invocare un metodo e` una di quelle operazioni piu` adatte ad un controller che non ad una vista. Opzioni disponibili:

- chiamare il metodo nel controller, salvare il risultato in un attributo del modello passato alla vista
- creare un tag personalizzato che "wrappi" l'invocazione del metodo desiderato, e richiamare il tag nella vista
- aggirare il limite con <jsp:useBean ...

 --%>
Numero di cantanti: ${fn:length(singers)}. <c:if test="${fn:length(singers) lt 10}">Sono pochi!</c:if>
Numero di cantanti: ${numCantanti}. <c:if test="${numCantanti lt 10}">Sono pochi!</c:if>
<table>
<thead><td>First name</td><td>Last name</td><td>Birth date</td></thead>
<c:forEach items="${singers}" var="s">
<tr><td>${s.firstName}</td><td>${s.lastName}</td></tr>
</c:forEach>
</table>
</body>
</html>