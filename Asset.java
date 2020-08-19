/*****************************************
 *  - mrm2234
 *
 * Creats an Asset object
 ****************************************/
public class Asset implements Comparable<Asset>{

		private int amount;
      private String assetType;
      private String company;
      private String delineate;

			public Asset(String d, String a, String b, int c){
		          delineate = d;
		          company = a;
		          assetType = b;
		          amount = c;
		  }

			public String getInformation(){
	          return delineate + company + "," + assetType + "," + amount;
	      }

	      public int getAmount(){
	          return amount;
	      }

	      public String getAssetType(){
	          return assetType;
	      }

	      public String getCompany(){
	          return company;
	      }

	      public int compareTo(Asset c){
	          if (company.equals(c.company)){
	              if (assetType.equals("BONDS") && c.assetType.equals("STOCK")){
	                  return 1;
	              }
	              else if (assetType.equals("STOCK") && c.assetType.equals("BONDS")){
	                  return -1;
	              }
	              else if (assetType.equals(c.assetType) && amount == c.amount){
	                  return 0;
	              }
	              else {
	                  return -1;
	              }
	          }
	          else {
	              for (int i =0; i<company.length(); i++){
	                  for (int j = 0; j<c.company.length(); j++){
	                      if (company.charAt(i)>(c.company.charAt(j))){
	                          return 1;
	                      }
	                      else if (company.charAt(i)<(c.company.charAt(j))){
	                          return -1;
	                      }
	                  }
	              }
	          }
	          return 1;
	      }

	  }
