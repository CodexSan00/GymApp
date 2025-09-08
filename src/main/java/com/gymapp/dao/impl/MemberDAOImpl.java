package com.gymapp.dao.impl;

import com.gymapp.models.Member;
import com.gymapp.dao.MemberDAO;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class MemberDAOImpl implements MemberDAO{
    private final Connection conn;

    public MemberDAOImpl(Connection conn){
        this.conn = conn;
    }

    @Override
    public Member create(Member member) {
        String sql = "INSERT INTO members (name, last_name, phone, email, join_date) VALUES (?, ?, ?, ?, ?)";
        try(PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, member.getName());
            stmt.setString(2, member.getLastName());
            stmt.setString(3, member.getPhone());
            stmt.setString(4, member.getEmail());
            stmt.setDate(5, Date.valueOf(member.getJoinDate()));
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    member.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return member;
    }

    @Override
    public Member getById(int id) {
        String sql = "SELECT * FROM members WHERE id = ?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return new Member(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("last_name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getDate("join_date").toLocalDate()
                );
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Member> getAll() {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT * FROM members";
        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){ //Try-with-resources...
            while(rs.next()){
                members.add(new Member(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("last_name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getDate("join_date").toLocalDate()
                ));
            }

        } catch(SQLException e){
            e.printStackTrace();
        }
        return members;
    }

    @Override
    public boolean update(Member member) {
        String sql = "UPDATE members SET name = ?, last_name = ?, phone = ?, email = ?, join_date = ? WHERE id = ?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, member.getName());
            stmt.setString(2, member.getLastName());
            stmt.setString(3, member.getPhone());
            stmt.setString(4, member.getEmail());
            stmt.setDate(5, Date.valueOf(member.getJoinDate()));
            stmt.setInt(6, member.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int memberId) {
        String sql = "DELETE FROM members WHERE id = ?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, memberId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
