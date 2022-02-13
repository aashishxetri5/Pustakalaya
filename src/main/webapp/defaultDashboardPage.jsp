<%-- 
    Document   : profile
    Created on : Jan 14, 2022, 03:41:51 PM
    Author     : Aashish Katwal
--%>

<%@page import="edu.achs.entities.Books"%>
<%@page import="edu.achs.dashboardContents.PopularBooks"%>
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
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/Images/LogoAndBg/Favicon.png">
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
                <a href="<%=request.getContextPath()%>/dashboard/members/all">
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
                <a href="<%=request.getContextPath()%>/dashboard/books/requests">
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
                            <th>Edition</th>
                            <th>Genre</th>
                            <th>Language</th>
                            <th>Borrow By:</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<Books> popularBooks = new PopularBooks().getPopularBooks(6);
                            int count = 1;
                            for (Books book : popularBooks) {
                        %>
                        <tr>
                            <td><%=count++%></td>
                            <td><%=book.getBookTitle()%></td>
                            <td><%=book.getAuthor()%></td>
                            <td><%=book.getPublisher()%></td>
                            <td><%=book.getEdition()%></td>
                            <td><%=book.getGenre()%></td>
                            <td><%=book.getLanguage()%></td>
                            <td><%=book.getNumOfTimesBorrowed()%> People</td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</section>
<%
} else if (user.getUserType().equals("Student")) {
%>


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
                    <th>Edition</th>
                    <th>Genre</th>
                    <th>Language</th>
                    <th>Borrow By:</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Books> popularBooks = new PopularBooks().getPopularBooks(10);
                    int count = 1;
                    for (Books book : popularBooks) {
                %>
                <tr>
                    <td><%=count++%></td>
                    <td><%=book.getBookTitle()%></td>
                    <td><%=book.getAuthor()%></td>
                    <td><%=book.getPublisher()%></td>
                    <td><%=book.getEdition()%></td>
                    <td><%=book.getGenre()%></td>
                    <td><%=book.getLanguage()%></td>
                    <td><%=book.getNumOfTimesBorrowed()%> People</td>
                </tr>
                <%}%>
            </tbody>
        </table>
    </div>
</div>

<%
        }
    }
%>
</body>
</html>