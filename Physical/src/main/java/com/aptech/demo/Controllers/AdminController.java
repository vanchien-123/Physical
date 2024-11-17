package com.aptech.demo.Controllers;


import com.aptech.demo.DTOs.LoyalCustomer;
import com.aptech.demo.DTOs.OrderDTO;
import com.aptech.demo.DTOs.OrderDetailDTO;
import com.aptech.demo.DTOs.TopSellingProduct;
import com.aptech.demo.Enums.OrderStatus;
import com.aptech.demo.Models.*;
import com.aptech.demo.Repositories.*;
import com.aptech.demo.Repositories.repositoryDTO.LoyalCustomerRepo;
import com.aptech.demo.Repositories.repositoryDTO.OrderDetailRepoDTO;
import com.aptech.demo.Repositories.repositoryDTO.OrderRepoDTO;
import com.aptech.demo.Repositories.repositoryDTO.TopSellingRepo;
import com.aptech.demo.Services.CartManager;
import com.aptech.demo.Services.OrderService;

import org.springframework.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.sql.DataSource;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
    private OrderService orderService;
	
    @Autowired
    LoyalCustomerRepo loyalCustomerRepo;

    @Autowired
    OrderRepoDTO orderRepoDTO;

    @Autowired
    OrderDetailRepoDTO orderDetailRepoDTO;

    @Autowired
    private CustomerRepository cusRepo;

    @Autowired
    private ProductRepository proRep;

    @Autowired
    Type_product_Repository typeRepo;
    
    @Autowired
   OrderRepository orderRepo;

    @Autowired
    CartManager cartManager;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    TopSellingRepo topSellingRepo;

    private final Path root = Paths.get("src", "main", "resources", "static", "upload");

	public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";
    

    @RequestMapping("/")
    public String homeAdmin(Model model) {
    	List<OrderDTO> orders = orderRepoDTO.showOrder();
    	 //List<Order> orders = orderRepo.findAll();
        model.addAttribute("orders", orders);
        model.addAttribute("orderStatuses", Arrays.asList("TOSHIP", "INTRANSIT", "DELIVERED", "COMPLETED", "CANCELED"));
        return "/admin/home";
    }

    @RequestMapping(value = "/updateStatus/{id}", method = RequestMethod.POST)
    public String updateStatus(@RequestParam(value = "update", required = false) OrderStatus status, @PathVariable(value = "id", required = false) Long id,  Model model){
//        orderRepoDTO.updateStatus(id, status);
        boolean updated = orderService.updateOrderStatus(id, status.name());
        
        if (updated) {
            model.addAttribute("message", "Order status updated successfully.");
        } else {
            model.addAttribute("error", "Order status updated not successfully.");
        }
        
        return "redirect:/admin/";
    }

    @RequestMapping("/order-detail/{id}")
    public String showOrderById(@PathVariable("id") Long id, Model model){
        List<OrderDetailDTO> orderDetailDTO = orderDetailRepoDTO.showOrderDetail(id);
        model.addAttribute("details", orderDetailDTO);
        return "/admin/showDetail";
    }



    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public String showCustomer(Model model, @RequestParam(name = "keyword", required = false) String keyword) {

        List<Customer> listC;

        if (keyword != null){
            listC = cusRepo.findBySearch(keyword);

        } else {
            listC = cusRepo.findAll();

        }
        model.addAttribute("customers", listC);

        return "/admin/showCustomer";
    }

    @RequestMapping(value = "/insertPro", method = RequestMethod.POST)
    public String insertPro(Product product, @RequestParam("pro_name") String pro_name,
                            @RequestParam("import_price") float import_price,
                            @RequestParam("pro_price") float pro_price,
                            @RequestParam("pro_spec") String pro_spec,
                            @RequestParam("type_id") Long type_id,
                            MyUploadForm myUploadForm,
                            @ModelAttribute("myUploadForm") MyUploadForm myUploadForm1,
                            @RequestParam("fileDatas") MultipartFile file,
                            HttpServletRequest request,
                            RedirectAttributes redirectAttributes) {


        String fileExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")).toLowerCase();
        if (fileExtension.equals(".jpg") || fileExtension.equals(".png")) {

            product.setName(pro_name);
            product.setImage(file.getOriginalFilename());
            product.setImportPrice(import_price);
            product.setSellPrice(pro_price);
            product.setDescription(pro_spec);
            product.setTypeID(type_id);
            proRep.insertProduct(product);


//         cấu hình đường dẫn gốc để lưu ảnh khi upload
            Path staticPath = Paths.get("src", "main", "resources", "static", "img");
            String temp = staticPath.toString();


//         gán đường dẫn để java hiểu
            File uploadRootDir = new File(temp);

            if (!uploadRootDir.exists()) {
                uploadRootDir.mkdirs();
            }

            MultipartFile[] fileDatas = myUploadForm.getFileDatas();

//        List<File> uploadedFiles = new ArrayList<File>();

            for (MultipartFile fileData : fileDatas) {

                String originalFileName = fileData.getOriginalFilename();
                // File imageSrc = new File(uploadRootDir.getAbsolutePath() + originalFileName);
                try {
                    File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + originalFileName);
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                    stream.write(fileData.getBytes());
                    stream.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return "redirect:/admin/products";
        } else {
            redirectAttributes.addFlashAttribute("msg", "Không phải hình ảnh");
            return "redirect:/admin/insert";
        }
    }

    @RequestMapping(value = "product/insert", method = RequestMethod.GET)
    public String insert(Model model) {
        MyUploadForm myUploadForm = new MyUploadForm();
        model.addAttribute("myUpload", myUploadForm);
        
        List<TypeProduct> listTypes = typeRepo.findAll();
		model.addAttribute("listTypes", listTypes);
        
        return "admin/insertProduct";
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String showProducts(Model model){
    	List<TypeProduct> listTypes = typeRepo.findAll();
		model.addAttribute("listTypes", listTypes);
        List<Product> listP = proRep.findAll();
        model.addAttribute("products", listP);
        model.addAttribute("proStatuses", Arrays.asList("InStock", "OutOfStock"));
        return "admin/showProduct";
    }


    @RequestMapping("/editProd/{id}")
    public String showEdit(@PathVariable("id") Long id, Model model){
        Product product = proRep.findById(id);
        model.addAttribute("ID", product.getID());
        model.addAttribute("Name", product.getName());
        model.addAttribute("Image", product.getImage());
        model.addAttribute("ImportPrice", product.getImportPrice());
        model.addAttribute("SellPrice", product.getSellPrice());
        model.addAttribute("Description", product.getDescription());
        model.addAttribute("type_name", product.getTypeName());
        
        List<TypeProduct> listTypes = typeRepo.findAll();
		model.addAttribute("listTypes", listTypes);
        return "admin/editProduct";
    }

    @RequestMapping(value = "/editPro", method = RequestMethod.POST)
    public String saveEditProduct(
    		@RequestParam("ID") Long id,
			@RequestParam("Name") String pro_name,
			@RequestParam("Image") MultipartFile image,
			@RequestParam("ImportPrice") float import_price,		
			@RequestParam("SellPrice") float pro_price,
			@RequestParam("Description") String pro_spec,
			@RequestParam("type_id") Long type_id,
			Model model
    		)  throws IOException{
    	
    	String fileName = StringUtils.cleanPath(image.getOriginalFilename());
    	Product product = new Product();
    	
    	Product oldProduct = proRep.findById(id);
		String oldImage = oldProduct.getImage();
    	
    	product.setID(id);
    	product.setName(pro_name);
		product.setImportPrice(import_price);
		product.setSellPrice(pro_price);
		product.setDescription(pro_spec);
		product.setTypeID(type_id);
		
		if(fileName != null && fileName.length() > 0) {
			DeleteOldImage(oldImage);
			product.setImage(fileName);
		}else {
			product.setImage(oldImage);
		}
		
		Path staticPath = Paths.get("src", "main", "resources", "static", "img");
		String uploadRootPath = staticPath.toString();

		System.out.println("uploadRootPath=" + uploadRootPath);
		File uploadRootDir = new File(uploadRootPath);
		// Create directory if it not exists.
		if (!uploadRootDir.exists()) {
			uploadRootDir.mkdirs();
		}
		List<File> uploadedFiles = new ArrayList<File>();
		List<String> failedFiles = new ArrayList<String>();
		if (fileName != null && fileName.length() > 0) {
			try {
				// Create the file at server
				File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + fileName);

				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(image.getBytes());
				stream.close();
				//
				uploadedFiles.add(serverFile);
				System.out.println("Write file: " + serverFile);
			} catch (Exception e) {
				System.out.println("Error Write file: " + pro_name);
				failedFiles.add(pro_name);
			}
		}
		
        proRep.update(product);
        return "redirect:/admin/products";
    }
    
    public boolean DeleteOldImage(String image) {
	    try {
	    	
	      Path file = root.resolve(image);
	      Files.deleteIfExists(file);
	      
	      System.out.print("Delete is " + Files.deleteIfExists(file));
	      return true;
	      
	    } catch (IOException e) {
	      throw new RuntimeException("Error: " + e.getMessage());
	    }
	  }

    @RequestMapping(value = "/edit-type/{id}", method = RequestMethod.GET)
    public String editType(@PathVariable("id") Long id, Model model){
    	TypeProduct typeProduct = typeRepo.findById(id);
        model.addAttribute("ID", typeProduct.getID());
        model.addAttribute("Name", typeProduct.getName());
        return "/admin/editType";
    }

    @RequestMapping(value = "/editType", method = RequestMethod.POST)
    public String saveType(TypeProduct typeProduct){
        typeRepo.updateType(typeProduct);
        return "redirect:/admin/type";
    }

    @RequestMapping("/list-product/{id}")
    public String showProductByFilter(@PathVariable("id") Long id, Model model, HttpServletRequest request){
        List<Product> listFilter = proRep.findByFilter(id);
        model.addAttribute("lists", listFilter);
        model.addAttribute("proStatuses", Arrays.asList("InStock", "OutOfStock"));
        request.getSession().setAttribute("type_id", id);
        request.getSession().setAttribute("type_name", typeRepo.findById(id).getName());
        return "admin/showProductByType";
    }

    @RequestMapping(value = "/updateProStatus/{id}", method = RequestMethod.POST)
    public String updateProStatus(@RequestParam(value = "update", required = false) String update, @PathVariable(value = "id", required = false) Long id){
        proRep.updateProStatus(update, id);
        return "redirect:/admin/products";
    }


    @RequestMapping(value = "/type", method = RequestMethod.GET)
    public String showType(Model model){
        List<TypeProduct> listType = typeRepo.findAll();
        model.addAttribute("types", listType);
        return "admin/showType";
    }

    @RequestMapping("/addType")
    public String addType(Model model){
    	TypeProduct type = new TypeProduct();
        model.addAttribute("types", type);
        return "/admin/insertType";
    }

    @RequestMapping(value = "/insertType", method = RequestMethod.POST)
    public String insertType(TypeProduct typeProduct){
        typeRepo.insertType(typeProduct);
        return "redirect:/admin/type";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginAD() {
        return "admin/LoginAD";
    }

    @RequestMapping(value = "/chklogin", method = RequestMethod.POST)
    public String chklogin(@RequestParam("usr")String username,@RequestParam("pwd") String password, HttpServletRequest request) {
        Logger log = Logger.getGlobal();
        log.info("Tài khoản: " +username + " <---> " + "Mật khẩu: " + password);
        String query = "select count(*) from Admin where Username = ? and Password = ? ";
        int count = jdbcTemplate.queryForObject(query, Integer.class, username, password);
        if (count == 1){
            request.getSession().setAttribute("myacc", username);
            return "redirect:/admin/";
        } else {
            return "redirect:/admin/login";
        }
    }

    @RequestMapping("/loyal-customer")
    public String loyalCustomer(Model model, @RequestParam(value = "keyword", required = false) String keyword){
        List<LoyalCustomer> loyalCustomers;
        if (keyword != null){
            loyalCustomers = loyalCustomerRepo.showByName(keyword);
        } else {
            loyalCustomers = loyalCustomerRepo.showAll();
        }
        model.addAttribute("list", loyalCustomers);
        return "/admin/loyalCustomer";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("myacc");
        return "redirect:/admin/login";
    }

    @RequestMapping("top-selling")
    public String topSelling(Model model){
        List<TopSellingProduct> topSellingProducts = topSellingRepo.topSellingProducts();
        model.addAttribute("list", topSellingProducts);
        return "/admin/topSellingPro";
    }

}
