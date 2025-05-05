package com.practice.SafeVision.repository;

import com.practice.SafeVision.Entity.Camera;
import com.practice.SafeVision.Entity.Organization;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class OrganizationRepository {
    private final JdbcTemplate jdbcTemplate;
    public Organization findById(Long id){
        try {
            String sql = "SELECT * FROM organization WHERE id = ?";
            return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Organization.class), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}

