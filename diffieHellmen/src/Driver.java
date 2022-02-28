public class Driver {

    // Main Method used as a Driver for DiffieHellman methods
    public static void main(String args[]){
        Diffie_Hellman dif = new Diffie_Hellman();
        dif.primeGenerator();
        dif.primitiveRoot(941);
        dif.calculatePrime(3,2,5);
    }
}
