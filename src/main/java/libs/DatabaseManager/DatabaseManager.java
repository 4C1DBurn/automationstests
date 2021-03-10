package libs.DatabaseManager;

import libs.xmlConfigReader.xmlConfigReader;

import java.io.File;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseManager {
    private String db_name;
    private Connection conn;
    private xmlConfigReader configs;

    public DatabaseManager(String db_name) {
        configs = new xmlConfigReader();
        this.db_name = db_name;

    }

    public void createDataBase() {
        try {
            Class.forName("org.sqlite.JDBC");
            this.conn = DriverManager.getConnection("jdbc:sqlite:"+System.getProperty("user.dir") + File.separatorChar + configs.getDbPath()+ db_name + ".db");
            System.out.println("Done");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void createTable() {
        try {
            Class.forName("org.sqlite.JDBC");
            this.conn = DriverManager.getConnection("jdbc:sqlite:"+System.getProperty("user.dir") + File.separatorChar + configs.getDbPath()+ db_name + ".db");
            Statement stmt = this.conn.createStatement();
            String table = "create table IF NOT EXISTS Countries ("
                    + "Country varchar(50)," + "Population int(50), " + "Area int(50)" + ")";
            stmt.execute(table);
            System.out.println("Done");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ResultSet selectCustom(String query) {
        try {
            this.conn = DriverManager.getConnection("jdbc:sqlite:"+System.getProperty("user.dir") + File.separatorChar + configs.getDbPath()+ db_name + ".db");
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            return rs;
        } catch (Exception e) {

            System.out.println(e);
            return null;
        }
    }

        public boolean insert (String query){
            try {
                this.conn = DriverManager.getConnection("jdbc:sqlite:" + System.getProperty("user.dir") + File.separatorChar + configs.getDbPath() + db_name + ".db");
                PreparedStatement pstmt = this.conn.prepareStatement(query);
                return pstmt.executeUpdate() == 1;
            } catch (Exception e) {
                System.out.println(e);
            }
            return false;
        }

        public List resultSetToArrayList (ResultSet rs) throws SQLException {
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
            List<Map<String, Object>> list = new ArrayList<>();
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>(columns);
                for (int i = 1; i <= columns; ++i) {
                    row.put(md.getColumnName(i), rs.getObject(i));
                }
                list.add(row);
            }

            return list;
        }

    }