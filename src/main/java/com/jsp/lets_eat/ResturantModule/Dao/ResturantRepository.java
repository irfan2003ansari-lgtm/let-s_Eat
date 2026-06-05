package com.jsp.lets_eat.ResturantModule.Dao;

import com.jsp.lets_eat.ResturantModule.Model.Resturant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ResturantRepository extends JpaRepository<Resturant, Long> {

    List<Resturant> findAllByRessturantNameIgnoreCase(String ressturantName);
    List<Resturant> findAllByCityIgnoreCase(String city);

}
