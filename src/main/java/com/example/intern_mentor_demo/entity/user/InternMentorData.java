package com.example.intern_mentor_demo.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InternMentorData {
    private Intern intern;
    private Mentor mentor;
}
