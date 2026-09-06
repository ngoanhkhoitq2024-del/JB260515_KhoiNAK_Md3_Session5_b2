package re.edu.md3ss5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import re.edu.md3ss5.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
