

$(document).ready(function(){
    let userCount = document.getElementById("userCount").value;

    //결과 띄우기 및 불필요한 요소들 보이지 않게 하기
    for(let i = 0; i < 4; i++){
        if((i+1) > userCount){
            let imgId = 'img' + (i+1).toString();
            let boxId = 'box' + (i+1).toString();

            document.getElementById(imgId).style.display = 'none';
            document.getElementById(boxId).style.display = 'none';
        }
        else{
            let boxId = 'box' + (i+1).toString();
            let resultId = 'result' + (i+1).toString() + '_invisible';
            document.getElementById(boxId).innerHTML = document.getElementById(resultId).value;
        }
    }

    if(Number(userCount) === 3){
        for(let i = 0; i < userCount; i++){
            let boxId = 'box' + (i+1).toString();
            document.getElementById(boxId).style.height = '23%';

        }
    }
    else if(Number(userCount) == 2){
        for(let i = 0; i < userCount; i++){
            let boxId = 'box' + (i+1).toString();
            document.getElementById(boxId).style.height = '33%';
        }
    }

    let formObj = $("form");

    //되돌리기 버튼 작업
    $('.button_51').on("click",function(e){
        e.preventDefault();

        formObj.attr("action", "/").attr("method","post");
        formObj.submit();
    });

});