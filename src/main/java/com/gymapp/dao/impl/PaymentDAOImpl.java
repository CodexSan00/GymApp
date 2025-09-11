package com.gymapp.dao.impl;

import com.gymapp.dao.PaymentDAO;
import com.gymapp.models.Payment;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PaymentDAOImpl implements PaymentDAO {
    private Connection conn;

    public PaymentDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Payment create(Payment payment) {
        String sql = "INSERT INTO payments (fee_id, amount, payment_date, reference, method, status) VALUES (?, ?, ?, ?, ?, ?)";

        //Gen unique reference
        String reference = UUID.randomUUID().toString();

        try(PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            stmt.setInt(1, payment.getFeeId());
            stmt.setDouble(2, payment.getAmount());
            stmt.setDate(3, Date.valueOf(payment.getPaymentDate()));
            payment.setReference(reference); // Set gen ref
            stmt.setString(4, payment.getReference());
            stmt.setString(5, payment.getPaymentMethod().name());
            stmt.setString(6, payment.getStatus().name());

            stmt.executeUpdate();

            try(ResultSet rs = stmt.getGeneratedKeys()){
                if(rs.next()){
                    payment.setId(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payment;
    }

    @Override
    public Payment getById(int paymentId) {
        String sql = "SELECT * FROM payments WHERE id = ?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, paymentId);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return mapRowToPayment(rs);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Payment> getByFee(int feeId) {
        List<Payment> listOfPayments = new ArrayList<>();
        String sql = "SELECT * FROM payments WHERE fee_id = ?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, feeId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                listOfPayments.add(mapRowToPayment(rs));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return listOfPayments;
    }

    @Override
    public List<Payment> getAll(){
        List<Payment> listOfAllPayments = new ArrayList<>();
        String sql = "SELECT * FROM payments";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()){
                listOfAllPayments.add(mapRowToPayment(rs));
            }
        } catch(SQLException e ){
            e.printStackTrace();
        }
        return listOfAllPayments;
    }

    @Override
    public boolean update(Payment payment) {
        String sql = "UPDATE payments SET amount = ?, payment_date = ?, method = ?, status = ? WHERE id = ?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setDouble(1, payment.getAmount());
            stmt.setDate(2, Date.valueOf(payment.getPaymentDate()));
            stmt.setString(3, payment.getPaymentMethod().name());
            stmt.setString(4, payment.getStatus().name());
            stmt.setInt(5, payment.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int paymentId) {
        String sql = "DELETE FROM payments WHERE id = ?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, paymentId);
            return stmt.executeUpdate() > 0;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    private Payment mapRowToPayment(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int feeId = rs.getInt("fee_id");
        double amount = rs.getDouble("amount");
        LocalDate paymentDate = rs.getDate("payment_date").toLocalDate();
        Payment.Method method = Payment.Method.valueOf(rs.getString("method"));
        Payment.Status status = Payment.Status.valueOf(rs.getString("status"));
        Payment payment = new Payment(id, feeId, amount, paymentDate, method, status);
        payment.setReference(rs.getString("reference")); // Set reference from DB so the user doesn't need to provide it manually
        return payment;
    }


}
