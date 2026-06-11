package com.jsp.lets_eat.CartModule.Service;

import com.jsp.lets_eat.CartModule.Dao.CartRepository;
import com.jsp.lets_eat.CartModule.Dto.CartItemRequest;
import com.jsp.lets_eat.CartModule.Dto.CartResponse;
import com.jsp.lets_eat.CartModule.Model.Cart;
import com.jsp.lets_eat.CartModule.Model.CartItem;
import com.jsp.lets_eat.CommonModule.Exception.CartException;
import com.jsp.lets_eat.CommonModule.Exception.FoodException;
import com.jsp.lets_eat.CommonModule.Exception.UserException;
import com.jsp.lets_eat.ResturantModule.Dao.FoodRepository;
import com.jsp.lets_eat.ResturantModule.Model.FoodItem;
import com.jsp.lets_eat.UserModule.Entity.User;
import com.jsp.lets_eat.UserModule.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImp implements CartService{
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final FoodRepository foodRepository;

    @Override
    public CartResponse createCart(CartItemRequest cartItemRequest) {
        User user = userRepository.findById(cartItemRequest.getUserId())
                .orElseThrow(() -> new UserException("user not found with id " + cartItemRequest.getUserId()));

        FoodItem foodItem=foodRepository.findById(cartItemRequest.getFoodItemId()).orElseThrow(()-> new FoodException("Food not found with id "+cartItemRequest.getFoodItemId()));

        Optional<Cart> optionalCart = cartRepository.findByUser_Id(cartItemRequest.getUserId());
        Cart cart;
        if (optionalCart.isPresent()) {
            cart = optionalCart.get();
        } else {

            if(user.getActive()){
                cart = new Cart();
                cart.setUser(user);
                cart.setTotalPrice(0.0);
            }else {
                throw new UserException("User with id " + cartItemRequest.getUserId() + " is inactive");
            }

        }

        CartItem cartItem = new CartItem(cartItemRequest, foodItem);
        cartItem.setCart(cart);
        cart.getCartItemList().add(cartItem);
        cart.setTotalPrice(cart.getTotalPrice() + cartItem.getTotalPrice());
        cartRepository.save(cart);

        return new CartResponse(cart);
    }

    @Override
    public CartResponse findByUserId(Integer userId) {
        Cart cart = cartRepository.findByUser_Id(userId)
                .orElseThrow(() -> new CartException("Cart not found for user with id " + userId));
        return new CartResponse(cart);
    }


    public CartResponse increaseQuantity(Long foodItemId,Integer userId){

        User user=userRepository.findById(userId).orElseThrow(()-> new UserException("User not found with id "+userId));
        Cart cart=cartRepository.findByUser_Id(userId).orElseThrow(()-> new CartException("Cart not found for user with id "+userId));
        List<CartItem> cartItem=cart.getCartItemList();

        if(cartItem.isEmpty()){
            throw new CartException("Cart is empty for user with id " + userId);
        }
        for(CartItem item:cartItem){
            if(item.getFoodItemId().equals(foodItemId)){
                item.setQuantity(item.getQuantity()+1);
                item.setTotalPrice(item.getPrice()*item.getQuantity());
                cart.setTotalPrice(cart.getTotalPrice()+item.getPrice());
                cartRepository.save(cart);

                return new CartResponse(cart);
            }
        }
        throw new CartException("Food item not found in cart for user with id " + userId);
    }

    @Override
    public CartResponse decreaseQuantity(Long foodItemId, Integer userId) {

        User user=userRepository.findById(userId).orElseThrow(()-> new UserException("User not found with id "+userId));
        Cart cart=cartRepository.findByUser_Id(userId).orElseThrow(()-> new CartException("Cart not found for user with id "+userId));
        List<CartItem> cartItem=cart.getCartItemList();

      if(cartItem.isEmpty()){
          throw new CartException("Cart is empty for user with id " + userId);
      }

        for(CartItem item:cartItem){
            if(item.getFoodItemId().equals(foodItemId)){
                if(item.getQuantity()>1){
                    item.setQuantity(item.getQuantity()-1);
                    item.setTotalPrice(item.getPrice()*item.getQuantity());
                    cart.setTotalPrice(cart.getTotalPrice()-item.getPrice());
                }else {
                    cart.setTotalPrice(cart.getTotalPrice()-item.getPrice());
                    cartItem.remove(item);
                }
                cartRepository.save(cart);
                return new CartResponse(cart);
            }
        }
        throw new CartException("Food item not found in cart for user with id " + userId);
    }
}


