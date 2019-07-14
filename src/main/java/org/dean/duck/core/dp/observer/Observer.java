package org.dean.duck.core.dp.observer;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/5/4
 * <p>
 * Company:
 * <p>
 *
 * @author Administrator
 * <p>
 * Version: 1.0
 * <p>
 */
public interface Observer {
    /**
     * 更新信息(推模式)
     * @param subject
     */
     void update(Subject subject);

    /**
     * 拉模式
     * @param content
     */
    void update(String content);
}
