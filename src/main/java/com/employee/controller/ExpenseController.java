package com.employee.controller;

import com.employee.reportvo.CategoryWiseReport;
import com.employee.service.CategoryExpenseService;
import com.employee.service.ExpenseService;
import com.employee.vo.ExpenseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/expense")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private CategoryExpenseService categoryExpenseService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String,String> addExpense(@RequestBody ExpenseVO expenseVO) {
        System.out.println(expenseVO);
        Map<String,String> m =new HashMap<>();
        m.put("message",categoryExpenseService.updateCategoryExpense(expenseVO)==true?"Expense added":"Expense not Added");
        return  m;
    }

    @RequestMapping(path = "/map", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String,String> doMapCategoryExpense(@RequestBody ExpenseVO expenseVO) {
        Map<String,String> m =new HashMap<>();
        m.put("message",categoryExpenseService.updateCategoryExpense(expenseVO)?"Updated":"Not Updated");
        return  m;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<ExpenseVO> getAllExpenses() {
        List<ExpenseVO> expenseVOS = expenseService.getAllExpenses();
        System.out.println( expenseVOS.stream().map(x->x.getCategory()).collect(Collectors.toList()));
        return expenseVOS;
    }

    @RequestMapping("/{id}")
    public ExpenseVO getExpenseById(@PathVariable("id") int id) {
        return expenseService.getExpenseById(id);
    }

    @RequestMapping("category/{id}")
    public List<ExpenseVO> getExpenseByCategoryId(@PathVariable("id") int id) {
        return expenseService.findByCategoryId(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public Map<String, String> deleteExpense(@PathVariable("id") int id) {
        Map<String,String> m =new HashMap<>();
        m.put("message", expenseService.deleteExpense(id) ? "Deleted" : "Not Deleted");
        return m;
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> updateExpense(@RequestBody ExpenseVO expenseVO) {
        Map<String,String> m =new HashMap<>();
        m.put("message", expenseService.updateExpense(expenseVO) ? "Updated" : "Not Updated");
        return m;
    }

    @RequestMapping(method = RequestMethod.GET,path = "/report/by/category")
    public List<CategoryWiseReport>  reportByCategory() {
        List<CategoryWiseReport> res=expenseService.countTotalExpenseByCategory();
        return res;
    }
}
