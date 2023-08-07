package com.test.test1.batch.config;

import com.test.test1.batch.model.User;
import org.springframework.batch.item.ItemProcessor;

public class UserItemProcessor implements ItemProcessor<User,User> {

    @Override
    public User process(User item) throws Exception {
        return item;
    }
}
