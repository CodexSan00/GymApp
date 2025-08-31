package com.gymapp.dao;

import com.gymapp.models.Member;
import java.util.List;

public interface MemberDAO {
    Member create(Member member);
    Member getById(int id);
    List<Member> getAll();
    boolean update(Member member);
    boolean delete(int id);
}
