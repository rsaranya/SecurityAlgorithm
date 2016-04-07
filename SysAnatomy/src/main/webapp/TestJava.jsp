<%-- 
    Document   : TestJava
    Created on : Apr 5, 2016, 10:35:26 PM
    Author     : saranya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="org.json.simple.JSONObject" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String s;
            Process p;

            p = Runtime.getRuntime().exec("cat /proc/cpuinfo");

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            System.out.println("File print starts here");
            String[] lstrKeyValue = null;
            while ((s = br.readLine()) != null) {
                JSONObject cpuInfoObject = new JSONObject();
                if (!s.isEmpty()) {
                    lstrKeyValue = s.split(":");
                }

                if (lstrKeyValue != null & lstrKeyValue.length == 2) {
                    cpuInfoObject.put(lstrKeyValue[0], lstrKeyValue[1]);
                     %>
                    <h5><%="Key : " + lstrKeyValue[0] + " Value : " + lstrKeyValue[1]%>
                        <%    }
            }
            p.waitFor();
            System.out.println("exit: " + p.exitValue());
            p.destroy();

        %>
        <h1>Hello World!</h1>
    </body>
</html>
