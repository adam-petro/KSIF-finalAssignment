package sk.stuba.fei.ksif.solver.methods;
import sk.stuba.fei.ksif.solver.helpers.crypto.Key;
import sk.stuba.fei.ksif.solver.helpers.crypto.TranspositionCipher;
import sk.stuba.fei.ksif.solver.helpers.crypto.TranspositionKey;
import sk.stuba.fei.ksif.solver.helpers.dictionary.Node;

import java.util.ArrayList;
import java.util.List;

public class DictionaryHC {


    public static double HillClimb(String ciphertext){
        List<String> words = Node.readDictionaryWords("dictionary_5000.txt");
        Node node = Node.loadDictionary(words);

        TranspositionCipher transpositionCipher  = new TranspositionCipher();
        TranspositionKey transpositionKey = new TranspositionKey(Key.randomKey(2));

        int cycles = 1000;
        int maxkeyLen = 20;
        double fitness = node.evaluate(ciphertext,2,10);
        String ot;
        Integer[] perm;


        for (int i=2;i<maxkeyLen;++i){
            perm = Key.randomKey(i);
            transpositionKey.setPermutation(perm);
            while(fitness<0.60){
                ot = transpositionCipher.decryption(transpositionKey,ciphertext);
                node.evaluate(ot,3,10);
            }
        }

        return 0.0;
    }
}
