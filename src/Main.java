import java.util.*;

class Main{


    public String reverseWord(String word){

       String [] wordOriginal= word.split("\\s");
       String wordReversed = "";

       for(String i:wordOriginal){
          wordReversed = i + " " + wordReversed ;

       }

       return wordReversed;
    }

    public String findWord(String phrase){
        String[] phraseOriginal = phrase.split("\\s");
        return phraseOriginal[phraseOriginal.length - 2];
    }

    public Boolean startsWith(String phrase,String inputWord){

        phrase  = phrase.toLowerCase();
        Boolean decision = phrase.startsWith(inputWord);
        return decision;
    }

    public boolean anagrams(String word1,String word2) {

        try {
            System.out.println(word1.charAt(0));

            String reversedWord = "";
            for (int i = word1.length() -1; i >= 0; i--) {
                reversedWord += word1.charAt(i);

            }

            boolean decision = reversedWord.equals(word2);
            return decision;

        } catch (IndexOutOfBoundsException e) {
            System.out.println("An error has occurred");
            e.printStackTrace();
            return false;
        }
    }

    public int numberReversal(int num){

        int reversedNum = 0;
        while(num != 0){
            int digit = num%10;
            reversedNum = reversedNum*10 + digit;
            num /=10;
        }return reversedNum;
    }

    public void productMaximizer(int[]nums){

        int total = 0;
        int product = 0;
        int num1 = 0,num2 =0;
        for(int i = 0; i<nums.length;i++){
            for(int j = i+ 1;j<nums.length;j++){

                product = nums[i]*nums[j];
                if(total<product){
                    total = product;
                    num1 = nums[i];
                    num2 = nums[j];
//                    System.out.println(total + "numbers are" +  nums[i] + "and"  + nums[j]);
//                }else System.out.println(total + nums[i] + nums[j]);
            }
        }

    }System.out.println(total + "numbers are" +  num1 + "and"  + num2);
    }
    public static void main(String[] args){

        Main myObj = new Main();
        String word = myObj.reverseWord("Dog bites man");
        System.out.println(word);
        String secondToLastWord = myObj.findWord("Dog bites man in the house");
        System.out.println(secondToLastWord);
        System.out.println(myObj.startsWith("My man is a drug addict","house"));
        System.out.println(myObj.anagrams("hello","olleh"));
        System.out.println(myObj.numberReversal(45671));
        int[] nums = {4,9,4,5,7,8};
        myObj.productMaximizer(nums);

    }
}