package huka173.code.app.dto.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentCreateDTO {
    @NotNull
    private Long taskId;

    @NotNull
    @NotBlank
    private String commentText;
}
