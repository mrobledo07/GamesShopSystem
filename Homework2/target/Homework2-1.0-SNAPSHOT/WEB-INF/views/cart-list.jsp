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
        <title>Cart List</title>
        
        <style>
            .game-item {
                display: flex;
                align-items: center; 
                height: 75px;
                margin-top: 20px;
                margin-bottom: 20px; 
                border: 2px solid silver; 
                border-radius: 3%;
                background-color: white;
            }

            .game-item img {
                width: 9%; 
                height: 100%; 
                border-radius: 3%;
            }

            .game-name-console {
                display: flex;
                align-items: center; 
                margin-left: 20px;
                margin-right: 20px; 
            }
            
            .game-price {
                margin-right: 40px;
            }

            
        </style>
    </head>
    <body style="background-color: beige">
    <h1 class="title" style="text-align: center; font-family: fantasy; margin-top: 20px">Cistella</h1>
    <div class="text-center">
        <a href="${mvc.uri('perform-rent')}" class="btn btn-primary">Formalitzar alquiler</a>
    </div>
    <div class="container">
            <div class="row"> 
                <c:forEach var="game" items="${cart.games}">
                    <div class="col-12">
                        <div class="game-item">
                            <img src="${pageContext.request.contextPath}/resources/img/${game.name}.jpeg" alt="Imagen de la caratula de ${game.name}" />
                            <h3 class="game-name-console">${game.name} (${game.gameConsole})</h3>
                            <h3 class="game-price"> ${game.price}</h3>
                            <form action="${mvc.uri('remove-from-cart')}" method="post">
                                <input type="hidden" name="gameId" value="${game.id}" />
                                <input type="hidden" name="gameName" value="${game.name}" />
                                <input type="hidden" name="gameConsole" value="${game.gameConsole}" />
                                <input type="hidden" name="gameAvailability" value="${game.availability}" />
                                <input type="hidden" name="gamePrice" value="${game.price}" />
                                <input type="hidden" name="gameDescription" value="${game.description}" />
                                <input type="hidden" name="gameType" value="${game.type}" />
                                <input type="submit" value="Eliminar de la cistella" />
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </div>
        <a href="/Homework2/Web/GamesList" class="btn btn-primary">Torna</a>
        </div>
    </body>
</html>