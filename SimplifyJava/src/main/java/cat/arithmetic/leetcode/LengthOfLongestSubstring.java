package cat.arithmetic.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 最长字串
 *
 */
public class LengthOfLongestSubstring {
    public static void main(String[] args) {
       LengthOfLongestSubstring length = new LengthOfLongestSubstring();
        System.out.println(length.lengthOfLongestSubstring("dvdf"));
        System.out.println(length.lengthOfLongestSubstring("abcddabc"));
    }

    public int lengthOfLongestSubstring(String s){
        int max = 0;
        int index = 0;
        Map<Character,Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character curr = s.charAt(i);
            if(map.containsKey(curr)){
                max = index>max?index:max;
                index = 0;
//                max = max > i-map.get(curr)?max:i-map.get(curr);
//                map.clear();

                for (int j = 0; j < s.substring(0, i-1).length(); j++) {
                   map.remove(s.charAt(j));
                }
            }
            map.put(s.charAt(i),i);
            index ++;
        }
        return map.size()>max?map.size():max;
    }

//    public int lengthOfLongestSubstring(String s) {
//        char[] array = s.toCharArray();
//        List<Character> window = new ArrayList<>();
//        int mount = 0;
//        for (char c : array) {
//            if(window.contains(c)){
//                if(mount < window.size()){
//                    mount = window.size();
//                }
//                int index = window.indexOf(c);
//                window = window.subList(index+1,window.size());
//            }
//            window.add(c);
//        }
//        return mount<window.size()?window.size():mount;
//    }
}
