package app;

public class SlangWord {
    private String word;
    private String meaning;

    public SlangWord(String word, String meaning) {
        this.word = word;
        this.meaning = meaning;
    }

    public String getWord() { return word; }
    public String getMeaning() { return meaning; }
    public void setWord(String word) { this.word = word; }
    public void setMeaning(String meaning) { this.meaning = meaning; }

    @Override
    public String toString() { return word + ": " + meaning; }
}