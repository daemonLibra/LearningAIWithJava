# Learning AI with Java
Learning how to make an simple AI from scratch. Will also create a Docker Container for learning purpose.

## Materials
 - http://iamtrask.github.io/2015/07/12/basic-python-network/
 - https://medium.com/technology-invention-and-more/how-to-build-a-simple-neural-network-in-9-lines-of-python-code-cc8f23647ca1
 - https://machinelearningmastery.com/how-to-control-the-speed-and-stability-of-training-neural-networks-with-gradient-descent-batch-size/
 - https://machinelearningmastery.com/rectified-linear-activation-function-for-deep-learning-neural-networks/
 - https://mlfromscratch.com/neural-networks-explained/#/
 - https://www.youtube.com/watch?v=gAkwW2tuIqE for Docker
 - https://www.jetbrains.com/help/idea/running-a-java-app-in-a-container.html

| Input  | Output |
| ------------- | ------------- |
| 0 , 0 , 1  | 0  |
| 1 , 1 , 1  | 1  |
| 1 , 0 , 1  | 1  |
| 0 , 1 , 1  | 0  |

The left side is the training data and on the right is the output to the input data.

## Docker Container
You can download the docker container with the following command (make sure to install docker):

```bash
docker pull daemonlibra/learningaiwithjava:latest
```
 
and run the container with: 
 
```bash
docker run daemonlibra/learningaiwithjava
```
 
