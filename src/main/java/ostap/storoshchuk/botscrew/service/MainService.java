package ostap.storoshchuk.botscrew.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ostap.storoshchuk.botscrew.entity.Department;
import ostap.storoshchuk.botscrew.entity.Lector;
import ostap.storoshchuk.botscrew.entity.LectorsType;
import ostap.storoshchuk.botscrew.entity.SingleDean;
import ostap.storoshchuk.botscrew.repository.DepartmentRepository;
import ostap.storoshchuk.botscrew.repository.LectorRepository;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class MainService {

    @Autowired
    private LectorRepository lectorRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @PostConstruct
    @Transactional
    public void postConstruct() {


        Lector lector = new Lector();
        lector.setFirstName("Sviatic");
        lector.setLastName("Tarasuk");
        lector.setLectorsType(LectorsType.PROFESSOR);
        lector.setSalary(25000d);

        Lector lector1 = new Lector();
        lector1.setFirstName("Roman");
        lector1.setLastName("Mikhalo");
        lector1.setLectorsType(LectorsType.PROFESSOR);
        lector1.setSalary(15000d);

        Lector lector2 = new Lector();
        lector2.setFirstName("Petro");
        lector2.setLastName("Petrov");
        lector2.setLectorsType(LectorsType.PROFESSOR);
        lector2.setSalary(18000d);


        Department department = new Department();
        department.setName("MexMat");
        // SingleDean singleDean;
        department.setDean(SingleDean.getInstance("Zelisco").getName());


        department.getLectors().add(lector); // add references to department
        department.getLectors().add(lector1); // add references to department
        department.getLectors().add(lector2); // add references to department

        lector.getDepartments().add(department); // add references to lector

        departmentRepository.save(department); // save from the side where use mapped by = ""

        Scanner sc = new Scanner(System.in);
        boolean b = true;
        while (b) {
            System.out.println("1 - Who is head_of_department {department_name}");
            System.out.println("2 - Show {department_name} statistic");
            System.out.println("3 - Show the average salary for department {department_name}");
            System.out.println("4 - Show count of employee for {department_name}");
            System.out.println("5 - Global search by {template}");
            String department_name = null;
            switch (sc.nextInt()) {
                case 1:
                    System.out.println("Who is head of department {department_name}");
                    System.out.println("Enter {department_name}:");
                    department_name = sc.next();
                    String head_of_department_name = departmentRepository.findByDepartmentName(department_name).getDean();
                    System.out.println("Head of {" + department_name + "} department is " +
                            "{" + head_of_department_name + "}");
                    break;
                case 2:
                    System.out.println("Enter {department_name}:");
                    department_name = sc.next();
                    System.out.println(" assistans - {" + departmentRepository.countOfAssistants(department_name) + "}.\n" +
                            "associate professors - {" + departmentRepository.countOfAssociateProfessors(department_name) + "}\n" +
                            "professors -{" + departmentRepository.countOfProfessors(department_name) + "}");
                    break;
                case 3:
                    System.out.println("Show the average salary for department {department_name}");
                    System.out.println("Enter {department_name}:");
                    department_name = sc.next();
                    System.out.println("The average salary of {" + department_name + "} is\n" +
                            "{" + lectorRepository.averageSalary(department_name) + "}");
                    break;
                case 4:
                    System.out.println("Enter {department_name}:");
                    department_name = sc.next();
                    System.out.println(departmentRepository.countOfEmployee(department_name));
                    break;
                case 5:
                    System.out.println("Global search by:");
                    System.out.println("Enter some world:");
                    System.out.println(lectorRepository.globalSearch(sc.next()));
                    break;
                case 0:
                    b = false;
                    break;
            }
        }
    }

    @Transactional
    public void printAllLectors() {
        lectorRepository.findAll().forEach(lector -> {
            System.out.print(lector.getFirstName() + " ");
            System.out.print(lector.getLastName() + " ");
            System.out.print(lector.getLectorsType() + "\n");
        });
    }

}
