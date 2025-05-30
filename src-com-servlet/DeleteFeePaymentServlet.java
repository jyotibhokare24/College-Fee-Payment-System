package com.servlet;

import com.dao.FeePaymentDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/deletePayment")
public class DeleteFeePaymentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int paymentID = Integer.parseInt(request.getParameter("paymentID"));
            FeePaymentDAO dao = new FeePaymentDAO();
            dao.deletePayment(paymentID);

            response.sendRedirect("displayPayments");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
