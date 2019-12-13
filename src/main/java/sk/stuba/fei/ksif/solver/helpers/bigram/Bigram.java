package sk.stuba.fei.ksif.solver.helpers.bigram;

import sk.stuba.fei.ksif.solver.helpers.common.*;
import sk.stuba.fei.ksif.solver.helpers.common.Misc.*;

import java.util.Map;

public class Bigram {
    public static double count (String text, Language language){
        double ref[][];

        if(language== Language.SK){
            ref = (double[][]) Text.readFromFile("skbigrams.bin");
        }
        else{
            ref = (double[][]) Text.readFromFile("_bigrams.bin");

        }

        Map<String, Double> bigrams = TextStatistics.readNgram(text,2,true);
        char first,second;
        double distance = 0.0;

        for (String temp : bigrams.keySet()){
            first  = temp.charAt(0) ;
            first -= 'a';
            second = temp.charAt(1);
            second -= 'a';
            distance += java.lang.Math.abs(bigrams.get(temp) - ref[first][second])  ;
            //distance += bigrams.get(temp) * (double)ref[first][second];
        }

        return distance;
    }
}
