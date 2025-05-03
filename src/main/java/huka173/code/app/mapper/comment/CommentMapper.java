package huka173.code.app.mapper.comment;

import huka173.code.app.dto.comment.CommentCreateDTO;
import huka173.code.app.dto.comment.CommentDTO;
import huka173.code.app.mapper.JsonNullableMapper;
import huka173.code.app.mapper.ReferenceMapper;
import huka173.code.app.model.Comment;
import org.mapstruct.*;

@Mapper(
        uses = { JsonNullableMapper.class, ReferenceMapper.class},
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class CommentMapper {
    @Mapping(target = "taskComment.id", source = "taskId")
    public abstract Comment map(CommentCreateDTO dto);

    @Mapping(target = "taskId", source = "taskComment.id")
    @Mapping(target = "userId", source = "userComment.id")
    public abstract CommentDTO map(Comment model);
}
