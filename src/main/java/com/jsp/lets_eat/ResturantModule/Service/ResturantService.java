package com.jsp.lets_eat.ResturantModule.Service;

import com.jsp.lets_eat.ResturantModule.Dto.ResturantRequest;
import com.jsp.lets_eat.ResturantModule.Dto.ResturantResponse;
import java.util.List;


public interface ResturantService {
    ResturantResponse createRestaurant(ResturantRequest resturantRequest);
    ResturantResponse getResturantById(Long id);
    /*
  void deleteResturant(Long id);
  ResturantResponse updateResturant(Long id, ResturantRequest resturantRequest);
     */
    List<ResturantResponse> getResturantByName(String name);
     List<ResturantResponse> getAll();
     List<ResturantResponse> getResturantByLoc(String city);
     ResturantResponse updateManager(Long resturantId,Integer userId );
}
