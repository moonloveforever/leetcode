package leetcode.day.may.fifteen;

/**
 *  给定一个字符串，找出不含有重复字符的最长子串的长度。
 * 	示例：
 * 		给定 "abcabcbb" ，没有重复字符的最长子串是 "abc" ，那么长度就是3。
 * 		给定 "bbbbb" ，最长的子串就是 "b" ，长度是1。
 * 		给定 "pwwkew" ，最长子串是 "wke" ，长度是3。请注意答案必须是一个子串，"pwke" 是 子序列  而不是子串。
 * @author Administrator
 *
 */

public class Test2 {
	public static void main(String[] args) {
		System.out.println(lengthOfLongestSubstring("abcabcbbs"));
		System.out.println(lengthOfLongestSubstring("bbbbb"));
		System.out.println(lengthOfLongestSubstring("pwwkew"));
		System.out.println(lengthOfLongestSubstring("dvdf"));
	}
	
    /**
     *  my  grade  at 58ms
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
    	if(s.isEmpty())
    		return 0;
        int count = 1;
    	int max = 1;
    	boolean flag = false;
    	String compareStr=s.substring(0, 1);
    	for (int i = 1; i < s.length(); i++) {
    		for (int j = 0; j < compareStr.length(); j++) {
    			if(compareStr.charAt(j)!=s.charAt(i))
    				flag=true;
    			else {
    				flag=false;
    				if(j+1<compareStr.length()) {
    					compareStr=compareStr.substring(j+1, compareStr.length())+s.charAt(i);
    					if(count>max)
    						max = count;
    					count=compareStr.length();
    				}
    				else {
    					compareStr=s.substring(i, i+1);
    					if(count>max)
    						max = count;
    					count=1;
    				}
    				break;
    			}
			}
    		if(flag) {
    			count++;
    			if(i+1 < s.length())
    				compareStr += s.charAt(i);
    			else if(count>max)
    					max = count;
			}
		}
		return max;
    }
    
    /**
     * the best realize at 26ms
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringBest(String s) {
        int[] list = new int[256];
        int previous = -1, right = 0, max_len = 0;
        for(int i=0;i<list.length;i++){
            list[i]=-1;
        }
        while(right<s.length()){
            char c = s.charAt(right);
            if(list[(int)c] > previous)
                previous = list[(int)c];
            max_len = Math.max(max_len, right - previous);
            list[(int)c] = right++;
        }
        return max_len;
    }
}
