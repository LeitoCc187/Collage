-- Sample Data: Game Table
-- Purpose: Insert sample games into the "game" table

INSERT INTO game (game_id, title, genre, release_version) VALUES
(101, 'Fantasy Quest', 'RPG', '1.0'),
(102, 'Space Explorer', 'FPS', '2.1'),
(103, 'Robo Rush', 'Platformer', '1.2'),
(104, 'City Builder Pro', 'Simulation', '3.4'),
(105, 'Zombie Horde', 'Survival', '0.9'),
(106, 'Sky Kingdoms', 'Adventure', '2.0'),
(107, 'Ocean Dive', 'Puzzle', '1.5'),
(108, 'Mech Battles', 'Strategy', '3.0'),
(109, 'Speed Fury', 'Racing', '1.3'),
(110, 'Alien Invasion', 'Shooter', '2.5'),
(111, 'Castle Siege', 'RTS', '1.1'),
(112, 'Escape Room', 'Mystery', '2.2'),
(113, 'Astro Miner', 'Sci-Fi', '3.1'),
(114, 'Monster Breach', 'Action', '2.6'),
(115, 'Dark Realms', 'Fantasy', '1.8');

-- Sample Data: Developer Table
-- Purpose: Insert sample developers into the "developer" table

INSERT INTO developer (developer_id, name, role, email) VALUES
(201, 'Alice Johnson', 'Programmer', 'alice@example.com'),
(202, 'Bob Smith', 'QA', 'bob@example.com'),
(203, 'Carol Lee', 'Designer', 'carol@example.com'),
(204, 'David Zhang', 'Programmer', 'david@example.com'),
(205, 'Emily Fox', 'QA', 'emily@example.com'),
(206, 'Frank White', 'Designer', 'frank@example.com'),
(207, 'Grace Kim', 'QA', 'grace@example.com'),
(208, 'Henry Brown', 'Programmer', 'henry@example.com'),
(209, 'Ivy Davis', 'Designer', 'ivy@example.com'),
(210, 'Jack Lee', 'QA', 'jack@example.com'),
(211, 'Katie Morgan', 'Programmer', 'katie@example.com'),
(212, 'Leo Walker', 'QA', 'leo@example.com'),
(213, 'Mia Torres', 'Programmer', 'mia@example.com'),
(214, 'Nathan Grey', 'Designer', 'nathan@example.com'),
(215, 'Olivia Reed', 'QA', 'olivia@example.com');

-- Sample Data: Bug Table
-- Purpose: Insert sample bugs into the "bug" table

INSERT INTO bug (bug_id, title, description, severity, status, reported_date, game_id) VALUES
(301, 'Crash on startup', 'Game crashes immediately after launch.', 'High', 'Open', '2025-04-01', 101),
(302, 'Missing textures', 'Textures fail to load in desert map.', 'Medium', 'In Progress', '2025-04-02', 102),
(303, 'Audio delay', 'Sound effects lag behind actions.', 'Low', 'Resolved', '2025-04-03', 103),
(304, 'Stuck in wall', 'Player clips through terrain.', 'High', 'Open', '2025-04-04', 104),
(305, 'Login failure', 'User unable to log in.', 'High', 'In Progress', '2025-04-05', 105),
(306, 'AI pathing error', 'Enemies walk into walls.', 'Medium', 'Resolved', '2025-04-06', 106),
(307, 'Leaderboard not updating', 'Scores not posted.', 'Low', 'Open', '2025-04-07', 107),
(308, 'Invisible character model', 'Player character not visible.', 'High', 'Resolved', '2025-04-08', 108),
(309, 'Level does not load', 'Black screen on load.', 'High', 'Open', '2025-04-09', 109),
(310, 'Incorrect score calculation', 'Final score lower than actual.', 'Medium', 'In Progress', '2025-04-10', 110),
(311, 'Multiplayer sync issue', 'Players out of sync.', 'High', 'Open', '2025-04-11', 111),
(312, 'Settings reset', 'Settings revert after restart.', 'Low', 'Resolved', '2025-04-12', 112),
(313, 'Achievements not unlocking', 'Progress stuck at 99%.', 'Medium', 'Open', '2025-04-13', 113),
(314, 'Incorrect dialogue', 'NPCs give wrong hints.', 'Low', 'Open', '2025-04-14', 114),
(315, 'Game freeze after cutscene', 'Locks up at loading screen.', 'High', 'In Progress', '2025-04-15', 115);

-- Sample Data: Bug Assignment Table
-- Purpose: Insert sample assignments linking developers to bugs

INSERT INTO bug_assignment (bug_id, developer_id, assigned_date) VALUES
(301, 201, '2025-04-01'),
(301, 202, '2025-04-01'),
(302, 204, '2025-04-02'),
(303, 203, '2025-04-03'),
(304, 205, '2025-04-04'),
(305, 206, '2025-04-05'),
(306, 207, '2025-04-06'),
(307, 208, '2025-04-07'),
(308, 209, '2025-04-08'),
(309, 210, '2025-04-09'),
(310, 211, '2025-04-10'),
(311, 212, '2025-04-11'),
(312, 213, '2025-04-12'),
(313, 214, '2025-04-13'),
(314, 215, '2025-04-14'),
(315, 202, '2025-04-15'),
(315, 204, '2025-04-15');