package com.github.kacperroman.taskmanager.service;

import com.github.kacperroman.taskmanager.model.Task;
import com.github.kacperroman.taskmanager.model.User;
import com.github.kacperroman.taskmanager.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    void shouldReturnTasksForAuthenticatedUser() {
        User user = new User(1, "kacper", "secret", "kacper@example.com");

        List<Task> tasks = List.of(new Task("title", "description", user));
        when(taskRepository.findByUser(user)).thenReturn(tasks);

        List<Task> result = taskService.getTasksForUser(user);

        assertSame(tasks, result);
        verify(taskRepository).findByUser(user);
    }

    @Test
    void shouldDelegateTaskCreationToService() {
        User user = new User(1, "kacper", "secret", "kacper@example.com");

        Task task = new Task("title", "description", user);
        when(taskRepository.save(task)).thenReturn(task);

        Task result = taskService.addTask(task, user);

        assertSame(task, result);
        assertSame(user, task.getUser());
        verify(taskRepository).save(task);
    }

    @Test
    void shouldDelegateTaskDeletionToService() {
        User user = new User(1, "kacper", "secret", "kacper@example.com");
        Task task = new Task("title", "description", user);
        task.setId(10);

        when(taskRepository.findById(10)).thenReturn(Optional.of(task));

        taskService.deleteTask(10, user);

        verify(taskRepository).findById(10);
        verify(taskRepository).delete(task);
    }

    @Test
    void shouldRejectDeletingSomeoneElsesTask() {
        User owner = new User(1, "owner", "secret", "owner@example.com");
        User otherUser = new User(2, "other", "secret", "other@example.com");
        Task task = new Task("title", "description", owner);
        task.setId(11);

        when(taskRepository.findById(11)).thenReturn(Optional.of(task));

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> taskService.deleteTask(11, otherUser)
        );

        assertEquals("You can only delete your own tasks", exception.getMessage());
        verify(taskRepository).findById(11);
        verify(taskRepository, never()).delete(task);
    }
}