package android.example.todoapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {TodoItem.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DAO todoItemDAO();
}
