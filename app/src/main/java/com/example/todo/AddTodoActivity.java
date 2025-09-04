package com.example.todo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddTodoActivity extends AppCompatActivity {
    private EditText etName, etStartTime, etEndTime, etRemindTime;
    private Calendar calendar;
    private SimpleDateFormat dateTimeFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        // 初始化控件
        etName = findViewById(R.id.et_name);
        etStartTime = findViewById(R.id.et_start_time);
        etEndTime = findViewById(R.id.et_end_time);
        etRemindTime = findViewById(R.id.et_remind_time);
        Button btnSave = findViewById(R.id.btn_save);

        // 初始化日期时间格式
        calendar = Calendar.getInstance();
        dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());

        // 设置时间选择点击事件
        etStartTime.setOnClickListener(v -> showDateTimePicker(etStartTime));
        etEndTime.setOnClickListener(v -> showDateTimePicker(etEndTime));
        etRemindTime.setOnClickListener(v -> showDateTimePicker(etRemindTime));

        // 保存按钮点击事件
        btnSave.setOnClickListener(v -> saveTodo());
    }

    // 显示日期时间选择器
    private void showDateTimePicker(EditText editText) {
        // 日期选择
        new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            // 时间选择
            new TimePickerDialog(this, (view1, hourOfDay, minute) -> {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                editText.setText(dateTimeFormat.format(calendar.getTime()));
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    // 保存待办事项
    private void saveTodo() {
        String name = etName.getText().toString().trim();
        String startTime = etStartTime.getText().toString().trim();
        String endTime = etEndTime.getText().toString().trim();
        String remindTime = etRemindTime.getText().toString().trim();

        // 简单验证
        if (name.isEmpty()) {
            etName.setError("请输入事项名称");
            return;
        }

        // 创建Todo对象
        Todo newTodo = new Todo(
                System.currentTimeMillis(), // 使用时间戳作为ID
                name,
                false,
                startTime,
                endTime,
                remindTime
        );

        // 返回结果给MainActivity
        Intent intent = new Intent();
        intent.putExtra("new_todo", newTodo);
        setResult(RESULT_OK, intent);
        finish();
    }
}