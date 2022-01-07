package com.crud.tasks.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AttachmentsByTypeTest {

    private final AttachmentsByType attachments = new AttachmentsByType();

    @Test
    void testGetTrello(){
        //given
        attachments.setTrello(new Trello());
        //when
        Trello trello = attachments.getTrello();
        //then
        assertNotNull(trello);
    }

    @Test
    void testGetString(){
        //given
        attachments.setTrello(new Trello());
        //when
        String string = attachments.toString();
        //then
        assertNotNull(string);
        assertEquals("AttachmentsByType(trello=Trello(board=0, card=0))",string);
    }

}