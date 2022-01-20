<%-- 
    Document   : addNewBook
    Created on : Jan 16, 2022, 8:16:26 PM
    Author     : Aashish Katwal
--%>

<%@page import="edu.achs.entities.Books"%>
<%@page import="edu.achs.daoImpl.BookDaoImpl"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Add New Book</title>
        <link rel="icon" href="${pageContext.request.contextPath}/Images/LogoAndBg/Favicon.png">
        <%@include file="Components/all_css_js.jsp" %>
    </head>
    <body class="form-page">
        <%@include file="Components/PreventUnloggedUsers.jsp" %>
        <section>
            <div class="container">
                <a href="home" style="display: inline-block">
                    <img src="${pageContext.request.contextPath}/Images/LogoAndBg/Logo.png" alt="Logo" title="Pustakalaya" />
                </a>
                <div class="major-form">
                    <%                        if (!request.getRequestURI().contains("/book/updateBookInfo")) {
                    %>
                    <form action="addBook" method="POST">
                        <h3 class="form-title">Book Registration</h3>
                        <div class="input-area">
                            <input type="text" name="bookId" placeholder="Book Id" value="<%%>" required />
                            <input type="text" name="bookname" placeholder="Title" required />
                            <input type="text" name="author" class="short-field" placeholder="Author" required />
                            <input type="text" name="publisher" class="short-field" placeholder="Publisher" required />
                            <input type="text" name="edition" class="short-field" placeholder="Edition" required />
                            <input type="text" name="numOfPages" class="short-field" placeholder="Number of pages" required />
                            <input type="text" name="isbn" placeholder="ISBN" required />
                            <select name="genre" class="short-field" title="Genre" required>
                                <option value="Select" disabled selected hidden>
                                    Select a Genre
                                </option>
                                <option value="Comics">Comic</option>
                                <option value="Horror">Horror</option>
                                <option value="Adventure">Adventure</option>
                                <option value="Adventure">Fantasy</option>
                                <option value="Adventure">Drama</option>
                            </select>
                            <input type="text" name="stock" class="short-field" placeholder="Stock" required />
                            <input type="text" name="price" class="short-field" placeholder="Price" required />
                            <input type="language" name="language" class="short-field" placeholder="Language" required />
                        </div>
                        <input type="submit" class="submit-btn" value="Add Book" />
                    </form>
                    <%
                    } else if (request.getRequestURI().contains("/book/updateBookInfo")) {
                        Books book = new BookDaoImpl().getDetailsOfBookToBeUpdated(request.getParameter("bookId"), request.getParameter("ISBN"));
                    %>

                    <form action="updateBook" method="POST">
                        <h3 class="form-title">Update Book</h3>
                        <div class="input-area">
                            <input type="text" name="bookId" placeholder="Book Id" value="<%=book.getBookId()%>" hidden required />
                            <input type="text" name="bookname" placeholder="Title" value="<%=book.getBookTitle()%>" required />
                            <input type="text" name="author" class="short-field" placeholder="Author" value="<%=book.getAuthor()%>" required />
                            <input type="text" name="publisher" class="short-field" placeholder="Publisher" value="<%=book.getPublisher()%>" required />
                            <input type="text" name="edition" class="short-field" placeholder="Edition" value="<%=book.getEdition()%>" required />
                            <input type="text" name="numOfPages" class="short-field" placeholder="Number of pages" value="<%=book.getNumOfPages()%>" required />
                            <input type="text" name="isbn" placeholder="ISBN" value="<%=book.getISBN()%>" required />
                            <select name="genre" class="short-field" title="Genre" required>
                                <option value="Select" disabled selected hidden>
                                    Select a Genre
                                </option>
                                <option value="Comics">Comic</option>
                                <option value="Horror">Horror</option>
                                <option value="Adventure">Adventure</option>
                                <option value="Adventure">Fantasy</option>
                                <option value="Adventure">Drama</option>
                            </select>
                            <input type="text" name="stock" class="short-field" placeholder="Stock" value="<%=book.getStock()%>" required />
                            <input type="text" name="price" class="short-field" placeholder="Price" value="<%=book.getPrice()%>" required />
                            <input type="language" name="language" class="short-field" placeholder="Language" value="<%=book.getLanguage()%>" required />
                        </div>
                        <input type="submit" class="submit-btn" value="Update Book" />
                    </form>

                    <% } %>
                </div>
            </div>
        </section>
        <% }%>
    </body>
</html>
