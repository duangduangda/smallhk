package org.dean.duck.core.dp.decorator;

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
public class ConcreComponent implements Component{
    @Override
    public void operation() {
        System.out.println("ConcreComponent operation...");
    }
}
