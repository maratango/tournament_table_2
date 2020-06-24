<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Турнирная таблица</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Чемпионат по футболу</h1>
</div>
<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <div class="w3-card-4">
        <div class="w3-container w3-light-blue">
            <h2>
                Турнирная таблица
            </h2>
        </div>
        <%
            out.println(
                    "\t\t<table border=\"2\" frame=\"border\" rules=\"all\" cellspacing=\"1\" cellpadding=\"2\" summary=\"Турнирная таблица АПЛ\">\n" +
//                            "\t\t\t<caption>Турнирная таблица АПЛ</caption>\n" +
                            "\t\t\t<col width=\"2%\">\n" +
                            "\t\t\t<col width=\"21%\">\n" +
                            "\t\t\t<col span=\"7\" width=\"5%\" bgcolor=\"#add8e6\">\n" +
                            "\t\t\t<colgroup width=\"3%\">\n" +
                            "\t\t\t\t<col span=\"7\">\n" +
                            "\t\t\t\t<col span=\"7\" bgcolor=\"#add8e6\">\n" +
                            "\t\t\t</colgroup>\n" +
                            "\t\t\t<tr>\n" +
                            "\t\t\t\t<th>место</th><th>команда</th><th>очки</th><th>победы</th><th>ничьи</th><th>поражения</th><th>голы забитые</th><th>голы пропущенные</th><th>разница голов</th>\t\t\t\n" +
                            "\t\t\t</tr>"
            );

            //запоминаем атрибут запроса turnPositions - список объектов "строки результата селекта"
            List<Object[]> turnTable = (List<Object[]>) request.getAttribute("turnPositions");
            int pos = 0;

            for (Object[] s : turnTable) {
                pos++;
                out.println(
                        "\t\t\t<tr>\n" +
                                "\t\t\t\t<td>" + pos + "</td>\n" +
                                "\t\t\t\t<td>" + s[0] + "</td>\n" +
                                "\t\t\t\t<td>" + s[1] + "</td>\n" +
                                "\t\t\t\t<td>" + s[2] + "</td>\n" +
                                "\t\t\t\t<td>" + s[3] + "</td>\n" +
                                "\t\t\t\t<td>" + s[4] + "</td>\n" +
                                "\t\t\t\t<td>" + s[5] + "</td>\n" +
                                "\t\t\t\t<td>" + s[6] + "</td>\n" +
                                "\t\t\t\t<td>" + s[7] + "</td>\t\n" +
                                "\t\t\t</tr>"
                );
            }
            out.println("\t\t</table>");
        %>
    </div>
</div>
<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-round-large" onclick="location.href='/'">На главную</button>
</div>
</body>
</html>