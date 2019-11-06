package org.rpnkv.hr.ivprep.warmup;

public class RepeatedString {

    static long repeatedString(String s, long n) {
        long totalRepetitions = n / s.length(),
                lastIndex = n % s.length() - 1,
                count = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'a') {
                count += totalRepetitions;
                if(i <= lastIndex){
                    count++;
                }
            }
        }

        return count;
    }

}
