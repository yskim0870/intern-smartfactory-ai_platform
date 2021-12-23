/*
 *  
 *
 * Date  : 2014. 8. 9. 오후 5:53:48
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

/**
 * 브라우저 관련 유틸리디.
 */
BrowserUtils = function() {
};

/**
 * 
 * @param navigator
 *            {object}: document.navigator
 * @param event
 *            {object}: MouseEvent
 * @returns
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @since 2015. 1. 29.
 */
BrowserUtils.getEventButton = function(navigator, event) {
	var userAgent = navigator.userAgent.toLocaleLowerCase();

	if (userAgent.contains("chrome")) {
		return event.button;
	} else //
	if (userAgent.contains("firefox")) {
		return event.buttons;
	}

	return undefined;
}

/**
 * 
 * 
 * @param url
 *            JavaScript file URL
 * @param callback
 *            a function to execute after loaded a JavaScript file
 * @param parameters
 *            a parameters of a callback function.
 * 
 * <ul>
 * <li>_this_: a this object of a callback function
 * <li>_arguments_: a parameters of a callback function
 * </ul>
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @since 2017. 12. 29.
 */
BrowserUtils.loadScript = function(url, callback, parameters) {

	var loaded = false;
	// check if a javascript file already loaded...
	var scripts = document.getElementsByTagName("script");
	for (var i = 0; Object.available(scripts) && i < scripts.length; i++) {
		if (scripts[i].src === document.origin + url) {
			loaded = true;
			continue;
		}
	}

	if (loaded) {
		if (Object.available(callback)) {
			callback.call();
		}
	} else {
		// Adding the script tag to the head as suggested before
		var head = document.getElementsByTagName('head')[0];
		var script = document.createElement('script');
		script.type = 'text/javascript';
		script.src = url;

		// Then bind the event to the callback function.
		// There are several events for cross browser compatibility.
		script.onreadystatechange = callback;
		script.onload = callback;

		// Fire the loading
		head.appendChild(script);
	}
};