package u3pp;

import java.util.Scanner;

public class Main 
{
    // public static void main(String[] args){

    // }
    public static void main(String[] args){
        Blackjack p = new Blackjack();
        Scanner scan = new Scanner(System.in);
        p.play(scan);
        scan.close();

    }

    // public static void main(String[] args) {

    //     String word = "mom";
        
    //     PalindromeTester myPalindromeTester = new PalindromeTester();

    //     // Use this space to test your code, or actually run your project
    //     System.out.println(myPalindromeTester.isPalindrome(word));

    // }

}
