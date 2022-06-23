package com.example.exam5.controller;

import com.example.exam5.model.dto.AddTaskDTO;
import com.example.exam5.model.entity.ClassificationName;
import com.example.exam5.service.TaskService;
import com.example.exam5.user.CurrentUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Arrays;

@Controller
@AllArgsConstructor
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;
    private final CurrentUser currentUser;

    @GetMapping("/add")
    public String addTask(Model model) {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/login";
        }
        model.addAttribute("classificationNames", Arrays.stream(ClassificationName.values()).toList());
        return "add-task";
    }

    @ModelAttribute("addTaskDTO")
    public AddTaskDTO addTaskDTO() {
        return new AddTaskDTO();
    }

    @PostMapping("/add")
    public String addTask(@Valid AddTaskDTO addTaskDTO,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addTaskDTO", addTaskDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addTaskDTO", bindingResult);
            return "redirect:/task/add";
        }
        if (taskService.nameIsTaken(addTaskDTO.getName())) {
            redirectAttributes.addFlashAttribute("nameIsTaken", true);
            redirectAttributes.addFlashAttribute("addTaskDTO", addTaskDTO);
            return "redirect:/task/add";
        }
        taskService.add(addTaskDTO);
        return "redirect:/";
    }

    @PostMapping("/progress/{id}")
    public String progress(@PathVariable("id") Long id) {
        taskService.progress(id);
        return "redirect:/";
    }
}