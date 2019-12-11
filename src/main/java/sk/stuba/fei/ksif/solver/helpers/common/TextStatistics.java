package sk.stuba.fei.ksif.solver.helpers.common;

import java.util.*;
import java.lang.Math;

public class TextStatistics {

    public static Map readNgram(String txt, int n, boolean relativeFr) {
        Map<String, Double> map = new HashMap<>();

        if (n > txt.length()) {
            return map;
        }

        //StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < txt.length() - n; i++) {
            String subStr = txt.substring(i, i + n);

            if (!map.containsKey(subStr)) {
                map.put(subStr, 1.0);
            } else {
                double tmp = map.get(subStr);
                map.replace(subStr, ++tmp);
            }

        }

        if (relativeFr) {
            for (Map.Entry<String, Double> entry : map.entrySet()) {
                map.replace(entry.getKey(), entry.getValue() / txt.length());
            }
        }


        return map;
    }


    //(- sum_i p_i * log_2 (p_i))
    public static double entropy(String txt) {
        double retVal = 0;
        Map<String, Double> map = readNgram(txt, 1, true);

        for (Map.Entry<String, Double> entry : map.entrySet()) {
            retVal += (entry.getValue() * (Math.log(entry.getValue()) / Math.log(2)));
        }

        retVal = retVal * (-1);
        return retVal;
    }

    public static double IndexOfCoincidence(Double[] p, int len) {
        double retVal = 0.0;

        double tmp = 0;

        for (int i = 0; i < p.length; i++) {
            tmp += (p[i] * (p[i] - 1));
        }
        retVal = tmp / ((len * (len - 1)) / p.length);

        return retVal;
    }
}