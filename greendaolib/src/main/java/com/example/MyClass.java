package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyClass {

    public static void main(String[] args) throws Exception {
        int version = 3;
        String defaultPackage = "greendao.entity";

        //这个greendao.entity是不是表示在主工程app的java-gen下创建greendao文件夹?  答: 是的
        //在greendao文件夹下又创建entity文件夹来存放生成的bean? 答: 是的
        //如果不移动生成的bean到Java目录下, 主工程可不可以直接引用这些bean? 答: 可以, 导包像这样:import greendao.entity.Student;
//        Schema schema = new Schema(version, "greendao.entity");
        Schema schema = new Schema(version, "com.plq.demoapp.greendao.entity");

        // 模式（Schema）同时也拥有两个默认的 flags，分别用来标示 entity 是否是 activie 以及是否使用 keep sections。
        schema.enableActiveEntitiesByDefault();
        schema.enableKeepSectionsByDefault();

        //greendao.dao是不是表示在greendao文件夹下又创建dao文件夹来存放生成的dao? 答: 是的
//        schema.setDefaultJavaPackageDao("greendao.dao");
        schema.setDefaultJavaPackageDao("com.plq.demoapp.greendao.dao");

        addStudentEntity(schema);
        addMessageEntity(schema);

        String outDir = "C:/Users/ygl_panpan/Desktop/workspaces/DemoApp/app/src/main/java-gen";

        new DaoGenerator().generateAll(schema, outDir);
    }

    private static Entity addStudentEntity(Schema schema) {
        //添加一个实体，则会自动生成实体Entity类
        Entity entity = schema.addEntity("Student");
        //指定表名，如不指定，表名则为 Entity（即实体类名）
        entity.setTableName("Students");
        //给实体类中添加属性（即给test表中添加字段）
        entity.addIdProperty().autoincrement();//添加Id,自增长
        entity.addStringProperty("name").notNull();//添加String类型的name,不能为空
        entity.addIntProperty("age");//添加Int类型的age
        entity.addStringProperty("comment");//添加Stirng类型的comment
        entity.addStringProperty("addStr");//addStr
        entity.addDoubleProperty("score");//添加Double的score

        return entity;
    }

    private static Entity addMessageEntity(Schema schema) {
        //添加一个实体，则会自动生成实体Entity类
        Entity entity = schema.addEntity("Message");
        //指定表名，如不指定，表名则为 Entity（即实体类名）
        entity.setTableName("Message");
        //给实体类中添加属性（即给test表中添加字段）
        entity.addIdProperty().autoincrement();//添加Id,自增长
        entity.addStringProperty("code").notNull();//添加String类型的name,不能为空
        entity.addStringProperty("msg").notNull();//添加Int类型的age

        return entity;
    }

}
