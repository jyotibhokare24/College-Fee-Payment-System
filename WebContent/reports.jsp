<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reports</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; background-color: #f4f4f4; }
        .container { background-color: #fff; padding: 30px; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); max-width: 800px; margin: auto; }
        h1 { text-align: center; color: #333; margin-bottom: 25px; }
        ul { list-style-type: none; padding: 0; text-align: center; }
        ul li { margin-bottom: 15px; }
        ul li a, ul li form input[type="submit"] {
            display: inline-block;
            padding: 12px 25px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-size: 1.1em;
            transition: background-color 0.3s ease;
            border: none;
            cursor: pointer;
        }
        ul li a:hover, ul li form input[type="submit"]:hover {
            background-color: #0056b3;
        }
        hr { border: 0; height: 1px; background-color: #ccc; margin: 30px 0; }
        .back-link { display: block; text-align: center; margin-top: 20px; text-decoration: none; color: #007bff; }
        .back-link:hover { text-decoration: underline; }
    </style>
</head>
<body>
    <div class="container">
        <h1>Generate Reports</h1>

        <ul>
            <li>
                <a href="generateReport?reportType=overdue">Students with Overdue Payments</a>
            </li>
            <li>
                <hr>
                <p><strong>Custom Reports (require date range):</strong></p>
                <a href="report_form.jsp">Generate Custom Report</a>
            </li>
        </ul>

        <a href="index.jsp" class="back-link">Back to Home</a>
    </div>
</body>
</html>
