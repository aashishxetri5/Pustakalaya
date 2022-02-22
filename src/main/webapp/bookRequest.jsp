<%-- 
    Document   : bookRequest
    Created on : Feb 22, 2022, 10:25:19 AM
    Author     : Aashish Katwal
--%>

<%@page import="edu.achs.entities.Books"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Dashboard | Requested Books</title>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/Images/LogoAndBg/Favicon.png">
        <%@include file="Components/all_css_js.jsp" %>
    </head>
    <body>
        <%@include file="Components/PageDashboard.jsp" %> 

        <!--Class names are not changed--> 

        <div class="all-books">
            <div class="tabular">
                <table>
                    <thead>
                        <tr>
                            <th>S.N.</th>
                            <th>Title</th>
                            <th>Author</th>
                            <th>Genre</th>
                            <th align="center">Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<Books> reqBooks = (List<Books>) request.getSession().getAttribute("Requested Books");
                            if (reqBooks != null) {
                                int count = 1;
                                for (Books book : reqBooks) {

                        %>
                        <tr>
                            <td><%=count++%></td>
                            <td><%=book.getBookTitle()%></td>
                            <td><%=book.getAuthor()%></td>
                            <td><%=book.getGenre()%></td>
                            <td style="padding-left: 0; padding-right: 0;" align="center">
                                <%
                                    if (Boolean.parseBoolean(book.getStatus())) {
                                %>
                                <button style="background: none; font-weight: bold;" title="Available In Library" disabled>
                                    Available <i class="fas fa-check-circle"></i>
                                </button>
                                <%
                                } else {
                                %>
                                <a href="${pageContext.request.contextPath}/requestedBook/markAvailable?id=<%=book.getBookId()%>">
                                    <button class="borrow-btn last-col-btn" title="Mark as available">Available</button>
                                </a>
                                <%
                                    }
                                %>
                            </td>
                        </tr>
                        <% }
                            } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</section>
<% }%>
</body>
</html>
