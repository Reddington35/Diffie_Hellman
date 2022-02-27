import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Diffie_Hellman {

    // Member Variables used
    private int prime = 10000;
    private boolean isPrime = false;
    private ArrayList<Integer> arr = new ArrayList<Integer>();

    // Method for generating random prime numbers
    public int primeGenerator(){
        for(int i = prime;i < 100000;i++){
            boolean foundFactor = false;
            for(int j = 2;j <= Math.sqrt(i);j++){
                if(i % j == 0){
                    foundFactor = true;
                    break;
                }
            }
            if(!foundFactor){
                arr.add(i);
            }
        }
        int randomPrime = arr.get(ThreadLocalRandom.current().nextInt(0,arr.size()));
        System.out.println("Generated Prime Number = " + randomPrime);
        return  randomPrime;
    }
}


