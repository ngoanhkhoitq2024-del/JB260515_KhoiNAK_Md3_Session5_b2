package re.edu.md3ss5.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import re.edu.md3ss5.dto.CourseResponse;
import re.edu.md3ss5.entity.Course;
import re.edu.md3ss5.repository.CourseRepository;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Page<CourseResponse> getPagedCourses(int page, int size, String sortBy,
            Sort.Direction direction) {
        // 1. Safety check cho page
        if (page < 0) {
            page = 0;
        }

        // 2. Nếu không truyền sortBy → mặc định sort theo id
        if (sortBy == null || sortBy.isBlank()) {
            sortBy = "id";
        }

        // 3. Tạo Sort
        Sort sort = Sort.by(direction, sortBy);

        // 4. Tạo Pageable
        Pageable pageable = PageRequest.of(page, size, sort);

        // 5. Lấy dữ liệu từ Database
        Page<Course> courses = courseRepository.findAll(pageable);

        return courses.map(course -> new CourseResponse(
                course.getId(),
                course.getName()
        ));
    }
}
