package android.example.todoapp;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class AddTodoItemFragment extends DialogFragment {
    // Declare EditText view for the task title input
    private EditText editItemTitle;

    // Default constructor for the fragment
    public AddTodoItemFragment() {
    }

    // Static factory method to create a new instance of the fragment with a specified title
    public static AddTodoItemFragment newInstance(String title) {
        AddTodoItemFragment frag = new AddTodoItemFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }


    @Override
    // Inflate the layout for the fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_add_todo_item, container);
    }

    @Override
    // Initialize the views in the dialog and set up click listeners for the save and close buttons
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get a reference to the edit text view for the task title input
        editItemTitle = view.findViewById(R.id.editTextTitle);
        // Request focus for the edit text view so that the keyboard is shown automatically
        editItemTitle.requestFocus();
        // Get references to the save button and close button
        Button saveButton = view.findViewById(R.id.button_save);
        ImageView closeBtn = view.findViewById(R.id.closeBtn);
        // Set up a click listener for the save button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Call the saveTask method to save the task to the database and dismiss the dialog
                saveTask();
                dismiss();
            }
        });

        // Set up a click listener for the close button
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Dismiss the dialog without saving the task
                dismiss();
            }
        });

        // Get the dialog window and set the soft input mode and background color
        Dialog dialog = getDialog();
        dialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    // Method to save the task to the database
    private void saveTask() {
        // Get the text from the edit text view for the task title input
        final String item = editItemTitle.getText().toString().trim();

        // If the edit text view is empty, display an error message and request focus for the view
        if (item.isEmpty()) {
            editItemTitle.setError("Task is required!");
            editItemTitle.requestFocus();
            return;
        }

        // Create a new TodoItem object with the task title and insert it into the database using a TodoItemViewModel
        TodoItem todoItem = new TodoItem();
        todoItem.setItem(item);
        TodoItemViewModel todoItemViewModel = new ViewModelProvider(this).get(TodoItemViewModel.class);
        todoItemViewModel.insert(todoItem);
    }
}