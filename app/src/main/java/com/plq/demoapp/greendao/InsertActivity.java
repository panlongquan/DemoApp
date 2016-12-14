package com.plq.demoapp.greendao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.plq.demoapp.DemoApp;
import com.plq.demoapp.R;
import com.plq.demoapp.greendao.dao.StudentDao;
import com.plq.demoapp.greendao.entity.Student;
import com.self.toollibs.TextUtils;


public class InsertActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_age;
    private EditText et_name;
    private EditText et_score;
    private Button bt_insert;

    private String name;
    private int age;
    private double score;
    private StudentDao studentDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        studentDao = DemoApp.getInstance().getDaoSession().getStudentDao();
        initView();
        initControl();
    }

    private void initControl() {
        et_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                name = "name:"+editable.toString();
            }
        });
        et_age.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable != null) {
                    if (TextUtils.isEmpty(editable.toString())){
                        age = 0;
                    } else {
                        age = Integer.valueOf(editable.toString());
                    }
                } else {
                    age = 0;
                }
            }
        });
        et_score.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable != null) {
                    if (TextUtils.isEmpty(editable.toString())){
                        score = 0;
                    } else {
                        score = Double.valueOf(editable.toString());
                    }
                } else {
                    score = 0;
                }
            }
        });
        bt_insert.setOnClickListener(this);
    }

    private void initView() {
        et_age = (EditText) findViewById(R.id.et_age);
        et_name = (EditText) findViewById(R.id.et_name);
        et_score = (EditText) findViewById(R.id.et_score);
        bt_insert = (Button) findViewById(R.id.bt_insert);
    }

    @Override
    public void onClick(View view) {
        if (TextUtils.isEmpty(name)) {
            return;
        }
        studentDao.insert(new Student(name, age, score));

        // 插入多条数据
//        studentDao.insertInTx(list);
    }
}
