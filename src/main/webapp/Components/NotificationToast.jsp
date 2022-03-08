
<%
    if (request.getSession().getAttribute("successMsg") != null) {
%>

<input type="text" name="valueOfToast" class="ToastMsg succ" value="<%=request.getSession().getAttribute("successMsg")%>" hidden>

<%request.getSession().removeAttribute("successMsg");
} else if (request.getSession().getAttribute("errorMsg") != null) {
%>

<input type="text" name="valueOfToast" class="ToastMsg err" value="<%=request.getSession().getAttribute("errorMsg")%>" hidden>

<%
    }
    request.getSession().removeAttribute("errorMsg");
%>

<div class="toast animate__animated animate__slideInLeft animate__faster"></div>
