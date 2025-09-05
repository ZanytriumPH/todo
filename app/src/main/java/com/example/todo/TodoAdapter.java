package com.example.todo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import com.example.todo.entity.Todo;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {

    // 数据集合
    private List<Todo> mTodoList;
    // 点击事件回调（处理删除、复选框状态变化）
    private OnTodoActionListener mListener;

    // 构造方法
    public TodoAdapter(List<Todo> todoList, OnTodoActionListener listener) {
        mTodoList = todoList;
        mListener = listener;
    }

    // 创建ViewHolder（加载Item布局）
    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_todo, parent, false);
        return new TodoViewHolder(view);
    }

    // 绑定数据到Item
    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        Todo todo = mTodoList.get(position);
        holder.tvContent.setText(todo.getContent());
        holder.cbTodo.setChecked(todo.isCompleted());

        // 复选框点击事件
        holder.cbTodo.setOnCheckedChangeListener((buttonView, isChecked) -> {
            todo.setCompleted(isChecked);
            mListener.onCheckChanged(todo);
        });

        // 删除按钮点击事件
        holder.btnDelete.setOnClickListener(v -> mListener.onDelete(todo));
    }

    // 数据数量
    @Override
    public int getItemCount() {
        return mTodoList.size();
    }

    // ViewHolder（缓存Item中的控件）
    public static class TodoViewHolder extends RecyclerView.ViewHolder {
        CheckBox cbTodo;
        TextView tvContent;
        Button btnDelete;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            cbTodo = itemView.findViewById(R.id.cb_todo);
            tvContent = itemView.findViewById(R.id.tv_content);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }

    // 事件回调接口（让外部处理逻辑）
    public interface OnTodoActionListener {
        void onCheckChanged(Todo todo); // 复选框状态变化
        void onDelete(Todo todo); // 删除事项
    }
}