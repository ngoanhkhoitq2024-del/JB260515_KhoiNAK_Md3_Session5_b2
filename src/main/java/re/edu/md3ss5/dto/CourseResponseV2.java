package re.edu.md3ss5.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import re.edu.md3ss5.entity.CourseStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponseV2 {
    private Long id;
    private String title;
    private CourseStatus status;
}