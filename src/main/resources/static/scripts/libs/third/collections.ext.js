// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
// ↓↓↓↓↓↓↓↓↓↓↓↓↓ added 'Array' JavaScript functions↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
/**
 * 
 * @param value
 *            {object}
 * @returns {Boolean}
 */
Array.prototype.contains = function(value) {
	return !(this.containedIndex(value) < 0);
};

Array.prototype.containedIndex = function(value) {
	if (value == undefined || value == null) {
		return -1;
	}

	for (var i = 0; i < this.length; i++) {
		// #1. using '==='. '===' means 'equal value and equal type'.
		if (this[i] === value) {
			return i;
		} else if (typeof value == 'object' && typeof value == 'object') {
			// #2. unsing a 'equals' function.
			// 'euqlas' function is an inherited property, not a direct property.
			if (("equals" in this[i] && "equals" in value //
			&& this[i].equals(value)) //
					|| this[i] == value) {
				return i;
			}
		} else
		// #3. using '=='
		if (this[i] == value) {
			return i;
		}
	}

	return -1;
}

/**
 * 
 * @param value
 *            {object}
 * @returns {Boolean}
 */
Array.prototype.remove = function(value) {

	var index = this.containedIndex(value);

	if (index < 0) {
		return false;
	}

	delete this[index];
	this.splice(index, 1);
	return true;
};

/**
 * 
 * @param from
 *            {number} integer, inclusive
 * @param to
 *            {number} integer, inclusive
 * 
 * @return A new array of cut off.
 */
Array.prototype.cutoff = function(from, to) {
	if (from < 0 || from > to || from + to - 1 > this.length) {
		throw new Error("[Illegal Argument] this.length: " + this.length + ", from: " + from + ", to: " + to);
	}

	return this.splice(from, to - from + 1);
};

/**
 * 
 * @param varArgs
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @since 2015. 1. 8.
 */
Array.prototype.pushIfAvailable = function(varArgs) {
	var f = function(e) {
		return Object.available(e);
	};

	var arr = (arguments.length == 1 && varArgs instanceof Array) ? varArgs : Array.fromArguments(arguments, 0, arguments.length);
	this.pushfilter(f, arr);

	return this;
};

/**
 * 
 * @param ifNotAvailable
 *            {boolean}:
 * @param varArgs
 *            {any}:
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @since 2015. 1. 8.
 */
Array.prototype.pushIfAbsent = function(ifNotAvailable, varArgs) {

	var f = function(e) {
		return (Object.available(e) || Object.available(ifNotAvailable) ? ifNotAvailable : false) && !this.contains(e);
	};

	if (arguments.length == 2 && varArgs instanceof Array) {
		this.pushfilter(f, Array.fromArguments(arguments, 1, arguments.length));
	} else {
		for (var i = 0; i < arguments.length; i++) {
			this.pushfilter(f, arguments[i]);
		}
	}

	return this;
};

/**
 * 
 * @param f
 *            {function}:
 * @param varArgs
 *            {...any}:
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @since 2015. 1. 8.
 */
Array.prototype.pushfilter = function(f, varArgs) {

	var arr = (arguments.length == 2 && varArgs instanceof Array) ? varArgs : Array.fromArguments(arguments, 1, arguments.length);

	for (var i = 0; i < arr.length; i++) {
		if (f.call(this, arr[i])) {
			this.push(arr[i]);
		}
	}

	return this;
};

/**
 * 
 * @param start
 *            {Number}: inclusive index of argumentsParam
 * @param end
 *            {NUmber}: exclusive index of argumentsParam
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @since 2015. 1. 8.
 */
Array.fromArguments = function(argumentsParam, start, end) {
	var arr = [];

	for (var i = start; i < end; i++) {
		arr.push(argumentsParam[i]);
	}

	return arr;
};


Array.prototype.equals = function (array) {
    // if the other array is a falsy value, return
    if (!array)
        return false;

    // compare lengths - can save a lot of time 
    if (this.length != array.length)
        return false;

    for (var i = 0, l=this.length; i < l; i++) {
        // Check if we have nested arrays
        if (this[i] instanceof Array && array[i] instanceof Array) {
            // recurse into the nested arrays
            if (!this[i].equals(array[i]))
                return false;       
        }           
        else if (this[i] != array[i]) { 
            // Warning - two different object instances will never be equal: {x:20} != {x:20}
            return false;   
        }           
    }       
    return true;
}

// ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
// ↑↑↑↑↑↑↑↑↑↑↑↑↑ added 'Array' JavaScript functions↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
// ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
// ↓↓↓↓↓↓↓↓↓↓↓↓↓ new 'Map' JavaScript functions↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

Map = function() {
	if (arguments.length < 1) {
		this.data = {};
	} else {
		this.data = arguments[0];
	}
};

Map.prototype.constructor = function() {
	if (arguments.length < 1) {
		this.data = {};
	} else {
		this.data = arguments[0];
	}
};

Map.prototype.clear = function() {
	this.data = {};
};

/**
 * 
 * @return
 * 
 * @author Park Jun-Hong
 * @since 2011. 12. 20. 오후 2:23:23
 */
Map.prototype.keySet = function() {
	var keySet = new Array();
	for (key in this.data) {
		keySet.push(key);
	}

	return keySet;
};
/**
 * 
 * @return
 * 
 * @author Park Jun-Hong
 * @since 2011. 12. 20. 오후 2:23:27
 */
Map.prototype.values = function() {
	var values = new Array();
	for (key in this.data) {
		if (typeof this.data[key] != "function" //
				|| !Object.available(Map.prototype[key]) // bug fix
		) {
			values.push(this.data[key]);
		}
	}

	return values;
};

/**
 * 예제코드:
 * 
 * <pre>
 * var map = new Map({
 * 	name : &quot;John&quot;,
 * 	age : 18
 * });
 * var entrySet = map.entrySet();
 * 
 * for (var i = 0; i &lt; entrySet.length; i++) {
 * 	var entry = entrySet[i];
 * 	akert(entry.key + &quot;=&quot; + entry.value);
 * }
 * </pre>
 * 
 * @return { key:${key}, value:${value} } 객체를 타입으로 하는 배열을 반환한다.
 * 
 * @author Park Jun-Hong
 * @since 2011. 12. 20. 오후 2:50:50
 */
Map.prototype.entrySet = function() {
	var entrySet = new Array();
	for (_key_ in this.data) {
		entrySet.push({
			key : _key_,
			value : this.data[_key_]
		});
	}

	return entrySet;
};
/**
 * 
 * @param key
 * @return
 * 
 * @author Park Jun-Hong
 * @since 2011. 12. 20. 오후 2:23:19
 */
Map.prototype.containsKey = function(key) {
	for (_key_ in this.data) {
		if (key == _key_) {
			return true;
		}
	}

	return false;
};
/**
 * 
 * @param key
 * @return
 * 
 * @author Park Jun-Hong
 * @since 2011. 12. 20. 오후 2:23:12
 */
Map.prototype.get = function(key) {
	try {
		if (typeof key != "undefined" && key != null) {
			if (this.containsKey(key)) {
				return this.data[key];
			} else {
				return null;
			}
		} else {
			return null;
		}
	} catch (e) {
		return null;
	}
};

/**
 * 
 * @param key
 * @param value
 * @return this
 * 
 * @author Park Jun-Hong
 * @since 2011. 12. 20. 오후 2:25:32
 */
Map.prototype.put = function(key, value) {
	var oldValue = null;
	if (typeof key != "undefined" && key != null) {
		if (this.containsKey(key)) {
			oldValue = this.data[key];
		}
		this.data[key] = value;
	}

	return oldValue;
};

/**
 * 
 * @param key
 * @returns
 */
Map.prototype.remove = function(key) {
	if (!key || key == "undefined" || !this.data[key]) {
		return null;
	}

	removed = this.data[key];

	delete this.data[key];

	return removed;
};

Map.prototype.size = function() {
	return this.values().length;
};

Map.prototype.toString = function() {
	var string = "";

	for (key in this.data) {
		string += key + "=" + this.data[key] + ",";
	}

	if (string.length > 1) {
		return string.substring(0, string.length - 1);
	} else {
		return string;
	}
};

// ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
// ↑↑↑↑↑↑↑↑↑↑↑↑↑ new 'Map' JavaScript functions↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
// ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
// ↓↓↓↓↓↓↓↓↓↓↓↓↓ new 'FIFOMap' JavaScript functions↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
FIFOMap = function() {
	this.map = new Map(arguments);
	this.keys = [];
};

FIFOMap.prototype.clear = function() {
	this.map = new Map();
	this.keys = [];
};

/**
 * 
 * @return
 * 
 * @author Park Jun-Hong
 * @since 2011. 12. 20. 오후 2:23:23
 */
FIFOMap.prototype.keySet = function() {
	var keySet = new Array();

	for (var idx = 0; idx < this.keys.length; idx++) {
		keySet.push(this.keys[idx]);
	}

	return keySet;
};
/**
 * 
 * @return
 * 
 * @author Park Jun-Hong
 * @since 2011. 12. 20. 오후 2:23:27
 */
FIFOMap.prototype.values = function() {
	var values = new Array();

	var key = null;
	for (var idx = 0; idx < this.keys.length; idx++) {
		key = this.keys[idx];
		values.push(this.map.get(key));
	}

	return values;
};

/**
 * 예제코드:
 * 
 * <pre>
 * var map = new FIFOMap({
 * 	name : &quot;John&quot;,
 * 	age : 18
 * });
 * var entrySet = map.entrySet();
 * 
 * for (var i = 0; i &lt; entrySet.length; i++) {
 * 	var entry = entrySet[i];
 * 	akert(entry.key + &quot;=&quot; + entry.value);
 * }
 * </pre>
 * 
 * @return { key:${key}, value:${value} } 객체를 타입으로 하는 배열을 반환한다.
 * 
 * @author Park Jun-Hong
 * @since 2011. 12. 20. 오후 2:50:50
 */
FIFOMap.prototype.entrySet = function() {
	var entrySet = new Array();

	var key = null;
	for (var idx = 0; idx < this.keys.length; idx++) {
		key = this.keys[idx];

		entrySet.push({
			key : key,
			value : this.map.get(key)
		});
	}

	return entrySet;
};
/**
 * 
 * @param key
 * @return
 * 
 * @author Park Jun-Hong
 * @since 2011. 12. 20. 오후 2:23:19
 */
FIFOMap.prototype.containsKey = function(key) {
	var _key = null;

	for (var idx = 0; idx < this.keys.length; idx++) {
		_key = this.keys[idx];

		if (key == _key) {
			return true;
		}
	}

	return false;
};
/**
 * 
 * @param key
 * @return
 * 
 * @author Park Jun-Hong
 * @since 2011. 12. 20. 오후 2:23:12
 */
FIFOMap.prototype.get = function(key) {
	try {
		if (typeof key != "undefined" && key != null) {
			if (this.keys.contains(key)) {
				return this.map.get(key);
			} else {
				return null;
			}
		} else {
			return null;
		}
	} catch (e) {
		return null;
	}
};

/**
 * 
 * @param key
 * @param value
 * @return this
 * 
 * @author Park Jun-Hong
 * @since 2011. 12. 20. 오후 2:25:32
 */
FIFOMap.prototype.put = function(key, value) {
	var oldValue = null;

	if (typeof key != "undefined" && key != null) {
		if (!this.keys.contains(key)) {
			this.keys.push(key);
		}

		oldValue = this.map.put(key, value);
	}

	return oldValue;
};

/**
 * 
 * @param key
 * @returns
 */
FIFOMap.prototype.remove = function(key) {
	if (!key || key == "undefined") {
		return null;
	}

	removed = null;
	if (this.keys.contains(key)) {
		removed = this.map.remove(key);

		this.keys.remove(key);
	}

	return removed;
};

FIFOMap.prototype.size = function() {
	return this.keys.length;
};

FIFOMap.prototype.toString = function() {
	var string = "";

	var key = null;
	for (var idx = 0; idx < this.keys.length; idx++) {
		key = this.keys[idx];

		string += key + "=" + this.map.get(key) + ",";
	}

	if (string.length > 1) {
		return string.substring(0, string.length - 1);
	} else {
		return string;
	}
};

// ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
// ↑↑↑↑↑↑↑↑↑↑↑↑↑ new 'FIFOMap' JavaScript functions↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
// ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
