package com.craft;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class App {

    public static EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("studentPU");

    // Create an EntityManager
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // CREATE: Add a student
    public static void createStudent(Student student) {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();

            System.out.println("Student created successfully!");
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    // READ: Get all students
    public static List<Student> getAllStudents() {
        EntityManager em = getEntityManager();

        try {
            TypedQuery<Student> query = em.createQuery(
                    "SELECT s FROM Student s", Student.class);

            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // READ: Get one student by ID
    public static Student getStudentById(int id) {
        EntityManager em = getEntityManager();

        try {
            return em.find(Student.class, id);
        } finally {
            em.close();
        }
    }

    // UPDATE: Change a student's name and technology
    public static void updateStudentNameAndTech(
            String newName, String newTech, int id) {

        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();

            Student student = em.find(Student.class, id);

            if (student != null) {
                student.setName(newName);
                student.setTech(newTech);
            }

            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    // DELETE: Remove a student by ID
    public static void deleteStudentById(int id) {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();

            Student student = em.find(Student.class, id);

            if (student != null) {
                em.remove(student);
            }

            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public static void main(String[] args) {
        try {
            // CREATE
            Student student = new Student();
            student.setName("Lab3 Test Student");
            student.setTech("Java");

            createStudent(student);

            // READ
            int id = student.getId();
            System.out.println("Created student ID: " + id);
            System.out.println("Student: " + getStudentById(id));

            // UPDATE
            updateStudentNameAndTech(
                    "Updated Student", "Python", id);

            System.out.println("After update: " + getStudentById(id));

            // READ ALL
            System.out.println("All students:");
            for (Student s : getAllStudents()) {
                System.out.println(s);
            }

            // DELETE the test student
            deleteStudentById(id);

            System.out.println(
                    "After delete: " + getStudentById(id));

        } finally {
            emf.close();
        }
    }
}
