package com.laboratory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.laboratory.model.User;
import com.laboratory.service.UserService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

	@Autowired
	UserService svc;
	
	@Test
	public void getUserById() {
		try {
			User user = svc.getUserById("ys");
			System.out.println(user.toString());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
