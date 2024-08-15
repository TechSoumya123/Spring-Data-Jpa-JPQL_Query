package com.developer.soumya.repository;

import com.developer.soumya.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("select e from Employee e where e.employeeId = :employeeId")
    Optional<Employee> findEmployeeByEmployeeId(@Param("employeeId") Integer emp);

    @Query("select e from Employee e where e.employeeName = ?1")
    List<Employee> findEmployeeByEmployeeName(String employeeName);

    @Query("select e from Employee e where e.department = :department and e.age > :age")
    List<Employee> findByDepartmentAndAgeGreaterThan(@Param("department") String department, @Param("age") int age);

    List<Employee> findTop5ByAge(int age);

    @Query("select e from Employee e where e.age between :startAge and :endAge")
    List<Employee> findByAgeBetween(@Param("startAge") int startAge, @Param("endAge") int endAge);

    @Query("select e from Employee e where e.age in :ageGroup")
    List<Employee> findByAgeIn(@Param("ageGroup") List<Integer> ageGroup);

    @Query("select e from Employee e where e.joiningDate > :date")
    List<Employee> findByJoiningDateAfter(@Param("date") Date date);

    @Query("select e from Employee e where e.joiningDate < ?1")
    List<Employee> findByJoiningDateBefore(Date date);

    @Query("select e from Employee e where e.joiningDate between :startDate and :endDate")
    List<Employee> findByJoiningDateBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    //    @Query("select e from Employee e where e.joiningDate between ?1 and ?2 and e.department = ?3")
    @Query("select e from Employee e where e.joiningDate between :startDate and :endDate and e.department = :department")
    List<Employee> findEmployeeByJoiningDateBetweenAndDepartment(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("department") String department);

    @Query("select e from Employee e where e.employeeName = :employeeName")
    List<Employee> findEmployeeByEmployeeNameEquals(@Param("employeeName") String employeeName);

    @Query("select e from Employee e where e.employeeName <> ?1")
    List<Employee> findEmployeeByEmployeeNameIsNot(String employeeName);

    @Query("select e from Employee e where e.leftOn is null")
    List<Employee> findByLeftOnIsNull();

    @Query("select e from Employee e where e.employeeName = :name")
    List<Employee> findByEmployeeNameEquals(@Param("name") String name);

    @Query("select e from Employee e where e.employeeName is null")
    List<Employee> findByEmployeeNameIsNull();

    @Query("select e from Employee e where e.employeeName is not null")
    List<Employee> findByEmployeeNameIsNotNull();

    @Query("select e from Employee e where e.employeeName = :employeeName order by e.joiningDate asc")
    List<Employee> findEmployeeByEmployeeNameOrderByJoiningDateAsc(@Param("employeeName") String employeeName);

    @Query("select e from Employee e where e.employeeName = ?1 order by e.joiningDate desc")
    List<Employee> findEmployeeByEmployeeNameOrderByJoiningDateDesc(String employeeName);

    @Query("select e from Employee e where e.leftJob = true")
    List<Employee> findByLeftJobTrue();

    @Query("select e from Employee e where e.leftJob = false")
    List<Employee> findByLeftJobFalse();


    @Query("select e from Employee e where e.leftJob = :leftJob")
    List<Employee> findByLeftJob(@Param("leftJob") boolean leftJob);

    @Query("select e from Employee e where e.employeeName like concat(?1, '%')")
    List<Employee> findAllByEmployeeNameStartingWith(String name);

    @Query("select e from Employee e where e.employeeName like concat('%', :name, '%')")
    List<Employee> findAllByEmployeeNameContaining(@Param("name") String name);


    @Query("""
            select e from Employee e
            where e.employeeName like concat(:prefix, '%') and e.joiningDate between :startDate and :endDate""")
//        @Query("select e from Employee e where e.employeeName like concat(?1, '%') and e.joiningDate between ?2 and ?3")
    List<Employee> findAllByEmployeeNameStartingWithAndJoiningDateBetween(String prefix, Date startDate, Date endDate);

    @Query("select e from Employee e where e.employeeName like concat('%', :name)")
    List<Employee> findByEmployeeNameEndingWith(@Param("name") String name);


}