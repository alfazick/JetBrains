package numbers;


import java.math.BigInteger;
import java.util.*;


public class Main {

    public static boolean isInteger(String input) {
        try {
            Long.parseLong(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isNatural(String digits) {
        if (digits.charAt(0) == '0') {
            return false;
        }
        boolean stop = !(isInteger(digits));
        if (stop) {
            return false;
        }
        Long num = Long.parseLong(digits);

        if (num < 0) {
            return false;
        }

        return true;
    }

    public static void supportedRequests(){
        String text = "Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameter shows how many consecutive numbers are to be processed;\n" +
                "- two natural numbers and a property to search for;\n" +
                "- two natural numbers and two properties to search for;\n" +
                "- a property preceded by minus must not be present in numbers;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.";

        System.out.println(text);
    }

    public static boolean isExclusive(String word1, String word2){
        String[] oddeven = {"even", "odd"};
        List<String> nameprop = new ArrayList<>(Arrays.asList(oddeven));

        if (nameprop.contains(word1.toLowerCase()) && nameprop.contains(word2.toLowerCase())){
            return false;
        }

        String[] duckspy = {"duck", "spy"};
        List<String> nameduck = new ArrayList<>(Arrays.asList(duckspy));

        if (nameduck.contains(word1.toLowerCase()) && nameduck.contains(word2.toLowerCase())){
            return false;
        }

        String[] sunnysquare = {"sunny", "square"};
        List<String> namesunny = new ArrayList<>(Arrays.asList(sunnysquare));

        if (namesunny.contains(word1.toLowerCase()) && namesunny.contains(word2.toLowerCase())){
            return false;
        }

        String[] happysad = {"happy", "sad"};
        List<String> namehappy = new ArrayList<>(Arrays.asList(happysad));

        if (namehappy.contains(word1.toLowerCase()) && namehappy.contains(word2.toLowerCase())){
            return false;
        }

        return true;

    }

    public static List isExclusiveParam(String[] activequery){
        List<String> exclusive = new ArrayList<>();

        for (String u : activequery){
            for (String a : activequery){
                if (u.equals(a)){
                    continue;
                }

                if (!isExclusive(u,a)){
                    exclusive.add(u);
                    exclusive.add(a);
                    return exclusive;
                }
            }
        }

        return exclusive;
    }

    public static List isBadParam(String[] good, String[] bad){
        List<String> exclusive = new ArrayList<>();

        for (String u : good){
            for (String a : bad){
                if (u.equals(a)){
                    exclusive.add(u);
                    exclusive.add(a);
                }
            }
        }

        return exclusive;
    }



    public static List isEligibleParam(String[] user, String[] active){
        ArrayList<String> notFound = new ArrayList<>();
        for (String u : user){
            boolean isFound = false;
            String userWant = u.toLowerCase();
            for (String a : active){
                if (userWant.equals(a)){
                    isFound = true;
                }
            }
            if (!isFound){
                notFound.add(u);
            }
        }
        return notFound;
    }

    public static void showProp(){
        System.out.println("Available properties: " +
                "[BUZZ, DUCK, PALINDROMIC, " +
                "GAPFUL, SPY, EVEN, ODD, " +
                "SUNNY, SQUARE, JUMPING, HAPPY, SAD]");
    }



    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean programOn = true;
        System.out.println("Welcome to Amazing Numbers!");
        supportedRequests();

        String[] possible = {"even", "odd", "buzz","duck","palindromic","gapful","spy","sunny","square","jumping","happy","sad"};
        List<String> nameList = new ArrayList<>(Arrays.asList(possible));


        while (programOn) {

            System.out.println("Enter a request:");
            String userInput = sc.nextLine();

            String[] nums = userInput.split("\\s+");





            if (nums[0].equals("0")){
                programOn = false;
                continue;
            }

            boolean notFinish = !(isNatural(nums[0]));
            if (notFinish) {
                System.out.println("The first parameter should be a natural number or zero.");
                continue;
            }

            if (nums.length == 1){
                MyNumber num = new MyNumber(userInput);
                num.printProperty();
            }
            if (nums.length > 1) {
                boolean notFinish2 = !(isNatural(nums[1]));
                if (notFinish2) {
                    System.out.println("The second parameter should be a natural number or zero.");
                    continue;
                }
            }

            if (nums.length == 2){

                ArrayList<MyNumber> ans = new ArrayList<MyNumber>();
                long firstnum = Long.valueOf(nums[0]);
                long limit = Long.valueOf(nums[1]);

                while (limit > 0){
                    String newnum = String.valueOf(firstnum);
                    ans.add(new MyNumber(newnum));
                    firstnum +=1;
                    limit -=1;
                }

                for (var num:ans){
                    num.printPropertyOneLine();
                }
            }

            ArrayList<String> desired = new ArrayList<>();
            ArrayList<String> hated = new ArrayList<>();

            if (nums.length > 2){



            String[] slice;
            int size = nums.length;
            slice = Arrays.copyOfRange(nums, 2, size);

            for (String property : slice) {
                if (property.charAt(0) == '-'){
                    hated.add(property.substring(1));
                } else {
                    desired.add(property);
                }
            }

            String[] propertyArr = hated.toArray(new String[0]);

            List<String> posibleArr = new ArrayList<>(Arrays.asList(possible));

            List<String> wrongParams = isEligibleParam(propertyArr, possible);


            propertyArr = desired.toArray(new String[0]);

            List<String> newParams = isEligibleParam(propertyArr, possible);


            for (String p : newParams){
                wrongParams.add(p);
            }

            if (wrongParams.size() == 1){
                String request = Arrays.toString(nums);

                for(var wrong: wrongParams){
                    request += wrong;
                    request += ", ";
                }
                System.out.println("The property " + request + " is wrong.");
                showProp();
                continue;
            }

            if (wrongParams.size() > 1){
                String request = Arrays.toString(nums);

                for(var wrong: wrongParams){
                    request += wrong;
                    request += ", ";
                }
                System.out.println("The properties " + request + " are wrong.");
                showProp();
                continue;
            }

            List<String> mutualExclusive = isExclusiveParam(hated.toArray(new String[0]));
            List<String> badParam = isBadParam(desired.toArray(new String[0]), hated.toArray(new String[0]));

            for (String p : badParam){
                mutualExclusive.add(p);
            }

            if (mutualExclusive.size() > 0){
                String text =
                        "The request contains mutually exclusive properties: " + mutualExclusive.get(0) + " " + mutualExclusive.get(1) + "\n" +
                                "There are no numbers with these properties.\n";
                System.out.println(text);
                continue;
            }

            mutualExclusive = isExclusiveParam(desired.toArray(new String[0]));

            if (mutualExclusive.size() > 0){
                String text =
                        "The request contains mutually exclusive properties: " + mutualExclusive.get(0) + " " + mutualExclusive.get(1) + "\n" +
                                "There are no numbers with these properties.\n";
                System.out.println(text);
                continue;
            }

            }

            if (nums.length > 2){

                String[] propertyArr = desired.toArray(new String[0]);
                String[] badproperties = hated.toArray(new String[0]);

                List<String> posibleArr = new ArrayList<>(Arrays.asList(possible));


                List<String> mutualExclusive = isExclusiveParam(propertyArr);

                if (mutualExclusive.size() > 0){
                    String text =
                            "The request contains mutually exclusive properties: " + mutualExclusive.get(0) + " " + mutualExclusive.get(1) + "\n" +
                                    "There are no numbers with these properties.\n";
                    System.out.println(text);
                    continue;
                }

                ArrayList<MyNumber> ans = new ArrayList<MyNumber>();

                long firstnum = Long.valueOf(nums[0]);
                long limit = Long.valueOf(nums[1]);

                while (limit > 0){
                    String newnum = String.valueOf(firstnum);
                    MyNumber possibleNum = new MyNumber(newnum);
                    possibleNum.fillProperties();
                    ArrayList<String> properties = possibleNum.getProperties();
                    boolean notFound = false;
                    for (String property: propertyArr){
                        if (!((properties.contains(property.toLowerCase()) && properties.contains(property.toLowerCase())))){
                            notFound = true;
                        }

                    }

                    for (String property : badproperties){
                        if (properties.contains(property.toLowerCase())){
                            notFound = true;
                        }
                    }


                    if (!notFound){
                        ans.add(possibleNum);
                        limit -=1;
                    }
                    firstnum +=1;
                }

                for (var num:ans){
                    num.printPropertyOneLine();
                }
            }

        }

        System.out.println("Goodbye!");
    }
}
