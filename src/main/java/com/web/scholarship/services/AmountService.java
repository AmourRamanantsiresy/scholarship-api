package com.web.scholarship.services;

import com.web.scholarship.models.Amount;
import com.web.scholarship.repository.AmountRepository;
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
public class AmountService {
    private AmountRepository amountRepository;

    public DataFormat<Amount> getAll(int page, int size, Order order) {
        DataParser<Amount> parse;
        parse = new DataParser<>(amountRepository.findAll(Sort.by(Sort.Direction.fromString(order.toString()), "amount")));
        return parse.format(page, size);
    }

    public Optional<Amount> getOne(Long id) {
        return amountRepository.findById(id);
    }

    @Transactional
    public List<Amount> createOrUpdate(List<Amount> amount) {
        return amountRepository.saveAll(amount);
    }
}
