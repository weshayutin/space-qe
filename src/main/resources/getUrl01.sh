 grep  "\<action path=" /var/tmp/rhn/src/main/resources/struts-config.xml | awk -F'"' '{print "https://'HOSTNAME'/rhn" $2 ".do"}'| sort | uniq > /var/tmp/rhn/src/main/resources/sat.out
