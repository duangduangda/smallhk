package com.smallhk.fp;

import com.google.common.collect.Lists;

import java.util.IntSummaryStatistics;

/**
 * @description:
 * @author: dean
 * @create: 2019/04/23 17:41
 */
public class Chapter4 {
    public static void main(String[] args) {
        printTrackLengthStatistics();
    }

    private static void printTrackLengthStatistics() {
        Track track1 = new Track("akkkkka", 2000);
        Track track2 = new Track("snoopy", 20);
        Albulumn albulumn = new Albulumn("eric", Lists.newArrayList(track1, track2), Lists.newArrayList());
        IntSummaryStatistics intSummaryStatistics = albulumn.getTracks()
                .mapToInt(track -> track.getLength()).summaryStatistics();
        System.out.println(intSummaryStatistics.getMax());
        System.out.println(intSummaryStatistics.getMin());
        System.out.println(intSummaryStatistics.getSum());
        System.out.println(intSummaryStatistics.getAverage());
        System.out.println(intSummaryStatistics.getCount());
    }

}
