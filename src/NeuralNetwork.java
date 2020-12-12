
import java.util.concurrent.ThreadLocalRandom;

public class NeuralNetwork {

    public double[] weights = {0,0,0};

    public NeuralNetwork(){
        generateWeights();
    }

    //This function is normalising data between 0 and 1
    private double sigmoidNormalisation(double x){
        return 1/(1 + Math.exp(-x));
    }

    private double sigmoidDerivative(double x){
        return x * (1 - x);
    }

    private double[] dotProduct(double[][] a, double[] b){
        double[][] products = new double[4][3];
        double[] result = new double[4];

        for(int i = 0; i < a.length; i++){
            for(int j = 0; j < a[i].length; j++){
                products[i][j] = a[i][j] * b[j];
            }
        }

        for(int x = 0; x <products.length; x++){
            double sum = 0;
            for (int y = 0; y <products[x].length; y++){
                sum += products[x][y];
            }
            result[x] = sum;
        }
        return result;
    }

    private double[] dotOtherProduct(double[][] a, double[] b){
        double[][] products = new double[4][3];
        double[] result = new double[3];

        for(int i = 0; i < a[i].length; i++){
            for(int j = 0; j < a.length; j++){
                products[j][i] = a[j][i] * b[j];
            }
        }

        for(int x = 0; x <products[x].length; x++){
            double sum = 0;
            for (int y = 0; y <products.length; y++){
                sum += products[y][x];
            }
            result[x] = sum;
        }
        return result;
    }

    private double[] calcError(double[] outputReal, double[] outputsEst){
        double[] result = new double[4];
        for (int i = 0; i < outputReal.length; i++){
            result[i] = outputReal[i]-outputsEst[i];
        }
        return result;
    }

    private double[] adjustmentInputs(double[] error, double[] output){
        double[] result = new double[4];
        for (int i = 0; i < error.length; i++){
            result[i] = error[i] * sigmoidDerivative(output[i]);
        }
        return result;
    }

    public void train(double inputs[][], double[] outputs, int trainingIteration){
        for (int i = 0; i < trainingIteration;i++){

            double[] output = think(inputs);

            double[] error = calcError(outputs,output);
            //System.out.println(error);

            double[] adjustment = dotOtherProduct(inputs, adjustmentInputs(error, output));

            for (int j = 0; j < weights.length; j++) {
                weights[j] += adjustment[j];
                //System.out.println(weights[j]);
            }
        }

        System.out.println("Training done!");
    }

    private double[] think(double inputs[][]){

        double[] result = dotProduct(inputs, weights);

        for (int a = 0; a < result.length; a++){
            result[a] = sigmoidNormalisation(result[a]);
        }

        return result;
    }

    public double thinking(double inputs[]){

        double[] result = new double[3];

        for (int i = 0; i < inputs.length; i++){
            result[i] = inputs[i] * weights[i];
        }

        double sum = 0;
        for (int a = 0; a < result.length; a++){
            sum += result[a];
        }

        return sigmoidNormalisation(sum);
    }

    private void generateWeights(){
        System.out.println("Generating Weights...");
        for (int i = 0; i < 3; i++){
            weights[i] = ThreadLocalRandom.current().nextDouble(2) -1;
            System.out.println(weights[i]);
        }
        System.out.println("Weights generation done!");
    }

}
