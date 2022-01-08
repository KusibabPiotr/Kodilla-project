package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskMapper taskMapper;

    @MockBean
    private DbService dbService;

    @Test
    void shouldFetchTaskDtoList() throws Exception {
        //given
        TaskDto taskDto = new TaskDto(1L, "title", "content");
        List<TaskDto> tasksDto = List.of(taskDto);
        Task task = new Task(1L, "title", "content");
        List<Task> tasks = List.of(task);
        when(dbService.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(tasksDto);
        //when&then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/task/getTasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title",Matchers.is("title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content",Matchers.is("content")));


    }

    @Test
    void shouldFetchTaskDto() throws Exception {
        //given
        TaskDto taskDto = new TaskDto(1L, "title", "content");
        Task task = new Task(1L, "title", "content");
        when(dbService.getTask(1L)).thenReturn(java.util.Optional.of(task));
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        //when&then
        mockMvc.perform(MockMvcRequestBuilders
                    .get("/v1/task/getTask")
                    .queryParam("id","1")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id",Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title",Matchers.is("title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content",Matchers.is("content")));
    }

    @Test
    void shouldCreateTask() throws Exception {
        //given
        TaskDto dto = new TaskDto(1L, "title", "content");
        Task task = new Task(1L, "title", "content");

        when(taskMapper.mapToTask(any(TaskDto.class))).thenReturn(task);
        when(dbService.createTask(any(Task.class))).thenReturn(task);
        when(taskMapper.mapToTaskDto(any(Task.class))).thenReturn(dto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(task);
        //when&then
        mockMvc.perform(MockMvcRequestBuilders
                    .post("/v1/task/createTask")
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8")
                    .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("content")));

    }

    @Test
    void shouldUpdateTask() throws Exception {
        //given
        TaskDto dto = new TaskDto(1L, "title", "content");
        Task task = new Task(1L, "title", "content");

        when(taskMapper.mapToTask(any(TaskDto.class))).thenReturn(task);
        when(dbService.createTask(any(Task.class))).thenReturn(task);
        when(taskMapper.mapToTaskDto(any(Task.class))).thenReturn(dto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(dto);
        //when&then
        mockMvc.perform(MockMvcRequestBuilders
                    .put("/v1/task/updateTask")
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8")
                    .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("content")));

    }

    @Test
    void shouldDeleteTask() throws Exception {
        //given
        doNothing().when(dbService).deleteTask(1L);
        //when&then
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/v1/task/deleteTask")
                .queryParam("id","1")
                .characterEncoding("UTF-8"))
                .andDo(MockMvcResultHandlers.log());
    }

}