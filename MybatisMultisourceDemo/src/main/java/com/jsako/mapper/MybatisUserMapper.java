package com.jsako.mapper;

import java.util.List;

import com.jsako.domain.MybatisUser;

public interface MybatisUserMapper {
	List<MybatisUser> getUserById(Integer id);
}
