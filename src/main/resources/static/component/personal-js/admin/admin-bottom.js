var marqueeContent=new Array();
marqueeContent[0]="助农兴商，惠及城乡。";
marqueeContent[1]="家中电商好平台，四面八方引财来。";
marqueeContent[2]="牵手电商，致富奔小康。";
marqueeContent[3]="电商进村，特产出村。";
marqueeContent[4]="家中网上开店铺，家家户户能致富。";
marqueeContent[5]="电子商务进农村，脱贫致富奔小康。";
marqueeContent[6]="我的生活，我的精彩，我的电商平台。";
marqueeContent[7]="农特产品网上卖，乡村振兴赢未来。";
marqueeContent[8]="电子商务进农村，农特产品成网红。";
marqueeContent[9]="电商平台多创意，精彩生活添新意。";
marqueeContent[10]="农民要想富，电商是条路。";
marqueeContent[11]="电子商务新时代，农特产品大舞台。";
marqueeContent[12]="买货卖货到网上，省钱赚钱两不误。";
marqueeContent[13]="农够鲜，够省，够便捷。";
var marqueeInterval=new Array();
var marqueeId=0;
var marqueeDelay=2000;
var marqueeHeight=22;
function initMarquee() {
    var str=marqueeContent[0];
    document.write('<div id="marqueeBox" style="font-size:15px;margin:-50px 200px 0px 700px;color:white;overflow:hidden;width:500px;height:'+marqueeHeight+'px" onmouseover="clearInterval(marqueeInterval[0])" onmouseout="marqueeInterval[0]=setInterval(\'startMarquee()\',marqueeDelay)"><div>'+str+'</div></div>');
    marqueeId++;
    marqueeInterval[0]=setInterval("startMarquee()",marqueeDelay);
}
function startMarquee() {
    var str=marqueeContent[marqueeId];
    marqueeId++;
    if(marqueeId>=marqueeContent.length) marqueeId=0;
    if(document.getElementById("marqueeBox").childNodes.length==1) {
        var nextLine=document.createElement('DIV');
        nextLine.innerHTML=str;
        document.getElementById("marqueeBox").appendChild(nextLine);
    }
    else {
        document.getElementById("marqueeBox").childNodes[0].innerHTML=str;
        document.getElementById("marqueeBox").appendChild(document.getElementById("marqueeBox").childNodes[0]);
        document.getElementById("marqueeBox").scrollTop=0;
    }
    clearInterval(marqueeInterval[1]);
    marqueeInterval[1]=setInterval("scrollMarquee()",20);
}
function scrollMarquee() {
    document.getElementById("marqueeBox").scrollTop++;
    if(document.getElementById("marqueeBox").scrollTop%marqueeHeight==(marqueeHeight-1)){
        clearInterval(marqueeInterval[1]);
    }
}
initMarquee();