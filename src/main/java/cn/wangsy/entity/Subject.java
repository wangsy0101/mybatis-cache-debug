package cn.wangsy.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;




@Setter
@Getter
public class Subject implements Serializable {

    private Long id;

    private String name;


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
