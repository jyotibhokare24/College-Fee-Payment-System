package com.dao;

import com.model.FeePayment;
import java.sql.*;
import java.util.*;
import java.math.BigDecimal;

public class FeePaymentDAO {

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    private Connection connect() throws SQLException {
        String url = "jdbc:mysql://localhost:3307/feepaymentdb?useSSL=false&serverTimezone=UTC";
        String user = "student";
        String password = ""; // Change accordingly
        return DriverManager.getConnection(url, user, password);
    }

    public void addPayment(FeePayment payment) throws SQLException {
        String sql = "INSERT INTO FeePayments (StudentID, StudentName, PaymentDate, Amount, Status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, payment.getStudentID());
            stmt.setString(2, payment.getStudentName());

            // Convert java.util.Date to java.sql.Date
            if (payment.getPaymentDate() != null) {
                stmt.setDate(3, new java.sql.Date(payment.getPaymentDate().getTime()));
            } else {
                stmt.setDate(3, null);
            }

            stmt.setBigDecimal(4, payment.getAmount());
            stmt.setString(5, payment.getStatus());
            stmt.executeUpdate();
        }
    }

    public List<FeePayment> getAllPayments() throws SQLException {
        List<FeePayment> list = new ArrayList<>();
        String sql = "SELECT * FROM FeePayments";
        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                FeePayment payment = new FeePayment(
                    rs.getInt("PaymentID"),
                    rs.getInt("StudentID"),
                    rs.getString("StudentName"),
                    rs.getDate("PaymentDate"),  // This returns java.sql.Date which extends java.util.Date
                    rs.getBigDecimal("Amount"),
                    rs.getString("Status")
                );
                list.add(payment);
            }
        }
        return list;
    }

    public void updatePayment(FeePayment payment) throws SQLException {
        String sql = "UPDATE FeePayments SET StudentID=?, StudentName=?, PaymentDate=?, Amount=?, Status=? WHERE PaymentID=?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, payment.getStudentID());
            stmt.setString(2, payment.getStudentName());

            if (payment.getPaymentDate() != null) {
                stmt.setDate(3, new java.sql.Date(payment.getPaymentDate().getTime()));
            } else {
                stmt.setDate(3, null);
            }

            stmt.setBigDecimal(4, payment.getAmount());
            stmt.setString(5, payment.getStatus());
            stmt.setInt(6, payment.getPaymentID());
            stmt.executeUpdate();
        }
    }

    public void deletePayment(int id) throws SQLException {
        String sql = "DELETE FROM FeePayments WHERE PaymentID=?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<FeePayment> getOverduePayments() throws SQLException {
        List<FeePayment> list = new ArrayList<>();
        String sql = "SELECT * FROM FeePayments WHERE Status = 'Overdue'";
        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new FeePayment(
                    rs.getInt("PaymentID"),
                    rs.getInt("StudentID"),
                    rs.getString("StudentName"),
                    rs.getDate("PaymentDate"),
                    rs.getBigDecimal("Amount"),
                    rs.getString("Status")
                ));
            }
        }
        return list;
    }

    public List<FeePayment> getUnpaidInPeriod(java.util.Date start, java.util.Date end) throws SQLException {
        List<FeePayment> list = new ArrayList<>();
        String sql = "SELECT * FROM FeePayments WHERE PaymentDate BETWEEN ? AND ? AND Status <> 'Paid'";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(start.getTime()));
            stmt.setDate(2, new java.sql.Date(end.getTime()));
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new FeePayment(
                        rs.getInt("PaymentID"),
                        rs.getInt("StudentID"),
                        rs.getString("StudentName"),
                        rs.getDate("PaymentDate"),
                        rs.getBigDecimal("Amount"),
                        rs.getString("Status")
                    ));
                }
            }
        }
        return list;
    }

    public BigDecimal getTotalCollection(java.util.Date start, java.util.Date end) throws SQLException {
        String sql = "SELECT SUM(Amount) AS Total FROM FeePayments WHERE PaymentDate BETWEEN ? AND ?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(start.getTime()));
            stmt.setDate(2, new java.sql.Date(end.getTime()));
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    BigDecimal total = rs.getBigDecimal("Total");
                    return (total != null) ? total : BigDecimal.ZERO;
                }
            }
        }
        return BigDecimal.ZERO;
    }
}
