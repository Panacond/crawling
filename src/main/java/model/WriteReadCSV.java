package model;

import org.testng.log4testng.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WriteReadCSV {
    private static final String PATH_SAVE_CSV = "src/main/resources/id.csv";
    final static Logger logger = Logger.getLogger(WriteReadCSV.class);

    public static void writeDataAtOnce(List<String> listId)
    {
        try (PrintWriter writer = new PrintWriter(new FileWriter(PATH_SAVE_CSV, true))) {
            StringBuilder sb = new StringBuilder();
            for (String i :listId) {
                sb.append(i);
                sb.append('\n');
            }
            writer.write(sb.toString());
            logger.debug("write csv");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            logger.error("no write csv");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("no write csv IOException");
        }
    }

    public static List<String> readCsvFile() {
        List<String> listId = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(PATH_SAVE_CSV));
            String line = reader.readLine();
            while (line != null) {
                listId.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("no read csv");
        }
        return listId;
    }
}
