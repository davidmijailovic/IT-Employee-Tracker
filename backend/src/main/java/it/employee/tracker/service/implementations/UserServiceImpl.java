package it.employee.tracker.service.implementations;

import it.employee.tracker.model.Address;
import it.employee.tracker.model.Role;
import it.employee.tracker.model.User;
import it.employee.tracker.model.dto.UserDTO;
import it.employee.tracker.repository.UserRepository;
import it.employee.tracker.service.interfaces.RoleService;
import it.employee.tracker.service.interfaces.UserService;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User registerUser(User user,UserDTO userDTO) {
        Address address = new Address();
        address.setCity(userDTO.getAddress().getCity());
        address.setCountry(userDTO.getAddress().getCountry());
        address.setStreet(userDTO.getAddress().getStreet());
        address.setNumber(userDTO.getAddress().getNumber());
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setAddress(address);
        user.setTitle(userDTO.getTitle());
        String salt = generateSalt();
        String saltedPassword = salt + userDTO.getPassword();
        String hashedPassword = passwordEncoder.encode(userDTO.getPassword());
        user.setPassword(hashedPassword);
        user.setSalt(salt);
        List<Role> roles = roleService.findByName("ROLE_"+userDTO.getAccountType().toString());
        user.setRoles(roles);
        user.setEnabled(true);
        user.setApproved(true);
        return user;
    }

    private String generateSalt() {
        // Generate a random salt using a secure random number generator
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[16];
        random.nextBytes(saltBytes);

        // Convert the salt bytes to a hexadecimal string representation
        return Hex.encodeHexString(saltBytes);
    }


}
