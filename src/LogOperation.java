import java.io.*;
import java.util.ArrayList;

public class LogOperation {
    // Имя лог-файла
    public static final String LOGFILENAME = "logfile.log";

    // Добавление записи в лог
    public void saveMessageToLog(String message) {
        String fileName = String.join("\\", String.join("\\",
                        new File("").getAbsolutePath()),"\\src",
                LOGFILENAME);
        try {
            createDataFile(fileName);
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        try (FileWriter fileWriter = new FileWriter(fileName, true)) {
            fileWriter.write(message);
            fileWriter.append('\n');
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        //System.out.println("Данные успешно внесены в файл " + fileName);
    }

    // Создание лог-файла, если он не существует
    public void createDataFile(String fileName) throws IOException {
        File file = new File(fileName);
        boolean created = file.createNewFile();
    }

    // Получение всех записей из лог-файла
    public ArrayList<String> readMessageFromLog() {
        String fileName = String.join("\\", String.join("\\",
                        new File("").getAbsolutePath()),"\\src",
                LOGFILENAME);
        ArrayList<String> logMessages = new ArrayList<>();

        File file = new File(fileName);
        if (file.exists()) {
            try {
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line = bufferedReader.readLine();
                if (line != null && line.equals("") == false) {
                    logMessages.add(line);
                }
                while (line != null) {
                    line = bufferedReader.readLine();
                    if (line != null && line.equals("") == false) {
                        logMessages.add(line);
                    }
                }
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return logMessages;
    }

}
