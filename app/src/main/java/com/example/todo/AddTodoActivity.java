package com.example.todo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddTodoActivity extends AppCompatActivity {
    private EditText etName, etEndTime, etRemindTime;  // 移除etStartTime
    private Calendar calendar;
    private SimpleDateFormat dateTimeFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        // 初始化控件（移除etStartTime）
        etName = findViewById(R.id.et_name);
        etEndTime = findViewById(R.id.et_end_time);
        etRemindTime = findViewById(R.id.et_remind_time);
        Button btnSave = findViewById(R.id.btn_save);

        // 初始化返回按钮
        ImageView ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(v -> finish());

        // 初始化日期时间格式
        calendar = Calendar.getInstance();
        dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());

        // 设置时间选择点击事件（移除startTime相关）
        etEndTime.setOnClickListener(v -> showDateTimePicker(etEndTime));
        etRemindTime.setOnClickListener(v -> showDateTimePicker(etRemindTime));

        // 保存按钮点击事件
        btnSave.setOnClickListener(v -> saveTodo());
    }

    // 显示日期时间选择器（保持不变）
    private void showDateTimePicker(EditText editText) {
        new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            new TimePickerDialog(this, (view1, hourOfDay, minute) -> {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                editText.setText(dateTimeFormat.format(calendar.getTime()));
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    // 保存待办事项（自动生成创建时间）
    private void saveTodo() {
        String name = etName.getText().toString().trim();
        String endTime = etEndTime.getText().toString().trim();
        String remindTime = etRemindTime.getText().toString().trim();
        // 自动生成当前时间作为创建时间
        String createTime = dateTimeFormat.format(Calendar.getInstance().getTime());

        if (name.isEmpty()) {
            etName.setError("请输入事项名称");
            return;
        }

        // 创建Todo对象（使用createTime）
        Todo newTodo = new Todo(
                System.currentTimeMillis(),
                name,
                false,
                createTime,  // 传入自动生成的创建时间
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