public class Perceptron2 {
    public double a;
    public double b;
    public double[] w;
    public double O;
    public String looking;
    public Perceptron2(String looking) {
        w = new double[26];
        for (int i = 0; i < w.length; i++) {
            w[i] = Math.random() * 2 - 1;
            if(w[i]==0)w[i]+=0.1;
        }
        this.looking=looking;
        O=1;
        a=0.0000001;
        b=0.0000001;
    }


    public boolean get(double[] x){
        double suma = 0;
        for (int i = 0; i < w.length; i++) {
            suma+=x[i]*w[i];
        }
        return suma >= O;
    }
    public void normalize(){
        double s =0;
        for (double v : w) {
            s += v * v;
        }
        s=Math.sqrt(s);
        for (int i = 0; i < w.length; i++) {
            w[i]/=s;
        }
    }
    public void train(double[] x, String name){
        if(get(x)!=(name.equals(looking))){
            double d = (name.equals(looking)?1:0);
            double y = (get(x)?1:0);
            for (int i = 0; i < w.length; i++) {
                w[i]+=(d-y)*a*x[i];
            }
            O = O - (d-y)*b;
            normalize();
        }
    }
}