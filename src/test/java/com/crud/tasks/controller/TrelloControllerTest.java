package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.trello.facade.TrelloFacade;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.jsonpath.spi.json.GsonJsonProvider;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(TrelloController.class)
class TrelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrelloFacade trelloFacade;

    @Test
    void shouldFetchEmptyTrelloBoards2() throws Exception {
        //given
        when(trelloFacade.fetchTrelloBoards()).thenReturn(List.of());
        //when&then
        mockMvc.perform(
                MockMvcRequestBuilders.get("/v1/trello/getTrelloBoards")
                .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.jsonPath("$",Matchers.hasSize(0)))
                    .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void shouldFetchTrelloBoards2() throws Exception {
        //given
        TrelloListDto listDto = new TrelloListDto("idL", "nameL", true);
        List<TrelloListDto> listOfTrelloListDto = List.of(listDto);
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("idB", "nameB", listOfTrelloListDto);
        when(trelloFacade.fetchTrelloBoards()).thenReturn(List.of(trelloBoardDto));
        //when&then
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/v1/trello/getTrelloBoards")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$",Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id",Matchers.is("idB")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name",Matchers.is("nameB")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lists",Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lists[0].id",Matchers.is("idL")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lists[0].name",Matchers.is("nameL")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lists[0].closed",Matchers.is(true)));
    }

    @Test
    void shouldCreateTrelloCard2() throws Exception {
        //given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name", "desc", "pos", "idList");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("id", "name", "www.url.com/url");
        when(trelloFacade.createCard(any(TrelloCardDto.class))).thenReturn(createdTrelloCardDto);
        Gson gson = new Gson();
        String requestBody = gson.toJson(trelloCardDto);
        //when&then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/trello/createTrelloCard")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id",Matchers.is("id")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name",Matchers.is("name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.shortUrl",Matchers.is("www.url.com/url")));
    }





    @Test
    void shouldFetchEmptyTrelloBoards() throws Exception {
        // Given
        when(trelloFacade.fetchTrelloBoards()).thenReturn(List.of());
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/trello/getTrelloBoards")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));

    }

    @Test
    void shouldFetchTrelloBoards() throws Exception {
        // Given
        List<TrelloListDto> trelloLists = List.of(new TrelloListDto("1", "Test list", false));
        List<TrelloBoardDto> trelloBoards = List.of(new TrelloBoardDto("1", "Test Task", trelloLists));
        when(trelloFacade.fetchTrelloBoards()).thenReturn(trelloBoards);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/trello/getTrelloBoards")
                        .contentType(MediaType.APPLICATION_JSON))
                // Trello board fields
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is("1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("Test Task")))
                // Trello list fields
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lists", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lists[0].id", Matchers.is("1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lists[0].name", Matchers.is("Test list")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lists[0].closed", Matchers.is(false)));
    }

    @Test
    void shouldCreateTrelloCard() throws Exception {
        // Given
        TrelloCardDto trelloCardDto =
                new TrelloCardDto("Test", "Test description", "top", "1");

        CreatedTrelloCardDto createdTrelloCardDto =
                new CreatedTrelloCardDto("232", "Test", "http://test.com");

        when(trelloFacade.createCard(any(TrelloCardDto.class))).thenReturn(createdTrelloCardDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(trelloCardDto);
        //when&then
        mockMvc.perform(MockMvcRequestBuilders
        .post("/v1/trello/createTrelloCard")
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding("UTF-8")
        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is("232")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.shortUrl", Matchers.is("http://test.com")));

    }

}