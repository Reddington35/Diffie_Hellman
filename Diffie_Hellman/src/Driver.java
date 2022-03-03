public class Driver {

    // Main Method used as a Driver for DiffieHellman methods
    public static void main(String args[]){

        int XB =40,MA = 0,XA = 20;
        int XM = 10,YM = 30;
        Diffie_Hellman dif = new Diffie_Hellman();
        int q = dif.primeGenerator();
        int a = dif.primitiveRoot(q);

        int YA = dif.largeModulous(a,XA,q);
        int YB = dif.largeModulous(a,XB,q);

        // Assume that YA is sent to UserB by UserA and
        // YB is sent toi UserA by UserB
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

        System.out.println("Simulates Man in the middle attack");
        YM = dif.largeModulous(a,XM,q);

        // Generate UserA Key
        KA = dif.largeModulous(YM,XA,q);
        System.out.println("Alice's Key is " + KA);

        int KMa = dif.largeModulous(YA,XM,q);
        System.out.println("Mallory,s shared key is " + KMa);
        System.out.println("Alice Malory key exchange completed");

        // Generate UserB Key
        KB = dif.largeModulous(YM,XB,q);
        System.out.println("Bob's Key is " + KB);

        int KMb = dif.largeModulous(YB,XM,q);
        System.out.println("Mallory shared key is " + KMb);
        System.out.println("Malory - Bob key exchange completed");
    }
}

