package com.plq.demoapp;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.plq.demoapp.greendao.dao.DaoMaster;
import com.plq.demoapp.greendao.dao.DaoSession;


/**
 * author：ygl_panpan on 2016/12/14 11:08
 * email：pan.lq@i70tv.com
 */
public class DemoApp extends Application {

    private static DemoApp demoApp;
    private SQLiteDatabase database;
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        demoApp = this;
        initDao();
    }

    private void initDao() {
        //创建数据库db_test?
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "db_test", null);
        database = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
    }

    public static DemoApp getInstance(){
        if(demoApp == null){
            demoApp = new DemoApp();
        }
        return demoApp;
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

}
