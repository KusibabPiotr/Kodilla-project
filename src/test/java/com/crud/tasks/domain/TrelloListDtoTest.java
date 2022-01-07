package com.crud.tasks.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrelloListDtoTest {

    private final TrelloListDto dto = new TrelloListDto("id","name",true);

    @Test
    void testGetId(){
        //when
        String id = dto.getId();
        //then
        assertEquals("id",id);
    }

    @Test
    void testGetName(){
        //when
        String name = dto.getName();
        //then
        assertEquals("name",name);
    }

    @Test
    void testGetIsClosed(){
        //when
        boolean closed = dto.isClosed();
        //then
        assertTrue(closed);
    }
}