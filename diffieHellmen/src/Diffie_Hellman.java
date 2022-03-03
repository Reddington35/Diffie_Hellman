import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Diffie_Hellman {

    // Member Variables used
    private int prime = 100;
    private int moduloBreak = 3;
    private int p = 0;
    private ArrayList<Integer> arr = new ArrayList<Integer>();

    // Method for generating random prime numbers
    public int primeGenerator() {
        for (int i = prime; i < 1000; i++) {
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
        //System.out.println(number + " ^ " + power + " % " + modulous);
        int count = 1;
        number = number % modulous;
        while(power > 0){
            if(power % 2 == 1){
                count = (count * number) % modulous;
            }
            power = power / 2;
            number = (number * number) % modulous;
        }
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

    public void keyExchange(){
        int publicRandom = primeGenerator();
        System.out.println(publicRandom);
        int bob = 320;
        int alice = 3040;
        int root = primitiveRoot(publicRandom);

        int keyBob = largeModulous(root,alice,publicRandom);
        System.out.println("Bobs key is " + bob);
        System.out.println("After Key Exchange, Bob's key is " + keyBob);

        int keyAlice = largeModulous(root,bob,publicRandom);
        System.out.println("Alice's key is " + alice);
        System.out.println("After Key Exchange, Alice's key is " + keyAlice);


        if(largeModulous(root,alice,publicRandom) == keyBob){
            System.out.println("Bobs's shared key is " + keyAlice);
        }

        if(largeModulous(root,bob,publicRandom) == keyAlice){
            System.out.println("Alice's shared key is " + keyAlice);
        }

        System.out.println("Key exchange completed");
    }
}


