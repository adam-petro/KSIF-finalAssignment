package sk.stuba.fei.ksif.solver.helpers.common;

public class Misc {

    public static int fac(int i){
        if(i == 1){
            return 1;
        }
        return i * fac(i-1);
    }
}
