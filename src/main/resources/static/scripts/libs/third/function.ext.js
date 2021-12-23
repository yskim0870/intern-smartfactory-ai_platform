/*
 * Copyright 2016 Park Jun-Hong_(fafanmama_at_naver_com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * 
 * @param f
 *            (function) 실행될 함수
 * @param self
 *            (any) 함수 내에서 this 로 참조될 정보
 * @param alais
 *            (string) 함수 이름
 * @param verbose
 *            (boolean) stack trace 여부
 */
var FunctionWrapBoundObject = function(f, self, alias, verbose) {
	this.f = f;
	this.fn = alias != null ? alias : f.name;
	this.self = self;
	this.alias = alias == undefined ? null : alias;
	this.verbose = verbose == undefined || verbose == null //
	? false //
	: "[object Boolean]".equalsIgnoreCase(Object.prototype.toString.call(verbose)) //
	? verbose : false;

	this.UUID = function() {
		var self = {};
		var lut = [];
		for (var i = 0; i < 256; i++) {
			lut[i] = (i < 16 ? '0' : '') + (i).toString(16);
		}
		self.generate = function() {
			var d0 = Math.random() * 0xffffffff | 0;
			var d1 = Math.random() * 0xffffffff | 0;
			var d2 = Math.random() * 0xffffffff | 0;
			var d3 = Math.random() * 0xffffffff | 0;
			return lut[d0 & 0xff] + lut[d0 >> 8 & 0xff] + lut[d0 >> 16 & 0xff] + lut[d0 >> 24 & 0xff] + '-' + lut[d1 & 0xff] + lut[d1 >> 8 & 0xff]
					+ '-' + lut[d1 >> 16 & 0x0f | 0x40] + lut[d1 >> 24 & 0xff] + '-' + lut[d2 & 0x3f | 0x80] + lut[d2 >> 8 & 0xff] + '-'
					+ lut[d2 >> 16 & 0xff] + lut[d2 >> 24 & 0xff] + lut[d3 & 0xff] + lut[d3 >> 8 & 0xff] + lut[d3 >> 16 & 0xff]
					+ lut[d3 >> 24 & 0xff];
		}
		return self;
	}();
};

FunctionWrapBoundObject.prototype.name = function() {
	return this.fn;
};

FunctionWrapBoundObject.prototype.uuid = function() {
	return this.UUID.generate();
};

FunctionWrapBoundObject.prototype.stacktrace = function() {
	var obj = {};
	Error.captureStackTrace(obj, this.fn);

	return obj.stack;
};

FunctionWrapBoundObject.prototype.getLocation = function() {
	var stacks = this.stacktrace().split("    at ");
	return stacks[4];
};

/**
 * 
 * @param self
 *            (any) 함수 내에서 this 로 참조될 정보
 * @param alais
 *            (string) 함수 이름
 * @param verbose
 *            (boolean) stack trace 여부
 */
Function.prototype.wrap = function(self, alias, verbose) {
	
	
	var boundObject = new FunctionWrapBoundObject(this, self, alias, verbose);
	
	var obj = {};
//	Error.captureStackTrace(obj, "");
	boundObject.callStack = obj.stack;//.split("    at ");
	
	return function() {
		// begin - for 'debugging'
		var fn = this.name();
		var uuid = this.uuid();
		var stime = Date.now();
		
		console.debug(">>>>>>>>>>", fn + "::" + uuid);
		// end - for 'debugging'
		
		if( this.verbose ) {
			console.debug("\t" + uuid + "::[wrap-stack ] ", this.callStack);
			console.debug("\t" + uuid + "::[stacktrace ] ", this.stacktrace());
			console.debug("\t" + uuid + "::[callocation] " + this.getLocation());
		}

		var params = [];

		for (i = 0; i < arguments.length; i++) {
			params.push(arguments[i]);
		}

		var retVal = this.f.apply(this.self, params);

		// begin - for 'debugging'
		console.debug("<<<<<<<<<<", fn + "::" + uuid + " / " + new Intl.NumberFormat().format(Date.now() - stime) + " ms");// + "\n" + "[location] " + location);
		// end - for 'debugging'

		return retVal;
	}.bind(boundObject);
};