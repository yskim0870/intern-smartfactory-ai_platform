#!/bin/bash

echo
echo "##############################################################"
echo "### -------------------- start.sh ------------------------ ###"
echo "### -------------------- start.sh ------------------------ ###"
echo "### -------------------- start.sh ------------------------ ###"
echo "##############################################################"

usage(){
	echo
	echo ">>> CALLED BY [[ $1 ]]"
	echo
	echo "[Usage]"
	echo
	echo "./start.sh [-h] [-jdwp]"
	echo
	echo "[Option]"
	echo " -h, --help   : 도움말"
	echo " -jdwp        : (optional) 설정되는 경우 원격디버깅 포트 개방"
	echo
}


JDWP=0
## 파라미터 읽기
while [ "$1" != "" ]; do
	case $1 in
		-jdwp)
			JDWP=1
			;;
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

INSALL_DIR="${install.dir}"
cd ${INSALL_DIR}

## Java 확인
JAVA_PATH=$(command -v java)
if [ ! ${JAVA_PATH} ];
then
	echo "\${JAVA_PATH} is null."
	echo "Need JDK/JRE 1.8 or higher"

	exit 1
fi

APP_NAME="${application.name}"
EXEC_FILE="${execution.filename}"
EXEC_OPTS="${execution.opts}"

echo
echo "=============================================================================================="
echo "APP_NAME     : ${APP_NAME}"
echo "DIRECTORY    : ${INSALL_DIR}"
echo "EXEC_FILE    : ${EXEC_FILE}"
echo "EXEC_OPTS    : ${EXEC_OPTS}"
echo "JAVA_PATH    : ${JAVA_PATH}"

#
# Log4j-2.x Making All Loggers Asynchronous
JAVA_OPTS="-DLog4jContextSelector=org.apache.logging.log4j.core.async.AsyncLoggerContextSelector -DAsyncLogger.ThreadNameStrategy=UNCACHED"

# 실행 명령어
EXEC_CMD="nohup ${JAVA_PATH}"


if [ "${JDWP}" = 1 ]
then
	JDWP_OPTS="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address="${jdwp.port}
	
	EXEC_CMD=${EXEC_CMD}" ${JDWP_OPTS}"
fi

# begin: 커스터마이징 !!!
# end: 커스터마이징 !!!

# 프로그램 설정정보
EXEC_CMD=${EXEC_CMD}" -jar -Dname=${APP_NAME} ${JAVA_OPTS} ${EXEC_FILE} ${EXEC_OPTS} > /dev/null 2>&1 &"


{
	eval ${EXEC_CMD}
	echo "[SUCCES] ${EXEC_CMD}"
} || {
	echo "[FAIL] ${EXEC_CMD}"
}

echo
echo "=============================================================================================="

exit 0

