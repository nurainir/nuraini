#
# Regular cron jobs for the iin package
#
0 4	* * *	root	[ -x /usr/bin/iin_maintenance ] && /usr/bin/iin_maintenance
