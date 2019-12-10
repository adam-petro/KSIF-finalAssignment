package sk.stuba.fei.ksif.solver.helpers.common;


public class TranspositionCipher extends Cipher<TranspositionKey> {


    @Override
    public String encryption(TranspositionKey key, String string) {
        // [stlpce][riadky]
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



        matrixOfChars = permutateCols(matrixOfChars,permutation);

        StringBuilder stringBuilder = new StringBuilder();


        boolean row = false;

        if (row){
            // po riadkoch
            for (i = 0; i < numberOfLines; i++){
                for (j = 0; j < lengthOfPermutation; j++){
                    stringBuilder.append(matrixOfChars[i][j]);
                }
            }
        }
        else{
            // po stlpoch
            for (i = 0; i < lengthOfPermutation; i++){
                for (j = 0; j < numberOfLines; j++){
                    stringBuilder.append(matrixOfChars[j][i]);
                }
            }

        }






        return stringBuilder.toString();
    }

    @Override
    public String decryption(TranspositionKey key, String string) {
        // [stlpce][riadky]
        Integer[] permutation = inversePerm(key.getPermutation());
        permutation = key.getPermutation();
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

        matrixOfChars = permutateCols(matrixOfChars,permutation);

        StringBuilder stringBuilder = new StringBuilder();

        // po riadkoch
        for (i = 0; i < numberOfLines; i++){
            for (j = 0; j < lengthOfPermutation; j++){
                stringBuilder.append(matrixOfChars[i][j]);
            }
        }

        // po stlpoch
        /*
        for (i = 0; i < lengthOfPermutation; i++){
            for (j = 0; j < numberOfLines; j++){
                stringBuilder.append(matrixOfChars[j][i]);
            }
        }
         */

        return stringBuilder.toString();
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