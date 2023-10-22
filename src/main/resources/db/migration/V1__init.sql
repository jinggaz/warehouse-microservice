CREATE TABLE manufacturer (
	manufacturer_id INT NOT NULL AUTO_INCREMENT,
	manufacturer_name VARCHAR(50) NOT NULL UNIQUE,
	created_timestamp timestamp DEFAULT CURRENT_TIMESTAMP,
  	PRIMARY KEY (manufacturer_id)
);

CREATE TABLE product (
	product_id INT NOT NULL AUTO_INCREMENT,
	product_name varchar(35) NOT NULL UNIQUE,
	description varchar(50),
	manufacturer_id INT,
	asset_type varchar(15) NOT NULL,
	is_asset BOOLEAN DEFAULT FALSE,
	created_timestamp timestamp DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (product_id),
	FOREIGN KEY (manufacturer_id) REFERENCES manufacturer(manufacturer_id)
);

CREATE TABLE inventory (
	inventory_id bigint NOT NULL AUTO_INCREMENT,
	manufacturer_id INT NOT NULL,
	product_id INT NOT NULL UNIQUE,
	current_inventory INT,
	created_timestamp timestamp DEFAULT CURRENT_TIMESTAMP,
	last_updated_timestamp timestamp DEFAULT CURRENT_TIMESTAMP,
  	PRIMARY KEY (inventory_id),
	FOREIGN KEY (manufacturer_id) REFERENCES manufacturer(manufacturer_id),
	FOREIGN KEY (product_id) REFERENCES product(product_id)
);

CREATE TABLE receive (
	receive_id bigint NOT NULL AUTO_INCREMENT,
	manufacturer_id INT NOT NULL,
	product_id INT NOT NULL,
	carrier varchar(15),
	recieved_quantity INT NOT NULL,
	created_timestamp timestamp DEFAULT CURRENT_TIMESTAMP,
	last_updated_timestamp timestamp DEFAULT CURRENT_TIMESTAMP,
	notes varchar(255),
	PRIMARY KEY (receive_id),
	FOREIGN KEY (manufacturer_id) REFERENCES manufacturer(manufacturer_id),
	FOREIGN KEY (product_id) REFERENCES product(product_id)
);

CREATE TABLE product_detail (
	product_detail_id bigint NOT NULL AUTO_INCREMENT,
	receive_id bigint NOT NULL,
	manufacturer_id INT NOT NULL,
	product_id INT NOT NULL,
	serial_number varchar(20) UNIQUE,
	tag_number varchar(20),
	product_status varchar(10) DEFAULT 'STAGING',
	created_timestamp timestamp DEFAULT CURRENT_TIMESTAMP,
	last_updated_timestamp timestamp DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (product_detail_id),
	FOREIGN KEY (receive_id) REFERENCES receive(receive_id),
	FOREIGN KEY (manufacturer_id) REFERENCES manufacturer(manufacturer_id),
	FOREIGN KEY (product_id) REFERENCES product(product_id)
);

CREATE TABLE role (
	role_id INT NOT NULL AUTO_INCREMENT,
  	name varchar(15) NOT NULL UNIQUE,
  	description varchar(45),
  	created timestamp DEFAULT CURRENT_TIMESTAMP,
  	PRIMARY KEY (role_id)
);

CREATE TABLE users (
	user_id INT NOT NULL AUTO_INCREMENT,
 	name varchar(50) NOT NULL UNIQUE,
	password varchar(60) NOT NULL,
	created timestamp DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (user_id)
);

CREATE TABLE user_roles (
	user_roles_id bigint NOT NULL AUTO_INCREMENT,
	user_id INT NOT NULL,
	role_id INT NOT NULL,
	created timestamp DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (user_roles_id)
);

INSERT INTO manufacturer (manufacturer_name) VALUES ('Apple');
INSERT INTO manufacturer (manufacturer_name) VALUES ('Dell');

INSERT INTO product (product_name, description, manufacturer_id, asset_type, is_asset) VALUES ('MacBook Pro 16 inch', 'Apple MacBook Pro 16 inch laptop computer', 1, 'LAPTOP', true);
INSERT INTO product (product_name, description, manufacturer_id, asset_type, is_asset) VALUES ('iPad 10 inch', 'Apple iPad 10 inch tablet', 1, 'TABLET', false);
INSERT INTO product (product_name, description, manufacturer_id, asset_type, is_asset) VALUES ('Monitor G2724D', 'Dell 27 Gaming Monitor', 2, 'MONITOR', false);
INSERT INTO product (product_name, description, manufacturer_id, asset_type, is_asset) VALUES ('Optiplex 7020', 'Dell Optiplex 7020 Workstation', 2, 'DESKTOP', true);

INSERT INTO inventory (manufacturer_id, product_id, current_inventory) VALUES (1, 1, 1);
INSERT INTO inventory (manufacturer_id, product_id, current_inventory) VALUES (1, 2, 2);
INSERT INTO inventory (manufacturer_id, product_id, current_inventory) VALUES (2, 3, 3);
INSERT INTO inventory (manufacturer_id, product_id, current_inventory) VALUES (2, 4, 4);

INSERT INTO role (name, description) VALUES ('READ_ONLY', 'Read only role');
INSERT INTO role (name, description) VALUES ('READ_WRITE', 'Read and write role');

INSERT INTO users (name, password) VALUES('read', '$2a$10$oYjTYwn8WAyvATDkLlvLp.a31yC4JBimlyj/eERZHImm2k5E2dgUq');
INSERT INTO users (name, password) VALUES('write', '$2a$10$2P8ng6b5IMUIBrnE1cqJvORVVrNNRNNqWp9UjARCET9NWa9zinLe.');

INSERT INTO user_roles (user_id, role_id) VALUES(1, 1);
INSERT INTO user_roles (user_id, role_id) VALUES(2, 2);
