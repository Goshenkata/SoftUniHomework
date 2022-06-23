package com.example.exam5.service;

import com.example.exam5.model.dto.AddTaskDTO;
import com.example.exam5.model.dto.TaskInfoDTO;
import com.example.exam5.model.entity.Progress;
import com.example.exam5.model.entity.Task;
import com.example.exam5.repository.ClassificationRepository;
import com.example.exam5.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor

public class TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;
    private final ModelMapper mapper;
    private final ClassificationRepository classificationRepository;
    public boolean nameIsTaken(String name) {
        return taskRepository.existsByName(name);
    }

    public void add(AddTaskDTO addTaskDTO) {
        Task task = mapper.map(addTaskDTO, Task.class);
        task.setAssignedTo(userService.getCurrentUser());
        task.setClassification(classificationRepository.getClassificationByClassificationName(addTaskDTO.getClassificationName()));
        task.setProgress(Progress.OPEN);
        taskRepository.save(task);
    }

    public List<TaskInfoDTO> getAllTasks() {
        return taskRepository
                .findAll()
                .stream()
                .map(t -> mapper.map(t, TaskInfoDTO.class))
                .toList();
    }

    @Transactional
    public void progress(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("invalid task id"));
        if (task.getProgress() == Progress.OPEN) {
            task.setProgress(Progress.IN_PROGRESS);
            taskRepository.save(task);
        } else {
            taskRepository.delete(task);
        }
    }
}
