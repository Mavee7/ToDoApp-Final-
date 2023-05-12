package android.example.todoapp;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface DAO {
    @Query("SELECT * FROM TodoItem")
    LiveData<List<TodoItem>> getAll();
    @Insert
    void insert(TodoItem todoItem);
    @Delete
    void delete(TodoItem todoItem);
    @Update
    void update(TodoItem todoItem);
}
