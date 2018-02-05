package pl.bak.database;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GetConstant {
    private InputStream inputStream;

    public List<String> getPropValues() throws IOException{
        List<String> readedconst = new ArrayList<>();
        try {
            Properties prop = new Properties();
            String propFileName = "const.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null){
                prop.load(inputStream);
            }else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            String URL = prop.getProperty("URL");
            String Usser = prop.getProperty("User");
            String Password = prop.getProperty("Password");
            String Driver  = prop.getProperty("Driver");
            String JTextAreaRows = prop.getProperty("JTextAreaRows");
            String JTextAreaColumns = prop.getProperty("JTextAreaColumns");
            String ENDPOINT = prop.getProperty("ENDPOINT");
            String WindowName = prop.getProperty("WindowName");
            readedconst.add(URL);
            readedconst.add(Usser);
            readedconst.add(Password);
            readedconst.add(Driver);
            readedconst.add(JTextAreaRows);
            readedconst.add(JTextAreaColumns);
            readedconst.add(ENDPOINT);
            readedconst.add(WindowName);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return readedconst;
    }
}