package com.web.scholarship.services;

import com.web.scholarship.models.Graduation;
import com.web.scholarship.repository.GraduationRepository;
import com.web.scholarship.utils.utils.Order;
import com.web.scholarship.utils.pagination.DataFormat;
import com.web.scholarship.utils.pagination.DataParser;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GraduationService {
    private GraduationRepository graduationRepository;

    public DataFormat<Graduation> getAll(int page, int size, Order order) {
        DataParser<Graduation> parse = new DataParser<>(graduationRepository.findAll(Sort.by(Sort.Direction.fromString(order.toString()), "name")));
        return parse.format(page, size);
    }

    public Optional<Graduation> getOne(Long id) {
        return graduationRepository.findById(id);
    }

    public DataFormat<Graduation> searchByGraduation(String name, int page, int size, Order order) {
        DataParser<Graduation> parse = new DataParser<>(graduationRepository.findAllByNameContainsIgnoreCase(name).stream()
                .sorted((o1, o2) -> {
                    if (order == Order.ASC) {
                        return o1.getName().compareTo(o2.getName());
                    }
                    return o2.getName().compareTo(o1.getName());
                }).toList()
        );
        return parse.format(page, size);
    }

    @Transactional
    public List<Graduation> createOrUpdate(List<Graduation> graduation) {
        return graduationRepository.saveAll(graduation);
    }
}
