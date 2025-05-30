<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Delete Fee Payment</title>
</head>
<body>
    <h2>Delete Fee Payment</h2>
    <form action="deletePayment" method="post">
        <label>Payment ID (to delete):</label><br>
        <input type="number" name="paymentID" required><br><br>

        <input type="submit" value="Delete Payment">
    </form>
    <br>
    <a href="index.jsp">Back to Home</a>
</body>
</html>
