/*****************************************
 * Molly McNutt - mrm2234
 * 
 * Shuffles/deals a deck of cards
 
 ****************************************/ 
import java.util.Arrays;
public class Deck {
	
	private Card[] cards;
	private int top; //the index of the top of the deck
    private int cardsDealt;

	//add more instance variables if needed
	
	public Deck(){ //creating a deck
        cards = new Card[52];
        int i = 0;
        for (int s = 0; s<4; s++){
            for (int r = 0; r<13; r++){
                cards[i] = new Card(s+1, r+1);
                i++;
            }
        }
        shuffle(); //calling the shuffle method
        top = 0;
		//make a 52 card deck here
	}
	
	public void shuffle(){ //shuffling the cards randomly
		for (int i = 0; i<52; i++){
            int a = (int) (Math.random()*51+1);
            Card temp = cards[i];
            cards[i] = cards[a];
            cards[a] = temp;
        }
        top = 0;
	}
	
	public Card deal(){ //deal the cards. 
        if (top == 52){
            return new Card(0,0);
        }
        top++;
        return cards[top-1]; //deal the top card in the deck
    }
    
    public String toString(){
        return Arrays.toString(cards);
    }
}


	
	//add more methods here if needed


