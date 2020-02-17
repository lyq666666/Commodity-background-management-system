package com.lyq.ssm.dao;

import com.lyq.ssm.domain.Member;
import org.apache.ibatis.annotations.Select;

public interface MemberDao {

    @Select("select * from member where id=#{id}")
    public Member findById(String id);
}
