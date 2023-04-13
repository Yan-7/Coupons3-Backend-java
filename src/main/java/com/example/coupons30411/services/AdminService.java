package com.example.coupons30411.services;


import com.example.coupons30411.entities.Company;
import com.example.coupons30411.entities.Customer;
import com.example.coupons30411.exceptions.CouponException;
import com.example.coupons30411.repositories.CompanyRepository;
import com.example.coupons30411.repositories.CouponRepository;
import com.example.coupons30411.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AdminService extends ClientService {

    private final static String emailA = "admin@admin.com";
    private final static String passwordA = "admin";

//    @Autowired
//    public CompanyRepository companyRepository;
//
//    @Autowired
//    public CustomerRepository customerRepository;
//
//    @Autowired
//    public CouponRepository couponRepository;


    @Override
    public boolean login(String email, String password) {
        if (email == emailA && password == passwordA) {
            System.out.println("user admin logged through service- return true");
            return true;
        } else {
            System.out.println("email or password are not correct");
            System.out.println("return false to login method");
            return false;
        }
    }

    //v
    public Company addCompany(Company company) throws CouponException {
        company.setId(0); //?
        if (this.companyRepository.findByEmailAndPassword(company.getEmail(), company.getPassword()).isPresent()) {
            throw new CouponException("company is already in the database");
        } else {
            companyRepository.save(company);
            System.out.println("company " + company.getId() + " saved");
        }
        return company;
    }

    public void updateCompany(Company company) throws CouponException {
        if (companyRepository.findById(company.getId()).isPresent()) {
            companyRepository.save(company);
            System.out.println("company updated");
        } else {
            throw new CouponException("failed to update");
        }
    }

    //v
    public List<Company> getAllCompanies() { //v
        List<Company> companies = new ArrayList<>();
        companies = companyRepository.findAll();
        if (!companies.isEmpty()) {
            System.out.println("all companies: ");
            return companyRepository.findAll();
        } else {
            System.out.println("list of companies is empty");
            return null;
        }
    }

    //v
    public void addCustomer(Customer customer) throws CouponException { //v
        customer.setId(0); //?
        if (customerRepository.existsById(customer.getId())) {
//            throw new CouponException("customer " + customer.getFirstName() + " already exist");
            System.out.println("customer " +customer.getFirstName() +" already exist");
            return;
        }
        customerRepository.save(customer);
        System.out.println("customer" + customer.getFirstName() + " saved");
    }

    //v
    public void updateCustomer(Customer customer) throws CouponException { //v
        if (customerRepository.existsById(customer.getId())) {
            customerRepository.save(customer);
            System.out.println(customer.getFirstName() + " updated");
        } else throw new CouponException("could not be updated");
    }

    //v
    public void deleteCompany(int companyId) throws CouponException { //v
        if (companyRepository.existsById(companyId)) {
            companyRepository.deleteById(companyId);
            System.out.println("company " + companyId + " deleted");
        } else {
            throw new CouponException(" could not not find company - cannot delete");
        }
    }

    //v
    public void deleteCustomer(int customerId) throws CouponException {
        if (customerRepository.existsById(customerId)) {
            customerRepository.deleteById(customerId);
            System.out.println("customer " + customerId + " deleted");
        } else {
            throw new CouponException("did not find customer");
        }
    }

    //v
    public List<Customer> getAllCustomers() {  //v
        System.out.println("getting all customers:");
        return customerRepository.findAll();
    }

    // v
    public Optional<Company> getOneCompany(int companyId) throws CouponException { //v
        if (companyRepository.existsById(companyId)) {
            System.out.println("company details:");
            return companyRepository.findById(companyId);
        } else {
            throw new CouponException("could not find company " + companyId);
        }
    }

    //v
    public Optional<Customer> getOneCustomer(int customerId) throws CouponException { //v
        if (companyRepository.existsById(customerId)) {
            System.out.println("customer details:");
            System.out.println(customerRepository.findById(customerId));
            return customerRepository.findById(customerId);
        } else {
//            throw new CouponException("could not find customer " + customerId);
            System.out.println("could not find customer");
            return null;
        }
    }


}


