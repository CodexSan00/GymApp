package com.gymapp.dao.impl;

import com.gymapp.dao.MembershipDAO;
import com.gymapp.models.Member;
import com.gymapp.models.Membership;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MembershipDAOImpl implements MembershipDAO {
    private Connection conn;

    public MembershipDAOImpl(Connection conn){
        this.conn = conn;
    }

    @Override
    public Membership create(Membership membership) {
        String sql = "INSERT INTO memberships (member_id, plan_type, start_date, status) VALUES (?, ? ,? ,?)";
        try(PreparedStatement stmt = conn.prepareStatement(sql,  Statement.RETURN_GENERATED_KEYS)){
            stmt.setInt(1, membership.getMemberId());
            stmt.setString(2, membership.getPlanType());
            stmt.setDate(3, Date.valueOf(membership.getStartDate()));
            stmt.setString(4, membership.getStatus().name()); //Gets the string

            stmt.executeUpdate();

            try(ResultSet rs = stmt.getGeneratedKeys()){
                if(rs.next()){
                    membership.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return membership;
    }

    @Override
    public Membership getById(int membershipId) {
        String sql = "SELECT * FROM memberships WHERE id = ?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, membershipId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapRowToMembership(rs);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Membership getByMemberId(int memberId) {
        String sql = "SELECT * FROM memberships WHERE member_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, memberId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapRowToMembership(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Membership> getAll() {
        List<Membership> memberships = new ArrayList<>();
        String sql = "SELECT * FROM memberships";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                memberships.add(mapRowToMembership(rs));
            }
        }catch(SQLException e){
                e.printStackTrace();
            }
        return memberships;
    }

    @Override
    public boolean update(Membership membership) {
        String sql = "UPDATE memberships SET plan_type = ?, start_date = ?, status = ? WHERE id = ?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, membership.getPlanType());
            stmt.setDate(2, Date.valueOf(membership.getStartDate()));
            stmt.setString(3,membership.getStatus().name());
            stmt.setInt(4, membership.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Membership membership) {
        String sql = "DELETE FROM memberships WHERE id = ?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, membership.getId());
            return stmt.executeUpdate() > 0;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    private Membership mapRowToMembership(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int memberId = rs.getInt("member_id");
        String planType = rs.getString("plan_type");
        LocalDate startDate = rs.getDate("start_date").toLocalDate();
        Membership.Status status = Membership.Status.valueOf(rs.getString("status"));
        return new Membership(id, memberId, planType, startDate, status);
    }
}
