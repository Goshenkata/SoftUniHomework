package com.example.codefirst.entities.hospital;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Scanner;

@Component
public class UI implements CommandLineRunner {

    final PatientRepository patientRepository;

    public UI(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("This is exercise 4");
        System.out.println("Add a new patient");
        Scanner scanner = new Scanner(System.in);
        addNewPatient();
    }

    private void addNewPatient() {
        Patient patient = new Patient();
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter the new patients first name");
        String firstName = scanner.nextLine();
        System.out.println("enter the new patient's last name");
        String lastName = scanner.nextLine();
        System.out.println("enter the new patient's email");
        String email = scanner.nextLine();
        if (patientRepository.findByAddress(email).isEmpty()) {
            System.out.println("Enter patient birthday");
            System.out.println("enter year");
            int year = Integer.parseInt(scanner.nextLine());
            System.out.println("enter month");
            int month = Integer.parseInt(scanner.nextLine());
            System.out.println("enter day");
            int day = Integer.parseInt(scanner.nextLine());
            System.out.println("Would you like to enter a picture [y/n]");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("y")) {
                System.out.println("Enter the full path to image");
                try {
                    patient.setPicture(Files.readAllBytes(Path.of(scanner.nextLine())));
                } catch (IOException e) {
                    System.out.println("error saving file");
                }
            }
            System.out.println("does the patient have insurance [y/n]");
            String ins = scanner.nextLine();
            patient.setHasInsurance(ins.equalsIgnoreCase("y"));


            patient.setFirstName(firstName);
            patient.setLastName(lastName);
            patient.setAddress(email);
            patient.setDateOfBirth(LocalDate.of(year,month,day));
            patientRepository.save(patient);
        } else {
            System.out.println("user with this email is in the database");
        }
    }
}
