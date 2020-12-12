import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {
        NeuralNetwork nn = new NeuralNetwork();

        double [][] input = {{0,0,1}, {1,1,1}, {1,0,1}, {0,1,1}};
        double [] testInput = {1,0,0};

        double [] output = {0,1,1,0};

        nn.train(input,output,10000);

        System.out.println(nn.thinking(testInput));
    }
}
