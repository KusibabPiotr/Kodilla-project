package com.crud.tasks.trello.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrelloConfigTest {

    @Autowired
    private TrelloConfig trelloConfig;

    @Test
    void testGetTrelloApiEndpoint(){
        //when
        String trelloApiEndpoint = trelloConfig.getTrelloApiEndpoint();
        //then
        assertEquals("https://api.trello.com/1",trelloApiEndpoint);
    }

    @Test
    void testGetTrelloAppKey(){
        //when
        String trelloAppKey = trelloConfig.getTrelloAppKey();
        //then
        assertEquals("9a0f23f03aa1cc2af772b431f637e238",trelloAppKey);
    }

    @Test
    void testGetTrelloToken(){
        //when
        String trelloToken = trelloConfig.getTrelloToken();
        //then
        assertEquals("132ff385ffb605c77146100a872cccb503c24f51aa2feda8c738c67da198aa22",trelloToken);
    }

    @Test
    void testGetTrelloUser(){
        //when
        String trelloUser = trelloConfig.getTrelloUser();
        //then
        assertEquals("piotrka4",trelloUser);
    }
}