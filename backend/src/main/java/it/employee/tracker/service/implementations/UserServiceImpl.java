package it.employee.tracker.service.implementations;

import it.employee.tracker.model.*;
import it.employee.tracker.model.dto.SkillDTO;
import it.employee.tracker.model.dto.UserDTO;
import it.employee.tracker.model.response.UserResponse;
import it.employee.tracker.repository.UserRepository;
import it.employee.tracker.service.interfaces.RoleService;
import it.employee.tracker.service.interfaces.UserService;
import org.apache.commons.codec.binary.Hex;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper modelMapper;

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
        String saltedPassword = userDTO.getPassword().concat(salt);
        String hashedPassword = passwordEncoder.encode(saltedPassword);
        user.setPassword(hashedPassword);
        user.setSalt(salt);
        List<Role> roles = roleService.findByName("ROLE_"+userDTO.getAccountType().toString());
        user.setRoles(roles);
        user.setEnabled(true);
        user.setApproved(true);
        return user;
    }

    @Override
    public User editUser(User editUser, UserResponse user, Long id) {
        editUser.setName(user.getName());
        editUser.setSurname(user.getSurname());
        editUser.setPhone(user.getPhone());
        editUser.setTitle(user.getTitle());
        editUser.setAddress(user.getAddress());
        userRepository.save(editUser);
        return editUser;
    }

    @Override
    public User findById(Long id) throws NoSuchElementException {
        return userRepository.findById(id).get();
    }


    @Override
    public List<SkillDTO> mapSkillToSkillDto(SoftwareEngineer softwareEngineer) {
        List<SkillDTO> skillDTOs = new ArrayList<>();
        TypeMap<Skill, SkillDTO> skillMap = modelMapper.getTypeMap(Skill.class, SkillDTO.class);

        if (skillMap == null) {
            skillMap = modelMapper.createTypeMap(Skill.class, SkillDTO.class);
            skillMap.addMappings(mapping -> {
                mapping.map(Skill::getName, SkillDTO::setName);
            });
        }

        TypeMap<Skill, SkillDTO> finalSkillMap = skillMap;
        skillDTOs = softwareEngineer.getSkills().stream()
                .map(s -> {
                    SkillDTO skillDTO = finalSkillMap.map(s);
                    if (!s.getSoftwareEngineerSkills().isEmpty()) {
                        skillDTO.setGrade(s.getSoftwareEngineerSkills().get(0).getValue());
                    }
                    return skillDTO;
                })
                .collect(Collectors.toList());

        return skillDTOs;
    }
    private String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[16];
        random.nextBytes(saltBytes);
        return Hex.encodeHexString(saltBytes);
    }


}
