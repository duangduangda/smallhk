package org.dean.duck.core.dp.observer;

import java.util.*;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/5/4
 * <p>
 * Company:
 * <p>
 *
 * @author
 * <p>
 * Version: 1.0
 * <p>
 */
public class Reader implements java.util.Observer{

    private String name;

    public Reader(String name) {
        this.name = name;
    }

    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     */
    @Override
    public void update(Observable o, Object arg) {
        System.out.println(name + "收到报纸了，准备进行阅读。目标推过来的内容是" + arg);
    }
}
