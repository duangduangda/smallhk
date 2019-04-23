package com.smallhk.fp;

/**
 * @description:
 * @author: dean
 * @create: 2019/04/22 16:41
 */
public class Artist {
    private String name;
    private String from;

    public Artist(String name, String from) {
        this.name = name;
        this.from = from;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }


    public Artist isFrom(String from) {
        if (this.from.equalsIgnoreCase(from)) {
            return this;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "name='" + name + '\'' +
                ", from='" + from + '\'' +
                '}';
    }
}
