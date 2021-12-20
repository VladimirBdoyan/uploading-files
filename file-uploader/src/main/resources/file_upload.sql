CREATE database uploaded_files;
CREATE SEQUENCE files_sequence INCREMENT 1 START 1;
CREATE SEQUENCE users_sequence INCREMENT 1 START 1;
CREATE TABLE users (
                       id   bigint NOT NULL PRIMARY KEY DEFAULT nextval('users_sequence'),
                       name varchar
);

CREATE TABLE files
(
    id          bigint  NOT NULL PRIMARY KEY DEFAULT nextval('files_sequence'),
    name        varchar NOT NULL,
    content_type varchar NOT NULL,
    data oid,
    size   bigint,
    upload_date timestamp,
    user_id     bigint  REFERENCES users (id)
);