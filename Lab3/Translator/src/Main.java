import java.io.*;
import java.util.HashMap;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        Translator translator = new Translator();

        File file = new File("data/Dictionary_test.json");      //USE data/Dictionary.json for full version
        translator.setDictionary(readJsonToMap(file));

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("data/Input.txt"))) {
            String line;
            Vector<String> text = new Vector<>();

            while ((line = bufferedReader.readLine()) != null) {
                text.add(line.toLowerCase());
            }

            text = translator.translateArray(text);
            for (String result : text) {
                System.out.println(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HashMap<String, String> readJsonToMap(File file) {
        HashMap<String, String> tempMap = new HashMap<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = bufferedReader.readLine()) != null){
                if (line.contains("{") && line.contains("}")) {
                    String key;
                    String value;
                    String[] buffer;
                    int bufferStart = 0;
                    int bufferEnd = 0;


                    buffer = line.split("\"ru\": ");

                    bufferStart = buffer[0].indexOf("\"en\"") + 7;
                    bufferEnd = buffer[0].lastIndexOf("\"");
                    key = buffer[0].substring(bufferStart, bufferEnd);

                    bufferStart = buffer[1].indexOf("\"") + 1;
                    bufferEnd = buffer[1].lastIndexOf("\"");
                    value = buffer[1].substring(bufferStart, bufferEnd);

                    tempMap.put(key.toLowerCase(), value.toLowerCase());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempMap;
    }
}