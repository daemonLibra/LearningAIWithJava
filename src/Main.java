import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {
        NeuralNetwork nn = new NeuralNetwork();

        //Input data for the Neural Network
        double [][] input = {{0,0,1}, {1,1,1}, {1,0,1}, {0,1,1}};

        //The expected output for the input data
        double [] output = {0,1,1,0};

        //The data which we want predicted after the training
        double [] testInput = {0,0,1};

        //give the Neural Network input data and the output plus we set the training iterations
        nn.train(input,output,10000);

        System.out.println("The estimated output is:");
        System.out.println(nn.thinking(testInput));
    }
}
