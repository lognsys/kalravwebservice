#!/bin/sh
#
# @author :  pjd - 17/03/17
# This script initialises all the tables in kalrav databases 
# TODO : configure my.cnf
#

mysql -u kalravdba -p kalrav < user.sql 
mysql -u kalravdba -p kalrav < drama.sql 
mysql -u kalravdba -p kalrav < group.sql 
mysql -u kalravdba -p kalrav < ratings.sql 
mysql -u kalravdba -p kalrav < auditorium.sql 
mysql -u kalravdba -p kalrav < admob_adverts.sql 
mysql -u kalravdba -p kalrav < adverts_type.sql 
