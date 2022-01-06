package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrelloMapperTestSuite {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    void testMapToBoards(){
        //given
        TrelloBoardDto dto1 = new TrelloBoardDto("id", "name", new ArrayList<>());
        TrelloBoardDto dto2 = new TrelloBoardDto("id2", "name2", new ArrayList<>());
        List<TrelloBoardDto> dtos = List.of(dto1, dto2);
        //when
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(dtos);
        //then
        assertEquals(2,trelloBoards.size());
        assertEquals("id",trelloBoards.get(0).getId());
        assertEquals("id2",trelloBoards.get(1).getId());
    }

    @Test
    void testMapToBoardsDto(){
        //given
        TrelloBoard trelloBoard = new TrelloBoard("id", "name", new ArrayList<>());
        TrelloBoard trelloBoard2 = new TrelloBoard("id2", "name2", new ArrayList<>());
        List<TrelloBoard> boards = List.of(trelloBoard, trelloBoard2);
        //when
        List<TrelloBoardDto> trelloBoardDtos = trelloMapper.mapToBoardsDto(boards);
        //then
        assertEquals(2,trelloBoardDtos.size());
        assertEquals("id",trelloBoardDtos.get(0).getId());
        assertEquals("id2",trelloBoardDtos.get(1).getId());
    }

    @Test
    void testMapToList(){
        //given
        TrelloListDto trelloListDto = new TrelloListDto("id", "name", true);
        TrelloListDto trelloListDto2 = new TrelloListDto("id2", "name2", true);
        List<TrelloListDto> trelloListsDto = List.of(trelloListDto, trelloListDto2);
        //when
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListsDto);
        //then
        assertEquals(2,trelloLists.size());
        assertEquals("id",trelloLists.get(0).getId());
        assertEquals("id2",trelloLists.get(1).getId());
    }

    @Test
    void testMapToListDto(){
        //given
        TrelloList trelloList = new TrelloList("id", "name", true);
        TrelloList trelloList2 = new TrelloList("id2", "name2", true);
        List<TrelloList> trelloLists = List.of(trelloList, trelloList2);
        //when
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(trelloLists);
        //then
        assertEquals(2,trelloListDtos.size());
        assertEquals("id",trelloListDtos.get(0).getId());
        assertEquals("id2",trelloListDtos.get(1).getId());
    }

    @Test
    void mapToCardDto(){
        //given
        TrelloCard trelloCard = new TrelloCard("my", "desc", "top", "1");
        //when
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //then
        assertNotNull(trelloCardDto);
        assertEquals("my",trelloCardDto.getName());
    }

    @Test
    void mapToCard(){
        //given
        TrelloCardDto trelloCardDto = new TrelloCardDto("my", "desc", "top", "1");
        //when
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //then
        assertNotNull(trelloCard);
        assertEquals("my",trelloCard.getName());
    }

}