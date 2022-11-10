package u3pp;

public class PalindromeTester {
     
    public static boolean isPalindrome(String s) {

        String reverse = "";
    
        for (int i = s.length() -1; i >= 0; i--){
        reverse += s.charAt(i);
        }
    
        reverse = reverse.toLowerCase();
        s = s.toLowerCase();

            if (s.equals(reverse)) {
                return true;
            }
            else {
                return false;
            }
        }
    }


