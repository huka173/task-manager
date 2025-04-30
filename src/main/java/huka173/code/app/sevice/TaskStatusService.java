package huka173.code.app.sevice;

import huka173.code.app.dto.taskstatus.TaskStatusCreateDTO;
import huka173.code.app.dto.taskstatus.TaskStatusDTO;
import huka173.code.app.dto.taskstatus.TaskStatusUpdateDTO;
import huka173.code.app.exception.ResourceNotFoundException;
import huka173.code.app.mapper.taskstatus.TaskStatusMapper;
import huka173.code.app.repository.TaskRepository;
import huka173.code.app.repository.TaskStatusRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskStatusService {
    @Autowired
    private TaskStatusMapper taskStatusMapper;

    @Autowired
    private TaskStatusRepository taskStatusRepository;

    @Autowired
    private TaskRepository taskRepository;

    public List<TaskStatusDTO> findAll() {
        return taskStatusRepository.findAll()
                .stream()
                .map(taskStatusMapper::map)
                .toList();
    }

    public TaskStatusDTO findById(Long id) {
        var model = taskStatusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task status with id " + id + " not found"));
        return taskStatusMapper.map(model);
    }

    public TaskStatusDTO create(TaskStatusCreateDTO dto) {
        var model = taskStatusMapper.map(dto);
        taskStatusRepository.save(model);
        return taskStatusMapper.map(model);
    }

    public TaskStatusDTO update(TaskStatusUpdateDTO dto, Long id) {
        var model = taskStatusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task status with id " + id + " not found"));
        taskStatusMapper.update(dto, model);
        taskStatusRepository.save(model);

        return taskStatusMapper.map(model);
    }

    public void destroy(Long id) throws BadRequestException {
        if (taskRepository.existsByTaskStatusId(id)) {
            throw new BadRequestException("You can not delete status with task");
        }

        taskStatusRepository.deleteById(id);
    }
}
