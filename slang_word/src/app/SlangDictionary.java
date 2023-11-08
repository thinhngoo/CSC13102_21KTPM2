package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SlangDictionary {
    private HashMap<String, String> slangWords;
    private LinkedHashSet<String> searchHistory;

    public SlangDictionary() {
        slangWords = new HashMap<>();
        searchHistory = new LinkedHashSet<>();
    }

    public void loadDefaultFile() {
        String filePath = "./data/slang.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // Skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("`");
                if (parts.length >= 2) {
                    String word = parts[0];
                    String meaning = parts[1];
                    slangWords.put(word, meaning);
                } else {
                    System.out.println("Ignoring line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveToFile(String filePath) {
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(slangWords);
            oos.close();
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void loadFromFile(String filePath) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            slangWords = (HashMap) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            return;
        }
    }

    public void addWord(String word, String meaning) {
        slangWords.put(word, meaning);
    }

    // Feature 1
    public String searchBySlangWord(String word) {
        searchHistory.add(word);
        return slangWords.get(word);
    }

    // Feature 2
    public List<String> searchByDefinition(String keyword) {
        List<String> matchingWords = new ArrayList<>();
        for (Map.Entry<String, String> entry : slangWords.entrySet()) {
            if (entry.getValue().contains(keyword)) {
                matchingWords.add(entry.getKey());
            }
        }
        return matchingWords;
    }

    // Feature 3
    public List<String> getSearchHistory() {
        return new ArrayList<>(searchHistory);
    }
}