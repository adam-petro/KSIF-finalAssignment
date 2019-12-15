package sk.stuba.fei.ksif.solver.methods;
import com.sun.org.apache.xerces.internal.xs.StringList;
import sk.stuba.fei.ksif.solver.helpers.common.Text;
import sk.stuba.fei.ksif.solver.helpers.common.TextStatistics;
import sk.stuba.fei.ksif.solver.helpers.crypto.Key;
import sk.stuba.fei.ksif.solver.helpers.crypto.TranspositionCipher;
import sk.stuba.fei.ksif.solver.helpers.crypto.TranspositionKey;
import sk.stuba.fei.ksif.solver.helpers.common.Misc.*;
import sk.stuba.fei.ksif.solver.helpers.bigram.Bigram;

import java.util.*;

import static sk.stuba.fei.ksif.solver.helpers.common.Misc.fac;

public class BigramHC {


    public static void HillClimb(String ciphertext, Language language){


        final int maxkeyLen = 13;
        final int minKeyLen = 2;
        final int maxIterations = 100000;


        TranspositionCipher transpositionCipher  = new TranspositionCipher();
        TranspositionKey transpositionKey = new TranspositionKey(Key.randomKey(minKeyLen));

        String ot1 = transpositionCipher.decryption(transpositionKey,ciphertext,true);
        String ot2 = transpositionCipher.decryption(transpositionKey,ciphertext,true);
        String bestot;
        int[] factorials = new int[maxkeyLen];
        for(int fac = 0; fac < maxkeyLen;fac++){
            factorials[fac]=fac(fac+1);
        }
        double fitness = Bigram.count(ciphertext, language);
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

            for(int j = 0; j < factorials[i-1]/2;j++){
                ++iteration;


                //swap

                Integer[] oldPerm = perm;
                Key.swap(perm);
                transpositionKey.setPermutation(perm);
                //OT
                ot1 = transpositionCipher.decryption(transpositionKey,ciphertext, true);
                ot2 = transpositionCipher.decryption(transpositionKey,ciphertext, false);
                ot1 = Text.convertToTSA(ot1,false);
                ot2 = Text.convertToTSA(ot2,false);

//
                        //vyhodnotenie fitness
                fitness1 = Bigram.count(ot1, language);
                fitness2 = Bigram.count(ot2, language);
                //boolean row;
                if (fitness1<fitness2){
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
                if(fitness < bestfitness){
                    System.out.print(i + ". " + iteration + ". " + fitness + " "
                            + Arrays.toString(transpositionKey.getPermutation()));
                   /* if(row)
                        System.out.print(" Riadkova");
                    else
                        System.out.print(" Stlpcova");
*/
                    System.out.println(" time:" + ((System.nanoTime() - startTime) / 1000000) + "ms\n" + bestot);
                    bestfitness=fitness;
                    bestVals.put(perm,bestot);

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
    }


}
