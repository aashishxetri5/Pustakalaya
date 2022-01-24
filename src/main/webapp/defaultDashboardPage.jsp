<%-- 
    Document   : profile
    Created on : Jan 14, 2022, 03:41:51 PM
    Author     : Aashish Katwal
--%>

<%@page import="edu.achs.dashboardContents.CountRecords"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="edu.achs.daoImpl.BookDaoImpl"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="icon" href="${pageContext.request.contextPath}/Images/LogoAndBg/Favicon.png">
        <title>Dashboard</title>

        <%@include file="Components/all_css_js.jsp" %>

    </head>
    <body id="dashboard-body">

        <%@include file="Components/PageDashboard.jsp" %> 
        <%-- This allows only the librarian to view the below content --%>
        <%
            if (user.getUserType().equals("Librarian")) {
        %>
        <div class="blocks">
            <div class="block-boxes">
                <a href="<%=request.getContextPath()%>/dashboard/books/all">
                    <p><strong><%=new CountRecords().getTotalBooks()%></strong></p>
                    <p>Books</p>
                </a>
            </div>
            <div class="block-boxes">
                <a href="<%=request.getContextPath()%>/dashboard/members">
                    <p><strong><%=new CountRecords().getTotalMembers()%></strong></p>
                    <p>Members</p>
                </a>
            </div>
            <div class="block-boxes">
                <a href="<%=request.getContextPath()%>/dashboard/books/borrowed">
                    <p><strong><%=new CountRecords().getTotalBorrowedBooks()%></strong></p>
                    <p>Borrowed</p>
                </a>
            </div>
            <div class="block-boxes">
                <a href="<%=request.getContextPath()%>/dashboard/books/request">
                    <p><strong><%=new CountRecords().getTotalBookRequests()%></strong></p>
                    <p>Requests</p>
                </a>
            </div>
            <div class="block-boxes">
                <a href="<%=request.getContextPath()%>/dashboard/feedbacks">
                    <p><strong><%=new CountRecords().getTotalFeedbacks()%></strong></p>
                    <p>Feedbacks</p>
                </a>
            </div>
        </div>
        <div class="recent">
            <div class="tabular">
                <!--<h3>Popular Picks</h3>-->
                <table>
                    <thead>
                        <tr>
                            <th>S.N.</th>
                            <th>Book Name</th>
                            <th>Author</th>
                            <th>Publication</th>
                            <th>Language</th>
                            <th>Genre</th>
                            <th>Price</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (int i = 0; i < 6; i++) {

                        %>
                        <tr>
                            <td><%=i + 1%></td>
                            <td>Rich Dad, Poor DadRich Dad, Poor Dad</td>
                            <td>Q</td>
                            <td>ABC</td>
                            <td>English</td>
                            <td>Fantasy</td>
                            <td>Rs. 804</td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</section>
<%
        }
    }
%>
</body>
</html>