package re.edu.md3ss5.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import re.edu.md3ss5.dto.ApiResponse;
import re.edu.md3ss5.dto.CourseResponse;
import re.edu.md3ss5.service.CourseService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    @GetMapping
    public ApiResponse<Page<CourseResponse>> getCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(defaultValue = "DESC") Sort.Direction direction
    ) {
        Page<CourseResponse> result =
                courseService.getPagedCourses(page, size, sortBy, direction);

        return new ApiResponse<>(true, "Ok", result);
    }
}
