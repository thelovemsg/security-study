package springinflearnstudy.study;

import java.util.*;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {
        int[] progresses = {93, 30, 55};
        Stack<Integer> stack = new Stack<>();
        for (int progress : progresses) {
            stack.push(progress);
        }
        int[] result = new int[stack.size()];
        int[] speeds = {1,30,5};

        int days = 0;
        boolean isAllDone = false;
        while(!isAllDone){
            isAllDone = true;
            for(int i=0; i<progresses.length; i++){
                progresses[i] +=speeds[i];
                if(progresses[i]>=100){
                    progresses[i] = Integer.MIN_VALUE;
                    result[i] = days++;
                    isAllDone=false;
                }
            }

        }
    }
}
