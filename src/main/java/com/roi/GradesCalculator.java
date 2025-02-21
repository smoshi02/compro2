package com.roi;

import java.io.*;
import java.util.Scanner;

public class GradesCalculator {
    public static final double MIN_GRADE = 50;
    public static final String FILE_DIR = "target/records";
    public static final int MAX_SUBJECTS = 64;
    public static final String[] TERMS = {"Prelim", "Midterm", "Finals"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String studentName;
        String[] subjectNames = new String[MAX_SUBJECTS];
        double[][] subjectGrades = new double[MAX_SUBJECTS][TERMS.length];
        int subjectCount = 0;

        // Get student name
        System.out.print("Enter student name: ");
        studentName = scanner.nextLine().trim();

        // Get subjects and grades
        while (subjectCount < MAX_SUBJECTS) {
            System.out.print("Enter subject " + (subjectCount + 1) + ": ");
            subjectNames[subjectCount] = scanner.nextLine().trim();

            for (int i = 0; i < TERMS.length; i++) {
                while (true) {
                    try {
                        System.out.print("\t" + TERMS[i] + " grade: ");
                        subjectGrades[subjectCount][i] = Double.parseDouble(scanner.nextLine());

                        if (subjectGrades[subjectCount][i] < MIN_GRADE) {
                            System.out.println("\tInvalid grade, must be at least " + MIN_GRADE);
                        } else {
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("\tInvalid input. Please enter a numeric grade.");
                    }
                }
            }
            subjectCount++;

            // Ask if the user wants to add more subjects
            System.out.print("Add another subject? (y/n): ");
            char c = scanner.nextLine().toLowerCase().charAt(0);
            if (c != 'y') break;
        }

        // Displaying the results
        StringBuilder sb = new StringBuilder();
        sb.append("Student Name: ").append(studentName).append("\n\n");
        sb.append(String.format("%-20s%10s%10s%10s%15s\n", "Subject", TERMS[0], TERMS[1], TERMS[2], "Final Rating"));
        sb.append("------------------------------------------------------------\n");

        for (int i = 0; i < subjectCount; i++) {
            sb.append(String.format("%-20s", subjectNames[i]));
            for (int j = 0; j < TERMS.length; j++) {
                sb.append(String.format("%10.2f", subjectGrades[i][j]));
            }
            sb.append(String.format("%15.2f", calculateFinalRating(subjectGrades[i])));
            sb.append("\n");
        }

        System.out.println(sb.toString());

        // Save the results to a file
        saveToFile(studentName, sb.toString());

        // Read and display all grade files
        System.out.println("\n\nReading Files -----------------");
        readAllGradeFiles();
    }

    /**
     * Calculates the final rating based on:
     * 30% Prelim + 30% Midterm + 40% Finals
     */
    public static double calculateFinalRating(double[] termGrades) {
        return termGrades[0] * 0.3 + termGrades[1] * 0.3 + termGrades[2] * 0.4;
    }

    /**
     * Saves the student's data to a file.
     */
    public static void saveToFile(String studentName, String data) {
        File folder = new File(FILE_DIR);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File file = new File(folder, studentName + "_grades.txt");

        try (FileWriter fw = new FileWriter(file)) {
            fw.write(data);
            System.out.println("Grades saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving grades data: " + e.getMessage());
        }
    }

    /**
     * Reads all student grade files from the directory.
     */
    public static void readAllGradeFiles() {
        File folder = new File(FILE_DIR);
        File[] gradeFiles = folder.listFiles();

        if (gradeFiles == null || gradeFiles.length == 0) {
            System.out.println("No grade records found.");
            return;
        }

        for (File file : gradeFiles) {
            if (file.isFile()) {
                displayGradeFile(file);
            }
        }
    }

    /**
     * Displays the content of a specific grade file.
     */
    public static void displayGradeFile(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            System.out.println("--------------------------");
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading grade file: " + e.getMessage());
        }
    }
}

