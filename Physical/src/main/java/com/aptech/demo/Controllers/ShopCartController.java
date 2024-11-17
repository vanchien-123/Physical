package com.aptech.demo.Controllers;


import com.aptech.demo.Models.Cart;
import com.aptech.demo.Models.Product;
import com.aptech.demo.Repositories.ProductRepository;
import com.aptech.demo.Services.CartManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ShopCartController {


    @Autowired
    ProductRepository proRepo;

    @Autowired
    CartManager cartManager;

    @RequestMapping(value = "/add/{id}", method = RequestMethod.POST)
    public String add(HttpSession session, @PathVariable("id") Long id, @RequestParam(value = "qty", required = false, defaultValue = "1") int qty, HttpServletRequest request){
        Object check_cart = request.getSession().getAttribute("user");
        if (check_cart != null) {
            Product product = proRepo.findById(id);
            Cart cart = cartManager.getCart(session);
            cart.addItem(product, qty);
            cartManager.setCart(session, cart);
            return "redirect:/shop";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping("/remove/{id}")
    public String remove(HttpSession session, @PathVariable("id") Long id){
        Product product = proRepo.findById(id);
        Cart cart = cartManager.getCart(session);
        cart.removeItem(product);
        return "redirect:/cart";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(HttpSession session, @PathVariable(value = "id", required = false) Long id, @RequestParam(value = "qty", required = false) Integer qty){
        Product product = proRepo.findById(id);
        Cart cart = cartManager.getCart(session);
        System.out.print(qty);
        if (qty == null){
            qty = 0;
            
        }
        System.out.print(product);
        cart.updateItem(product, qty);
        return "redirect:/cart";
    }

    
}
