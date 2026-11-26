import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentGrades {

    private static final Pattern GRADE_5 = Pattern.compile("\\b5\\b");

    public static void filterStudentsWithGrade5(Path inputFile, Path outputFile) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(inputFile, StandardCharsets.UTF_8);
             BufferedWriter writer = Files.newBufferedWriter(outputFile, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = GRADE_5.matcher(line);
                if (matcher.find()) {
                    writer.write(line);
                    writer.newLine();
                }
            }
        }
    }
}
