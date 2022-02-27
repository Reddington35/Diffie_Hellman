public class Driver {

    // Main Method used as a Driver for DiffieHellman methods
    public static void main(String args[]){
        Diffie_Hellman dif = new Diffie_Hellman();
        dif.primeGenerator();
        dif.primitiveRoot(17);
    }
}
