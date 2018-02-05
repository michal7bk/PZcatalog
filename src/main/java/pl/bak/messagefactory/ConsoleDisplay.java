package pl.bak.messagefactory;

class ConsoleDisplay implements MsgDisplay {
    public void show(String s) {
        System.out.println(s);
    }
}