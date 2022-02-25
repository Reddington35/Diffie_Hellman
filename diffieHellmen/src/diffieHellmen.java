import java.util.Random;

public class diffieHellmen {
    //new
    public void randomGenerator(int prime) {
        int min = 10000;
        int max = 100000;
        boolean isPrime = true;
        for (int i = min; i < max; i++) {
            Random rand = new Random();
            prime = min + rand.nextInt(max);
        }
        if(prime % 2 == 0){
            isPrime = false;
        }
    }
}
