package com.cleanup.todoc.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cleanup.todoc.model.Project;

import java.util.List;

 
@Dao
public interface ProjectDAO {

    @Insert
    void insertProject(Project project);

    @Query("Select * from Project")
    List<Project> getAllProjects();


    @Update
    int updateProject(Project project);

    @Delete
    void deleteProject(Project project);

}
