package com.hibernate.dao;
import com.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Queue;

/**
 * @author :Mr.Xu
 * Date    :2019-4-8
 */
public class StudentJutilTest {

    private SessionFactory sessionFactory = null;
    private Session session = null;

    @Before
    public void doBefore(){

        sessionFactory = HibernateUtil.getsessionfactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
    }
    @After
    public void doAfter(){
        //提交事务
        session.getTransaction().commit();
        //关闭session
        session.close();
        sessionFactory.close();
    }



    @Test
    public void add(){
        Student student = new Student();
        student.setName("张三");
        student.setAge(22);
        session.save(student);
    }
    @Test
    public void update(){
        Student student = session.get(Student.class,1);
        student.setName("李四");
        student.setAge(25);
        session.save(student);
    }
    @Test
    public void delete(){
        Student student = session.get(Student.class,1);
        if (student!=null){
            session.delete(student);
        }
    }
    @Test
    public void query(){

        String hql ="from Student";
        Query query = session.createQuery(hql);
        List<Student> list = query.list();
        for (Student s :list) {
            System.out.println(s);
        }


    }



}
