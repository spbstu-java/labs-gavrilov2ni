import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Translator {
    private Map<String, String> dictionary;

    public Translator(){
        this.dictionary = new HashMap<>();
    }
    public Translator(Map<String, String> dictionary){
        this.dictionary = dictionary;
    }

    public void setDictionary(Map<String, String> dictionary) {
        this.dictionary = dictionary;
    }
    public Map<String, String> getDictionary() {
        return this.dictionary;
    }

    public void addEntryToDictionary(String key, String value){
        this.dictionary.put(key, value);
    }
    public void removeEntryFromDictionary(String key){
        this.dictionary.remove(key);
    }

    public Vector<String> translateArray(Vector<String> text){
        Vector<String> tempText = new Vector<>();
        for (String line : text) {
            tempText.add(this.translateLine(line));
        }
        return tempText;
    }

    public String translateLine(String line){
        String temp = line;
        String[] buffer = line.replaceAll("[^a-zA-Z ]", "").toLowerCase().split(" ");
        for (String word : buffer) {
            String translation = this.dictionary.get(word);
            if (translation == null) {
                translation = word;
            } else if (translation.contains(",")) {
                String[] translations = translation.split(", ");
                translation = translations[0];
                for (String t : translations) {
                    if (t.length() > translation.length()) {
                        translation = t;
                    }
                }
            }
            temp = temp.replaceAll(word, translation);
        }
        return temp;
    }
}