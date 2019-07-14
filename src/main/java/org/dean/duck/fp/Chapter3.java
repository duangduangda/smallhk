package org.dean.duck.fp;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

/**
 * @description:
 * @author: dean
 * @create: 2019/04/23 13:12
 */
public class Chapter3 {
    public static void main(String[] args) {
        example1_1();
        example1_2();
        example1_3();
        example2();
        example6();
        example7();
    }

    public static <I, O> List<O> map(Stream<I> stream, Function<I, O> mapper) {
        return stream.reduce(new ArrayList<O>(), (acc, x) -> {
            // We are copying data from acc to new list instance. It is very inefficient,
            // but contract of Stream.reduce method requires that accumulator function does
            // not mutate its arguments.
            // Stream.collect method could be used to implement more efficient mutable reduction,
            // but this exercise asks to use reduce method.
            List<O> newAcc = new ArrayList<>(acc);
            newAcc.add(mapper.apply(x));
            return newAcc;
        }, (List<O> left, List<O> right) -> {
            // We are copying left to new list to avoid mutating it.
            List<O> newLeft = new ArrayList<>(left);
            newLeft.addAll(right);
            return newLeft;
        });
    }

    public static <I> List<I> filter(Stream<I> stream, Predicate<I> predicate) {
        List<I> initial = new ArrayList<>();
        return stream.reduce(initial,
                (List<I> acc, I x) -> {
                    if (predicate.test(x)) {
                        // We are copying data from acc to new list instance. It is very inefficient,
                        // but contract of Stream.reduce method requires that accumulator function does
                        // not mutate its arguments.
                        // Stream.collect method could be used to implement more efficient mutable reduction,
                        // but this exercise asks to use reduce method explicitly.
                        List<I> newAcc = new ArrayList<>(acc);
                        newAcc.add(x);
                        return newAcc;
                    } else {
                        return acc;
                    }
                },
                Chapter3::combineLists);
    }

    private static <I> List<I> combineLists(List<I> left, List<I> right) {
        // We are copying left to new list to avoid mutating it.
        List<I> newLeft = new ArrayList<>(left);
        newLeft.addAll(right);
        return newLeft;
    }

    private static void example2() {
        Artist artist1 = new Artist("eric1", "China");
        List<Artist> members = Lists.newArrayList(artist1);
        Artist artist2 = new Artist("eric2", members, "China");
        members.add(artist2);
        Artist artist3 = new Artist("eric3", members, "USA");
        System.out.println("members:" + countBandMembersInternal(Lists.newArrayList(artist1, artist2, artist3)));
    }

    private static int countBandMembersInternal(List<Artist> artists) {
        return artists.stream().map(artist -> artist.getMembers().count()).reduce(0L, Long::sum).intValue();
//        return (int)artists.stream().flatMap(artist -> artist.getMembers()).count();
    }

    private static void example1_3() {
        List<Albulumn> albulumns = Lists.newArrayList();
        getAlbulumnWithAtMostThreeTracks(albulumns);
    }

    private static List<Albulumn> getAlbulumnWithAtMostThreeTracks(List<Albulumn> albulumns) {
        return albulumns.stream().filter(albulumn -> albulumn.getTrackList().size() <= 3).collect(Collectors.toList());
    }

    private static void example1_2() {
        List<Artist> artists = Lists.newArrayList();
        getNameAndOrigins(artists);
    }

    private static List<String> getNameAndOrigins(List<Artist> artists) {
        return artists.stream().flatMap(artist -> Stream.of(artist.getName(), artist.getNationality())).collect(Collectors.toList());
    }

    private static void example7() {
        checkState("".equals(mostLowercaseString(Lists.newArrayList()).get()), "No value present");
        checkState("abd".equals(mostLowercaseString(Lists.newArrayList("abd", "YHHKKA", "daYYYYLLLL")).get()), "wrong answer");
    }

    /**
     * 在一个字符串列表中，找出包含最多小写字母的字符串。对于空列表，返回 Optional
     * <String> 对象。
     */
    private static Optional<String> mostLowercaseString(List<String> stringList) {
        checkNotNull(stringList, "strings can not be null");
        return stringList.stream().max(Comparator.comparing(Chapter3::countLowerLetters));
    }

    private static void example6() {
        checkState(3 == countLowerLetters("aEERRECDSFEbFe"), "wrong answer");
        checkState(0 == countLowerLetters(""), "wrong answer");
        checkState(0 == countLowerLetters(null), "wrong answer");
    }

    /**
     * 计算一个字符串中小写字母的个数
     *
     * @param string
     * @return
     */
    private static int countLowerLetters(String string) {
        checkNotNull(string, "string can not be null");
        return (int) string.chars().filter(Character::isLowerCase).count();
    }


    private static void example1_1() {
        checkState(10 == addUp(Stream.of(1, 2, 3, 4)), "wrong answer");
    }

    private static int addUp(Stream<Integer> numbers) {
        return numbers.reduce((x, y) -> x + y).get();
    }
}
