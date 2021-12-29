package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.exceptions.ParamNotProvidedException;
import com.crud.tasks.exceptions.TaskNotFoundException;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskMapper taskMapper;
    private final DbService dbService;

    @GetMapping(value = "getTasks")
    public List<TaskDto> getTasks(){
        return taskMapper.mapToTaskDtoList(dbService.getAllTasks());
    }

    @GetMapping(value = "getTask")
    public TaskDto getTask(@RequestParam(required = false) Optional<Long> id)
            throws TaskNotFoundException, ParamNotProvidedException {
        Long provided = id.orElseThrow(ParamNotProvidedException::new);
        return taskMapper.mapToTaskDto(dbService.getTask(provided)
                .orElseThrow(() -> new TaskNotFoundException("There is no task with given id: " + id)));
    }

    @PostMapping(value = "createTask", consumes = MediaType.APPLICATION_JSON_VALUE)
    public TaskDto createTask(@RequestBody TaskDto taskDto){
        return taskMapper.mapToTaskDto(dbService.createTask(taskMapper.mapToTask(taskDto)));
    }

    @PutMapping(value = "updateTask", consumes = MediaType.APPLICATION_JSON_VALUE)
    public TaskDto updateTask(@RequestBody TaskDto taskDto,
                              @RequestParam(required = false) Optional<Long> id)
            throws TaskNotFoundException, ParamNotProvidedException {
        Long provided = id.orElseThrow(ParamNotProvidedException::new);
        return taskMapper.mapToTaskDto(dbService.updateTask(taskMapper.mapToTask(taskDto),provided));
    }

    @DeleteMapping(value = "deleteTask")
    public void deleteTask(@RequestParam(required = false) Optional<Long> id)
            throws ParamNotProvidedException {
        Long provided = id.orElseThrow(ParamNotProvidedException::new);
        dbService.deleteTask(provided);
    }
}
