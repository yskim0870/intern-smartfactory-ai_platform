#!/bin/bash

echo
echo "##############################################################"
echo "### -------------------- stop.sh ------------------------ ###"
echo "### -------------------- stop.sh ------------------------ ###"
echo "### -------------------- stop.sh ------------------------ ###"
echo "##############################################################"

usage(){
	echo
	echo ">>> CALLED BY [[ $1 ]]"
	echo
	echo "[Usage]"
	echo
	echo "./stop.sh "
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


# 프로그램 정보
APP_NAME="${application.name}"
PS_NAME="["${APP_NAME:0:1}"]"${APP_NAME:1}
# PID 선택 관련 'grep' 기반 필터
CUSTOMIZE_FILTER="grep java" 

#  프로세스 조회 명령어
PS_CMD="ps -aef | grep ${PS_NAME} | ${CUSTOMIZE_FILTER} | awk '{print \$2}'"

# $1: target PID
isAlive(){
	pids=$(eval ${PS_CMD})
	
	tpid=""
	for pid in $pids;
	do
		if [ $1 -eq $pid ];
		then
			tpid=$1
			break
		fi
	done

	if [ $tpid ];
	then
		return 1
	else
		return 0
	fi
}

PIDS=$(eval ${PS_CMD})
# kill -l => 15:  SIGTERM
# '-s SIGTERM'과 동일
SIGNAL=-15
for PID in ${PIDS}
do
	kill ${SIGNAL} ${PID}
done

for PID in ${PIDS}
do
	isAlive ${PID}
	IS_ALIVE=$?

	while [ $IS_ALIVE == 1 ]
	do
		isAlive ${PID}
		IS_ALIVE=$?
		sleep 1
	done

	echo "Process is killed. PID: ${PID}"
done

exit 0
