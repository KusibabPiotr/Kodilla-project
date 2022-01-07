package com.crud.tasks.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrelloCardDtoTest {

    private final TrelloCardDto trelloCardDto = new TrelloCardDto("name","desc","pos","id");

    @Test
    void testGetName(){
        //when
        String name = trelloCardDto.getName();
        //then
        assertEquals("name",name);
    }

    @Test
    void testGetDesc(){
        //when
        String desc = trelloCardDto.getDescription();
        //then
        assertEquals("desc",desc);
    }

    @Test
    void testGetPos(){
        //when
        String pos = trelloCardDto.getPos();
        //then
        assertEquals("pos",pos);
    }

    @Test
    void testGetIdList(){
        //when
        String idList = trelloCardDto.getIdList();
        //then
        assertEquals("id",idList);
    }

    @Test
    void testGetToString(){
        //when
        String string = trelloCardDto.toString();
        System.out.println(string);
        //then
        assertEquals("TrelloCardDto(name=name, description=desc, pos=pos, idList=id)",string);
    }
}