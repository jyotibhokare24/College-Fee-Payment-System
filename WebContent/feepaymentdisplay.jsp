<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.model.FeePayment" %>
<!DOCTYPE html>
<html>
<head>
    <title>All Fee Payments</title>
</head>
<body>
    <h2>All Fee Payments</h2>
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
                List<FeePayment> payments = (List<FeePayment>) request.getAttribute("payments");
                if (payments != null && !payments.isEmpty()) {
                    for (FeePayment p : payments) {
            %>
            <tr>
                <td><%= p.getPaymentID() %></td>
                <td><%= p.getStudentID() %></td>
                <td><%= p.getStudentName() %></td>
                <td><%= p.getPaymentDate() %></td>
                <td><%= p.getAmount() %></td>
                <td><%= p.getStatus() %></td>
            </tr>
            <%      }
                } else { %>
            <tr><td colspan="6">No payment records found.</td></tr>
            <% } %>
        </tbody>
    </table>
    <br>
    <a href="index.jsp">Back to Home</a>
</body>
</html>
