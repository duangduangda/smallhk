package com.smallhk.guava;

import com.google.common.base.Optional;

/**
 * @description:
 * @author: dean
 * @create: 2019/03/10 18:48
 */
public class OptinalHandler {
    public static void main(String[] args) {
        Optional<Integer> possible = Optional.of(5);
        System.out.println(possible.isPresent());
        System.out.println(possible.get());

        Optional<Integer> nullPossible = Optional.fromNullable(null);
        System.out.println(nullPossible.isPresent());

    }
}
