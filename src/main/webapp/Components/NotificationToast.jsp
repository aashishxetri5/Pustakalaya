
<%
    if (request.getSession().getAttribute("successMsg") != null) {
%>

<input type="text" name="valueOfToast" class="ToastMsg" value="<%=request.getSession().getAttribute("successMsg")%>" hidden>

<%request.getSession().removeAttribute("successMsg");
} else if (request.getSession().getAttribute("errorMsg") != null) {
%>

<input type="text" name="valueOfToast" class="ToastMsg" value="<%=request.getSession().getAttribute("errorMsg")%>" hidden>

<%
    }
    request.getSession().removeAttribute("errorMsg");
    System.out.println("SUCCESS MSG:: " + request.getSession().getAttribute("successMsg"));
    System.out.println("ERROR MSG:: " + request.getSession().getAttribute("errorMsg"));
%>

<!-- The actual snackbar -->
<div id="snackbar"> </div>
