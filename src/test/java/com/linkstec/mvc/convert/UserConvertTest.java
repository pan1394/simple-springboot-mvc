package com.linkstec.mvc.convert;


import com.linkstec.mvc.domain.User;
import com.linkstec.mvc.dto.UserDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserConvertTest {

    @Autowired
    private UserConverter converter;

    @Test
    public void test(){
        UserConverter uc = UserConverter.instance;
        User u = new User("1", "jack", "blu..cha...", "");
        UserDto dto = uc.domain2dto(u);
        UserDto expected = new UserDto("jack", "1", "blu..cha...");
        Assert.assertEquals( expected.getId(), u.getId());
        Assert.assertEquals( expected.getLoginName(), u.getName());
        Assert.assertEquals( expected.getPwd(), u.getPassword());
    }

    @Test
    public void test2(){
        User u = new User("1", "jack", "blu..cha...", "");
        UserDto dto = converter.domain2dto(u);
        UserDto expected = new UserDto("jack", "1", "blu..cha...");
        Assert.assertEquals( expected.getId(), u.getId());
        Assert.assertEquals( expected.getLoginName(), u.getName());
        Assert.assertEquals( expected.getPwd(), u.getPassword());

    }
}
