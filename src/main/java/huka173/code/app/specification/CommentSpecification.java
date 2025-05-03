package huka173.code.app.specification;

import huka173.code.app.dto.comment.CommentParamsDTO;
import huka173.code.app.model.Comment;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class CommentSpecification {
    public Specification<Comment> build(CommentParamsDTO params) {
        return withTaskId(params.getTaskId());
    }

    private Specification<Comment> withTaskId(Long taskId) {
        return (root, query, criteriaBuilder) -> taskId == null
                ? criteriaBuilder.conjunction()
                : criteriaBuilder.equal(root.get("taskComment").get("id"), taskId);
    }
}
