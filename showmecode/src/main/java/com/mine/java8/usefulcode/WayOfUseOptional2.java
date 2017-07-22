package com.mine.java8.usefulcode;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by Administrator on 2017/7/15.
 */
public class WayOfUseOptional2 {

    public static void main(String args[]) {
        WayOfUseOptional2 guavaTester = new WayOfUseOptional2();

        Integer invalidInput = null;
        Optional<Integer> a = Optional.of(invalidInput);
        Optional<Integer> b = Optional.of(new Integer(10));
        System.out.println(guavaTester.sum(a, b));
    }

    public Integer sum(Optional<Integer> a, Optional<Integer> b) {
        return a.orElse(0) + b.orElse(0);
    }

}
