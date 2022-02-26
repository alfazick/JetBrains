
package numbers;

import java.util.HashMap;
import java.util.HashSet;

public class NumHandler {
    public  boolean isEven(Long num) {
        return (num % 2 == 0);
    }

    public  boolean isBuzz(Long num) {
        return ((num % 7 == 0) || (num % 10 == 7));
    }

    public  boolean isDuck(Long num) {
        while (num > 0){
            if (num % 10 == 0) {
                return true;
            }
            num /= 10;
        }
        return false;
    }

    public boolean isHappy(String digits) {
        HashSet<String> seen = new HashSet<>();

        while (!seen.contains(digits)){

            seen.add(digits);

            int num = 0;

            for (String digit : digits.split("")) {
                num += Math.pow((Integer.valueOf(digit)),2);
            }

            //System.out.println(num);

            if (num == 1){
                return true;

            } else {
                digits = String.valueOf(num);
            }
        }

        return false;

    }

    public  boolean isPalindrome(String digits){
        int start = 0;
        int end = digits.length() - 1;
        while (start < end) {
            if (digits.charAt(start) == digits.charAt(end)){
                start += 1;
                end -= 1;
            } else{
                return false;
            }
        }
        return true;
    }

    public  boolean isGapful(String digits) {

        if (digits.length() < 3){
            return false;
        }
        String num = digits.charAt(0) + "" + digits.charAt(digits.length()-1);

        long divisor = Long.valueOf(num);
        long dividend = Long.valueOf(digits);

        return dividend % divisor == 0;

    }

    public static boolean isSpy(String digits) {
        long total = 0;
        long product = 1;

        for (char d : digits.toCharArray()){
            long num = Long.valueOf((d - '0'));
            //System.out.println(num);
            total += num;
            product *= num;
        }
        //System.out.println(total + " : " + product);

        return total == product;
    }

    public static boolean isSunny(long x)
    {
        x +=1;
        double sq = Math.sqrt(x);
        return ((sq - Math.floor(sq)) == 0);
    }

    public static boolean isSquare(long x)
    {
        double sq = Math.sqrt(x);
        return ((sq - Math.floor(sq)) == 0);
    }

    public static boolean isJumping(String digits){
        int prevNum = Integer.valueOf(digits.charAt(0) + "");

        for (int i = 1; i < digits.length(); i++){
            int curNum = Integer.valueOf(digits.charAt(i) + "");
            //System.out.println(curNum);

            if (Math.abs(curNum - prevNum) == 1) {
                prevNum = curNum;
                continue;
            } else if (curNum == prevNum) {
                return false;
            } else if ((curNum == 0) && (prevNum == 9)){
                prevNum = curNum;
                return false;


            } else {
                return false;
            }
        }
        return true;
    }
}
