package com.test.backend.specifications;

import com.test.backend.entities.Employee;
import com.test.backend.models.EmployeeModel;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class EmployeeSpecifications {

    public static Specification<Employee> filteredEmployeeBasedOnProps(
            final EmployeeModel employeeModel) {
        return new Specification<Employee>() {

            public Predicate toPredicate(Root<Employee> rootEmployee,
                                         CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();

                if (employeeModel.getEmployeeId() != null) {
                    predicates.add(cb.equal(
                            rootEmployee.<Integer> get("employeeId"),
                            employeeModel.getEmployeeId()));
                }

                if (employeeModel.getEmployeeName() != null && employeeModel.getEmployeeName() != "") {
                    predicates.add(cb.equal(
                            rootEmployee.<String> get("employeeName"),
                            employeeModel.getEmployeeName()));
                }

                if (employeeModel.getSalary() != null) {
                    predicates.add(cb.equal(rootEmployee.<Long> get("salary"),
                            employeeModel.getSalary()));
                }

                Predicate finalPredicate = cb.conjunction();
                for (Predicate predicate : predicates) {
                    finalPredicate = cb.and(finalPredicate,predicate);
                }

                return finalPredicate;
            }
        };
    }
}