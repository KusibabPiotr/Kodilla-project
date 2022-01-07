package com.crud.tasks.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdminConfigTest {

    @Autowired
    private AdminConfig adminConfig;

    @Test
    void testGetAdminEmail(){
        //when
        String adminMail = adminConfig.getAdminMail();
        //then
        assertEquals("starszykruku@op.pl",adminMail);
    }
}