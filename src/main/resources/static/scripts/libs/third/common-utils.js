/*
 * This file is generated under this project, "com.lguplus.stams". 
 *
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @copyright: 
 * @package: 
 * @license: 
 * @url: 
 * @require: 
 * @since: 2015. 8. 25. 오전 11:10:37
 */

/**
 * 
 */
CommonUtils = function() {
};

/**
 * 
 * @param string
 * @returns {Boolean}
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @since 2015. 8. 25.
 */
CommonUtils.availableString = function(string) {
	return Object.available(string) && string.trim().length > 0;
}

/**
 * 비밀번호 요구조건 만족 여부를 반환한다.
 * 
 * @param password
 * @returns {Boolean}
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @since 2015. 8. 25.
 */
CommonUtils.availablePassword = function(password) {

	if (!Object.available(password)) {
		return false;
	}

	password = password.trim();

	if (!/.{8,32}/g.test(password)) {
		return false;
	}

	if (CommonUtils.containsWhiteSpace(password)) {
		return false;
	}

	if (!CommonUtils.containsLowercase(password)) {
		return false;
	}

// if (!CommonUtils.containsUppercase(password)) {
// return false;
// }

	if (!CommonUtils.containsDigit(password)) {
		return false;
	}

	if (!CommonUtils.containsSpecialCharacter(password)) {
		return false;
	}

	return true;
}

/**
 * 알파벳 소문자 포함여부를 반환한다.
 * 
 * @param str
 * @returns {Boolean}
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @since 2015. 8. 25.
 */
CommonUtils.containsLowercase = function(str) {
	return /[a-z]+/g.test(str);
}

/**
 * 알파벳 대문자 포함여부를 반환한다.
 * 
 * @param str
 * @returns {Boolean}
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @since 2015. 8. 25.
 */
CommonUtils.containsUppercase = function(str) {
	return /[A-Z]+/g.test(str);
}

/**
 * 숫자 포함여부를 반환한다.
 * 
 * @param str
 * @returns {Boolean}
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @since 2015. 8. 25.
 */
CommonUtils.containsDigit = function(str) {
	return /[0-9]+/g.test(str);
}

/**
 * 특수문자 포함여부를 반환한다.<br>
 * 
 * <pre>
 * ! @ # \ $ % &circ; &amp; * ( ) _ + -
 * </pre>
 * 
 * @param str
 * @returns {Boolean}
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @since 2015. 8. 25.
 */
