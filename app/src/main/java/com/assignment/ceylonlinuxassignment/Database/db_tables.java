package com.assignment.ceylonlinuxassignment.Database;

public class db_tables {

    static final String TABLE_NAME_EXPENSES = "EXPENSES";

    public  static final  String EXPENSES_ID   = "_id";
    static  final  String EXPENSES_TYPE        = "expenses_type";
    static  final  String EXPENSES_AMOUNT      = "expenses_amount";
    static  final  String EXPENSES_IMAGE       = "expenses_image";
    static  final  String EXPENSES_DATE        = "expenses_date";
    static  final  String EXPENSES_ADD_DATE    = "expenses_add_date";
    static  final  String EXPENSES_ADD_TIME    = "expenses_add_time";

    static final String CREATE_TABLE_EXPENSES = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_EXPENSES
            + " ( " +
            EXPENSES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            EXPENSES_TYPE + " TEXT, " +
            EXPENSES_AMOUNT + " TEXT," +
            EXPENSES_IMAGE + " TEXT," +
            EXPENSES_DATE + " TEXT," +
            EXPENSES_ADD_DATE + " TEXT,"+
            EXPENSES_ADD_TIME + " TEXT" +
            ");";

}
