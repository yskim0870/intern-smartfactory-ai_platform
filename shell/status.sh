#!/bin/bash

echo
echo "##############################################################"
echo "### -------------------- status.sh ------------------------ ###"
echo "### -------------------- status.sh ------------------------ ###"
echo "### -------------------- status.sh ------------------------ ###"
echo "##############################################################"

usage(){
	echo
	echo ">>> CALLED BY [[ $1 ]]"
	echo
	echo "[Usage]"
	echo
	echo "./status.sh"
	echo
	echo "[Option]"
	echo " -h, --help  : 도움말"
}

## 파라미터 읽기
while [ "$1" != "" ]; do
	case $1 in
		-h | --help) 
			usage "--help"
			exit 0
			;;
		*)
			usage "Invalid option. option: $1"
			exit 1
			;;
	esac
	shift
done

# ###########################################################################

SERVICE_NAME="${service.name}"
APP_NAME="${application.name}"
PS_NAME="["${APP_NAME:0:1}"]"${APP_NAME:1}

PIDS=$(ps -aef | grep ${PS_NAME} | grep [j]ava | awk '{print $2}')
		
if [ -z "${PIDS}" ];
then
	echo
	echo "'${SERVICE_NAME}' daemon is not running..."
	echo
else
	echo
	for pid in ${PIDS[@]}
	do
		echo "'${SERVICE_NAME}' daemon (pid ${pid}) is running..."
	done
	echo
fi

exit 0
