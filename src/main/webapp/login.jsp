<%-- 
    Document   : login
    Created on : Jan 10, 2022, 12:48:13 PM
    Author     : Aashish Katwal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Login</title>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/Images/LogoAndBg/Favicon.png">
        <%@include file="Components/all_css_js.jsp" %>
    </head>
    <body class="form-page">

        <%
            if (request.getSession().getAttribute("currentUser") == null) {
        %>

        <section class="login-form-section">
            <div class="container">
                <a href="home" style="display: inline-block">
                    <img src="${pageContext.request.contextPath}/Images/LogoAndBg/Logo.png" alt="Logo" title="Pustakalaya" />
                </a>
                <div class="major-form">
                    <form action="validateLogin" method="POST">
                        <h3 class="form-title">Login</h3>
                        <div class="input-area">
                            <input
                                type="text"
                                placeholder="Username"
                                name="username"
                                required
                                autofocus
                                autocomplete="off"
                                />
                            <input type="password" placeholder="Password" name="password" required />
                        </div>
                        <input type="submit" class="submit-btn" value="Log In" /><br />
                        <a href="recoverPassword" class="bottom-link" id="forgot-pwd">Forgot Password?</a>
                        <hr />
                        <br />
                    </form>
                    <a href="signup" id="create-nacc">
                        <button>Create New Account</button>
                    </a>
                </div>
            </div> 
        </section>
        <%        
            } else {
                response.sendRedirect(request.getContextPath() + "/home");
            }
            

        %>
    </body>
</html>
