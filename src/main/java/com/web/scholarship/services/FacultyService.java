package com.web.scholarship.services;

import com.web.scholarship.models.Faculty;
import com.web.scholarship.repository.FacultyRepository;
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
public class FacultyService {
    private FacultyRepository facultyRepository;

    public DataFormat<Faculty> getAll(int page, int size, Order order) {
        DataParser<Faculty> parse = new DataParser<>(facultyRepository.findAll(Sort.by(Sort.Direction.fromString(order.toString()), "name")));
        return parse.format(page, size);
    }

    public Optional<Faculty> getOne(Long id) {
        return facultyRepository.findById(id);
    }

    public DataFormat<Faculty> searchByFaculty(String name, int page, int size, Order order) {
        DataParser<Faculty> parse = new DataParser<>(facultyRepository.findAllByNameContainsIgnoreCase(name).stream()
                .sorted((o1, o2) -> {
                    if (order == Order.ASC)
                        return o1.getName().compareTo(o2.getName());
                    return o2.getName().compareTo(o1.getName());
                }).toList());
        return parse.format(page, size);
    }

    @Transactional
    public List<Faculty> createOrUpdate(List<Faculty> faculty) {
        return facultyRepository.saveAll(faculty);
    }
}
