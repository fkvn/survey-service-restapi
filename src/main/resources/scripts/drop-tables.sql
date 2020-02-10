SET FOREIGN_KEY_CHECKS = 0;

drop table if exists  `survey_responses`, `answer_section`, `answer_selection`, `answer`,
								`question_choices`, `question_ranking_choices`, `question_section_questions`, `question_section`,
								`survey_chart`, `survey_chart_point`, `survey_chart_serie`,`survey_chart_xcoordinate`, 
                                `survey_questions`, `question`, `survey`,
								`hibernate_sequence`;
            
SET FOREIGN_KEY_CHECKS = 1;