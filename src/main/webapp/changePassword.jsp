<%-- 
    Document   : changePassword
    Created on : Feb 3, 2022, 12:56:09 PM
    Author     : Aashish Katwal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/Images/LogoAndBg/Favicon.png">
        <title>Change Password</title>
        <%@include file="Components/all_css_js.jsp" %>
    </head>
    <body>
        <%@include file="Components/PageDashboard.jsp" %> 


        <div class="container">
            <div class="major-form">
                <form action="#" method="POST">
                    <h3 class="form-title">Change Password</h3>
                    <div class="input-area">
                        <input type="password" placeholder="Old Password" name="oldPassword" required />
                        <input type="password" placeholder="Type New Password" name="newPassword" required />
                        <input type="password" placeholder="Retype Password" name="retypedPassword" required />
                    </div>
                    <input type="submit" class="submit-btn" value="Log In" /><br />
                </form>
            </div>
        </div> 

    </div>
</section>
<% }%>
</body>
</html>

