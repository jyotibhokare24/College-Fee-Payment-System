package com.servlet;

import com.dao.FeePaymentDAO;
import com.model.FeePayment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@WebServlet("/generateReport")
public class ReportServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String reportType = request.getParameter("reportType");
            FeePaymentDAO dao = new FeePaymentDAO();

            switch (reportType) {
                case "overdue":
                    List<FeePayment> overduePayments = dao.getOverduePayments();
                    request.setAttribute("report", overduePayments);
                    break;
                case "unpaid":
                    Date startDate = Date.valueOf(request.getParameter("startDate"));
                    Date endDate = Date.valueOf(request.getParameter("endDate"));
                    List<FeePayment> unpaidPayments = dao.getUnpaidInPeriod(startDate, endDate);
                    request.setAttribute("report", unpaidPayments);
                    break;
                case "total":
                    Date start = Date.valueOf(request.getParameter("startDate"));
                    Date end = Date.valueOf(request.getParameter("endDate"));
                    BigDecimal total = dao.getTotalCollection(start, end);
                    request.setAttribute("total", total);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid report type");
            }

            request.setAttribute("type", reportType);
            request.getRequestDispatcher("report_result.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
