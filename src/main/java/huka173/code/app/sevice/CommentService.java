package huka173.code.app.sevice;

import huka173.code.app.dto.comment.CommentCreateDTO;
import huka173.code.app.dto.comment.CommentDTO;
import huka173.code.app.dto.comment.CommentParamsDTO;
import huka173.code.app.exception.ResourceNotFoundException;
import huka173.code.app.mapper.comment.CommentMapper;
import huka173.code.app.repository.CommentRepository;
import huka173.code.app.specification.CommentSpecification;
import huka173.code.app.util.UserUtils;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentSpecification commentSpecification;

    @Autowired
    private UserUtils userUtils;

    public List<CommentDTO> findAll(CommentParamsDTO params, int page) {
        var spec = commentSpecification.build(params);
        return commentRepository.findAll(spec, PageRequest.of(page - 1, 10))
                .map(commentMapper::map)
                .stream()
                .toList();
    }

    public CommentDTO findById(Long id) {
        var model = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment with id " + id + " not found"));
        return commentMapper.map(model);
    }

    public CommentDTO create(CommentCreateDTO dto) {
        var model = commentMapper.map(dto);
        model.setUserComment(userUtils.getCurrentUser());
        commentRepository.save(model);
        return commentMapper.map(model);
    }

    public void delete(Long id) throws BadRequestException {
        var model = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment with id " + id + " not found"));
        if (userUtils.getCurrentUser().getId() != model.getUserComment().getId()) {
            throw new BadRequestException("You can only delete your comments");
        }
        commentRepository.deleteById(id);
    }
}
