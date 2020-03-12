package com.linkstec.mvc.convert;

import com.linkstec.mvc.domain.User;
import com.linkstec.mvc.dto.UserDto;

public class Test {
    public static void main(String[] args) {
        UserConverter uc = UserConverter.instance;
        User u = new User();
        u.setId("1");
        u.setName("jack");
        u.setPassword("pdsaf");
        UserDto dto = uc.domain2dto(u);
        System.out.println(dto.toString());
        System.out.println(uc.dto2Domain(dto));
    }
}
