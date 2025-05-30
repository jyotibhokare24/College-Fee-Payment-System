package com.servlet;

import com.dao.FeePaymentDAO;
import com.model.FeePayment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/displayPayments")
public class DisplayFeePaymentsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            FeePaymentDAO dao = new FeePaymentDAO();
            List<FeePayment> payments = dao.getAllPayments();
            request.setAttribute("payments", payments);
            request.getRequestDispatcher("feepaymentdisplay.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
