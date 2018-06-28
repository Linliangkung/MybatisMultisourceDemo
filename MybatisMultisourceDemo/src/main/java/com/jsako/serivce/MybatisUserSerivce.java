package com.jsako.serivce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jsako.annotations.TargetDataSource;
import com.jsako.domain.MybatisUser;
import com.jsako.domain.TestUser;
import com.jsako.mapper.MybatisUserMapper;
import com.jsako.mapper.TestUserMapper;

@Transactional
@Service
@TargetDataSource("mybatis")
public class MybatisUserSerivce {
	@Autowired
	private MybatisUserMapper mybatisUserMapper;
	
	public List<MybatisUser> getUserById(Integer id){
		List<MybatisUser> mybatisUsers = mybatisUserMapper.getUserById(id);
		return mybatisUsers;
	}
}
