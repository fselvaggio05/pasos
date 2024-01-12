<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Ejemplo de JSTL y forEach</title>
</head>
<body>
    <h1>Lista de Nombres</h1>
    
    <ul>
       <%--  <c:forEach var="nombre" items="${listaNombres}">
            <li>${nombre}</li>
        </c:forEach> --%>
        
          <c:forEach var="pract" items="${practicas}">
                            <td><c:out value="${pract.id_practica}"></c:out></td>
                            <td><c:out value="${pract.descripcion}"></c:out></td>
                            <td><c:out value="${pract.id_equipo}"></c:out></td>
		  </c:forEach>
		  
		  
    </ul>
</body>
</html>