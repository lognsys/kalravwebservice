#!/bin/sh
#
# @author :  pjd - 17/03/17
# This script initialises all the tables in kalrav databases 
# CHANGE LOG : Added environment.sh for installing scripts on dev/stage/prod 
##

#source envronment file
. ../../conf/environment.sh


mysql kalrav -e "drop table admob_adverts;"
mysql kalrav -e "drop table adverts_types;"
mysql kalrav -e "drop table all_dramas_seats"
mysql kalrav -e "drop table refer_seat_status"

#auditoriums
mysql kalrav -e "drop table dramas_auditoriums;"
mysql kalrav -e "drop table confirmation_codes;" #depends on auditorium
mysql kalrav -e "drop table row_seat;" #depends on auditoriums
mysql kalrav -e "drop table auditoriums;"

mysql kalrav -e "drop table booking;" #depends on users & drama

#group
mysql kalrav -e "drop table users_groups;"
mysql kalrav -e "drop table sub_groups;"
mysql kalrav -e "drop table dramas_groups;"
mysql kalrav -e "drop table groups;"

#roles
mysql kalrav -e "drop table users_roles;"  #depends on roles
mysql kalrav -e "drop table roles;"  #depends on roles

#main tables
mysql kalrav -e "drop table ratings;" #depends on users & dramas
mysql kalrav -e "drop table dramas;"
mysql kalrav -e "drop table users;"









