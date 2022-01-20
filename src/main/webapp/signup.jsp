<%-- 
    Document   : signup
    Created on : Jan 10, 2022, 4:21:30 PM
    Author     : Aashish Katwal
--%>

<%@page import="edu.achs.daoImpl.UserDaoImpl"%>
<%@page import="edu.achs.entities.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign-up</title>
        <link rel="icon" href="${pageContext.request.contextPath}/Images/LogoAndBg/Favicon.png">
        <%@include file="Components/all_css_js.jsp" %>
    </head>
    <body class="form-page">

        <%
            if (!request.getRequestURI().contains("/updateInfo")) {
                System.out.println("FOUND THIS TO BE NULL");
                if (session.getAttribute("currentUser") != null) {
                    response.sendRedirect(request.getContextPath() + "/home");
                } else {
        %>

        <section>
            <div class="container">
                <div class="major-form">
                    <form action="validateRegistration" method="POST">
                        <h3 class="form-title">Welcome to the family</h3>
                        <div class="input-area">
                            <input type="text" name="fname" placeholder="First name" required/>
                            <input type="text" name="lname" placeholder="Last name" required/>
                            <input type="email" name="email" placeholder="Email" required/>
                            <input type="text" name="address" placeholder="Address" required/>
                            <input type="text" name="phoneNum" placeholder="Phone Number" required/>
                            <select name="gender" title="Choose Gender" required>
                                <option value="Select" hidden selected disabled>Choose Gender</option>
                                <option title="Male" value="Male">Male</option>
                                <option title="Female" value="Female">Female</option>
                                <option title="Other" value="Other">Other</option>
                            </select>
                            <input type="text" name="username" placeholder="Username" autocomplete="off" required />
                            <input type="password" name="password" placeholder="Password (minimum 8 digits recommended)" required />
                        </div>
                        <input type="submit" value="Register" class="submit-btn"/><br />
                        <a href="login" class="bottom-link" id="to-login">Have an account? Login</a>
                    </form>
                </div>
            </div>
        </section>

        <%
            }
        } else if (request.getRequestURI().contains("/updateInfo")) {
            if (session.getAttribute("currentUser") == null) {
                response.sendRedirect(request.getContextPath() + "/home");
            } else {
                Users user = (Users) session.getAttribute("currentUser");
                Users cUser = new UserDaoImpl().getCurrentUserDetail(user.getUserId());
                request.setAttribute("currentUserId", cUser.getUserId());
        %>

        <section>
            <div class="container">
                <div class="major-form">
                    <form action="updateInformation" method="POST">
                        <h3 class="form-title">Update your info</h3>
                        <div class="input-area">
                            <input type="text" name="userId" hidden value="<%=cUser.getUserId()%>" required/>
                            <input type="text" name="fname" placeholder="First name" value="<%=cUser.getFirstName()%>" required/>
                            <input type="text" name="lname" placeholder="Last name" value="<%=cUser.getLastName()%>" required/>
                            <input type="email" name="email" placeholder="Email" value="<%=cUser.getEmail()%>" required/>
                            <input type="text" name="address" placeholder="Address" value="<%=cUser.getAddress()%>" required/>
                            <input type="text" name="phoneNum" placeholder="Phone Number" value="<%=cUser.getContactNum()%>" required/>
                            <select name="gender" title="Choose Gender" required>
                                <option value="Select" hidden selected disabled>Choose Gender</option>
                                <option title="Male" value="Male">Male</option>
                                <option title="Female" value="Female">Female</option>
                                <option title="Other" value="Other">Other</option>
                            </select>
                        </div>
                        <input type="submit" value="Update" class="submit-btn"/><br />
                    </form>
                </div>
            </div>
        </section>
        <% }
            }%>
    </body>
</html>
