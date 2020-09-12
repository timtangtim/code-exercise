package com.projectx.webreqestlib

class BaseResponse<T> (var code:Int, var message:String, var data:T){
}