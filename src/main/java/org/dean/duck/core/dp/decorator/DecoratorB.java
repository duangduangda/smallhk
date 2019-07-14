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
public class DecoratorB extends Decorator {
    public DecoratorB(Component component) {
        super(component);
    }

    public void addBehavior(){
        System.out.println("DecoratorB addBehavior");
    }

    @Override
    public void operation(){
        super.operation();
        addBehavior();
    }


}
