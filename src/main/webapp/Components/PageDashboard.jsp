<%-- 
    Document   : dashboard
    Created on : Sep 8, 2020, 2:05:24 PM
    Author     : Aashish Katwal
--%>
<%@page import="edu.achs.daoImpl.UserDaoImpl"%>
<%@page import="edu.achs.entities.Users"%>
<%@include file="PreventUnloggedUsers.jsp" %>
<header class="dashboard-h">
    <div class="container-db-top">
        <div class="left-nav-dashboard">
            <div id="title">
                <i class="hamburg-menu-dash fas fa-bars"></i>
                <a href="${pageContext.request.contextPath}/home">
                    <img src="${pageContext.request.contextPath}/Images/LogoAndBg/Logo.png" alt="Logo" />
                </a>
            </div>
        </div>

        <div class="middle-nav-dashboard">
            <div class="search">
                <form action="dashboard/searchBook">
                    <input
                        type="text"
                        class="searchbox"
                        name="searchKeyword"
                        placeholder="Type your keyword"
                        autocomplete="off"
                        required
                        />
                    <button type="submit"> <i class="fas fa-search"></i> </button>
                </form>
            </div>
        </div>

        <% Users user = (Users) session.getAttribute("currentUser");%>
        <div class="right-nav-dashboard">
            <div class="profile-icon">
                <a href="<%=request.getContextPath()%>/dashboard/profile">
                    <b><%=user.getUsername()%></b>
                </a>
                <div class="profile-container-topbar">
                    <%
                        if (new UserDaoImpl().getUserGender(user.getUserId()).equals("M")) {
                    %>
                    <img src="${pageContext.request.contextPath}/Images/ProfilePictures/Male_Default_pp.png" alt="profile" class="profile-img-dshb"/>
                    <%
                    } else if (new UserDaoImpl().getUserGender(user.getUserId()).equals("F")) {
                    %>
                    <img src="${pageContext.request.contextPath}/Images/ProfilePictures/Female_Default_pp.png" alt="profile" class="profile-img-dshb"/>
                    <%
                    } else if (new UserDaoImpl().getUserGender(user.getUserId()).equals("O")) {
                    %>
                    <img src="${pageContext.request.contextPath}/Images/ProfilePictures/Female_Default_pp.png" alt="profile" class="profile-img-dshb"/>
                    <%
                        }
                    %>
                </div>
            </div>
        </div>
    </div>
</header>

<section id="dashboard-section">
    <div class="sidebar-container">
        <div class="profile-container">
            <%
                if (new UserDaoImpl().getUserGender(user.getUserId()).equals("M")) {
            %>
            <img src="${pageContext.request.contextPath}/Images/ProfilePictures/Male_Default_pp.png" alt="profile" class="profile-img-dshb"/>
            <%
            } else if (new UserDaoImpl().getUserGender(user.getUserId()).equals("F")) {
            %>
            <img src="${pageContext.request.contextPath}/Images/ProfilePictures/Female_Default_pp.png" alt="profile" class="profile-img-dshb"/>
            <%
            } else if (new UserDaoImpl().getUserGender(user.getUserId()).equals("O")) {
            %>
            <img src="${pageContext.request.contextPath}/Images/ProfilePictures/Female_Default_pp.png" alt="profile" class="profile-img-dshb"/>
            <%
                }
            %>
        </div>
        <!-- Sidebar Navigation Menu -->
        <nav class="sidebar-nav">
            <ul>
                <%
                    if (user.getUserType().equals("Librarian")) {
                %>
                <li class="db-icon" title="Genres">
                    <a href="<%=request.getContextPath()%>/dashboard">
                        <i class="fas fa-tachometer-alt"></i>
                        <span>Dashboard</span>
                    </a>
                </li>
                <% }%>

                <li class="db-icon" title="Genres">
                    <a href="<%=request.getContextPath()%>/dashboard/profile">
                        <i class="fas fa-user"></i>
                        <span>Profile</span>
                    </a>
                </li>

                <%
                    if (user.getUserType().equals("Librarian")) {
                %>
                <li class="db-icon" title="Members">
                    <a href="<%=request.getContextPath()%>/dashboard/members">
                        <i class="fas fa-users"></i>
                        <span>Members</span>
                    </a>
                </li>

                <li class="db-icon"  title="Genres">
                    <a href="<%=request.getContextPath()%>/dashboard/genres">
                        <i class="fas fa-clipboard-list"></i>
                        <span>Genres</span>
                    </a>
                </li>
                <% }%>

                <li class="db-icon" title="Books">
                    <a href="<%=request.getContextPath()%>/dashboard/books/all">
                        <i class="fas fa-book"></i>
                        <span>Books</span>
                    </a>
                </li>

                <%
                    if (user.getUserType().equals("Librarian")) {
                %>
                <li class="db-icon" title="Book Requests">
                    <a href="<%=request.getContextPath()%>/dashboard/books/requests">
                        <i class="fas fa-book-reader"></i>
                        <span>Book Requests</span>
                    </a>
                </li>
                <% }%>

                <li class="db-icon" title="Notice">
                    <a href="<%=request.getContextPath()%>/dashboard/notices">
                        <i class="fas fa-envelope-open"></i>
                        <span>Notice</span>
                    </a>
                </li>
                <%
                    if (user.getUserType().equals("Librarian")) {
                %>
                <li class="db-icon" title="Feedbacks">
                    <a href="<%=request.getContextPath()%>/dashboard/feedbacks">
                        <i class="fas fa-comments"></i>
                        <span>Feedbacks</span>
                    </a>
                </li>
                <% }%>
            </ul>
        </nav>
    </div>
    <div class="main-container">
