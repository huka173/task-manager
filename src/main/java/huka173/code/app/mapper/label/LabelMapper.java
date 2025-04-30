package huka173.code.app.mapper.label;

import huka173.code.app.dto.label.LabelCreateDTO;
import huka173.code.app.dto.label.LabelDTO;
import huka173.code.app.dto.label.LabelUpdateDTO;
import huka173.code.app.mapper.JsonNullableMapper;
import huka173.code.app.model.Label;
import org.mapstruct.*;

@Mapper(
        uses = { JsonNullableMapper.class },
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class LabelMapper {
    public abstract Label map(LabelCreateDTO dto);
    public abstract LabelDTO map(Label model);
    public abstract void update(LabelUpdateDTO dto, @MappingTarget Label model);
}
