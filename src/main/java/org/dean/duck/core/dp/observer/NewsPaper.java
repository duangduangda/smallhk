package org.dean.duck.core.dp.observer;

import java.util.Observable;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/5/4
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class NewsPaper extends Observable {

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        // 使用jdk的Observer模式必须有下面这句话
        this.setChanged();
        notifyObservers(this.content);
    }
}
