CREATE TABLE tasks(
	id BIGINT AUTO_INCREMENT primary key,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    completed BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    user_id bigint NOT NULL,

    CONSTRAINT fk_task_user
		FOREIGN KEY(user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);
