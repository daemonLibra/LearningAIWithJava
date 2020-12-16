
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

    //calculates the matrix product, in this case we have a 4x3 matrix * a 3x1 matrix/vector
    private double dotProduct(double[] a, double[] b){
        double[] products = new double[a.length];

        //first we do the multiplication
        for(int i = 0; i < a.length; i++){
            products[i] = a[i] * b[i];
        }

        //then we calculate the product
        double result = 0;
        for(int x = 0; x <products.length; x++){
                result += products[x];
            }
        return result;
    }

    private double[][] turnMatrix(double[][] a){
        double[][] result = new double[a[0].length][a.length];

        for(int i = 0; i < a[i].length; i++){
            for(int j = 0; j < a.length; j++){
                result[i][j] = a[j][i];
            }
        }
        return result;
    }

    //this function calculates the error rate from the given output and the estimated output
    private double calcError(double outputReal, double outputsEst){
        return outputReal-outputsEst;
    }

    //here the error rates are being multiplied by the estiamted output,
    // if the error rate was low then the est. output was close to being right
    private double adjustmentInputs(double error, double output){
        return error * sigmoidDerivative(output);
    }

    public void train(double inputs[][], double[] outputs, int trainingIteration){
        for (int i = 0; i < trainingIteration;i++){

            double[] output = new double[outputs.length];
            double[] error = new double[output.length];
            double[] loss = new double[error.length];
            for(int cInputs = 0; cInputs < inputs.length; cInputs++){
                output[cInputs] = think(inputs[cInputs]);
                error[cInputs] = calcError(outputs[cInputs], output[cInputs]);
                loss[cInputs] = adjustmentInputs(error[cInputs], output[cInputs]);
            }

            double[][] turnedInput = turnMatrix(inputs);
            double[] adjustment = new double[weights.length];
            for(int k = 0; k < adjustment.length; k++){
               adjustment[k] = dotProduct(turnedInput[k], loss);
            }

            //the training weights are being adjusted
            for (int j = 0; j < weights.length; j++) {
                weights[j] += adjustment[j];
            }

            printLoss(error, i);
        }
        System.out.println("Training done!");
    }

    private void printLoss(double [] error, int iteration){
        double loss = 0;

        for (int a = 0; a < error.length; a++){
            loss += error[a];
        }

        System.out.println("Loss: " + loss + " Iteration: " + iteration);
    }

    public double think(double inputs[]){

        double result = dotProduct(inputs, weights);

        return sigmoidNormalisation(result);
    }

    //creating random weights at the begining, 3x1 matrix
    private void generateWeights(){
        System.out.println("Generating Weights...");
        for (int i = 0; i < 3; i++){
            weights[i] = ThreadLocalRandom.current().nextDouble(2) -1;
            System.out.println(weights[i]);
        }
        System.out.println("Weights generation done!");
    }
}
