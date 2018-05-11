package com.smallhk.core.dp.observer;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/5/4
 * <p>
 * Company:
 * <p>
 *
 * @Author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class ConcreteSubject extends Subject {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
//        notifyObservers();//推模式
        notifyObservers(content);//拉模式
    }
}
