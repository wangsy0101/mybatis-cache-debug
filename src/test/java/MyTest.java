import cn.wangsy.dao.ScoreCacheRefDao;
import cn.wangsy.entity.Student;
import cn.wangsy.dao.ScoreDao;
import cn.wangsy.dao.StudentDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;


public class MyTest {

    private SqlSessionFactory factory;

    @Before
    public void before() throws Exception {
        factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config.xml"));
        System.out.println("本地缓存范围: " + factory.getConfiguration().getLocalCacheScope());
        System.out.println("二级缓存是否被启用: " + factory.getConfiguration().isCacheEnabled());
        System.out.println("==================================");
    }


    /**
     * 1、一级缓存
     */
    @Test
    public void test01() throws Exception {
        SqlSession sqlSession = factory.openSession(true);  
        StudentDao studentDao1 = sqlSession.getMapper(StudentDao.class);

        System.out.println("studentDao1 读取数据: " + studentDao1.getStudentById(1L));
        System.out.println("studentDao1 读取数据: " + studentDao1.getStudentById(1L));
        System.out.println("studentDao1 读取数据: " + studentDao1.getStudentById(1L));

        sqlSession.close();
    }


    /**
     * 1、一级缓存
     * 2、更新之后，缓存更新
     */
    @Test
    public void test02() throws Exception {
        SqlSession sqlSession = factory.openSession(true);  
        StudentDao studentDao1 = sqlSession.getMapper(StudentDao.class);

        System.out.println("studentDao1 读取数据: " + studentDao1.getStudentById(1L));
        System.out.println("studentDao1 更新数据: " + studentDao1.addStudent(buildStudent()));
        System.out.println("studentDao1 读取数据: " + studentDao1.getStudentById(1L));
        System.out.println("studentDao1 读取数据: " + studentDao1.getStudentById(1L));

        sqlSession.close();
    }


    @Test
    public void test03() throws Exception {
        SqlSession sqlSession1 = factory.openSession(true);  
        SqlSession sqlSession2 = factory.openSession(true);  

        StudentDao studentDao1 = sqlSession1.getMapper(StudentDao.class);
        StudentDao studentDao2 = sqlSession2.getMapper(StudentDao.class);

        System.out.println("studentDao1 读取数据: " + studentDao1.getStudentById(4L));
        System.out.println("studentDao1 读取数据: " + studentDao1.getStudentById(4L));
        System.out.println("studentDao2 更新数据：" + studentDao2.updateStudentName(4L, "赵六5"));
        System.out.println("studentDao1 读取数据: " + studentDao1.getStudentById(4L));
        System.out.println("studentDao2 读取数据: " + studentDao2.getStudentById(4L));

    }


    private Student buildStudent(){
        return new Student("赵六", 15);
    }


    /**
     * 当sqlsession没有调用commit()方法时，二级缓存并没有起到作用
     */
    @Test
    public void test04() throws Exception {
        SqlSession sqlSession1 = factory.openSession(true);  
        SqlSession sqlSession2 = factory.openSession(true);  

        StudentDao studentDao1 = sqlSession1.getMapper(StudentDao.class);
        StudentDao studentDao2 = sqlSession2.getMapper(StudentDao.class);

        System.out.println("studentDao1 读取数据: " + studentDao1.getStudentById(1L));
        System.out.println("studentDao2 读取数据: " + studentDao2.getStudentById(1L));

    }

    /**
     * 当sqlsession调用commit()方法时，二级缓存起作用
     */
    @Test
    public void test05() throws Exception {
        SqlSession sqlSession1 = factory.openSession(true);  
        SqlSession sqlSession2 = factory.openSession(true);  

        StudentDao studentDao1 = sqlSession1.getMapper(StudentDao.class);
        StudentDao studentDao2 = sqlSession2.getMapper(StudentDao.class);

        System.out.println("studentDao1 读取数据: " + studentDao1.getStudentById(1L));
        sqlSession1.commit();
        System.out.println("studentDao2 读取数据: " + studentDao2.getStudentById(1L));

    }



    @Test
    public void test06() throws Exception {
        SqlSession sqlSession1 = factory.openSession(true);  
        SqlSession sqlSession2 = factory.openSession(true);  
        SqlSession sqlSession3 = factory.openSession(true);  

        // 同一个namespace
        StudentDao studentDao1 = sqlSession1.getMapper(StudentDao.class);
        StudentDao studentDao2 = sqlSession2.getMapper(StudentDao.class);
        StudentDao studentDao3 = sqlSession3.getMapper(StudentDao.class);


        System.out.println("studentDao1 读取数据: " + studentDao1.getStudentById(1L));
        sqlSession1.commit();
        System.out.println("studentDao2 读取数据: " + studentDao2.getStudentById(1L));

        System.out.println("studentDao3 更新数据: " + studentDao3.updateStudentName(1L, "张三4"));
        sqlSession3.commit();
        System.out.println("studentDao2 读取数据: " + studentDao2.getStudentById(1L));
    }


    /**
     * 未设置二级缓存关联刷新，<cache-ref namespace="cn.wangsy.dao.StudentDao"/>
     */
    @Test
    public void test07() throws Exception {
        SqlSession sqlSession1 = factory.openSession(true);
        SqlSession sqlSession2 = factory.openSession(true);
        SqlSession sqlSession3 = factory.openSession(true);

        // 不同的namespace
        StudentDao studentDao1 = sqlSession1.getMapper(StudentDao.class);
        StudentDao studentDao2 = sqlSession2.getMapper(StudentDao.class);
        ScoreDao scoreDao = sqlSession3.getMapper(ScoreDao.class);

        System.out.println("studentMapper 读取数据: " + studentDao1.getStudentScoreById(1L));
        sqlSession1.close();

        System.out.println("studentDao2 读取数据: " + studentDao2.getStudentScoreById(1L));

        System.out.println("scoreDao 更新数据: " + scoreDao.updateStudentSubjectScore(1L, 1L, 76));
        sqlSession3.commit();

        System.out.println("studentDao2 读取数据: " + studentDao2.getStudentScoreById(1L));
    }


    /**
     * 设置了二级缓存关联刷新，<cache-ref namespace="cn.wangsy.dao.StudentDao"/>
     */
    @Test
    public void test08() throws Exception {
        SqlSession sqlSession1 = factory.openSession(true); 
        SqlSession sqlSession2 = factory.openSession(true); 
        SqlSession sqlSession3 = factory.openSession(true);

        // 不同的namespace
        StudentDao studentDao1 = sqlSession1.getMapper(StudentDao.class);
        StudentDao studentDao2 = sqlSession2.getMapper(StudentDao.class);
        ScoreCacheRefDao scoreCacheRefDao = sqlSession3.getMapper(ScoreCacheRefDao.class); // 设置二级缓存关联刷新


        System.out.println("studentDao1 读取数据: " + studentDao1.getStudentScoreById(1L));
        sqlSession1.commit();

        System.out.println("studentDao2 读取数据: " + studentDao2.getStudentScoreById(1L));

        System.out.println("scoreCacheRefDao 更新数据: " + scoreCacheRefDao.updateStudentSubjectScore(1L, 1L, 66));
        sqlSession3.commit();

        System.out.println("studentDao2 读取数据: " + studentDao2.getStudentScoreById(1L));
    }

}
