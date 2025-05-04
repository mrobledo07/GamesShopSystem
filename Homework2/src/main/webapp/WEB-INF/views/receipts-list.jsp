<%-- 
    Document   : receipts-list
    Created on : 20 ene 2024, 18:43:18
    Author     : micha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Receipts</title>
    </head>
    <body>
        <c:forEach var="receipt" items="${receipts}">
            ${receipt}<br/>
        </c:forEach>   
    </body>
</html>
