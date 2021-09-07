package io.github.muhittinpalamutcu.schoolmanagementsystem.service;

import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.ErrorDetail;
import io.github.muhittinpalamutcu.schoolmanagementsystem.repository.ErrorDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ErrorDetailService {

    @Autowired
    private ErrorDetailRepository errorDetailRepository;

    public List<ErrorDetail> findAll() {
        return (List<ErrorDetail>) errorDetailRepository.findAll();
    }

    @Transactional
    public ErrorDetail save(ErrorDetail errorDetail) {
        return errorDetailRepository.save(errorDetail);
    }

    public List<ErrorDetail> findByException(String exception) {
        return errorDetailRepository.findByException(exception);
    }

    public List<ErrorDetail> findByLocalDate(LocalDate localDate) {
        return errorDetailRepository.findByLocalDate(localDate);
    }
}
