package com.example.readFiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ReadAllFiles {
    public static Map readAllFiles(String folderPath) throws IOException {
        File folder = new File(folderPath);
        String regex = "^[А-Яа-яёЁ]+(?:-[А-Яа-яёЁ]+){0,2}\\s[А-Яа-яёЁ]+(?:-[А-Яа-яёЁ]+){0,2}\\s[А-Яа-яёЁ]+(?:-[А-Яа-яёЁ]+){0,2}.txt"; // Переменная
                                                                                                                                      // на
                                                                                                                                      // проверку
                                                                                                                                      // правильности
                                                                                                                                      // имени
                                                                                                                                      // файла
        // студента

        Map<String, Map> nameGrades = new LinkedHashMap<>();

        // Проверка существует ли папка
        if (!folder.exists()) {
            System.out.println("Папка: " + folderPath + " - не существует");
            return null;
        }

        // Проверка является ли папкой
        if (!folder.isDirectory()) {
            System.out.println(folderPath + " - не является папкой");
            return null;
        }

        // Проверкаа на читабельность папки
        File[] files = folder.listFiles();
        if (files == null) {
            System.out.println("Не удалось прочитать папку" + folder);
            return null;
        }

        // Проверка не пуста ли папка
        if (files.length == 0) {
            System.out.println("Папка: " + folder + " пуста!");
            return null;
        }

        // Все имена файлов папки заносим в массив
        for (File file : files) {
            String name = file.getName();
            if (name.matches(regex)) { // Проверяем правильность названия файла через регулярку и отправляем файл на
                                       // обработку
                Map<String, Integer> studentGrade = makeMapStudentGrade(file);
                if (studentGrade != null && !studentGrade.isEmpty()) { // Если вернулся не пустой словарь то добавляем в
                                                                       // список словарей
                    name = name.replace(".txt", "");
                    nameGrades.put(name, studentGrade);
                }
            }
        }
        return nameGrades;
    }

    public static Map makeMapStudentGrade(File file) throws IOException {

        Map<String, Integer> student = new LinkedHashMap<>();
        String line;
        ArrayList<String> stringFile = new ArrayList<>();
        String regex = new String("([А-Яа-яёЁ]*)\\s*[-]\\s*([1-5])");

        // считываем файл студента
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            while ((line = br.readLine()) != null) { // когда строка пуста цикл завершается
                stringFile.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }

        /*
         * проверка списка строк файла
         * если хоть одна строка не удовлетворяет условию - метод возвращает пустой
         * словарь
         */
        for (String string : stringFile) {
            if (!string.matches(regex)) {
                return student;
            }
        }

        Pattern pattern = Pattern.compile(regex);

        // добавляем элементы в словарь ключ(предмет):значение(оценка)
        for (String string : stringFile) {
            Matcher matcher = pattern.matcher(string);
            if (matcher.find()) {
                String keyString = matcher.group(1);
                int valueString = Integer.parseInt(matcher.group(2));
                student.put(keyString, valueString);
            }
        }

        return student;
    }

}
