package core;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

public class SlangDictionary {
    private HashMap<String, String> slangWords;
    private LinkedHashSet<String> searchHistory;

    protected SlangDictionary() {
        slangWords = new HashMap<>();
        searchHistory = new LinkedHashSet<>();

        String localFileLocation = "./data/local.data";
        File localData = new File(localFileLocation);
        if (localData.exists())
            loadFromFile(localFileLocation);
        else
            loadDefaultFile();
    }

    protected void loadDefaultFile() {
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
                } else
                    System.out.println("Ignoring line: " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void saveToFile(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) file.createNewFile();
            
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(slangWords);
            oos.close();
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    // Sure checking
    @SuppressWarnings("unchecked")
    protected void loadFromFile(String filePath) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            slangWords = (HashMap<String, String>) ois.readObject();
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

    protected boolean isSlangWordExist(String word) { return slangWords.containsKey(word); }

    // * Feature 1
    protected String searchBySlangWord(String word) {
        searchHistory.add(word);
        return slangWords.get(word);
    }

    // * Feature 2
    protected ArrayList<String> searchByDefinition(String keyword) {
        ArrayList<String> matchingWords = new ArrayList<>();
        for (Map.Entry<String, String> entry : slangWords.entrySet())
            if (entry.getValue().contains(keyword))
                matchingWords.add(entry.getKey());
        
        return matchingWords;
    }

    // * Feature 3
    protected ArrayList<String> getSearchHistory() { return new ArrayList<>(searchHistory); }

    // * Feature 4
    protected void addSlangWord(String word, String meaning) { slangWords.put(word, meaning); }

    // * Feature 5
    protected void editSlangWord(String word, String newMeaning) { slangWords.put(word, newMeaning); }

    // * Feature 6
    protected void deleteSlangWord(String word) { slangWords.remove(word); }

    // * Feature 7
    protected void resetSlangWords() { slangWords.clear(); loadDefaultFile(); }

    // * Feature 8
    protected Object randomSlangWord() {
        Object[] keys = slangWords.keySet().toArray();
        Object key = keys[new Random().nextInt(keys.length)];
        return key;
    }

    // * Feature 9
    protected ArrayList<Object> getSlangWordQuiz(Object correctAnswer) {
        Object[] keys = slangWords.keySet().toArray();
        ArrayList<Object> answers = new ArrayList<>();
        answers.add(slangWords.get(correctAnswer));
        while (answers.size() < 4) {
            Object randomKey = keys[new Random().nextInt(keys.length)];
            String randomAnswer = slangWords.get(randomKey);
            if (!answers.contains(randomAnswer))
                answers.add(randomAnswer);
        }
        return answers;
    }

    // Feature 10
    protected ArrayList<Object> getDefinitionQuiz(Object correctAnswer) {
        ArrayList<Object> answers = new ArrayList<>();
        answers.add(correctAnswer.toString());
        while (answers.size() < 4) {
            Object randomDefinition = slangWords.get(randomSlangWord());
            if (!answers.contains(randomDefinition)) {
                ArrayList<String> matchingWords = searchByDefinition(randomDefinition.toString());
                answers.add(searchByDefinition(randomDefinition.toString()).get(new Random().nextInt(matchingWords.size())));
            }
        }
        return answers;
    }
}