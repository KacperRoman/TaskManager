package com.github.kacperroman.taskmanager.controller;

import com.github.kacperroman.taskmanager.model.Task;
import com.github.kacperroman.taskmanager.model.User;
import com.github.kacperroman.taskmanager.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    @Test
    void shouldReturnTasksForAuthenticatedUser() {
        User user = new User(1, "kacper", "secret", "kacper@example.com");

        List<Task> tasks = List.of(new Task("title", "description", user));
        when(taskService.getTasksForUser(user)).thenReturn(tasks);

        List<Task> result = taskController.getTasksForUser(user);

        assertSame(tasks, result);
        verify(taskService).getTasksForUser(user);
    }

    @Test
    void shouldDelegateTaskCreationToService() {
        User user = new User(1, "kacper", "secret", "kacper@example.com");

        Task task = new Task("title", "description", user);
        when(taskService.addTask(task, user)).thenReturn(task);

        Task result = taskController.addTask(task, user);

        assertSame(task, result);
        verify(taskService).addTask(task, user);
    }

    @Test
    void shouldDelegateTaskDeletionToService() {
        User user = new User(1, "kacper", "secret", "kacper@example.com");

        assertDoesNotThrow(() -> taskController.deleteTask(10, user));

        verify(taskService).deleteTask(10, user);
    }

}