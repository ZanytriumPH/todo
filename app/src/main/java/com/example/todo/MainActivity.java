package com.example.todo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TodoAdapter.OnTodoActionListener {

    private TodoAdapter mAdapter;
    private List<Todo> mTodoList;
    private static final int ADD_TODO_REQUEST = 1;

    // 注册Activity结果监听器
    private final ActivityResultLauncher<Intent> addTodoLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Todo newTodo = (Todo) result.getData().getSerializableExtra("new_todo");
                    if (newTodo != null) {
                        mTodoList.add(newTodo);
                        mAdapter.notifyDataSetChanged();
                    }
                }
            }
    );

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
        rvTodo.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new TodoAdapter(mTodoList, this);
        rvTodo.setAdapter(mAdapter);

        // 绑定加号按钮点击事件
        Button btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddTodoActivity.class);
            addTodoLauncher.launch(intent);
        });
    }

    @Override
    public void onCheckChanged(Todo todo) {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDelete(Todo todo) {
        mTodoList.remove(todo);
        mAdapter.notifyDataSetChanged();
    }
}