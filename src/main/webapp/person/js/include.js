/*
 * @Author: your name
 * @Date: 2020-04-21 06:29:22
 * @LastEditTime: 2020-04-21 15:25:38
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \person\js\include.js
 */
$(function () {
    $.get("header1.html",function (data) {
        $("#header").html(data);
    });
    $.get("footer.html",function (data) {
        $("#footer").html(data);
    });
});