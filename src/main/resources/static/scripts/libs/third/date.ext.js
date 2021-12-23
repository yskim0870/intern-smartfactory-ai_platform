// 날짜 yyyy-mm-dd로 변경
Date.prototype.yyyy_mm_dd = function() {
  var mm = this.getMonth() + 1;
  var dd = this.getDate();

  return [this.getFullYear() + "-" ,
          (mm>9 ? '' : '0') + mm  + "-",
          (dd>9 ? '' : '0') + dd
         ].join('');
};

// 날짜를 'yyyy-MM-ddThh:mm:ss.zzz'
Date.prototype.setOperatorTimestampStr = function() {
	var yyyy = this.getFullYear();
	var mm = this.getMonth() + 1;
	var dd = this.getDate();
	
	var hh = this.getHours();
	var mm2 = this.getMinutes();
	var ss = this.getSeconds();
	var zzz = this.getMilliseconds();
	
	return [this.getFullYear() + "-" ,
		(mm>9 ? '' : '0') + mm  + "-",
		(dd>9 ? '' : '0') + dd + "T",
		
		(hh>9 ? '' : '0') + hh + ":",
		(mm2>9 ? '' : '0') + mm2 + ":",
		(ss>9 ? '' : '0') + ss + ".",
		(zzz>9 ? (zzz>99 ? '' : '0') : '00') + zzz		
		].join('');
};



