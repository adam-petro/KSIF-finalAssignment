
package sk.stuba.fei.ksif.solver;
import sk.stuba.fei.ksif.solver.helpers.bigram.Bigram;
import sk.stuba.fei.ksif.solver.helpers.common.Misc;
import sk.stuba.fei.ksif.solver.helpers.common.Text;
import sk.stuba.fei.ksif.solver.helpers.crypto.TranspositionCipher;
import sk.stuba.fei.ksif.solver.helpers.crypto.TranspositionKey;
import sk.stuba.fei.ksif.solver.methods.BigramHC;
import sk.stuba.fei.ksif.solver.methods.DictionaryHC;


public class Main {

    public static void main(String[] args) {




/*
        Integer[] permutation = {1,4,3,2,5};
        TranspositionCipher transpositionCipher = new TranspositionCipher();
        TranspositionKey transpositionKey = new TranspositionKey(permutation);


        String cipherText = "timriitttlsseooriotraotcarrpnseeetmewcllviameuthcmlcruttloseybgdlaehlilpirfheeotfeneasorciwcrltlasertrwpusslsistccheetfwrdmngeceuelyroearamsipripirefsseleoallatatnsyieerorttrleoobyilinwniblotefuilmrpsytissecetnseteupnehbopasuifemesroeiqirrmtmpddtrteutphiihsniehnrenglberryrgcdtihtaltrlwerutfcepegthfnhatvseeehetnsonuthhrieteeebeibsfdatirjaehotnieesotgeeanmnporeceprdcreacytsihrhorshrmlnaprnesorstrloseotgfstczbchwooitapiivomfaopotlicaehilniacemnfohtatehurwirmtiaafsrelowineolaioanqcypaspeathcaolialdswtlensnicwscptgfsheoegnmurneteitneenhnfcdieipasuptarhthtrdtfmosreenotcstteuwghesenrlhieitctratpselghllteocwatreegoooiimrembiehayesptheroraemfacreltahmseardnirrgnrtnwaphhiawalodoitnhertiathhoahrlrctuteipsolnmeleniutynceasorrgdtaeoehdiseehsupielaeoeirsptlesopettogelseorvrcnytinebieecnaepxpyrxeiuolsutuedafoitn";
        String cipherText1 = transpositionCipher.encryption(transpositionKey,"ABCDEFGHIJikl",true);
//      System.out.println(cipherText1);
//        System.out.println(transpositionCipher.decryption(transpositionKey,cipherText1,false));
        //String cipherText2 = transpositionCipher.encryption(transpositionKey,"But somehow, it’s possible. It also implies that cause is a misnomer: if existence is eternal, then cause is not all that important. Existence, without a beginning or end, continues to change its form. In addition, the concept of time has to be thrown out as well if we consider the nature of the universe",true);
        String cipherText3 = transpositionCipher.encryption(transpositionKey,"Without a beginning or end to existence, time is rendered irrelevant. Sure, it is a useful tool in our daily lives, but it is not a universal fact. Time is just our perception of duration within an eternal universe.",true);
        //System.out.println(cipherText1);
        //System.out.println(transpositionCipher.decryption(transpositionKey,cipherText1,true));
        DictionaryHC.HillClimb(cipherText, Misc.Language.SK);
*/

        TranspositionCipher transpositionCipher  = new TranspositionCipher();
        Integer[] perm = {7, 2, 12, 6, 5, 10, 3, 11, 4, 1, 8, 9};
        TranspositionKey transpositionKey = new TranspositionKey(perm);
        //String ot = new String("svesfgqsrfpsevosrqosltvsbinsaonsggvsfgnsorysynfsbvasgurspvcsuresvfrsnflsgbhsaqrsefgsnaqsnaqsvzcsyrzsragsohgsvgesrfvsfgrsqnysyngsgrzscgfsgbosernsxvgshagsvygsuersrprsaghsevrsfynsgresguvsfrnsearsqvgsgursqrfspevscgvsbaysrpusvssservsaqrspuvsssesnoysrsesrapsusbsegusrvasqrpsvcusrensoyrspvcsuresznaslcrsbcysrunsirgsevrsqgbsvzcsyrzsragsrapselcsgvbsafpsurzsrfgsungsnersrffsragsvnysylisvtrsaresrpvscursefvsasesvrqsevpsuxnsfvfsxvjsnfgsurssvefsggbschosyvfsuntsrarsenyszrgsubqsbsqsrpvscursevastivstrasrerspvcsuresf");
        //String ot2 = new String("razyadroamzoekdrnptstuomveripaathzsaoyasaskydkshrnulvvsrtlptjouknsuoeoeahgszujieovdaodndvmulvvvlnlrajdearaapoardnenmuooi");

        //String ot1= new String("Permutations are studied in almost every branch of mathematics, and in many other fields of science.");
   /*     String ot1 = new String("Marriage is a three-ring circus: engagement ring, wedding ring, and suffering.");
        String ot2 = new String("I have a dream that one day this nation will rise up, live out the true meaning of its creed: we hold these truths to be self-evident, that all men are created equal.");
        String ot3 = new String("Today there is talk of war everywhere. Everyone fears a war breaking out between the two countries. If that happens it will be a calamity both for India and for Pakistan. India has written to the U.N. because whenever there is a fear of conflict anywhere the U.N. is asked to promote a settlement and to stop fighting from breaking out.");
        String ot4 = new String("My message is that we'll be watching you This is all wrong. I shouldn't be up here. I should be back in school on the other side of the ocean. Yet you all come to us young people for hope. How dare you You have stolen my dreams and my childhood with your empty words. And yet I'm one of the lucky ones. People are suffering. People are dying. Entire ecosystems are collapsing. We are in the beginning of a mass extinction, and all you can talk about is money and fairy tales of eternal economic growth. How dare you!");

        String zt1 = transpositionCipher.encryption(transpositionKey,ot1, true);
        String zt2 = transpositionCipher.encryption(transpositionKey,ot2, true);
        String zt3 = transpositionCipher.encryption(transpositionKey,ot3, true);
        String zt4 = transpositionCipher.encryption(transpositionKey,ot4, true);
*/
        String zt = new String("aldeekprtnmsyekiricfasmabolzerisiaenid");


        //String sk = Text.convertToTSA(ot5,false);
        //System.out.println(transpositionCipher.decryption(transpositionKey,ot));
        //System.out.println(transpositionCipher.decryption(transpositionKey,ot2));
        //System.out.println(zt);
        BigramHC.HillClimb(zt,Misc.Language.SK);
        //BigramHC.HillClimb(zt);
    }
}
