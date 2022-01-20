<%-- 
    Document   : profile
    Created on : Jan 16, 2022, 10:31:19 AM
    Author     : Aashish Katwal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="icon" href="${pageContext.request.contextPath}/Images/LogoAndBg/Favicon.png">
        <title>Dashboard | Profile</title>

        <%@include file="Components/all_css_js.jsp" %>

    </head>
    <body id="dashboard-body">

        <%@include file="Components/PageDashboard.jsp" %> 
        <div class="profile-left-div">
            <h1 class="p-title-heading">Profile:</h1>
            <div class="profile-tab-dash">
                <img src="${pageContext.request.contextPath}/Images/ProfilePictures/Male_Default_pp.png" alt="Profile PP" class="profile-tab-img" />
                <form action="changePP" method="POST" id="profile-upload-form">
                    <label for="pimg-upload"title="Upload Profile Picture">Choose</label>
                    <input type="file" id="pimg-upload" name="newProfileImg" accept="image/png,image/jpeg" onchange="form.submit()">
                    <p class="img-desc">jpg or png only</p>
                    <p class="img-desc">max. size is 500kb and preferred resolution is 500x500</p>
                </form>
            </div>
            <div class="acc-info">
                <h1 class="p-title-heading">Account Information:</h1>

                <%
                    Users thisUser = new UserDaoImpl().getCurrentUserDetail(user.getUserId());
                %>

                <div class="labels">
                    <label for="UserId">User Id</label>
                    <input type="text" id="UserId" class="ptab-inputs" value="<%=thisUser.getUserId()%>" autocomplete="off" disabled/>
                </div>

                <div class="labels">
                    <label for="LibraryId">Library Id</label>
                    <input type="text" id="LibraryId" class="ptab-inputs" value="<%=thisUser.getLibraryId()%>" disabled/>
                </div>

                <div class="labels">
                    <label for="Fullname">Fullname</label>
                    <input type="text" id="Fullname" class="ptab-inputs" disabled
                           value="<%=thisUser.getFirstName().concat(" ").concat(thisUser.getLastName())%>" />
                </div>

                <div class="labels">
                    <label for="Email">Email</label>
                    <input type="text" id="Email" class="ptab-inputs" disabled value="<%=thisUser.getEmail()%>" />
                </div>

                <div class="labels">
                    <label for="Address">Address</label>
                    <input type="text" id="Address" class="ptab-inputs" disabled value="<%=thisUser.getAddress()%>" />
                </div>

                <div class="labels">
                    <label for="Contact">Contact</label>
                    <input type="text" id="Contact" class="ptab-inputs"disabled value="<%=thisUser.getContactNum()%>" />
                </div>
            </div>
        </div>
        <div class="profile-right-div">
            <div class="wrapper-box">
                <div>
                    <p class="Title">Borrowed Books</p>
                    <div class="ptab-block-boxes" title="Borrowed Books">
                        <a href="<%=request.getContextPath()%>/dashboard/books/borrowed">
                            <p><strong>4<%//=new CountRecords().getTotalBooks()%></strong></p>
                            <p>Borrowed</p>
                        </a>
                    </div>
                </div>
                <div>
                    <p class="Title"> Fine </p>
                    <div class="ptab-block-boxes" title="Fine: Click to pay">
                        <p><strong>Rs. <%=thisUser.getFine()%></strong></p>
                        <p>Fine</p>
                    </div>
                </div>
            </div>
            <hr id="left-div-hr" />
            <div id="button-group">
                <button class="update-info-btn" title="Update Information">
                    <a href="${pageContext.request.contextPath}/updateInfo">Update</a>
                </button>
                <button class="p-logout-btn" title="Logout">
                    <a href="${pageContext.request.contextPath}/logout">Logout</a>
                </button>
            </div>
        </div>
    </div>
</section>
<%    }
%>
</body>
</html>