package huka173.code.app.sevice;

import huka173.code.app.dto.user.UserCreateDTO;
import huka173.code.app.dto.user.UserDTO;
import huka173.code.app.dto.user.UserUpdateDTO;
import huka173.code.app.exception.ResourceNotFoundException;
import huka173.code.app.mapper.user.UserMapper;
import huka173.code.app.repository.TaskRepository;
import huka173.code.app.repository.UserRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TaskRepository taskRepository;

    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::map)
                .toList();
    }

    public UserDTO findById(Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
        return userMapper.map(user);
    }

    public UserDTO create(UserCreateDTO dto) {
        var model = userMapper.map(dto);

        model.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(model);

        return userMapper.map(model);
    }

    public UserDTO update(UserUpdateDTO dto, Long id) {
        var model = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));

        userMapper.update(dto, model);
        model.setPassword(passwordEncoder.encode(model.getPassword()));
        userRepository.save(model);

        return userMapper.map(model);
    }

    public void destroy(Long id) throws BadRequestException {
        if (taskRepository.existsByAssignee_Id(id)) {
            throw new BadRequestException("You can not delete user with task");
        }
        userRepository.deleteById(id);
    }
}
