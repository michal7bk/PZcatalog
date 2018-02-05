package pl.bak.catalog;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.bak.database.ConnectionManager;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CatalogRepository extends SwingWorker {

    private static final Logger log = LogManager.getLogger(CatalogRepository.class);
    private static final CatalogRepository INSTANCE = new CatalogRepository();

    public CatalogRepository() {
    }

    public static CatalogRepository anInstance() {
        return INSTANCE;
    }

    public CatalogEntry add(CatalogEntry entry) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO subzab(id, nazwa,opis) VALUES (?,?,?)");

            int id = nextId();
            ps.setInt(1, id);
            ps.setString(2, entry.getName());
            ps.setString(3, entry.getDescription());
            ps.executeUpdate();
            log.info("Record was added ");
            return new CatalogEntry(id, entry.getName(), entry.getDescription());
        } catch (SQLException e) {
            log.error("Error while inserting a catalog entry", e);
            throw new RuntimeException(e);
        }
    }

    public void remove(int id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM subzab WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.executeUpdate();
            log.info("Record was removed ");
        } catch (SQLException e) {
            log.error("Error while removing a catalog entry", e);
            throw new RuntimeException(e);
        }
    }


    public List<CatalogEntry> findName(String searches) {
        List<CatalogEntry> entries = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            Statement st = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, nazwa, opis FROM subzab WHERE nazwa = ?");
            preparedStatement.setString(1, searches);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("nazwa");
                String desc = rs.getString("opis");
                entries.add(new CatalogEntry(id, name, desc));
            }
            st.close();
        } catch (Exception s) {
            log.error("Error while searching : " + searches, s);
            throw new RuntimeException(s);
        }

        return entries;
    }

    public List<CatalogEntry> findAll() {
    List<CatalogEntry> entries = new ArrayList<>();
        try(
    Connection connection = ConnectionManager.getConnection())
    {
        Statement st = connection.createStatement();
        ResultSet rec = st.executeQuery("SELECT id, nazwa, opis FROM  subzab");
        while (rec.next()) {
            int id = rec.getInt("id");
            String name = rec.getString("nazwa");
            String desc = rec.getString("opis");
            entries.add(new CatalogEntry(id, name, desc));
        }

        st.close();
    } catch(
    Exception s)
    {
        log.error("Error while reading ID", s);
    }
        return entries;
}

    private int nextId() {
        int AutoIncrement = 0;
        try (
                Connection connection = ConnectionManager.getConnection();
                Statement st = connection.createStatement()) {
            ResultSet rec = st.executeQuery("SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_NAME = \"subzab\"");
            while (rec.next()) {
                AutoIncrement = rec.getInt(1);
            }
            st.close();
        } catch (SQLException s) {
            log.error("Error while reading Next_ID", s);
        } catch (Exception e) {
            System.out.print("błąd: " + e.toString()
                    + e.getMessage());
        }
        return (AutoIncrement);
    }

    public List<Integer> findAllIds() {
        List<Integer> ids = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            Statement st = connection.createStatement();
            ResultSet rec = st.executeQuery("SELECT id FROM  subzab");
            while (rec.next()) {
                ids.add(rec.getInt(1));
            }
        } catch (Exception e) {
            log.error("Error while reading number of records", e);
        }
        return ids;
    }



    @Override
    protected List<CatalogEntry> doInBackground() throws Exception {

        List<CatalogEntry> entries = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()) {

            Statement st = connection.createStatement();
            ResultSet rec = st.executeQuery("SELECT id, nazwa, opis FROM  subzab");
            while (rec.next()) {
                int id = rec.getInt("id");
                String name = rec.getString("nazwa");
                String desc = rec.getString("opis");
                entries.add(new CatalogEntry(id, name, desc));
            }

            st.close();
        } catch (Exception s) {
            log.error("Error while reading ID", s);
        }
        return entries;
    }


    @Override
    protected void done() {
        try {
            get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    public List<CatalogEntry>  returnstatm() throws ExecutionException, InterruptedException {
        return (List<CatalogEntry>)get();
    }
}
