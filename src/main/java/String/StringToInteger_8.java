package String;

import com.sun.org.apache.bcel.internal.generic.RETURN;

/**
 * Created by yuehu on 7/22/19.
 * Example 1:

 Input: "42"
 Output: 42
 Example 2:

 Input: "   -42"
 Output: -42
 Explanation: The first non-whitespace character is '-', which is the minus sign.
 Then take as many numerical digits as possible, which gets 42.
 Example 3:

 Input: "4193 with words"
 Output: 4193
 Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.
 Example 4:

 Input: "words and 987"
 Output: 0
 Explanation: The first non-whitespace character is 'w', which is not a numerical
 digit or a +/- sign. Therefore no valid conversion could be performed.
 Example 5:

 Input: "-91283472332"
 Output: -2147483648
 Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
 Thefore INT_MIN (−231) is returned.
 Time: O(n)
 Space: O(1)
 */
public class StringToInteger_8 {
    public int myAtoi(String str) {
        if(str == null || str.length() == 0) return 0;
        str = str.trim();
        int sign = 1;
        int start = 0;
        char firstChar = str.charAt(0);
        long res = 0;
        if(firstChar == '+'){
            sign = 1;
            start++;
        }else if(firstChar == '-'){
            sign = -1;
            start++;
        }

        for(int i = 0 ; i < str.length(); i++){
            if(!Character.isDigit(str.charAt(i))){
                return (int)res * sign;
            }

            res = res * 10 + str.charAt(i) - '0';
            if(sign == 1 && res > Integer.MAX_VALUE) return Integer.MAX_VALUE;
            if(sign == -1 && res < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        }
        return (int)res * sign;
    }


}
