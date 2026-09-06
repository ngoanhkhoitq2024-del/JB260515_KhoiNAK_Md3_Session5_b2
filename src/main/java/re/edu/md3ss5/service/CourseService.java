package re.edu.md3ss5.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import re.edu.md3ss5.dto.CourseResponse;
import re.edu.md3ss5.dto.PageResponse;
import re.edu.md3ss5.entity.Course;
import re.edu.md3ss5.entity.CourseStatus;
import re.edu.md3ss5.repository.CourseRepository;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    public PageResponse<CourseResponse> getPagedCourses(int page, int size, String sortBy,
            Sort.Direction direction, CourseStatus status) {
        // Safety check
        if (page < 0) {page = 0;}

        // Không truyền sortBy → mặc định sort theo id
        if (sortBy == null || sortBy.isBlank()) {
            sortBy = "id";
        }

        Sort sort = Sort.by(direction, sortBy);

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Course> courses = courseRepository.findAllByStatus(status, pageable);

        // Map Entity → DTO
        Page<CourseResponse> courseResponses = courses.map(
                course -> new CourseResponse(
                        course.getId(),
                        course.getName(),
                        course.getStatus()
                )
        );

        // Page<CourseResponse> → PageResponse<CourseResponse>
        return new PageResponse<>(
                courseResponses.getContent(),
                courseResponses.getNumber(),
                courseResponses.getSize(),
                (int) courseResponses.getTotalElements(),
                courseResponses.getTotalPages(),
                courseResponses.isLast()
        );
    }
}