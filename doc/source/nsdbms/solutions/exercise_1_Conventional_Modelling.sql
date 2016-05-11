CREATE TABLE Person (
    person_id INT PRIMARY KEY,
    name VARCHAR(256),
    address_id INT,
    age INT,
    date_of_birth DATE
);

CREATE TABLE Hobby (
    hobby_id INT PRIMARY KEY,
    name VARCHAR(256),
    description VARCHAR(2048)
);

CREATE TABLE PersonHobby (
    hobby_id INT,
    person_id INT,
    since_when DATE,
    FOREIGN KEY (person_id) REFERENCES Person(person_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (hobby_id) REFERENCES Hobby(hobby_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Parent (
    person_id INT,
    spouse_id INT,
    FOREIGN KEY (person_id) REFERENCES Person(person_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (spouse_id) REFERENCES Parent(person_id) ON DELETE SET NULL ON UPDATE CASCADE,
    PRIMARY KEY (person_id)
);

CREATE TABLE Child (
    person_id INT,
    father_id INT,
    mother_id INT,
    kindergarden_id INT,
    FOREIGN KEY (person_id) REFERENCES Person(person_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (father_id) REFERENCES Parent(person_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (mother_id) REFERENCES Parent(person_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (kindergarden_id) REFERENCES Kindergarden(kindergarden_id) ON DELETE SET NULL ON UPDATE CASCADE,
    PRIMARY KEY (person_id)
);

CREATE TABLE Sibling (
    sibling_one_id INT,
    sibling_two_id INT,
    FOREIGN KEY (sibling_one_id) REFERENCES Child(person_id) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (sibling_two_id) REFERENCES Child(person_id) ON DELETE SET NULL ON UPDATE CASCADE,
    PRIMARY KEY (sibling_one_id, sibling_two_id)
);

CREATE TABLE Kindergarden (
    kindergarden_id INT,
    name VARCHAR(100),
    PRIMARY KEY (kindergarden_id)
);

CREATE TABLE School (
    school_id INT,
    name VARCHAR(100),
    PRIMARY KEY (school_id)
);
