package cn.wangsy.dao;


import org.apache.ibatis.annotations.Param;

public interface ScoreDao {

    int updateStudentSubjectScore(@Param("studentId") Long studentId,
                                  @Param("subjectId") Long subjectId,
                                  @Param("score") int score);
}
