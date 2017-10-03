#!/bin/sh
#
# @author :  pjd - 17/03/17
# This script initialises all the testdata in kalrav databases 
# CHANGE LOG : Added environment.sh for installing scripts on dev/stage/prod 
#  
# NOTE : Order of the sql testdata is important. 
# 
##

#source envronment file
. ~/kalravwebservice/conf/environment.sh

#users, dramas, groups
mysql kalrav < ${PROJECT_DIR}/database/testdata/insert_groups.sql 
mysql kalrav < ${PROJECT_DIR}/database/testdata/insert_dramas.sql
mysql kalrav < ${PROJECT_DIR}/database/testdata/insert_users.sql

mysql kalrav < ${PROJECT_DIR}/database/testdata/insert_devices.sql

mysql kalrav < ${PROJECT_DIR}/database/testdata/insert_refer_seat_status.sql
mysql kalrav < ${PROJECT_DIR}/database/testdata/insert_ratings.sql
#mysql kalrav < ${PROJECT_DIR}/database/testdata/adverts_types.sql
#mysql kalrav < ${PROJECT_DIR}/database/testdata/admob_adverts.sql 

#auditoriums
mysql kalrav < ${PROJECT_DIR}/database/testdata/insert_auditoriums.sql
mysql kalrav < ${PROJECT_DIR}/database/testdata/insert_row_seat.sql
mysql kalrav < ${PROJECT_DIR}/database/testdata/insert_dramas_auditoriums.sql
mysql kalrav < ${PROJECT_DIR}/database/testdata/insert_auditoriums_price.sql

mysql kalrav < ${PROJECT_DIR}/database/testdata/insert_users_groups.sql 
#mysql kalrav < ${PROJECT_DIR}/database/testdata/insert_sub_groups.sql
mysql kalrav < ${PROJECT_DIR}/database/testdata/insert_dramas_groups.sql

#roles
#mysql kalrav < ${PROJECT_DIR}/database/testdata/insert_roles.sql 
mysql kalrav < ${PROJECT_DIR}/database/testdata/insert_users_roles.sql 

#removing drama reviews
#mysql kalrav < ${PROJECT_DIR}/database/testdata/insert_users_reviews.sql
 


mysql kalrav < ${PROJECT_DIR}/database/testdata/insert_booking.sql
mysql kalrav < ${PROJECT_DIR}/database/testdata/insert_booked_seats.sql 
mysql kalrav < ${PROJECT_DIR}/database/testdata/insert_notifications.sql 

