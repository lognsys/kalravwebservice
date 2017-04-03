#!/bin/sh
#
# @author :  pjd - 17/03/17
# This script initialises all the tables in kalrav databases 
# CHANGE LOG : Added environment.sh for installing scripts on dev/stage/prod 
##

#source envronment file
. ../../conf/environment.sh

mysql kalrav < ${PROJECT_DIR}/database/tables/users.sql 
mysql kalrav < ${PROJECT_DIR}/database/tables/dramas.sql 
mysql kalrav < ${PROJECT_DIR}/database/tables/groups.sql 
mysql kalrav < ${PROJECT_DIR}/database/tables/ratings.sql 
mysql kalrav < ${PROJECT_DIR}/database/tables/auditoriums.sql 
mysql kalrav < ${PROJECT_DIR}/database/tables/admob_adverts.sql 
mysql kalrav < ${PROJECT_DIR}/database/tables/adverts_types.sql 
