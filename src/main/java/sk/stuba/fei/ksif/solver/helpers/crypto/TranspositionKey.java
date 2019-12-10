package sk.stuba.fei.ksif.solver.helpers.crypto;

public class TranspositionKey extends Key {


    private Integer[] permutation = null;


    public TranspositionKey(Integer[] permutation) {
        this.permutation = permutation;
    }\


    public Integer[] getPermutation() {
        return permutation;
    }
    public void setPermutation(Integer[] permutation) {
        this.permutation = permutation;
    }

}