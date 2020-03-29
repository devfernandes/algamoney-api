CREATE TABLE LAUNCH(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(50) NOT NULL,
    due_date DATE NOT NULL,
    payment_date DATE NOT NULL,
    value DOUBLE NOT NULL,
    observation VARCHAR(50),
    type VARCHAR(50),
    category_id BIGINT,
    person_id BIGINT,
    FOREIGN KEY (category_id) REFERENCES category (id),
    FOREIGN KEY (person_id) REFERENCES person (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;