<%-- 
    Document   : feedbackPage
    Created on : Jan 13, 2022, 12:53:07 PM
    Author     : Aashish Katwal
--%>

<%@page import="edu.achs.entities.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Feedback Page</title>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/Images/LogoAndBg/Favicon.png">
        <%@include file="Components/all_css_js.jsp" %>
    </head>
    <body class="form-page">
        <%@include file="Components/PreventUnloggedUsers.jsp" %>
        <section class="major-form-section">
            <div class="container">
                <a href="home" style="display: inline-block">
                    <img src="${pageContext.request.contextPath}/Images/LogoAndBg/Logo.png" alt="Logo" title="Pustakalaya" />
                </a>
                <div class="major-form">
                    <form action="FeedbackSubmit" method="POST">
                        <h3 class="form-title">Feedback Form</h3>
                        <div class="input-area">
                            <%                            Users cUser = new Users();
                                if (session.getAttribute("currentUser") != null) {
                                    cUser = (Users) session.getAttribute("currentUser");
                                }
                            %>
                            <span class="input-title">Email</span>
                            <input type="email" name="f-email" placeholder="Email" class="cf-inp" required />
                            <input type="text" name="username" value="<%=cUser.getUsername()%>" hidden/>
                            <input type="text" name="userID" value="<%=cUser.getUserId()%>" hidden/>

                            <span class="input-title">Message</span>
                            <textarea name="f-message" cols="30" rows="10" placeholder="Have feedback? We'd love to hear..." class="cf-inp" required></textarea>
                            <p style="font-size: 13px;">
                                When you submit this feedback form, your account information such as email, username will be collected.
                                We use these information to keep track of who submitted the feedback.
                                Your feedback will be reviewed by our team and they'll get back to you as soon as possible.
                            </p>
                            <input type="submit" name="FeedbackFormSubmission" value="Send" class="submit-btn"/>
                        </div>
                    </form>
                </div>
            </div>
        </section>
        <%}%>
    </body>
</html>
