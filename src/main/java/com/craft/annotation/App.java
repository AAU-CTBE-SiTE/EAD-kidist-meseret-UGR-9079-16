package com.craft.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        System.out.println("\n===== 1. BEAN CREATION =====");
        Student student = context.getBean("student", Student.class);
        student.display();

        System.out.println("\n===== 2. FIELD AND SETTER INJECTION =====");
        student.display();

        System.out.println("\n===== 3. CONSTRUCTOR INJECTION =====");
        Student constructor =
                context.getBean("constructorStudent", Student.class);
        constructor.display();

        System.out.println("\n===== 4. CONSTRUCTOR OBJECT INJECTION =====");
        Student object =
                context.getBean("objectStudent", Student.class);
        object.display();

        System.out.println("\n===== 5. COLLECTIONS INJECTION =====");
        Student collection =
                context.getBean("collectionStudent", Student.class);
        collection.display();

        System.out.println("\n===== 6. SINGLETON SCOPE =====");
        Student s1 =
                context.getBean("singletonStudent", Student.class);
        Student s2 =
                context.getBean("singletonStudent", Student.class);

        System.out.println(s1 == s2);

        System.out.println("\n===== 7. PROTOTYPE SCOPE =====");
        Student p1 =
                context.getBean("prototypeStudent", Student.class);
        Student p2 =
                context.getBean("prototypeStudent", Student.class);

        System.out.println(p1 == p2);

        context.close();
    }
}