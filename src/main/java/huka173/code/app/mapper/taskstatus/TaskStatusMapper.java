package huka173.code.app.mapper.taskstatus;

import huka173.code.app.dto.taskstatus.TaskStatusCreateDTO;
import huka173.code.app.dto.taskstatus.TaskStatusDTO;
import huka173.code.app.dto.taskstatus.TaskStatusUpdateDTO;
import huka173.code.app.mapper.JsonNullableMapper;
import huka173.code.app.model.TaskStatus;
import org.mapstruct.*;

@Mapper(
        uses = { JsonNullableMapper.class },
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class TaskStatusMapper {
    public abstract TaskStatus map(TaskStatusCreateDTO dto);
    public abstract TaskStatusDTO map(TaskStatus model);
    public abstract void update(TaskStatusUpdateDTO dto, @MappingTarget TaskStatus model);
}
