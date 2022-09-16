var main = {
    init: function(){
        var _this = this;
        $('.wish_Btn').on('click', function(){
            _this.likes();
        });
        $('.cart_Btn').on('click', function(){
            _this.cart();
        });
    },
    likes : function(){
        $.ajax({
        type:'POST',
        url: window.location.pathname+"/like",
        async: false,
        success: function(){
            alert("찜 목록에 상품을 등록했습니다!");
        },
        error: function(jqXHR, error){
            if (jqXHR.status == '403'){
                alert("찜 등록을 위해서는, 로그인이 필요합니다!");
                window.location.href='/users/signin';
                return invalidSessionModal();
            }
            else {
                return errorModal();
            }
        }
        })
    },
    cart : function(){
        if (sizeCheck() == -1)
            return;

        var data = {
            size: $('#size').val()
        };

        $.ajax({
            type:'POST',
            url: window.location.pathname+"/cart",
            dataType:'text',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data),
            async: false,
            success: function(){
                alert("장바구니에 상품을 등록했습니다!");
            },
            error: function(jqXHR, error){
                if (jqXHR.status == '403'){
                    alert("장바구니 등록을 위해서는, 로그인이 필요합니다!");
                    window.location.href='/users/signin';
                    return invalidSessionModal();
                }
                else {
                    alert(error);
                }
            }
        });
    }
}

main.init();

function sizeCheck(){
    var form = document.createElement("form");
    var param = new Array();
    var input = new Array();

    var size_input = document.getElementById("size");
    var size_value = size_input.options[size_input.selectedIndex].value;

    if (size_value == "notSelected"){
        alert("사이즈를 선택해주세요.");
        return -1;
    }
}

function deleteBtn(url){
    var form = document.createElement("form");
    form.action = url;
    form.method = "post";
    document.body.appendChild(form);
    form.submit();
}
