package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.exceptions.NoSuchTaskException;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskMapper taskMapper;
    private final DbService dbService;

    @GetMapping
    public List<TaskDto> getTasks(){
        return taskMapper.mapToListDto(dbService.getTasks());
    }

    @GetMapping(value = "/{id}")
    public TaskDto getTask(@PathVariable long id) throws NoSuchTaskException {
        return taskMapper.mapDto(dbService.getTask(id)
                .orElseThrow(() -> new NoSuchTaskException("There is no task with given id: " + id)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody TaskDto taskDto){
        dbService.createTask(taskMapper.map(taskDto));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public TaskDto updateTask(@RequestBody TaskDto taskDto, @PathVariable long id) throws NoSuchTaskException {
        return taskMapper.mapDto(dbService.updateTask(taskMapper.map(taskDto),id));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteTask(@PathVariable long id){
        dbService.deleteTask(id);
    }
}
