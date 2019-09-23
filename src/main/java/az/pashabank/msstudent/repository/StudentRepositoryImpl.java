package az.pashabank.msstudent.repository;

import az.pashabank.msstudent.dto.StudentDTO;
import az.pashabank.msstudent.model.StudentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Repository
public class StudentRepositoryImpl implements StudentRepository{

    private static final String findAllStudentSql = "select s.id as student_id, s.name, s.lastname, s.college_id  from student s ";
    private static final String findAllStudentByIdSql = "select s.id as student_id, s.name, s.lastname, c.id as college_id, c.name as university, c.city from student s inner join college c on s.college_id = c.id where  s.id = ?";
    private static final String addStudentSql = "insert into student (id, name, lastname,college_id) values (?,?,?,?)";
    private static final String updateStudentByIdSql = "update student set name = ?, lastname = ?, college_id = ? where id = ?";
    private static final String deleteStudentSql = "DELETE from student where id = ?";


    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<StudentDTO> findAllStudents() {
        List<StudentDTO> studentList =jdbcTemplate.query(findAllStudentSql, new Object[]{}, new ResultSetExtractor<List<StudentDTO>>() {
            @Override
            public List<StudentDTO> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<StudentDTO> list = new LinkedList<>();

                while (rs.next()) {
                    StudentDTO studentDTO = new StudentDTO();
                    studentDTO.setId(rs.getLong("student_id"));
                    studentDTO.setName(rs.getString("name"));
                    studentDTO.setLastname(rs.getString("lastname"));
                    studentDTO.setCollege(rs.getInt("college_id"));
                    list.add(studentDTO);
                }
                return list;
            }
        });
        return studentList;
    }

    @Override
    public StudentDTO findStudentById(Long studentId) {

        StudentDTO studentDTO = jdbcTemplate.queryForObject(findAllStudentByIdSql, new Object[]{studentId}, new RowMapper<StudentDTO>() {
            @Override
            public StudentDTO mapRow(ResultSet rs, int i) throws SQLException {
                StudentDTO studentDTO1 = new StudentDTO();
                studentDTO1.setId(rs.getLong("student_id"));
                studentDTO1.setName(rs.getString("name"));
                studentDTO1.setLastname(rs.getString("lastname"));
                studentDTO1.setCollege(rs.getInt("college_id"));
                return studentDTO1;
            }
        });


        return studentDTO;
    }
    @Override
    public void addStudent(StudentModel s) {
    int affectedRows = jdbcTemplate.update(addStudentSql, s.getId(), s.getName(),s.getLastname(),s.getCollege());
    }

    @Override
    public void updateStudent(StudentModel s) {
    int affectedRows = jdbcTemplate.update(updateStudentByIdSql, s.getName(),s.getLastname(), s.getCollege(), s.getId());

    }

    @Override
    public void deleteStudentById(Long studentId) {
        int affectedRows = jdbcTemplate.update(deleteStudentSql, studentId);

    }

}
