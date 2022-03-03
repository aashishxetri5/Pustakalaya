<%-- 
    Document   : notices
    Created on : Mar 3, 2022, 5:03:54 PM
    Author     : Aashish Katwal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Dashboard | Notices</title>
        
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/Images/LogoAndBg/Favicon.png">
        <%@include file="Components/all_css_js.jsp" %>
    </head>
    <body>
        <%@include file="Components/PageDashboard.jsp" %> 
        <%
            if (user.getUserType().equals("Librarian")) {
        %>

        <div class="top-btn-div" style="margin-bottom: 15px;">
            <button class="addNotice" title="Add New Librarian">
                <a href="<%=request.getContextPath()%>/notice/new" class="add-new-btn">New Notice</a>
            </button>
        </div>

        <% } %>
    </div>
</section>
<% }%>
</body>
</html>
