package cat.arithmetic.leetcode.expiredLeetcode;

public class MaxArea {
    public int maxArea(int[] height) {
        int result = 0;
        int rs = 0;
        // 左指针
        int left = 0;
        // 右指针
        int right = height.length - 1;
        for (int i = 0; i < height.length; i++) {
            result = Math.min(height[left], height[right]) * (right - left);
            if(height[left] > height[right]){
                right --;
            }else{
                left ++;
            }
            if(rs < result){
                rs = result;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        MaxArea maxArea = new MaxArea();
        //                                           0 1 2 3  4  5 6
        System.out.println(maxArea.maxArea(new int[]{1,3,2,5,25,24,5}));
    }
}
