import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.readFiles.DataProcessing;
import com.example.readFiles.ReadAllFiles;

public class grade {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите имя файла для отчета, например: \"Отчет.txt\"");
        String report = sc.nextLine();
        System.out.println(
                "Введите путь расположения папки с файлами студентов, например: \"/Users/aleksey/Documents/java_projects/grade_students/grade_students/src/studentsFile\" - для Mac/Linux");
        System.out.println("\"c:\\grade_students\\src\\studentsFile\" - для Windows");
        String filePath = sc.nextLine();
        Map<String, Map> mapStudentsSubjectsGrades = null;
        ReadAllFiles rAF = new ReadAllFiles();
        try {
            mapStudentsSubjectsGrades = rAF.readAllFiles(filePath);
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }

        DataProcessing dp = new DataProcessing();
        dp.dataFiles(mapStudentsSubjectsGrades, report);

    }
}
