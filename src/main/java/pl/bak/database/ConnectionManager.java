package pl.bak.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager   {

    private static final Logger log = LogManager.getLogger(ConnectionManager.class);

    private static final ConnectionManager INSTANCE = new ConnectionManager();

    private GetConstant properites = new GetConstant();



    private ConnectionManager() {
    }



    public static ConnectionManager anInstance() {
        return INSTANCE;
    }

    public static Connection getConnection() {
        return anInstance().createConnection();
    }

    public Connection createConnection() {
        try {

            return DriverManager.getConnection(properites.getPropValues().get(0),properites.getPropValues().get(1),properites.getPropValues().get(2));
        } catch (Exception e) {
            log.error("Error while creating connection to DB", e);
            throw new DbException("Error while creating connection to DB", e);
        }
//
    }

}
