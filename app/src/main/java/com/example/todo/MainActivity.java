package com.example.todo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import com.example.todo.database.AppDatabase;
import com.example.todo.entity.Todo;

public class MainActivity extends AppCompatActivity implements TodoAdapter.OnTodoActionListener {

    // 在MainActivity中初始化数据库
    private AppDatabase db;
    private TodoAdapter mAdapter;
    private List<Todo> mTodoList = new ArrayList<>();
    private static final int ADD_TODO_REQUEST = 1;

    // 注册Activity结果监听器
    private final ActivityResultLauncher<Intent> addTodoLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Todo newTodo = (Todo) result.getData().getSerializableExtra("new_todo");
                    if (newTodo != null) {
                        // 将新待办事项保存到数据库，并更新UI
                        new Thread(() -> {
                            // 保存到数据库
                            long id = db.todoDao().insert(newTodo);
                            newTodo.setId((int) id); // 设置数据库生成的ID

                            // 在主线程更新UI
                            runOnUiThread(() -> {
                                mTodoList.add(newTodo);
                                mAdapter.notifyDataSetChanged();
                            });
                        }).start();
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化数据库
        db = AppDatabase.getInstance(this);

        // 加载数据（需要在子线程执行）
        new Thread(() -> {
            List<Todo> dbTodos = db.todoDao().getAll();
            runOnUiThread(() -> {
                mTodoList.clear(); // 清空原有列表
                mTodoList.addAll(dbTodos); // 添加数据库数据
                mAdapter.notifyDataSetChanged(); // 通知适配器更新
            });
        }).start();

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
        // 更新待办事项的完成状态到数据库
        new Thread(() -> {
            db.todoDao().update(todo);
        }).start();
    }

    @Override
    public void onDelete(Todo todo) {
        // 从数据库删除待办事项，并更新列表
        new Thread(() -> {
            db.todoDao().delete(todo);
            // 从列表中移除并刷新UI
            mTodoList.remove(todo);
            runOnUiThread(() -> mAdapter.notifyDataSetChanged());
        }).start();
    }
}