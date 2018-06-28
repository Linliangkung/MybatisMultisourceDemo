package com.jsako.mapper;

import java.util.List;

import com.jsako.domain.TestUser;

public interface TestUserMapper {
	List<TestUser> getUserById(Integer id);
	boolean saveUser(TestUser testUser);
}
