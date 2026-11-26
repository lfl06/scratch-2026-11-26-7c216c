import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimetableChecker {

    private static final Pattern LESSON_PATTERN = Pattern.compile(
            "^(MON|TUE|WED|THU|FRI) "
            + "([01][0-9]|2[0-3]):([0-5][0-9])-([01][0-9]|2[0-3]):([0-5][0-9]) "
            + "([0-9]{3}) "
            + "([A-Z][A-Za-z]+(?: [A-Za-z]+)*)$");

    public static boolean isValid(String line) {
        Matcher matcher = LESSON_PATTERN.matcher(line);
        if (!matcher.matches()) {
            return false;
        }
        int start = Integer.parseInt(matcher.group(2)) * 60 + Integer.parseInt(matcher.group(3));
        int end = Integer.parseInt(matcher.group(4)) * 60 + Integer.parseInt(matcher.group(5));
        return end > start;
    }

    public static void main(String[] args) throws IOException {
        Path file = Path.of(args.length > 0 ? args[0] : "lessons.txt");
        List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);

        for (String line : lines) {
            if (line.isEmpty()) {
                continue;
            }
            System.out.println(line + " -> " + (isValid(line) ? "VALID" : "INVALID"));
        }
    }
}
