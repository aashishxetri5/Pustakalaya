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
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/Images/LogoAndBg/Favicon.png">
        <title>Dashboard | Readers</title>

        <%@include file="Components/all_css_js.jsp" %>

    </head>
    <body id="dashboard-body">
        <%@include file="Components/PageDashboard.jsp" %> 
        <%
            if (user.getUserType().equals("Librarian")) {
        %>

        <div class="booktop-div" style="margin-bottom: 15px;">

            <button class="addBook" title="Add New Librarian">
                <a href="<%=request.getContextPath()%>/librarian/new" class="add-newbook">Add Librarian</a>
            </button>

            <div class="dropdown-bm-tab">
                <button class="dropbtn-bm-tab">View</button>
                <div class="dropdown-content-bm-tab">
                    <a href="<%=request.getContextPath()%>/dashboard/members/all">Members</a>
                    <a href="<%=request.getContextPath()%>/dashboard/users/librarians">Librarians</a>
                </div>
            </div>

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
                            String userType = request.getRequestURI().contains("/members/all") ? "Student" : "Librarian";
                            allUsers = new UserDaoImpl().getAllMembers(userType);
                            int count = 1;
                            for (Users alluser : allUsers) {
                                if (user.getUserId() == alluser.getUserId()) {
                                    continue;
                                }

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
                                <a href="/user/ban?userId=<%=alluser.getUserId()%>" title="Remove user">
                                    <i class="fas fa-user-times"></i>
                                </a>
                                
                                <a href="/user/changerole?currentRole=<%=alluser.getUserType()%>&userId=<%=alluser.getUserId()%>">
                                    <% if (alluser.getUserType().equals("Student")) { %>
                                    <i title="Promote to Admin" class="fas fa-user-shield"></i>
                                    <% } else if (alluser.getUserType().equals("Librarian")) { %>
                                    <i title="Demote to User" class="fas fa-user"></i>
                                    <% } %>
                                </a>
                                <%}%>
                            </td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
            </div>
        </div>
        <% } else {%>
        <%@include file="Components/RestrictedPage.jsp" %>
        <% } %>
    </div>
</section>
<% }%>
</body>
</html>
