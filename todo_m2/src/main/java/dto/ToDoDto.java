package dto;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ToDoDto {
    // table 구조와 동일하게 작성
    private int no;
    private String title;
    private Date createdAt;
    private boolean completed;
    private String description;
}
