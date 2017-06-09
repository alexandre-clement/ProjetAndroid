package fr.unice.polytech.tobeortohave.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexandre Clement
 *         Created the 12/05/2017.
 */

public class Database extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 8;
    private static final String DATABASE_NAME = "database.db";
    private static final String SQL_CREATE_ITEM = "CREATE TABLE " + ItemEntry.TABLE_NAME + " (" +
            ItemEntry._ID + " INTEGER PRIMARY KEY," +
            ItemEntry.COLUMN_NAME + " TEXT," +
            ItemEntry.COLUMN_URL + " TEXT," +
            ItemEntry.COLUMN_DESCRIPTION + " TEXT," +
            ItemEntry.COLUMN_CATEGORY + " TEXT," +
            ItemEntry.COLUMN_PRICE + " FLOAT)";

    private static final String SQL_CREATE_STAFF = "CREATE TABLE " + StaffEntry.TABLE_NAME + " (" +
            StaffEntry._ID + " INTEGER PRIMARY KEY," +
            StaffEntry.COLUMN_NAME + " TEXT," +
            StaffEntry.COLUMN_PHOTO + " TEXT," +
            StaffEntry.COLUMN_JOB + " TEXT," +
            StaffEntry.COLUMN_OPEN + " TEXT, " +
            StaffEntry.COLUMN_CLOSE + " TEXT)";

    private static final String SQL_CREATE_COMMAND = "CREATE TABLE " + CommandEntry.TABLE_NAME + " (" +
            CommandEntry._ID + " INTEGER PRIMARY KEY," +
            CommandEntry.COLUMN_STATE + " TEXT," +
            CommandEntry.COLUMN_CLIENT + " TEXT," +
            CommandEntry.COLUMN_DATE + " TEXT," +
            CommandEntry.COLUMN_STATUS + " TEXT)";


    private static final String SQL_DELETE_ITEM = "DROP TABLE IF EXISTS " + ItemEntry.TABLE_NAME;
    private static final String SQL_DELETE_STAFF = "DROP TABLE IF EXISTS " + StaffEntry.TABLE_NAME;
    private static final String SQL_DELETE_COMMAND = "DROP TABLE IF EXISTS " + CommandEntry.TABLE_NAME;

    public Database(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(SQL_CREATE_ITEM);
        db.execSQL(SQL_CREATE_STAFF);
        db.execSQL(SQL_CREATE_COMMAND);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(SQL_DELETE_ITEM);
        db.execSQL(SQL_DELETE_STAFF);
        db.execSQL(SQL_DELETE_COMMAND);
        onCreate(db);
    }

    public List<Item> getItems()
    {
        final List<Item> articles = new ArrayList<>();
        final Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM item", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast())
        {
            articles.add(new Item(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getDouble(5)));
            cursor.moveToNext();
        }
        cursor.close();
        return articles;
    }

    public List<Staff> getStaff()
    {
        final List<Staff> staff = new ArrayList<>();
        final Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM staff", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast())
        {
            staff.add(new Staff(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5)));
            cursor.moveToNext();
        }
        cursor.close();
        return staff;
    }

    public List<Command> getCommand()
    {
        final List<Command> commands = new ArrayList<>();
        final Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM command", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast())
        {
            commands.add(new Command(Command.State.valueOf(cursor.getString(1)), cursor.getString(2), cursor.getString(3), cursor.getString(4)));
            cursor.moveToNext();
        }
        cursor.close();
        return commands;
    }

    public static class ItemEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "item";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_URL = "url";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_CATEGORY = "category";
        public static final String COLUMN_PRICE = "price";
    }

    public static class StaffEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "staff";
        public static final String COLUMN_PHOTO = "photo";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_JOB = "job";
        public static final String COLUMN_OPEN = "open";
        public static final String COLUMN_CLOSE = "close";
    }

    public static class CommandEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "command";
        public static final String COLUMN_STATE = "state";
        public static final String COLUMN_CLIENT = "client";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_STATUS = "status";
    }
}
