package org.dean.duck.mybatis.model;

import lombok.Data;

/**
 * @description:
 * @author: dean
 * @create: 2019/07/14 19:08
 */
@Data
public class Emp {
	private Integer id;
	private String name;
	private String deg;
	private String salary;
	private String dept;
}
