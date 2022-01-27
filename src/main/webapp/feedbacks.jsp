<%-- 
    Document   : feedbacks
    Created on : Jan 24, 2022, 9:05:57 PM
    Author     : Aashish Katwal
--%>

<%@page import="edu.achs.daoImpl.OtherServices"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="edu.achs.entities.FeedbacksAndContacts"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Dashboard | Feedbacks</title>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/Images/LogoAndBg/Favicon.png">

        <%@include file="Components/all_css_js.jsp" %>

    </head>
    <body id="dashboard-body">
        <%@include file="Components/PageDashboard.jsp" %> 
        <%
            if (user.getUserType().equals("Librarian")) {
                List<FeedbacksAndContacts> feedbacks = new ArrayList<>();
                feedbacks = new OtherServices().getAllFeedbacks();
        %>
        <div class="feedback-wrapper">
            <%
                for (FeedbacksAndContacts feedback : feedbacks) {
            %>
            <div class="feedbacks">
                <button class="accordion-feedback">From: @<%=feedback.getUsername()%></button>
                <div class="details-feedback">
                    <p class="email-fd"><strong> Email: <%=feedback.getEmail()%> </strong></p>
                    <hr />
                    <p class="msg-content"><%=feedback.getMessage() %></p>
                </div>
            </div>
            <div class="feedbacks">
                <button class="accordion-feedback">From: @<%=feedback.getUsername()%></button>
                <div class="details-feedback">
                    <p class="email-fd"><strong> Email: <%=feedback.getEmail()%> </strong></p>
                    <hr />
                    <p class="msg-content"><%=feedback.getMessage() %></p>
                </div>
            </div>
            <div class="feedbacks">
                <button class="accordion-feedback">From: @<%=feedback.getUsername()%></button>
                <div class="details-feedback">
                    <p class="email-fd"><strong> Email: <%=feedback.getEmail()%> </strong></p>
                    <hr />
                    <p class="msg-content"><%=feedback.getMessage() %></p>
                </div>
            </div>
            <div class="feedbacks">
                <button class="accordion-feedback">From: @<%=feedback.getUsername()%></button>
                <div class="details-feedback">
                    <p class="email-fd"><strong> Email: <%=feedback.getEmail()%> </strong></p>
                    <hr />
                    <p class="msg-content"><%=feedback.getMessage() %></p>
                </div>
            </div>
            <div class="feedbacks">
                <button class="accordion-feedback">From: @<%=feedback.getUsername()%></button>
                <div class="details-feedback">
                    <p class="email-fd"><strong> Email: <%=feedback.getEmail()%> </strong></p>
                    <hr />
                    <p class="msg-content"><%=feedback.getMessage() %></p>
                </div>
            </div>
            <div class="feedbacks">
                <button class="accordion-feedback">From: @<%=feedback.getUsername()%></button>
                <div class="details-feedback">
                    <p class="email-fd"><strong> Email: <%=feedback.getEmail()%> </strong></p>
                    <hr />
                    <p class="msg-content"><%=feedback.getMessage() %></p>
                </div>
            </div>
            <div class="feedbacks">
                <button class="accordion-feedback">From: @<%=feedback.getUsername()%></button>
                <div class="details-feedback">
                    <p class="email-fd"><strong> Email: <%=feedback.getEmail()%> </strong></p>
                    <hr />
                    <p class="msg-content"><%=feedback.getMessage() %></p>
                </div>
            </div>
            <div class="feedbacks">
                <button class="accordion-feedback">From: @<%=feedback.getUsername()%></button>
                <div class="details-feedback">
                    <p class="email-fd"><strong> Email: <%=feedback.getEmail()%> </strong></p>
                    <hr />
                    <p class="msg-content"><%=feedback.getMessage() %></p>
                </div>
            </div>
            <div class="feedbacks">
                <button class="accordion-feedback">From: @<%=feedback.getUsername()%></button>
                <div class="details-feedback">
                    <p class="email-fd"><strong> Email: <%=feedback.getEmail()%> </strong></p>
                    <hr />
                    <p class="msg-content"><%=feedback.getMessage() %></p>
                </div>
            </div>
            <div class="feedbacks">
                <button class="accordion-feedback">From: @<%=feedback.getUsername()%></button>
                <div class="details-feedback">
                    <p class="email-fd"><strong> Email: <%=feedback.getEmail()%> </strong></p>
                    <hr />
                    <p class="msg-content"><%=feedback.getMessage() %></p>
                </div>
            </div>
            <div class="feedbacks">
                <button class="accordion-feedback">From: @<%=feedback.getUsername()%></button>
                <div class="details-feedback">
                    <p class="email-fd"><strong> Email: <%=feedback.getEmail()%> </strong></p>
                    <hr />
                    <p class="msg-content"><%=feedback.getMessage() %></p>
                </div>
            </div>
            <div class="feedbacks">
                <button class="accordion-feedback">From: @<%=feedback.getUsername()%></button>
                <div class="details-feedback">
                    <p class="email-fd"><strong> Email: <%=feedback.getEmail()%> </strong></p>
                    <hr />
                    <p class="msg-content"><%=feedback.getMessage() %></p>
                </div>
            </div>
            <div class="feedbacks">
                <button class="accordion-feedback">From: @<%=feedback.getUsername()%></button>
                <div class="details-feedback">
                    <p class="email-fd"><strong> Email: <%=feedback.getEmail()%> </strong></p>
                    <hr />
                    <p class="msg-content"><%=feedback.getMessage() %></p>
                </div>
            </div>
            <%
                }
            %>
        </div>
        <% } else {%>
        <%@include file="Components/RestrictedPage.jsp" %>
        <% } %>
    </div>
</section>
<% }%>
</body>
</html>
