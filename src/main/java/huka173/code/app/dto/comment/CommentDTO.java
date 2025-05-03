package huka173.code.app.dto.comment;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CommentDTO {
    private Long id;
    private Long taskId;
    private Long userId;
    private String commentText;
    private Date createdAt;
}
