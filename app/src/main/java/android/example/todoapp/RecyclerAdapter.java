// import necessary libraries
package android.example.todoapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// define RecyclerAdapter class which extends RecyclerView.Adapter<MyViewHolder> class
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    Context context;
    List<TodoItem> todoItems;
    TodoItemViewModel viewModel;

    // constructor for RecyclerAdapter
    public RecyclerAdapter(Context context, List<TodoItem> todoItems, TodoItemViewModel model) {
        this.context = context;
        this.todoItems = todoItems;
        this.viewModel = model;
    }

    // inflate card_row layout file and create a new view holder
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_row, parent, false);
        return new MyViewHolder(view);
    }

    // update the todo item with the given position with the appropriate data from the todoItems list
    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        holder.todoItemCheckBox.setText(todoItems.get(position).getItem().toString());
        holder.todoItemCheckBox.setChecked(todoItems.get(position).getIsSelected());
        if (todoItems.get(position).getIsSelected())
            holder.todoItemCheckBox.setPaintFlags(holder.todoItemCheckBox.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        else
            holder.todoItemCheckBox.setPaintFlags(holder.todoItemCheckBox.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
    }

    // get the count of todo items in the todoItems list
    @Override
    public int getItemCount() {
        if (todoItems != null) {
            return todoItems.size();
        }
        return 0;
    }

    // create the view holder class
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        CheckBox todoItemCheckBox;

        // constructor for MyViewHolder class
        public MyViewHolder(View itemView) {
            super(itemView);
            // find the todo item check box view
            todoItemCheckBox = (CheckBox) itemView.findViewById(R.id.title);
            // set the checkbox to be unclickable
            todoItemCheckBox.setClickable(false);
            // set click and long click listeners on the item view
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        // handle click events on the todo item checkbox
        @Override
        public void onClick(View view) {
            TodoItem item = todoItems.get(getAdapterPosition());
            if (!item.getIsSelected())
                item.setIsSelected(true);
            else
                item.setIsSelected(false);
            viewModel.update(item);
        }

        // handle long click events on the todo item view
        @Override
        public boolean onLongClick(View view) {
            TodoItem item = todoItems.get(getAdapterPosition());
            // get the fragment manager for the activity
            FragmentManager fm = ((FragmentActivity) context).getSupportFragmentManager();
            // create a new update todo item fragment
            UpdateTodoItemFragment updateTodoItemFragment = UpdateTodoItemFragment.newInstance("Update Todo Item", item);
            // show the update todo item fragment
            updateTodoItemFragment.show(fm, "update_todo_item");
            return true;
        }
    }
}
