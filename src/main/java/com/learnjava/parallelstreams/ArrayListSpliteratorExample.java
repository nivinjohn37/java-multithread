package com.learnjava.parallelstreams;

import com.learnjava.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;

public class ArrayListSpliteratorExample {

    public List<Integer> multiplyEachValue(ArrayList<Integer> inputList, int multiplyValue, boolean isParallel) {
        startTimer();
        Stream<Integer> integerStream = inputList.stream();
        if(isParallel){
            integerStream = integerStream.parallel();
        }
        List<Integer> resultList = integerStream.map(integer -> integer * multiplyValue)
                .toList();
        timeTaken();
        return resultList;
    }
}
