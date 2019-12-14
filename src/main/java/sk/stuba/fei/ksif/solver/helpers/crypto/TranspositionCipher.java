package sk.stuba.fei.ksif.solver.helpers.crypto;


import sk.stuba.fei.ksif.solver.helpers.common.Text;

public class TranspositionCipher extends Cipher<TranspositionKey> {


    @Override
    public String encryption(TranspositionKey key, String string, Boolean row) {
        // [stlpce][riadky]
        string = Text.convertToTSA(string,false);
        Integer[] permutation = key.getPermutation();
        int lengthOfPermutation = key.getPermutation().length;
        int numberOfLines = intDivideToUp(string.length(),lengthOfPermutation);

        char[][] matrixOfChars = new char[numberOfLines][lengthOfPermutation];

        int i = 0, j = 0;
        int counter = 0;


        while (counter < string.length()){
            if (i < numberOfLines && j < lengthOfPermutation){
                if (Character.isLetter(string.charAt(counter))) {
                    matrixOfChars[i][j] = string.charAt(counter);
                    j++;
                    if (j == lengthOfPermutation){
                        i++;
                        j = 0;
                    }
                }
                counter++;
            }
        }


        while(i < numberOfLines && j < lengthOfPermutation){
            matrixOfChars[i][j] = ' ';
            j++;
        }

        //printMatrix(matrixOfChars);
        matrixOfChars = permutateCols(matrixOfChars,permutation);
        //printMatrix(matrixOfChars);
        StringBuilder stringBuilder = new StringBuilder();



        if (row){
            // po riadkoch
            for (i = 0; i < numberOfLines; i++){
                for (j = 0; j < lengthOfPermutation; j++){
                    if(matrixOfChars[i][j]!=' ')
                        stringBuilder.append(matrixOfChars[i][j]);
                }
            }
        }
        else{
            // po stlpoch
            for (i = 0; i < lengthOfPermutation; i++){
                for (j = 0; j < numberOfLines; j++){
                    if(matrixOfChars[j][i]!=' ')
                        stringBuilder.append(matrixOfChars[j][i]);
                }
            }

        }






        return stringBuilder.toString();
    }

    @Override
    public String decryption(TranspositionKey key, String string, Boolean row) {
        // [stlpce][riadky]
        Integer[] permutation = inversePerm(key.getPermutation());
        permutation = key.getPermutation();
        int lengthOfPermutation = key.getPermutation().length;
        int numberOfLines = intDivideToUp(string.length(),lengthOfPermutation);

        char[][] matrixOfChars = new char[numberOfLines][lengthOfPermutation];

        int i = 0, j = 0;
        int counter = 0;
        int longcol = string.length() % permutation.length;

        if (row) {
            while (counter < string.length()) {
                if (i < lengthOfPermutation && j < numberOfLines) {
                    if (Character.isLetter(string.charAt(counter))) {

                            matrixOfChars[j][i] = string.charAt(counter);



                        if (j == numberOfLines - 1 && index(permutation, i + 1) > longcol - 1 && longcol > 0) {
                            //i++;
                            matrixOfChars[j][i] = ' ';
                            counter--;
                        }
                        i++;
                        if (i == lengthOfPermutation) {
                            j++;
                            i = 0;
                        }

                    }
                    counter++;
                }
            }

        }else{
            while (counter < string.length()) {
                if (i < numberOfLines && j < lengthOfPermutation) {
                    if (Character.isLetter(string.charAt(counter))) {

                            matrixOfChars[i][j] = string.charAt(counter);



                        if (i == numberOfLines - 1 && index(permutation, j + 1) > longcol - 1 && longcol > 0) {
                            //i++;
                            matrixOfChars[i][j] = ' ';
                            counter--;
                        }
                        i++;
                        if (i == numberOfLines) {
                            j++;
                            i = 0;
                        }

                    }
                    counter++;
                }
            }
        }


        //printMatrix(matrixOfChars);
        matrixOfChars = permutateCols(matrixOfChars,permutation);
        //printMatrix(matrixOfChars);
        StringBuilder stringBuilder = new StringBuilder();

        if(row) {
            // po riadkoch
            for (i = 0; i < numberOfLines; i++) {
                for (j = 0; j < lengthOfPermutation; j++) {
                    if (!(matrixOfChars[i][j] == ' '))
                        stringBuilder.append(matrixOfChars[i][j]);
                }
            }

        }

        // po stlpcoch
        else {
            for (i = 0; i < numberOfLines; i++) {
                for (j = 0; j < lengthOfPermutation; j++) {
                    if (!(matrixOfChars[i][j] == ' '))
                        stringBuilder.append(matrixOfChars[i][j]);
                }
            }
        }


        return stringBuilder.toString();
    }
    public static int index(Integer[] perm, int j){
        for(int i = 0; i < perm.length;i++)
            if(perm[i]==j)
                return i;
        return -1;
    }



    private void printMatrix(char[][] matrix){
        System.out.println();

        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[i].length; j++){
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }

    //stlpce
    private char[][]  permutateCols(char[][] matrixOfChars, Integer[] permutation){
        int lengthOfPermutation = matrixOfChars.length;
        int numberOfLines = matrixOfChars[0].length;

        char[][] retVal = new char[lengthOfPermutation][numberOfLines];

        for (int i = 0; i < lengthOfPermutation; i++){
            for (int j = 0; j < numberOfLines; j++){
                retVal[i][j] = matrixOfChars[i][permutation[j]-1];
            }
        }

        return retVal;
    }

    private Integer[] inversePerm(Integer[] permutation){
        Integer[] retVal = new Integer[permutation.length];

        int j = 0;
        for (int i = permutation.length-1; i >= 0; i--){
            retVal[j] = permutation[i];
            j++;
        }

        return retVal;
    }
}