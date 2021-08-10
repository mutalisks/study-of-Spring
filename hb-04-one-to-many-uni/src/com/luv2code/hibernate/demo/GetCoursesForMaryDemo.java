package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class GetCoursesForMaryDemo {

    public static void main(String[] args){

        //create session factory
        SessionFactory factory = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(Instructor.class).
                addAnnotatedClass(InstructorDetail.class).
                addAnnotatedClass(Course.class).
                addAnnotatedClass(Review.class).
                addAnnotatedClass(Student.class).
                buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try{
            //start a transaction
            session.beginTransaction();
            //get the student John from database
            int studentId = 1;
            Student theStudent = session.get(Student.class,studentId);
            System.out.println("Loaded student: " + theStudent);
            System.out.println("Courses :" + theStudent.getCourses());

            //commit transaction
            session.getTransaction().commit();
            System.out.println("OK");
        }finally {
            //add clean up code
            session.close();
            factory.close();
        }

    }
}
