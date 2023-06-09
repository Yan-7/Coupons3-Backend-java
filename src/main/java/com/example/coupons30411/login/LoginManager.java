package com.example.coupons30411.login;


import com.example.coupons30411.repositories.CompanyRepository;
import com.example.coupons30411.repositories.CustomerRepository;
import com.example.coupons30411.security.JwtTokenUtil;
import com.example.coupons30411.services.AdminService;
import com.example.coupons30411.services.ClientService;
import com.example.coupons30411.services.CompanyService;
import com.example.coupons30411.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@Service
public class LoginManager {

    private final AdminService adminService;
    private final CustomerService customerService;
    private final CompanyService companyService;
    private final CompanyRepository companiesRep;
    private final CustomerRepository customerRep;

    @Autowired
    public LoginManager(
            AdminService adminService,
            CustomerService customerService,
            CompanyService companyService,
            CompanyRepository companiesRep,
            CustomerRepository customerRep) {
        this.adminService = adminService;
        this.customerService = customerService;
        this.companyService = companyService;
        this.companiesRep = companiesRep;
        this.customerRep = customerRep;
    }

    public String login(String email, String password, ClientType clientType) {
        switch (clientType) {
            case ADMIN: {
                if ("admin".equals(email) && "admin".equals(password)) {
                    System.out.println("welcome admin - logging manager");
                    return JwtTokenUtil.generateToken(email);
                }
                break;
            }
            case COMPANY: {
                if (companiesRep.findByEmailAndPassword(email, password).isPresent()) {
                    return JwtTokenUtil.generateToken(email);
                }
                break;
            }
            case CUSTOMER: {
                if (customerRep.findByEmailAndPassword(email, password).isPresent()) {
                    System.out.println("token from logging manager");
                    return JwtTokenUtil.generateToken(email);
                }
                break;
            }
        }
        System.out.println("Could not connect");
        return null;
    }


}




