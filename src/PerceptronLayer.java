public class PerceptronLayer {
    private Perceptron[] perceptrons;

    public PerceptronLayer(String[] langs) {
        this.perceptrons = new Perceptron[langs.length];
        for (int i = 0; i < langs.length; i++) {
            this.perceptrons[i] = new Perceptron(langs[i]);
        }
    }

    public String classify(double[] input) {
        double[] outputs = new double[perceptrons.length];
        for (int i = 0; i < perceptrons.length; i++) {
            outputs[i] = perceptrons[i].computeActivation(input);
        }
        double max = -2;
        String lang = "";
        for (int i = 0; i < perceptrons.length; i++) {
            if(outputs[i]>max){
                max = outputs[i];
                lang = perceptrons[i].looking;
            }
        }
        return lang;
    }

    public void train(double[] input, String lang) {
        for (int i = 0; i < this.perceptrons.length; i++) {
            this.perceptrons[i].train(input, lang);
        }
    }
}
