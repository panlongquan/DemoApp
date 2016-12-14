package com.plq.demoapp.greendao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.plq.demoapp.DemoApp;
import com.plq.demoapp.R;
import com.plq.demoapp.greendao.dao.StudentDao;
import com.plq.demoapp.greendao.entity.Student;

import java.util.List;

public class GreenDaoMainActivity extends AppCompatActivity implements View.OnClickListener {

    private StudentDao studentDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao_main);
        studentDao = DemoApp.getInstance().getDaoSession().getStudentDao();

        initView();
    }

    private void initView() {
        findViewById(R.id.bt_greendao_insert).setOnClickListener(this);
        findViewById(R.id.bt_greendao_delete).setOnClickListener(this);
        findViewById(R.id.bt_greendao_update).setOnClickListener(this);
        findViewById(R.id.bt_greendao_query).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_greendao_insert:
                insert();
                break;
            case R.id.bt_greendao_delete:
                delete();
                break;
            case R.id.bt_greendao_update:
                update();
                break;
            case R.id.bt_greendao_query:
                query();
                break;
        }
    }

    private void insert() {
        startActivity(new Intent(this, InsertActivity.class));
    }

    private void delete() {
//        studentDao.deleteAll();

        if (studentDao != null) {
            List<Student> students = studentDao.loadAll();
            if (students.size() == 0) {
                Log.i("plq", "数据库为空");
            } else {
                for (int i = 0; i < students.size(); i++) {
                    Student s = students.get(i);
                    if (s.getAge() == 20) {
                        s.delete();
                    }
                }
            }
        }
    }

    private void update() {
        studentDao.deleteAll();
    }

    private void query() {
        if (studentDao != null) {
            List<Student> students = studentDao.loadAll();
            if (students == null) {
                Log.i("plq", "数据库为空null");
            } else {
                if (students.size() == 0) {
                    Log.i("plq", "数据库为空:0.............................");
                } else {
                    for (int i = 0; i < students.size(); i++) {
                        Log.i("plq", "index:"+i+", "+students.get(i).toString());
                    }
                }
            }
        }
    }

}
