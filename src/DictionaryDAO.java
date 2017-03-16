import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by sun on 14.03.17.
 */

class Dict {
    enum Lang {
        Eng(0), Rus(1);
        private int n;

        public int getN() {
            return n;
        }

        Lang(int n) { //всегда публичный конструктор
            this.n = n;
        }
    }

    //считывания с file.txt и создание мапы
    static Map<String, ArrayList> getDictionary(Lang K, Lang V) throws Exception {
        DictionaryDAO dao = new DictionaryDAO();
        Map<String, ArrayList> fregMap = new TreeMap<>();
        String[] words = dao.getWordEnRu();
        for (int i = 0; i < words.length; i++) {
            System.out.print(words[i] + " ");
        }
        //TODO  java.lang.NullPointerException
        ArrayList value = fregMap.get(words[K.getN()]); //англо русский 1
        if (value == null) {
            value = new ArrayList();
            value.add(words[V.getN()]);
        }
        fregMap.put(words[K.getN()], value); //англ-русский 1 можно сделать классом, и добавить метод подстановки 0 и 1
        return fregMap;
    }
}


public class DictionaryDAO {
    private Connection getConnectionFrom() throws Exception {
        // Подгрузка драйвера БД Derby
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        // Получение соединения с БД
        String url = "jdbc:mysql://localhost:3306/dictionary?autoReconnect=true&useSSL=false";
        return DriverManager.getConnection(
                url, "root", "92e3579a");
    }


    public String[] getWordEnRu() throws Exception {
        ResultSet rs = null;
        Connection con = getConnectionFrom();
        con.setAutoCommit(false);
        PreparedStatement ps = con.prepareStatement("Select word, value From dictionary.enRu");
        String[] words = new String[2];
        try {
            rs = ps.executeQuery();
            con.commit();
            while (rs.next()) {

                words[0] = rs.getString(1);
                words[1] = rs.getString(2);
            }
        } catch (SQLException e) {
            System.err.println("SQLState: " + e.getSQLState()
                    + "Error Message: " + e.getMessage());
            con.rollback();
        } finally {
            // rs.close();
            con.close();
        }
        return words;
    }

//    public String getWord() throws Exception {
//
//        ResultSet rs = null;
//        Connection con = getConnectionFrom();
//        con.setAutoCommit(false);
//        PreparedStatement ps = con.prepareStatement("Select word, value From en-ru");
//        String word = "";
//        try {
//            rs = ps.executeQuery();
//            con.commit();
//            while (rs.next()) {
//                word = rs.getString(2);
//            }
//        } catch (SQLException e) {
//            System.err.println("SQLState: " + e.getSQLState()
//
//                    + "Error Message: " + e.getMessage());
//            con.rollback();
//        } finally {
//            rs.close();
//            con.close();
//        }
//        return word;
//    }


//    public TreeMap<WordEn, WordRu> getWordEnRu() throws Exception {
//        TreeMap<WordEn, WordRu> word = new TreeMap<>();
//        ResultSet rs = null;
//        Connection con = getConnectionFrom();
//        con.setAutoCommit(false);
//        PreparedStatement ps = con.prepareStatement("Select word, value From en-ru");
//
//        try {
//            rs = ps.executeQuery();
//            con.commit();
//            while (rs.next()) {
//                WordEn en = new WordEn();
//                WordRu ru = new WordRu();
//                en.setWordEn(rs.getString(2));
//                ru.setWordRu(rs.getString(3));
//                System.out.println("yyyy");
//                word.put(en, ru);
//                System.out.println("iiii");
//            }
//        } catch (SQLException e) {
//            System.err.println("SQLState: " + e.getSQLState()
//
//                    + "Error Message: " + e.getMessage());
//            con.rollback();
//        } finally {
//            rs.close();
//            con.close();
//        }
//        return word;
//    }


}
