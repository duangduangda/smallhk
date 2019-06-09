package com.smallhk.guava.basic;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.*;
import com.smallhk.fp.Track;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author dean
 * @since 2019-06-04
 */
public class MapsHandler {
    public static void main(String[] args) {
        Track track1 = new Track("Zhangsan", 10);
        Track track2 = new Track("Lisi", 20);
        List<Track> tracks = Lists.newArrayList(track1, track2);
        // List转成map.转化后有唯一的key值
        Map<String,Track> trackMap = Maps.uniqueIndex(tracks,Track::getName);
        System.out.println(trackMap);
        Set<Track> trackSet = Sets.newHashSet(track1, track2);
        // Set转成Map
        Map<Track, String> trackStringMap = Maps.asMap(trackSet,Track::getName);
        System.out.println(trackStringMap);
        // 转化Map的value值
        Map<String,Integer> transformValueMap = Maps.transformValues(trackMap,Track::getLength);
        System.out.println(transformValueMap);
        // 过滤Map
        System.out.println(Maps.filterValues(trackMap, track -> track.getLength() > 15));
        // 获取两个map不同点
        Map<String, Integer> left = ImmutableMap.of("a", 1, "b", 2, "c", 3);
        Map<String, Integer> right = ImmutableMap.of("a", 1, "b", 1, "d", 3);
        MapDifference<String, Integer> diff = Maps.difference(left, right);
        System.out.println(diff);
        // 获取共同点
        System.out.println(diff.entriesInCommon());
        //只存在的左边的项
        System.out.println(diff.entriesOnlyOnLeft());
        // 只存在在右边的项
        System.out.println(diff.entriesOnlyOnRight());
        // 键相同，但是值不同的项
        System.out.println(diff.entriesDiffering());
    }

}
