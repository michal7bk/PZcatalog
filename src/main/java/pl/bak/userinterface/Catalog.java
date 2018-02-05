package pl.bak.userinterface;


import java.io.IOException;

public class Catalog {



    public static void main(String[] args) {

        try {
            new Window();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
