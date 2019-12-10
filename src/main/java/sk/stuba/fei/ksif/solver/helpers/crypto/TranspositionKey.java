package sk.stuba.fei.ksif.solver.helpers.common;

public class TranspositionKey extends Key {

    private Integer[] permutation = null;


    public TranspositionKey(Integer[] permutation) {
        this.permutation = permutation;
    }


    public Integer[] getPermutation() {
        return permutation;
    }
}