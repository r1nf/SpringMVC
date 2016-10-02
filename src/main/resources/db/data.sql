-- add users
INSERT INTO user (id, first_name, last_name, email, level, created_by_user, created_date, modified_by_user, modified_date) VALUES
(NULL, 'Viktoriia','Moiseienko','viktoriia_moiseienko@epam.com','D1','vikki','2016-05-01','vikki','2016-05-01'),
(NULL, 'Oleksander','Barchuk','oleksander_barchuk@epam.com','D2','vikki','2016-05-01','vikki','2016-05-01'),
(NULL, 'Ihor','Kruk','ihor_kruk@epam.com','D4','vikki','2016-05-01','vikki','2016-05-01');

-- add mentorship programs
INSERT INTO mentorship (id, name, start_date, end_date, created_by_user, created_date, modified_by_user, modified_date) VALUES
(NULL, 'Java H1','2016-06-01','2016-08-31','vikki','2016-05-01','vikki','2016-05-01'),
(NULL, 'Java H2','2016-09-01','2016-11-30','vikki','2016-05-01','vikki','2016-05-01');