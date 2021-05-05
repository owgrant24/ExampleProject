package com.github.owgrant24.springbootone.repository.specification;

import com.github.owgrant24.springbootone.model.Car;
import org.springframework.data.jpa.domain.Specification;

public class CarSpec {
    public static Specification<Car> brandContains(String filterBrand) {
        return (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.like(root.get("brand"), "%" + filterBrand + "%");
    }
    public static Specification<Car> modelContains(String filterModel) {
        return (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.like(root.get("model"), "%" + filterModel + "%");
    }
    
    public static Specification<Car> mileageLesserThanOrEq(Integer value) {
        return (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.lessThanOrEqualTo(root.get("mileage"), value);
    }

    public static Specification<Car> mileageGreaterThanOrEq(Integer value) {
        return (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.greaterThanOrEqualTo(root.get("mileage"), value);
    }
}
