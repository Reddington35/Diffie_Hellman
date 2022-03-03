import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

public class Diffie_Hellman {

    // Member Variables used
    private int prime = 10000;
    private int moduloBreak = 3;
    private int primRoot = 0;
    private int bob = 0;
    private int alice =0;
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

    public void keyExchange(int bob,int alice){
        int publicRandom = primeGenerator();
        System.out.println(publicRandom);
        this.bob = bob;
        this.alice = alice;
        int root = primitiveRoot(publicRandom);

        int bobKey = largeModulous(root,bob,publicRandom);
        int aliceKey = largeModulous(root,alice,publicRandom);

        System.out.println("Bobs key is " + bob);
        System.out.println("After Key Exchange, Bob's key is " + bobKey);
        bobKey = aliceKey;

        System.out.println("Alice's key is " + alice);
        System.out.println("After Key Exchange, Alice's key is " + aliceKey);
        aliceKey = bobKey;

        int sharedA = largeModulous(aliceKey,bobKey,publicRandom);
        int sharedB = largeModulous(bobKey,aliceKey,publicRandom);
        System.out.println("Alice,s shared key is " + sharedA);
        System.out.println("Bob,s shared key is " + sharedB);
        System.out.println("Key exchange completed");
    }
}


