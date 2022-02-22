<%@page import="edu.achs.entities.Users"%>
<nav class="navigation">
    <ul class="left-nav nav">
        <li>
            <a href="home">
                <img src="${pageContext.request.contextPath}/Images/LogoAndBg/Logo.png" alt="Logo" />
            </a>
        </li>
    </ul>

    <ul class="center-nav nav">
        <li class="center-nav-links"><a href="home">Home</a></li> <!-- /home -->
        <li class="center-nav-links"><a href="#">Books</a></li> <!-- /books/all -->
        <li class="center-nav-links"><a href="#">Blog</a></li> <!-- /blog-->
            <%
                if (session.getAttribute("currentUser") != null) {
            %>
        <li class="center-nav-links"><a href="feedback">Feedback</a></li> <!-- /feedback -->
            <%
                }
            %>
    </ul>

    <%
        Users currentUser = (Users) session.getAttribute("currentUser");
        if (currentUser == null) {
    %>            
    <ul class="right-nav nav">
        <li><a href="login">Login</a></li>
        <li><a href="signup">Signup</a></li>
    </ul>
    <%
    } else {
    %>

    <div class="dropdown-nav nav">
        <button class="dropbtn-nav">@<%=currentUser.getUsername()%></button><br>
        <div class="dropdown-content-nav">
            <a href="dashboard">Dashboard</a>
            <a href="logout">Logout</a>
        </div>
    </div>

    <%
        }
    %>
</nav>
<span><i class="hamburg-menu fas fa-bars"></i></span>