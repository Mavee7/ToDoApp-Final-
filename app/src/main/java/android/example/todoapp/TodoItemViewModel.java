package android.example.todoapp;

import android.app.Application;
import android.content.Context;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class TodoItemViewModel extends AndroidViewModel {
    private TodoItemRepo repo; // Declare a TodoItemRepo object to interact with the database
    private LiveData<List<TodoItem>> todoItemsList; // Declare a LiveData object to store the list of todo items

    public TodoItemViewModel(Application application) {
        super(application);
        repo = new TodoItemRepo(application); // Instantiate the TodoItemRepo object using the Application context
        todoItemsList = repo.getAllItems(); // Set the LiveData object to the list of todo items retrieved from the database
    }

    public LiveData<List<TodoItem>> getTodoItems(Context context) {
        return todoItemsList; // Return the LiveData object containing the list of todo items
    }

    public void insert(TodoItem item) {
        repo.insert(item); // Insert a new todo item into the database using the TodoItemRepo object
    }

    public void update(TodoItem item) {
        repo.update(item); // Update an existing todo item in the database using the TodoItemRepo object
    }

    public void delete(TodoItem item) {
        repo.delete(item); // Delete an existing todo item from the database using the TodoItemRepo object
    }
}
