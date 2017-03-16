import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.util.*;

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
    private Map<String, ArrayList> fregMap;


    public DictionaryGUI() throws Exception {


        //fregMap = Dict.getDictionary(Dict.Lang.Eng, Dict.Lang.Rus);
        // for (String item : fregMap.keySet()) { //извлечение множества ключей


        translateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textArea1 != null) {
                    if (enLabel.getText().equals("ENGLISH")) {
                        try {
                            fregMap = Dict.getDictionary(Dict.Lang.Eng, Dict.Lang.Rus);
                            showWord();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }

                    } else if (ruLabel.getText().equals("RUSSIAN"))
                        try {
                            fregMap = Dict.getDictionary(Dict.Lang.Rus, Dict.Lang.Eng);
                            showWord();
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

    protected void showWord() {
        StringBuffer stringBuffer = null;
        try {
            // TreeMap<String, ArrayList> word = (TreeMap<String, ArrayList>) fregMap;


//TODO java.lang.ClassCastException: java.util.ArrayList cannot be cast to java.lang.Comparable
//            for (Object item : fregMap.values()) { //множество значений
//                ArrayList valuesWord = fregMap.get(item);
//                for (Object value : valuesWord) {
//                    textArea2.setText(value.toString() + "\n");
//                }
//            }

            for(Map.Entry entry: fregMap.entrySet()) { { //множество значений
                ArrayList valuesWord = fregMap.get(entry.getValue());
                for (Object value : valuesWord) {
                    textArea2.setText(value.toString() + "\n");
                }
            }



          //      V value = entry.getValue();
            }

//            for (Map.Entry<String, String> entry : map.entrySet()) {
//                System.out.println("ID =  " + entry.getKey() + " День недели = " + entry.getValue());
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        } else {//if (enLabel.getText().equals("RUSSIAN")){
            enLabel.setText("ENGLISH");
            ruLabel.setText("RUSSIAN");
        }
    }


}
