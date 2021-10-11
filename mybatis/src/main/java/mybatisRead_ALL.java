import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class mybatisRead_ALL { 

   public static void main(String args[]) throws IOException{

      Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);		
      SqlSession session = sqlSessionFactory.openSession();
      
      //select contact all contacts		
//      SelectStudent(1,session);
//      selectAllStudents(session);
      updateStudent(session, 1);     
      session.commit();   
      session.close();			
   }
   
   private static void SelectStudent(int id,SqlSession session) {
		Student student = session.selectOne("Student.getById", id);
		if(student != null)
			System.out.println(student.getEmail());
	}
  
  private static void selectAllStudents(SqlSession session) {
		List<Student> student = session.selectList("Student.getAll");

		for (Student st : student) {
			System.out.println(st.getId());
			System.out.println(st.getName());
			System.out.println(st.getBranch());
			System.out.println(st.getPercentage());
			System.out.println(st.getEmail());
			System.out.println(st.getPhone());
		}
	}
  
  private static void updateStudent(SqlSession session, int id) {
	  Student student = session.selectOne("Student.getById", id);
	  student.setEmail("mohamad123@yahoo.com");
      student.setPhone(90000000);
      
      //Update the student record
      session.update("Student.update",student);
      System.out.println("Record updated successfully");
  }
  
  
} 