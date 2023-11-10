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
import java.io.File;
import java.util.Random;

public class SlangDictionary {
    private HashMap<String, String> slangWords;
    private LinkedHashSet<String> searchHistory;

    public SlangDictionary() {
        slangWords = new HashMap<>();
        searchHistory = new LinkedHashSet<>();

        String localFileLocation = "../data/local.data";
        File localData = new File(localFileLocation);
        if (localData.exists()) {
            loadFromFile(localFileLocation);
        } else {
            loadDefaultFile();
        }
    }

    public void loadDefaultFile() {
        String filePath = "../data/slang.txt";
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
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
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

    // Sub-feature
    public boolean isSlangWordExist(String word) {
        return slangWords.containsKey(word);
    }

    // * Feature 1
    public String searchBySlangWord(String word) {
        searchHistory.add(word);
        return slangWords.get(word);
    }

    // * Feature 2
    public List<String> searchByDefinition(String keyword) {
        List<String> matchingWords = new ArrayList<>();
        for (Map.Entry<String, String> entry : slangWords.entrySet()) {
            if (entry.getValue().contains(keyword)) {
                matchingWords.add(entry.getKey());
            }
        }
        return matchingWords;
    }

    // * Feature 3
    public List<String> getSearchHistory() {
        return new ArrayList<>(searchHistory);
    }

    // * Feature 4
    public void addSlangWord(String word, String meaning) {
        slangWords.put(word, meaning);
    }

    // * Feature 5
    public void editSlangWord(String word, String newMeaning) {
        slangWords.put(word, newMeaning);
    }

    // * Feature 6
    public void deleteSlangWord(String word) {
        slangWords.remove(word);
    }

    // * Feature 7
    public void resetSlangWords() {
        slangWords.clear();
        loadDefaultFile();
    }

    // * Feature 8
    public Object randomSlangWord() {
        Object[] keys = slangWords.keySet().toArray();
        Object key = keys[new Random().nextInt(keys.length)];
        return key;
    }

    // * Feature 9
    public List<Object> getSlangWordQuiz(Object correctAnswer) {
        Object[] keys = slangWords.keySet().toArray();
        List<Object> answers = new ArrayList<>();
        answers.add(slangWords.get(correctAnswer));
        while (answers.size() < 4) {
            Object randomKey = keys[new Random().nextInt(keys.length)];
            String randomAnswer = slangWords.get(randomKey);
            if (!answers.contains(randomAnswer)) {
                answers.add(randomAnswer);
            }
        }
        return answers;
    }

    // Feature 10
    public List<Object> getDefinitionQuiz(Object correctAnswer) {
        List<Object> answers = new ArrayList<>();
        answers.add(correctAnswer.toString());
        while (answers.size() < 4) {
            Object randomDefinition = slangWords.get(randomSlangWord());
            if (!answers.contains(randomDefinition)) {
                List<String> matchingWords = searchByDefinition(randomDefinition.toString());
                answers.add(searchByDefinition(randomDefinition.toString()).get(new Random().nextInt(matchingWords.size())));
            }
        }
        return answers;
    }
}