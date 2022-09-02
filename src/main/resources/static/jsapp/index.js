
// product 여기서 실행됨 왜ㅐ지..?
function addList(url){ // jQuery 방식으로 수정하기..

    var form = document.createElement("form");
    form.action = url;
    form.method = "post";
    document.body.appendChild(form);
    form.submit();
    alert("찜 목록에 상품을 등록했습니다!"); // --> 이거는 리다이렉션 되고 나서 해야 할 듯..

}
function addCart(url){
    var form = document.createElement("form");
    var param = new Array();
    var input = new Array();

    var size_input = document.getElementById("size");
    var size_value = size_input.options[size_input.selectedIndex].value;

    if (size_value == "notSelected"){
        alert("사이즈를 선택해주세요.");
        return;
    }


    form.action = url;
    form.method = "post";

    param.push(['productNo', '{{product.productNo}}']);
    param.push(['size', size_value]);

    for (var i=0; i<param.length; i++){
        input[i] = document.createElement("input");
        input[i].setAttribute("type", "hidden");
        input[i].setAttribute("name", param[i][0]);
        input[i].setAttribute("value", param[i][1]);
        form.appendChild(input[i]);
    }

    document.body.appendChild(form);
    form.submit();

    alert("장바구니에 상품을 등록했습니다!");
}

function deleteBtn(url){
    var form = document.createElement("form");
    form.action = url;
    form.method = "post";
    document.body.appendChild(form);
    form.submit();
}

