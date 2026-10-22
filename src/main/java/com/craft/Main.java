
package com.craft;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class Main {

    // Create one EntityManagerFactory for the whole class
    public static EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("studentPU");

    // Get an EntityManager for database operations
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void main(String[] args) {
        try {
            // Run read-only examples. No automatic create, update, or delete.
            runJpqlQueries();
        } finally {
            emf.close();
        }
    }

    // 1. CREATE: Save a student
    public static void createStudent(Student student) {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();

            System.out.println("Student created successfully!");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    // 2. READ: Find a student by ID
    public static Student getStudentById(int id) {
        EntityManager em = getEntityManager();

        try {
            return em.find(Student.class, id);
        } finally {
            em.close();
        }
    }

    // 3. UPDATE: Change a student's name
    public static void updateStudentName(String newName, int id) {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();

            Student student = em.find(Student.class, id);

            if (student != null) {
                student.setName(newName);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    // 4. UPDATE: Change a student's name and technology
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
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    // 5. DELETE: Remove a student by ID
    public static void deleteStudentById(int id) {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();

            Student student = em.find(Student.class, id);

            if (student != null) {
                em.remove(student);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    // 6. Check EntityManager.contains()
    public static boolean checkContains(int id) {
        EntityManager em = getEntityManager();

        try {
            Student student = em.find(Student.class, id);

            // Check while the student is managed by this EntityManager
            return student != null && em.contains(student);
        } finally {
            em.close();
        }
    }

    // 7. JPQL: Run the required query examples
    public static void runJpqlQueries() {
        EntityManager em = getEntityManager();

        try {
            // 1. Select all students
            System.out.println("\n1. All Students");
            List<Student> students = em.createQuery(
                            "SELECT s FROM Student s", Student.class)
                    .getResultList();
            students.forEach(System.out::println);

            // 2. Select student names
            System.out.println("\n2. Student Names");
            List<String> names = em.createQuery(
                            "SELECT s.name FROM Student s", String.class)
                    .getResultList();
            names.forEach(System.out::println);

            // 3. Find students with ID = 1
            System.out.println("\n3. Student with ID 1");
            em.createQuery(
                            "SELECT s FROM Student s WHERE s.id = :id",
                            Student.class)
                    .setParameter("id", 1)
                    .getResultList()
                    .forEach(System.out::println);

            // 4. Find students named John
            System.out.println("\n4. Students Named John");
            em.createQuery(
                            "SELECT s FROM Student s WHERE s.name = :name",
                            Student.class)
                    .setParameter("name", "John")
                    .getResultList()
                    .forEach(System.out::println);

            // 5. Find students using Java
            System.out.println("\n5. Students Using Java");
            em.createQuery(
                            "SELECT s FROM Student s WHERE s.tech = :tech",
                            Student.class)
                    .setParameter("tech", "Java")
                    .getResultList()
                    .forEach(System.out::println);

            // 6. Names starting with J
            System.out.println("\n6. Names Starting With J");
            em.createQuery(
                            "SELECT s FROM Student s WHERE s.name LIKE 'J%'",
                            Student.class)
                    .getResultList()
                    .forEach(System.out::println);

            // 7. Technology is Java or Python
            System.out.println("\n7. Java or Python Students");
            em.createQuery(
                            "SELECT s FROM Student s WHERE s.tech IN :techs",
                            Student.class)
                    .setParameter("techs", List.of("Java", "Python"))
                    .getResultList()
                    .forEach(System.out::println);

            // 8. IDs between 5 and 15
            System.out.println("\n8. IDs Between 5 and 15");
            em.createQuery(
                            "SELECT s FROM Student s " +
                                    "WHERE s.id BETWEEN 5 AND 15",
                            Student.class)
                    .getResultList()
                    .forEach(System.out::println);

            // 9. Order by name ascending
            System.out.println("\n9. Order by Name ASC");
            em.createQuery(
                            "SELECT s FROM Student s ORDER BY s.name ASC",
                            Student.class)
                    .getResultList()
                    .forEach(System.out::println);

            // 10. Order by technology descending
            System.out.println("\n10. Order by Technology DESC");
            em.createQuery(
                            "SELECT s FROM Student s ORDER BY s.tech DESC",
                            Student.class)
                    .getResultList()
                    .forEach(System.out::println);

            // 11. Select distinct technologies
            System.out.println("\n11. Distinct Technologies");
            em.createQuery(
                            "SELECT DISTINCT s.tech FROM Student s",
                            String.class)
                    .getResultList()
                    .forEach(System.out::println);

            // 12. Count all students
            System.out.println("\n12. Total Students");
            Long total = em.createQuery(
                            "SELECT COUNT(s) FROM Student s",
                            Long.class)
                    .getSingleResult();
            System.out.println(total);

            // 13. Count students using Java
            System.out.println("\n13. Count Java Students");
            Long javaCount = em.createQuery(
                            "SELECT COUNT(s) FROM Student s " +
                                    "WHERE s.tech = 'Java'",
                            Long.class)
                    .getSingleResult();
            System.out.println(javaCount);

            // 14. Maximum student ID
            System.out.println("\n14. Maximum ID");
            System.out.println(em.createQuery(
                            "SELECT MAX(s.id) FROM Student s",
                            Integer.class)
                    .getSingleResult());

            // 15. Minimum student ID
            System.out.println("\n15. Minimum ID");
            System.out.println(em.createQuery(
                            "SELECT MIN(s.id) FROM Student s",
                            Integer.class)
                    .getSingleResult());

            // 16. Average student ID
            System.out.println("\n16. Average ID");
            System.out.println(em.createQuery(
                            "SELECT AVG(s.id) FROM Student s",
                            Double.class)
                    .getSingleResult());

            // 17. Sum of student IDs
            System.out.println("\n17. Sum of IDs");
            System.out.println(em.createQuery(
                            "SELECT SUM(s.id) FROM Student s",
                            Long.class)
                    .getSingleResult());

            // 18. Parameterized query using a name
            System.out.println("\n18. Search by Name Parameter");
            em.createQuery(
                            "SELECT s FROM Student s WHERE s.name = :name",
                            Student.class)
                    .setParameter("name", "John Snow")
                    .getResultList()
                    .forEach(System.out::println);

            // 19. Positional parameter using technology
            System.out.println("\n19. Search by Technology Parameter");
            em.createQuery(
                            "SELECT s FROM Student s WHERE s.tech = ?1",
                            Student.class)
                    .setParameter(1, "Java")
                    .getResultList()
                    .forEach(System.out::println);

            // 20. Bulk UPDATE: Python to Java
            // Defined separately below so it does not run automatically.

            // 21. Bulk UPDATE: Change the name of student ID 10
            // Defined separately below so it does not run automatically.

            // 22. Bulk DELETE: Delete students named Test
            // Defined separately below so it does not run automatically.

            // 23. Bulk DELETE: Delete students using C++
            // Defined separately below so it does not run automatically.

            // 24. Group students by technology
            System.out.println("\n24. Count by Technology");
            List<Object[]> grouped = em.createQuery(
                            "SELECT s.tech, COUNT(s) FROM Student s " +
                                    "GROUP BY s.tech", Object[].class)
                    .getResultList();

            for (Object[] row : grouped) {
                System.out.println(row[0] + ": " + row[1]);
            }

            // 25. Technologies with more than 2 students
            System.out.println("\n25. Technologies with More Than 2 Students");
            List<Object[]> havingResults = em.createQuery(
                            "SELECT s.tech, COUNT(s) FROM Student s " +
                                    "GROUP BY s.tech HAVING COUNT(s) > 2",
                            Object[].class)
                    .getResultList();

            for (Object[] row : havingResults) {
                System.out.println(row[0] + ": " + row[1]);
            }

            // 26. Find the student with the maximum ID
            System.out.println("\n26. Student with Maximum ID");
            em.createQuery(
                            "SELECT s FROM Student s WHERE s.id = " +
                                    "(SELECT MAX(s2.id) FROM Student s2)",
                            Student.class)
                    .getResultList()
                    .forEach(System.out::println);

            // 27. Find students with the same technology as ID 1
            System.out.println("\n27. Same Technology as Student 1");
            em.createQuery(
                            "SELECT s FROM Student s WHERE s.tech = " +
                                    "(SELECT s2.tech FROM Student s2 WHERE s2.id = 1)",
                            Student.class)
                    .getResultList()
                    .forEach(System.out::println);

            // 28. Students who share technology with another student
            System.out.println("\n28. Students Sharing Technology");
            em.createQuery(
                            "SELECT s FROM Student s WHERE EXISTS " +
                                    "(SELECT s2 FROM Student s2 " +
                                    "WHERE s2.tech = s.tech AND s2.id <> s.id)",
                            Student.class)
                    .getResultList()
                    .forEach(System.out::println);

            // 29. Convert student names to uppercase
            System.out.println("\n29. Names in Uppercase");
            em.createQuery(
                            "SELECT UPPER(s.name) FROM Student s",
                            String.class)
                    .getResultList()
                    .forEach(System.out::println);

            // 30. Get the length of student names
            System.out.println("\n30. Length of Student Names");
            em.createQuery(
                            "SELECT LENGTH(s.name) FROM Student s",
                            Integer.class)
                    .getResultList()
                    .forEach(System.out::println);

        } finally {
            em.close();
        }
    }

    // 20. Bulk UPDATE: Change Python technology to Java
    public static int updatePythonToJava() {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();

            int updated = em.createQuery(
                            "UPDATE Student s SET s.tech = 'Java' " +
                                    "WHERE s.tech = 'Python'")
                    .executeUpdate();

            em.getTransaction().commit();
            return updated;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    // 21. Bulk UPDATE: Change the name of student ID 10
    public static int updateStudentTenName() {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();

            int updated = em.createQuery(
                            "UPDATE Student s SET s.name = 'Updated' " +
                                    "WHERE s.id = 10")
                    .executeUpdate();

            em.getTransaction().commit();
            return updated;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    // 22. Bulk DELETE: Delete students named Test
    public static int deleteStudentsNamedTest() {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();

            int deleted = em.createQuery(
                            "DELETE FROM Student s WHERE s.name = 'Test'")
                    .executeUpdate();

            em.getTransaction().commit();
            return deleted;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    // 23. Bulk DELETE: Delete students using C++
    public static int deleteCppStudents() {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();

            int deleted = em.createQuery(
                            "DELETE FROM Student s WHERE s.tech = 'C++'")
                    .executeUpdate();

            em.getTransaction().commit();
            return deleted;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
}
