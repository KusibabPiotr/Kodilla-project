package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrelloServiceTest {

    @InjectMocks
    private TrelloService trelloService;
    @Mock
    private AdminConfig adminConfig;
    @Mock
    private TrelloClient trelloClient;
    @Mock
    private SimpleEmailService simpleEmailService;

    @Test
    void testFetchTrelloBoards(){
        //given
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("id", "name", new ArrayList<>());
        List<TrelloBoardDto> list = List.of(trelloBoardDto);
        when(trelloClient.getTrelloBoards()).thenReturn(list);
        //when
        List<TrelloBoardDto> dtoList = trelloService.fetchTrelloBoards();
        //then
        assertEquals(1,dtoList.size());
    }

    @Test
    void testCreateTrelloCard(){
        //given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name", "desc", "pos", "id");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("id", "name","test@short.pl");
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
        when(adminConfig.getAdminMail()).thenReturn("test@test.pl");
        //when
        CreatedTrelloCardDto created = trelloService.createTrelloCard(trelloCardDto);
        //then
        verify(simpleEmailService,times(1)).send(any());
        assertEquals("name",created.getName());
    }
}