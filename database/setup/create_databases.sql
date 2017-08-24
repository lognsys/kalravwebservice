# create_databases.sql
# 3/16/2017 pjd
# Create database(s) and MySQL user(s) for Kalrav project
# mysql -uroot -p  < create_databases.sql
#
# Note: Elastic IP address hard coded. Needs update for production.
#
# Change history:
#   3/12/2014 dbb drop privileges for paprro jreport testing
#   3/12/2014 dbb drop access to westproto except paprdba@localhost

create database if not exists kalrav default character set utf8 default collate utf8_general_ci;

# Access from CDL desktops for development: 128.48.204.*
# Requires matching firewall rules
create user 'kalravdba'@'localhost' identified by 'kal-dba999dev';
create user 'kalravrw'@'localhost' identified by 'kal-rw8dev';

grant all on kalrav.* to 'kalravdba'@'localhost';
grant SELECT, INSERT, DELETE, UPDATE on kalrav.* to 'kalravdba'@'localhost';

