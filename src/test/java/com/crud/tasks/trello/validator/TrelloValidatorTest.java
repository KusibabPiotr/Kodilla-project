package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class TrelloValidatorTest {

    @Autowired
    private TrelloValidator trelloValidator;
    @Mock
    private TrelloValidator mockValidator;

    @Test
    void testValidateTrelloBoardsContainsWordTest() {
        //given
        TrelloBoard trelloBoard = new TrelloBoard("test", "test", new ArrayList<>());
        List<TrelloBoard> boards = List.of(trelloBoard);
        //when
        List<TrelloBoard> trelloBoards = trelloValidator.validateTrelloBoards(boards);
        //then
        assertEquals(0,trelloBoards.size());
    }

    @Test
    void testValidateTrelloBoardsNotContainsWordTest() {
        //given
        TrelloBoard trelloBoard = new TrelloBoard("id", "name", new ArrayList<>());
        List<TrelloBoard> boards = List.of(trelloBoard);
        //when
        List<TrelloBoard> trelloBoards = trelloValidator.validateTrelloBoards(boards);
        //then
        assertEquals(1,trelloBoards.size());
    }

    @Test
    void testValidateCardWasCalled(){
        //given
        TrelloCard trelloCard = new TrelloCard("name", "desc", "top", "id");
        //when
        mockValidator.validateCard(trelloCard);
        //then
        verify(mockValidator,times(1)).validateCard(trelloCard);
    }
}