import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Set;
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
        Dialog dialog = new Dialog();
        dialog.pack();
        dialog.setVisible(true);

    }

    DictionaryDAO dao = new DictionaryDAO();

    private javax.swing.JButton icon;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JButton button3;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JLabel enLabel;
    private JLabel ruLabel;
    private JButton translateButton;

    public DictionaryGUI() {


       translateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textArea1 != null){
                    showWordEn();
                }
               // JOptionPane.showMessageDialog(null, "Hello!");
            }
        });
    }

    private void showWordEn() {
        try {
            TreeMap<WordEn, WordRu> word = dao.getWordEnRu();
            for(Map.Entry<WordEn, WordRu> item : word.entrySet()){
                if (item.getKey().getWordEn().equals(textArea1.getText()));
                textArea1.setText(item.getValue().getWordRu());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
