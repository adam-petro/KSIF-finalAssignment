package sk.stuba.fei.ksif.solver.helpers.crypto;

public abstract class Cipher<CipherKey> {

    public abstract String encryption(CipherKey key, String string, Boolean row);

    public abstract String decryption(CipherKey key, String string, Boolean row);

    public int intDivideToUp(int a, int b) {
        float retVal = 0;

        retVal = (float)a/(float)b;


        if (retVal > ((int)retVal)){
            retVal++;
        }

        return (int)retVal;
    }
}