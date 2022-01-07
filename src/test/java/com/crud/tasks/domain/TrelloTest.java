package com.crud.tasks.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrelloTest {

    private final Trello trello = new Trello();

    @Test
    void testGetBoard(){
        //given
        trello.setBoard(1);
        //when
        int board = trello.getBoard();
        //then
        assertEquals(1,board);
    }

    @Test
    void testGetCard(){
        //given
        trello.setCard(1);
        //when
        int card = trello.getCard();
        //then
        assertEquals(1,card);
    }

    @Test
    void testGetString(){
        //given
        trello.setCard(1);
        trello.setBoard(1);
        //when
        String string = trello.toString();
        //then
        assertEquals("Trello(board=1, card=1)",string);
    }

    @Test
    void testHash(){
        //given
        trello.setCard(1);
        trello.setBoard(1);
        //when
        int hash = trello.hashCode();
        //then
        assertEquals(3541,hash);

    }

}