package com.github.owgrant24.springbootone.util;

import com.github.owgrant24.springbootone.model.Car;
import com.github.owgrant24.springbootone.repository.specification.CarSpec;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

@Getter
public class CarFilter {
    private Specification<Car> spec;
    private StringBuilder filterDefinition;

    public CarFilter(Map<String, String> map) {
        this.spec = Specification.where(null);
        this.filterDefinition = new StringBuilder();
        if (map.containsKey("brand") && !map.get("brand").isEmpty()) {
            String brand = map.get("brand");
            spec = spec.and((CarSpec.brandContains(brand)));
            filterDefinition.append("&brand=").append(brand);
        }
        if (map.containsKey("model") && !map.get("model").isEmpty()) {
            String model = map.get("model");
            spec = spec.and((CarSpec.modelContains(model)));
            filterDefinition.append("&model=").append(model);
        }
        if (map.containsKey("minMileage") && !map.get("minMileage").isEmpty()) {
            int minMileage = Integer.parseInt(map.get("minMileage"));
            spec = spec.and(CarSpec.mileageGreaterThanOrEq(minMileage));
            filterDefinition.append("&minMileage=").append(minMileage);
        }
        if (map.containsKey("maxMileage") && !map.get("maxMileage").isEmpty()) {
            int maxMileage = Integer.parseInt(map.get("maxMileage"));
            spec = spec.and(CarSpec.mileageLesserThanOrEq(maxMileage));
            filterDefinition.append("&maxMileage=").append(maxMileage);
        }

    }
}
