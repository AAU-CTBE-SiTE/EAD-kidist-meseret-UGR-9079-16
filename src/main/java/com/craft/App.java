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

    // JPQL 1: Find students by exact name
    public static List<Student> getStudentsByName(String name) {
        EntityManager em = getEntityManager();

        try {
            TypedQuery<Student> query = em.createQuery(
                    "SELECT s FROM Student s WHERE s.name = :name",
                    Student.class);

            query.setParameter("name", name);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // JPQL 2: Find students by name, ignoring uppercase/lowercase
    public static List<Student> getStudentsByNameCaseInsensitive(
            String name) {
        EntityManager em = getEntityManager();

        try {
            TypedQuery<Student> query = em.createQuery(
                    "SELECT s FROM Student s " +
                            "WHERE LOWER(s.name) = LOWER(:name)",
                    Student.class);

            query.setParameter("name", name);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // JPQL 3: Find students by technology
    public static List<Student> getStudentsByTech(String tech) {
        EntityManager em = getEntityManager();

        try {
            TypedQuery<Student> query = em.createQuery(
                    "SELECT s FROM Student s WHERE s.tech = :tech",
                    Student.class);

            query.setParameter("tech", tech);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // JPQL 4: Find students whose names start with a letter
    public static List<Student> getStudentsByNameStartingWith(
            String letter) {
        EntityManager em = getEntityManager();

        try {
            TypedQuery<Student> query = em.createQuery(
                    "SELECT s FROM Student s WHERE s.name LIKE :pattern",
                    Student.class);

            query.setParameter("pattern", letter + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // JPQL 5: Find students whose names contain some text
    public static List<Student> getStudentsByNameContaining(
            String substring) {
        EntityManager em = getEntityManager();

        try {
            TypedQuery<Student> query = em.createQuery(
                    "SELECT s FROM Student s WHERE s.name LIKE :pattern",
                    Student.class);

            query.setParameter("pattern", "%" + substring + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // JPQL 6: Find students whose IDs are greater than a given ID
    public static List<Student> getStudentsWithIdGreaterThan(
            int minId) {
        EntityManager em = getEntityManager();

        try {
            TypedQuery<Student> query = em.createQuery(
                    "SELECT s FROM Student s WHERE s.id > :minId",
                    Student.class);

            query.setParameter("minId", minId);
            return query.getResultList();
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

            System.out.println("\n--- JPQL TESTS ---");

            System.out.println("Exact name:");
            System.out.println(getStudentsByName("John Snow"));

            System.out.println("Case-insensitive name:");
            System.out.println(
                    getStudentsByNameCaseInsensitive("john snow"));

            System.out.println("Students using Java:");
            System.out.println(getStudentsByTech("Java"));

            System.out.println("Names starting with J:");
            System.out.println(getStudentsByNameStartingWith("J"));

            System.out.println("Names containing Snow:");
            System.out.println(getStudentsByNameContaining("Snow"));

            System.out.println("Students with ID greater than 0:");
            System.out.println(getStudentsWithIdGreaterThan(0));

        } finally {
            emf.close();
        }
    }
}
