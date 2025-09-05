package com.example.todo.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todo.entity.Todo;

import java.util.List;

@Dao
public interface TodoDao {
    // 查询所有待办事项
    @Query("SELECT * FROM todo ORDER BY createTime DESC")
    List<Todo> getAll();

    // 根据ID查询
    @Query("SELECT * FROM todo WHERE id = :id")
    Todo getById(long id);

    // 插入待办事项
    @Insert
    long insert(Todo todo);

    // 更新待办事项
    @Update
    void update(Todo todo);

    // 删除待办事项
    @Delete
    void delete(Todo todo);
}