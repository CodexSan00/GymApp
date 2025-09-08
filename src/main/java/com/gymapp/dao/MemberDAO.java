package com.gymapp.dao;

import com.gymapp.models.Member;
import java.util.List;

public interface MemberDAO {
    Member create(Member member);
    Member getById(int memberId);
    List<Member> getAll();
    boolean update(Member member);
    boolean delete(int memberId);
}
