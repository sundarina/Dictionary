import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by sun on 14.03.17.
 */
public class DictionaryGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Dictionary");
        frame.setContentPane(new DictionaryGUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
//        Dialog dialog = new Dialog();
//        dialog.pack();
//        dialog.setVisible(true);

    }

    DictionaryDAO dao = new DictionaryDAO();

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


    public DictionaryGUI() {

       translateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textArea1 != null){
                    if(enLabel.getText().equals("ENGLISH")) {
                        showWordEn();
                    } else if (ruLabel.getText().equals("RUSSIAN"))
                        showWordRu();
                }

               // JOptionPane.showMessageDialog(null, "Hello!");
            }
        });

        cleanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textArea1 !=null && textArea2 !=null)
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

    protected void showWordEn() {
        try {
            TreeMap<WordEn, WordRu> word = dao.getWordEnRu();
            for(Map.Entry<WordEn, WordRu> item : word.entrySet()){
                if (item.getKey().getWordEn().equals(textArea1.getText()));
                StringBuffer stringBuffer = new StringBuffer(item.getValue().getWordRu() + "\n");
                textArea2.setText(String.valueOf(stringBuffer));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void showWordRu() {
        try {
            TreeMap<WordEn, WordRu> word = dao.getWordEnRu();
            for(Map.Entry<WordEn, WordRu> item : word.entrySet()){
                if (item.getValue().getWordRu().equals(textArea1.getText()));
                StringBuffer stringBuffer = new StringBuffer(item.getKey().getWordEn() + "\n");
                textArea2.setText(String.valueOf(stringBuffer));
            }
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

    protected void changeLanguage(){
        if (enLabel.getText().equals("ENGLISH")){
            enLabel.setText("RUSSIAN");
            ruLabel.setText("ENGLISH");
        } else {//if (enLabel.getText().equals("RUSSIAN")){
            enLabel.setText("ENGLISH");
            ruLabel.setText("RUSSIAN");
        }
    }


}
