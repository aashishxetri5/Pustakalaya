<%-- 
    Document   : notices
    Created on : Mar 3, 2022, 5:03:54 PM
    Author     : Aashish Katwal
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="edu.achs.entities.FeedbacksAndContacts"%>
<%@page import="edu.achs.daoImpl.OtherServices"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/Images/LogoAndBg/Favicon.png">
        <title>Dashboard | Notices</title>
        
        <%@include file="Components/all_css_js.jsp" %>
        
    </head>
    <body>
        
        <%@include file="Components/PageDashboard.jsp" %> 
        
        <%
            if (user.getUserType().equals("Librarian")) {
        %>

        <div class="top-btn-div" style="margin-bottom: 15px;">
            <button class="addNotice" title="Add New Librarian">
                <a href="${pageContext.request.contextPath}/notice/new" class="add-new-btn">New Notice</a>
            </button>
        </div>

        <% }
            List<FeedbacksAndContacts> notices = new ArrayList<>();
            notices = new OtherServices().getAllNotices();
            if (notices != null) {
        %>
        <div class="nf-wrapper">
            <%
                for (FeedbacksAndContacts notice : notices) {
            %>
            <div class="nf">
                <div class="accordion-nf">
                    <button><strong>Title:</strong> <%=notice.getTitle()%></button>
                    <% if (user.getUserType().equals("Librarian")) { %>
                    <div class="action">
                        <a href="${pageContext.request.contextPath}/notice/delete?noticeId=<%=notice.getId()%>" title="Delete Notice">
                            <i class="fas fa-trash" style="color: #BA2D0B;"></i>
                        </a>
                    </div>
                    <% } %>
                </div>
                <div class="details-nf">
                    <p class="pub-date"> Published On: <%=notice.getDate()%> </p>
                    <hr />
                    <p class="msg-content"><%=notice.getMessage()%></p>
                </div>
            </div>
            <%
                    }
                }
            %>
        </div>
    </div>
</section>
<% } %>
</body>
</html>
