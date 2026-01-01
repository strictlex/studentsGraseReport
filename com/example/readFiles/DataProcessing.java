package com.example.readFiles;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class DataProcessing {

    public static void dataFiles(Map stringMap, String nameFile) {
        int cntStudents = stringMap.size();
        Map<String, Map> nameSubjectsGrades = stringMap;
        Map<String, Integer> avgGradesToSubjects = new LinkedHashMap<>();
        String bestStudent = null;
        String worstStudent = null;
        Double maxAvgGrade = 0.0;
        Double minAvgGrade = 5.0;
        Map<String, Double> bestWorstStudent = new HashMap<>();

        for (String name : nameSubjectsGrades.keySet()) {
            Double cnt = 0.0;
            Double sumGrades = 0.0;
            Map<String, Integer> valuesName = nameSubjectsGrades.get(name);
            for (String subject : valuesName.keySet()) {
                ++cnt;
                sumGrades += valuesName.get(subject);
            }
            bestWorstStudent.put(name, (sumGrades / cnt));
        }
        for (String name : bestWorstStudent.keySet()) {
            if (bestStudent == null) {
                bestStudent = name;
            }
            if (worstStudent == null) {
                worstStudent = name;
            }
            if (maxAvgGrade < bestWorstStudent.get(name)) {
                maxAvgGrade = bestWorstStudent.get(name);
                bestStudent = name;
            }
            if (minAvgGrade > bestWorstStudent.get(name)) {
                minAvgGrade = bestWorstStudent.get(name);
                worstStudent = name;
            }
        }

        try {
            File file = new File(nameFile);
            if (!file.exists()) {
                file.createNewFile();
            }
            PrintWriter pw = new PrintWriter(file);
            pw.println("Лучший ученик:");
            System.out.println("Лучший ученик:");
            pw.println(bestStudent + " средний балл - " + maxAvgGrade);
            System.out.println(bestStudent + " средний балл - " + maxAvgGrade);
            pw.println();
            System.out.println();

            pw.println("Худший ученик:");
            System.out.println("Худший ученик:");
            pw.println(worstStudent + " средний балл - " + minAvgGrade);
            System.out.println(worstStudent + " средний балл - " + minAvgGrade);
            pw.println();
            System.out.println();

            pw.println("Количество учеников: " + cntStudents);
            System.out.println("Количество учеников: " + cntStudents);
            pw.close();
        } catch (final IOException e) {
            System.out.println("Error: " + e);
        }

    }
}
