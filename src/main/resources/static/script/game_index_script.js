let minus = document.querySelector('.minus');
let plus = document.querySelector('.plus');
let clearButton = document.querySelector('.rere');

let count = 4;
let imgName = '';
let destNum = '';
let insertCanvas = '';

$(document).ready(function(){
    let formObj = $("form");

   $(".button_text").on("click",function (e) {
       formObj.attr("action", "/start/next").attr("method","post");
       formObj.submit();
   });
});

$(function(){
    var form = {
        userNum : count
    }
    $.ajax({
        url: "/start/countNum",
        type: "POST",
        data: form,
        dataType : 'json',
        success: function(data){
            console.log(data);
        },
        error: function(){
            alert("통신 중 에러가 발생했습니다.");
        }
    });
    document.getElementById('userCount').value = count.toString();
});

//초기화 버튼 작동
clearButton.addEventListener('click', ()=>{
    for(let i = 0 ; i < count; i++){
        let textId = 'text' + (i+1).toString();
        document.getElementById(textId).value = '';
    }
});

//인원수를 줄였을 때의 작업
minus.addEventListener('click',()=>{

    if(count > 2) {
        imgName = "";
        destNum = ""
        count--;

        //입력창 비활성화
        document.getElementById("text" + (count+1).toString()).value= '';
        $("#text" + (count+1).toString()).attr("disabled",true);

        document.getElementById("num_name").innerHTML = count.toString();
        for(let i = 1; i <=count; i++) {
            imgName += '<li><img src="img/' + i.toString() + '.png" alt="" class = img' + i.toString() + '></li>'
            destNum += '<li id = "dest' + i.toString() + '">' + i.toString() + '</li>'
        }
        document.getElementById("animal_image").innerHTML = imgName;
        document.getElementById('destination_list').innerHTML = destNum;
    }
    else{
        alert("더이상 줄일 수 없습니다!");
    }
    var form = {
        userNum : count
    }
    $.ajax({
        url: "/start/countNum",
        type: "POST",
        data: form,
        dataType : 'json',
        success: function(data){
            console.log(data);
            document.getElementById('userCount').value = count.toString();
        },
        error: function(){
            alert("통신 중 에러가 발생했습니다.");
        }
    });
});

//인원수를 늘렸을 때의 작업
plus.addEventListener('click',()=>{
    if(count < 4) {
        imgName = "";
        destNum = ""
        count++;
        document.getElementById("num_name").innerHTML = count.toString();

        //입력창 활성화
        - $("#text" + (count).toString()).removeAttr("disabled");

        for(let i = 1; i <=count; i++) {
            imgName += '<li><img src="img/' + i.toString() + '.png" alt=""></li>'
            destNum += '<li id = "dest' + i.toString() + '">' + i.toString() + '</li>'
        }
        document.getElementById("animal_image").innerHTML = imgName;
        document.getElementById('destination_list').innerHTML = destNum;
    }
    else{
        alert("더이상 늘릴 수 없습니다!");
    }
    var form = {
        userNum : count
    }
    $.ajax({
        url: "/start/countNum",
        type: "POST",
        data: form,
        dataType : 'json',
        success: function(data){
            console.log(data);
            document.getElementById('userCount').value = count.toString();
        },
        error: function(){
            alert("통신 중 에러가 발생했습니다.");
        }
    });
});
