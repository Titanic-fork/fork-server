drop table if exists point_image;
drop table if exists point;
drop table if exists account_goal;
drop table if exists goal;
drop table if exists target_day_of_week;
drop table if exists alarm;
drop table if exists account;

create table account (
        dtype varchar(31) not null,
        account_id bigint not null auto_increment,
        email varchar(255) unique,
        name varchar(255),
        password varchar(255),
        phone_number varchar(255),
        primary key (account_id)
);

create table account_goal (
       id bigint not null auto_increment,
        target_time time,
        account_id bigint,
        ALARM_ID bigint,
        goal_id bigint,
        primary key (id)
);

create table alarm (
       id bigint not null auto_increment,
        alarm_time time,
        alarm_content VARCHAR(500),
        primary key (id)
);

create table goal (
       goal_id bigint not null auto_increment,
        address varchar(255),
        latitude double precision,
        longitude double precision,
        title varchar(255),
        primary key (goal_id)
);

create table point (
       dtype varchar(31) not null,
        point_id bigint not null auto_increment,
        amount integer not null,
        point_content VARCHAR(500),
        created_date datetime,
        approval_date datetime,
        point_status varchar(255),
        account_goal_id bigint,
        primary key (point_id)
);

create table point_image (
       point_id bigint not null,
        point_image_url varchar(255)
);

create table target_day_of_week (
       alarm_id bigint not null,
        day_of_weeks varchar(255)
);