
DROP DATABASE IF EXISTS clientB;

-- Drop users if they exist

-- Create databases and assign ownership
CREATE DATABASE clientB OWNER notifuser;

-- Grant privileges
GRANT ALL PRIVILEGES ON DATABASE clientA TO notifuser;
GRANT ALL PRIVILEGES ON DATABASE clientB TO notifuser;
