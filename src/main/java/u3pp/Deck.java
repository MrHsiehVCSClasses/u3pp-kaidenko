package u3pp;
import java.util.Random;

public class Deck {
    
    public static final String[] SUITS = {"Clubs", "Spades", "Diamonds", "Hearts"};
    public static final String[] VALUES = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

    private Card[] deck = new Card[52];
    private int cardsLeft;
    private int draw;

    public Deck() {
        cardsLeft = 52;
        draw = 0;
        int k = 0;

        for (int i = 0; i< SUITS.length; i++) {
            for (int j = 0; j < VALUES.length; j++){
                deck [k] =  new Card(SUITS[i], VALUES[j]);
                k += 1;
            }
        }
        System.out.println(deck.toString());
         
    }

    public int numLeft(){
        return cardsLeft;
    }

    public Card deal() {
        draw += 1;
        cardsLeft -= 1;

        return deck[draw - 1];

    }

    public void shuffle() {
            
            Random rand = new Random();
            
            for (int i = 0; i < 52; i++) {
                int randomPosiiton = rand.nextInt(52);
                Card temp = deck[randomPosiiton];
                deck[randomPosiiton] = deck[i];
                deck[i] = temp;
            }

            cardsLeft = 52;
            draw = 0;
            
            System.out.println(deck.toString());
            for(int i = 0; i<deck.length; i++){
            System.out.println(deck[i]);
        }
    }
    public int getDraw() {
        return this.draw;
    }
        
}
    
    