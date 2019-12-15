package sk.stuba.fei.ksif.solver.methods;
import sk.stuba.fei.ksif.solver.helpers.bigram.Bigram;
import sk.stuba.fei.ksif.solver.helpers.common.Misc;
import sk.stuba.fei.ksif.solver.helpers.crypto.Key;
import sk.stuba.fei.ksif.solver.helpers.crypto.TranspositionCipher;
import sk.stuba.fei.ksif.solver.helpers.crypto.TranspositionKey;
import sk.stuba.fei.ksif.solver.helpers.dictionary.Node;
import sk.stuba.fei.ksif.solver.helpers.common.Misc.*;

import java.lang.reflect.Array;
import java.util.*;

import static sk.stuba.fei.ksif.solver.helpers.common.Misc.fac;


public class DictionaryHC{

    public static void HillClimb(String ciphertext, Language language){

        final int maxkeyLen = 13;
        final int minKeyLen = 2;
        final int maxIterations = 1000000;

        List<String> words;

        if(language == Language.SK){
            words = Node.readDictionaryWords("sk.telegraf.dic");
        }
        else{
            words = Node.readDictionaryWords("dictionary_5000.txt");
        }
        Node node = Node.loadDictionary(words);

        TranspositionCipher transpositionCipher  = new TranspositionCipher();
        TranspositionKey transpositionKey = new TranspositionKey(Key.randomKey(2));

        String ot1 = transpositionCipher.decryption(transpositionKey,ciphertext,true);
        String ot2 = transpositionCipher.decryption(transpositionKey,ciphertext,true);
        String bestot;
        int[] factorials = new int[maxkeyLen];
        for(int fac = 0; fac < maxkeyLen;fac++){
            factorials[fac]=fac(fac+1);
        }
        double fitness = node.evaluate(ciphertext,2,10);
        double fitness1;
        double fitness2;
        double bestfitness = fitness;

        Integer[] perm;
        perm = Key.randomKey(minKeyLen);
        Map<Integer[],String>bestVals = new LinkedHashMap<>();

        int iteration = 0;


        long startTime = System.nanoTime();
        for (int i=minKeyLen;i<=maxkeyLen;i++){

            perm = Key.randomKey(i);
            transpositionKey.setPermutation(perm);

            for(int j = 0; j < factorials[i-1]/2;j++) {
                iteration++;

                //if(j>factorials[i])break;
                //swap

                Integer[] oldPerm = perm;
                Key.swap(perm);

                ot1 = transpositionCipher.decryption(transpositionKey,ciphertext, true);
                ot2 = transpositionCipher.decryption(transpositionKey,ciphertext, false);

                //vyhodnotenie fitness
                fitness1 = node.evaluate(ot1,2,15);
                fitness2 = node.evaluate(ot2,2,15);
               // boolean row;
                if (fitness1>fitness2){
                    bestot = ot1;
                    fitness=fitness1;
                    //row = true;
                }
                else{
                    bestot=ot2;
                    fitness=fitness2;
                    //row = false;
                }

                //ak je sused lepsi
                if (fitness > bestfitness) {
                    System.out.print(i + ". " + iteration + ". " + fitness + " "
                            + Arrays.toString(transpositionKey.getPermutation()));
                   /* if(row)
                        System.out.print(" Riadkova");
                        else
                        System.out.print(" Stlpcova");
*/
                    System.out.println(" time:" + ((System.nanoTime() - startTime) / 1000000) + "ms\n" + bestot);
                    bestfitness = fitness;
                    bestVals.put(perm, bestot);

                }


                //ak nie vratim sa spat
                else {
                    perm = oldPerm;
                }

                //Ak je pocet iteracii pre permutaciu jednej dlzky vacsi ako dana konstatna
                if (iteration == maxIterations) {
                    iteration = 0;
                    break;
                }
            }
        }
    }
}
