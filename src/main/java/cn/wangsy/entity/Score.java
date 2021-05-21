package cn.wangsy.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;



@Setter
@Getter
public class Score implements Serializable {

    private Long id;

    private Long subject_id;

    private Long student_id;

    private Integer score;


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"subject_id\":")
                .append(subject_id);
        sb.append(",\"student_id\":")
                .append(student_id);
        sb.append(",\"score\":")
                .append(score);
        sb.append('}');
        return sb.toString();
    }
}
