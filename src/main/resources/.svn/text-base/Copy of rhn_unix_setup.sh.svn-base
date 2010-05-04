#!/usr/bin/bash

#constants

PATH=/usr/local/bin:${PATH}
export PATH

SF=sunfreeware.secsup.org
processor=`uname -p`
release=`uname -r`
relshort=`echo ${release} | cut -d\. -f2`
baseurl="ftp://${SF}/pub/solaris/freeware/${processor}/${release}"
sathost=$1
satbase="http://$1"
username=$2
actkey=$3
password=$4
channel=$5
tpp="gzip libgcc openssl zlib bash bind gtk+ less libiconv mktemp ncurses sed vim wget"

ping=`ping $sathost`
pres=$?

#some helpful function declarations

grabPackage () {
	p=$1
	wget -q -O - ${baseurl}/ --passive-ftp | grep \/${p}\- | sort -n | tail -1 | cut -d\" -f2 | xargs -i wget --passive-ftp {}
	
	for q in `ls -1 *.gz`
	do
	   gunzip ${q}
	done
	
	for r in `ls -1`
	do
	   yes | pkgadd -d ${r} all
	   rm -f ${r}
	done
}

installSupportPackages () {
	if [ -d ${SF} ]; then
   	   rm -rf ${SF}
	fi

	if [ ! -d ${SF} ]; then
	   mkdir ${SF}
	fi

	if [ -d ${SF} ]; then
	    cd /${SF}
	    dd if=/dev/zero of=test count=42
	else
	    echo "Cannot change to the ${SF} directory!"
	    exit 1
	fi
	
	#for p in ${tpp}
	#do
	#   wget -q -O - ${baseurl}/ --passive-ftp | grep \/${p}\- | sort -n | tail -1 | cut -d\" -f2 | xargs -i wget --passive-ftp {}
	#done
	
	#for p in `ls -1 *.gz`
	#do
	#   gunzip ${p}
	#done
	
	#for p in `ls -1`
	#do
	#   yes | pkgadd -d ${p} all
	#done
	
	for q in ${tpp}
	do
	   p=`echo $q | sed -e 's/^lib//g'`
	   psun=`pkginfo | grep SUNW${p} | awk '{print $2}'`
	   psmc=`pkginfo | grep SMC${p} | awk '{print $2}'`
	   qsun=`pkginfo | grep SUNW${q} | awk '{print $2}'`
	   qsmc=`pkginfo | grep SMC${q} | awk '{print $2}'`
	   pav=${psun}${psmc}${qsun}${qsmc}
	   if [ "${pav}" -eq "" ]; then
	      echo "Third-Party Package ${q} not Installed!"
	      grabPackage q
	      yes | pkgadd -d *${q}* all
	   fi
	done
}

if [ ${pres} != 0 ]; then
   echo "Problem with $sathost!"
   exit 1
fi

#akwc=`echo ${actkey} | wc -c | awk '{print $1}'`
#if [ "${akwc}" -lt "33" ]; then 
#   echo "Problem with Activation Key ${actkey}!"
#   exit 1
#fi

installSupportPackages

if [ "${processor}" -eq "sparc" ]; then
   crle -c /var/ld/ld.config -l /lib:/usr/lib:/usr/local/lib
elif [ "${processor}" -eq "i386" ]; then
   crle -c /var/ld/ld.config -l /lib:/usr/lib:/usr/local/lib:/usr/sfw/lib
else
   echo "Do not know how to Configure the Library Search Path for ${processor}"
   exit 1
fi

bsfile=`wget -q -O - ${satbase}/pub/ | grep \"rhn\-solaris\-bootstrap\- | grep ${processor}\-sol${relshort}\.tar\.gz | sort -n | tail -1 | cut -d\" -f6` 

wget ${satbase}/pub/${bsfile}

gunzip -cd ${bsfile} | tar -xvf -

cd *${processor}\-sol${relshort}

for p in `ls -1 *.pkg`;
do
   pname=`echo ${p} | cut -d\- -f1`
   yes | pkgadd -d ${p} all
   pinst=`pkginfo | grep ${pname} | awk '{print $2}'`
   if [ "${pinst}" -eq "" ]; then
      echo "Red Hat Package ${pname} not Installed!"
      yes | pkgadd -d ${p} all
   fi
done

for p in `find /usr /opt/redhat/rhn/solaris -type d -name '*bin' -print`;
do
   PATH=${p}:${PATH}
   export PATH
done
echo ${PATH}

for p in `find /usr /opt/redhat/rhn/solaris -type d -name '*man' -print`;
do
   MANPATH=${p}:${MANPATH}
   export MANPATH
done
echo ${MANPATH}

for p in `find /usr /opt/redhat/rhn/solaris -type d -name '*lib' -print`;
do
   LD_LIBRARY_PATH=${p}:${LD_LIBRARY_PATH}
   export LD_LIBRARY_PATH
done
echo ${LD_LIBRARY_PATH}

if [ "${processor}" -eq "sparc" ]; then
   crle -c /var/ld/ld.conifg -l /lib:/usr/lib:/usr/local/lib:/opt/redhat/rhn/solaris/lib
elif [ "${processor}" -eq "i386" ]; then
   crle -c /var/ld/ld.config -l /lib:/usr/lib:/usr/local/lib:/usr/sfw/lib:/opt/redhat/rhn/solaris/lib
else
   echo "Do not know how to Configure the Library Search Path for ${processor}"
   exit 1
fi

wget ${satbase}/pub/RHN-ORG-TRUSTED-SSL-CERT

if [ ! -d /opt/redhat/rhn/solaris/usr/share/rhn/ ]; then
   mkdir -p /opt/redhat/rhn/solaris/usr/share/rhn/
fi

mv RHN-ORG-TRUSTED-SSL-CERT /opt/redhat/rhn/solaris/usr/share/rhn/

cd /opt/redhat/rhn/solaris/etc/sysconfig/rhn/

mv up2date up2date.orig
cp up2date.orig up2date

sed -i -e "s/xmlrpc.rhn.redhat.com/${sathost}/g" up2date
sed -i -e "s/RHNS-CA-CERT/RHN-ORG-TRUSTED-SSL-CERT/g" up2date

diff up2date up2date.orig

echo rhnreg_ks --user ${username} --serverUrl=${satbase}/XMLRPC --activationkey=${actkey} --force

rhnreg_ks --user ${username} --serverUrl=${satbase}/XMLRPC --activationkey=${actkey} --force

cd /${SF}/*${processor}\-sol${relshort}

solaris2mpm *.pkg

rhnpush -v --server ${sathost}/APP --username ${username} --password ${password} -c ${channel} --force *.mpm

echo "Creating Environment File..."

cat <<EOF >/env.sh
#! /usr/bin/env bash

PATH=/usr/local/bin:/opt/redhat/rhn/solaris/bin:/opt/redhat/rhn/solaris/usr/bin:/opt/redhat/rhn/solaris/usr/sbin:${PATH}
export PATH

LD_LIBRARY_PATH=/usr/local/ssl/lib:/usr/local/lib:${LD_LIBRARY_PATH}
export LD_LIBRARY_PATH

MANPATH=/opt/redhat/rhn/solaris/man:${MANPATH}
export MANPATH

EOF
chmod 755 /env.sh
cp /env.sh /.profile

echo "Done"