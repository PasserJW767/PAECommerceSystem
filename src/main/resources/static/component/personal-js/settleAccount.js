function amountSub(){
    var amountStr = document.getElementById("amount").value;
    var amount = parseFloat(amountStr);//数量转为字符串
    //    进行判断、比较、修改
    if(amount - 1 < 1){
        alert("购买量不允许小于1kg！");
        amount = 1;
        document.getElementById("amount").value = amount;
    }
    else{
        amount = amount - 1;
        document.getElementById("amount").value = amount;
    }
    var unitStr = document.getElementById("unit").innerText;
    var unit = parseFloat(unitStr);
    var allValue = unit * amount;
    var allValueText = allValue.toString();
    document.getElementById("allValue").innerText = allValueText;
}

//点击后mount中的值加1
function amountAdd(){
    var amountStr = document.getElementById("amount").value;
    var amount = parseFloat(amountStr);//数量转为字符串
    //    获得库存
    var repertoryStr = document.getElementById("maxRepertory").innerText;
    var repertory = parseFloat(repertoryStr);
    //    进行判断、比较、修改
    if(repertory < amount + 1){
        alert("购买量不允许超过库存最大值！");
        amount = repertory;
        document.getElementById("amount").value = amount;
    }
    else{
        amount = amount + 1;
        document.getElementById("amount").value = amount;
    }
    var unitStr = document.getElementById("unit").innerText;
    var unit = parseFloat(unitStr);
    var allValue = unit * amount;
    var allValueText = allValue.toString();
    document.getElementById("allValue").innerText = allValueText;
}

//根据文本框输入的内容改变具体的价格
function changeAmount(){
    var amountStr = document.getElementById("amount").value;
    var amount = parseFloat(amountStr);
    var repertoryStr = document.getElementById("maxRepertory").innerText;
    var repertory = parseFloat(repertoryStr);
    //对输入的数量进行判断
    if(amount > repertory){
        alert("不允许超过最大库存！");
        amount = repertory;
        document.getElementById("amount").value = amount;
    }
    else if(amount < 1){
        alert("最少购买1kg该商品！");
        amount = 1;
    }
    if(amountStr == "")
        amount = 0;
    //计算总价值，并赋给id为allValue的span标签
    var unitStr = document.getElementById("unit").innerText;
    var unit = parseFloat(unitStr);
    var allValue = unit * amount;
    var allValueText = allValue.toString();
    document.getElementById("allValue").innerText = allValueText;
}