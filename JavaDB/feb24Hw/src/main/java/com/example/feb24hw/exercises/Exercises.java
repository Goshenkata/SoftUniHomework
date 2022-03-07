package com.example.feb24hw.exercises;

import com.example.feb24hw.entities.*;
import com.example.feb24hw.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class Exercises implements CommandLineRunner {

    final TownRepository townRepository;
    final EmployeeRepository employeeRepository;
    final AddressRepository addressRepository;
    final ProjectRepository projectRepository;
    final DepartmentRepository departmentRepository;

    public Exercises(TownRepository townRepository, EmployeeRepository employeeRepository, AddressRepository addressRepository, ProjectRepository projectRepository, DepartmentRepository departmentRepository) {
        this.townRepository = townRepository;
        this.employeeRepository = employeeRepository;
        this.addressRepository = addressRepository;
        this.projectRepository = projectRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("NOTE: execute the init.sql script again, all data was cleared");
        System.out.println("pick an exercise [2-13]");
        Scanner scanner = new Scanner(System.in);
        Integer exercise = Integer.parseInt(scanner.nextLine());
        switch (exercise) {
            case 2:
                exercise2();
                break;
            case 3:
                exercise3();
                break;
            case 4:
                exercise4();
                break;
            case 5:
                exercise5();
                break;
            case 6:
                exercise6();
                break;
            case 7:
                exercise7();
                break;
            case 8:
                exercise8();
                break;
            case 9:
                exercise9();
                break;
            case 10:
                exercise10();
                break;
            case 11:
                exercise10();
                exercise11();
                break;
            case 12:
                exercise10();
                exercise12();
                break;
            case 13:
                exercise10();
                exercise13();
                break;
            default:
                System.out.println("Invalid exercise number");
        }
    }

    private void exercise13() {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        Town town = townRepository.findAllByName(name);
        List<Address> addresses = addressRepository.findAllByTown(town);
        System.out.printf("%d %s in %s deleted%n", addresses.size(), addresses.size() == 1 ? "address" : "addresses", name);
        addressRepository.deleteAll(addresses);
        townRepository.delete(town);
    }

    private void exercise12() {
        List<Department> departments = departmentRepository.findAll();
        for (Department d : departments) {
            double max = d.getEmployees().stream().mapToDouble(e -> e.getSalary().doubleValue()).max().getAsDouble();
            if (!(max >= 30000 || max <= 70000)) {
                System.out.printf("%s %.2f%n", d.getName(), max);
            }
        }
    }

    private void exercise11() {
        Scanner scanner = new Scanner(System.in);
        String patter = scanner.nextLine();
        List<Employee> employees = employeeRepository.findAll();
        employees.stream().filter(emp -> emp
                .getFirstName()
                .toLowerCase()
                .startsWith(patter.toLowerCase(Locale.ROOT)))
                        .forEach(employee -> {
                            System.out.printf("%s %s - %s - (%.2f)%n",
                                    employee.getFirstName(),
                                    employee.getLastName(),
                                    employee.getJobTitle(),
                                    employee.getSalary().doubleValue());
                        });

    }

    private void exercise10() {
        List<Employee> employees = employeeRepository.findAll();
        employees.stream()
                .filter(e -> {
            String name = e.getDepartment().getName();
            return name.equalsIgnoreCase("Engineering")
                    || name.equalsIgnoreCase("Tool Design")
                    || name.equalsIgnoreCase("Marketing")
                    || name.equalsIgnoreCase("Information Services");
        }).forEach(employee -> {
                    employee.setSalary(employee.getSalary().multiply(new BigDecimal("0.12")));
                    employeeRepository.save(employee);
                    System.out.printf("%s %s (%.2f)%n",
                            employee.getFirstName(),
                            employee.getLastName(),
                            employee.getSalary().doubleValue());
                });

    }

    private void exercise9() {
        List<Project> projects = projectRepository.findByOrderByStartDateDesc();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss:S");
        projects.stream()
                .limit(10)
                .sorted(Comparator.comparing(Project::getName))
                .forEach(p -> {
                    System.out.printf("Project name: %s%n", p.getName()).printf("     Project description: %s%n", p.getDescription());
                    System.out.printf("     Project Start Date: %s%n", p.getStartDate().format(formatter));
                    System.out.printf("     Project End Date: %s%n", p.getEndDate() == null ? "null" : p.getEndDate().format(formatter));
                });
    }

    private void exercise8() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id: ");
        Integer id = Integer.parseInt(scanner.nextLine());
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("No employee woth this id"));
        System.out.printf("%s %s -%s%n", employee.getFirstName(), employee.getLastName(), employee.getJobTitle());
        employee.getProjects()
                .stream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(p -> System.out.printf("     %s%n", p.getName()));
    }

    private void exercise7() {
        List<Address> all = addressRepository.findAll();
        all.stream().sorted((a1, a2) -> Integer.compare(a2.getEmployees().size(), a1.getEmployees().size()))
                .limit(10)
                .forEach((a) -> System.out.printf("%s, %s, - %d employees%n",
                        a.getText(),
                        a.getTown().getName(),
                        a.getEmployees().size()));
    }

    private void exercise6() {
        System.out.println("Enter last name");
        Scanner scanner = new Scanner(System.in);
        String lastName = scanner.nextLine();

        Address address = new Address();
        address.setText("Vitoshka 15");
        Employee employee = employeeRepository.findByLastName(lastName).orElseThrow(() -> new RuntimeException("Employee not found"));
        employee.setAddress(address);
        addressRepository.save(address);
        employeeRepository.save(employee);
    }

    private void exercise5() {
        List<Employee> employees = employeeRepository
                .findAllByDepartmentNameOrderBySalaryAscIdAsc("Research and Development ");
        employees.forEach(e ->
                System.out.printf("%s %s from Research and Development - $%.2f%n",
                        e.getFirstName(),
                        e.getLastName(),
                        e.getSalary().doubleValue()));
    }

    private void exercise4() {
        List<Employee> allBySalaryGreaterThan = employeeRepository.findAllBySalaryGreaterThan(new BigDecimal(50000));
        allBySalaryGreaterThan.forEach(e -> System.out.println(e.getFirstName()));
    }

    private void exercise3() {
        System.out.println("Enter name: ");
        Scanner scanner = new Scanner(System.in);
        String[] info = scanner.nextLine().split(" ");
        String firstName = info[0];
        String lastName = info[1];
        if (employeeRepository.existsAllByFirstNameAndLastName(firstName, lastName)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    private void exercise2() {
        List<Town> towns = townRepository.findAll();
        for (Town town : towns) {
            if (town.getName().length() > 5) {
                town.setName(town.getName().toUpperCase(Locale.ROOT));
                townRepository.save(town);
            }
        }

    }
}
