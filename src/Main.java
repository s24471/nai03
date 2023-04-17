import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        new Main();
    }

    public PerceptronLayer perceptrons;
    public Map<String, ArrayList<Value>> valuesTrain;
    public Map<String, ArrayList<Value>> valuesTest;

    public Main(){
        Map<String, ArrayList<String>> train = DirectoryParser.get("data/");
        Map<String, ArrayList<String>> test = DirectoryParser.get("test/");
        valuesTrain = new HashMap<>();
        for (String i: train.keySet()) {
            valuesTrain.put(i, new ArrayList<>());
            for(String j: train.get(i)){
                valuesTrain.get(i).add(new Value(i, j));
            }
        }
        perceptrons = new PerceptronLayer(train.keySet().toArray(new String[0]));
        valuesTest = new HashMap<>();
        for (String i: test.keySet()) {
            valuesTest.put(i, new ArrayList<>());
            for(String j: test.get(i)){
                valuesTest.get(i).add(new Value(i, j));
            }
        }
        boolean good = false;
        int dobrze = 0;
        int zle = 0;
        while(!good){
            good = true;
            dobrze = 0;
            zle = 0;
            for (ArrayList<Value> j : valuesTrain.values()) {
                for (Value k : j) {
                    perceptrons.train(k.arr, k.name);
                }
            }
            for (ArrayList<Value> j : valuesTest.values()) {
                int i=0;
                for (Value k : j) {
                    i++;
                    if (!(perceptrons.classify(k.arr).equals(k.name))) {
                        //System.out.println(k.name + " " + i);
                        zle++;
                        good = false;
                    } else {
                        dobrze++;
                    }
                }
            }
            System.out.println(dobrze + " " + zle);
        }
        System.out.println(dobrze + " " + zle);

        manualInput(perceptrons);
    }

    public static void manualInput(PerceptronLayer perceptrons) {
        JFrame frame = new JFrame("mpp3");
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel jLabel = new JLabel("Wynik");
        panel.add(jLabel);
        JButton button = new JButton("Sprawdz");

        panel.add(button);
        JTextArea jTextArea = new JTextArea("Wprowadz tekst lub wpisz 'exit', aby zakonczyc");
        panel.add(jTextArea);

        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String txt =jTextArea.getText();
                if (txt.equalsIgnoreCase("exit")) {
                    return;
                }

                jLabel.setText("Wynik: " + perceptrons.classify(new Value("input", txt).arr));
                jTextArea.setText("Wprowadz tekst lub wpisz 'exit', aby zakonczyc:");
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }

}