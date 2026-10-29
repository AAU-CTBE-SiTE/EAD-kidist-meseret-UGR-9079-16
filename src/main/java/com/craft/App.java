package com.craft;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.Query;
import jakarta.persistence.EntityTransaction;
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
    public static int deleteStudentById(int id) {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();

            Student student = em.find(Student.class, id);
            int count = 0;

            if (student != null) {
                em.remove(student);
                count = 1;
            }

            em.getTransaction().commit();
            return count;

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
    // 7. Get students ordered by name
    public static List<Student> getStudentsOrderedByName() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Student> query = em.createQuery(
                    "SELECT s FROM Student s ORDER BY s.name ASC",
                    Student.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // 8. Get students ordered by ID, highest first
    public static List<Student> getStudentsOrderedByIdDescending() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Student> query = em.createQuery(
                    "SELECT s FROM Student s ORDER BY s.id DESC",
                    Student.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // 9. Count all students
    public static long countStudents() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery(
                    "SELECT COUNT(s) FROM Student s",
                    Long.class);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }

    // 10. Find the minimum student ID
    public static Integer getMinimumStudentId() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Integer> query = em.createQuery(
                    "SELECT MIN(s.id) FROM Student s",
                    Integer.class);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }

    // 11. Find the maximum student ID
    public static Integer getMaximumStudentId() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Integer> query = em.createQuery(
                    "SELECT MAX(s.id) FROM Student s",
                    Integer.class);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }

    // 12. Get students with a specific technology and name pattern
    public static List<Student> getStudentsByTechAndNamePattern(
            String tech, String pattern) {

        EntityManager em = getEntityManager();
        try {
            TypedQuery<Student> query = em.createQuery(
                    "SELECT s FROM Student s " +
                            "WHERE s.tech = :tech AND s.name LIKE :pattern",
                    Student.class);

            query.setParameter("tech", tech);
            query.setParameter("pattern", "%" + pattern + "%");

            return query.getResultList();

        } finally {
            em.close();
        }
    }
    // 13. Get students using pagination
    public static List<Student> getStudentsWithPagination(
            int offset, int limit) {

        EntityManager em = getEntityManager();
        try {
            TypedQuery<Student> query = em.createQuery(
                    "SELECT s FROM Student s ORDER BY s.id ASC",
                    Student.class);

            query.setFirstResult(offset);
            query.setMaxResults(limit);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // 14. Count students by technology
    public static List<Object[]> countStudentsByTech() {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery(
                    "SELECT s.tech, COUNT(s) FROM Student s GROUP BY s.tech");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // 15. Find distinct technologies
    public static List<String> getDistinctTechs() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<String> query = em.createQuery(
                    "SELECT DISTINCT s.tech FROM Student s",
                    String.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // 16. Find average student ID
    public static Double getAverageId() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Double> query = em.createQuery(
                    "SELECT AVG(s.id) FROM Student s",
                    Double.class);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }

    // 17. Find students with the longest names
    public static List<Student> getStudentsWithLongestNames() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Student> query = em.createQuery(
                    "SELECT s FROM Student s WHERE LENGTH(s.name) = " +
                            "(SELECT MAX(LENGTH(s2.name)) FROM Student s2)",
                    Student.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // 18. Find students whose technology is NULL
    public static List<Student> getStudentsWithNullTech() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Student> query = em.createQuery(
                    "SELECT s FROM Student s WHERE s.tech IS NULL",
                    Student.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    // 19. Count technologies with more than 2 students
    public static List<Object[]> getTechsWithMoreThanTwoStudents() {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery(
                    "SELECT s.tech, COUNT(s) FROM Student s " +
                            "GROUP BY s.tech HAVING COUNT(s) > 2"
            );
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // 20. Find the student with the highest ID
    public static List<Student> getStudentWithHighestId() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Student> query = em.createQuery(
                    "SELECT s FROM Student s WHERE s.id = " +
                            "(SELECT MAX(s2.id) FROM Student s2)",
                    Student.class
            );
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // 21. Find students using the same technology as student ID 1
    public static List<Student> getStudentsWithSameTechAsStudentOne() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Student> query = em.createQuery(
                    "SELECT s FROM Student s WHERE s.tech = " +
                            "(SELECT s2.tech FROM Student s2 WHERE s2.id = 1)",
                    Student.class
            );
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // 22. Find students who share their technology with another student
    public static List<Student> getStudentsSharingTech() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Student> query = em.createQuery(
                    "SELECT s FROM Student s WHERE EXISTS " +
                            "(SELECT s2 FROM Student s2 " +
                            "WHERE s2.tech = s.tech AND s2.id <> s.id)",
                    Student.class
            );
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // 23. Get student names in uppercase
    public static List<String> getStudentNamesUppercase() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<String> query = em.createQuery(
                    "SELECT UPPER(s.name) FROM Student s",
                    String.class
            );
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // 24. Get the length of each student name
    public static List<Integer> getStudentNameLengths() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Integer> query = em.createQuery(
                    "SELECT LENGTH(s.name) FROM Student s",
                    Integer.class
            );
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    // 25. Calculate the sum of all student IDs
    public static Long getSumOfStudentIds() {
        EntityManager em = getEntityManager();

        try {
            TypedQuery<Long> query = em.createQuery(
                    "SELECT SUM(s.id) FROM Student s",
                    Long.class
            );

            return query.getSingleResult();
        } finally {
            em.close();
        }
    }

    // 26. Find students with non-null technology
    public static List<Student> getStudentsWithNonNullTech() {
        EntityManager em = getEntityManager();

        try {
            TypedQuery<Student> query = em.createQuery(
                    "SELECT s FROM Student s WHERE s.tech IS NOT NULL",
                    Student.class
            );

            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // 27. Find students using a named parameter
    public static List<Student> getStudentsByNamedParameter(String name) {
        EntityManager em = getEntityManager();

        try {
            TypedQuery<Student> query = em.createQuery(
                    "SELECT s FROM Student s WHERE s.name = :name",
                    Student.class
            );

            query.setParameter("name", name);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // 28. Find students using a positional parameter
    public static List<Student> getStudentsByPositionalParameter(String tech) {
        EntityManager em = getEntityManager();

        try {
            TypedQuery<Student> query = em.createQuery(
                    "SELECT s FROM Student s WHERE s.tech = ?1",
                    Student.class
            );

            query.setParameter(1, tech);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    // Update a student's technology using JPQL
    public static int updateStudentTechJPQL(int id, String newTech) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Query query = em.createQuery(
                    "UPDATE Student s SET s.tech = :newTech WHERE s.id = :id"
            );

            query.setParameter("newTech", newTech);
            query.setParameter("id", id);

            int count = query.executeUpdate();
            tx.commit();

            return count;
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
    // Update technology for students matching a pattern
    public static int updateTechByPattern(
            String oldPattern, String newTech) {

        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Query query = em.createQuery(
                    "UPDATE Student s SET s.tech = :newTech " +
                            "WHERE s.tech LIKE :oldPattern"
            );

            query.setParameter("newTech", newTech);
            query.setParameter("oldPattern", "%" + oldPattern + "%");

            int count = query.executeUpdate();
            tx.commit();

            return count;
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
    // Delete students with a specific technology
    public static int deleteStudentsByTech(String tech) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Query query = em.createQuery(
                    "DELETE FROM Student s WHERE s.tech = :tech"
            );

            query.setParameter("tech", tech);

            int count = query.executeUpdate();
            tx.commit();

            return count;
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
    public static void testBulkOperations() {
        EntityManager em = getEntityManager();
        int firstId;

        // Create two temporary students
        try {
            em.getTransaction().begin();

            Student first = new Student();
            first.setName("Lab3 Temporary One");
            first.setTech("Lab3TempTech");
            em.persist(first);

            Student second = new Student();
            second.setName("Lab3 Temporary Two");
            second.setTech("Lab3TempTech");
            em.persist(second);

            em.flush();

            firstId = first.getId();
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }


        // Update both temporary students by technology pattern
        System.out.println("Pattern update count: " +
                updateTechByPattern("Lab3TempTech", "Lab3UpdatedTech"));

        // Update only the first temporary student
        System.out.println("Single update count: " +
                updateStudentTechJPQL(firstId, "Lab3FinalTech"));

        // Delete the first temporary student by ID
        System.out.println("Delete by ID count: " + deleteStudentById(firstId));

        // Delete the second temporary student by its unique technology
        System.out.println("Delete by technology count: " +
                deleteStudentsByTech("Lab3UpdatedTech"));

        System.out.println("Temporary students cleaned up.");
    }
        public static void main (String[]args){
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
                System.out.println("\n--- JPQL TESTS 7–12 ---");

                System.out.println("Students ordered by name:");
                System.out.println(getStudentsOrderedByName());

                System.out.println("Students ordered by ID descending:");
                System.out.println(getStudentsOrderedByIdDescending());

                System.out.println("Total students: " + countStudents());

                System.out.println("Minimum student ID: " + getMinimumStudentId());

                System.out.println("Maximum student ID: " + getMaximumStudentId());

                System.out.println("Java students whose names contain 'Snow':");
                System.out.println(getStudentsByTechAndNamePattern("Java", "Snow"));
                System.out.println("\n--- JPQL TESTS 13–18 ---");

                System.out.println("First 5 students:");
                System.out.println(getStudentsWithPagination(0, 5));

                System.out.println("Student count by technology:");
                for (Object[] row : countStudentsByTech()) {
                    System.out.println(row[0] + " = " + row[1]);
                }

                System.out.println("Distinct technologies:");
                System.out.println(getDistinctTechs());

                System.out.println("Average student ID:");
                System.out.println(getAverageId());

                System.out.println("Students with the longest names:");
                System.out.println(getStudentsWithLongestNames());

                System.out.println("Students with NULL technology:");
                System.out.println(getStudentsWithNullTech());
                System.out.println("\n--- JPQL TESTS 19–24 ---");

                System.out.println("Technologies with more than 2 students:");
                for (Object[] row : getTechsWithMoreThanTwoStudents()) {
                    System.out.println(row[0] + " = " + row[1]);
                }

                System.out.println("Student with highest ID:");
                System.out.println(getStudentWithHighestId());

                System.out.println("Students with the same tech as student ID 1:");
                System.out.println(getStudentsWithSameTechAsStudentOne());

                System.out.println("Students sharing technology:");
                System.out.println(getStudentsSharingTech());

                System.out.println("Uppercase student names:");
                System.out.println(getStudentNamesUppercase());

                System.out.println("Student name lengths:");
                System.out.println(getStudentNameLengths());
                System.out.println("\n--- PARAMETER AND AGGREGATE TESTS ---");

                System.out.println("Sum of student IDs:");
                System.out.println(getSumOfStudentIds());

                System.out.println("Students with non-null technology:");
                System.out.println(getStudentsWithNonNullTech());

                System.out.println("Named parameter search:");
                System.out.println(getStudentsByNamedParameter("John Snow"));

                System.out.println("Positional parameter search:");
                System.out.println(getStudentsByPositionalParameter("Java"));
                System.out.println("Positional parameter search:");
                System.out.println(getStudentsByPositionalParameter("Java"));

                System.out.println("\n--- BULK UPDATE AND DELETE TESTS ---");
                testBulkOperations();

            } finally {
                emf.close();
            }
        }
    }
