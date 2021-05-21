package cn.wangsy.dao;


import org.apache.ibatis.annotations.Param;


/**
 * 区别于 ScoreDao， 设置了二级缓存的关联刷新 <cache-ref namespace="cn.wangsy.dao.StudentDao"/>
 */
public interface ScoreCacheRefDao {

    int updateStudentSubjectScore(@Param("studentId") Long studentId,
                                  @Param("subjectId") Long subjectId,
                                  @Param("score") int score);
}
