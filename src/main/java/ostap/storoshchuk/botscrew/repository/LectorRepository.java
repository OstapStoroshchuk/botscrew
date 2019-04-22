package ostap.storoshchuk.botscrew.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ostap.storoshchuk.botscrew.entity.Lector;

import java.util.List;

@Repository
public interface LectorRepository extends JpaRepository<Lector, Long> {

//    @Query("select avg (l.salary) from Lector l")
//    Long avg();

    @Query(value = "select avg(l.salary) from Lector l join l.departments d where d.name =:depName")
    Double averageSalary(@Param("depName") String depName);

    @Query(value = "select l from Lector l where l.firstName LIKE CONCAT('%',:temp,'%') or l.lastName LIKE CONCAT('%',:temp,'%')")
    List<Lector>globalSearch(@Param("temp") String temp);
}
