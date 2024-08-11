package com.example.intellipickcoaching

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHelper(context: Context?,name:String?,factory:SQLiteDatabase.CursorFactory?,version: Int)
    : SQLiteOpenHelper(context,name,factory,version) {

    //매개변수는 DB instance
    override fun onCreate(db: SQLiteDatabase?) {
        var sql : String = " CREATE TABLE IF NOT EXISTS PERSON( " +
                "id VARCHAR2(200) NOT NULL,"+
                "pw VARCHAR2(200) NOT NULL,"+
                "name VARCHAR2(20),"+
                "phone VARCHAR2(10),"+
                "email VARCHAR2(200)); "
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        var sql : String = " DROP TABLE IF EXISTS PERSON "
        db?.execSQL(sql)
        onCreate(db)
    }

    fun insert(db: SQLiteDatabase,id:String,pw:String,name:String,phone:String,email:String){
        var sql = " INSERT INTO PERSON(id,pw,name,phone,email) " +
                " VALUES('${id}','${pw}','${name}','${phone}','${email}')"
        db.execSQL(sql)

    }

    fun select(db: SQLiteDatabase, id:String,pw:String) : Person?{
        var sql = " SELECT * FROM PERSON " +
                "   WHERE id='${id}' "+
                "AND pw='${pw}'"
        var result = db.rawQuery(sql, null)

        var obj:Person? = Person(
            result.getString(result.getColumnIndexOrThrow("id")),
            result.getString(result.getColumnIndexOrThrow("pw")),
            result.getString(result.getColumnIndexOrThrow("name")),
            result.getString(result.getColumnIndexOrThrow("phone")),
            result.getString(result.getColumnIndexOrThrow("email")))
        result.close()
        return obj
    }

    fun delete(db:SQLiteDatabase,id:String){
        val sql = " DELETE FROM PERSON " +
                " WHERE id='${id}' "

        db.execSQL(sql)
    }


}