package cat.arithmetic.leetcode;

import java.util.HashMap;
import java.util.Map;

public class RomanToInt {

    public int romanToInt(String s) {
        StringBuilder stringBuilder = new StringBuilder(s);
        Map<String,Integer> map = new HashMap<>();
        map.put("I",1);
        map.put("V",5);
        map.put("X",10);
        map.put("L",50);
        map.put("C",100);
        map.put("D",500);
        map.put("M",1000);
        map.put("IV",4);
        map.put("IX",9);
        map.put("XL",40);
        map.put("XC",90);
        map.put("CD",400);
        map.put("CM",900);
        // map.put("CM",1900);
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if(i+2 <= s.length()){
                String two = s.substring(i,i+2);
                if(map.containsKey(two)){
                   result = result + map.get(two);
                   i = i+1;
                }
                else{
                    result = result + map.get(two);
                }
            }else{
                result = result + map.get(s.substring(i,i+1));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        RomanToInt romanToInt = new RomanToInt();
        System.out.println(romanToInt.romanToInt("MCMXCIV"));
        System.out.println("IIIV".substring(0,4) );
    }

}
