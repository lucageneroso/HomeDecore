<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.IOException" %>
<%
    // Invalida la sessione per effettuare il logout
    if (session != null) {
        session.invalidate(); // Termina la sessione corrente
    }

    // Reindirizza alla pagina principale dopo il logout
    response.sendRedirect("home.jsp");
%>