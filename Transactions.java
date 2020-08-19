/*****************************************
 * Molly McNutt - mrm2234
 *
 *
 ****************************************/
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.nio.charset.StandardCharsets;
 import java.util.Collections;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.List;

public class Transactions {
	public static void main(String[] args) {
       String line = "Vodafone,STOCK,10|Google,STOCK,15:Vodafone,STOCK,15|Vodafone,BOND,10|Google,STOCK,10";
        matchBenchmark(line);
	}
	public static void matchBenchmark(String input){
        String[] split = input.split(":");
        String own = split[0];
        String target = split[1];
        String[] cutOwn = own.split("[|]");
        String[] cutTarget = target.split("[|]");
        Portfolio iHave = createPortfolio(cutOwn);
        Portfolio iWant =  createPortfolio(cutTarget);
        Collections.sort(iHave.getPortfolio());
        Collections.sort(iWant.getPortfolio());
        ArrayList<Asset> finalList = calculations(iHave, iWant);
        Collections.sort(finalList);
        for (Asset d : finalList){
            System.out.println(d.getInformation());
        }

    }

    private static Portfolio createPortfolio(String[] theString){
        Portfolio thePortfolio = new Portfolio();
        for (String q : theString){
             String b = q;
            String[] a = b.split(","); //Company, assetType, and amount are now individual.
            for (int i = 0; i<a.length; i+=3){
                String v = "";
                String w = a[i];
                String x = a[i+1];
                String y = a[i+2];
                int z = Integer.parseInt(y);
                Asset one = new Asset(v, w, x, z);
                thePortfolio.addOne(one);
            }
        }
        Collections.sort(thePortfolio.getPortfolio());
        return thePortfolio;
    }

    public static ArrayList<Asset> inTargetNotHave(Portfolio have, Portfolio target){ //come in Sorted.
        //If have + want have assets of the same company, but their assetTypes differ, add to this list.
        //Will be used later in the Buy/Sell
        ArrayList<Asset> list = new ArrayList<Asset>();
        for (Asset c : target.getPortfolio()){
            for (int i = 0; i<have.getPortfolio().size(); i++){
                if (c.getCompany().equals(have.getPortfolio().get(i).getCompany())){
                    if (!c.getAssetType().equals(have.getPortfolio().get(i).getAssetType()) && ((i == have.getPortfolio().size()-1) || (!c.getCompany().equals(have.getPortfolio().get(i+1).getCompany())))){
                        list.add(new Asset("BUY,", c.getCompany(), c.getAssetType(), c.getAmount()));
                    }
                }
            }
        }
        return list;
    }

    public static ArrayList<Asset> inHaveNotTarget(Portfolio have, Portfolio target){ //come in sorted.
        //If have + want have assets of the same company, but assetTypes differ. add to list.
        //Will be used later in the buy/sell
        ArrayList<Asset> list2= new ArrayList<Asset>();
        for (Asset c : have.getPortfolio()){
            for (int i = 0; i<target.getPortfolio().size()-1; i++){
                if (c.getCompany().equals(target.getPortfolio().get(i).getCompany())){
                    if (!c.getAssetType().equals(target.getPortfolio().get(i).getAssetType()) && ((i == target.getPortfolio().size()-1) || (!c.getCompany().equals(target.getPortfolio().get(i+1).getCompany())))){
                        list2.add(new Asset("SELL,", c.getCompany(), c.getAssetType(), c.getAmount()));
                    }
                }
            }
        }
        return list2;
    }

    public static ArrayList<Asset> mathFun(Portfolio have, Portfolio target){
        ArrayList<Asset> list3 = new ArrayList<Asset>();
        for (Asset a : have.getPortfolio()){
            for (Asset b : target.getPortfolio()){
                if (a.getCompany().equals(b.getCompany())){
                    if (a.getAssetType().equals(b.getAssetType())){
                        if (a.compareTo(b) !=0){
                            int x = a.getAmount()-b.getAmount();
                            if (x<3){
                                Asset theAsset = new Asset("BUY,", a.getCompany(), a.getAssetType(), Math.abs(x));
                                list3.add(theAsset);
                            }
                            else {
                                Asset otherAsset = new Asset("SELL,", a.getCompany(), a.getAssetType(), x);
                                list3.add(otherAsset);
                            }
                        }
                    }
                }
            }
        }
        return list3;
    }

    public static ArrayList<Asset> calculations(Portfolio have, Portfolio target){
        Collections.sort(have.getPortfolio());
        Collections.sort(target.getPortfolio());
        ArrayList<Asset> fin = new ArrayList<Asset>();
        ArrayList<Asset> a = inTargetNotHave(have, target);
        ArrayList<Asset> b = inHaveNotTarget(have, target);
        ArrayList<Asset> c = mathFun(have, target);
        fin.addAll(a);
        fin.addAll(b);
        fin.addAll(c);
        Collections.sort(fin);
        return fin;
    }


}
