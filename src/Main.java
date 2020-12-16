public class Main {

    public static void main(String[] args) {
        //Input data for the Neural Network
        double[][] input = {{0, 0, 1}, {1, 1, 1}, {1, 0, 1}, {0, 1, 1}};

        //The expected output for the input data
        double[] output = {0, 1, 1, 0};

        //The data which we want predicted after the training
        double[] testInput = {1, 0, 0};

        //give the Neural Network input data and the output plus we set the training iterations
        NeuralNetwork nn = new NeuralNetwork(input, output);
        nn.train(10000);

        System.out.println("------------------------");
        System.out.println("The estimated output is:");
        System.out.println(nn.prediction(testInput));
    }
}
