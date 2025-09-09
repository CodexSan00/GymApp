package com.gymapp.dao;

import com.gymapp.models.Fee;
import java.util.List;

public interface FeeDAO {
    Fee create(Fee fee);
    List<Fee> getAll();
    Fee getById(int feeId);
    List<Fee> getByMembership(int membershipId);
    boolean update(Fee fee);
    boolean delete(int feeId);
}
