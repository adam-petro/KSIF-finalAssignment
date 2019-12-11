package sk.stuba.fei.ksif.solver.helpers.crypto;

import java.util.Arrays;

public class TranspositionKey extends Key {


    private Integer[] permutation = null;


    public TranspositionKey(Integer[] permutation) {
        this.permutation = permutation;
    }

    public String toString() {
        return ""+ Arrays.toString(permutation);
    }

    public Integer[] getPermutation() {
        return permutation;
    }
    public void setPermutation(Integer[] permutation) {
        this.permutation = permutation;
    }

}