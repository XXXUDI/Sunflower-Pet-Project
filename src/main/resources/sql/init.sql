-- MySQL --

CREATE TABLE users (
                       user_id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       password_hash VARCHAR(255) NOT NULL,
                       balance DECIMAL(10, 2) DEFAULT 0.00,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       avatar_url VARCHAR(255)
);

CREATE TABLE communities (
                             community_id INT AUTO_INCREMENT PRIMARY KEY,
                             community_name VARCHAR(100) NOT NULL,
                             owner_id INT NOT NULL,
                             logo_url VARCHAR(255),
                             banner_url VARCHAR(255),
                             description TEXT,
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             FOREIGN KEY (owner_id) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE community_members (
                                   community_id INT NOT NULL,
                                   user_id INT NOT NULL,
                                   role VARCHAR(20) NOT NULL, -- 'creator', 'admin', 'member'
                                   joined_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                   PRIMARY KEY (community_id, user_id),
                                   FOREIGN KEY (community_id) REFERENCES communities(community_id) ON DELETE CASCADE,
                                   FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE chats (
                       chat_id INT AUTO_INCREMENT PRIMARY KEY,
                       chat_name VARCHAR(100) NOT NULL,
                       community_id INT NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       FOREIGN KEY (community_id) REFERENCES communities(community_id) ON DELETE CASCADE
);

CREATE TABLE messages (
                          message_id INT AUTO_INCREMENT PRIMARY KEY,
                          chat_id INT NOT NULL,
                          user_id INT NOT NULL,
                          content TEXT NOT NULL,
                          sent_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          FOREIGN KEY (chat_id) REFERENCES chats(chat_id) ON DELETE CASCADE,
                          FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE posts (
                       post_id INT AUTO_INCREMENT PRIMARY KEY,
                       community_id INT NOT NULL,
                       user_id INT NOT NULL,
                       title VARCHAR(255) NOT NULL,
                       content TEXT NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       FOREIGN KEY (community_id) REFERENCES communities(community_id) ON DELETE CASCADE,
                       FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);
