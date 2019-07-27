<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: setenish
  Date: 29.06.2019
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.ifmo.ctddev.tenischev.news.publisher.Constants" %>
<html>
<head>
    <title>Hello Page</title>
    <style type="text/css">
        * {
            box-sizing: border-box;
        }
        textarea {
            height:400px;
        }
        input[type=text], textarea {
            width: 80%;
            padding: 12px 20px;
            margin: 8px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
            resize: none;
        }
        input[type=button], input[type=submit], input[type=reset] {
            background-color: #4caf50;
            border: none;
            color: white;
            padding: 16px 32px;
            text-decoration: none;
            margin: 4px 2px;
            cursor: pointer;
            width: 30%;
        }
        input[type=submit]:hover {
            background-color: #45a049;
        }
        .response-info {
            font-weight: bold;
            color: black;
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div style="text-align: center;">
    <h1>Publish News</h1>
    <form action="news-publisher/news/add" method="post">
        <div><input maxlength="<%=Constants.TITLE_MAX_LENGTH%>" required spellcheck="true" type="text" name="title" placeholder="Title"></div>
        <div><textarea maxlength="<%=Constants.TEXT_MAX_LENGTH%>" required spellcheck="true" id="text" name="text" placeholder="Write text of news here..." style="height:200px"></textarea></div>
        <div><input maxlength="<%=Constants.PUBLISHER_MAX_LENGTH%>" type="text" name="publisher" placeholder="Author"></div>
        <div><input type="submit" value="Publish"></div>
    </form>
    <c:if test="${!empty pageContext.request.getParameter('response-status')}">
        <div class="response-info"><%= request.getParameter("response-status") %></div>
    </c:if>
</div>
</body>
</html>
