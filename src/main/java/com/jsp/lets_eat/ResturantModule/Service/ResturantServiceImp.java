package com.jsp.lets_eat.ResturantModule.Service;

import com.jsp.lets_eat.CommonModule.Exception.ResturantException;
import com.jsp.lets_eat.ResturantModule.Dao.ResturantRepository;
import com.jsp.lets_eat.ResturantModule.Dto.FoodResponse;
import com.jsp.lets_eat.ResturantModule.Dto.ResturantRequest;
import com.jsp.lets_eat.ResturantModule.Dto.ResturantResponse;
import com.jsp.lets_eat.ResturantModule.Model.FoodItem;
import com.jsp.lets_eat.ResturantModule.Model.Resturant;
import com.jsp.lets_eat.UserModule.Entity.Role;
import com.jsp.lets_eat.UserModule.Entity.User;
import com.jsp.lets_eat.UserModule.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResturantServiceImp implements ResturantService {
    private final ResturantRepository resturantRepository;
    private final UserRepository userRepository;
    
    @Override
    public ResturantResponse createRestaurant(ResturantRequest resturantRequest) {
        Resturant resturant=new Resturant(resturantRequest);
        resturant.setActive(true);
       Integer manager=resturantRequest.getUser_id();
        User user=userRepository.findById(manager).orElseThrow(() -> new ResturantException("User not found with id: " + manager));
        resturant.setManager(user);
        user.setResturant(resturant);
        user.setRole(Role.MANAGER);
        resturantRepository.save(resturant);
        userRepository.save(user);

        LocalTime time = LocalTime.now();
        boolean Open = !time.isBefore(resturant.getOpeningTime()) &&
                !time.isAfter(resturant.getClosingTime());
        ResturantResponse response=new ResturantResponse(resturant);
        response.setIsOpen(Open);

        return response;
    }

    @Override
    public ResturantResponse getResturantById(Long id) {

        Resturant resturant=resturantRepository.findById(id).orElseThrow(() -> new ResturantException("Resturant not found with id: " + id));
        if(resturant.getActive()){

            LocalTime time = LocalTime.now();
            boolean Open = !time.isBefore(resturant.getOpeningTime()) &&
                    !time.isAfter(resturant.getClosingTime());
            ResturantResponse response=new ResturantResponse(resturant);
            response.setIsOpen(Open);
            return response;
        }else {
            throw new ResturantException("Resturant with id: " + id + " is inactive");
        }
    }
/*
    @Override
    public void deleteResturant(Long id) {

        Resturant resturant=resturantRepository.findById(id).orElseThrow(() -> new ResturantNotFoundException("Resturant not found with id: " + id));
      if(resturant.getActive()){
          resturant.setActive(false);
          resturantRepository.save(resturant);
      }else {
          throw new ResturantNotFoundException("Resturant with id: " + id + " is already inactive");
      }
    }

 */
/*
    @Override
    public ResturantResponse updateResturant(Long id, ResturantRequest request) {
        Resturant resturant=resturantRepository.findById(id).orElseThrow(() -> new ResturantNotFoundException("Resturant not found with id: " + id));
    if(resturant.getActive()){
        if (request.getRessturantName() != null) {
            resturant.setRessturantName(request.getRessturantName());
        }

        if (request.getDescription() != null) {
            resturant.setDescription(request.getDescription());
        }

        if (request.getAddress() != null) {
            resturant.setAddress(request.getAddress());
        }

        if (request.getCity() != null) {
            resturant.setCity(request.getCity());
        }

        if (request.getState() != null) {
            resturant.setState(request.getState());
        }

        if (request.getCountry() != null) {
            resturant.setCountry(request.getCountry());
        }

        if (request.getOpeningTime() != null) {
            resturant.setOpeningTime(request.getOpeningTime());
        }

        if (request.getClosingTime() != null) {
            resturant.setClosingTime(request.getClosingTime());
        }
        return new ResturantResponse(resturantRepository.save(resturant));
    }else {
        throw new ResturantNotFoundException("Resturant with id: " + id + " is inactive");
    }
    }
    */

    @Override
    public List<ResturantResponse> getResturantByName(String name) {
        List<Resturant> resturants=resturantRepository.findAllByRessturantNameIgnoreCase(name);
        List<ResturantResponse> responses=new ArrayList<>();
       if(!resturants.isEmpty()){
           for (Resturant resturant:resturants){
               if (resturant.getActive()){
                   LocalTime time = LocalTime.now();
                   Boolean Open = !time.isBefore(resturant.getOpeningTime()) &&
                           !time.isAfter(resturant.getClosingTime());
                   ResturantResponse response=new ResturantResponse(resturant);
                   response.setIsOpen(Open);
                   responses.add(response);
               }
           }
           return responses;
       }else {
           throw new ResturantException("Resturant not found with name: " + name);
       }
    }


    public List<ResturantResponse> getAll() {
         List<Resturant> resturant= resturantRepository.findAll();

         List<ResturantResponse> responses=new ArrayList<>();

         for(Resturant r:resturant){
             responses.add(new ResturantResponse(r));
         }
         return responses;
    }

    @Override
    public List<ResturantResponse> getResturantByLoc(String city) {
        List<Resturant> resturants=resturantRepository.findAllByCityIgnoreCase(city);
        List<ResturantResponse> responses=new ArrayList<>();
        if(!resturants.isEmpty()){
            for (Resturant resturant:resturants){
                if (resturant.getActive()){
                    LocalTime time = LocalTime.now();
                    Boolean Open = !time.isBefore(resturant.getOpeningTime()) &&
                            !time.isAfter(resturant.getClosingTime());
                    ResturantResponse response=new ResturantResponse(resturant);
                    response.setIsOpen(Open);
                    responses.add(response);
                }
            }
            return responses;
        }else {
            throw new ResturantException("Resturant not found in city: " + city);
        }
    }

    @Override
    public ResturantResponse updateManager(Long resturantId,Integer userId) {
        Resturant resturant=resturantRepository.findById(resturantId).orElseThrow(()->new ResturantException("Resturant not found with id: " + resturantId));
        if(resturant.getActive()){
            User user=userRepository.findById(userId).orElseThrow(() -> new ResturantException("User not found with id: " + userId));
            List<Resturant> resturants=resturantRepository.findAll();
           for (Resturant r:resturants){
               if(r.getManager()!=null && r.getManager().getId().equals(userId)){
                   throw new ResturantException("User with id: " + userId + " is already a manager of resturant with id: " + r.getId());
               }
           }
            resturant.setManager(user);
            user.setResturant(resturant);
            user.setRole(Role.MANAGER);
            resturantRepository.save(resturant);
            userRepository.save(user);
            return new ResturantResponse(resturant);
        }
        else {
            throw new ResturantException("Resturant with id: " + resturantId + " is inactive");
        }
    }

    @Override
    public List<FoodResponse> getFoodItemsByResturantId(Long resturantId) {
        Resturant resturant=resturantRepository.findById(resturantId).orElseThrow(() -> new ResturantException("Resturant not found with id: " + resturantId));
        if(resturant.getActive()){
            List<FoodItem> foodItems=resturant.getFood();
            List<FoodResponse> responses=new ArrayList<>();
            for(FoodItem food:foodItems){
                responses.add(new FoodResponse(food));
            }
            return responses;
        }else {
            throw new ResturantException("Resturant with id: " + resturantId + " is inactive");
        }
    }

}
