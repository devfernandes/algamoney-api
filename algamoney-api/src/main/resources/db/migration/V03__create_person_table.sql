CREATE TABLE PERSON(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    active tinyint(1) NOT NULL,
    public_place VARCHAR(30),
    number VARCHAR(30),
    complement VARCHAR(30),
    neightborhood VARCHAR(30),
    CEP VARCHAR(30),
    city VARCHAR(30),
    state VARCHAR(30)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;