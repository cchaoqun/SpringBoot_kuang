package com.kuang.dao;

import com.kuang.pojo.Department;
import com.kuang.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Chaoqun Cheng
 * @date 2021-05-2021/5/27-23:48
 */

//员工Dao
@Repository
public class EmployeeDao {

    private static Map<Integer, Employee> employees = null;

    @Autowired
    private DepartmentDao departmentDao;
    static {
        employees = new HashMap<Integer, Employee>();
        employees.put(1001,new Employee(1001, "AA", "A123456@qq.com", 0, new Department(101,"部门1")));
        employees.put(1002,new Employee(1002, "BB", "B123456@qq.com", 1, new Department(101,"部门2")));
        employees.put(1003,new Employee(1003, "CC", "C123456@qq.com", 0, new Department(101,"部门3")));
        employees.put(1004,new Employee(1004, "DD", "D123456@qq.com", 1, new Department(101,"部门4")));
        employees.put(1005,new Employee(1005, "EE", "E123456@qq.com", 0, new Department(101,"部门5")));

    }

    //主键自增
    private static Integer initId = 1006;
    //增加一个员工
    public void addEmployee(Employee employee){
        if(employee.getId()==null){
            employee.setId(initId++);
        }
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));

        employees.put(employee.getId(), employee);
    }

    //查询全部员工信息
    public Collection<Employee> getAllEmployees(){
        return employees.values();
    }

    //通过id查询员工
    public Employee getEmployeeById(Integer id){
        return employees.get(id);
    }

    //删除员工
    public void deleteEmployee(Integer id){
        employees.remove(id);
    }




}
