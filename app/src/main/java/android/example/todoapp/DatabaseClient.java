package android.example.todoapp;

import androidx.room.Room;
import android.content.Context;

public class DatabaseClient {
    private Context context;                   // declaring a private variable context of type Context
    private static DatabaseClient mInstance;   // declaring a private static variable mInstance of type DatabaseClient
    private AppDatabase appDatabase;           // declaring a private variable appDatabase of type AppDatabase

    private DatabaseClient(Context context) {  // constructor with parameter context
        this.context = context;
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "TodoItems")  // creating an instance of AppDatabase using Room database builder with context, AppDatabase class and database name as parameters
                .fallbackToDestructiveMigration()   // allowing Room to destroy and recreate the database tables when migrations become too complex
                .build();                          // building and returning the instance of AppDatabase
    }

    public static synchronized DatabaseClient getInstance(Context context) {  // method to get the instance of DatabaseClient with synchronized access
        if (mInstance == null) {
            mInstance = new DatabaseClient(context);  // if mInstance is null then create a new instance of DatabaseClient with the context
        }
        return mInstance;  // otherwise return the existing instance of DatabaseClient
    }

    public AppDatabase getAppDatabase() {   // method to get the instance of AppDatabase
        return appDatabase;
    }
}
