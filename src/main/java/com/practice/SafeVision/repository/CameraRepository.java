package com.practice.SafeVision.repository;

import com.practice.SafeVision.Entity.Camera;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class CameraRepository {
    private final JdbcTemplate jdbcTemplate;
    public Camera findById(Long id){
        try {
            String sql = "SELECT * FROM camera WHERE id = ?";
            return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Camera.class), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
