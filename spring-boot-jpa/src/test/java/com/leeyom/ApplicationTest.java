package com.leeyom;

import com.leeyom.dao.UserDetailRepository;
import com.leeyom.dao.UserRepository;
import com.leeyom.domain.User;
import com.leeyom.domain.UserDetail;
import com.leeyom.domain.UserInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Resource
    private UserRepository userRepository;
    @Resource
    private UserDetailRepository userDetailRepository;

    @Test
    public void testUserRespository() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(date);

        userRepository.save(new User("leeyom1", "123", "leeyomwang1@qq.com", "Leeyom1", formattedDate));
        userRepository.save(new User("leeyom2", "456", "leeyomwang2@qq.com", "Leeyom2", formattedDate));
        userRepository.save(new User("leeyom3", "789", "leeyomwang3@qq.com", "Leeyom3", formattedDate));


        System.out.println(userRepository.findAll().size());
        System.out.println("Leeyom3".equals(userRepository.findByUserNameOrEmail("leeyom3", "leeyomwang3@qq.com").getNickName()));
        userRepository.delete(userRepository.findByUserName("leeyom2"));
        System.out.println(userRepository.findAll().size());

        // 打印后的结果为：
        // 3
        // true
        // 2
    }

    @Test
    public void testPageQuery() {
        int page=1,size=20;
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        Page<User> userPage = userRepository.findAll(pageable);
        System.out.println("总数量："+userPage.getTotalPages());
        System.out.println(userPage.getContent());
    }

    @Test
    public void testUserInfo(){
        userDetailRepository.save(new UserDetail("12","Hong Kong","running"));
        List<UserInfo> userInfos=userDetailRepository.findUserInfo("running");
        for (UserInfo userInfo:userInfos){
            System.out.println("addree "+userInfo.getAddress());
        }
    }

}
