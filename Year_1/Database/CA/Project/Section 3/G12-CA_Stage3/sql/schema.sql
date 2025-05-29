-- Drop Existing Tables (in Reverse Dependency Order)
-- Purpose: Cleanly reset the database structure before re-creating tables
-- (Dropping child tables first to avoid foreign key constraint errors)

DROP TABLE IF EXISTS bug_assignment;
DROP TABLE IF EXISTS bug;
DROP TABLE IF EXISTS developer;
DROP TABLE IF EXISTS game;

-- Create Game Table
-- Purpose: Stores information about different video games

CREATE TABLE game
(
    game_id INT PRIMARY KEY,
    title VARCHAR(100),
    genre VARCHAR(50),
    release_version VARCHAR(20)
);

-- Create Bug Table
-- Purpose: Stores details about bugs reported for each game

CREATE TABLE bug 
(
    bug_id INT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    severity VARCHAR(10) CHECK (severity IN ('Low', 'Medium', 'High')),
    status  VARCHAR(20) CHECK (status IN ('Open', 'In Progress', 'Resolved')),
    reported_date DATE,
    game_id INTEGER NOT NULL,
    FOREIGN KEY (game_id) REFERENCES game(game_id) ON DELETE CASCADE -- If a game is deleted, delete related bugs
);

-- Create Developer Table
-- Purpose: Stores developer information

CREATE TABLE developer 
(
    developer_id INTEGER PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    role VARCHAR(50) CHECK (role IN ('Programmer', 'QA', 'Designer')),
    email VARCHAR(100) UNIQUE NOT NULL
);

-- Create Bug Assignment Table (Junction Table)
-- Purpose: Many-to-Many relationship between bugs and developers

CREATE TABLE bug_assignment
(
    bug_id INTEGER,
    developer_id INTEGER,
    assigned_date DATE,
    PRIMARY KEY (bug_id, developer_id),
     -- If a bug is deleted, remove assignment
    FOREIGN KEY (bug_id) REFERENCES bug(bug_id) ON DELETE CASCADE,
    -- If a developer is deleted, remove assignment
    FOREIGN KEY (developer_id) REFERENCES developer(developer_id) ON DELETE CASCADE
);
