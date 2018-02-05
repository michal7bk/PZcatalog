package pl.bak.word;

public class Word {

    private final String value;

    private Word(String value) {
        this.value = value;
    }

    public static Word of(String value) {
        return new Word(value);
    }

    public String getValue() {
        return value;
    }




}
