package com.sda.java9.finalproject.dao;

import com.sda.java9.finalproject.dto.PostDTO;
import com.sda.java9.finalproject.generics.AirlinesMapper;
import com.sda.java9.finalproject.generics.GenericDAO;
import com.sda.java9.finalproject.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component @RequiredArgsConstructor
public class PostDAO implements GenericDAO<PostDTO> {

    private final PostRepository postRepository;

    @Override
    public List<PostDTO> findAll() {
        return postRepository.findAll().stream().map(AirlinesMapper::mapPostToDTO).collect(Collectors.toList());
    }

    @Override
    public PostDTO findById(Long id) {
        return postRepository.findById(id).map(AirlinesMapper::mapPostToDTO).orElse(null);
    }

    @Override
    public void save(PostDTO postDTO) {
        postRepository.save(AirlinesMapper.mapPostDTOToEntity(postDTO));
    }

    @Override
    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }
}
