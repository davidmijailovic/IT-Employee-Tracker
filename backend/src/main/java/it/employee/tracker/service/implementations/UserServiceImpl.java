package it.employee.tracker.service.implementations;

import it.employee.tracker.model.*;
import it.employee.tracker.model.dto.ProjectDTO;
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
import org.springframework.ui.Model;

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


    public List<SkillDTO> mapSkillToSkillDto(SoftwareEngineer softwareEngineer) {
        List<SkillDTO> skillDTOList = new ArrayList<>();
        for (SoftwareEngineerSkill softwareEngineerSkill : softwareEngineer.getSkills()) {
            SkillDTO skillDTO = modelMapper.map(softwareEngineerSkill, SkillDTO.class);
            skillDTO.setName(softwareEngineerSkill.getSkill().getName());
            skillDTO.setGrade(softwareEngineerSkill.getValue());
            skillDTOList.add(skillDTO);
        }
        return skillDTOList;
    }

    @Override
    public List<ProjectDTO> mapProjectToProjectDto(SoftwareEngineer softwareEngineer) {
        List<ProjectDTO> projects = new ArrayList<>();
        for (SoftwareEngineerProject softwareEngineerProject : softwareEngineer.getProjects()) {
            ProjectDTO projectDTO = modelMapper.map(softwareEngineerProject, ProjectDTO.class);
            projectDTO.setName(softwareEngineerProject.getProject().getName());
            projectDTO.setDescription(softwareEngineerProject.getDescription());
            projectDTO.setDuration(softwareEngineerProject.getDuration());
            projects.add(projectDTO);
        }
        return projects;
    }

    private String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[16];
        random.nextBytes(saltBytes);
        return Hex.encodeHexString(saltBytes);
    }


}
