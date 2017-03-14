import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by sun on 14.03.17.
 */
public class DictionaryDAO {
    private Connection getConnectionFrom() throws Exception {
        // Подгрузка драйвера БД Derby
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        // Получение соединения с БД
        String url = "jdbc:mysql://localhost:3306/dictionary?autoReconnect=true&useSSL=false";
        return DriverManager.getConnection(
                url, "root", "92e3579a");
    }

    public TreeMap<WordEn, WordRu> getWordEnRu() throws Exception {
        TreeMap<WordEn, WordRu> word = new TreeMap<>();
        ResultSet rs = null;
        Connection con = getConnectionFrom();
        con.setAutoCommit(false);
        PreparedStatement ps = con.prepareStatement("Select word, value From dictionary.en-ru");
        WordEn en = new WordEn();
        WordRu ru = new WordRu();
        try {
            rs = ps.executeQuery();
            con.commit();
            while (rs.next()) {
                en.setWordEn(rs.getString(2));
                ru.setWordRu(rs.getString(3));
                word.put(en, ru);
            }
        } catch (SQLException e) {
            System.err.println("SQLState: " + e.getSQLState()

                    + "Error Message: " + e.getMessage());
            con.rollback();
        } finally {
            rs.close();
            con.close();
        }
        return word;
    }


}
