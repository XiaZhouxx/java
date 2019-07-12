package cn.xz.annotation;

import java.io.Serializable;

/**
 * @author xz
 * @ClassName User
 * @Description
 * @date 2019/6/13 0013 21:46
 **/
public class User implements Serializable {

    @CustomAnnotation(name = "张三")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer age;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public User(){}

    public User(String name , Integer age){
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
