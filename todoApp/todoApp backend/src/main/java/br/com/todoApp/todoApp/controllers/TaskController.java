package br.com.todoApp.todoApp.controllers;

import br.com.todoApp.todoApp.dtos.TaskDto;
import br.com.todoApp.todoApp.models.TaskModel;
import br.com.todoApp.todoApp.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/todos")
public class TaskController {
    final TaskService taskService;
    
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    
    @PostMapping
    public ResponseEntity<Object> saveTask(@RequestBody @Valid TaskDto taskDto) {
        var taskModel = new TaskModel();
        BeanUtils.copyProperties(taskDto, taskModel);
        taskModel.setCreatedAt(LocalDate.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.save(taskModel));
    }
    
    @GetMapping
    public ResponseEntity<List<TaskModel>> getAllTask() {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.findAll());
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<TaskModel>> searchTasks(
            @RequestParam(value = "description__regex") String description) {
        List<TaskModel> searchResults = taskService.search(description);
        return ResponseEntity.status(HttpStatus.OK).body(searchResults);
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneTask(@PathVariable(value = "id") UUID id) {
        Optional<TaskModel> taskModelOptional = taskService.findById(id);
        return taskModelOptional.
                <ResponseEntity<Object>>map(taskModel -> ResponseEntity.status(HttpStatus.OK)
                .body(taskModel)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Task not found."));
        
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOneTask(@PathVariable(value = "id") UUID id) {
        Optional<TaskModel> taskModelOptional = taskService.findById(id);
        if (taskModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found.");
        }
        
        taskService.delete(taskModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Task deleted successfully.");
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTask(@PathVariable(value = "id") UUID id,
                                             @RequestBody @Valid TaskDto taskDto) {
        Optional<TaskModel> taskModelOptional = taskService.findById(id);
        if (taskModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found.");
        }
        var taskModel = taskModelOptional.get();
        BeanUtils.copyProperties(taskDto, taskModel);
        taskModel.setId(taskModelOptional.get().getId());
        taskModel.setCreatedAt(taskModelOptional.get().getCreatedAt());
        return ResponseEntity.status(HttpStatus.OK).body(taskService.save(taskModel));
    }
}
