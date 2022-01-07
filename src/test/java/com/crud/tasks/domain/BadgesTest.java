package com.crud.tasks.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BadgesTest {

    private Badges badges = new Badges();

    @Test
    void testGetVotes(){
        //given
        badges.setVotes(1);
        //when
        int votes = badges.getVotes();
        //then
        assertEquals(1,votes);
    }
}