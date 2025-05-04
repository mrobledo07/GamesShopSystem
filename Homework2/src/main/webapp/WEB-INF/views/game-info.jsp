<%-- 
    Document   : game-info
    Created on : 21 ene 2024, 14:45:39
    Author     : micha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
        <script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
        <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
        <title>Game Info</title>
        <style>
            .game-item {
                width: 500px; 
                height: 170px;
                display: flex;
                align-items: flex-start;
                gap: 20px;
                margin-top: 60px;
                margin-left: auto;
                margin-right: auto;
            }

            .info {
                display: grid;
                width: 200px;
            }
            
            .info input {
                margin-top: 10px;
            }
            
            .imatge {
                display: grid;
                margin-left: 30px
            }
            
            .imatge h5 {
                text-align: center;
                margin-top: 18px;
            }
            
            .game-item img {
                width: 200px; 
                height: 130px; 
                border: 2px solid black;
            }
            
           .status-indicator {
                height: 10px;
                width: 10px;
                border-radius: 50%;
                display: inline-block;
                margin-right: 5px;
            }

            .disponible {
                background-color: lime;
            }

            .no-disponible {
                background-color: red;
            }
            
        </style>
    </head>
    <body style="background-color: beige">
        <h1 style="font-family: fantasy; text-align: center">${gameCart.name}</h1>
        <div class="game-item">
            <div class="imatge">
                <img src="${pageContext.request.contextPath}/resources/img/${gameCart.name}.jpeg" alt="Imagen de la caratula de ${game.name}" />
                <h5>
                    <span class="status-indicator ${gameCart.availability ? 'disponible' : 'no-disponible'}"></span>
                    ${gameCart.availability ? "Disponible" : "No disponible"}
                </h5> 
            </div>
            <div class="info">
                <p>Consola: ${gameCart.gameConsole}</p>
                <p>Descripció: ${gameCart.description}</p>
                <h4>Preu: ${gameCart.price} €</h4>
                <form action="${mvc.uri('add-to-cart')}" method="post">
                    <input type="hidden" name="gameId" value="${gameCart.id}" />
                    <input type="hidden" name="gameName" value="${gameCart.name}" />
                    <input type="hidden" name="gameConsole" value="${gameCart.gameConsole}" />
                    <input type="hidden" name="gameAvailability" value="${gameCart.availability}" />
                    <input type="hidden" name="gamePrice" value="${gameCart.price}" />
                    <input type="hidden" name="gameDescription" value="${gameCart.description}" />
                    <input type="hidden" name="gameType" value="${gameCart.type}" />
                    <c:if test="${gameCart.availability eq 'true'}">
                        <input class="btn btn-primary" type="submit" value="Afegir a la cistella" />
                    </c:if>
                </form>
            </div>
        </div>
    </body>
</html>
