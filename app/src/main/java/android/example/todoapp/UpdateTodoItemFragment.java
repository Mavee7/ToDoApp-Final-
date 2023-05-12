package android.example.todoapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class UpdateTodoItemFragment extends DialogFragment {

    // Define member variables
    private EditText editItemTitle;
    private TodoItem item;

    // Empty constructor
    public UpdateTodoItemFragment() {
    }

    // Constructor that returns a new instance of UpdateTodoItemFragment
    public static UpdateTodoItemFragment newInstance(String title, TodoItem todoItem) {
        UpdateTodoItemFragment frag = new UpdateTodoItemFragment();

        // Create a bundle to hold arguments
        Bundle args = new Bundle();
        args.putString("title", title);

        // Set the TodoItem as an argument in the bundle
        frag.setArguments(args);
        frag.setTodoItem(todoItem);
        frag.setRetainInstance(true);
        return frag;
    }

    // Set the TodoItem
    public void setTodoItem(TodoItem item) {
        this.item = item;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the fragment view
        return inflater.inflate(R.layout.activity_update_todo_item, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get the EditText and load the TodoItem
        editItemTitle = view.findViewById(R.id.updateTextTitle);
        loadItem(item);
        editItemTitle.requestFocus();

        // Get the update button and close button
        Button updateButton = view.findViewById(R.id.updateButton);
        ImageView closeBtn = view.findViewById(R.id.closeBtn);

        // Set the click listener for the update button
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Update the TodoItem and dismiss the dialog
                updateItem(item);
                dismiss();
            }
        });

        // Set the click listener for the close button
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Dismiss the dialog
                dismiss();
            }
        });

        // Set the dialog window properties
        Dialog dialog = getDialog();
        dialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    // Load the TodoItem
    private void loadItem(TodoItem todoItem) {
        editItemTitle.setText(todoItem.getItem());
    }

    // Update the TodoItem
    private void updateItem(final TodoItem todoItem) {
        final String itemText = editItemTitle.getText().toString().trim();

        // Validate the input
        if (itemText.isEmpty()) {
            editItemTitle.setError("Item is required!");
            editItemTitle.requestFocus();
            return;
        }

        // Update the TodoItem and update the view model
        todoItem.setItem(itemText);
        TodoItemViewModel todoItemViewModel = new ViewModelProvider(this).get(TodoItemViewModel.class);
        todoItemViewModel.update(todoItem);
    }
}
