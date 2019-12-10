package sk.stuba.fei.ksif.solver.helpers.crypto;

import java.util.List;
import java.util.Random;

public abstract class Key {

    public static Integer[] randomKey(int len){
        Integer out[] = new Integer[len];

        for (int i=0;i<len;i++){
            out[i]=i;
        }
        return out;
    }
    public static int[] swap(int[] perm){
        Random random = new Random();
        int index1,index2;
        do{
            index1 = random.nextInt(perm.length);
            index2 = random.nextInt(perm.length);
        }
        while(index1==index2);
        int temp = perm[index1];
        perm[index1] = perm[index2];
        perm[index2] = temp;
        return perm;
    }
}
