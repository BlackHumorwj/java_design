package com.example.kotlin_demo.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.kotlin_demo.App
import com.example.kotlin_demo.objects.CityForecastTable
import com.example.kotlin_demo.objects.DayForecastTable
import org.jetbrains.anko.db.*

/**
 * @time 2019/10/17 15:34
 * @desc
 */
class ForecastDbHelper(ctx : Context = App.instance) : ManagedSQLiteOpenHelper(ctx, ForecastDbHelper.DB_NAME, null, ForecastDbHelper.DB_VERSION) {

    companion object {
        const val DB_NAME = "forecast.db"
        const val DB_VERSION = 1
        val instance by lazy {
            ForecastDbHelper()
        }
    }


    override fun onCreate(db: SQLiteDatabase?) {

        //创建表
        db?.createTable(CityForecastTable.NAME, true,
                CityForecastTable.ID to INTEGER + PRIMARY_KEY,
                CityForecastTable.CITY to TEXT,
                CityForecastTable.COUNTRY to TEXT
        )

        //创建表
        db?.createTable(DayForecastTable.NAME, true,
                DayForecastTable.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                DayForecastTable.CITY_ID to INTEGER,
                DayForecastTable.DESCRIPTION to TEXT,
                DayForecastTable.HIGH to INTEGER,
                DayForecastTable.LOW to INTEGER,
                DayForecastTable.ICON_URL to TEXT,
                DayForecastTable.CITY_ID to INTEGER)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(CityForecastTable.NAME)
        db?.dropTable(DayForecastTable.NAME)
        onCreate(db)
    }


}