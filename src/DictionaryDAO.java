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

    static Map<String, ArrayList> getDictionary(Lang K, Lang V) throws Exception {
        DictionaryDAO dao = new DictionaryDAO();
        Map<String, ArrayList> fregMap = new TreeMap<>();
        Map<String, ArrayList> words = dao.getWordEnRu();
      //  System.out.println("step 2" + words);
        for (Map.Entry<String, ArrayList> item : words.entrySet()) {
            String[] str = new String[2];
            str[0] = item.getKey();
            //как сделать так, чтобы потом каждое слово из ArrayList было вставлено как value
            str[1] = String.valueOf(item.getValue()); //ошибка
            ArrayList value = words.get(str[K.getN()]);
            if (value == null) value = new ArrayList();
            value.add(str[V.getN()]);
            fregMap.put(str[K.getN()], value);
        }
       // System.out.println("step 3" + fregMap);
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


//    public String[] getWordEnRu() throws Exception {
//        ResultSet rs = null;
//        Connection con = getConnectionFrom();
//        con.setAutoCommit(false);
//        PreparedStatement ps = con.prepareStatement("Select word, value From dictionary.enRu");
//        String[] words = new String[2];
//        //cписок пар формирование словаря в фрегмап
//        try {
//            rs = ps.executeQuery();
//            con.commit();
//            while (rs.next()) {
//                words[0] = rs.getString(1);
//                words[1] = rs.getString(2);
//                System.out.println(words[0] + " " + words[1]);
//            }
//        } catch (SQLException e) {
//            System.err.println("SQLState: " + e.getSQLState()
//                    + "Error Message: " + e.getMessage());
//            con.rollback();
//        } finally {
//            // rs.close();
//            con.close();
//        }
//        return words;
//    }

//    public String getWord() throws Exception {
//
//        ResultSet rs = null;
//        Connection con = getConnectionFrom();
//        con.setAutoCommit(false);
//        PreparedStatement ps = con.prepareStatement("Select word, value From enRu");
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


    public TreeMap<String, ArrayList> getWordEnRu() throws Exception {
        TreeMap<String, ArrayList> word = new TreeMap<>();
        ResultSet rs = null;
        Connection con = getConnectionFrom();
        con.setAutoCommit(false);
        PreparedStatement ps = con.prepareStatement("Select word, value From enRu");

        try {
            rs = ps.executeQuery();
            con.commit();
            while (rs.next()) {
                WordClass wordClass = new WordClass();
                wordClass.setKeyWord(rs.getString(1));
                wordClass.setValueWord(rs.getString(2));
                ArrayList value = word.get(wordClass.getKeyWord()); //англо русский 1
                if (value == null) value = new ArrayList();
               // System.out.println(wordClass.getValueWord());
                value.add(wordClass.getValueWord());
                word.put(wordClass.getKeyWord(), value);
            }
        } catch (SQLException e) {
            System.err.println("SQLState: " + e.getSQLState()

                    + "Error Message: " + e.getMessage());
            con.rollback();
        } finally {
//            rs.close();
            con.close();
        }
        System.out.println("step 1" + word);
        System.out.println(word.get("list"));
        return word;
    }


}
