package sk.stuba.fei.ksif.solver.methods;
import sk.stuba.fei.ksif.solver.helpers.crypto.Key;
import sk.stuba.fei.ksif.solver.helpers.crypto.TranspositionCipher;
import sk.stuba.fei.ksif.solver.helpers.crypto.TranspositionKey;
import sk.stuba.fei.ksif.solver.helpers.dictionary.Node;

import java.util.Arrays;
import java.util.List;

import static sk.stuba.fei.ksif.solver.helpers.common.Misc.fac;


public class DictionaryHC {


    public static double HillClimb(String ciphertext){

        final int maxkeyLen = 14;
        final int minKeyLen = 2;
        final int maxIterations = 1000000;


        List<String> words = Node.readDictionaryWords("dictionary_5000.txt");
        Node node = Node.loadDictionary(words);

        TranspositionCipher transpositionCipher  = new TranspositionCipher();
        TranspositionKey transpositionKey = new TranspositionKey(Key.randomKey(2));


        String ot = transpositionCipher.decryption(transpositionKey,ciphertext);
        int[] factorials = new int[maxkeyLen];
        for(int fac = 0; fac < maxkeyLen;fac++){
            factorials[fac]=fac(fac+1);
        }
        double fitness = node.evaluate(ciphertext,2,10);
        double bestfitness = fitness;

        Integer[] perm,bestPerm;
        perm = Key.randomKey(2);
        bestPerm = perm;
        String bestot = new String();
        int iteration = 0;

        for (int i=minKeyLen;i<maxkeyLen;++i){

            transpositionKey.setPermutation(perm);

            for(int j = 0; j < factorials[i-1];j++){
                iteration++;

                //if(j>factorials[i])break;
                //swap

                Key.swap(perm);
                Integer[] oldPerm = perm;

                //OT
                ot = transpositionCipher.decryption(transpositionKey,ciphertext);

                //vyhodnotenie fitness
                fitness = node.evaluate(ot,2,10);

                //ak je sused lepsi
                if(fitness > bestfitness){
                    System.out.println(i+". "+iteration+". "+fitness+" "+transpositionKey.toString()+"\n"+ot);
                    //if(fitness>0.63)break;
                    bestfitness=fitness;
                    bestot=ot;
                    bestPerm = perm;
                }


                //ak nie vratim sa spat
                else {
                    perm = oldPerm;
                }

                //Ak je pocet iteracii pre permutaciu jednej dlzky vacsi ako dana konstatna
                if (iteration==maxIterations){
                    iteration=0;
                    break;
                }
            }
        }
        System.out.println(bestPerm+"\n"+bestot);

        return 0.0;
    }
}