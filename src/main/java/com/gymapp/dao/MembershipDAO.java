package com.gymapp.dao;

import com.gymapp.models.Membership;
import java.util.List;

public interface MembershipDAO {
    Membership create(Membership membership);
    Membership getById(int membershipId);
    Membership getByMemberId(int memberId);
    List<Membership> getAll();
    boolean update(Membership membership);
    boolean delete(int membershipId);
}
