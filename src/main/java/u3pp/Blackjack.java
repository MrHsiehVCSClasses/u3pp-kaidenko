package u3pp;

import java.util.Scanner;

public class Blackjack {
    
    private Deck deck;
    private Card[] dealer;
    private Card[] player;
    private int dealerNumCards;
    private int playerNumCards;
    public Blackjack() {
        this.deck = new Deck();
    }

    public void play(Scanner scanner) {
        
        boolean keepPlaying = true;
        //ask the user if they wanna play.
        while (true) {
            System.out.println("Hey Bitch, you wanna play Black Jack? Yes/No");
            String yesOrNo = scanner.nextLine();
            // if no, then exit the function
            if (yesOrNo.toLowerCase().equals("no")) {
                System.out.println("Aight then, see ya bitch.");
                return;
            // if yes break, and continue
            } else if (yesOrNo.toLowerCase().equals("yes")) {
                break;
            } else {
                System.out.println("Wrong Input, Try Again");
            }
        }
        System.out.println("okay bet, you bout to lose.");
        while (keepPlaying) {
            this.dealer = new Card[52];
            this.player = new Card[52];
            this.dealerNumCards = 0;
            this.playerNumCards = 0;
            shuffleIfOut();
            this.dealer[0] = this.deck.deal();
            shuffleIfOut();
            this.dealer[1] = this.deck.deal();
            shuffleIfOut();
            this.player[0] = this.deck.deal();
            shuffleIfOut();
            this.player[1] = this.deck.deal();
            shuffleIfOut();
            this.playerNumCards = 2;
            this.dealerNumCards = 2;
            String newOrOld = "";
            boolean gameDone = false;
            while (calcPoints(player) < 21 && calcPoints(dealer) < 21) {
                System.out.println("Player's " + newOrOld + " Hand: " + getAllOfTheCards(player) + ": " + calcPoints(player) + "points");
                System.out.println("Dealer's Hand " + getAllOfTheCards(dealer) +  ": " + calcPoints(dealer) + "points");
                if (calcPoints(player) >= 17 && calcPoints(dealer) >= 17) {
                    gameDone = true;
                    System.out.println(determineResult(player, dealer));
                    break;
                }
                System.out.println("Stay or Hit? Stay/Hit");
                while (true) {
                    String stayOrHit = scanner.nextLine();
                    if (stayOrHit.toLowerCase().equals("stay")) {
                        shuffleIfOut();
                        dealer[dealerNumCards] = this.deck.deal();
                        dealerNumCards += 1;
                        break;
                    } else if (stayOrHit.toLowerCase().equals("hit")) {
                        shuffleIfOut();
                        player[playerNumCards] = this.deck.deal();
                        playerNumCards += 1;
                        newOrOld = "new";
                        break;
                    } else {
                        System.out.println("Wrong Input, try again");
                    }
                }
            }

            if (!gameDone) {
                System.out.println("Player's " + newOrOld + " Hand: " + getAllOfTheCards(player)+  ": " + calcPoints(player) + "points");
                System.out.println("Dealer's Hand " + getAllOfTheCards(dealer) +  ": " + calcPoints(dealer) + "points");
                System.out.println(determineResult(player, dealer));
            }
            System.out.println("wanna play again? Yes/No");
            while (true) {
                String yesOrNo = scanner.nextLine();
                if (yesOrNo.toLowerCase().equals("no")) {
                    System.out.println("Aight then, see ya bitch.");
                    keepPlaying = false;
                return;
                } else if (yesOrNo.toLowerCase().equals("yes")) {
                    break;
                } else {
                    System.out.println(yesOrNo + " Wrong Output, Try Again");
                }
            }
        }
    }

    public static int calcPoints(Card[] hand) {
        
        int currentTotalPoint = 0;
        if (hand[0] == null) {
            return 0;
        }
        int counter = 0;

        while (hand[counter] != null) {
            String currValue = hand[counter].getValue();
            if (currValue == "Jack" || currValue == "Queen" || currValue == "King") {
                currentTotalPoint += 10;
            } else if (currValue == "Ace") {
                currentTotalPoint += 11;
            } else {
                currentTotalPoint += Integer.valueOf(currValue);
            }
            counter++;
        }
        
        return currentTotalPoint;
    }
    // will call this method if both users have higher or equal than 17
    // if a user has 21 then user wins
    // if a dealer has 21 then dealer wins
    //if both of the players have 21, push/tie
    //if both have points 17 or greater, then they reveal the card and whoever has more points wins. In case of a tie, -> push/tie.
    //if one of the players gets higher score than 21, that player loses.
    public static String determineResult(Card[] userHand, Card[] dealerHand) {
        int userScore = calcPoints(userHand);
        int dealerScore = calcPoints(dealerHand);
        if ((dealerScore < userScore && userScore <= 21) || (isBlackjack(userHand) && !isBlackjack(dealerHand)) || isBust(dealerHand)) {
            return "User Wins";
        } else if (dealerScore < userScore && dealerScore <= 21  || (isBlackjack(dealerHand) && !isBlackjack(userHand)) || isBust(userHand))  {
            return "User Loses";
        } else {
            return "User Pushes";
        }
    }

    public static boolean isBust(Card[] hand) {
        if (calcPoints(hand) > 21) {
            return true;
        }
        return false;
    }

    public static boolean isBlackjack(Card[] hand) {
        if (calcPoints(hand) == 21) {
            return true;
        }
        return false;
    }

    public static boolean shouldDealerKeepHitting(Card[] hand) {
        int currentTotalPoint =  calcPoints(hand);
        if (currentTotalPoint <= 16) {
            return true;
        }
        return false;
    }
    public String getAllOfTheCards(Card[] hand) {
        String retString = "";
        int counter = 0;
        while (hand[counter] != null) {
            retString += hand[counter].toString();
            retString += " ";
            counter++;
        }
        return retString;
    }
    public void shuffleIfOut() {
        if (this.deck.getDraw() >= 52) {
            this.deck.shuffle();
        }
    }
}