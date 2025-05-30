package com.servlet;

import com.dao.FeePaymentDAO;
import com.model.FeePayment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;

@WebServlet("/updatePayment")
public class UpdateFeePaymentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int paymentID = Integer.parseInt(request.getParameter("paymentID"));
            int studentID = Integer.parseInt(request.getParameter("studentID"));
            String studentName = request.getParameter("studentName");
            Date paymentDate = Date.valueOf(request.getParameter("paymentDate"));
            BigDecimal amount = new BigDecimal(request.getParameter("amount"));
            String status = request.getParameter("status");

            FeePayment payment = new FeePayment(paymentID, studentID, studentName, paymentDate, amount, status);
            FeePaymentDAO dao = new FeePaymentDAO();
            dao.updatePayment(payment);

            response.sendRedirect("displayPayments");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
