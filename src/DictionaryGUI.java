import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sun on 14.03.17.
 */
public class DictionaryGUI {
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("Dictionary");
        frame.setContentPane(new DictionaryGUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
//        Dialog dialog = new Dialog();
//        dialog.pack();
//        dialog.setVisible(true);

    }

    // DictionaryDAO dao = new DictionaryDAO();

    private JButton changeButton;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JLabel enLabel;
    private JLabel ruLabel;
    private JButton translateButton;
    private JPanel panel5;
    private JButton cleanButton;
    Map<String, ArrayList> fregMap;

    public DictionaryGUI() throws Exception {

        // for (String item : fregMap.keySet()) { //извлечение множества ключей

        translateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textArea1 != null) {
                    if (enLabel.getText().equals("ENGLISH")) {
                        try {
                            fregMap = Dict.getDictionary(Dict.Lang.Eng, Dict.Lang.Rus);
                            ArrayList valuesWord = null;
                            valuesWord = fregMap.get(textArea1.getText());

                            for (Object value : valuesWord) {
                                textArea2.setText(value.toString());
                               //    textArea2.setText(findRegEx(value.toString()));
                            }

                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }

                    } else if (enLabel.getText().equals("RUSSIAN"))
                        try {
                            fregMap = Dict.getDictionary(Dict.Lang.Rus, Dict.Lang.Eng);
                            ArrayList valuesWord = null;
                            valuesWord = fregMap.get("[" + textArea1.getText() + "]");

                            for (Object value : valuesWord) {
                                textArea2.setText(value + "");
                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }

                }

                // JOptionPane.showMessageDialog(null, "Hello!");
            }
        });

        cleanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textArea1 != null && textArea2 != null)
                    cleanButton();
            }
        });

        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeLanguage();
            }
        });
    }




    private static String findRegEx(String userNameString) {
        String s = null;
        Pattern p = Pattern.compile("\\w"); //ничего не возвращает
      //  Pattern p = Pattern.compile("[^\\[\\]]"); //только посдеднюю букву
        Matcher m = p.matcher(userNameString);
        while (m.find()) {
            s = userNameString.substring(m.start(), m.end());
        }
        return s;
    }


    protected void cleanButton() {
        try {
            textArea1.setText("");
            textArea2.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void changeLanguage() {
        if (enLabel.getText().equals("ENGLISH")) {
            enLabel.setText("RUSSIAN");
            ruLabel.setText("ENGLISH");
        } else { //if (enLabel.getText().equals("RUSSIAN")){
            enLabel.setText("ENGLISH");
            ruLabel.setText("RUSSIAN");
        }
    }


}
