
import java.util.concurrent.ThreadLocalRandom;

public class NeuralNetwork {

    private double[] weights;
    private double bias;

    private double[][] inputData;
    private double[] outputData;

    public NeuralNetwork(double[][] inputs, double[] outputs) {
        this.inputData = inputs;
        this.outputData = outputs;

        this.weights = generateWeights(inputData[0].length);
        this.bias = 1;
    }

    private double reluActivation(double x) {
        if (x < 0) {
            return 0;
        } else return x * 0.01; //Leaky ReLU
    }

    //calculates the matrix product
    private double dotProduct(double[] a, double[] b) {
        double[] products = new double[a.length];

        //first we do the multiplication
        for (int i = 0; i < a.length; i++) {
            products[i] = a[i] * b[i];
        }

        //then we calculate the product
        double result = 0;
        for (int x = 0; x < products.length; x++) {
            result += products[x];
        }
        return result;
    }

    private double[][] turnMatrix(double[][] a) {
        double[][] result = new double[a[0].length][a.length];

        for (int i = 0; i < a[0].length; i++) {
            for (int j = 0; j < a.length; j++) {
                result[i][j] = a[j][i];
            }
        }
        return result;
    }

    //this function calculates the error rate from the given output and the estimated output
    private double calcError(double outputReal, double outputsEst) {
        return outputReal - outputsEst;
    }

    //here the error rates are being multiplied by the estiamted output,
    // if the error rate was low then the est. output was close to being right
    private double calcGradiant(double error, double output) {
        return error * output;
    }

    private void adjustWeights(double[] gradProduct) {
        for (int j = 0; j < weights.length; j++) {
            weights[j] += gradProduct[j];
        }
    }

    private double[] feedforward() {
        double[] result = new double[outputData.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = prediction(inputData[i]);
        }
        return result;
    }

    private void backpropagation(double[] resultFF) {
        double[] error = new double[resultFF.length];
        double[] gradValue = new double[error.length];
        for (int i = 0; i < inputData.length; i++) {
            error[i] = calcError(outputData[i], resultFF[i]);
            gradValue[i] = calcGradiant(error[i], resultFF[i]);
        }

        double[][] turnedInputData = turnMatrix(inputData);
        double[] gradProduct = new double[weights.length];
        for (int j = 0; j < gradProduct.length; j++) {
            gradProduct[j] = dotProduct(turnedInputData[j], gradValue);
        }

        adjustWeights(gradProduct);
    }

    public void train(int trainingIteration) {
        for (int i = 0; i < trainingIteration; i++) {

            double[] output = feedforward();

            backpropagation(output);
        }
        System.out.println("Training done!");
    }

    public double prediction(double inputs[]) {

        double result = dotProduct(inputs, weights);

        return reluActivation(result + bias);
    }

    //creating random weights
    private double[] generateWeights(double count) {
        double[] weight = new double[(int) count];
        for (int i = 0; i < count; i++) {
            //Generating Weight with +-(1/Math.sqrt(inputNodes)) should be best practice
            weight[i] = ThreadLocalRandom.current().nextDouble(-(1 / Math.sqrt(count)), (1 / Math.sqrt(count)));
        }
        return weight;
    }
}
