package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskMapperTest {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void testMapToTask(){
        //given
        TaskDto taskDto = new TaskDto(1L, "title", "content");
        //when
        Task task = taskMapper.mapToTask(taskDto);
        //then
        assertNotNull(task);
        assertEquals(1L,task.getId());
    }

    @Test
    public void testMapToTaskDto(){
        //given
        Task task = new Task(1L, "title", "content");
        //when
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //then
        assertNotNull(taskDto);
        assertEquals(1L,taskDto.getId());
    }

    @Test
    public void testMapToTaskDtoList(){
        //given
        Task task = new Task(1L, "title", "content");
        Task task2 = new Task(2L, "title", "content");
        List<Task> tasks = List.of(task, task2);
        //when
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(tasks);
        //then
        assertNotNull(taskDtoList);
        assertEquals(2,taskDtoList.size());

        taskDtoList.forEach(list -> {
            assertEquals("title",list.getTitle());
            assertEquals("content",list.getContent());
        });
    }


}