/*****************************************
 *  mrm2234

 ****************************************/
import java.util.Arrays;
public class Portfolio { //will allow us to sort our Portfolio
      private ArrayList<Asset> portfolio;

      public Portfolio(){
          portfolio = new ArrayList<Asset>();
      }

      public void addOne(Asset p){
          portfolio.add(p);
      }

      public void remove(Asset e){
          portfolio.remove(e);
      }

      public void sortPortfolio(){
          Collections.sort(portfolio);
      }

      public ArrayList<Asset> getPortfolio(){ //returns Portfolio
        return portfolio;
    }
  }
