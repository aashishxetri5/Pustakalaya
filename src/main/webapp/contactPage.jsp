<%-- 
    Document   : contactPage
    Created on : Jan 13, 2022, 12:12:26 PM
    Author     : Aashish Katwal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Contact Page</title>
        <link rel="icon" href="${pageContext.request.contextPath}/Images/LogoAndBg/Favicon.png">
        <%@include file="Components/all_css_js.jsp" %>
    </head>
    <body class="form-page">
        <div class="container">
            <a href="home" style="display: inline-block">
                <img src="${pageContext.request.contextPath}/Images/LogoAndBg/Logo.png" alt="Logo" title="Pustakalaya" />
            </a>
            <div class="major-form">
                <form action="ContactSubmit" method="POST">
                    <h3 class="form-title">Lets Talk</h3>
                    <div class="input-area">
                        <span class="input-title">Fullname</span>
                        <input type="text" name="fullname" placeholder="Full name" class="cf-inp" />
                        <span class="input-title">Email</span>
                        <input type="email" name="c-email" placeholder="Email" class="cf-inp" required />
                        <span class="input-title">Message</span>
                        <textarea name="c-message" cols="30" rows="10" placeholder="Message" class="cf-inp" required></textarea>
                        <input type="submit" name="ContactFormSubmission" value="Send" class="submit-btn"/>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
