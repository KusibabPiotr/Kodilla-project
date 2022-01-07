package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.exceptions.TaskNotFoundException;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DbServiceTest {

    @InjectMocks
    private DbService dbService;
    @Mock
    private TaskRepository taskRepository;

    @Test
    void testGetAllTasksReturnTasks(){
        //given
        Task task = new Task(1L, "task", "task");
        List<Task> list = List.of(task);
        when(taskRepository.findAll()).thenReturn(list);
        //when
        List<Task> allTasks = dbService.getAllTasks();
        //then
        assertEquals(1,allTasks.size());
    }

    @Test
    void testGetAllTasksReturnEmptyList(){
        //given
        when(taskRepository.findAll()).thenReturn(new ArrayList<>());
        //when
        List<Task> allTasks = dbService.getAllTasks();
        //then
        assertEquals(0,allTasks.size());
    }

    @Test
    void testGetTaskReturnTask(){
        //given
        Task task = new Task(1L, "task", "task");
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        //when
        Optional<Task> optionalTask = dbService.getTask(1L);
        //then
        assertTrue(optionalTask.isPresent());
        assertEquals(1L,optionalTask.get().getId());
    }

    @Test
    void testGetTaskReturnEmptyOptional(){
        //given
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());
        //when
        Optional<Task> optionalTask = dbService.getTask(1L);
        //then
        assertFalse(optionalTask.isPresent());
    }

    @Test
    void testCreateTask(){
        //given
        Task task = new Task(1L, "task", "task");
        when(taskRepository.save(task)).thenReturn(task);
        //when
        Task created = dbService.createTask(task);
        //then
        assertNotNull(created);
        assertEquals(1L,created.getId());
    }

    @Test
    void testUpdateTask(){
        //given
        Task updated = new Task(1L, "updated", "updated");
        Task task = new Task(1L, "task", "task");
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        //when
        Task afterUpdate = dbService.updateTask(updated);
        //then
        assertNotNull(afterUpdate);
        assertEquals(1L,afterUpdate.getId());
        assertEquals("updated",afterUpdate.getTitle());
        assertEquals("updated",afterUpdate.getContent());
    }

    @Test
    void testUpdateTaskThrowException(){
        //given
        //when&then
        assertThrows(TaskNotFoundException.class,() -> dbService.deleteTask(1L));
    }

    @Test
    void testDelateTaskTrue(){
        //given
        when(taskRepository.existsById(1L)).thenReturn(true);
        //when
        dbService.deleteTask(1L);
        //then
        verify(taskRepository,times(1)).deleteById(1L);
    }

    @Test
    void testDelateTaskThrowException(){
        //given
        when(taskRepository.existsById(1L)).thenReturn(false);
        //when&then
        assertThrows(TaskNotFoundException.class,()->dbService.deleteTask(1L));
    }

}