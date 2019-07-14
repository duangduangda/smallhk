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
public class Client {
    public static void main(String[] args) {
        Component component = new DecoratorA(new DecoratorB(new ConcreComponent()));
        component.operation();
        System.out.println("---------------------------------------");
        component = new DecoratorB(new DecoratorA(new ConcreComponent()));
        component.operation();
    }
}
