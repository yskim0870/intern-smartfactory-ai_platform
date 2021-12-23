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

UIMasking = function() {
	return {
		activated : false,
		activating : 0,
		release : function() {

			this.activating -= 1

			if (this.activating < 1) {
				this.activated = false;
				this.activating = 0;
			}

			setTimeout(this.resetMessage, 500);

		},
		activate : function() {
			this.activating += 1;

			if (this.activating > 0) {
				this.activated = true;
			}
		},
		resetMessage : function() {
			this.message = "Working ...";
		},
		message : "Working...",
		// callback 함수로 사용할 때
		releaseHandler : function() {
			return this.release.bind(this);
		}
	};
};

/**
 * 
 * @param owner
 *            (any) 이벤트 처리자
 * @param auth
 *            (any) 수신자 인증값
 */
AjaxHandleOwner = function(owner, auth) {
	this.owner = owner;
	this.auth = auth;
};

HandleAfterAjaxOnSuccess = function() {
	// key: 이벤트 처리자, value: 이벤트 처리 결과 수신자
	this.subscribers = new Map();
};

HandleAfterAjaxOnSuccess.prototype.addOwner = function(owner) {
	this.subscribers.put(owner, []);
};

/**
 * @param owner
 *            (any) callback 처리자
 * @param callback
 *            (function) 실행 함수 callback 함수가 전달받는 파라미터는 다음과 같다.
 *            <ul>
 *            <li>Ajax Response Data
 *            <li>Ajax Response Headers
 *            <li>Ajax On-Success Function
 *            </ul>
 * @param validator
 *            (function) 실행여부 확인자
 */
HandleAfterAjaxOnSuccess.prototype.subscribe = function(owner, callback, validator) {
	var array = this.subscribers.get(owner);

	if (!Object.available(array)) {
		console.error("'owner'에 해당하는 배열이 존재하지 않습니다. 'owner'가 추가되지 않은 것 같습니다.", owner);
		return;
	}

	var subscriber = {
		fn : callback,
		val : validator
	};

	array.push(subscriber);
};

/**
 * @parma ajaxHandleOwner (AjaxHandleOwner)
 */
HandleAfterAjaxOnSuccess.prototype.poll = function(ajaxHandleOwner) {

	var owner = ajaxHandleOwner.owner;
	var auth = ajaxHandleOwner.auth;

	var array = this.subscribers.get(owner);

	if (!Object.available(array)) {
		console.error("'owner'에 해당하는 배열이 존재하지 않습니다. 'owner'가 추가되지 않은 것 같습니다.", owner);
		return null;
	}

	if (array.length < 1) {
		// console.debug("'owner'에 등록된 callback 함수가 존재하지 않습니다.", owner);
		return null;
	}

	var fs = [];
	for (i = 0; i < array.length;) {
		// 검증된 것만 추가.
		if (Object.available(array[i].val)) {
			if (array[i].val.call(this, auth)) {
				fs.push(array.splice(i, 1)[0]);
			} else {
				i++;
			}
		} else {
			fs.push(array.splice(i, 1)[0]);
		}
	}

	return fs;
};

/**
 * handle에 연결된 callback 함수를 실행한다.
 * 
 * @param ajaxHandleOwner
 *            (AjaxHandleOwner)
 * @param params
 *            (any) 파라미터
 */
HandleAfterAjaxOnSuccess.prototype.exec = function(ajaxHandleOwner, params) {

	switch (typeof ajaxHandleOwner) {
	case "function":
		ajaxHandleOwner = new AjaxHandleOwner(ajaxHandleOwner);
		break;
	case "object":
		if (!ajaxHandleOwner.hasOwnProperty("owner") || !ajaxHandleOwner.hasOwnProperty("auth")) {
			throw new Error("잘못된 AjaxHandleOwner 정보입니다. 정보: " + ajaxHandleOwner);
		}
		break;
	default:
		throw new Error("잘못된 AjaxHandleOwner 정보입니다. 정보: " + ajaxHandleOwner);
		break;
	}

	var subscribers = this.poll(ajaxHandleOwner);

	if (subscribers == null || subscribers.length < 1) {
		return;
	}

	var args = [];
	for (var i = 1; i < arguments.length; i++) {
		args.push(arguments[i]);
	}

	for (var i = 0; i < subscribers.length; i++) {
		subscribers[i].fn.apply(this, args);
	}
};

/**
 * handle에 연결된 callback 함수를 제거한다.
 * 
 * @param ajaxHandleOwner
 *            (AjaxHandleOwner)
 * @param params
 *            (any) 파라미터
 */
HandleAfterAjaxOnSuccess.prototype.flush = function(ajaxHandleOwner) {

	switch (typeof ajaxHandleOwner) {
	case "function":
		ajaxHandleOwner = new AjaxHandleOwner(ajaxHandleOwner);
		break;
	case "object":
		if (!ajaxHandleOwner.hasOwnProperty("owner") || !ajaxHandleOwner.hasOwnProperty("auth")) {
			throw new Error("잘못된 AjaxHandleOwner 정보입니다. 정보: " + ajaxHandleOwner);
		}
		break;
	default:
		throw new Error("잘못된 AjaxHandleOwner 정보입니다. 정보: " + ajaxHandleOwner);
		break;
	}

	var subscribers = this.poll(ajaxHandleOwner);
};