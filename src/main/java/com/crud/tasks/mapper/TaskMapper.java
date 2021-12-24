package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskMapper {

    public Task map(final TaskDto taskDto){
        return Task.builder()
                .id(taskDto.getId())
                .title(taskDto.getTitle())
                .content(taskDto.getContent())
                .build();
    }

    public TaskDto mapDto(final Task task){
        return TaskDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .content(task.getContent())
                .build();
    }

    public List<TaskDto> mapToListDto(final List<Task> tasks){
        return tasks.stream()
                .map(this::mapDto)
                .collect(Collectors.toList());
    }
}
