package pl.bak.messagefactory;

public class MsgDisplayFactory {

    public static MsgDisplay getInstance() {
        return new DialogDisplay();
    }

}