package re.edu.md3ss5.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import re.edu.md3ss5.dto.CourseResponse;
import re.edu.md3ss5.dto.CourseResponseV2;
import re.edu.md3ss5.dto.PageResponse;
import re.edu.md3ss5.entity.Course;
import re.edu.md3ss5.entity.CourseStatus;
import re.edu.md3ss5.repository.CourseRepository;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    public PageResponse<CourseResponseV2> getPagedCoursesV2(int page, int size, String sortBy,
            Sort.Direction direction, CourseStatus status) {
        // Safety check
        if (page < 0) {page = 0;}

        if (sortBy == null || sortBy.isBlank()) {
            sortBy = "id";
        }

        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        // Repository trả về DTO trực tiếp
        Page<CourseResponseV2> courses =
                courseRepository.findAllByStatusV2(status, pageable);

        // Đóng gói thành PageResponse
        return new PageResponse<>(
                courses.getContent(),
                courses.getNumber(),
                courses.getSize(),
                (int) courses.getTotalElements(),
                courses.getTotalPages(),
                courses.isLast()
        );
    }
}