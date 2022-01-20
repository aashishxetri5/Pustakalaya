<%-- 
    Document   : index
    Created on : Jan 8, 2022, 4:51:05 PM
    Author     : Aashish Katwal
--%>
<%@page import="edu.achs.utility.GenerateIDs"%>
<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="pageNotFound.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Home</title>
        <link rel="icon" href="${pageContext.request.contextPath}/Images/LogoAndBg/Favicon.png">
        <%@include file="Components/all_css_js.jsp" %>
    </head>
    <body>
        <header>
            <%@include file="Components/Navbar.jsp" %>
        </header>

        <section class="main">
            <%@include file="Components/HomeMiddleSection.jsp" %>
        </section>

        <footer>
            <%@include file="Components/Footer.jsp" %>
        </footer>

    </body>
</html>
