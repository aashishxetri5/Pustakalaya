<%-- 
    Document   : genres
    Created on : Feb 18, 2022, 8:24:17 AM
    Author     : Aashish Katwal
--%>

<%@page import="edu.achs.daoImpl.BookDaoImpl"%>
<%@page import="edu.achs.dao.BookDao"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Set"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/Images/LogoAndBg/Favicon.png">
        <title>Dashboard | Genres</title>

        <%@include file="./Components/all_css_js.jsp" %>
    </head>
    <body id="dashboard-body">
        <%@include file="./Components/PageDashboard.jsp" %> 

        <div class="container genre-container">
            <div class="genre-left">
                <form action="newGenre">
                    <h3 class="form-title">Add New Genre</h3>
                    <div class="input-area">
                        <input type="text" name="genreTitle" title="New Genre" placeholder="Type a Genre" required autocomplete />
                    </div>
                    <input type="submit" value="Add" name="addNewGenre" class="submit-btn"/>
                </form>
            </div>

            <div class="genre-right">
                <div class="tabular">
                    <h3 class="form-title">Available Genres</h3>
                    <table>
                        <thead>
                            <tr>
                                <th>S.N.</th>
                                <th>Genre</th>
                                <th align="center">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                BookDao bd = new BookDaoImpl();
                                Map<Integer, String> genres = bd.getAllGenres();

                                if (genres != null) {
                                    Set setOfGenre = genres.entrySet();
                                    Iterator allGenre = setOfGenre.iterator();
                                    int count = 1;
                                    while (allGenre.hasNext()) {
                                        Map.Entry<?, ?> genre = (Map.Entry<?, ?>) allGenre.next();
                            %>
                            <tr>
                                <td><%=count++%></td>
                                <td><%=genre.getValue()%></td>
                                <td style="text-align: center;">
                                    <a href="/genre/delete?genreId=<%=genre.getKey()%>" title="Remove Genre" style="color: #f00;">
                                        <i class="fas fa-trash"></i>
                                    </a>
                                </td>
                            </tr>
                            <% }
                                }
                                genres.clear();
                            %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

    </div>
</section>
<% }%>
</body>
</html>
