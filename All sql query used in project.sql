
-- To create database ...
create database real_time_event_tracking ;

-- Use database 
use real_time_event_tracking ; 

-- Creating tables 

-- event table 
CREATE TABLE Event (
    event_id INT AUTO_INCREMENT PRIMARY KEY,
    event_name VARCHAR(100) NOT NULL,
    location VARCHAR(150),
    date DATE NOT NULL
);

-- add new coloumn event_organizer in Event table 
alter table event add event_organizer varchar(100) ; 

-- session table
CREATE TABLE Session (
    session_id INT AUTO_INCREMENT PRIMARY KEY,
    event_id INT,
    session_name VARCHAR(100) NOT NULL,
    start_time DATETIME NOT NULL,
    end_time DATETIME,
    speaker VARCHAR(100),
    FOREIGN KEY (event_id) REFERENCES Event(event_id) ON DELETE CASCADE
);

-- I have to take time for start_time and end_time so 
ALTER TABLE session MODIFY start_time TIME;
ALTER TABLE session MODIFY end_time TIME;

-- Metric table
CREATE TABLE Metric (
    metric_id INT AUTO_INCREMENT PRIMARY KEY,
    session_id INT,
    metric_type VARCHAR(50) NOT NULL,
    metric_value INT,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (session_id) REFERENCES Session(session_id) ON DELETE CASCADE
);

-- Report table
CREATE TABLE Report (
    report_id INT AUTO_INCREMENT PRIMARY KEY,
    event_id INT,
    report_name VARCHAR(100),
    generated_on DATETIME DEFAULT CURRENT_TIMESTAMP,
    description TEXT,
    FOREIGN KEY (event_id) REFERENCES Event(event_id) ON DELETE CASCADE
);


-- DashboardConfig
CREATE TABLE DashboardConfig (
    config_id INT AUTO_INCREMENT PRIMARY KEY,
    event_id INT,
    refresh_rate INT DEFAULT 30,  -- Example: refresh every 30 seconds
    theme VARCHAR(50) DEFAULT 'Light',
    FOREIGN KEY (event_id) REFERENCES Event(event_id) ON DELETE CASCADE
);



-- Adding Indexes
-- To optimize performance, especially when querying metrics and events, 
-- let’s add indexes to frequently accessed columns:

-- Index for Event table
CREATE INDEX idx_event_name ON Event(event_name);

-- Index for Metric table
CREATE INDEX idx_metric_type ON Metric(metric_type);
CREATE INDEX idx_timestamp ON Metric(updated_at);

-- Index for Session table
CREATE INDEX idx_session_event ON Session(event_id);


use real_time_event_tracking ; 
desc event ;
select * from event ;

desc session ; 
select * from session ; 

select * from metric ; 


select * from report ;




