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
import com.example.readFiles.ReadAllFiles;

public class grade {
    public static void main(String[] args) {
        File report = new File("Отчет.txt");
        String filePath = "/Users/aleksey/Documents/java_projects/grade_students/grade_students/src/studentsFile";
        ArrayList<Map> listStudentsGrades = null;
        // listStudentsGrades = readAllFiles(filePath);
        ReadAllFiles rAF = new ReadAllFiles();
        try {
            listStudentsGrades = rAF.readAllFiles(filePath);
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }

        System.out.println(listStudentsGrades);
    }
}
