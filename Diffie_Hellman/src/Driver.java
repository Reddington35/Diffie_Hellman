public class Driver {

    // Main Method used as a Driver for DiffieHellman methods
    public static void main(String args[]){

        // Diffie Hellman Opperations
        System.out.println("\nDiffie Hellman Opperations");
        System.out.println("**********************************************");
        int XB =40,MA = 0,XA = 20;
        int XM = 10,YM = 30;
        Diffie_Hellman dif = new Diffie_Hellman();
        int q = dif.primeGenerator();
        int a = dif.primitiveRoot(q);

        int YA = dif.largeModulous(a,XA,q);
        int YB = dif.largeModulous(a,XB,q);
        System.out.println("\n");

        // Assume that YA is sent to UserB by UserA and
        // YB is sent toi UserA by UserB
        System.out.println("Alice and Bob's Key Exchange");
        System.out.println("**********************************************");
        System.out.println("Bobs key is " + XA);
        System.out.println("After Key Exchange, Bob's key is " + YA);

        System.out.println("Alice's key is " + XB);
        System.out.println("After Key Exchange, Alice's key is " + YB);

        // Generate UserA Key
        int KA = dif.largeModulous(YB,XA,q);

        // Generate UserB key
        int KB = dif.largeModulous(YA,XB,q);
        System.out.println("Alice,s shared key is " + KA);
        System.out.println("Bob,s shared key is " + KB);
        System.out.println("Key exchange completed");

        // Simulates Man in the middle attack
        System.out.println("\nSimulates Man in the middle attack");
        System.out.println("**********************************************");
        YM = dif.largeModulous(a,XM,q);

        // Generate UserA Key
        KA = dif.largeModulous(YM,XA,q);
        System.out.println("Alice's Shared Key is " + KA);

        // Alice thinks she is communicating with Bob, however Mallory has hijacked the communication
        int KMa = dif.largeModulous(YA,XM,q);
        System.out.println("Mallory,s shared key is " + KMa);
        System.out.println("Alice - Malory key exchange completed");

        // Generate UserB Key
        KB = dif.largeModulous(YM,XB,q);
        System.out.println("Bob's Shared Key is " + KB);

        // Bob thinks he is communicating with Alice, however Mallory has hijacked the communication
        int KMb = dif.largeModulous(YB,XM,q);
        System.out.println("Mallory shared key is " + KMb);
        System.out.println("Bob - Mallory key exchange completed");
    }
}

//  References and Bibliography
//  GeeksforGeeks. 2022. Primitive root of a prime number n modulo n - GeeksforGeeks. [online] Available at: <https://www.geeksforgeeks.org/primitive-root-of-a-prime-number-n-modulo-n/> [Accessed 3 March 2022].

