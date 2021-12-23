/*
 *  
 *
 * Date  : 2014. 8. 9. 오후 5:53:48
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */
/**
 * 
 * @param obj
 *            (object):
 * @param property
 *            (string): property name
 * @param defaultValue
 *            (any):
 * @returns
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @since 2014. 8. 6.
 */
Object.getPropertyValue = function(obj, property, defaultValue) {
	if (obj != undefined && obj != null && obj.hasOwnProperty(property)) {
		return obj[property];
	} else {
		return defaultValue == undefined ? null : defaultValue;
	}
};

Object.available = function(param) {
	return param != undefined && param != null;
};

Object.availableFunction = function(_function_) {
	return Object.available(_function_) && typeof _function_ == "function";
};

/**
 * 
 * @param srcObj
 *            (object): source object
 * @param tgtObj
 *            (object): target object
 * @param properties
 *            (array of string/string): If a argument is array, the first property value is in a source object, next is
 *            in target object. <br>
 *            If a argument is a string, it means that a property is for a source and a target object.
 * @param defaultValue
 *            (any):
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @since 2014. 8. 8.
 */
Object.transferValue = function(srcObj, tgtObj, properties, defaultValue) {

	var srcProperty = null;
	var tgtProperty = null;

	if (typeof properties == "string") {
		srcProperty = properties;
		tgtProperty = properties;
	} else {
		srcProperty = properties[0];
		tgtProperty = properties[1];
	}

	tgtObj[tgtProperty] = srcObj.hasOwnProperty(srcProperty) ? srcObj[srcProperty] : defaultValue;
};

/**
 * 
 * @param object
 * @param varargs
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @since 2014. 9. 29.
 */
Object.removeProperty = function(object, varargs) {
	if (arguments.length < 2) {
		return;
	}

	var internal = function(object, varargs) {
		if (object instanceof Array) {

			for (var i = 0; i < object.length; i++) {
				internal(object[i], varargs);
			}
		} else if (typeof object == "object") {
			for ( var p in object) {
				if (varargs.contains(p)) {
					object[p] = undefined;
				}
				//
				else if (object[p] instanceof Array) {
					for (var i = 0; i < object[p].length; i++) {
						internal(object[p][i], varargs);
					}
				} //
				else if (typeof object[p] == "object") {
					internal(object[p], varargs);
				}
			}
		}
	}

	var properties = [];
	for (var i = 1; i < arguments.length; i++) {
		properties.push(arguments[i]);
	}

	internal(object, properties);
};

/**
 * 2개의 object가 동일한지 여부를 반환한다.
 * 
 * @param obj1
 *            (object)
 * @param obj2
 *            (object)
 * 
 * @param props
 *            (array) 속성이름 배열
 *            
 * @returns {Boolean}
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @since 2014. 10. 10.
 */
Object.equalObject = function(obj1, obj2, props) {
	if (!obj1 && !obj2) {
		return false;
	}

	if (!obj1 || !obj2) {
		return true;
	}

	var obj1Props = null;
	var obj2Props = null;

	if (Object.isArray(props)) {
		obj1Props = props;
		obj2Props = props;
	} else {
		obj1Props = [];
		for ( var prop in obj1) {
			obj1Props.push(prop);
		}

		obj2Props = [];
		for ( var prop in obj2) {
			if (!obj1Props.contains(prop)) {
				return false;
			}
			obj2Props.push(prop);
		}

		if (obj1Props.length != obj2Props.length) {
			return false;
		}
	}

	var value = null;
	for (var i = 0; i < obj1Props.length; i++) {
		value = obj1[obj1Props[i]];
		if (typeof value == "object") {
			if (!equalObject(value, obj2[obj1Props[i]])) {
				return false;
			}
		} else {
			if (obj1[obj1Props[i]] != obj2[obj1Props[i]]) {
				return false;
			}
		}
	}

	return true;
};

/**
 * 주어진 값이 배열인지 여부를 반환한다.
 * 
 * @param a
 * @returns
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @since 2015. 1. 22.
 */
Object.isArray = function(a) {
	return Object.available(a) && "[object Array]".equalsIgnoreCase(Object.prototype.toString.call(a));
};

/**
 * 주어진 값이 boolean 여부를 반환한다.
 * 
 * @param a
 * @returns
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @since 2015. 1. 22.
 */
Object.isBoolean = function(b) {
	return Object.available(b) && "[object Boolean]".equalsIgnoreCase(Object.prototype.toString.call(b));
};

/**
 * 주어진 값이 함수인지 여부를 반환한다.
 * 
 * @param f
 * @returns
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @since 2015. 1. 22.
 */
Object.isFunction = function(f) {
	return Object.available(f) && "[object Function]".equalsIgnoreCase(Object.prototype.toString.call(f));
};

/**
 * 주어진 값이 숫자인지 여부를 반환한다.
 * 
 * @param n
 * @returns
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @since 2015. 1. 22.
 */
Object.isNumber = function(n) {
	return Object.available(n) && "[object Number]".equalsIgnoreCase(Object.prototype.toString.call(n));
};

/**
 * 주어진 값이 객체인지 여부를 반환한다.
 * 
 * @param o
 * @returns
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @since 2015. 1. 22.
 */
Object.isObject = function(o) {
	return Object.available(o) && "[object Object]".equalsIgnoreCase(Object.prototype.toString.call(o));
};

/**
 * 주어진 값이 문자열인지 여부를 반환한다.
 * 
 * @param s
 * @returns
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @since 2015. 1. 27.
 */
Object.isString = function(s) {
	return Object.available(s) && "[object String]".equalsIgnoreCase(Object.prototype.toString.call(s));
};

/**
 * Date 객체를 'yyyyMMddHHmmss' 포멧으로 변경한다.
 */
Object.defineProperty(Date.prototype, 'YYYYMMDDHHMMSS', {
	value : function() {
		function pad2(n) { // always returns a string
			return (n < 10 ? '0' : '') + n;
		}

		return this.getFullYear() + pad2(this.getMonth() + 1) + pad2(this.getDate()) + pad2(this.getHours()) + pad2(this.getMinutes())
				+ pad2(this.getSeconds());
	}
});

Object.availables = function(object) {
	if (!Object.available(object)) {
		return false;
	}

	if (arguments.length < 2) {
		return true;
	}

	for (var i = 1; i < arguments.length; i++) {
		if (!Object.available(arguments[i])) {
			return false;
		}
	}

	return true;
};

Object.copyObject = function(obj, props) {

	var replacer = null;

	if (arguments.length > 1) {
		var ps = [];
		for (var i = 1; i < arguments.length; i++) {
			ps.push(arguments[i]);
		}

		replacer = function(key, value) {
			if (this.contains(key)) {
				return undefined;
			}

			return value;
		}.bind(ps);
	}

	return JSON.parse(JSON.stringify(obj, replacer));
};