
package sk.stuba.fei.ksif.solver;
import sk.stuba.fei.ksif.solver.helpers.bigram.Bigram;
import sk.stuba.fei.ksif.solver.helpers.common.Misc;
import sk.stuba.fei.ksif.solver.helpers.crypto.TranspositionCipher;
import sk.stuba.fei.ksif.solver.helpers.crypto.TranspositionKey;
import sk.stuba.fei.ksif.solver.methods.BigramHC;
import sk.stuba.fei.ksif.solver.methods.DictionaryHC;


public class Main {

    public static void main(String[] args) {




        Integer[] permutation = {1,4,3,2,5,7,6};
        TranspositionCipher transpositionCipher = new TranspositionCipher();
        TranspositionKey transpositionKey = new TranspositionKey(permutation);


        String cipherText = "timriitttlsseooriotraotcarrpnseeetmewcllviameuthcmlcruttloseybgdlaehlilpirfheeotfeneasorciwcrltlasertrwpusslsistccheetfwrdmngeceuelyroearamsipripirefsseleoallatatnsyieerorttrleoobyilinwniblotefuilmrpsytissecetnseteupnehbopasuifemesroeiqirrmtmpddtrteutphiihsniehnrenglberryrgcdtihtaltrlwerutfcepegthfnhatvseeehetnsonuthhrieteeebeibsfdatirjaehotnieesotgeeanmnporeceprdcreacytsihrhorshrmlnaprnesorstrloseotgfstczbchwooitapiivomfaopotlicaehilniacemnfohtatehurwirmtiaafsrelowineolaioanqcypaspeathcaolialdswtlensnicwscptgfsheoegnmurneteitneenhnfcdieipasuptarhthtrdtfmosreenotcstteuwghesenrlhieitctratpselghllteocwatreegoooiimrembiehayesptheroraemfacreltahmseardnirrgnrtnwaphhiawalodoitnhertiathhoahrlrctuteipsolnmeleniutynceasorrgdtaeoehdiseehsupielaeoeirsptlesopettogelseorvrcnytinebieecnaepxpyrxeiuolsutuedafoitn";
        String cipherText1 = transpositionCipher.encryption(transpositionKey,"ABCDEFGHIJKLMNO",false);
//        System.out.println(cipherText1);
//        System.out.println(transpositionCipher.decryption(transpositionKey,cipherText1,false));
        String cipherText2 = transpositionCipher.encryption(transpositionKey,"But somehow, it’s possible. It also implies that cause is a misnomer: if existence is eternal, then cause is not all that important. Existence, without a beginning or end, continues to change its form. In addition, the concept of time has to be thrown out as well if we consider the nature of the universe",true);
        String cipherText3 = transpositionCipher.encryption(transpositionKey,"Without a beginning or end to existence, time is rendered irrelevant. Sure, it is a useful tool in our daily lives, but it is not a universal fact. Time is just our perception of duration within an eternal universe.",false);

        BigramHC.HillClimb(cipherText2, Misc.Language.EN);
    }
}
