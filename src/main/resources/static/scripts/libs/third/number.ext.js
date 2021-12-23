/**
* @ProgramName : number.ext.js
*
* Description: This is a xxxxxxxxxxxxxxxxxxxxxxxxxxxxx, and is executed continuously and interrupted
* Only to perform in case of reset or failure detection.
* @Package : src/main/webapp/scripts/libs/third
* @Project : ksb.beeai.webtoolkit_2018                            
 * @Type :  number.ext
*
* @Revision_history:
*   Date : 2018. 5. 16.,  Author : yskim,  Version : 1.0
* 
 * COPYRIGHT(c) 2016, KSB(Knowledge-converged Super Brain) Convergence Research Department, ETRI.
* 
 * Opensource License:
* Copyright (C) 2016 The KSB Open Source Project
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
// 숫자 타입에서 쓸 수 있도록 format() 함수 추가
Number.prototype.comma = function(){
    if(this==0) return 0;
 
    var reg = /(^[+-]?\d+)(\d{3})/;
    var n = (this + '');
 
    while (reg.test(n)) n = n.replace(reg, '$1' + ',' + '$2');
 
    return n;
};