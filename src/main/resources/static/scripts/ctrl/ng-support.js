/**
 * @ProgramName : ng-support.js
 * 
 * Description: This is a xxxxxxxxxxxxxxxxxxxxxxxxxxxxx, and is executed continuously and interrupted Only to perform in
 * case of reset or failure detection.
 * @Package : src/main/webapp/scripts/ctrl
 * @Project : ksb.beeai.webtoolkit
 * @Type : ng-support
 * 
 * @Revision_history: Date : 2017. 9. 14., Author : yskim, Version : 1.0
 * 
 * COPYRIGHT(c) 2016, KSB(Knowledge-converged Super Brain) Convergence Research Department, ETRI.
 * 
 * Opensource License: Copyright (C) 2016 The KSB Open Source Project Licensed under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the
 * License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

var _lastSplitedText = function(value, seperate, length) {
	if (value == null) {
		return
	}
	
	var returnText = value.split(seperate).pop(-1);
	
	if(returnText.length > length){
		return returnText.substring(0, length) + "..";
	}else{
		return returnText;
	}
}

var hidePassword = function(password) {
	let hidedPassword = null;
	if(password == null){
		return hidedPassword;
	}
	
	hidedPassword = "";
	for(i = 0 ; i < password.length ; i++){
		hidedPassword += "*";
	}
	return hidedPassword;
}

//snake to camel convert
const camelToSnakeCase = input => input.replace(/[A-Z]/g, letter => `_${letter.toLowerCase()}`);

// camel to snake convert
const snakeCaseToCamelCase = input => input.split("_").
	reduce((res, word, i) => i === 0 ? word.toLowerCase() : `${res}${word.charAt(0).toUpperCase()}${word.substr(1).toLowerCase()}`,"" );

// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
// ↓↓↓↓↓↓↓↓↓↓↓↓↓'directives for Angular JS' JavaScript Object ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
NgDirective = function() {
	
	/**
	 * 'Enter key Press'
	 */
	this.dlEnterKey = function(){
		return {
			priority : -1,
			restrict : 'A',
			link : function(scope, element, attrs) {
				element.bind('keydown keypress', function(e) {
					var keyCode = e.which || e.keyCode;
					if( keyCode == 13 ) {
						var message = attrs.dlConfirmEnter;
						if (message && !confirm(message)) {
							e.stopImmediatePropagation();
							e.preventDefault();
							
							return;
						}
						
						scope.$apply(function(){
							scope.$eval(attrs.dlEnterKey);
						});
						
						event.preventDefault();
					}
				});
			}
		};
	};
	
	/**
	 * 'Number key Press'
	 */
	this.dlNumberKey = function(){
		return {
			priority : -1,
			restrict : 'A',
			link : function(scope, element, attrs) {
				element.bind('keypress', function(e) {
					
					var keyCode = e.which || e.keyCode;
					
					if( keyCode > 47 && keyCode < 59 ) {
						scope.$apply(function(){
							scope.$eval(attrs.dlNumberKey);
						});
					}
				});
			}
		};
	};
	
	/**
	 * directive: ngConfirmClick
	 */
	this.ngConfirmClick = function() {
		return {
			priority : -1,
			restrict : 'A',
			link : function(scope, element, attrs) {
				element.bind('click', function(e) {
					var message = attrs.ngConfirmClick;
					if (message && !confirm(message)) {
						e.stopImmediatePropagation();
						e.preventDefault();
					}
				});
			}
		}
	};

	/**
	 * directive: ngValidateClick
	 */
	this.ngValidateClick = function() {
		return {
			priority : -1,
			restrict : 'A',
			link : function($scope, element, attrs) {
				element.bind('click', function(e) {
					var values = attrs.ngValidateClick.split(",");
					if (values.length < 1) {
						return;
					}

					for (var i = 0; i < values.length; i++) {
						values[i] = values[i].trim();
					}

					var validator = $scope[attrs.validator];

					var unvalid = [];

					if (isArray(values)) {
						values.forEach(function(value) {
							if (Object.available(validator) && !validator(value)) {
								unvalid.push(value);
							}
						});
					}

					if (unvalid.length > 0 && !confirm("다음 정보들이 설정되지 않았습니다. 진행을 하겠습니까?\n\n설정대상: " + unvalid.join(", "))) {
						e.stopImmediatePropagation();
						e.preventDefault();
					}
				});
			}
		}
	};

	this.draggable = function() {
		return {
			restrict : "A",
			link : function(scope, elem, attr) {
				jQuery(elem).draggable();
			}
		}
	};
	
	this.resizable = function() {
		return {
			restrict : "A",
			link : function(scope, elem, attr) {
				jQuery(elem).resizable({
					  handles: "se"
				});
			}
		}
	};

	this.stopPropagationEvent = function() {
		return {
			restrict : "A",
			link : function(scope, elem, attr) {
				jQuery(elem).bind(attr.stopPropagationEvent, null, function(event) {
					event.stopPropagation();
				});
			}
		}
	};

	// modal draggable
	this.draggableOnlyPopHeader = function() {
		return {
			restrict : "A",
			link : function(scope, elem, attr) {
				// ngTransclude가 tab 쪽에서 적용되어서 dialog에만 적용시키도록.
				if ("modal-content" === elem.attr("class")) {
					jQuery(elem).draggable();
				}
			}
		}
	};

	this.onReadFile = function($parse) {
		return {
			restrict : 'A',
			scope : false,
			link : function(scope, element, attrs) {
				var fn = $parse(attrs.onReadFile);

				element.on('change', function(onChangeEvent) {
					var reader = new FileReader();

					reader.onload = function(onLoadEvent) {
						scope.$apply(function() {
							fn(scope, {
								$fileContent : onLoadEvent.target.result
							});
						});
					};
					
					try{
						reader.readAsText((onChangeEvent.srcElement || onChangeEvent.target).files[0]);
					} catch (ignored) {}
					
				});
			}
		};
	};

	this.file = function() {
		return {
			scope : {
				file : '='
			},
			link : function(scope, el, attrs) {
				el.bind('change', function(event) {

					var file = event.target.files[0];

					scope.file = file ? file : undefined;
					scope.$apply();

				});
			}
		}
	};

	this.onWindowResized = function($timeout) {

		return function(scope, element, attr) {

			// 검색조건 열려있을때의 검색조건 div height
			var uncollapseHeight = 131;
			// 검색조건 닫혀있을때의 검색조건 div height
			var collapseHeight = 55;
			// div의 header 사이즈
			// var tableHeaderHeight= 0;
			var tableHeaderHeight = 30;
			// 각 chart div의 bottom margin
			var chartDivMarginBottom = 10;
			// 
			var padding = 10 * 2;
			var border = 1 * 2;
			// deprecated
			var widthInnerPadding = 22;

			var isCollapsed = false;
			var isWindowMap = {};

			var w = $(window);
			scope
					.$watch(
							function() {
								return {
									'h' : w.height(),
									'w' : w.width()
								};
							},
							function(newValue, oldValue) {

								scope.windowHeight = newValue.h;
								scope.windowWidth = newValue.w;

								var templateDiv = $("#template_content_div");
								var containerDiv = $("#chart_container_div");
								var chartDiv = $("[id^=pktCntChart]");
								isCollapsed = scope.$eval(attr.iscollapsed);
								var chartCnt = Number(scope.$eval(attr.chartcnt)) * 1;
								isWindowMap = scope.$eval(attr.iswindow);

								// init
								containerDiv.css("height", isCollapsed ? scope.windowHeight - collapseHeight : scope.windowHeight - uncollapseHeight);

								var height = isCollapsed ? Math.floor((newValue.h - collapseHeight - padding) / chartCnt) - tableHeaderHeight
										- chartDivMarginBottom - border : Math.floor((newValue.h - uncollapseHeight - padding) / chartCnt)
										- tableHeaderHeight - chartDivMarginBottom - border;

								scope.resizeChartDiv = function(divId, existHeaderFlag, item) {

									var oldIsCollapsed = isCollapsed;

									chartCnt = Number(scope.$eval(attr.chartcnt)) * 1;
									isCollapsed = scope.$eval(attr.iscollapsed);
									isWindowMap = scope.$eval(attr.iswindow);

									if (!existHeaderFlag) { // 실시간 모니터링

										var isWindowCnt = 0;
										for ( var key in isWindowMap) {
											if (isWindowMap[key]) {
												isWindowCnt++;
											}
										}

										if (isWindowMap[item]) {

											height = isCollapsed ? Math
													.floor((newValue.h - collapseHeight - padding - (tableHeaderHeight * chartCnt) - (chartDivMarginBottom * (chartCnt - isWindowCnt)))
															/ isWindowCnt)
													- chartDivMarginBottom - border
													: Math
															.floor((newValue.h - uncollapseHeight - padding - (tableHeaderHeight * chartCnt) - (chartDivMarginBottom * (chartCnt - isWindowCnt)))
																	/ isWindowCnt)
															- chartDivMarginBottom - border;

										} else {
											height = 0;

										}

									} else { // E2E 연동 이력 페이지

										height = isCollapsed ? Math.floor((newValue.h - collapseHeight - padding) / chartCnt) - chartDivMarginBottom
												- border : Math.floor((newValue.h - uncollapseHeight - padding) / chartCnt) - chartDivMarginBottom
												- border;

									}

									return {
										'height' : height + 'px'
									};
								};

							}, true);

			function fireResize() {
				if (document.createEvent) { // W3C
					var ev = document.createEvent('Event');
					ev.initEvent('resize', true, true);
					window.dispatchEvent(ev);
				} else { // IE
					element = document.documentElement;
					var event = document.createEventObject();
					element.fireEvent("onresize", event);
				}
			}
			;

			scope.$watch(function() {
				return isCollapsed
			}, function(newValue, oldValue) {
				if (newValue == oldValue) {
					return;
				}
				// isCollapsed 변경 될 경우, window resize 이벤트를 강제로 발생
				$timeout(fireResize, 250);
			}, true);

			scope.$watch(function() {
				return isWindowMap
			}, function(newValue, oldValue) {
				if (newValue == oldValue) {
					return;
				}
				$timeout(fireResize, 0);
			}, true);

			w.bind('resize', function() {
				scope.$apply();
			});

		}

	}

	this.numberformat = function($filter) {
		return {

			require : '?ngModel',
			link : function(scope, elem, attrs, ctrl) {
				if (!ctrl) {
					return;
				}

				ctrl.$formatters.unshift(function() {
					return $filter('number')(ctrl.$modelValue);
				});

				ctrl.$parsers.unshift(function(viewValue) {
					var plainNumber = viewValue.replace(/[\,\.]/g, ''), b = $filter('number')(plainNumber);

					elem.val(b);

					return plainNumber;
				});
			}

		}
	};
	
	
	/**
	 * textarea 에서 tab 을 허용하기 위함.
	 */
	this.ngAllowTab = function(){
		return {
			priority : -1,
			restrict : 'A',
			link : function(scope, element, attrs) {
				element.bind('keydown', function (event) {
		            if (event.which == 9) {
		                event.preventDefault();
		                
		                var start = this.selectionStart;
		                var end = this.selectionEnd;
		                
		                element.val(element.val().substring(0, start) 
		                    + "   " + element.val().substring(end));
		                
		                this.selectionStart = this.selectionEnd = start + 1;
		                
		                element.triggerHandler('change');
		            }
		        });
			}
		};
	};
	
	/**
	 * mouse right button click event
	 */
	this.ngRightClick = function($parse){
		 return function(scope, element, attrs) {
		        var fn = $parse(attrs.ngRightClick);
		        element.bind('contextmenu', function(event) {
		            scope.$apply(function() {
		                event.preventDefault();
		                fn(scope, {$event:event});
		            });
		        });
		    };
	};
	
	
	
	//This function will fire an event when the container/document is scrolled to the bottom of the page
	this.onScrollToBottom = function ($document) {
	    return function (scope, element, attrs) {
            element.bind("scroll", function () {
            	
            	let elem = element[0];
            	
            	// scroll이 바닥일때
                if (elem.scrollTop + elem.offsetHeight >= elem.scrollHeight) {
                    //run the event that was passed through
                    scope.$apply(attrs.onScrollToBottom);
                } 
            });
        }
	}
	
};
// ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
// ↑↑↑↑↑↑↑↑↑↑↑↑↑'directives for Angular JS' JavaScript Object ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
// ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
// ↓↓↓↓↓↓↓↓↓↓↓↓↓'factories for Angular JS' JavaScript Object ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
NgFactory = function() {
	this.colorPicker = function() {
		return {
			pickOddEven : function(i) {
				return i % 2 == 0 ? "#FFFFFF" : "#fbf5f9 !important";
			},

			bgColor : function(valid) {
				return valid ? "#85FF85" : "#FFEBF5";
			},
		}
	};

	this.emailChecker = function() {
		return {
			regex : /^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$/i,
			check : function(email) {
				return this.regex.test(email);
			}
		};
	};

	this.eventQueue = function() {
		var EQF = {
			eventListeners : [],
			register : function(listener) {
				if (!EQF.eventListeners.contains(listener)) {
					EQF.eventListeners.push(listener);

					console.log("[Drop Listener Register] (registered) " + listener.toString());
				}
			},
			unregister : function(listener) {
				var unregistered = EQF.eventListeners.remove(listener);

				if (unregistered) {
					console.log("[Drop Listener Register] (unregistered) " + listener.toString());
				}
			},
			fireEvent : function(eventType, data, loc) {
				EQF.eventListeners.forEach(function(l) {
					if (Object.available(loc)) {
						var elem = document.getElementById(l.id);
						if (Object.available(elem)) {
							var elemDim = new Rectangle(elem.offsetLeft, elem.offsetTop, elem.offsetWidth, elem.offsetHeight);

							if (elemDim.intersectPoint(loc.x, loc.y)) {
								l.listen(eventType, data);
							}
						}
					} else {
						l.listen(eventType, data);
					}

				});
			}
		};

		return EQF;
	};

	// from배열에서 to배열로 array 이동시키기
	this.transfer = function() {
		var __factory__ = {
			transfer : function(from, to, array) {
				array.forEach(function(element, index, total) {
					to.push(element);
					from.remove(element);
				});
			}
		};

		return __factory__;
	};

	// BEGIN: 팝업 띄우기
	// 2015.08.11. 김영석
	this.newWindow = function() {
		var __factory__ = {
			open : function(content, templateUrl, target, options) {

				if (options == null || options == undefined || options == "") {
					options = "width=1000, height=600, location=no,directories=no,titlebar=0,menubar=no,resizable=yes,scrollbars=yes;"; // 팝업창
					// 옵션(optoin)
				}

				var url = ctx;
				if (templateUrl != undefined && templateUrl != "") {
					url += "/popup?templateUrl=" + templateUrl;
				}
				if (content.key != undefined) {
					url += "&key=" + content.key;
				}
				if (content.type != undefined && content.type != "") {
					url += "&type=" + content.type;
				}
				if (content.data != undefined && content.data != "") {
					url += "&data=" + encodeURI(encodeURIComponent(JSON.stringify(content.data)));
				}

				var newWindowRef = window.open(url, target, options);

			}
		};

		return __factory__;
	};

	// 단위 변환
	this.convertUnits = function() {
		var __factory__ = {
			convert : function(value) {
				var sizes = [ ' B', ' KiB', ' MiB', ' GiB', ' TiB', ' PiB', ' EiB', ' ZiB', ' YiB' ];
				if(value == null){
					return null;
				}
				if (value == 0) {
					return {
						value : 0,
						unit : ''
					};
				}
				var i = parseInt(Math.floor(Math.log(value) / Math.log(1024)));
				return {
					value : (value / Math.pow(1024, i)).toFixed(1) * 1 + sizes[i],
					unit : sizes[i]
				};
			}
		};

		return __factory__;
	};

	this.mWebSocket = function($location) {
		var __factory__ = {
			COMMAND : {

				LISTEN_REALTIME_PACKET_COUNT : "listen-realtime-packet_count",
				LISTEN_REALTIME_RESPONSE_RATE : "listen-realtime-response_rate",
				LISTEN_REALTIME_IP_ASSIGNMENT_RATE : "listen-realtime-ip_assignment_rate",

				LISTEN_HISTORY_PACKET_COUNT : "listen-history-packet_count",
				LISTEN_HISTORY_RESPONSE_RATE : "listen-history-response_rate",
				LISTEN_HISTORY_IP_ASSIGNMENT_RATE : "listen-history-ip_assignment_rate",

				UPDATE_REALTIME_THRESHOLD_PACKET_COUNT : "update-realtime_threshold-packet_count",
				UPDATE_REALTIME_THRESHOLD_RESPONSE_RATE : "update-realtime_threshold-response_rate",
				UPDATE_REALTIME_THRESHOLD_IP_ASSIGNMENT_RATE : "update-realtime_threshold-ip_assignment_rate",

			},
			// url : "ws://" + $location.$$host + ":" + $location.$$port + ctx + "/websocket/monitoring/listen",
			url : ($location.$$protocol == "https" ? "wss" : "ws") + "://" + $location.$$host + ":" + $location.$$port + ctx
					+ "/websocket/monitoring/listen",
			websocket : null,
			/**
			 * @param $scope
			 *            {object}: Angular JS Scope
			 * @param fnOnOpen
			 *            {function}: callback function for 'websocket is opened.'
			 * @param fnOnMessage
			 *            {function}: callback function for 'websocket receives a message.'
			 * @param fnOnClose
			 *            {function}: callback function for 'websocket is closed.'
			 * @param fnOnError
			 *            {function}: callback function for 'a error caused on websocket.'
			 * 
			 * 
			 */
			connect : function($scope, fnOnOpen, fnOnMessage, fnOnClose, fnOnError) {
				var websocket = new WebSocket(__factory__.url);

				websocket.onopen = fnOnOpen;
				websocket.onmessage = fnOnMessage;

				if (Object.available(fnOnClose)) {
					websocket.onclose = fnOnClose;
				}

				if (Object.available(fnOnError)) {
					websocket.onerror = fnOnError;
				}

				return __factory__.websocket = websocket;
			},
			close : function($scope) {
				if (this.websocket) {
					this.websocket.close();
				}

				this.websocket = null;
			},
			send : function($scope, data) {
				if (this.websocket) {
					this.websocket.send(data);
				}
			}
		};

		return __factory__;
	};
	
	this.lastSplitedText = function() {
		var __factory__ = {
			lastSplitedText : _lastSplitedText
		};

		return __factory__;
	};
	
};

// ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
// ↑↑↑↑↑↑↑↑↑↑↑↑↑'factories for Angular JS' JavaScript Object ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
// ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
// ↓↓↓↓↓↓↓↓↓↓↓↓↓'filters for Angular JS' JavaScript Object ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
NgFilter = function() {
	/**
	 * name: yesNo
	 */
	this.yesNo = function() {
		return function(trueFalse) {
			return trueFalse ? 'Yes' : 'No';
		};
	};
	this.upDown = function() {
		return function(trueFalse) {
			return trueFalse ? 'Up' : 'Down';
		};
	};
	this.enabledDisabled = function() {
		return function(trueFalse) {
			return trueFalse ? 'Enabled' : 'Disabled';
		};
	};
	this.activeDown = function() {
		return function(trueFalse) {
			return trueFalse ? 'Active' : 'Down';
		};
	};

	this.nullToDash = function() {
		return function(value) {
			return Object.available(value) ? value : "-";
		}
	};
	this.trueFalse = function() {
		return function(trueFalse) {
			return trueFalse ? 'true' : 'false';
		};
	};
	this.prettyString = function() {
		return function(v) {
			var serializeObject = function(obj, indent) {
				var result = [];

				var v = null;
				for ( var prop in obj) {
					v = obj[prop];
					if (Object.isArray(v)) {
						v = serializeArray(v, indent + " ");
					} else if (Object.isObject(v)) {
						v = serializeObject(v, indent + " ");
					}

					result.push(indent + prop + ": " + v);
				}

				return result.join("\n");

			};

			var serializeArray = function(arr, indent) {
				var result = [];

				var v = null;
				for (var i = 0; i < arr.length; i++) {
					v = arr[i];

					if (Object.isArray(v)) {
						v = serializeArray(v, indent);
					} else if (Object.isObject(v)) {
						v = serializeObject(v, indent);
					}

					result.push(indent + v);
				}

				return result.join("\n");
			};

			if (Object.isArray(v)) {
				return serializeArray(v, "");
			}

			if (Object.isObject(v)) {
				return serializeObject(v, "");
			}

			return v;
		}
	};

	this.mlFilter = function() {
		return function(text) {
			if (text !== undefined && text !== null)
				return text.replace(/\n/g, '<br>');
		};
	}

	// pagination
	this.startFrom = function() {
		return function(input, start) {
			if (input) {
				start = +start; // parse to int
				return input.slice(start);
			}
			return [];
		}
	};

	// 단위변환
	this.convertUnits = function() {
		return function(input) {
			var sizes = [ ' B', ' KiB', ' MiB', ' GiB', ' TiB', ' PiB', ' EiB', ' ZiB', ' YiB' ];
			if(input == null){
				return null;
			}
			if (input == 0) {
				// return;
				return '0';
			}
			var i = parseInt(Math.floor(Math.log(input) / Math.log(1024)));
			return (input / Math.pow(1024, i)).toFixed(1) * 1 + sizes[i];
		}
	};

	// number, 0 -> 00
	this.numberFixedLen = function() {
		return function(n, len) {
			var num = parseInt(n, 10);
			len = parseInt(len, 10);
			if (isNaN(num) || isNaN(len)) {
				return n;
			}
			num = '' + num;
			while (num.length < len) {
				num = '0' + num;
			}
			return num;
		};
	};

	this.limitToUseEscape = function() {
		return function(str, limit) {

			var len = 0;
			for (var i = 0; i < limit; i++) {
				if (escape(str.charAt(i)).length == 6) {
					len++;
				}
				len++;
			}

			return str.substring(0, len);

		}
	};

	this.agencyFilter = function() {
		// input : 필터링 되는 배열, prefix : 입력받는 값
		return function(input, prefix) {
			if (!Object.available(input)) {
				return [];
			}

			var filterIn = [];

			input.forEach(function(elem) {
				if (Object.available(elem.name)//
						&& elem.name.startsWith(prefix)) {
					this.push(elem);
				}
			}, filterIn);

			return filterIn;
		}
	};

	this.salesTeamFilter = function() {
		// input : 필터링 되는 배열, prefix : 입력받는 값
		return function(input, prefix) {
			if (!Object.available(input)) {
				return [];
			}

			var filterIn = [];

			input.forEach(function(elem) {
				if (Object.available(elem)//
						&& elem.startsWith(prefix)) {
					this.push(elem);
				}
			}, filterIn);

			return filterIn;
		}
	};

	this.lastSplitedText = function() {
		return _lastSplitedText;
	};
	
	this.noLongerText = function() {
		return CommonUtils.noLongerText;
	};

	/**
	 * @param number:
	 *            숫자값. 정수 또는 실수
	 * @param fractionSize:
	 *            실수인 경우 소숫점 이하 자리수
	 */
	this.numberEx = function(){
		return function(number, fractionSize) {
			if( Number.isInteger(number)) {				
				return number;
			}else {
				return new Number(number).toFixed(fractionSize);
			}
		};
	};
	
	// String의 첫문자를 대문자로 표시하는 Filter
	this.capitalize = function(){
		return CommonUtils.capitalize;
	};
	
	// String에 'Engine'이 있으면 삭제한다.
	this.engineDeleted = function(){
		return CommonUtils.engineDeleted;
	};
	
	/**
	 * 데이터의 index가 기준 index 보다 크거가 같은지를 제공한다.
	 * 
	 * @param index
	 *            데이터 index
	 * @param after
	 *            기준 index
	 * @param inclusive
	 *            기준 index와 동일한 경우 처리 내용
	 */
	this.greaterOrIndex = function(){
		 return function(array, after, inclusive) {
		        if(array) {
		        	if( !inclusive ) {
		        		after != 1
		        	}
		            return array.slice(after);
		        }else{
		        	return [];
		        }
		    };
	};
	
	this.userActivated = function() {
		return function(userActivatedCode) {
			if(userActivatedCode == 1){
				return "활성화";
			}else if(userActivatedCode == 0){
				return "비활성화";
			}else if(userActivatedCode == -1){
				return "가입요청중";
			}else if(userActivatedCode == 2){
				return "승인요청중";
			}
			
			return "-";
		};
	};
	
	this.titleProperties = function() {
		return function(title, seperate, length) {
			if(title==null){
				return;
			}
			
			var splitedTitle = title.split(seperate);
			console.log("splitedTitle", splitedTitle);
			
			if(length > splitedTitle.length){
				return title;
			}else{
				
				var splitedTitleFront = "";
				for(var i = 0 ; i < splitedTitle.length ; i++){
					if(i < length-1){
						splitedTitleFront += splitedTitle[i] + seperate;
					}
				}
				splitedTitleFront +="\n";
				splitedTitleFront += splitedTitle[splitedTitle.length-1];
				
				return splitedTitleFront;
				
				
			}
			
			
			
			return title;
		};
	};
	
	this.hidePassword = function() {
		return hidePassword;
	};
	
	this.msgFilter = function() {
		return function(arr, msgVersion) {
			if(arr == null){
				return;
			}
			
			return arr.filter(function(obj){
				return obj.msgVersion == msgVersion;
			});
		}
	};

	
};

(function(){
	
	let ngSupport = angular.module("ngSupport", []);
	
	let ngDirective = new NgDirective();
	ngSupport.directive("ngConfirmClick", ngDirective.ngConfirmClick);
	ngSupport.directive("dlEnterKey", ngDirective.dlEnterKey);
	ngSupport.directive("stopPropagationEvent", ngDirective.stopPropagationEvent);	
	
})();

// ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
// ↑↑↑↑↑↑↑↑↑↑↑↑↑'filters for Angular JS' JavaScript Object ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
// ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

