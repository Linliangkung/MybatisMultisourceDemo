package com.jsako.serivce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jsako.annotations.TargetDataSource;
import com.jsako.domain.TestUser;
import com.jsako.mapper.TestUserMapper;

@Transactional
@Service
@TargetDataSource("test")
public class TestUserSerivce {
	@Autowired
	private TestUserMapper testUserMapper;
	
	public List<TestUser> getUserById(Integer id){
		return 	testUserMapper.getUserById(id);
	}
}
