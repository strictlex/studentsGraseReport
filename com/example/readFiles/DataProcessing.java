package com.example.readFiles;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class DataProcessing {

    public static void dataFiles(Map stringMap, String nameFile) {
        int cntStudents = stringMap.size();
        Map<String, Map> nameSubjectsGrades = stringMap;
        // Map<String, Integer> avgGradesToSubjects = new LinkedHashMap<>();
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

        Map<String, ArrayList<Integer>> subjectsGradesMapList = createVars(nameSubjectsGrades);

        try {
            File file = new File(nameFile);
            if (!file.exists()) {
                file.createNewFile();
            }
            PrintWriter pw = new PrintWriter(file);
            for (String sub : subjectsGradesMapList.keySet()) {
                ArrayList<Integer> gradeList = subjectsGradesMapList.get(sub);
                String line = sub + " - " + sumAvg(gradeList);
                pw.println(line);
                System.out.println(line);
            }
            pw.println();
            System.out.println();
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

    // метод для создания предмет: массив оценок
    public static Map<String, ArrayList<Integer>> createVars(Map<String, Map> mapName) {
        Map<String, ArrayList<Integer>> subjectsListGrade = new HashMap<>();
        ArrayList<Integer> gradesList = new ArrayList<>();

        for (String name : mapName.keySet()) {
            Map<String, Integer> subjectGrade = mapName.get(name);
            for (String sub : subjectGrade.keySet()) {
                Integer grade = subjectGrade.get(sub);
                subjectsListGrade.putIfAbsent(sub, new ArrayList<>());
                subjectsListGrade.get(sub).add(grade);

            }
        }

        return subjectsListGrade;

    }

    public static double sumAvg(ArrayList<Integer> listName) {
        ArrayList<Integer> list = listName;
        int len = listName.size();
        int sum = 0;
        for (Integer i : list) {
            sum += i;
        }
        return (double) (sum / len);
    }
}
