package com.Stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Stream流式计算
 *
 * 现在有5个用户 筛选  只能用一行代码实现
 * 1.ID必须为偶数
 * 2.年龄必须大于23
 * 3.用户名必须转为大写
 * 4.用户名字母倒着排序
 * 5.值输出一个用户
 */
public class StreamDemo {
    public static void main(String[] args) {
        User user1 = new User(1,"a",21);
        User user2 = new User(2,"b",22);
        User user3 = new User(3,"c",23);
        User user4 = new User(4,"d",24);
        User user5 = new User(6,"e",25);
        List<User> users = Arrays.asList(user1, user2, user3, user4, user5);
        //计算
      users.stream()
              .filter(user -> {return user.getId() % 2 == 0;})
              .filter(user -> {return  user.getAge() > 23;})
              .map(user ->{return user.getName().toUpperCase();})
              .sorted((u1,u2) -> {return u2.compareTo(u1);})
              .limit(1)
              .forEach(user -> { System.out.println(user); });
    }
}
