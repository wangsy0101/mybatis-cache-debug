package cn.wangsy.dao;

import cn.wangsy.entity.Student;
import cn.wangsy.entity.StudentScore;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentDao {


	Student getStudentById(@Param("studentId") Long studentId);

	int addStudent(Student student);

	int updateStudentName(@Param("studentId") Long studentId, @Param("studentName") String studentName);

	List<StudentScore> getStudentScoreById(@Param("studentId") Long studentId);
}
