<%-- 
    Document   : readers
    Created on : Jan 16, 2022, 10:39:40 AM
    Author     : Aashish Katwal
--%>

<%@page import="edu.achs.daoImpl.UserDaoImpl"%>
<%@page import="java.util.ArrayList"%>
<%@page import="edu.achs.entities.Users"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="icon" href="${pageContext.request.contextPath}/Images/LogoAndBg/Favicon.png">
        <title>Dashboard | Readers</title>

        <%@include file="Components/all_css_js.jsp" %>

    </head>
    <body id="dashboard-body">

        <%@include file="Components/PageDashboard.jsp" %> 

        <div class="booktop-div" style="margin-bottom: 15px;">
            <%
                if (user.getUserType().equals("Librarian")) {
            %>

            <button class="addBook" title="Add New Book">
                <a href="<%=request.getContextPath()%>/librarian/new" class="add-newbook">Add Librarian</a>
            </button>

            <% } %>

        </div>

        <div class="allreaders">
            <div class="tabular">
                <table>
                    <thead>
                        <tr>
                            <th>S.N.</th>
                            <th>User ID</th>
                            <th>Library ID</th>
                            <th>Full Name</th>
                            <th>Sex</th>
                            <th>Email</th>
                            <th>Contact</th>
                            <th>Fine</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<Users> allUsers = new ArrayList<>();
                            allUsers = new UserDaoImpl().getAllUsers();
                            int count = 1;
                            for (Users alluser : allUsers) {

                        %>
                        <tr>
                            <td><%=count++%>.</td>
                            <td><%=alluser.getUserId()%></td>
                            <td><%=alluser.getLibraryId()%></td>
                            <td><%=alluser.getFirstName().concat(" ").concat(alluser.getLastName())%></td>
                            <td><%=alluser.getGender()%></td>
                            <td><%=alluser.getEmail()%></td>
                            <td><%=alluser.getContactNum()%></td>
                            <td>Rs. <%=alluser.getFine()%></td>
                            <td class="last-col" style="font-weight: bolder;">

                                <%
                                    if (user.getUserType().equals("Librarian")) {

                                %>
                                <a href="#" title="Ban this user"><i class="fas fa-ban"></i></a>
                                <a href="#" title="Suspend this user"><i class="fas fa-user-slash"></i></a>
                                <!--<a href="#" title="Promote to Admin"><i class="fas fa-user-shield"></i></a>-->
                                <%}%>
                            </td>
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
%>
</body>
</html>
