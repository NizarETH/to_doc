package com.cleanup.todoc;




import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import android.content.Context;


import com.cleanup.todoc.dao.ProjectDAO;
import com.cleanup.todoc.dao.TaskDAO;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.repository.TaskRepository;
import com.cleanup.todoc.repository.ProjectRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import utils.AppDatabase;

public class TestTaskDao {
    private TaskDAO dbTest;
    private List<Task> tasks = new ArrayList<>();
    private TaskRepository taskRepository;

    Project project = new Project(1, "Projet Tartampion", 0xFFEADAD1);
    Project project1 = new Project(2, "Projet Lucidia", 0xFFEADAD1);
    Project project2 =new Project(3L, "Projet Circus", 0xFFA3CED2);

    Task task1 = new Task(1,1,"Task 1", System.currentTimeMillis());
    Task task2 = new Task(2,2,"Task 2", System.currentTimeMillis());
    Task task3 = new Task(3,1,"Task 3", System.currentTimeMillis());

    //    tasks.add(task1);
    //    tasks.add(task2);
    //    tasks.add(task3);

    @Before
    public void TaskRepository(Context context) { dbTest = AppDatabase.getDatabase(context).taskDAO();
    }

    @Test
    public void  baseVideTaskTest(){
        taskRepository.insertAllTask(tasks);
        assertTrue(tasks.isEmpty());
    }

    @Test
    public void  insertTaskTest(){
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        tasks=taskRepository.getAllTasks();
        assertEquals(3, tasks.size());
    }

    @Test
    public void  deleteTaskTest(){
        taskRepository.deleteTask(task1);
        tasks=taskRepository.getAllTasks();
        assertEquals(2, tasks.size());
    }

    @Test
    public void  updateTaskTest(){
         task1=taskRepository.getTaskById((int) task2.getId());
        assertEquals("Task 2",task1.getName());

         task1.setName("Task 1");
         taskRepository.updateTask(task1);
         task1=taskRepository.getTaskById((int) task1.getId());
        assertEquals("Task 1",task1.getName());
    }
    

}
