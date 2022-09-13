package com.web.scholarship.services;

import com.web.scholarship.models.StudyLevel;
import com.web.scholarship.repository.StudyLevelRepository;
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
public class StudyLevelService {
    private StudyLevelRepository studyLevelRepository;

    public Optional<StudyLevel> getOne(Long id) {
        return studyLevelRepository.findById(id);
    }

    public DataFormat<StudyLevel> searchByStudyLevel(String name, int page, int size, Order order) {
        DataParser<StudyLevel> parse = new DataParser<>(studyLevelRepository.findAllByNameContainsIgnoreCase(name).stream()
                .sorted((o1, o2) -> {
                    if (order == Order.ASC) {
                        return o1.getName().compareTo(o2.getName());
                    }
                    return o2.getName().compareTo(o1.getName());
                }).toList()
        );
        return parse.format(page, size);
    }

    public DataFormat<StudyLevel> getAll(int page, int size, Order order) {
        DataParser<StudyLevel> parse = new DataParser<>(studyLevelRepository.findAll(Sort.by(Sort.Direction.fromString(order.toString()), "name")));
        return parse.format(page, size);
    }

    @Transactional
    public List<StudyLevel> createOrUpdate(List<StudyLevel> studyLevel) {
        return studyLevelRepository.saveAll(studyLevel);
    }
}
