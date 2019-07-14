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
public class DecoratorA extends Decorator {

    public DecoratorA(Component component) {
        super(component);
    }


    public void addState(){
        System.out.println("DecoratorA addState");
    }

    @Override
    public void operation(){
        super.operation();
        addState();
    }

}
