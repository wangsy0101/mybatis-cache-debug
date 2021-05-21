package cn.wangsy.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class StudentScore implements Serializable {

    private Long studentId;         // 学生id

    private String studentName;     // 学生姓名

    private Integer studentAge;     // 学生年龄

    private String subjectName;     // 学科

    private Integer score;          // 成绩


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"studentId\":")
                .append(studentId);
        sb.append(",\"studentName\":\"")
                .append(studentName).append('\"');
        sb.append(",\"studentAge\":")
                .append(studentAge);
        sb.append(",\"subjectName\":\"")
                .append(subjectName).append('\"');
        sb.append(",\"score\":")
                .append(score);
        sb.append('}');
        return sb.toString();
    }
}
