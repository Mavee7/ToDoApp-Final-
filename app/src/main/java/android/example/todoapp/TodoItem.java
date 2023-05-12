package android.example.todoapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.io.Serializable;

@Entity // Marks the class as an entity for Room database.
public class TodoItem implements Serializable {
    @PrimaryKey(autoGenerate = true) // Specifies the primary key of the entity, and also sets it to auto-generate.
    private int id;
    @ColumnInfo(name = "item") // Specifies the column name in the database table.
    private String item;
    @ColumnInfo(name = "isSelected", defaultValue = "false") // Specifies the column name in the database table, and sets its default value.
    @NonNull // Specifies that the field cannot be null.
    private boolean isSelected;

    public int getId() { // Getter for the ID field.
        return id;
    }

    public void setId(int id) { // Setter for the ID field.
        this.id = id;
    }

    public String getItem() { // Getter for the item field.
        return item;
    }

    public void setItem(String item) { // Setter for the item field.
        this.item = item;
    }

    public boolean getIsSelected() { // Getter for the isSelected field.
        return isSelected;
    }

    public void setIsSelected(boolean selected) { // Setter for the isSelected field.
        this.isSelected = selected;
    }
}
