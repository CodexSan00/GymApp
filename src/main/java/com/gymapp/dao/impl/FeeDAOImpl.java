package com.gymapp.dao.impl;

import com.gymapp.dao.FeeDAO;
import com.gymapp.models.Fee;
import com.gymapp.models.Membership;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class FeeDAOImpl implements FeeDAO {
    private final Connection conn;

    public FeeDAOImpl(Connection conn){
        this.conn = conn;
    }

    @Override
    public Fee create(Fee fee) {
        String sql = "INSERT INTO fees (amount, start_date, due_date, status, membership_id) VALUES (?, ?, ?, ?, ?)";
        try(PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            stmt.setDouble(1, fee.getAmount());
            stmt.setDate(2, Date.valueOf(fee.getStartDate()));
            stmt.setDate(3, Date.valueOf(fee.getDueDate()));
            stmt.setString(4, fee.getStatus().name());
            stmt.setInt(5, fee.getMembershipId());

            stmt.executeUpdate();

            try(ResultSet rs = stmt.getGeneratedKeys()){
                if(rs.next()){
                    fee.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return fee;
    }

    @Override
    public Fee getById(int feeId) {
        String sql = "SELECT * FROM fees WHERE id = ?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, feeId);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return mapRowToFee(rs);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Fee> getAll() {
        List<Fee> fees = new ArrayList<>();
        String sql = "SELECT * FROM fees";
        try(Statement stmt = conn.createStatement()){
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                fees.add(mapRowToFee(rs));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return fees;
    }

    @Override
    public List<Fee> getByMembership(int membershipId) {
        List<Fee> feesByMembership = new ArrayList<>();
        String sql = "SELECT * FROM fees WHERE membership_id = ?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, membershipId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                feesByMembership.add(mapRowToFee(rs));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return feesByMembership;
    }

    @Override
    public boolean update(Fee fee) {
        String sql = "UPDATE fees SET amount = ?, start_date = ?, due_date = ?, status = ? WHERE id = ?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setDouble(1, fee.getAmount());
            stmt.setDate(2,Date.valueOf(fee.getStartDate()));
            stmt.setDate(3, Date.valueOf(fee.getDueDate()));
            stmt.setString(4, fee.getStatus().name());
            stmt.setInt(5, fee.getId());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int feeId) {
        String sql = "DELETE FROM fees WHERE id = ?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, feeId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Fee mapRowToFee(ResultSet rs) throws SQLException { //amount, start_date, due_date, status, membership_id
        int id = rs.getInt("id");
        double amount = rs.getDouble("amount");
        LocalDate startDate = rs.getDate("start_date").toLocalDate();
        LocalDate dueDate = rs.getDate("due_date").toLocalDate();
        Fee.Status status = Fee.Status.valueOf(rs.getString("status"));
        int membershipId = rs.getInt("membership_id");
        return new Fee(id, membershipId, amount, status, startDate, dueDate);
    }




}
