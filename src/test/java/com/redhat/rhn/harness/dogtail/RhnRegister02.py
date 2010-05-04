#!/usr/bin/python
from dogtail.procedural import *
from optparse import OptionParser
import sys

#rhn_server = "http://xmlrpc.rhn.webqa.redhat.com/XMLRPC"
#rhn_username = "whayutin"
#rhn_password = "redhat"
#rhn_profile = "DOGTAIL_TEST_02"

parser = OptionParser()
parser.add_option("-s", "--serverURL", dest="rhn_server", action="store", type="string", help="the rhn server URL", metavar="http://server.com/XMLRPC")
parser.add_option("-u", "--username", dest="rhn_username", action="store", type="string", help="the rhn username")
parser.add_option("-p", "--password", dest="rhn_password", action="store", type="string", help="the rhn password")
parser.add_option("-c", "--client-hostname", dest="rhn_profile", action="store", type="string", help="the rhn profile name for the client")


(options, args) = parser.parse_args()
print "variables listed here."
print "rhn_server = " + options.rhn_server
print "rhn_username = " + options.rhn_username
print "rhn_password = " + options.rhn_password
print "rhn_profile = " + options.rhn_profile

run("rhn_register")


focus.application('rhn_register')
focus.dialog('System software updates already set up')
click('Yes, Continue', roleName='push button')
focus.frame('Registering for software updates')
#click('Why Should I Connect to RHN? ...', roleName='push button')
#focus.frame('Why Register')
#click('Red Hat Privacy Statement', roleName='push button')
#focus.frame('Red Hat Privacy Statement')
#click('OK', roleName='push button')
#focus.frame('Why Register')
#click('Take me back to the registration', roleName='push button')
#focus.frame('Registering for software updates')
click('Forward', roleName='push button')
#doDelay(3)
click("I have access to a Red Hat Network Satellite or Red Hat Network Proxy. I'd like to receive software updates from the Satellite or Proxy below:", roleName='radio button')
keyCombo("Tab")
activate('', roleName='text')
keyCombo("<Control>a")
keyCombo("BackSpace")
type(options.rhn_server)
#click('Advanced Network Configuration ...', roleName='push button')
#focus.dialog('Advanced Network Configuration')
#click('I would like to connect to Red Hat Network via an HTTP proxy.', roleName='check box')
#click('I would like to connect to Red Hat Network via an HTTP proxy.', roleName='check box')
#click('Use Authentication with HTTP Proxy:', roleName='check box')
#click('Use Authentication with HTTP Proxy:', roleName='check box')
#click('Close', roleName='push button')
#click('Forward', roleName='push button')
#click('Back', roleName='push button')
click('Forward', roleName='push button')

#error handling
try:
    click('Close', roleName='push button')
    click('Cancel', roleName='push button')
    click('No thanks, I\'ll connect later.', roleName='push button')
    click('Exit software update setup', roleName='push button')
    print "Connection Error: check server url and proxy settings.. exiting"
    #sys.exit(1)
    #keyCombo("<Alt>F4")
    #focus.frame('Registering for software updates')
    #keyCombo("<Alt>F4")
except:
    print "Success: Connection Settings worked, no errors found"

#focus.application('rhn_register')
#focus.frame('Registering for software updates')
type(options.rhn_username)
keyCombo("Tab")
type(options.rhn_password)
click('Forward', roleName='push button')
#error handling
try:
    click('OK', roleName='push button')
    click('Cancel', roleName='push button')
    click('No thanks, I\'ll connect later.', roleName='push button')
    click('Exit software update setup', roleName='push button')
    print "Connection Error: check server url and proxy settings.. exiting"
except:
    print "no errors found, moving on"

focus.application('rhn_register')
#focus.frame('Registering for software updates')
keyCombo("Tab")
keyCombo("BackSpace")
type(options.rhn_profile)

click('Forward', roleName='push button')
click('', roleName='text', raw=True)
click('Forward', roleName='push button')
focus.frame('Finish setting up software updates')
click('Finish', roleName='push button')