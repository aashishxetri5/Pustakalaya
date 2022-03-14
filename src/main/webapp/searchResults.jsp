<%-- 
    Document   : searchResults
    Created on : Feb 18, 2022, 1:13:30 PM
    Author     : Aashish Katwal
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="edu.achs.entities.Books"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
            String query = "";
            if (request.getSession().getAttribute("searchQ") != null) {
                query = (request.getSession().getAttribute("searchQ")).toString();
            }
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/Images/LogoAndBg/Favicon.png">
        <title>Dashboard | Searched Results for <%=query %></title>

        <%@include file="Components/all_css_js.jsp" %>

    </head>
    <body>

        <%@include file="Components/PageDashboard.jsp" %> 

        <div class="booktop-div" style="margin-bottom: 15px;">
            <p>Search results for "<%=query %>"</p>
        </div>

        <div class="all-books">
            <div class="tabular">
                <table>
                    <% if (request.getSession().getAttribute("searchResult") != null) { %>
                    <thead>
                        <tr>
                            <th>S.N.</th>
                            <th>Book Name</th>
                            <th>Author</th>
                            <th>Genre</th>
                                <%if (request.getRequestURI().contains("/")) { %>
                            <th>Language</th>
                            <th>Price</th>

                            <% } %>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<Books> searchedBookResults = new ArrayList<>();
                            int count;
                            searchedBookResults = (List<Books>) request.getSession().getAttribute("searchResult");
                            count = 1;
                            for (Books book : searchedBookResults) {

                        %>
                        <tr>
                            <td><%=count++%></td>
                            <td><%=book.getBookTitle()%></td>
                            <td><%=book.getAuthor()%></td>
                            <td><%=book.getGenre()%></td>
                            <td><%=book.getLanguage()%></td>
                            <td>Rs. <%=book.getPrice()%></td>
                            <td class="last-col">

                                <%
                                    if (user.getUserType().equals("Librarian")) {

                                %>
                                <a href="${pageContext.request.contextPath}/book/updateBookInfo?bookId=<%=book.getBookId()%>&ISBN=<%=book.getISBN()%>" title="Edit Book Info">
                                    <i class="fas fa-pen"></i>
                                </a>
                                <a href="${pageContext.request.contextPath}/book/deleteBook?bookId=<%=book.getBookId()%>" title="Delete Book">
                                    <i class="fas fa-trash"></i>
                                </a>
                                <%} else if (user.getUserType().equals("Student")) {%>
                                <a href="${pageContext.request.contextPath}/book/borrow?bookId=<%=book.getBookId()%>&borrowerId=<%=user.getUserId()%>" title="Borrow This Book">
                                    <button class="borrow-btn">Borrow</button>
                                </a>
                                <% } %>
                            </td>
                        </tr>
                        <%
                                }
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>

    </div>
</section>
<% }%>
</body>
</html>
