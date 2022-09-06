import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class example {
    public static void main(String[] args) {
//        Integer Integer;
//        List<Integer> i = new ArrayList<>();
//        for (int j = 0; j < 10; j++) {
//            i.add(j);
//        }
//        System.out.println(i);
//        System.out.println(getCurrentTimeStamp());
//
//        String a = "background-image: url(\"https://external.fods3-1.fna.fbcdn.net/static_map.php?v=2031&theme=default&ccb=4-4&size=328x120&language=ru_RU&scale=1&zoom=12&center=9.5364666753787%2C100.05244439495&marker_list[0]=9.5364666753787%2C100.05244439495\"); background-repeat: no-repeat; background-size: 100% 100%; height: 120px; width: 328px;";
//        System.out.println(regEx(a));
        List<String> a = Arrays.asList("1", "2", "6", "3", "5");
        String b = "26";
        if (!a.contains(b)){
            System.out.println(b);
        }
//        writeDataAtOnce(a);
//        System.out.println(readCsvFile().get(1));
    }

    public static void writeDataAtOnce(List<String> listId)
    {
        try (PrintWriter writer = new PrintWriter(new FileWriter("test.csv", true))) {
            StringBuilder sb = new StringBuilder();
            for (String i :listId) {
                sb.append(i);
                sb.append('\n');
            }
            writer.write(sb.toString());
            System.out.println("write csv");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> readCsvFile() {
        List<String> listId = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("test.csv"));
            String line = reader.readLine();
            while (line != null) {
                listId.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listId;
    }

    public static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd-HH--mm-ss-ms");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    public static String regEx(String a){
        Pattern pattern = Pattern.compile("center=(\\d+\\.\\d+)%2C(\\d+\\.\\d+)");
        Matcher matcher = pattern.matcher(a);
        matcher.find();
        String geolocation_x = matcher.group(1);
        String geolocation_y = matcher.group(2);
        return geolocation_x + "--//--" + geolocation_y;
    }

}
