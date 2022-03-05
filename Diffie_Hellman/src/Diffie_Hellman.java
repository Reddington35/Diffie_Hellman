import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

public class Diffie_Hellman {

    // Member Variables used
    private int prime = 10000;
    private ArrayList<Integer> arr = new ArrayList<Integer>();

    // Method for generating random prime numbers
    public int primeGenerator() {
        // loops from 10000 to 100000
        for (int i = prime; i < 100000; i++) {
            // boolean declared here to check if prime found
            boolean foundFactor = false;
            // loop starting from index 2 to the square root of i
            for (int j = 2; j <= Math.sqrt(i); j++) {
                // if i mod j = 0, prime is then found as it is only dividable by 1 and itself
                // therefore cannot = 0
                if (i % j == 0) {
                    foundFactor = true;
                    break;
                }
            }
            // if prime add to ArrayList arr
            if (!foundFactor) {
                arr.add(i);
            }
        }
        // returns a random integer value in the given ArrayList
        int randomPrime = arr.get(ThreadLocalRandom.current().nextInt(0, arr.size()));
        System.out.println("Generated Prime Number = " + randomPrime);
        return randomPrime;
    }

    // Method for splitting large primes to prevent overflow
    // some logic was taken from Geeks for Geeks
    // (Primitive root of a prime number n modulo n - GeeksforGeeks, 2022)
    public int largeModulous(int number, int power, int modulous){
        // print to check calculations in testing
        //System.out.println(number + " ^ " + power + " % " + modules);
        // count set to 1
        int count = 1;
        // stores modules of number in given int
        number = number % modulous;
        // while loop used to calculate if power mod 2 = 1
        // then power is prime then multiply count by number mod modules
        while(power > 0){
            if(power % 2 == 1){
                count = (count * number) % modulous;
            }
            // then divide power by 2 and multiply the number by itself mod modules
            power = power / 2;
            number = (number * number) % modulous;
        }
        //returns the count
        return count;
    }

    // Method for finding Primitive root
    // some logic was taken from Geeks for Geeks
    // (Primitive root of a prime number n modulo n - GeeksforGeeks, 2022)
    public int primitiveRoot(int prime) {
        HashSet<Integer> prim = new HashSet<Integer>();
        ArrayList<Integer> p = new ArrayList<Integer>();
        for (int i = 1; i < prime; i++) {
            ArrayList<Integer> column = new ArrayList<Integer>();
            for (int j = 1; j < prime; j++) {
                int count = largeModulous(i, j, prime);
                column.add(count);
            }

            // Hashset used here to remove duplicates
            prim = new HashSet<Integer>(column);
            // if column ArrayList size is the same as Hashset list
            // then it is a primitive root and is stored in ArrayList p
            if (column.size() == prim.size()) {
                p.add(i);
            }
        }
        System.out.println("Primitive roots are " + p);
        System.out.println("Random Primitive root is "
                + p.get(ThreadLocalRandom.current().nextInt(0, p.size())));
        // returns a random value in ArrayList p
        return p.get(ThreadLocalRandom.current().nextInt(0, p.size()));
    }
}
// References and Bibliography
// GeeksforGeeks. 2022. Primitive root of a prime number n modulo n - GeeksforGeeks. [online] Available at: <https://www.geeksforgeeks.org/primitive-root-of-a-prime-number-n-modulo-n/> [Accessed 3 March 2022].