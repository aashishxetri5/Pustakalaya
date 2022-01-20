<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    if (session.getAttribute("currentUser") == null) {
        response.sendRedirect(request.getContextPath() + "/home");
    } else {

%>
