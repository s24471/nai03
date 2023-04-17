import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class DirectoryParser {
    public static Map<String, ArrayList<String>> get(String n){
        String directoryPath = n;
        Map<String, ArrayList<String>> directoryContents = new HashMap<>();

        File directory = new File(directoryPath);
        File[] subdirectories = directory.listFiles(File::isDirectory);

        for (File subdirectory : subdirectories) {
            String subdirectoryName = subdirectory.getName();
            File[] files = subdirectory.listFiles(file -> file.getName().endsWith(".txt"));

            ArrayList<String> s = new ArrayList<>();

            for (File file : files) {
                try {
                    String fileContents = new String(Files.readAllBytes(file.toPath()));
                    s.add(fileContents);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            directoryContents.put(subdirectoryName, s);
        }
        return directoryContents;
    }
}
