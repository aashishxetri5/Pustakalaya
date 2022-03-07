<%-- 
    Document   : publishNoticeTemplate
    Created on : Mar 3, 2022, 5:18:51 PM
    Author     : Aashish Katwal
--%>

<%@page import="edu.achs.entities.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Contact Page</title>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/Images/LogoAndBg/Favicon.png">
        <%@include file="Components/all_css_js.jsp" %>
    </head>
    <body class="form-page">
        <%
            if (request.getSession().getAttribute("currentUser") != null) {
                Users user = (Users) request.getSession().getAttribute("currentUser");
                if (user.getUserType().equals("Librarian")) {

        %>
        <section class="major-form-section">
            <div class="container">
                <a href="/home" style="display: inline-block">
                    <img src="${pageContext.request.contextPath}/Images/LogoAndBg/Logo.png" alt="Logo" title="Pustakalaya" />
                </a>
                <div class="major-form">
                    <form action="newNotice" method="POST">
                        <h3 class="form-title">New Notice</h3>
                        <div class="input-area">
                            <span class="input-title">Title</span>
                            <input type="text" name="notice-title" placeholder="Title of Notice" title="Title of Notice" class="cf-inp" autocomplete="off" required />
<!--                            <span class="input-title">For whom</span>
                            <select class="cf-inp" title="Whom is this notice for?" name="noticeFor" required>
                                <option title="Select" value="select" disabled selected hidden>Select whom is this notice for</option>
                                <option title="Memebers"> Members </option>
                                <option title="For All"> All </option>
                            </select>-->
                            <span class="input-title">Notice</span>
                            <textarea title="Message" name="notice-message" cols="30" rows="10" placeholder="Message in notice" class="cf-inp" required></textarea>
                            <input type="submit" name="ContactFormSubmission" value="Send" class="submit-btn"/>
                        </div>
                    </form>
                </div>
            </div>
        </section>
        <%                } else {
                    response.sendRedirect(request.getContextPath() + "/home");
                    request.getSession().setAttribute("errorMsg", "Access blocked!! You're not authorized");
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/home");
                request.getSession().setAttribute("errorMsg", "Access blocked!! You're not authorized");
            }
        %>
    </body>
</html>

