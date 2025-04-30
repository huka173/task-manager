package huka173.code.app.mapper.task;

import huka173.code.app.dto.task.TaskCreateDTO;
import huka173.code.app.dto.task.TaskDTO;
import huka173.code.app.dto.task.TaskUpdateDTO;
import huka173.code.app.mapper.JsonNullableMapper;
import huka173.code.app.mapper.ReferenceMapper;
import huka173.code.app.model.Task;
import huka173.code.app.model.TaskLabel;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        uses = { JsonNullableMapper.class, ReferenceMapper.class },
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class TaskMapper {
    @Mapping(target = "assignee.id", source = "assignee_id")
    @Mapping(target = "name", source = "title")
    @Mapping(target = "description", source = "content")
    @Mapping(target = "taskStatus.slug", source = "status")
    public abstract Task map(TaskCreateDTO dto);

    @Mapping(target = "assignee_id", source = "assignee.id")
    @Mapping(target = "title", source = "name")
    @Mapping(target = "content", source = "description")
    @Mapping(target = "status", source = "taskStatus.slug")
    @Mapping(target = "taskLabelIds", source = "taskLabels")
    public abstract TaskDTO map(Task model);

    @Mapping(target = "name", source = "title")
    @Mapping(target = "description", source = "content")
    public abstract void update(TaskUpdateDTO dto, @MappingTarget Task model);

    protected List<Long> mapTaskLabelsToIds(List<TaskLabel> taskLabels) {
        if (taskLabels == null) {
            return null;
        }
        return taskLabels.stream()
                .map(taskLabel -> taskLabel.getLabel().getId())
                .toList();
    }
}
