package com.sda.java9.finalproject.dao;

import com.sda.java9.finalproject.dto.ReviewDTO;
import com.sda.java9.finalproject.generics.AirlinesMapper;
import com.sda.java9.finalproject.generics.GenericDAO;
import com.sda.java9.finalproject.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component @RequiredArgsConstructor
public class ReviewDAO implements GenericDAO<ReviewDTO> {

    private final ReviewRepository reviewRepository;

    @Override
    public List<ReviewDTO> findAll() {
        return reviewRepository.findAll().stream().map(AirlinesMapper::mapReviewToDTO).collect(Collectors.toList());
    }

    @Override
    public ReviewDTO findById(Long id) {
        return reviewRepository.findById(id).map(AirlinesMapper::mapReviewToDTO).orElse(null);
    }

    @Override
    public void save(ReviewDTO reviewDTO) {
        reviewRepository.save(AirlinesMapper.mapReviewDTOToEntity(reviewDTO));
    }

    @Override
    public void deleteById(Long id) {
        reviewRepository.deleteById(id);
    }
}
