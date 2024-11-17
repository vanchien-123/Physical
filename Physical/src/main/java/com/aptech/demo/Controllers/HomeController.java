package com.aptech.demo.Controllers;

import com.aptech.demo.Configs.Config;
import com.aptech.demo.DTOs.OrderDTO;
import com.aptech.demo.DTOs.OrderDetailDTO;
import com.aptech.demo.Models.*;
import com.aptech.demo.Repositories.*;
import com.aptech.demo.Repositories.repositoryDTO.OrderDetailRepoDTO;
import com.aptech.demo.Repositories.repositoryDTO.OrderRepoDTO;
import com.aptech.demo.Services.CartManager;
import com.aptech.demo.Services.MailSenderService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.sql.DataSource;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private MailSenderService mailSenderService;
	
    @Autowired
    ProductRepository proRepo;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CartManager cartManager;

    @Autowired
    Type_product_Repository typeProductRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    @Autowired
    OrderRepoDTO orderRepoDTO;

    @Autowired
    OrderDetailRepoDTO orderDetailRepoDTO;
    
    @RequestMapping("/home")
    public String Home(Model model) {
      
        return "default/index";
    }

    @RequestMapping(value = "/shop", method = RequestMethod.GET)
    public String Shop(Model model, @RequestParam(name = "keyword", required = false) String keyword) {
        List<Product> listP ;
        
        if (keyword != null){
        	listP = proRepo.findBySearch(keyword);
        	model.addAttribute("products", listP);     	
        } else {
        	listP = proRepo.findAllForUser();
        	model.addAttribute("products", listP);     	
        }
        
        return "default/shop";
    }

    @RequestMapping("/product/viewdetail")
    public String productDetail(@RequestParam("id") Long id,
            Model model) {
        Product product = proRepo.findById(id);
        model.addAttribute("product", product);
        List<TypeProduct> showMenu = typeProductRepository.findAll();
        model.addAttribute("menus", showMenu);
        return "default/productDetail";
    }

    @RequestMapping("/cart")
    public String showCart(HttpSession session,
            Model model) {
        List<TypeProduct> showMenu = typeProductRepository.findAll();
        model.addAttribute("menus", showMenu);
        List<CartItem> items = cartManager.getCart(session).getItems();
        if (items == null || items.isEmpty()) {
            model.addAttribute("msg", "No products in the cart");
        } else {
        	System.out.print(items);
            model.addAttribute("items", items);
        }
        return "default/shopCart";
    }

    @RequestMapping(value = "/check-out", method = RequestMethod.GET)
    public String checkOut(Model model) {
        List<TypeProduct> showMenu = typeProductRepository.findAll();
        model.addAttribute("menus", showMenu);
        return "default/shopcartCheckout";
    }

   

    @RequestMapping("/login")
    public String loginForm(Model model) {
        List<TypeProduct> showMenu = typeProductRepository.findAll();
        model.addAttribute("menus", showMenu);
        return "default/loginForm";
    }

    @RequestMapping(value = "/checkin", method = RequestMethod.POST)
    public String checkin(@RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpServletRequest request) {
     
        Customer customer = customerRepository.findByUsername(username);
        if (customer == null) {
            return "redirect:/login";
        } else {
            String pass = customer.getPassword();
            if (checkPassword(password, pass)) {
                request.getSession().setAttribute("user", customer);
                request.getSession().removeAttribute("myacc");
                return "redirect:/home";
            }
        }
        return "redirect:/login";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
		/* request.getSession().removeAttribute("gioHang"); */
        return "redirect:/home";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "default/register";
    }

    @RequestMapping(value = "/registered", method = RequestMethod.POST)
    public String registered(@RequestParam("Sex") String gender,
            @RequestParam("Password") String password,
            Customer customer,
            @RequestParam("Username") String username,
            @RequestParam("Email") String email,
            @RequestParam("Phone") String phone,
            RedirectAttributes redirectAttributes) {
        String temp = encryptPassword(password);
        customer.setPassword(temp);
        String cus_gender = gender;
        customer.setSex(cus_gender);
        String findByUsername = "select count(*) from Customer where Username = ?";
        int countUsername = jdbcTemplate.queryForObject(findByUsername, Integer.class, username);
        if (countUsername == 1) {
            redirectAttributes.addFlashAttribute("error", "Tên tài khoản đã tồn tại");
            return "redirect:/register";
        } else {
            customer.setEmail(username);
            String findByEmail = "select count(*) from Customer where Email = ?";
            int countEmail = jdbcTemplate.queryForObject(findByEmail, Integer.class, email);
            if (countEmail == 1) {
                redirectAttributes.addFlashAttribute("error", "Email đã tồn tại");
                return "redirect:/register";
            } else {
                customer.setEmail(email);
                String findByPhone = "select count(*) from Customer where Phone = ?";
                int countPhone = jdbcTemplate.queryForObject(findByPhone, Integer.class, phone);
                if (countPhone >= 1) {
                    redirectAttributes.addFlashAttribute("error", "Số điện thoại đã tồn tại");
                    return "redirect:/register";
                } else {
                    customer.setPhone(phone);
                }
            }
        }
        customerRepository.insertCustomer(customer);
        return "redirect:/login";
    }

    @RequestMapping(value = "/save-order", method = RequestMethod.POST)
    public String saveOrder(@RequestParam("order_receiver") String receiver,
            @RequestParam("order_phone_receiver") String phone_receiver,
            @RequestParam("order_delivery_address") String address,
            @RequestParam("note") String order_note,
            @RequestParam("temp") String temp, // cái này để lấy địa chỉ gán sẵn của user
            @RequestParam("optionsRadios") String options,
            HttpSession session,
            HttpServletRequest request) throws UnsupportedEncodingException {
        
        Customer customer = (Customer) session.getAttribute("user");
        Long cus_id = customer.getID();
        Cart cart = (Cart) session.getAttribute("gioHang");
        double order_total = cart.getTotal();

        String adr = "";
        if (address.isEmpty()) {
            adr = temp;
        } else {
            adr = address;
        }
        Order odrer = new Order();
        String insertOrder = String.format("exec sp_insert_order ?, ?, ?, ?, ?, ?", cus_id, order_total, receiver, adr,
                phone_receiver, order_note);
        jdbcTemplate = new JdbcTemplate(dataSource);
        System.out.print("trước: " + odrer.getStatus());
        Long order_id = jdbcTemplate.queryForObject(insertOrder,
                new Object[] { cus_id, order_total, receiver, adr, phone_receiver, order_note }, Long.class);
        
        System.out.println(order_id);
        String sql = "insert into OrderDetail (OrderID, ProID, ImportPrice, SellPrice, Quantity, Total) values (?, ?, ?, ?, ?, ?)";
        cart = cartManager.getCart(session);
        List<CartItem> items = cart.getItems();
        for (CartItem item : items) {
            Product product = item.getProduct();
            Long pro_id = product.getID();
            float import_price = product.getImportPrice();
            float pro_price = product.getSellPrice();
            int quantity = item.getQuantity();
            double subTotal = item.getSubTotal();

            jdbcTemplate.update(sql, order_id, pro_id, import_price, pro_price, quantity, subTotal);
        }

        
        if (options.equals("COD")) {
            String updatePayment = "update orders set Payment = ? where ID = ?";
            jdbcTemplate.update(updatePayment, options, order_id);
            System.out.print("sau: " + odrer.getStatus());
            session.removeAttribute("gioHang");
            return "redirect:/resultCOD";
        } else {
            String vnp_Version = "2.1.0";
            String vnp_Command = "pay";
            String orderType = "other";

            Long amount = (long) (order_total * 100);
            String bankCode = "NCB";

            String vnp_TxnRef = Config.getRandomNumber(8);
            String vnp_IpAddr = Config.getIpAddress(request);

            String vnp_TmnCode = Config.vnp_TmnCode;

            Map<String, String> vnp_Params = new HashMap<>();
            vnp_Params.put("vnp_Version", vnp_Version);
            vnp_Params.put("vnp_Command", vnp_Command);
            vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
            vnp_Params.put("vnp_Amount", String.valueOf(amount));
            vnp_Params.put("vnp_CurrCode", "VND");

            // cho nay co 3 cach thanh toan VNBANK,VNPAYQR,INTCARD nay h thieu cai nay
            vnp_Params.put("vnp_BankCode", "VNBANK");

            vnp_Params.put("vnp_Locale", "vn");
            vnp_Params.put("vnp_TxnRef", String.valueOf(order_id));
            vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + order_id);

            // cai nay optional
            vnp_Params.put("vnp_OrderType", orderType);

            vnp_Params.put("vnp_ReturnUrl", "http://localhost:8080/result");
            vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

            Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String vnp_CreateDate = formatter.format(cld.getTime());
            vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

            cld.add(Calendar.MINUTE, 15);
            String vnp_ExpireDate = formatter.format(cld.getTime());
            vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

            List fieldNames = new ArrayList(vnp_Params.keySet());
            Collections.sort(fieldNames);
            StringBuilder hashData = new StringBuilder();
            StringBuilder query = new StringBuilder();
            Iterator itr = fieldNames.iterator();
            while (itr.hasNext()) {
                String fieldName = (String) itr.next();
                String fieldValue = (String) vnp_Params.get(fieldName);
                if ((fieldValue != null) && (fieldValue.length() > 0)) {
                    // Build hash data
                    hashData.append(fieldName);
                    hashData.append('=');
                    hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    // Build query
                    query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                    query.append('=');
                    query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    if (itr.hasNext()) {
                        query.append('&');
                        hashData.append('&');
                    }
                }
            }
            String updatePayment = "update orders set Payment = ? where ID = ?";
            jdbcTemplate.update(updatePayment, options, order_id);
            String queryUrl = query.toString();
            String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, hashData.toString());
            queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
            String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;
            session.removeAttribute("gioHang");
            return "redirect:" + paymentUrl;
        }
    }

    @RequestMapping("/result")
    public String result(@RequestParam("vnp_ResponseCode") int resultCode,
            @RequestParam("vnp_TxnRef") Long id,
            @RequestParam("vnp_BankCode") String name,
            @RequestParam("vnp_PayDate") String date,
            @RequestParam("vnp_TransactionNo") String no,
            Model model) throws ParseException {
        List<TypeProduct> showMenu = typeProductRepository.findAll();
        model.addAttribute("menus", showMenu);
        if (resultCode == 00) {

            Long order_id = id;
            String dateTimeString = date;
            String pattern = "yyyyMMddHHmmss";
            SimpleDateFormat inputDateFormat = new SimpleDateFormat(pattern);
            java.util.Date date2 = inputDateFormat.parse(dateTimeString);

            // Chuyển đổi sang định dạng ngày giờ của SQL Server
            String sqlServerDateTimeFormat = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat outputDateFormat = new SimpleDateFormat(sqlServerDateTimeFormat);
            String sqlServerDateTimeString = outputDateFormat.format(date2);
            String transaction_no = no;
            String sql = "update orders set TransactionNo = ?, TransactionDate = ? where ID = ?";
            jdbcTemplate.update(sql, transaction_no, sqlServerDateTimeString, order_id);
            
            model.addAttribute("msg", "Payment successful");
            model.addAttribute("date", sqlServerDateTimeString);
            model.addAttribute("no", transaction_no);
            model.addAttribute("bank_name", name);
        } else {
            model.addAttribute("msg", "Payment failed");
        }
        return "/default/thankyou";
    }

    @RequestMapping("/resultCOD")
    public String COD(Model model) {
        List<TypeProduct> showMenu = typeProductRepository.findAll();
        model.addAttribute("menus", showMenu);
        model.addAttribute("msg", "Order successful. We will contact you as soon as possible.");
        return "default/thankyou";
    }

    @RequestMapping("/lich-su-dat-hang")
    public String lichSuDonHang(Model model,
            HttpServletRequest request) {
       
        Customer customer = (Customer) request.getSession().getAttribute("user");
        Long id = customer.getID();
        List<OrderDTO> orderHistory = orderRepoDTO.showOrderByCusId(id);
        model.addAttribute("orders", orderHistory);

        return "/default/orderHistory";
    }

    @RequestMapping("/thong-tin-user")
    public String thongTin(Model model,
            HttpServletRequest request) {
        List<TypeProduct> showMenu = typeProductRepository.findAll();
        model.addAttribute("menus", showMenu);
        return "/default/information";
    }

    @RequestMapping("/chi-tiet-don-hang/{id}")
    public String chiTiet(Model model,
            HttpServletRequest request,
            @PathVariable("id") Long id) {
        List<TypeProduct> showMenu = typeProductRepository.findAll();
        model.addAttribute("menus", showMenu);
        List<OrderDetailDTO> list = orderDetailRepoDTO.showOrderDetail(id);
        model.addAttribute("details", list);
        return "default/orderDetailUser";
    }

    @RequestMapping("/thay-doi-mat-khau")
    public String pass(HttpSession session,
            Model model) {
        List<TypeProduct> showMenu = typeProductRepository.findAll();
        model.addAttribute("menus", showMenu);
        return "default/changePass";
    }

    @RequestMapping(value = "/change-pass", method = RequestMethod.POST)
    public String changePassword(@RequestParam(value = "old-password", required = false) String old,
            @RequestParam(value = "new-password", required = false) String neww,
            @RequestParam(value = "confirm-password", required = false) String confirm,
            HttpSession session,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes) {

        Object object = session.getAttribute("user");
        Customer customer = (Customer) object;
        String oldPass = customer.getPassword();
        Long id = customer.getID();
        if (checkPassword(old, oldPass)) {
            if (!neww.equals(confirm)) {
                redirectAttributes.addFlashAttribute("msg", "Mật khẩu không trùng khớp!");
                return "redirect:/thay-doi-mat-khau";
            } else {
                String newPass = encryptPassword(confirm);
                customerRepository.updatePassword(newPass, id);
                session.removeAttribute("user");
                return "redirect:/login";
            }
        } else {
            redirectAttributes.addFlashAttribute("msg", "Sai mật khẩu hiện tại!");
            return "redirect:/thay-doi-mat-khau";
        }
        // return "/default/changePass";
    }
    
    @RequestMapping(value = "/updateStatus/{id}", method = RequestMethod.POST)
    public String updateStatus( @PathVariable(value = "id", required = false) Long id){
        orderRepoDTO.updateStatusHome(id, "CANCELEDREQUEST");
        return "redirect:/lich-su-dat-hang";
    }

    public static String encryptPassword(String password) {
        String salt = BCrypt.gensalt(12);
        return BCrypt.hashpw(password, salt);
    }

    public boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
    
    @RequestMapping("/services")
    public String Service() {
      
        return "default/service";
    }
    
    @RequestMapping("/blog")
    public String Blog() {
      
        return "default/blog";
    }
    
    @RequestMapping("/contact")
    public String Contact(Model model) {
      
        return "default/contact";
    }
    
    @RequestMapping(value = "/contact/sendtome", method = RequestMethod.POST)
	public String SendMailToMe(String message,String email, String first_name, String last_name) {
		
		String name =first_name + " " +  last_name;
		
	mailSenderService.sendEmailContact(email, name, message);
		/* return "HTML email sent successfully!"; */
				
		return "redirect:/contact";
	}
    
}
