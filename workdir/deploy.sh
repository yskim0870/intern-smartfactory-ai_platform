#!/bin/bash

# $1 {string} variable name
# $2 {any} value
assign(){
    eval $1=\"$2\"
}

usage(){
	echo
	echo ">>> CALLED BY [[ $1 ]]"
	echo
	echo "[Usage]"
	echo
	echo "./deploy.sh -c <configuration>"
	echo
	echo "[Option]"
	echo " -c, --config     : (Optional) 설정파일 절대경로. 기본값: service.properties"
	echo " -h, --help       : 도움말"
	echo
	echo
	echo " ================ OPERATING SYSTEM INFORMATION ================ "
	cat /etc/*-release
	echo " =============================================================== "
}

support_msg(){
	echo 
	echo " ================ OPERATING SYSTEM INFORMATION ================ "
	echo
	echo " Supporting System: CentOS 6, 7 / Ubuntu 16, 18"
	echo
	echo " -------------------------------------------------------------- "
	cat /etc/*-release
	echo " -------------------------------------------------------------- "
	echo " =============================================================== "
}

# 기본설정 파일
CONFIG_FILE="service.properties"

## 파라미터 읽기
while [ "$1" != "" ]; do
	case $1 in
		-c | --config)
			shift
			CONFIG_FILE=$1
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

validate() {
	local INP_VALUE=$1
	local SYS_VALUE=$2
	
	if [ "$INP_VALUE" != "$SYS_VALUE" ]
	then
		echo
		echo
		echo " ============ SYSTEM INFORMATION============ "
		cat /etc/*-release
		echo " =========================================== "
		echo
		echo
		usage "Illegal System Information"
		exit 2
	fi
}

# Assigns a value input by a user to variable.  
# $1 {string} Question Message.
# $2 {string} varaible
read_cli(){
    local variable="$2"
    local confirm="N"
    local answer=""

    while [ "$confirm" != "Y" ];
    do
        echo
        read -p "$1 -> " answer
        read -p "Your answer is '$answer'. Right? [Y/N] -> " confirm
        local confirm=$(echo $confirm | tr [:lower:] [:upper:])
    done

    assign "$variable" "$answer"
}

# Loads OS name and OS version. 
load_os_info(){
	# CentOS 7 or higher, Ubuntu 16 or higher
	local releasefile="/etc/os-release"
	
	if [ -f "$releasefile" ];
	then
		echo
		echo " ---> read $releasefile"
		#OS_NAME=$(cat /etc/os-release | grep -i "^id=" | sed -e "s/\"//g" | sed -e "s/id=//gi" | tr [:upper:] [:lower:])
		#OS_VERSION=$(cat /etc/os-release | grep -i "^VERSION_id=" | sed -e "s/\"//g" | sed -e "s/version_id=//gi" | tr -dc '0-9.' | cut -d \. -f1)
		OS_NAME=$(grep -v "#" /etc/os-release | grep -i "^id=" | sed -e "s/\"//g" | sed -e "s/id=//gi" | tr [:upper:] [:lower:])
		OS_VERSION=$(grep -v "#"  /etc/os-release | grep -i "^VERSION_id=" | sed -e "s/\"//g" | sed -e "s/version_id=//gi" | tr -dc '0-9.' | cut -d \. -f1)
	else
		# CentOS 6
		local releasefile="/etc/centos-release" 
		if [ -f "$releasefile" ];
		then
			echo
			echo " ---> read $releasefile"
			#OS_NAME=$(cat /etc/centos-release | awk {'print $1'} | tr [:upper:] [:lower:])
			#OS_VERSION=$(cat /etc/centos-release | tr -dc '0-9.'|cut -d \. -f1)
			OS_NAME=$(grep -v "#" /etc/centos-release | awk {'print $1'} | tr [:upper:] [:lower:])
			OS_VERSION=$(grep -v "#" /etc/centos-release | tr -dc '0-9.'|cut -d \. -f1)
		else
			support_msg
			echo
			read -p "Insert your OS Name. (See 'OPERATING SYSTEM INFORMATION' above.) " OS_NAME			
			read -p "Insert your OS version (only Major value). (See 'OPERATING SYSTEM INFORMATION' above.) " OS_VERSION
		fi
	fi
	
	echo
	echo "OS Name="${OS_NAME}
	echo "OS Verson="${OS_VERSION}
}

check_centos(){
	echo
	echo "-------- ${FUNCNAME[0]} --------"
	
	#local OSN=$(cat /etc/centos-release | awk {'print $1'} | tr [:upper:] [:lower:])
	local OSN=$(grep -v "#" /etc/centos-release | awk {'print $1'} | tr [:upper:] [:lower:])
	validate ${OS_NAME} $OSN
	
	#local OSV=$(cat /etc/centos-release | tr -dc '0-9.'|cut -d \. -f1)
	local OSV=$(grep -v "#" /etc/centos-release | tr -dc '0-9.'|cut -d \. -f1)
	validate ${OS_VERSION} $OSV
}

check_ubuntu(){
	echo
	echo "-------- ${FUNCNAME[0]} --------"
		
	#local OSN=$(cat /etc/os-release | grep -i "^NAME=" | sed -e "s/\"//g" | sed -e "s/name=//gi" | tr [:upper:] [:lower:])
	local OSN=$(grep -v "#" /etc/os-release | grep -i "^NAME=" | sed -e "s/\"//g" | sed -e "s/name=//gi" | tr [:upper:] [:lower:])
	validate ${OS_NAME} $OSN
		
	#local OSV=$(cat /etc/os-release | grep -i "^VERSION=" | sed -e "s/\"//g" | sed -e "s/version=//gi" | tr -dc '0-9.' | cut -d \. -f1)
	local OSV=$(grep -v "#" /etc/os-release | grep -i "^VERSION=" | sed -e "s/\"//g" | sed -e "s/version=//gi" | tr -dc '0-9.' | cut -d \. -f1)
	validate ${OS_VERSION} $OSV	
}


# $1 {string} Question message.
# $2 {string} "Y"es string.
# $3 {string} "N"o string.
# $4 {string} response variable
yesOrNo(){
	local yesorno_answer=""
	while [ -z $yesorno_answer ] || ( [ "$2" != "$yesorno_answer" ] && [ "$3" != "$yesorno_answer" ] );
	do
		echo
		read -p "$1 [$2/$3] ? " yesorno_answer
			
		local yesorno_answer=$(echo $yesorno_answer | tr [:lower:] [:upper:] )
	done
	
	assign "$4" "$yesorno_answer"
}

# Pattern: ${...}
GLOBAL_REMATCH=""

# $1 {string} string
# $2 {string} regular expression
global_rematch() {
	GLOBAL_REMATCH=""
	local str="$1"
	local regex="$2"
	
	while [[ $str =~ $regex ]];
	do
		if [ -z "$GLOBAL_REMATCH" ];
		then
			GLOBAL_REMATCH="${BASH_REMATCH[1]}"
		else
			GLOBAL_REMATCH="$GLOBAL_REMATCH ${BASH_REMATCH[1]}"
		fi
		local str=${str#*"${BASH_REMATCH[1]}"}
	done
}

## 설정파일 읽기
# $1 {string} file
# $2 {string} prop_name
# $3 {any} default_value
prop(){
	local property=""
	# 1. profile 에 기반한 설정부터 조회 
	if [ ! -z "${1}" ];
	then
		local property=$(grep -v -e "^#" ${1} | grep -e "^${2}\.${PROFILE}=" | cut -d"=" -f2-)
	fi
	
	# 2. profile에 기반한 설정이 없는 경우 기본 설정조회
	if [ -z "${property}" ];
	then
		local property=$(grep -v -e "^#" ${1} | grep -e "^${2}=" | cut -d"=" -f2-)
		
		# 3. 기본설정이 없고 함수 호출시 기본값이 있는 경우
		if [ -z "${property}" ] && [ ! -z "$3" ];
		then
			echo $3
		else
			echo ${property}
		fi
	else
		echo ${property}
	fi
}

#
# @param $1 {string} property name
check_sys_prop(){
	 case "$1" in 
	 	"sys:user.home")
	 		echo ${HOME}
	 		;;
	 	"sys:username")
	 		echo ${USER}
	 		;;
	 	*)
	 		;;
	 esac
}

REGEX_PROP_REF="\\\$\{([^\}]+)\}"
# $1 {string} absolute file path.
# $2 {string} prop_name
# $3 {any} default_value
read_prop(){
	local property=$(prop "$1" "$2" "$3")
	global_rematch "${property}" "$REGEX_PROP_REF"
	
	if [ -z "$GLOBAL_REMATCH" ];
	then
		echo ${property}
	else	
		local references=($(echo $GLOBAL_REMATCH))
		for ref in "${references[@]}";
		do
			# check system property
			local ref_value=$(check_sys_prop ${ref})
			if [ ! -z "${ref_value}" ] ;
			then
				property=${property//\$\{$ref\}/$ref_value}
			else
				local ref_value=$(read_prop "$1" "$ref")
				if [ ! -z "$ref_value" ];
				then
					property=${property//\$\{$ref\}/$ref_value}
				fi
			fi
		done
		echo ${property}
	fi
}


# Replace a old string to a new string.
# $1 {string} file path
# $2 {string} old string
# $3 {string} new string
update_property(){
	# 데이터에 경로구분자(/)가 포함된 경우 변경
	local targetfile=$1
	local oldstr=$2
	local newstr=$3
	local newstr=${newstr//\//\\\/}
	# format of a variable in xxx.service file is ${variable_name}.
	eval "sed -i 's/\${$oldstr}/$newstr/g' ${targetfile}" 
}

# 큰따옴표(") 또는 작은 따옴표(')로 묶은 문자열을 찾는다.
# $1 {string} string
# $2 {string} variable
unwrap_quote(){
    global_rematch "$1" "^\"([^\"]+)\"$"
    if [ -z "$GLOBAL_REMATCH" ];
    then    
        global_rematch "$1" "^'([^']+)'$"
    fi
    
	if [ ! -z "$GLOBAL_REMATCH" ];
	then
		assign "$2" "$GLOBAL_REMATCH"
	fi    
}

# Replace a old string to a new string.
# $1 {string} file path
# $@:1 {any} properties
update_properties(){
	echo
	echo "-------- ${FUNCNAME[0]} --------"
	
	local targetfile="$1"
	local arguments=(${@})
	local prop_value=""
	
	printf "	%-30s = %s\n" "filename" "${targetfile}"
	
	for prop in "${arguments[@]:1}";
	do
		# 큰/작은 따옴표 제거
		unwrap_quote "$prop" "prop"
		
		local prop_value=$(read_prop "${CONFIG_FILE}" "$prop")
		if [ ! -z "$prop_value" ];
		then
			printf "	%-30s = %s\n" "$prop" "$prop_value"
			update_property "${targetfile}" "$prop" "$prop_value"	
		fi
	done
	
	echo "--------------------------------"
}

# check a file exists.
# $1 {string} filepath
# $2 {number} exit value
check_file_then_exit(){
	if [ ! -f "$1" ];
	then
		echo
		echo "[A file DOES NOT EXIST] file="$1
		
		exit $2
	fi
}

# check a directory exists.
# $1 {string} directory path
# $2 {number} exit value
check_dir_then_exit(){
	if [ ! -d "$1" ];
	then
		echo
		echo "[A directory DOES NOT EXIST] directory="$1
		
		exit $2
	fi
}

# Build 프로파일 읽기
# $1 {string} 빌드명
load_profile(){
	local build_name="$1"
	## profile 검증
	if [ -f "./$build_name/.profile" ];
	then
		BUILTIN_PROFILE=$(cat ./$build_name/.profile)
	fi
	
	if [ -z $BUILTIN_PROFILE ];
	then
		echo "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
		echo "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
		echo
		echo "	Cannot find a built-in profile."
		echo "	So cannot verify this installation."
		
		yesOrNo "	Do you want to process this installation" "Y" "N" "ANSWER"
		
		if [ "$ANSWER" == "N" ];
		then
			clean_temp_dir
			
			echo
			echo "	+++ INSTALLATION is interrupted... +++"
			echo "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
			
			exit 0
		fi
		
		read -p "	Please, input a new profile name. Or just push the enter key if no profile !!! " PROFILE
		
		echo "	YOUR PROFILE is ${PROFILE}. "
		echo "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
	else
		PROFILE=$BUILTIN_PROFILE
	fi
}

# $1 {string} full filepath
# $@:1 {any} properties
update_file(){
	local targetfile=$1
	
	if [ -f "${targetfile}" ];
	then
		echo
		echo "[DETECTED] ${targetfile}"
		update_properties $@
	fi
}


#
# 배열에 값이 존재하는지 여부를 제공한다
#
# @param $1 {string} 배열 이름
# @param $2 {any} 찾는 값
# @return 'echo' 0: 없음, 1: 있음
contains() {
	local ar=$1
	local val=$2
	local has=0
	
	for v in $(eval "echo \${${ar}[@]}")
	do
		if [ "${v}" == "${val}" ];
		then
			has=1
			break
		fi
	done
	
	echo ${has}
}

## 소스 디렉토리 안의 파일을 대상 디렉토리로 복사
# @param $1 {string} from directory. (fullpath)
# @param $2 {string} to directory. (fullpath)
# @param $3 {string} 복사대상 식별정보 
copy_files(){
	local source=$1
	local target=$2
	local filecontainer=$3
	local files=$(ls -ap ${source} | grep -v /)
	
	## 'contains' 함수에서 사용하기 위해서 global 변수로 설정
	filesconfig=($(read_prop "${CONFIG_FILE}" "${filecontainer}.configuration.filenames"))
	echo " ... args=$@"	
	echo " ... filesconfig=${filesconfig}"
	for file in ${files};
	do
	{
		local filesconfigprops=""
		if [ -f "${source}/${file}" ];
		then
			# 디렉토리에 포함된 모든 
			if [ "${filesconfig}" == "\*" ];
			then
				filesconfigprops=$(read_prop "${CONFIG_FILE}" "${filecontainer}.configuration.properties")
			elif [ $(contains "filesconfig" ${file}) -eq 1 ];
			then
				filesconfigprops=$(read_prop "${CONFIG_FILE}" "${file}.configuration.properties")
			fi
			
			if [ ! -z "$filesconfigprops" ];
			then
				update_file "${source}/${file}" "$filesconfigprops"
			fi
			
			
			if [ -f ${target} ];
			then
				echo
				echo " ################ INVALID TARGET DIRECTORY ###############"
				echo
				echo " >>> ${target} is a FILE !!!!"
				
				exit 1
			fi
			
			if [ ! -d ${target} ];
			then
				mkdir -p ${target}
			fi
			
			cp ${source}/${file} ${target}/
			echo "[SUCCESS] cp ${source}/${file} ${target}/" 
		else
			echo "[FAIL] ${source}/${file} does NOT EXIST"
		fi
	}||{
		echo
		
		echo
		echo "[Errors] step: 'copy resource file', file: ${file}"
		echo
		
		echo
		
		exit 2
	}
	done 
}

# @param $1 {string} 명령어. start|stop|status|enable|copy|create|remove
handle_by_systemctl(){
	case "$1" in
		enable)
			 svc_name=$2".service"
	
			sudo systemctl enable ${svc_name}
			echo "[SUCCESS] sudo systemctl enable ${svc_name}"
			;;
		copy)
			local svc_file=$2
			local svc_name=$3".service"
			local svc_dir=$4
						
			echo
			sudo cp -f ${svc_file} "${svc_dir}/${svc_name}"
			echo "[SUCCESS] sudo cp -f ${svc_file} ${svc_dir}/${svc_name}"
			;;
		create)
			local svc_name=$2".service"
			local svc_dir=$3
			local tplfile=$4
			local tplstring=$(cat "${tplfile}")
			
			global_rematch "${tplstring}" "$REGEX_PROP_REF"
			
			if [ ! -z "$GLOBAL_REMATCH" ];
			then
				local properties=($(echo $GLOBAL_REMATCH))
				for prop_ref in "${properties[@]}";
				do
					if [ "${prop_ref}" == "service.filepath" ];
					then
						local property="${svc_dir}/${svc_name}"			
					else
						local property=$(read_prop "${CONFIG_FILE}" "${prop_ref}")
					fi
					
					printf "	%-30s = %s\n" "${prop_ref}" "${property}"
					# 데이터에 경로구분자(/)가 포함된 경우 변경
					local property=${property//\//\\\/}
					# format of a variable in xxx.service file is ${variable_name}.
					eval "sed -i 's/\${${prop_ref}}/${property}/g' $tplfile"
				done
			fi
			;;
		remove)
			local svc_name=$2".service"
			local svc_dir=$3
			
			echo
			echo "service: ${svc_dir}/${svc_name}"
			if [ -f "${svc_dir}/${svc_name}" ];
			then
				echo
				echo "FORCE to remove a old 'Service Unit' file."
				echo 
				sudo systemctl disable ${svc_name}
				echo "[SUCCESS] sudo systemctl disable ${svc_name}"
				echo
				sudo rm -f  "${svc_dir}/${svc_name}"	
				echo "[SUCCESS] sudo rm -f  ${svc_dir}/${svc_name}"
			fi		
			;;
		start)
			local svc_name=$2".service"
			
			sudo systemctl start ${svc_name}
			echo
			echo "[SUCCESS] sudo systemctl start ${svc_name}"		
			;;
		status)
			local svc_name=$2".service"
			
			sudo systemctl status -l ${svc_name}
			echo
			echo "[SUCCESS] sudo systemctl status -l ${svc_name}"		
			;;
		stop)
			local svc_name=$2".service"
			
			sudo systemctl stop ${svc_name}
			echo
			echo "[SUCCESS] sudo systemctl stop ${svc_name}"		
			;;
		*)
			echo
			echo "Unsupported command.(= $1)"
			exit 1
			;;
	esac
}

# @param $1 {string} 명령어. start|stop|status|enable|copy|create|remove
handle_by_service(){
	
	case "$1" in
		enable)
			local svc_name=$2
	
			echo
			sudo chkconfig --add ${svc_name}
			echo "sudo chkconfig --add ${svc_name}"
			;;
		copy)
			local svc_file=$2
			local svc_name=$3
			local svc_dir=$4
			
			echo
			sudo cp -f ${svc_file} "${svc_dir}/${svc_name}"
			echo "sudo cp -f ${svc_file} ${svc_dir}/${svc_name}"
			echo
			sudo chmod +x "${svc_dir}/${svc_name}"
			echo "sudo chmod +x ${svc_dir}/${svc_name}"		
			;;
		create)
			local svc_name=$2
			local svc_dir=$3
			local tplfile=$4
			local tplstring=$(cat "${tplfile}")
			
			global_rematch "${tplstring}" "$REGEX_PROP_REF"
			
			if [ ! -z "$GLOBAL_REMATCH" ];
			then
				local properties=($(echo $GLOBAL_REMATCH))
				for prop_ref in "${properties[@]}";
				do
					if [ "${prop_ref}" == "service.filepath" ];
					then
						local property="${svc_dir}/${svc_name}"			
					else
						local property=$(read_prop "${CONFIG_FILE}" "${prop_ref}")
					fi
					
					printf "	%-30s = %s\n" "${prop_ref}" "${property}"
					# 데이터에 경로구분자(/)가 포함된 경우 변경
					local property=${property//\//\\\/}
					# format of a variable in xxx.service file is ${variable_name}.
					eval "sed -i 's/\${${prop_ref}}/${property}/g' $tplfile"
				done
			fi
			
			;;
		remove)
			local svc_name=$2
			local svc_dir=$3
			
			echo "service: ${svc_dir}/${svc_name}"
			if [ -f "${svc_dir}/${svc_name}" ]
			then
				echo "FORCE to remove a old 'Service Unit' file."
				echo 
				sudo chkconfig ${svc_name} off
				echo "sudo chkconfig ${svc_name} off"
				echo
				sudo chkconfig --del ${svc_name}
				echo "sudo chkconfig --del ${svc_name}"
				echo
				sudo rm -f  "${svc_dir}/${svc_name}"
				echo "sudo rm -f  ${svc_dir}/${svc_name}"
			fi			
			;;
		start)
			local svc_name=$2
			
			sudo service ${svc_name} start
			echo "sudo service ${svc_name} start"
			;;
		status)
			local svc_name=$2
	
			echo
			sudo service ${svc_name} status
			echo "sudo service ${svc_name} status"
			;;
		stop)
			local svc_name=$2
			local status_msg=$(service ${svc_name} status | grep [p]id)
			
			if [ "$${status_msg}" != "" ]
			then
				echo
				sudo service ${svc_name} stop
				echo "sudo service ${svc_name} stop"
			else
				echo
				echo "No running"
			fi		
			;;
		*)
			echo
			echo "Unsupported command.(= $1)"
			exit 1
			;;
	esac
}

#
# @param $1 {string} OS name
# @param $2 {number} OS major number
# @param $3 {string} command 
# @param $~ {any} service name과 command에 따라 필요한 정보
handle_service(){

	local os_name="$1"
	local os_version="$2"
	local svc_cmd="$3"
	local svc_cmd_args=${@:4}
	
	echo
	echo " >>> On ${os_name}_${os_version} / ${svc_cmd} ${svc_cmd_args[@]}"
	
	case ${os_name} in
		# CentOS 
		centos)
			case ${os_version} in
				6)
					handle_by_service ${svc_cmd} ${svc_cmd_args}
					;;
				7)
					handle_by_systemctl ${svc_cmd} ${svc_cmd_args}
					;;
				*)
					echo
					echo "Unsupported O.S version. os=${os_name}, version=${os_version}"
					exit 1
					;;
			esac
			;;
		# Red Had
		red) 
			case ${os_version} in
				6)
					handle_by_service ${svc_cmd} ${svc_cmd_args}
					;;
				*)
					echo
					echo "Unsupported O.S version. os=${os_name}, version=${os_version}"
					exit 1
					;;
			esac 
			;;
		# Ubuntu
		ubuntu)
			case ${os_version} in
				16)
					handle_by_systemctl ${svc_cmd} ${svc_cmd_args}
					;;
				18)
					handle_by_systemctl ${svc_cmd} ${svc_cmd_args}
					;;
				*)
					echo
					echo "Unsupported O.S version. os=${os_name}, version=${os_version}"
					exit 1
					;;
			esac
			;;
		*)
			echo "Unsupported O.S version. os=${os_name}"
			exit 1
			;;
	esac
}

# =========================================================
# =========================================================

## 설정파일이 전달되지 않은 경우 종료
if [ -f "${CONFIG_FILE}" ]
then
	echo "[Configurations] ${CONFIG_FILE} FOUND!"
else
	echo "[Configurations] ${CONFIG_FILE} NOT FOUND!"

	usage "No Configuration file"

	exit 0
fi


## 서비스 등록 여부
AS_A_SERVICE=$(read_prop "${CONFIG_FILE}"  "service.registration")
# 서비스로 등록하는 경우
if [ "$AS_A_SERVICE" == "Y" ];
then
	load_os_info
fi


## sudo 필요 여부 확인
SUDO=$(read_prop "${CONFIG_FILE}" "system.sudo")
if [ "$SUDO" == "true" ];
then
	# Check whether a user is one of 'root' and 'sudoers' or not.
	if (( $EUID != 0 ));
	then
		echo
		echo "You MUST run this script as a 'ROOT' or 'sudoers'".
		echo
		
		exit 100
	fi
fi

echo
echo "###############################################################"
echo "### -------------------- deploy.sh ------------------------ ###"
echo "### -------------------- deploy.sh ------------------------ ###"
echo "### -------------------- deploy.sh ------------------------ ###"
echo "###############################################################"


## 설정파일 절대 경로
CUR_DIR=$(pwd)
CONFIG_FILE_PATH=${CUR_DIR}/${CONFIG_FILE}

BUILD_NAME=$(read_prop "${CONFIG_FILE}" "build.name")
BUILD_FILE=$(read_prop "${CONFIG_FILE}" "build.file")
clean_temp_dir(){
	rm -rf ${BUILD_NAME}
}

## 이전 설치파일 디렉토리 삭제
echo
echo "Remove a old directory"
{
	rm -rf ${BUILD_NAME}
	echo "[SUCCESS]  rm -rf ${BUILD_NAME}"
}||{
	echo
	echo " >>>>>>>>>>>>>>> OooooooooooooooPs !!! <<<<<<<<<<<<<< "
	
	clean_temp_dir
	exit 1
}
echo "OK!"

## 설치파일 압축 해제
echo
echo "Extract a new deployment file."
{
	tar -zxf ${BUILD_FILE}
	echo "[SUCCESS]  tar -zxf ${BUILD_FILE}"
	
	load_profile "${BUILD_NAME}"
	
}||{
	echo
	echo " >>>>>>>>>>>>>>> OooooooooooooooPs !!! <<<<<<<<<<<<<< "
	
	clean_temp_dir
	exit 1
}

echo "OK!"

echo
echo " ================================================================================="
echo " >>>>>>>>>>>>>>>>>> INSTALL '${PROFILE}' version >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
echo " >>>>>>>>>>>>>>>>>> INSTALL '${PROFILE}' version >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
echo " >>>>>>>>>>>>>>>>>> INSTALL '${PROFILE}' version >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
echo " ================================================================================="

INST_DIR=$(read_prop "${CONFIG_FILE}" "install.dir")

echo
echo "current_dir: "${CUR_DIR}
echo "install_dir: "${INST_DIR}

echo
echo "This module is installed at ${INST_DIR}/"

## 설치디렉토리가 설정되지 않은 경우 종료
if [ ! ${INST_DIR} ]
then
	echo
	echo "Installation Directory MUST BE SET."
	
	clean_temp_dir
	exit 1
fi

## 현재 디렉토리와 설치 디렉토리가 동일한 경우 종료
if [[ "${INST_DIR}" == "${CUR_DIR}" ]];
then
	echo
	echo "Current directory is equal to a install directory."
	
	clean_temp_dir
	exit 2
fi

# 서비스 명
SVC_NAME=$(read_prop "${CONFIG_FILE}" "service.name")
# 서비스 설치 디렉토리
SVC_DIR=$(read_prop "${CONFIG_FILE}" "service.dir.${OS_NAME}.${OS_VERSION}")
# 서비스로 등록하는 경우
if [ "$AS_A_SERVICE" = "Y" ];
then
	## 기존 서비스 정지
	echo "## 기존 서비스 정지"
	{
		handle_service ${OS_NAME} ${OS_VERSION} "stop" ${SVC_NAME}
	}||{
		echo
		echo " >>>>>>>>>>>>>>> OooooooooooooooPs !!! <<<<<<<<<<<<<< "
		
		clean_temp_dir
		exit 2
	}
fi


## 설치디렉토리 무조건 초기화
echo
echo "Initialize a installation directory" 
{
	if [ -d ${INST_DIR} ];
	then
		rm -rf ${INST_DIR}/*
	else
		rm -rf ${INST_DIR}
		mkdir -p ${INST_DIR}	
	fi
}||{
	echo
	echo "[Error] Fail to create a directory, ${INST_DIR}/" >2
	
	clean_temp_dir
	exit 2
}

echo 
echo ">>> ### copy resource directories ###" 
##
## begin: 디렉토리 복사
## 디렉토리를 복사하면서 특정 파일에 대한 별도 처리를 추가할 수 있다. 
RES_DIRS=($(read_prop "${CONFIG_FILE}" "resources.directories"))
for dir_name in "${RES_DIRS[@]}";
do
{
	res_dir="${BUILD_NAME}/${dir_name}"
	if [  -d "${res_dir}" ]; then
		copy_files ${res_dir} "${INST_DIR}/${dir_name}" "${dir_name}"
	else
		echo "[FAIL] ${res_dir} does NOT EXIST !!!"
	fi
}||{
	echo
	echo "[Errors] step: 'copy resource directories', directory: $res_dir"
	
	exit 2
}
done
echo "<<<"
## end: 디렉토리 복사

echo 
echo ">>> ### copy resoureces files ###" 
## 모듈 관련 파일 복사 
copy_files ${BUILD_NAME} ${INST_DIR} "files"
echo "<<<"

echo
echo "###########################################################################################"
echo "### -------------------- Install '${BUILD_NAME}' as a Service  ------------------------ ###"
echo "### -------------------- Install '${BUILD_NAME}' as a Service  ------------------------ ###"
echo "### -------------------- Install '${BUILD_NAME}' as a Service  ------------------------ ###"
echo "###########################################################################################"

INST_MOD_DIR=$(read_prop "${CONFIG_FILE}" "install.module.directory")
SVC_TEMPLATE_FILE="${BUILD_NAME}/${INST_MOD_DIR}/${OS_NAME}/${OS_VERSION}/service.template"
# 서비스로 등록하는 경우
if [ "$AS_A_SERVICE" = "Y" ];
then
	echo
	echo "### "$(read_prop "${CONFIG_FILE}" "service.file.description")
	
	# 기존 서비스 삭제
	handle_service ${OS_NAME} ${OS_VERSION} "remove" ${SVC_NAME} ${SVC_DIR}
	
	sleep 0.5
	
	# 서비스 파일 생성
	handle_service ${OS_NAME} ${OS_VERSION} "create" ${SVC_NAME} ${SVC_DIR} "${SVC_TEMPLATE_FILE}"
	
	sleep 0.5
	
	# 서비스 파일 복사
	handle_service ${OS_NAME} ${OS_VERSION} "copy" "${SVC_TEMPLATE_FILE}" ${SVC_NAME} ${SVC_DIR}
	
	sleep 0.5
	
	# 서비스 활성화
	handle_service ${OS_NAME} ${OS_VERSION} "enable" ${SVC_NAME}
	
	sleep 0.5
	
	# 서비스 정지
	handle_service ${OS_NAME} ${OS_VERSION} "stop" ${SVC_NAME}
	
	sleep 1
	
	AUTOSTART=$(read_prop "${CONFIG_FILE}" "service.autostart")
	if [ "$AUTOSTART" = "Y" ];
	then
		# 서비스 시작
		handle_service ${OS_NAME} ${OS_VERSION} "start" ${SVC_NAME}
		
		# 서비스 상태 조회
		#handle_service ${OS_NAME} ${OS_VERSION} "status" ${SVC_NAME}
		_status_cmd_=$(read_prop "${CONFIG_FILE}" "service.file.exec_status")
		eval ${_status_cmd_}
	fi
fi


echo "------------------------------------------------"
echo "------------------------------------------------"
echo "------------------------------------------------"
echo
echo "Bye~"

clean_temp_dir

exit 0
