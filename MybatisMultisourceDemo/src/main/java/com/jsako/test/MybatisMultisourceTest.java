
package com.jsako.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jsako.datasource.DatasourceHelper;
import com.jsako.domain.MybatisUser;
import com.jsako.domain.TestUser;
import com.jsako.serivce.MybatisUserSerivce;
import com.jsako.serivce.TestUserSerivce;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class MybatisMultisourceTest {
	@Autowired
	private TestUserSerivce testUserSerivce;
	@Autowired 
	private MybatisUserSerivce mybatisUserSerivce;

	@Test
	public void test1(){
		List<MybatisUser> users = mybatisUserSerivce.getUserById(1);
		users.forEach((user)->{
			System.out.println(user);
		});
		
		List<TestUser> users2 = testUserSerivce.getUserById(1);
		users2.forEach((user)->{
			System.out.println(user);
		});
	}
}