CommonUtils.containsSpecialCharacter = function(str) {
	return /[!@#\$%\^&\*\(\)_+-]+/g.test(str);
}

/**
 * whitespace 포함여부를 반환한다.
 * 
 * @param str
 * @returns {Boolean}
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @since 2015. 8. 25.
 */
CommonUtils.containsWhiteSpace = function(str) {
	return /[\s]+/g.test(str);
}

/**
 * 두 개의 비밀번호 조건 및 일치여부를 반환한다.
 * 
 * @param pwd1
 * @param pwd2
 * @returns {Boolean}
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @since 2015. 8. 25.
 */
CommonUtils.matchPassword = function(pwd1, pwd2) {
	return CommonUtils.availablePassword(pwd1) //
			&& CommonUtils.availablePassword(pwd2) //
			&& pwd1 == pwd2;
}

/**
 * email 주소를 검증합니다.
 * 
 * @param email
 * @returns
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @since 2015. 10. 5.
 */
CommonUtils.validateEmail = function(email){
	var regex = /^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$/i;
	return regex.test(email);
}




/**
 * IP를 Decimal 값으로 연산한다.
 */
CommonUtils.ipToDecimal = function (ip) {
    // a not-perfect regex for checking a valid ip address
	// It checks for (1) 4 numbers between 0 and 3 digits each separated by dots
	// (IPv4)
	// or (2) 6 numbers between 0 and 3 digits each separated by dots (IPv6)
	var ipAddressRegEx = /^(\d{0,3}\.){3}.(\d{0,3})$|^(\d{0,3}\.){5}.(\d{0,3})$/;
	var valid = ipAddressRegEx.test(ip);
	if (!valid) {
		return -1;
	}
	var dots = ip.split('.');
	// make sure each value is between 0 and 255
	for (var i = 0; i < dots.length; i++) {
		var dot = dots[i];
		if (dot > 255 || dot < 0) {
			return -1;
		}
	}
	if (dots.length == 4) {
		// IPv4
		return ((((((+dots[0])*256)+(+dots[1]))*256)+(+dots[2]))*256)+(+dots[3]);
	} else if (dots.length == 6) {
		// IPv6
		return ((((((((+dots[0])*256)+(+dots[1]))*256)+(+dots[2]))*256)+(+dots[3])*256)+(+dots[4])*256)+(+dots[5]);
	}
	return -1;
}

CommonUtils.cidrToDecimal = function (cidr) {
	
	if(!cidr){
		return -1;
	}
	var splitedCidr = cidr.split("/");
	if(splitedCidr.length < 2){
		return -1;
	}
	var ip = splitedCidr[0];
	var mask = splitedCidr[1];

	
	var ipAddressRegEx = /^(\d{0,3}\.){3}.(\d{0,3})$|^(\d{0,3}\.){5}.(\d{0,3})$/;
	var valid = ipAddressRegEx.test(ip);
	if (!valid) {
		return -1;
	}
	var dots = ip.split('.');
	// make sure each value is between 0 and 255
	for (var i = 0; i < dots.length; i++) {
		var dot = dots[i];
		if (dot > 255 || dot < 0) {
			return -1;
		}
	}
	if (dots.length == 4) {
		// IPv4
		return ((((((+dots[0])*256)+(+dots[1]))*256)+(+dots[2]))*256)+(+dots[3])+(mask*1);
	} else if (dots.length == 6) {
		// IPv6
		return ((((((((+dots[0])*256)+(+dots[1]))*256)+(+dots[2]))*256)+(+dots[3])*256)+(+dots[4])*256)+(+dots[5])+(mask*1);
	}
	return -1;
}

/**
 * 현재 날짜 반환
 * 
 * @param format
 * @returns
 */
CommonUtils.getCurrentTime = function(format) {
	var result = null;
	var date = new Date();
	
	var year = date.getFullYear().toString();
	var mon = (date.getMonth()+1).toString();
	var date_ = date.getDate().toString();
	var hour = date.getHours().toString();
	var min = date.getMinutes().toString();
	var sec = date.getSeconds().toString();
	
	if(format == "yyyyMMddHHmmss"){
		year = year[1] ? year : "0"+year[0];
		mon = mon[1] ? mon : "0"+mon[0];
		date_ = date_[1] ? date_ : "0"+date_[0];
		hour = hour[1] ? hour : "0"+hour[0];
		min = min[1] ? min : "0"+min[0];
		sec = sec[1] ? sec : "0"+sec[0];
		
		result = year + mon + date_ + hour + min + sec;
		
	}else if(format == "yyyy-MM-dd"){
		year = year[1] ? year : "0"+year[0];
		mon = mon[1] ? mon : "0"+mon[0];
		date_ = date_[1] ? date_ : "0"+date_[0];
		
		result = year + mon + date_;
	}
	
	return result;
};

function getTwoStr( num ){
	if(Number(num) < 10){
		return "0"+num;
	}
	return num;
}

/**
 * Date 포맷(yyyy-MM-dd HH:mm:ss)
 * 
 * @param date
 * @returns
 */
CommonUtils.getDateToString = function( input ) {
	
	if(input == null){
		return null;			
	}
	
	var date = new Date(input);
	
	var year = date.getFullYear();
	year = getTwoStr(year); 
	var mon = date.getMonth() + 1;
	mon = getTwoStr(mon); 
	var day = date.getDate();
	day = getTwoStr(day); 
	
	var hour = date.getHours();
	hour = getTwoStr(hour); 
	var min = date.getMinutes();
	min = getTwoStr(min); 
	var sec = date.getSeconds();
	sec = getTwoStr(sec); 
	
	return "%s-%s-%s %s:%s:%s".format(year, mon, day, hour, min, sec); 
// return "%s-%s-%s".format(year, mon, day);
}

/**
 * 파일명에서 확장자 추출
 */
CommonUtils.getExtensionOfFileName = function(fileName){
	
	var fileNameLength = fileName.length;
	
	var lastDot = fileName.lastIndexOf('.');
	var fileExtension = fileName.substring(lastDot, fileNameLength).toLowerCase();
	
	return fileExtension;
}


CommonUtils.lastSplitedText = function(value, seperate, length) {
	if (!value) {
		return
	}
	
	var returnText = value.split(seperate).pop(-1);
	
	if(returnText.length > length){
		return returnText.substring(0, length) + "..";
	}else{
		return returnText;
	}
}

CommonUtils.noLongerText = function(value, length) {
	if (!value || !value.length) {
		return
	}
	
// if( length < 5) {
// return value;
// }
	
	var returnText = value;
	
	if(returnText.length > length){
		return returnText.substring(0, length) + "..";
	}else{
		return returnText;
	}
}

CommonUtils.capitalize = function(input){
	return (!!input) ? input.charAt(0).toUpperCase() + input.substr(1).toLowerCase() : '';
}

CommonUtils.engineDeleted = function(engineName){

	var engineNameChanged = engineName;
	if(!engineNameChanged ){
		
	}else if(engineName.indexOf("Engine") > 0) {
		engineNameChanged = engineNameChanged.replace("Engine", "");
		
	}else if(engineName.indexOf("engine") > 0){
		engineNameChanged = engineNameChanged.replace("engine", "");
		
	}
	
	return engineNameChanged;
}


CommonUtils.linkSrcDstId = function(linkId){
	return {
			src: linkId.substr(2, 34),
			dst: linkId.substr(38, 34),
	}
}

CommonUtils.getPosition = function(x1, x2, y1, y2){
	
	var angle = Math.atan2(y2 - y1, x2 - x1) * 180 / Math.PI;
	
	var posX = 25 * Math.cos(angle* Math.PI/180);
	var posY = 25 * Math.sin(angle* Math.PI/180);
	
	return {
		x: posX, 
		y: posY
	}
	
}


/**
 * 
 * @param array
 *            배열
 * @param threshold
 *            보관 최대 개수
 */
CommonUtils.removeLast = function(array, threshold) {
	if( !Object.isArray(array) || array.length <= threshold) {
		return ;
	}
	
	array.splice(threshold - 1, 1);
	
}

/**
 * 매칭결과가 true인 것을 제외시킨다. <b>파라미터로 받은 배열이 데이터가 변경된다.</b>
 * 
 * @param array
 *            (array) 배열
 * @param fn
 *            (function) 매칭 함수
 */
CommonUtils.removeIfMatch = function(array, fn) {
	if( !Object.isArray(array)) {
		return;
	}
	
	for(var i=0; i < array.length;) {
		if( fn.call(this, array[i]) ) {
			array.splice(i,1)[0];
		}else {
			i++;
		}
	}
}

/**
 * 매칭결과가 true 것을 새로운 배열로 제공한다.
 * 
 * @param array
 *            (array) 배열
 * @param fn
 *            (function) 매칭 함수
 * 
 */
CommonUtils.getIfMatch = function(array, fn) {
	
	if( !Object.isArray(array)) {
		return [];
	}
	
	var matched = [];
	
	for(var i=0; i < array.length; i++) {
		if( fn.call(this, array[i]) ) {
			matched.push(array[i]);
		}
	}
	
	return matched;
}

/**
 * 조건에 ㅔ맞는 처번째 데이터를 제공한다.
 * 
 * @param array
 *            (array) 배열
 * @param fn
 *            (function) 매칭 함수
 */
CommonUtils.getFirstIfMatch = function(array, fn) {
	
	if( !Object.isArray(array)) {
		return [];
	}
	
	for(var i=0; i < array.length; i++) {
		if( fn.call(this, array[i]) ) {
			return array[i];
		}
	}

	return null;
}


CommonUtils.webkitRelativePathRemoveLast = function(webkitRelativePath){
	var path = webkitRelativePath.split("/");
	if(path == null || "" == path){
		return webkitRelativePath;
	}
	path.pop();
	return path.join("/");
}

/**
 * 동일한 타입의 객체를 대상으로 주어진 항목을 이용하여 정렬기준을 제공한다.
 * 
 * @param obj1
 *            (object) 비교 대상 객체
 * @param obj2
 *            (object) 비교 대상 객체
 * @param ...
 *            (varargs) 비교 기준 properties
 */
CommonUtils.compare = function(obj1, obj2){
	
	if( arguments.length < 2) {
		throw new Error("비교 기준 객체가 존재하지 않습니다.");
	}
	
	var props = [];
	if( arguments.length < 3 ) {
		props = Object.getOwnPropertyNames(obj1);
	}else {
		for(var i=2; i < arguments.length; i++){
			props.push(arguments[i]);
		}
	}
	
	var prop = null;
	var value1 = null;
	var value2 = null;
	var c = -1;
	
	for(var i=0; i < props.length; i++){
		
		prop = props[i];
		
		value1 = obj1[prop]; 
		value2 = obj2[prop];
		
		if( Object.available(value1) && Object.available(value2)) {
			if( Object.isString(value1)) {
				if( (c = value1.localeCompare(value2, "kf", "base")) != 0) {
					return c;
				}
			}else if( Object.isNumber(value1)) {
				if( (c = value1 - value2) != 0) {
					return c;
				}
			}else if( Object.isBoolean(value1) ) {
				// 서로 다르다.
				if( value1 ^ value2  == 1 ) {
					return value1 ? -1 : 1
				}
			}else if( Object.isObject(value1)) {
				if( ( c = CommonUtils.compare(value1, value2)) != 0 ) {
					return c;
				}
			}
		}else if ( Object.available(value1)) {
			return -1;
		}else {
			return 1;
		}
	}
	
	return c;
}

/**
 * 
 * @param arr
 *            (array) 데이터
 * @param fn
 *            비교 함수
 * 
 * <pre>
 *            function(obj, value) {
 *            	...
 *            }
 * </pre>
 * 
 * @parma v 비교값
 */
CommonUtils.get = function(arr, fn, v) {
	
	for(var i=0; i < arr.length; i++){
		
		if( fn.call(this, arr[i], v)) {
			return arr[i];
		}
	}
	
	return null;
}

/**
 * String온 데이터를 (ex. 1|KB)를 byte로 변환한다. (ex. 1024) 
 */
CommonUtils.convertByteStrToByte = function(byteStr) {
	
	if(!byteStr){
		return null;
	}
	
	var splitedByteStr = byteStr.split("|");
	if(splitedByteStr.length < 2){
		return null;
	}
		
	var size = splitedByteStr[0];
	var unit = splitedByteStr[1];
	
	var unitSize = 1;
	switch (unit) {
	case "BYTE":
		break;
	case "KB":
		unitSize = unitSize * 1024;
		break;
	case "MB":
		unitSize = unitSize * 1024 * 1024;
		break;
	case "GB":
		unitSize = unitSize * 1024 * 1024 * 1024; 
		break;
	case "TB":
		unitSize = unitSize * 1024 * 1024 * 1024 * 1024;
		break;
	default:
		break;
	}
	
	return size * unitSize;

}