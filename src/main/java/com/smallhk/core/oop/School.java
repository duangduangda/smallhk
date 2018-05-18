package com.smallhk.core.oop;

import java.io.Serializable;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018/5/17
 * <p>
 * Company:
 * <p>
 *
 * @author: eric
 * <p>
 * Version: 1.0
 * <p>
 */
public class School implements Cloneable,Serializable {

    private String name;

    public School(){
        System.out.println("School constructor ");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null)?0:name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object object){
        if (this == object){
            return true;
        }

        if (null == object){
            return false;
        }

        if (getClass() != object.getClass()){
            return false;
        }

        School school = (School)object;

        if (null == name){
            if (null != school.name){
                return false;
            }
        }else if (!name.equals(school.name)){
            return false;
        }
        return true;
    }


    @Override
    public String toString(){
        return "School[name = [" + name + "]";
    }

    @Override
    public Object clone(){
        Object object = null;
        try {
            object = super.clone();
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return object;
    }
}
