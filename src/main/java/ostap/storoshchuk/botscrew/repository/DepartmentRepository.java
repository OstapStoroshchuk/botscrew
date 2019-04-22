package ostap.storoshchuk.botscrew.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ostap.storoshchuk.botscrew.entity.Department;


@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query(value = "select d from Department d where d.name =:depName")
    Department findByDepartmentName(@Param("depName") String depName);

    @Query(value = "select count(d) from Department d join d.lectors l " +
            "where  d.name =:depName and l.lectorsType = ostap.storoshchuk.botscrew.entity.LectorsType.PROFESSOR")
    Long countOfProfessors(@Param("depName") String depName);

    @Query(value = "select count(d) from Department d join d.lectors l " +
            "where d.name =:depName and l.lectorsType = ostap.storoshchuk.botscrew.entity.LectorsType.ASSOCIATE_PROFESSOR")
    Long countOfAssociateProfessors(@Param("depName") String depName);

    @Query(value = "select count(d) from Department d join d.lectors l " +
            "where d.name =:depName and l.lectorsType = ostap.storoshchuk.botscrew.entity.LectorsType.ASSISTANT ")
    Long countOfAssistants(@Param("depName") String depName);

    @Query(value = "select count(d) from Department d join d.lectors l " +
            "where d.name =:depName")
    Long countOfEmployee(@Param("depName") String depName);


}
