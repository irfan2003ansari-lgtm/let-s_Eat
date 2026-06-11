package com.jsp.lets_eat.ResturantModule.Dao;

import com.jsp.lets_eat.ResturantModule.Model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<FoodItem, Long> {

    List<FoodItem> findByFoodNameIgnoreCase(String foodName);
}
