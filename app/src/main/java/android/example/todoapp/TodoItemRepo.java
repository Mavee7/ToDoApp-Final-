package android.example.todoapp;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.Callable;

// Repository class for TodoItem entities
public class TodoItemRepo {

    // Context object to access resources and services
    private Context context;

    // Data Access Object for TodoItem entities
    private DAO todoItemDao;

    // LiveData object to observe TodoItem entities changes
    private LiveData<List<TodoItem>> todoItems;

    // Constructor to create a new instance of the repository
    public TodoItemRepo(Application application) {

        // Get database instance from DatabaseClient singleton
        AppDatabase database = DatabaseClient.getInstance(application).getAppDatabase();

        // Get DAO instance from database
        todoItemDao = database.todoItemDAO();

        // Initialize LiveData object with all TodoItem entities in the database
        todoItems = todoItemDao.getAll();
    }

    // Insert a new TodoItem entity
    public void insert(TodoItem item) {
        // Execute insert operation in a background thread using AsyncTask
        new InsertNoteAsyncTask(todoItemDao).execute(item);
    }

    // Update an existing TodoItem entity
    public void update(TodoItem item) {
        // Execute update operation in a background thread using AsyncTask
        new UpdateNoteAsyncTask(todoItemDao).execute(item);
    }

    // Delete an existing TodoItem entity
    public void delete(TodoItem item) {
        // Execute delete operation in a background thread using AsyncTask
        new DeleteNoteAsyncTask(todoItemDao).execute(item);
    }

    // Get all TodoItem entities
    public LiveData<List<TodoItem>> getAllItems() {
        return todoItems;
    }

    // AsyncTask for insert operation
    private static class InsertNoteAsyncTask extends AsyncTask<TodoItem, Void, Void> {
        private DAO todoItemDao;

        private InsertNoteAsyncTask(DAO todoItemDao) {
            this.todoItemDao = todoItemDao;
        }

        // Insert a TodoItem entity in a background thread
        @Override
        protected Void doInBackground(TodoItem... todoItems) {
            todoItemDao.insert(todoItems[0]);
            return null;
        }
    }

    // AsyncTask for update operation
    private static class UpdateNoteAsyncTask extends AsyncTask<TodoItem, Void, Void> {
        private DAO todoItemDao;

        private UpdateNoteAsyncTask(DAO todoItemDao) {
            this.todoItemDao = todoItemDao;
        }

        // Update a TodoItem entity in a background thread
        @Override
        protected Void doInBackground(TodoItem... todoItems) {
            todoItemDao.update(todoItems[0]);
            return null;
        }
    }

    // AsyncTask for delete operation
    private static class DeleteNoteAsyncTask extends AsyncTask<TodoItem, Void, Void> {
        private DAO todoItemDao;

        private DeleteNoteAsyncTask(DAO todoItemDao) {
            this.todoItemDao = todoItemDao;
        }

        // Delete a TodoItem entity in a background thread
        @Override
        protected Void doInBackground(TodoItem... todoItems) {
            todoItemDao.delete(todoItems[0]);
            return null;
        }
    }
}
