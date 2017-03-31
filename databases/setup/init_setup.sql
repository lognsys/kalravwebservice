#!/bin/sh
#
# @author :  pjd - 17/03/17
# This script initialises all the tables in kalrav databases 
# TODO : configure my.cnf
#

mysql -u kalravdba -p kalrav < users.sql 
mysql -u kalravdba -p kalrav < dramas.sql 
mysql -u kalravdba -p kalrav < groups.sql 
mysql -u kalravdba -p kalrav < ratings.sql 
mysql -u kalravdba -p kalrav < auditoriums.sql 
mysql -u kalravdba -p kalrav < admob_adverts.sql 
mysql -u kalravdba -p kalrav < adverts_types.sql 
