package numbers;

import java.util.ArrayList;
import java.util.HashSet;

public class MyNumber {

    String numString;
    Long numLong;
    boolean isbuzz = false;
    boolean isduck = false;
    boolean ispalindromic = false;
    boolean iseven =  false;
    boolean isodd = false;
    boolean isgapful = false;
    boolean isspy = false;
    boolean issunny = false;
    boolean issquare = false;
    boolean isjumping = false;
    boolean issad = false;
    boolean ishappy = false;

    ArrayList<String> properties = null;


    MyNumber(String number){
        this.numString = number;
        this.numLong = Long.parseLong(number);
        properties = new ArrayList<String>();
        NumHandler nh = new NumHandler();
        this.iseven = nh.isEven(numLong);
        this.isodd = !this.iseven;
        this.isbuzz = nh.isBuzz(numLong);
        this.isduck = nh.isDuck(numLong);
        this.ispalindromic = nh.isPalindrome(numString);
        this.isgapful = nh.isGapful(numString);
        this.isspy = nh.isSpy(numString);
        this.issunny = nh.isSunny(numLong);
        this.issquare = nh.isSquare(numLong);
        this.isjumping = nh.isJumping(numString);
        this.ishappy = nh.isHappy(numString);
        this.issad = !this.ishappy;
        this.fillProperties();

    }

    public ArrayList<String> getProperties(){
        return this.properties;
    }

    public void printProperty(){
        System.out.println("Properties of " + numString);
        System.out.println("buzz:  " + isbuzz);
        System.out.println("duck:  " + isduck);
        System.out.println("palindromic: " + ispalindromic);
        System.out.println("gapful: " + isgapful);
        System.out.println("spy: " + isspy);
        System.out.println("jumping: " + isjumping );
        System.out.println("square: " + issquare);
        System.out.println("sunny:  " + issunny);
        System.out.println("even:  " + iseven);
        System.out.println("odd:  " + isodd);
        System.out.println("sad:  " + issad);
        System.out.println("happy: " + ishappy);

    }

    public void fillProperties(){
        if (isbuzz){
            properties.add("buzz");
        }

        if (isduck){
            properties.add("duck");
        }

        if (ispalindromic) {
            properties.add("palindromic");
        }

        if (isjumping){
            properties.add("jumping");
        }

        if (isspy){
            properties.add("spy");
        }

        if (isgapful) {
            properties.add("gapful");
        }

        if (issunny) {
            properties.add("sunny");
        }

        if(issquare) {
            properties.add("square");
        }

        if (iseven) {
            properties.add("even");
        }

        if (isodd) {
            properties.add("odd");
        }

        if (ishappy){
            properties.add("happy");
        }

        if (issad){
            properties.add("sad");
        }

    }

    public void printPropertyOneLine(){

        String text = "";

        text += numString + " is ";

        HashSet<String> hashSet = new HashSet<>(this.properties);

        for (String property : hashSet) {
            text += property;
            text += " , ";
        }

        System.out.println(text);
    }





}
