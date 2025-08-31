package com.example.todo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TodoAdapter.OnTodoActionListener {

    private TodoAdapter mAdapter;
    private List<Todo> mTodoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化数据
        mTodoList = new ArrayList<>();
        mTodoList.add(new Todo(1, "学习Android开发", false));
        mTodoList.add(new Todo(2, "完成代办事项组件", false));
        mTodoList.add(new Todo(3, "提交代码", false));

        // 初始化RecyclerView
        RecyclerView rvTodo = findViewById(R.id.rv_todo);
        rvTodo.setLayoutManager(new LinearLayoutManager(this)); // 线性布局
        mAdapter = new TodoAdapter(mTodoList, this); // 绑定适配器
        rvTodo.setAdapter(mAdapter);
    }

    // 处理复选框状态变化
    @Override
    public void onCheckChanged(Todo todo) {
        // 这里可以更新数据到数据库或SharedPreferences
        mAdapter.notifyDataSetChanged(); // 刷新列表
    }

    // 处理删除事件
    @Override
    public void onDelete(Todo todo) {
        mTodoList.remove(todo); // 从列表中移除
        mAdapter.notifyDataSetChanged(); // 刷新列表
    }
}