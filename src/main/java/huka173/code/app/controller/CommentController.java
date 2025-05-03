package huka173.code.app.controller;

import huka173.code.app.dto.comment.CommentCreateDTO;
import huka173.code.app.dto.comment.CommentDTO;
import huka173.code.app.dto.comment.CommentParamsDTO;
import huka173.code.app.sevice.CommentService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping(path = "/comments")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<CommentDTO>> index(CommentParamsDTO params, @RequestParam(defaultValue = "1") int page) {
        var comments = commentService.findAll(params, page);

        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(comments.size()))
                .body(comments);
    }

    @GetMapping(path = "/comments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CommentDTO> show(@PathVariable Long id) {
        var comment = commentService.findById(id);

        return ResponseEntity.ok(comment);
    }

    @PostMapping(path = "/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CommentDTO> create(@Valid @RequestBody CommentCreateDTO dto) {
        var comment = commentService.create(dto);

        return ResponseEntity.ok(comment);
    }

    @DeleteMapping(path = "/comments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) throws BadRequestException {
        commentService.delete(id);
    }
}
