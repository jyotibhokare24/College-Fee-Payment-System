<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.model.FeePayment" %>
<%@ page import="java.math.BigDecimal" %>
<!DOCTYPE html>
<html>
<head>
    <title>Report Results</title>
</head>
<body>
    <h2>Report Results</h2>
    <%
        String reportType = (String) request.getAttribute("type");

        if ("total".equals(reportType)) {
            BigDecimal total = (BigDecimal) request.getAttribute("total");
    %>
            <p><strong>Total Collection:</strong> <%= (total != null ? total : "0.00") %></p>
    <%
        } else {
            List<FeePayment> reportList = (List<FeePayment>) request.getAttribute("report");
            if (reportList != null && !reportList.isEmpty()) {
    %>
                <table border="1" cellpadding="5" cellspacing="0">
                    <thead>
                        <tr>
                            <th>Payment ID</th>
                            <th>Student ID</th>
                            <th>Student Name</th>
                            <th>Payment Date</th>
                            <th>Amount</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (FeePayment p : reportList) {
                        %>
                        <tr>
                            <td><%= p.getPaymentID() %></td>
                            <td><%= p.getStudentID() %></td>
                            <td><%= p.getStudentName() %></td>
                            <td><%= p.getPaymentDate() %></td>
                            <td><%= p.getAmount() %></td>
                            <td><%= p.getStatus() %></td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
    <%
            } else {
    %>
                <p>No records found for the selected criteria.</p>
    <%
            }
        }
    %>
    <br>
    <a href="reportForm">Back to Reports</a> | <a href="index.jsp">Back to Home</a>
</body>
</html>
