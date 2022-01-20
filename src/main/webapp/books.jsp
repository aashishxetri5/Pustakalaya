<%-- 
    Document   : books
    Created on : Jan 16, 2022, 4:38:26 PM
    Author     : Aashish Katwal
--%>
<%@page import="edu.achs.daoImpl.BookDaoImpl"%>
<%@page import="java.util.ArrayList"%>
<%@page import="edu.achs.entities.Books"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="icon" href="${pageContext.request.contextPath}/Images/LogoAndBg/Favicon.png">
        <title>Dashboard | All Books</title>

        <%@include file="Components/all_css_js.jsp" %>

    </head>
    <body id="dashboard-body">

        <%@include file="Components/PageDashboard.jsp" %> 

        <div class="booktop-div" style="margin-bottom: 15px;">
            <%
                if (user.getUserType().equals("Librarian")) {
            %>

            <button class="addBook" title="Add New Book">
                <a href="<%=request.getContextPath()%>/newbook" class="add-newbook">Add New Book</a>
            </button>

            <% }%>
            <div class="dropdown-book-tab">
                <button class="dropbtn-book-tab">View</button>
                <div class="dropdown-content-book-tab">
                    <a href="<%=request.getContextPath()%>/dashboard/books/all">All Books</a>
                    <a href="<%=request.getContextPath()%>/dashboard/books/borrowed">Borrowed Books</a>
                </div>
            </div>
        </div>

        <div class="all-books">
            <div class="tabular">
                <table>
                    <thead>
                        <tr>
                            <th>S.N.</th>
                            <th>Book Id</th>
                            <th>Book Name</th>
                            <th>Author</th>
                            <th>Publication</th>
                            <th>Genre</th>
                                <%
                                    if (request.getRequestURI().contains("/books/borrowed")) {
                                %>

                            <th>Issue Date</th>
                            <th>Return Date</th>

                            <%} else {%>
                            <th>Language</th>
                            <th>Price</th>
                                <% } %>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<Books> allBooks = new ArrayList<>();
                            int count;
                            if (request.getRequestURI().contains("/books/all")) {
                                allBooks = new BookDaoImpl().getAllBooks();
                                count = 1;
                                for (Books book : allBooks) {

                        %>
                        <tr>
                            <td><%=count++%></td>
                            <td><%=book.getBookId()%></td>
                            <td><%=book.getBookTitle()%></td>
                            <td><%=book.getAuthor()%></td>
                            <td><%=book.getPublisher()%></td>
                            <td><%=book.getGenre()%></td>
                            <td><%=book.getLanguage()%></td>
                            <td>Rs. <%=book.getPrice()%></td>
                            <td class="last-col">

                                <%
                                    if (user.getUserType().equals("Librarian")) {

                                %>
                                <a href="#" title="Edit"><i class="fas fa-pen"></i></a>
                                <a href="#" title="Delete"><i class="fas fa-trash"></i></a>
                                    <%} else if (user.getUserType().equals("Student")) { %>
                                <a href="#"><button class="borrow-btn">Borrow</button></i></a>
                                <% } %>

                            </td>
                        </tr>

                        <% }
                        } else if (request.getRequestURI().contains("/books/borrowed")) {
                            if (user.getUserType().equals("Librarian")) {
                                allBooks = new BookDaoImpl().getAllBorrowedBooks();
                            } else if (user.getUserType().equals("Student")) {
                                allBooks = new BookDaoImpl().getBorrowedBooks(user.getUserId());
                            }
                            count = 1;
                            for (Books book : allBooks) {
                        %>
                        <tr>
                            <td><%=count++%></td>
                            <td><%=book.getBookId()%></td>
                            <td><%=book.getBookTitle()%></td>
                            <td><%=book.getAuthor()%></td>
                            <td><%=book.getPublisher()%></td>
                            <td><%=book.getGenre()%></td>
                            <td><%=book.getIssue_date()%></td>
                            <td><%=book.getReturn_date()%></td>
                            <td>
                                <%
                                    if (book.getStatus().equals("returned")) {
                                %>
                                <button style="background: none; font-weight: bold;" disabled>Returned</button>
                                <%
                                } else if (book.getStatus().equals("pending")) {
                                    if (user.getUserType().equals("Librarian")) {
                                %>
                                <a href="#"><button class="borrow-btn last-col-btn" title="Request Return">Request</button></a>
                                <% } else if (user.getUserType().equals("Student")) { %>
                                <a href="#"><button class="borrow-btn last-col-btn" title="Return Book">Return</button></a>
                                <% }
                                    }%>
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
<%
    }
%>
</body>
</html>