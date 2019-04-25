package com.smallhk.fp;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkState;
import static com.google.common.primitives.Ints.asList;
import static java.util.stream.Collectors.*;

/**
 * @description:
 * @author: dean
 * @create: 2019/04/24 10:36
 */
public class Chapter5 {
    public static void main(String[] args) {
        ordering();
        transfer2OtherCollection();
        transfer2Value();
        partitionData();
        groupData();
        transfer2String();
        combination1();
        combination2();
        countUsingReduce();
    }

    private static void countUsingReduce() {
        List<Integer> numbers = Lists.newArrayList(1, 2, 3, 4);
        checkState(4 == countNumbers(numbers), "not equals");
    }

    private static int countNumbers(List<Integer> numbers) {
        return numbers.stream().reduce(0, (x, y) -> 1, (left, right) -> left + right);
    }

    private static void combination2() {
        Artist artist1 = new Artist("eric1", "China");
        List<Artist> members = Lists.newArrayList(artist1);
        Artist artist2 = new Artist("eric2", members, "China");
        members.add(artist2);
        Artist artist3 = new Artist("eric3", members, "USA");
        Track track1 = new Track("ericduang", 200);
        Track track2 = new Track("ericduang", 200);
        Albulumn albulumn1 = new Albulumn("allalal", Lists.newArrayList(track1), Lists.newArrayList(artist1, artist2, artist3));
        Albulumn albulumn2 = new Albulumn("goooooooolal", Lists.newArrayList(track2), Lists.newArrayList(artist2, artist3));
        System.out.println(nameOfAlbumns(Stream.of(albulumn1, albulumn2)));
    }

    /**
     * 每个艺术家的专辑名
     *
     * @param albulumnStream
     */
    private static Map<Artist, List<String>> nameOfAlbumns(Stream<Albulumn> albulumnStream) {
        return albulumnStream.collect(groupingBy(Albulumn::getMainMusician, mapping(Albulumn::getName, toList())));
    }

    private static void combination1() {
        Artist artist1 = new Artist("eric1", "China");
        List<Artist> members = Lists.newArrayList(artist1);
        Artist artist2 = new Artist("eric2", members, "China");
        members.add(artist2);
        Artist artist3 = new Artist("eric3", members, "USA");
        Track track = new Track("ericduang", 200);
        Albulumn albulumn = new Albulumn("allalal", Lists.newArrayList(track), Lists.newArrayList(artist1, artist2, artist3));
        System.out.println(numberOfalbum(Stream.of(albulumn)));
    }

    /**
     * 计算每个艺术家的专辑数
     *
     * @param albulumnStream
     * @return
     */
    private static Map<Artist, Long> numberOfalbum(Stream<Albulumn> albulumnStream) {
        return albulumnStream.collect(groupingBy(albulumn -> albulumn.getMainMusician(), counting()));
    }

    private static void transfer2String() {
        Artist artist1 = new Artist("eric1", "China");
        Artist artist2 = new Artist("eric2", "China");
        Artist artist3 = new Artist("eric3", "USA");
        System.out.println(joinArtistNameIntoString1(Stream.of(artist1, artist2, artist3)));
        System.out.println(joinArtistNameIntoString2(Stream.of(artist1, artist2, artist3)));
    }

    private static String joinArtistNameIntoString1(Stream<Artist> artistStream) {
        return artistStream.map(Artist::getName).collect(Collectors.joining(",", "[", "]"));
    }

    private static String joinArtistNameIntoString2(Stream<Artist> artistStream) {
        return artistStream.map(Artist::getName).collect(new StringCollector(",", "[", "]"));
    }

    private static void groupData() {
        Artist artist1 = new Artist("eric1", "China");
        List<Artist> members = Lists.newArrayList(artist1);
        Artist artist2 = new Artist("eric2", members, "China");
        members.add(artist2);
        Artist artist3 = new Artist("eric3", members, "USA");
        Track track = new Track("ericduang", 200);
        Albulumn albulumn = new Albulumn("allalal", Lists.newArrayList(track), Lists.newArrayList(artist1, artist2, artist3));
        System.out.println(albumsByArtist(Stream.of(albulumn)));
    }

    /**
     * 按主唱进行分组
     *
     * @param albulumnStream
     * @return
     */
    private static Map<Artist, List<Albulumn>> albumsByArtist(Stream<Albulumn> albulumnStream) {
        return albulumnStream.collect(groupingBy(Albulumn::getMainMusician));
    }

    private static void partitionData() {
        Artist artist1 = new Artist("eric1", "China");
        List<Artist> members = Lists.newArrayList(artist1);
        Artist artist2 = new Artist("eric2", members, "China");
        members.add(artist2);
        Artist artist3 = new Artist("eric3", members, "USA");
        System.out.println(bandsAndSolo(Stream.of(artist1, artist2, artist3)));
    }

    /**
     * 区分乐队和独唱
     *
     * @param artistStream
     * @return
     */
    private static Map<Boolean, List<Artist>> bandsAndSolo(Stream<Artist> artistStream) {
//        return artistStream.collect(partitioningBy(artist -> artist.isSolo()));
        return artistStream.collect(partitioningBy(Artist::isSolo));
    }


    private static void transfer2Value() {
        Artist artist1 = new Artist("eric1", "China");
        List<Artist> members = Lists.newArrayList(artist1);
        Artist artist2 = new Artist("eric2", members, "China");
        members.add(artist2);
        Artist artist3 = new Artist("eric3", members, "USA");
        System.out.println(biggestGroup(Stream.of(artist1, artist2, artist3)).get());
        System.out.println(averageNumberOfMember(Stream.of(artist1, artist2, artist3)));
    }

    /**
     * 平均数
     *
     * @param artistStream
     * @return
     */
    private static double averageNumberOfMember(Stream<Artist> artistStream) {
        return artistStream.collect(averagingLong(artist -> artist.getMembers().count()));
    }

    /*
     * 找出成员最多的乐队
     * @param artistStream
     * @return
     */
    private static Optional<Artist> biggestGroup(Stream<Artist> artistStream) {
        return artistStream.collect(maxBy(Comparator.comparing(artist -> artist.getMembers().count())));
    }

    private static void transfer2OtherCollection() {
        List<Integer> numbers = asList(2, 43, 1, 41, 4);
//        System.out.println(numbers.stream().collect(toCollection(TreeSet::new)));
    }

    private static void ordering() {
        List<Integer> numbers = asList(1, 2, 3, 4);
        List<Integer> stillOrdered = numbers.stream().map(x -> x + 1).collect(Collectors.toList());
        checkState(Objects.equal(asList(2, 3, 4, 5), stillOrdered), "not equals");
        Set<Integer> unordered = Sets.newHashSet(numbers);
        List<Integer> stillUnOrdered = unordered.stream().map(x -> x + 1).collect(Collectors.toList());
        System.out.println(stillUnOrdered);
    }
}
