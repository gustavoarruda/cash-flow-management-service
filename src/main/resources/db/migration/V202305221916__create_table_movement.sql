CREATE TABLE IF NOT EXISTS cash_flow_management_service.movement (
	id varchar(36) PRIMARY KEY,
	description varchar(255) NOT NULL,
	person_id varchar(36) NOT NULL,
	date date NOT NULL,
	value numeric(20,2),
	type_movement varchar(1) NOT NULL,
	updated_at TIMESTAMP,
	created_at TIMESTAMP NULL);
