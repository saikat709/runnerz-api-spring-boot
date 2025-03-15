CREATE TABLE IF NOT EXISTS RUN (
    id INT NOT NULL,
    title varchar(250) NOT NULL,
    start_on timestamp NOT NULL,
    stop_on timestamp NOT NULL,
    miles INT NOT NULL,
    location varchar(10) NOT NULL,
    primary key (id)
);