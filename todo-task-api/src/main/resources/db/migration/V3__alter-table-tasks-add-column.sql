alter table tasks add active tinyint;
update tasks set active = 1;
ALTER TABLE tasks
MODIFY active tinyint NOT NULL;