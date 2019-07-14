package org.dean.duck.fp;

import com.google.common.collect.Lists;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkState;
import static java.lang.Character.isDigit;
import static java.util.Arrays.asList;

/**
 * @description: stream
 * @author: dean
 * @create: 2019/04/19 11:27
 */
public class StreamHandler {

    public static void main(String[] args) {
        first();
        toList();
        toMap();
        filter();
        flatMap();
        maxAndmin();
        reduce();
    }

    //从一组数值中生成一个值
    private static void reduce() {
        int sum = Stream.of(1, 2, 3).reduce(0, (acc, element) -> acc + element);
        checkState(sum == 6, "not the same");
        long sumWithStat = Stream.of(1, 2, 3).mapToInt(x -> x).summaryStatistics().getSum();
        checkState(sumWithStat == 6, "Not the same");
    }

    private static void maxAndmin() {
        Track track1 = new Track("eric", 100);
        Track track2 = new Track("duang", 240);
        Track track3 = new Track("dong", 200);
        Track track4 = new Track("son", 300);

        Albulumn albulumn1 = new Albulumn("wakaka", Lists.newArrayList(track1), Lists.newArrayList());
        Albulumn albulumn2 = new Albulumn("Morning", Lists.newArrayList(track1, track2), Lists.newArrayList());
        Albulumn albulumn3 = new Albulumn("Sun in the Seasons", Lists.newArrayList(track1, track2, track3), Lists.newArrayList());
        Albulumn albulumn4 = new Albulumn("Baka", Lists.newArrayList(track1, track2, track3, track4), Lists.newArrayList());
        Albulumn max = Stream.of(albulumn1, albulumn2, albulumn3, albulumn4).
                max(Comparator.comparing(albulumn -> albulumn.getTrackList().size())).get();
        checkState(max.equals(albulumn4), "不一致");
        Albulumn min = Stream.of(albulumn1, albulumn2, albulumn3, albulumn4)
                .min(Comparator.comparing(albulumn -> albulumn.getTrackList().size())).get();
        checkState(min.equals(albulumn1), "不一致");
    }

    private static void flatMap() {
        List<Integer> together = Stream.of(asList(1, 2, 3), asList(3, 4, 5)).flatMap(numbers -> numbers.stream()).collect(Collectors.toList());
        checkState(asList(1, 2, 3, 3, 4, 5).equals(together), "不一致");
        checkState(!asList(1, 2, 3, 4, 3, 5).equals(together), "一致");
    }

    private static void filter() {
        List<String> beginWithNumber = Stream.of("a", "1abc", "abc1").filter(value -> isDigit(value.charAt(0))).collect(Collectors.toList());
        checkState(asList("1abc").equals(beginWithNumber), "不一致");
    }

    private static void toMap() {
        List<String> collected = Stream.of("a", "b", "c").map(str -> str.toUpperCase()).collect(Collectors.toList());
        checkState(asList("A", "B", "C").equals(collected), "不一致");
    }

    private static void toList() {
        List<String> collected = Stream.of("a", "b", "c").collect(Collectors.toList());
        checkState(asList("a", "b", "c").equals(collected), "不一致");
    }

    private static void first() {
        Artist artist1 = new Artist("eric", "London");
        Artist artist2 = new Artist("eric", "New York");
        Artist artist3 = new Artist("eric", "Beijing");
        Artist artist4 = new Artist("duang", "London");
        List<Artist> artistList = Lists.newArrayList(artist1, artist2, artist3, artist4);
        long counter = artistList.stream().filter(artist -> "London".equalsIgnoreCase(artist.getNationality())).count();
        checkState(2 == counter, "不一致");
    }
}
