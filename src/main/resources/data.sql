INSERT INTO users (username, password, birthday)
VALUES ('admin', '{noop}admin', '1990-01-10'),
       ('user', '{noop}user', '1995-02-10');

INSERT INTO roles (name)
VALUES ('ADMIN'),
       ('USER');

INSERT INTO users_roles (user_id, role_id)
VALUES (1, 1),
       (1, 2),
       (2, 2);
