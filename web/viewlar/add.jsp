<%--
  Created by IntelliJ IDEA.
  User: Марат
  Date: 17.06.2020
  Time: 18:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Добавление команды</title>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    </head>
    <body class="w3-light-grey">
        <div class="w3-container w3-blue-grey w3-opacity w3-right-align">
            <h1>Чемпионат по футболу</h1>
        </div>
        <div class="w3-container w3-padding">
            <%
                if(request.getAttribute("teamName") != null) {
                    out.println("<div class=\"w3-panel w3-green w3-display-container w3-card-4 w3-round\">\n" +
                            "<span onclick=\"this.parentElement.style.display='none'\"\n" +
                            "class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-green w3-border w3-border-green w3-hover-border-grey\">×</span>\n" +
                            "<h5>Команда '" + request.getAttribute("teamName") + "' добавлена!</h5>\n" +
                            "</div>");
                }
            %>
            <div class="w3-card-4">
                <div class="w3-container w3-center w3-green">
                    <h2>
                        Добавление команды
                    </h2>
                </div>
                <form method="post" class="w3-selection w3-light-grey w3-padding">
                    <label>id:
                        <input type="text" name="id" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
                    </label>
                    <label>name:
                        <input type="text" name="name" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
                    </label>
                    <label>country:
                        <input type="text" name="country" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
                    </label>
                    <label>city:
                        <input type="text" name="city" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
                    </label>
                    <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom">Добавить</button>
                </form>
            </div>
        </div>
        <div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
            <button class="w3-btn w3-round-large" onclick="location.href='/'">На главную</button>
        </div>
    </body>
</html>
