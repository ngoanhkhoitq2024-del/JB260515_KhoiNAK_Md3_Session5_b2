package re.edu.md3ss5.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import re.edu.md3ss5.entity.CourseStatus;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourseResponse {
    private Long id;
    private String title;
    private CourseStatus status;
}
