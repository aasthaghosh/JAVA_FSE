package com.cognizant.ormlearn;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.model.Stock;
import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.model.Department;
import com.cognizant.ormlearn.model.Skill;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.EmployeeService;
import com.cognizant.ormlearn.service.DepartmentService;
import com.cognizant.ormlearn.service.SkillService;
import com.cognizant.ormlearn.repository.StockRepository;

@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);
    
    private static CountryService countryService;
    private static StockRepository stockRepository;
    private static EmployeeService employeeService;
    private static DepartmentService departmentService;
    private static SkillService skillService;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
        
        countryService = context.getBean(CountryService.class);
        stockRepository = context.getBean(StockRepository.class);
        employeeService = context.getBean(EmployeeService.class);
        departmentService = context.getBean(DepartmentService.class);
        skillService = context.getBean(SkillService.class);

        testFindCountriesByNameContainingOrderByNameAsc();
        testFindCountriesByNameStartingWith();
        
        testGetFacebookStocksInSeptember2019();
        testGetGoogleStocksGreaterThan1250();
        testGetTop3StocksByVolumeDesc();
        testGetTop3LowestNetflixStocks();
        
        testGetEmployee();
        testAddEmployee();
        testUpdateEmployee();
        testGetDepartment();
        testAddSkillToEmployee();
    }

    private static void testFindCountriesByNameContainingOrderByNameAsc() {
        LOGGER.info("Start");
        List<Country> countries = countryService.findCountriesByNameContainingOrderByNameAsc("ou");
        for (Country country : countries) {
            LOGGER.debug("{}\t{}", country.getCode(), country.getName());
        }
        LOGGER.info("End");
    }

    private static void testFindCountriesByNameStartingWith() {
        LOGGER.info("Start");
        List<Country> countries = countryService.findCountriesByNameStartingWith("Z");
        for (Country country : countries) {
            LOGGER.debug("{}\t{}", country.getCode(), country.getName());
        }
        LOGGER.info("End");
    }

    private static void testGetFacebookStocksInSeptember2019() {
        LOGGER.info("Start");
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date start = df.parse("2019-09-01");
            Date end = df.parse("2019-09-30");
            List<Stock> stocks = stockRepository.findByCodeAndDateBetween("FB", start, end);
            for (Stock stock : stocks) {
                LOGGER.debug("{}", stock);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.info("End");
    }

    private static void testGetGoogleStocksGreaterThan1250() {
        LOGGER.info("Start");
        List<Stock> stocks = stockRepository.findByCodeAndCloseGreaterThan("GOOGL", new BigDecimal("1250.00"));
        for (Stock stock : stocks) {
            LOGGER.debug("{}", stock);
        }
        LOGGER.info("End");
    }

    private static void testGetTop3StocksByVolumeDesc() {
        LOGGER.info("Start");
        List<Stock> stocks = stockRepository.findTop3ByOrderByVolumeDesc();
        for (Stock stock : stocks) {
            LOGGER.debug("{}", stock);
        }
        LOGGER.info("End");
    }

    private static void testGetTop3LowestNetflixStocks() {
        LOGGER.info("Start");
        List<Stock> stocks = stockRepository.findTop3ByCodeOrderByCloseAsc("NFLX");
        for (Stock stock : stocks) {
            LOGGER.debug("{}", stock);
        }
        LOGGER.info("End");
    }

    private static void testGetEmployee() {
        LOGGER.info("Start");
        Employee employee = employeeService.get(1);
        LOGGER.debug("Employee:{}", employee);
        LOGGER.debug("Department:{}", employee.getDepartment());
        LOGGER.debug("Skills:{}", employee.getSkillList());
        LOGGER.info("End");
    }

    private static void testAddEmployee() {
        LOGGER.info("Start");
        Employee employee = new Employee();
        employee.setName("John Doe");
        employee.setSalary(50000.0);
        employee.setPermanent(true);
        employee.setDateOfBirth(new Date());
        Department department = departmentService.get(1);
        employee.setDepartment(department);
        employeeService.save(employee);
        LOGGER.debug("Saved Employee:{}", employee);
        LOGGER.info("End");
    }

    private static void testUpdateEmployee() {
        LOGGER.info("Start");
        Employee employee = employeeService.get(1);
        Department department = departmentService.get(2);
        employee.setDepartment(department);
        employeeService.save(employee);
        LOGGER.debug("Updated Employee:{}", employee);
        LOGGER.info("End");
    }

    private static void testGetDepartment() {
        LOGGER.info("Start");
        Department department = departmentService.get(1);
        LOGGER.debug("Department:{}", department);
        LOGGER.debug("Employees:{}", department.getEmployeeList());
        LOGGER.info("End");
    }

    private static void testAddSkillToEmployee() {
        LOGGER.info("Start");
        Employee employee = employeeService.get(1);
        Skill skill = skillService.get(2);
        Set<Skill> skills = employee.getSkillList();
        skills.add(skill);
        employeeService.save(employee);
        LOGGER.debug("Skills after addition:{}", employee.getSkillList());
        LOGGER.info("End");
    }
}
