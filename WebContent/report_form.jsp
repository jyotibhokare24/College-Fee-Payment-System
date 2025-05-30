<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Generate Reports</title>
    <script>
        function toggleDateRange() {
            var reportType = document.getElementById("reportType").value;
            var dateRangeDiv = document.getElementById("dateRange");
            if (reportType === "unpaid" || reportType === "total") {
                dateRangeDiv.style.display = "block";
            } else {
                dateRangeDiv.style.display = "none";
            }
        }
    </script>
</head>
<body>
    <h2>Generate Reports</h2>
    <form action="generateReport" method="post">
        <label>Select Report Type:</label><br>
        <select id="reportType" name="reportType" onchange="toggleDateRange()" required>
            <option value="">--Select--</option>
            <option value="overdue">Students with Overdue Payments</option>
            <option value="unpaid">Students Who Haven't Paid in a Period</option>
            <option value="total">Total Collection in a Period</option>
        </select><br><br>

        <div id="dateRange" style="display:none;">
            <label>Start Date:</label><br>
            <input type="date" name="startDate"><br><br>
            <label>End Date:</label><br>
            <input type="date" name="endDate"><br><br>
        </div>

        <input type="submit" value="Generate Report">
    </form>
    <br>
    <a href="index.jsp">Back to Home</a>
</body>
</html>
