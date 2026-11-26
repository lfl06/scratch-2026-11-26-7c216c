import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentGradesTest {

    private Path inputFile;
    private Path outputFile;

    private final List<String> students = List.of(
            "Anna Kowalska 5",
            "Jan Nowak 3",
            "Piotr Wisniewski 4",
            "Maria Wojcik 5",
            "Tomasz Kaminski 2"
    );

    @BeforeEach
    void setUp() throws IOException {
        inputFile = Files.createTempFile("students", ".txt");
        Files.write(inputFile, students, StandardCharsets.UTF_8);

        outputFile = Files.createTempFile("students_grade5", ".txt");

        inputFile.toFile().deleteOnExit();
        outputFile.toFile().deleteOnExit();
    }

    @Test
    void testFilterStudentsWithGrade5() throws IOException {
        StudentGrades.filterStudentsWithGrade5(inputFile, outputFile);

        List<String> expected = List.of("Anna Kowalska 5", "Maria Wojcik 5");
        List<String> actual = Files.readAllLines(outputFile, StandardCharsets.UTF_8);

        assertEquals(expected, actual);
    }
}
