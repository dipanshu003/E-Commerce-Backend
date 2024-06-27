package com.amazon.apis.service.security;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.amazon.apis.enums.UserRole;
import com.amazon.apis.model.Product;
import com.amazon.apis.model.User;
import com.amazon.apis.repo.ProductRepository;
import com.amazon.apis.repo.UserRepository;

import jakarta.annotation.PostConstruct;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private ProductRepository proRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepo;

    @PostConstruct
    @Override
    public void createAdminAccount() {
        User user = userRepo.findByUserRole(UserRole.ADMIN);

        if (user == null) {
            User adminUser = new User();
            adminUser.setEmail("admin@gmail.com");
            adminUser.setName("admin");
            adminUser.setUserRole(UserRole.ADMIN);
            adminUser.setAddedDate(new Date());
            adminUser.setPassword(passwordEncoder.encode("admin"));
            userRepo.save(adminUser);
        }
    }

    @Override
    public boolean createUser(User user) {
        if (userRepo.existsByEmail(user.getEmail())) {
            System.err.println("User Already Exists...");
            return false;
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setUserRole(UserRole.USER);
            userRepo.save(user);
            System.out.println("User Created...");
            return true;
        }
    }
}
