import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Diffie_Hellman {

    // Member Variables used
    private int prime = 10000;
    private int moduloBreak = 3;
    private int p = 0;
    private ArrayList<Integer> arr = new ArrayList<Integer>();

    // Method for generating random prime numbers
    public int primeGenerator() {
        for (int i = prime; i < 100000; i++) {
            boolean foundFactor = false;
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    foundFactor = true;
                    break;
                }
            }
            if (!foundFactor) {
                arr.add(i);
            }
        }
        int randomPrime = arr.get(ThreadLocalRandom.current().nextInt(0, arr.size()));
        System.out.println("Generated Prime Number = " + randomPrime);
        return randomPrime;
    }

    // Method for splitting large primes to prevent overflow
    public int largeModulous(int number, int power, int modulous){
        //System.out.println(number + " ^ " + " mod " + modulous);
        int breakNum = 1;
        int breakPoint = power / breakNum;
        int remainder = power % breakNum;

        int count = 1;
        for (int k = 0; k < breakPoint; k++) {
            count *= (int) Math.pow(number,breakNum) % modulous;
            count = count % modulous;
        }
        if (remainder != 0) {
            count *= (int) Math.pow(number, remainder) % modulous;
        }
        count = count % modulous;
        return count;
    }

    // Method for finding Primitive root
    public int primitiveRoot(int prime) {
        HashSet<Integer> prim = new HashSet<Integer>();
        ArrayList<Integer> p = new ArrayList<Integer>();
        for (int i = 1; i < prime; i++) {
            ArrayList<Integer> column = new ArrayList<Integer>();
            for (int j = 1; j < prime; j++) {
                int count = largeModulous(i, j, prime);
                column.add(count);
            }
            prim = new HashSet<Integer>(column);
            if (column.size() == prim.size()) {
                p.add(i);
            }
        }
        System.out.println("Primitive roots are " + p);
        System.out.println("Random Primitive root is "
                + p.get(ThreadLocalRandom.current().nextInt(0, p.size())));
        return p.get(ThreadLocalRandom.current().nextInt(0, p.size()));
    }

    // Method for calculating Prime
    public int calculatePrime(int number, int power, int modulous){
        int breakNum = 1;
        int breakPoint = power / breakNum;
        int remainder = power % breakNum;

        int count = 1;
        for (int k = 0; k < breakPoint; k++) {
            count *= (int) Math.pow(number, breakNum) % modulous;
            count = count % modulous;
        }
        if (remainder != 0) {
            count *= (int) Math.pow(number, remainder) % modulous;
        }
        count = count % modulous;
        System.out.println("Calculated prime = " + count);
        return count;
    }

    public void keyExchange(){
        int randPrime = primeGenerator();
        int root = primitiveRoot(109);
        int calcPrime = calculatePrime(randPrime,3,5);
    }
}


