-- Query 1: Multi-table JOIN
-- Purpose: List each bug along with its game title and the developer assigned
SELECT b.bug_id, b.title AS bug_title, g.title AS game_title, d.name AS developer
FROM bug b
JOIN game g ON b.game_id = g.game_id
JOIN bug_assignment ba ON b.bug_id = ba.bug_id
JOIN developer d ON ba.developer_id = d.developer_id;

-- Query 2: Multi-table JOIN
-- Purpose: List each developer and the number of bugs they are assigned

SELECT d.name, COUNT(ba.bug_id) AS total_bugs
FROM developer d
LEFT JOIN bug_assignment ba ON d.developer_id = ba.developer_id
GROUP BY d.name;

-- Query 3: GROUP BY and HAVING
-- Purpose: Show games that have more than 1 reported bug

SELECT g.title AS game_title, COUNT(b.bug_id) AS bug_count
FROM game g
JOIN bug b ON g.game_id = b.game_id
GROUP BY g.title
HAVING COUNT(b.bug_id) > 1;

-- Query 4: GROUP BY
-- Purpose: Count number of bugs by severity level (Low, Medium, High)

SELECT severity, COUNT(*) AS total
FROM bug
GROUP BY severity;

-- Query 5: Subquery (Scalar)
-- Purpose: List all bugs with "High" severity (above average assumed)

SELECT * FROM bug
WHERE severity = 'High'
AND bug_id IN (SELECT bug_id FROM bug WHERE severity = 'High');

-- Query 6: Subquery (Correlated)
-- Purpose: Find developers assigned to the most recently reported bug

SELECT d.name, b.title
FROM bug_assignment ba
JOIN developer d ON ba.developer_id = d.developer_id
JOIN bug b ON ba.bug_id = b.bug_id
WHERE b.reported_date = (SELECT MAX(reported_date) FROM bug);

-- Query 7: CASE Statement
-- Purpose: Show bugs with human-readable severity labels

SELECT title,
    CASE severity
        WHEN 'Low' THEN 'Minor'
        WHEN 'Medium' THEN 'Moderate'
        WHEN 'High' THEN 'Critical'
    END AS severity_level
FROM bug;

-- Query 8: CTE (Common Table Expression)
-- Purpose: List bugs reported in the last 7 days using WITH clause

WITH RecentBugs AS (
    SELECT * FROM bug
    WHERE reported_date >= DATE('2025-04-08')
)
SELECT * FROM RecentBugs;

-- Query 9: View-based Query
-- Purpose: Create and query a view called "BugDetails" showing bugs and developers

-- Drop the view first if it exists
DROP VIEW IF EXISTS BugDetails;

-- Create the view
CREATE VIEW BugDetails AS
SELECT b.bug_id, b.title, g.title AS game_title, d.name AS developer_name
FROM bug b
JOIN game g ON b.game_id = g.game_id
JOIN bug_assignment ba ON b.bug_id = ba.bug_id
JOIN developer d ON ba.developer_id = d.developer_id;

-- Query the created view (run separately after the view is created)
SELECT * FROM BugDetails;

-- Query 10: Wildcards and Filtering
-- Purpose: Find bugs where the title contains the word 'load'

SELECT * FROM bug
WHERE title LIKE '%load%';
