package ex3;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Scanner;

public class Ex3 {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);
        String[] info = scanner.nextLine().split(" ");

        Employee employee = entityManager
                .createQuery("select e from Employee e " +
                        "where e.firstName = :first_name and " +
                        "e.lastName = :last_name", Employee.class)
                .setParameter("first_name", info[0])
                .setParameter("last_name", info[1])
                .getSingleResult();
        if (employee != null) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
