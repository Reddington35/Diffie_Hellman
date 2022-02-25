import java.util.Random;

public class diffieHellmen {

    public void randomGenerator(int prime) {
        int min = 10000;
        int max = 100000;
        boolean isPrime = true;
        for (int i = 2; i < max; i++) {
            Random rand = new Random();
            prime = min + rand.nextInt(max);
        }
        if(prime % 2 == 0){
            isPrime = false;
        }
        System.out.println("practice");

    }
}
