/*
 * This file is generated under this project, "com.lguplus.stams". 
 *
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @copyright: 
 * @package: 
 * @license: 
 * @url: 
 * @require: 
 * @since: 2015. 8. 5. 오후 2:48:59
 */

NgRouteUtil = function() {
};

/**
 * Angular JS $routeProvider 를 통해서 전달하는 데이터를 ng-controller에서 $route를 통해서 획득하는 것을 지원.
 * 
 * @param route
 *            angular $route module.
 * @param name
 *            property name in $route.resolve property
 * @param params
 *            use for a property if a properly is function.
 * @returns
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @since 2014. 8. 25.
 */
NgRouteUtil.resolve = function(route, name, params) {
	var property = route.current.$$route.resolve[name];

	return typeof property == "function" ? property.call(this, params) : property;
}

NgResourceUtil = function() {
};
NgResourceUtil.errorToStr = function(response) {
	return JSON.stringify(response, function(name, value) {
		if (name == "") {
			value.data = "{{discarded}}";
		}
		return value;
	}, "\t");
};

// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
// ↓↓↓↓↓↓↓↓↓↓↓↓↓'for "ng-style" directive' JavaScript Object ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
Style = function() {
	this.map = new Map();
};

Style.prototype.apply = function(key, value, unit) {
	if (unit) {
		value = value + "" + unit;
	}

	this.map.put(key, value);

	return this;
};

Style.prototype.get = function(key) {
	return this.map.get(key);
};

Style.prototype.clear = function() {
	this.map.clear();
};

Style.prototype.toCSS = function() {
	var style = [];

	var entrySet = this.map.entrySet();
	var entry = null;
	for (var i = 0; i < entrySet.length; i++) {
		entry = entrySet[i];
		style.push(entry.key + ":" + entry.value);
	}

	return style.join(";");
};
// ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
// ↑↑↑↑↑↑↑↑↑↑↑↑↑'for "ng-style" directive' JavaScript Object ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
// ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

NgLogger = function(level) {
	this.level = Object.available(level) ? level : "debug";
};
NgLogger.prototype.setLevel = function(level) {
	if (Object.available(level)) {

		switch (level.toLowerCase()) {
		case "trace": 
		case "debug":
		case "info":
		case "critical":
		case "error":
			this.level = level;
			break;
		default:
			break;
		}
	}
};
NgLogger.prototype.getLevel = function(level) {
	return this.level;
};
NgLogger.prototype.isTraceable = function() {
	return this.level.equalsIgnoreCase("trace");
};
NgLogger.prototype.isDebuggable = function() {
	return this.isTraceable() || this.level.equalsIgnoreCase("debug");
};
NgLogger.prototype.isInfoable = function() {
	return this.isDebuggable() || this.level.equalsIgnoreCase("info");
};
NgLogger.prototype.isCritical = function() {
	return this.isInfoable() || this.level.equalsIgnoreCase("critical");
};
NgLogger.prototype.isErrorable = function() {
	return this.isCritical() || this.level.equalsIgnoreCase("error");
};