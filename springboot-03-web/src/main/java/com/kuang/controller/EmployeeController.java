package com.kuang.controller;

import com.kuang.dao.DepartmentDao;
import com.kuang.dao.EmployeeDao;
import com.kuang.pojo.Department;
import com.kuang.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

/**
 * @author Chaoqun Cheng
 * @date 2021-05-2021/5/28-13:35
 */

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    //显示所有员工信息
    @RequestMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getAllEmployees();
        model.addAttribute("emps", employees);
        return "emp/list";
    }

    //添加员工页面
    @GetMapping("/emp")
    public String toAddpage(Model model){
        //查出所有部门的信息
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments);
        return "emp/add";
    }

    //添加员工的方法
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        System.out.println("add=>"+employee);
        //添加员工
        employeeDao.addEmployee(employee);
        //跳转到主页 list方法上面的注解
        return "redirect:/emps";
    }

    //修改员工
    @GetMapping("/emp/{id}")
    public String toUpdateEmp(@PathVariable("id")Integer id, Model model){
        Employee employee = employeeDao.getEmployeeById(id);
        model.addAttribute("emp", employee);
        //查出所有部门的信息
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments);
        return "emp/update";
    }

    //修改员工提交
    @PostMapping("/updateEmp")
    public String updateEmp(Employee employee){
        employeeDao.addEmployee(employee);
        //修改完返回list
        return "redirect:/emps";
    }

    //删除员工
    @GetMapping("/delemp/{id}")
    public String deleteEmp(@PathVariable("id") int id){
        employeeDao.deleteEmployee(id);
        return "redirect:/emps";
    }

}
