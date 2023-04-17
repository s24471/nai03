public class Perceptron {
    private double[] weights;
    private double bias;
    String looking;
    private double learningRate;

    public Perceptron(String looking) {
        weights = new double[26];
        for (int i = 0; i < weights.length; i++) {
            weights[i] = Math.random() * 2 - 1;
            if(weights[i]==0) weights[i]+=0.1;
        }
        this.looking=looking;
        bias=1;
        learningRate=0.00001;
    }

    public int classify(double[] input) {
        double activation = this.computeActivation(input);
        return (activation >= 0) ? 1 : -1;
    }
    public void normalize(){
        double s =0;
        for (double v : weights) {
            s += v * v;
        }
        s=Math.sqrt(s);
        for (int i = 0; i < weights.length; i++) {
            weights[i]/=s;
        }
    }
    public void train(double[] input, String target) {
        double output = this.computeActivation(input);
        double error;
        if(target.equals(looking)) {
            error = 1 - output;
        }else{
            error = -1 - output;
        }

        for (int i = 0; i < this.weights.length; i++) {
            this.weights[i] += this.learningRate * error * input[i];
        }
        this.bias += this.learningRate * error;
        normalize();
    }

    public double computeActivation(double[] input) {
        double activation = 0;
        for (int i = 0; i < this.weights.length; i++) {
            activation += this.weights[i] * input[i];
        }
        activation += this.bias;
        return activation;
    }
}
