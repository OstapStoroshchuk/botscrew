package ostap.storoshchuk.botscrew.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ostap.storoshchuk.botscrew.entity.Department;
import ostap.storoshchuk.botscrew.repository.DepartmentRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Transactional
    public void saveDepartment(Department department) {
        departmentRepository.save(department);
    }

    @Transactional
    public List<Department> findAllDepartments(){
        return departmentRepository.findAll();
    }

    @Transactional
    public Optional<Department> findDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }
}
