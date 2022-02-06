
<%
    String msg = "", className = "";
    if (request.getAttribute("successMsg") != null) {
        msg = (String) request.getAttribute("successMsg");
        className = "show";
    } else if (request.getAttribute("errorMsg") != null) {
        msg = (String) request.getAttribute("errorMsg");
        className = "show";
    }
%>

<!-- The actual snackbar -->
<div id="snackbar">
    
</div>