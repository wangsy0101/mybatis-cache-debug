package cn.wangsy.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Setter
@Getter
public class Student implements Serializable {

	private Long id;

	private String name;

	private Integer age;


	public Student() {
	}

	public Student(String name, Integer age) {
		this.name = name;
		this.age = age;
	}


	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("{");
		sb.append("\"id\":")
				.append(id);
		sb.append(",\"name\":\"")
				.append(name).append('\"');
		sb.append(",\"age\":")
				.append(age);
		sb.append('}');
		return sb.toString();
	}
}
