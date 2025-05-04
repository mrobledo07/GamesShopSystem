<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="deim.urv.cat.homework2.controller.UserForm" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
        <script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
        <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>

        <!-- FontAwesome -->
        <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
              rel="stylesheet"
              integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
              crossorigin="anonymous"/>
        
        <title>Games List</title>
        <style>
            .game-item {
                width: 280px; 
                height: 395px;
                border: 2px solid silver;
                border-radius: 3%;
                background-color: white;
                text-align: center; 
                margin-top: 13px;
                margin-left: auto;
                margin-right: auto;
                cursor: pointer;
            }
            
            .game-item:hover {
                color: #204d74;
            }

            .game-item img {
                width: 100%;
                height: 45%;
                border-top-left-radius: 3%;
                border-top-right-radius: 3%;
                margin-bottom: -10px; 
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
            
            .header {
                display: flex;
                flex-direction: column;
                align-items: center;
            }

            .filter-cart-container {
                display: flex;
                justify-content: space-between;
                align-items: center;
                width: 100%;
                max-width: 1200px;
                margin-bottom: 30px;
                border-bottom: 2px solid #DFE0D7;
                border-top: 2px solid #DFE0D7;
                padding-bottom: 5px;
                padding-top: 5px;
                background-color: #F3F5EA;
            }
            
            .filter-container {
                margin-left: 32px;
            }
            
            .cart-container {
                margin-right: 15px;
            }

        </style>
    </head>
    <body style="background-color: beige">
        <div class="header">
        <h2 style="font-family: fantasy;">
            <% if (session != null && session.getAttribute("user") != null) { 
                        UserForm user = (UserForm)session.getAttribute("user"); %>
                Hola, <%= user.getUsername() %>
                <a href="/Homework2/Web/Logout" class="btn btn-primary" style="margin-left:10px">Logout</a>
                <a href="/Homework2/Web/Receipts" class="btn btn-primary" style="margin-left:10px" target="_blank">Receipts</a>
            <% } else { %>
                <a href="${mvc.uri('show-log-in')}">Login</a>
            <% } %>
        </h2>            
            <h1 class="title" style="text-align: center; font-family: fantasy; margin-bottom: 30px">Videojocs Retro</h1>
            <div class="filter-cart-container">
                <div class="filter-container">
                    <form action="${mvc.uri('games-list-parameters')}" method="post">    
                        <select name="Type">
                            <option value="" selected disabled>Type</option>
                            <option value="PLATAFORM">Plataform</option>
                            <option value="ACTION">Action</option>
                            <option value="FIGHT">Fight</option>
                            <option value="FPS">FPS</option>
                            <option value="RPG">RPG</option>
                            <option value="ROL">Rol</option>
                        </select>
                        <select name="Console">
                            <option value="" selected disabled>Console</option>
                            <option value="GAME_BOY">Game Boy</option>
                            <option value="MEGA_DRIVE">Mega Drive</option>
                            <option value="ATARI_2600">Atari 2600</option>
                            <option value="NINTENDO_64">Nintendo 64</option>
                        </select>    
                        <input type="submit" value="Aplicar filtre"/>
                    </form>
                </div>
                        
                <div class="cart-container">
                    <a href="${mvc.uri('do-rent')}">
                        <img src="${pageContext.request.contextPath}/resources/svg/cart3.svg" />
                    </a>    
                    <span>${cart.nGames}</span>
                </div>  
            </div>
        </div>
                
        <div class="container">
            <div class="row"> 
                <c:forEach var="game" items="${games}" varStatus="status">
                    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                        <div class="game-item" onclick="document.getElementById('formulari${status.index}').submit()">
                            <img src="${pageContext.request.contextPath}/resources/img/${game.name}.jpeg" alt="Imagen de la caratula de ${game.name}" />
                            <h3>${game.name} (${game.gameConsole})</h3>
                            <h3> ${game.price} €</h3>
                            <h4>
                                <span class="status-indicator ${game.availability ? 'disponible' : 'no-disponible'}"></span>
                                ${game.availability ? "Disponible" : "No disponible"}
                            </h4>    
                        </div>
                        <form id="formulari${status.index}" action="${mvc.uri('get-game-info')}" method="post">
                            <input type="hidden" name="gameId" value="${game.id}" />
                            <input type="hidden" name="gameName" value="${game.name}" />
                            <input type="hidden" name="gameConsole" value="${game.gameConsole}" />
                            <input type="hidden" name="gameAvailability" value="${game.availability}" />
                            <input type="hidden" name="gamePrice" value="${game.price}" />
                            <input type="hidden" name="gameDescription" value="${game.description}" />
                            <input type="hidden" name="gameType" value="${game.type}" />
                        </form>
                    </div>
                </c:forEach>
            </div>
        </div>
        
    </body>
</html>
