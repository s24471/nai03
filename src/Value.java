import java.util.Arrays;

public class Value {
    String name;
    double[] arr;

    public Value(String name, String txt) {
        this.name = name;
        arr = new double[26];
        for (int i = 0; i < 26; i++) {
            arr[i] = 0;
        }
        int ile =0;
        String tmp = txt.toUpperCase();
        for (int j = 0; j < tmp.length(); j++) {
            char litera = tmp.charAt(j);
            if (litera >= 'A' && litera <= 'Z') {
                arr[litera - 'A']++;
                ile++;
            }
        }
        int total = ile;
        for (int i = 0; i < 26; i++) {
            arr[i] = arr[i] / total;
        }

    }

    @Override
    public String toString() {
        return "Value{" +
                "name='" + name + "'}";

    }
}
