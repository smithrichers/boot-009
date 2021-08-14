package com.example.boot009.repository;


import com.example.boot009.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture,Integer> {
}
