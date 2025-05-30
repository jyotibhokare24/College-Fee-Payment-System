<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Fee Payment</title>
</head>
<body>
    <h2>Add New Fee Payment</h2>
    <form action="addPayment" method="post">
        <label>Student ID:</label><br>
        <input type="number" name="studentID" required><br><br>

        <label>Student Name:</label><br>
        <input type="text" name="studentName" required><br><br>

        <label>Payment Date:</label><br>
        <input type="date" name="paymentDate" required><br><br>

        <label>Amount:</label><br>
        <input type="number" step="0.01" name="amount" required><br><br>

        <label>Status:</label><br>
        <select name="status" required>
            <option value="Paid">Paid</option>
            <option value="Overdue">Overdue</option>
        </select><br><br>

        <input type="submit" value="Add Payment">
    </form>
    <br>
    <a href="index.jsp">Back to Home</a>
</body>
</html>
