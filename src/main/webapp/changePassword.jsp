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
        <title>Dashboard | Change Password</title>
        <%@include file="./Components/all_css_js.jsp" %>
    </head>
    <body id="dashboard-body">
        <%@include file="./Components/PageDashboard.jsp" %> 


        <div class="container">
            <div class="major-form">
                <form id="changePass" action="passwordChange" method="POST">
                    <h3 class="form-title">Change Password</h3>
                    <div class="input-area">
                        <input type="password" placeholder="Old Password" name="oldPassword" required />
                        <input type="password" placeholder="Type New Password" name="newPassword" id="newP" required />
                        <input type="password" placeholder="Retype Password" name="retypedPassword" id="re-NewPass" required />
                        <p id="errmsg" style="color: #BA2D0B; font-size: .9rem; font-weight: bold;"></p>
                    </div>
                    <input type="submit" class="submit-btn" value="Change Password" /><br />
                </form>
            </div>
        </div> 

    </div>
</section>
<% }%>

<script src="${pageContext.request.contextPath}/JS/checkPassword.js"></script>

</body>
</html>

