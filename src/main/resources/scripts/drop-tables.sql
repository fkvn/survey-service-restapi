-- *** Get all table names *****-----

-- SELECT concat('`', table_name, '`,')
-- FROM information_schema.tables
-- WHERE table_schema = 'surveyService'; 

-- ** Note: table_schema = 'your_database_name'

-- *****************************-----



SET FOREIGN_KEY_CHECKS = 0; -- disable foreign key check

-- *** paste all tables' names returned from above after "exists" ***---------

drop table if exists `answer`,
`answer_ranking_selection`,
`answer_section`,
`answer_selection`,
`file`,
`hibernate_sequence`,
`question`,
`question_choices`,
`question_ranking_choices`,
`question_section`,
`survey`,
`survey_chart`,
`survey_chart_point`,
`survey_chart_serie`,
`survey_chart_xcoordinate`,
`survey_responses`,
`user`;

-- ** Note: remember to remove "," for the last table's name

SET FOREIGN_KEY_CHECKS = 1; -- enable foreign key check